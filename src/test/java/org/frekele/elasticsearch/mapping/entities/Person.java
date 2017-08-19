package org.frekele.elasticsearch.mapping.entities;

import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.ElasticNestedField;
import org.frekele.elasticsearch.mapping.annotations.ElasticObjectField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;

import java.util.List;

@ElasticDocument(value = "person",parent = "sdfsdfd")
public class Person {

    @ElasticLongField
    private Long id;

    @ElasticTextField(copyTo = {"sdkfjsdklf"})
    private String name;
//
//    @ElasticObjectField
//    private Address objectAddress;
//
//    @ElasticObjectField
//    private List<Address> listObjectAddress;
//
//    @ElasticNestedField
//    private Address nestedAddress;
//
//    @ElasticNestedField
//    private List<Address> listNestedAddress;

    public Person() {
    }
}
