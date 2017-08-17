package org.frekele.elasticsearch.mapping.entities;

import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.ElasticNestedField;
import org.frekele.elasticsearch.mapping.annotations.ElasticObjectField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;

@ElasticDocument(value = "person")
public class Person {

    @ElasticLongField
    private Long id;

    @ElasticTextField
    private String name;

    @ElasticObjectField
    private Address address;

    @ElasticNestedField
    private Address address2;

    public Person() {
    }
}
