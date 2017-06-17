package serializationInterfaces;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Inherited
/**
 * Annotation is used for annotate fields that are need to be serialized even they are not public or with transient modifier.
 */
public @interface JsonProperty {
    String DEFAULT_NAME = "";

    String name() default DEFAULT_NAME;
}
