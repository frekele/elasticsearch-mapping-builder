package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A half-precision 16-bit IEEE 754 floating point.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/number.html">elasticsearch half float number field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticHalfFloatField {

    FieldType type = FieldType.HALF_FLOAT;

    String suffixName() default "halfFloat";

    Bool coerce() default @Bool(ignore = true);

    @Deprecated
    float boost() default 1.0f;

    Bool docValues() default @Bool(ignore = true);

    Bool ignoreMalformed() default @Bool(ignore = true);

    Bool index() default @Bool(ignore = true);

    String nullValue() default "";

    Bool store() default @Bool(ignore = true);

}
