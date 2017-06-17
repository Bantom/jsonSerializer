package serializationInterfaces;

import java.lang.annotation.*;

@Retention(value= RetentionPolicy.RUNTIME)
@Target(value= ElementType.FIELD)
@Inherited
/**
 * Annotation is used for annotate fields that are not need to be serialized.
 */
public @interface JsonIgnore {
}
