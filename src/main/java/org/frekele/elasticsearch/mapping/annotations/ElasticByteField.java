package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.annotations.values.FloatValue;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A signed 8-bit integer with a minimum value of -128 and a maximum value of 127.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/number.html">elasticsearch byte number field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticByteField {

    FieldType type = FieldType.BYTE;

    String suffixName() default "byte";

    BoolValue coerce() default @BoolValue(ignore = true);

    @Deprecated
    FloatValue boost() default @FloatValue(ignore = true);

    BoolValue docValues() default @BoolValue(ignore = true);

    BoolValue ignoreMalformed() default @BoolValue(ignore = true);

    BoolValue index() default @BoolValue(ignore = true);

    String nullValue() default "";

    BoolValue store() default @BoolValue(ignore = true);

}
