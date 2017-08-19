package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A range of date values represented as unsigned 64-bit integer milliseconds elapsed since system epoch.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/range.html">elasticsearch date range field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticDateRangeField {

    FieldType type = FieldType.DATE_RANGE;

    String suffixName() default "dateRange";

    @Deprecated
    float boost() default 1.0f;

    Bool docValues() default @Bool(ignore = true);

    String format() default "";

    String locale() default "";

    Bool ignoreMalformed() default @Bool(ignore = true);

    Bool index() default @Bool(ignore = true);

    String nullValue() default "";

    Bool store() default @Bool(ignore = true);

}
