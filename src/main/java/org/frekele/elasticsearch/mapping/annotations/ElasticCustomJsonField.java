package org.frekele.elasticsearch.mapping.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add a custom JSON into field.
 *
 * @author frekele - Leandro Kersting de Freitas
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticCustomJsonField {

    String path();

    Class classLoader() default ClassLoader.class;
}
