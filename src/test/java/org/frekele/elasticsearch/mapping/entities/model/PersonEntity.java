package org.frekele.elasticsearch.mapping.entities.model;

import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.ElasticObjectField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;

import java.util.List;

@ElasticDocument(value = "person")
public class PersonEntity {

    @ElasticLongField
    private Long id;

    @ElasticTextField
    private String name;

    @ElasticObjectField
    private List<AddressEntity> multipleAddress;

    public PersonEntity() {
    }
}
