package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JSON documents are hierarchical in nature: the document may contain inner objects which, in turn, may contain inner objects themselves.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/object.html">Site Elasticsearch Reference Guide.</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticObjectField {

    FieldType type = FieldType.OBJECT;

    BoolValue dynamic() default @BoolValue(ignore = true);

    //If false, just store the field without indexing it.
    BoolValue enabledJson() default @BoolValue(ignore = true);

    BoolValue includeInAll() default @BoolValue(ignore = true);

}
