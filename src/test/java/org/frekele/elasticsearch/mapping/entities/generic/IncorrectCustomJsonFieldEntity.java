package org.frekele.elasticsearch.mapping.entities.generic;

import org.frekele.elasticsearch.mapping.annotations.ElasticCustomJsonField;

public class IncorrectCustomJsonFieldEntity {

    @ElasticCustomJsonField(path = "/custom/mapping/invalid-not-exist.json")
    private String customValue;

    public IncorrectCustomJsonFieldEntity() {
    }
}
