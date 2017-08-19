package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A floating point that is backed by a long and a fixed scaling factor.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/number.html">elasticsearch scaled float number field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticScaledFloatField {

    FieldType type = FieldType.SCALED_FLOAT;

    String suffixName() default "scaledFloat";

    boolean coerce() default true;

    @Deprecated
    float boost() default 1.0f;

    boolean docValues() default false;

    boolean ignoreMalformed() default false;

    boolean index() default true;

    String nullValue() default "";

    boolean store() default false;

    int scalingFactor() default -1;

}
