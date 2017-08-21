package org.frekele.elasticsearch.mapping.entities.model;

import org.frekele.elasticsearch.mapping.annotations.ElasticCompletionField;
import org.frekele.elasticsearch.mapping.annotations.ElasticKeywordField;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;

public class Address {

    @ElasticKeywordField
    private String postalCode;

    @ElasticTextField
    @ElasticKeywordField
    @ElasticCompletionField
    private String street;

    @ElasticLongField
    private Long number;

    public Address() {
    }
}
