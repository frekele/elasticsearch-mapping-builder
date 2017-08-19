package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An ip field can index/store either IPv4 or IPv6 addresses.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/ip.html">elasticsearch ip field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticIpField {

    FieldType type = FieldType.IP;

    String suffixName() default "ip";

    @Deprecated
    float boost() default 1.0f;

    Bool docValues() default @Bool(ignore = true);

    Bool index() default @Bool(ignore = true);

    String nullValue() default "";

    Bool store() default @Bool(ignore = true);

}
