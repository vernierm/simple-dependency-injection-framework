package vernierm.dependency.injection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ApplicationContext {
    public <T> T getInstance(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = clazz.getConstructor();
        T t = constructor.newInstance();

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
}
