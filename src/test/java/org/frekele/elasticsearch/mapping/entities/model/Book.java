package org.frekele.elasticsearch.mapping.entities.model;

import org.frekele.elasticsearch.mapping.annotations.ElasticBinaryField;
import org.frekele.elasticsearch.mapping.annotations.ElasticBooleanField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDateField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticKeywordField;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;

import java.time.OffsetDateTime;

@ElasticDocument("book")
public class Book {

    @ElasticLongField
    private Long id;

    @ElasticTextField
    @ElasticKeywordField
    private String name;

    @ElasticTextField
    private String description;

    @ElasticDateField
    private OffsetDateTime releaseDate;

    @ElasticBooleanField
    private Boolean active;

    @ElasticBinaryField
    private String blob;

    public Book() {
    }
}
