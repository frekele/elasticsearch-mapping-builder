package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;
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

    Bool coerce() default @Bool(ignore = true);

    @Deprecated
    float boost() default 1.0f;

    Bool docValues() default @Bool(ignore = true);

    Bool ignoreMalformed() default @Bool(ignore = true);

    Bool index() default @Bool(ignore = true);

    String nullValue() default "";

    Bool store() default @Bool(ignore = true);

}
