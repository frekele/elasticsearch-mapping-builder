package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.ElasticFielddataFrequencyFilter;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/text.html">elasticsearch text field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticTextField {

    FieldType type = FieldType.TEXT;

    String analyzer() default "";

    //float boost() default 1.0f;

    boolean fielddata() default false;

    ElasticFielddataFrequencyFilter fielddataFrequencyFilter() default @ElasticFielddataFrequencyFilter();

    boolean index() default true;

    String indexOptions() default "";

    boolean norms() default true;

    int positionIncrementGap() default 100;

    boolean store() default false;

    String searchAnalyzer() default "";

    String searchQuoteAnalyzer() default "";

    String similarity() default "";

    String termVector() default "";
}
