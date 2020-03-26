package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Elasticsearch Document Type.
 *
 * @author frekele - Leandro Kersting de Freitas
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticDocument {

    //Document name Type.
    String value();

    BoolValue dynamic() default @BoolValue(ignore = true);

    //add eager_global_ordinals into _parent
    BoolValue eagerGlobalOrdinalsParent() default @BoolValue(ignore = true);

    //add required into _routing
    BoolValue requiredRouting() default @BoolValue(ignore = true);

}
