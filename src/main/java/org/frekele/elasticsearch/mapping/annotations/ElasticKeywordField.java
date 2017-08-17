package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/keyword.html">elasticsearch keyword field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticKeywordField {

    FieldType type = FieldType.KEYWORD;

    String suffixName() default "keyword";

    String analyzer() default "";

    float boost() default 1.0f;

    boolean docValues() default false;

    int ignoreAbove() default 256;

    boolean index() default true;

    String indexOptions() default "";

    boolean norms() default true;

    String nullValue() default "";

    boolean store() default false;

    String similarity() default "";

    String normalizer() default "";

}
