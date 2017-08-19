package org.frekele.elasticsearch.mapping.annotations.values;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FielddataFrequencyFilterValue {

    boolean ignore() default false;

    IntValue min() default @IntValue(ignore = true);

    IntValue max() default @IntValue(ignore = true);

    IntValue minSegmentSize() default @IntValue(ignore = true);

}
