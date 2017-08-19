package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A double-precision 64-bit IEEE 754 floating point.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/number.html">elasticsearch double number field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticDoubleField {

    FieldType type = FieldType.DOUBLE;

    String suffixName() default "double";

    BoolValue coerce() default @BoolValue(ignore = true);

    @Deprecated
    float boost() default 1.0f;

    BoolValue docValues() default @BoolValue(ignore = true);

    BoolValue ignoreMalformed() default @BoolValue(ignore = true);

    BoolValue index() default @BoolValue(ignore = true);

    String nullValue() default "";

    BoolValue store() default @BoolValue(ignore = true);

}
