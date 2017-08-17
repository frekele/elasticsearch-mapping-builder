package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The completion suggester provides auto-complete/search-as-you-type functionality.
 * This is a navigational feature to guide users to relevant results as they are typing, improving search precision.
 * It is not meant for spell correction or did-you-mean functionality like the term or phrase suggesters.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-suggesters-completion.html">elasticsearch completion field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticCompletionField {

    FieldType type = FieldType.COMPLETION;

    String suffixName() default "completion";

    String analyzer() default "";

    String searchAnalyzer() default "";

    boolean preserveSeparators() default true;

    boolean preservePositionIncrements() default true;

    int maxInputLength() default 50;
}
