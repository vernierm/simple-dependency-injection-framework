package vernierm.dependency.injection.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ComponentScans.class)
public @interface ComponentScan {
    String value();
}
