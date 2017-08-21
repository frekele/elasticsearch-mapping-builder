package org.frekele.elasticsearch.mapping.entities.model;

import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticKeywordField;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.ElasticNestedField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;

@ElasticDocument(value = "author")
public class AuthorEntity {

    @ElasticLongField
    private Long id;

    @ElasticTextField
    private String name;

    @ElasticTextField
    @ElasticKeywordField
    private String artisticName;

    @ElasticNestedField
    private AddressEntity address;

    public AuthorEntity() {
    }
}
