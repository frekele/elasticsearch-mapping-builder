package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A range of signed 64-bit integers with a minimum value of -263 and maximum of 263-1.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/range.html">elasticsearch long range field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticLongRangeField {

    FieldType type = FieldType.LONG_RANGE;

    String suffixName() default "longRange";

    boolean coerce() default true;

    float boost() default 1.0f;

    boolean index() default true;

    boolean store() default false;

}
