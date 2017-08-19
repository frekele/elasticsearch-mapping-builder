package org.frekele.elasticsearch.mapping.annotations;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Document Type.
 *
 * @author frekele - Leandro Kersting de Freitas
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticDocument {

    //Document name Type.
    String value();

    Bool dynamic() default @Bool(ignore = true);
}
