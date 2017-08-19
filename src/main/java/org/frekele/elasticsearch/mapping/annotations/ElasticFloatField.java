package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A single-precision 32-bit IEEE 754 floating point.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/number.html">elasticsearch float number field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticFloatField {

    FieldType type = FieldType.FLOAT;

    String suffixName() default "float";

    boolean coerce() default true;

    @Deprecated
    float boost() default 1.0f;

    boolean docValues() default false;

    boolean ignoreMalformed() default false;

    boolean index() default true;

    String nullValue() default "";

    boolean store() default false;

}
