package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The binary type accepts a binary value as a Base64 encoded string. The field is not stored by default and is not searchable.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/binary.html">elasticsearch binary field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticBinaryField {

    FieldType type = FieldType.BINARY;

    String suffixName() default "binary";

    Bool docValues() default @Bool(ignore = true);

    Bool store() default @Bool(ignore = true);
}
