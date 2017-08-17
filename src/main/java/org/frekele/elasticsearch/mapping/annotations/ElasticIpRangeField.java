package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A range of ip values supporting either IPv4 or IPv6 (or mixed) addresses.
 *
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/range.html">elasticsearch ip range field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticIpRangeField {

    FieldType type = FieldType.IP_RANGE;

    String suffixName() default "ipRange";

    boolean coerce() default true;

    float boost() default 1.0f;

    boolean index() default true;

    boolean store() default false;

}
