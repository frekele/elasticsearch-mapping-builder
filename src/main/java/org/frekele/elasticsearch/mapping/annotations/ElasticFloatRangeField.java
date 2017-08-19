package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A range of single-precision 32-bit IEEE 754 floating point values.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/range.html">elasticsearch float range field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticFloatRangeField {

    FieldType type = FieldType.FLOAT_RANGE;

    String suffixName() default "floatRange";

    boolean coerce() default true;

    @Deprecated
    float boost() default 1.0f;

    boolean index() default true;

    boolean store() default false;

}
