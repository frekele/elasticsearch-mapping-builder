package org.frekele.elasticsearch.mapping.entities.generic;

import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.ElasticObjectField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;

public class RecursiveErrorDocumentEntity {

    @ElasticLongField
    private Long id;

    @ElasticTextField
    private String name;

    //Validate the loop with exception.
    @ElasticObjectField
    private RecursiveErrorDocumentEntity recursiveDocument;

    public RecursiveErrorDocumentEntity() {
    }

}
