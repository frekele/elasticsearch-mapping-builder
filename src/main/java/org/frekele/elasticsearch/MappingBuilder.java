package org.frekele.elasticsearch;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.frekele.elasticsearch.annotations.ElasticDocument;
import org.frekele.elasticsearch.exceptions.InvalidDocumentClassException;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MappingBuilder implements Serializable {

    private List<Class> docsClass;

    public MappingBuilder(Class... documentClass) {
        if (documentClass != null && documentClass.length > 0) {
            this.docsClass = Arrays.asList(documentClass);
        }
        this.validateElasticDocument();
    }

    static boolean isElasticDocument(Class documentClass) {
        return (documentClass.isAnnotationPresent(ElasticDocument.class));
    }

    void validateElasticDocument() {
        for (Class clazz : docsClass) {
            if (!isElasticDocument(clazz)) {
                throw new InvalidDocumentClassException("Document Class[" + clazz.getCanonicalName() + "] Invalid. @ElasticDocument must be present.");
            }
        }

    }

    public XContentBuilder build() throws IOException {
        XContentBuilder mapping = XContentFactory.jsonBuilder().startObject().startObject("type1")
            .startObject("properties")
            .startObject("text").field("type", "text").field("analyzer", "keyword").endObject()
            .endObject()
            .endObject().endObject();
        return mapping;
    }

    //public String buildToJson();

    public void teste() throws IOException {

        //  System.out.println(mapping.string());
    }

}
