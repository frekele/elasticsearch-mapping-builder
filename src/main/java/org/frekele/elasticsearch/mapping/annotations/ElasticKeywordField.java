package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.annotations.values.FloatValue;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A field to index structured content such as email addresses, hostnames, status codes, zip codes or tags.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/keyword.html">elasticsearch keyword field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticKeywordField {

    FieldType type = FieldType.KEYWORD;

    String suffixName() default "keyword";

    String analyzer() default "";

    @Deprecated
    FloatValue boost() default @FloatValue(ignore = true);

    BoolValue docValues() default @BoolValue(ignore = true);

    int ignoreAbove() default 256;

    BoolValue index() default @BoolValue(ignore = true);

    String indexOptions() default "";

    BoolValue norms() default @BoolValue(ignore = true);

    String nullValue() default "";

    BoolValue store() default @BoolValue(ignore = true);

    String similarity() default "";

    String normalizer() default "";

}
