package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A signed 16-bit integer with a minimum value of -32,768 and a maximum value of 32,767.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/number.html">elasticsearch short number field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticShortField {

    FieldType type = FieldType.SHORT;

    String suffixName() default "short";

    boolean coerce() default true;

    @Deprecated
    float boost() default 1.0f;

    boolean docValues() default false;

    boolean ignoreMalformed() default false;

    boolean index() default true;

    String nullValue() default "";

    boolean store() default false;

}
