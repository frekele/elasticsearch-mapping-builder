package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A field to index full-text values, such as the body of an email or the description of a product.
 *
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/binary.html">elasticsearch binary field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticBinaryField {

    FieldType type = FieldType.BINARY;

    String suffixName() default "binary";

    boolean docValues() default false;

    boolean store() default false;
}
