package org.frekele.elasticsearch.mapping.annotations.values;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FloatValue {

    boolean ignore() default false;

    float value() default 0.0f;

}
