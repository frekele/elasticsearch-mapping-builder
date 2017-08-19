package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;
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

    Bool coerce() default @Bool(ignore = true);

    @Deprecated
    float boost() default 1.0f;

    Bool docValues() default @Bool(ignore = true);

    Bool ignoreMalformed() default @Bool(ignore = true);

    Bool index() default @Bool(ignore = true);

    String nullValue() default "";

    Bool store() default @Bool(ignore = true);

}
