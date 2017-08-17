package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JSON documents are hierarchical in nature: the document may contain inner objects which, in turn, may contain inner objects themselves.
 *
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/object.html">elasticsearch object field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticObjectField {

    FieldType type = FieldType.OBJECT;

    boolean dynamic() default true;

    //If false, just store the field without indexing it.
    boolean enabled() default true;

}
