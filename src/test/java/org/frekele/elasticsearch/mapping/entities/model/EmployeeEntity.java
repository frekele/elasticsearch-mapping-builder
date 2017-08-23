package org.frekele.elasticsearch.mapping.entities.model;

import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticKeywordField;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;

@ElasticDocument(value = "employee", parent = "person")
public class EmployeeEntity {

    @ElasticLongField
    private Long id;

    @ElasticKeywordField
    private String documentNumber;

    @ElasticTextField
    @ElasticKeywordField
    private String registerNumber;

    public EmployeeEntity() {
    }
}
