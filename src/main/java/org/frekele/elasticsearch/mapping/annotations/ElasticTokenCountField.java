package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;
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

    Bool enablePositionIncrements() default @Bool(ignore = true);

    @Deprecated
    float boost() default 1.0f;

    Bool docValues() default @Bool(ignore = true);

    Bool index() default @Bool(ignore = true);

    String nullValue() default "";

    Bool store() default @Bool(ignore = true);

}
