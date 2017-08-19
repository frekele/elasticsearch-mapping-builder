package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.annotations.values.FloatValue;
import org.frekele.elasticsearch.mapping.enums.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A range of signed 32-bit integers with a minimum value of -231 and maximum of 231-1.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/range.html">elasticsearch integer range field</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticIntegerRangeField {

    FieldType type = FieldType.INTEGER_RANGE;

    String suffixName() default "integerRange";

    BoolValue coerce() default @BoolValue(ignore = true);

    @Deprecated
    FloatValue boost() default @FloatValue(ignore = true);

    BoolValue index() default @BoolValue(ignore = true);

    BoolValue store() default @BoolValue(ignore = true);

}
