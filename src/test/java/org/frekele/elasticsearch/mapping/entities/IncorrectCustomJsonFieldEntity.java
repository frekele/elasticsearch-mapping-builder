package org.frekele.elasticsearch.mapping.entities;

import org.frekele.elasticsearch.mapping.annotations.ElasticCustomJsonField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;

@ElasticDocument("incorrect")
public class IncorrectCustomJsonFieldEntity {

    @ElasticCustomJsonField(path = "/custom/mapping/invalid-not-exist.json")
    private String customValue;

    public IncorrectCustomJsonFieldEntity() {
    }
}
