package org.frekele.elasticsearch.mapping.entities.model;

import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticKeywordField;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.ElasticObjectField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;

import java.util.List;

@ElasticDocument(value = "person")

public class PersonEntity {

    @ElasticLongField
    private Long id;

    @ElasticTextField
    @ElasticKeywordField
    private String name;

    @ElasticTextField(copyTo = {"name"})
    private String fistName;

    @ElasticTextField(copyTo = {"name"})
    private String lastName;

    @ElasticObjectField
    private List<AddressEntity> multipleAddress;

    public PersonEntity() {
    }
}
