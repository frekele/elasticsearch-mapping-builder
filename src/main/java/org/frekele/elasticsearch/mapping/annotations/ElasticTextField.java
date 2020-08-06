package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.annotations.values.FielddataFrequencyFilterValue;
import org.frekele.elasticsearch.mapping.annotations.values.FloatValue;
import org.frekele.elasticsearch.mapping.annotations.values.IntValue;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A field to index full-text values, such as the body of an email or the description of a product.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/text.html">Site Elasticsearch Reference Guide.</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticTextField {

    FieldType type = FieldType.TEXT;

    String suffixName() default "text";

    String analyzer() default "";

    @Deprecated
    FloatValue boost() default @FloatValue(ignore = true);

    BoolValue eagerGlobalOrdinals() default @BoolValue(ignore = true);

    BoolValue fielddata() default @BoolValue(ignore = true);

    FielddataFrequencyFilterValue fielddataFrequencyFilter() default @FielddataFrequencyFilterValue(
        ignore = true,
        min = @FloatValue(ignore = true),
        max = @FloatValue(ignore = true),
        minSegmentSize = @IntValue(ignore = true)
    );

    BoolValue index() default @BoolValue(ignore = true);

    String indexOptions() default "";

    BoolValue norms() default @BoolValue(ignore = true);

    IntValue positionIncrementGap() default @IntValue(ignore = true);

    BoolValue store() default @BoolValue(ignore = true);

    String searchAnalyzer() default "";

    String searchQuoteAnalyzer() default "";

    String similarity() default "";

    String termVector() default "";

    String[] copyTo() default {};

}
