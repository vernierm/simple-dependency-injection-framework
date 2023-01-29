package vernierm.dependency.injection;

import vernierm.dependency.injection.annotations.Autowired;
import vernierm.dependency.injection.annotations.Component;
import vernierm.dependency.injection.annotations.ComponentScan;
import vernierm.dependency.injection.annotations.Configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationContext {
    private final Map<Class<?>, Object> objectRegistry = new HashMap<>();

    public ApplicationContext(Class<?> clazz) {
        initializeContext(clazz);
    }

    public <T> T getInstance(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T t = (T) objectRegistry.get(clazz);

        Field[] declaredFields = clazz.getDeclaredFields();
        injectAnnotatedFields(t, declaredFields);

        return t;
    }

    private <T> void injectAnnotatedFields(T t, Field[] declaredFields) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(Autowired.class)) continue;

            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            Object fieldObject = fieldType.getDeclaredConstructor().newInstance();

            field.set(t, fieldObject);
            injectAnnotatedFields(fieldObject, fieldType.getDeclaredFields());
        }
    }

    private void initializeContext(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Configuration.class))
            throw new RuntimeException("Please provide a valid configuration file.");

        ComponentScan componentScan = clazz.getAnnotation(ComponentScan.class);
        String packageValue = componentScan.value();
        Set<Class<?>> classes = findClasses(packageValue);

        for (Class<?> aClass : classes) {
            try {
                if (aClass.isAnnotationPresent(Component.class)) {
                    Constructor<?> constructor = aClass.getDeclaredConstructor();
                    Object o = constructor.newInstance();
                    objectRegistry.put(aClass, o);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Set<Class<?>> findClasses(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        assert stream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
