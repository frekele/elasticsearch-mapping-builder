package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.annotations.values.FloatValue;
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

    BoolValue coerce() default @BoolValue(ignore = true);

    @Deprecated
    FloatValue boost() default @FloatValue(ignore = true);

    BoolValue docValues() default @BoolValue(ignore = true);

    BoolValue ignoreMalformed() default @BoolValue(ignore = true);

    BoolValue index() default @BoolValue(ignore = true);

    String nullValue() default "";

    BoolValue store() default @BoolValue(ignore = true);

}
