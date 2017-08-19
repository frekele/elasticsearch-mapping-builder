package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JSON doesn’t have a date datatype, so dates in Elasticsearch can either be:
 * - strings containing formatted dates, e.g. "2015-01-01" or "2015/01/01 12:10:30".
 * - a long number representing milliseconds-since-the-epoch.
 * - an integer representing seconds-since-the-epoch.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/date.html">elasticsearch date field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticDateField {

    FieldType type = FieldType.DATE;

    String suffixName() default "date";

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
