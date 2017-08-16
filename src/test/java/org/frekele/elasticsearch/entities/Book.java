package org.frekele.elasticsearch.entities;

import org.frekele.elasticsearch.annotations.Field;
import org.frekele.elasticsearch.annotations.Fields;
import org.frekele.elasticsearch.annotations.Mapping;
import org.frekele.elasticsearch.enums.FieldType;

@Mapping
public class Book {

    @Field(type = FieldType.LONG)
    private Long id;

    @Field(type = FieldType.TEXT)
    private String name;

    @Fields(type = FieldType.TEXT, fields = {
        @Field(type = FieldType.KEYWORD)}
    )
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
