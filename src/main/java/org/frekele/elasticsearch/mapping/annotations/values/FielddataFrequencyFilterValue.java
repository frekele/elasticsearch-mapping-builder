package org.frekele.elasticsearch.mapping.annotations.values;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/fielddata.html#field-data-filtering">Site Elasticsearch Reference Guide.</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FielddataFrequencyFilterValue {

    boolean ignore() default false;

    FloatValue min();

    FloatValue max();

    IntValue minSegmentSize();

}
