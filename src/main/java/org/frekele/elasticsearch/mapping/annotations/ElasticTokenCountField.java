package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A field of type token_count is really an integer field which accepts string values, analyzes them, then indexes the number of tokens in the string.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/token-count.html">elasticsearch token count field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticTokenCountField {

    FieldType type = FieldType.TOKEN_COUNT;

    String suffixName() default "tokenCount";

    String analyzer() default "";

    boolean enablePositionIncrements() default true;

    float boost() default 1.0f;

    boolean docValues() default false;

    boolean index() default true;

    String nullValue() default "";

    boolean store() default false;

}
