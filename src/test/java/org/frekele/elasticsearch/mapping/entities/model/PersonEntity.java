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

    @ElasticTextField
    @ElasticKeywordField
    private String fullName;

    @ElasticTextField(copyTo = {"name", "fullName"})
    private String fistName;

    @ElasticTextField(copyTo = {"fullName"})
    private String lastName;

    @ElasticObjectField
    private List<AddressEntity> multipleAddress;

    public PersonEntity() {
    }
}
