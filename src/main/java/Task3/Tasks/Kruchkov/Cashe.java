package Task3.Tasks.Kruchkov;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Cashe {
 int value() default 10;  // для ускорения тестирования вместо 1000 используется 10
}

