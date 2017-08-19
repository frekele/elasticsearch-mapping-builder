package org.frekele.elasticsearch.mapping.annotations;

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

    boolean docValues() default false;

    String format() default "";

    String locale() default "";

    boolean ignoreMalformed() default false;

    boolean index() default true;

    String nullValue() default "";

    boolean store() default false;

}
