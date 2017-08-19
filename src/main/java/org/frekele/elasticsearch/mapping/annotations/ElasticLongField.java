package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A signed 64-bit integer with a minimum value of -263 and a maximum value of 263-1.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/number.html">elasticsearch long number field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticLongField {

    FieldType type = FieldType.LONG;

    String suffixName() default "long";

    BoolValue coerce() default @BoolValue(ignore = true);

    @Deprecated
    float boost() default 1.0f;

    BoolValue docValues() default @BoolValue(ignore = true);

    BoolValue ignoreMalformed() default @BoolValue(ignore = true);

    BoolValue index() default @BoolValue(ignore = true);

    String nullValue() default "";

    BoolValue store() default @BoolValue(ignore = true);

}
