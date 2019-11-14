package org.frekele.elasticsearch.mapping.entities.model;

import java.time.OffsetDateTime;

import org.frekele.elasticsearch.mapping.annotations.ElasticBinaryField;
import org.frekele.elasticsearch.mapping.annotations.ElasticBooleanField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDateField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticKeywordField;
import org.frekele.elasticsearch.mapping.annotations.ElasticObjectField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;
import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;

@ElasticDocument("book")
public class BookEntity {

    @ElasticKeywordField
    private String isbn;

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
    private String imageBlob;

    @ElasticObjectField(enabledJson = @BoolValue(false))
    private AuthorEntity author;

    public BookEntity() {
    }
}
