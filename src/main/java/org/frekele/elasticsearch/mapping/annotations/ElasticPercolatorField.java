package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The percolator field type parses a json structure into a native query and stores that query, so that the percolate query can use it to match provided documents.
 * Any field that contains a json object can be configured to be a percolator field.
 * The percolator field type has no settings.
 * Just configuring the percolator field type is sufficient to instruct Elasticsearch to treat a field as a query.
 *
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/percolator.html">elasticsearch percolator field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticPercolatorField {

    FieldType type = FieldType.PERCOLATOR;

    String suffixName() default "percolator";

}
