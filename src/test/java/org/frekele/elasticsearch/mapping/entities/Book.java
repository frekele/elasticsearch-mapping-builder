package org.frekele.elasticsearch.mapping.entities;

import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticKeywordField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;

@ElasticDocument("book")
public class Book {

    private Long id;

    @ElasticTextField()
    @ElasticKeywordField()
    private String name;

    private String description;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
