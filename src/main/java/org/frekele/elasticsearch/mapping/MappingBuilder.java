package org.frekele.elasticsearch.mapping;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticKeywordField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;
import org.frekele.elasticsearch.mapping.exceptions.InvalidDocumentClassException;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MappingBuilder implements Serializable {

    private List<Class> docsClass;

    private XContentBuilder mapping;

    public MappingBuilder(Class... documentClass) {
        if (documentClass != null && documentClass.length > 0) {
            this.docsClass = Arrays.asList(documentClass);
        } else {
            this.docsClass = new ArrayList<>(0);
        }
        this.validateElasticDocument();
    }

    static boolean isElasticDocument(Class documentClass) {
        return (documentClass.isAnnotationPresent(ElasticDocument.class));
    }

    void validateElasticDocument() {
        for (Class clazz : this.docsClass) {
            if (!isElasticDocument(clazz)) {
                throw new InvalidDocumentClassException("Document Class[" + clazz.getCanonicalName() + "] Invalid. @ElasticDocument must be present.");
            }
        }
    }

    static boolean containElasticFieldAnnotation(Field field) {
        List<Annotation> result = getElasticFieldAnnotations(field);
        return (result != null && !result.isEmpty());
    }

    static boolean isElasticFieldAnnotation(Annotation annotation) {
        return (annotation instanceof ElasticTextField || annotation instanceof ElasticKeywordField);
    }

    static List<Annotation> getElasticFieldAnnotations(Field field) {
        List<Annotation> result = new ArrayList<>();
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations != null && annotations.length != 0) {
            for (Annotation annotation : annotations) {
                if (isElasticFieldAnnotation(annotation)) {
                    result.add(annotation);
                }
            }
            return result;
        } else {
            return null;
        }
    }

    void processElasticField(Annotation annotation, boolean subField) throws IOException {
        if (annotation instanceof ElasticTextField) {
            this.processElasticField((ElasticTextField) annotation, subField);
        }
        if (annotation instanceof ElasticKeywordField) {
            this.processElasticField((ElasticKeywordField) annotation, subField);
        }
    }

    void processElasticField(ElasticTextField elasticField, boolean subField) throws IOException {
        if (subField) {
            //Add suffixName to subField;
            this.mapping.startObject(elasticField.suffixName());
        }
        this.mapping.field("type", ElasticTextField.type.getName());
        if (!elasticField.analyzer().isEmpty()) {
            this.mapping.field("analyzer", elasticField.analyzer());
        }
        //default 100
        if (elasticField.boost() != 1.0f) {
            this.mapping.field("boost", elasticField.boost());
        }
        //Default false
        if (elasticField.fielddata()) {
            this.mapping.field("fielddata", elasticField.fielddata());
        }
        if (elasticField.fielddataFrequencyFilter().enabled()) {
            this.mapping.startObject("fielddata_frequency_filter");
            this.mapping.field("min", elasticField.fielddataFrequencyFilter().min());
            this.mapping.field("max", elasticField.fielddataFrequencyFilter().max());
            this.mapping.field("min_segment_size", elasticField.fielddataFrequencyFilter().minSegmentSize());
            //fielddata_frequency_filter
            this.mapping.endObject();
        }
        //Default true
        if (!elasticField.index()) {
            this.mapping.field("index", elasticField.index());
        }
        if (!elasticField.indexOptions().isEmpty()) {
            this.mapping.field("index_options", elasticField.indexOptions());
        }
        //Default true
        if (!elasticField.norms()) {
            this.mapping.field("norms", elasticField.norms());
        }
        //default 100
        if (elasticField.positionIncrementGap() != 100) {
            this.mapping.field("positionIncrementGap", elasticField.positionIncrementGap());
        }
        //Default false
        if (elasticField.store()) {
            this.mapping.field("store", elasticField.store());
        }
        if (!elasticField.searchAnalyzer().isEmpty()) {
            this.mapping.field("search_analyzer", elasticField.searchAnalyzer());
        }
        if (!elasticField.searchQuoteAnalyzer().isEmpty()) {
            this.mapping.field("search_quote_analyzer", elasticField.searchQuoteAnalyzer());
        }
        if (!elasticField.similarity().isEmpty()) {
            this.mapping.field("similarity", elasticField.similarity());
        }
        if (!elasticField.termVector().isEmpty()) {
            this.mapping.field("term_vector", elasticField.termVector());
        }
        if (subField) {
            //Close suffixName to subField;
            this.mapping.endObject();
        }
    }

    void processElasticField(ElasticKeywordField elasticField, boolean subField) throws IOException {
        if (subField) {
            //Add suffixName to subField;
            this.mapping.startObject(elasticField.suffixName());
        }
        this.mapping.field("type", ElasticKeywordField.type.getName());
        if (!elasticField.analyzer().isEmpty()) {
            this.mapping.field("analyzer", elasticField.analyzer());
        }
        //default 100
        if (elasticField.boost() != 1.0f) {
            this.mapping.field("boost", elasticField.boost());
        }
        //Default false
        if (elasticField.docValues()) {
            this.mapping.field("doc_values", elasticField.docValues());
        }
        //default 256
        if (elasticField.ignoreAbove() != 256) {
            this.mapping.field("ignore_above", elasticField.ignoreAbove());
        }
        //Default true
        if (!elasticField.index()) {
            this.mapping.field("index", elasticField.index());
        }
        if (!elasticField.indexOptions().isEmpty()) {
            this.mapping.field("index_options", elasticField.indexOptions());
        }
        //Default true
        if (!elasticField.norms()) {
            this.mapping.field("norms", elasticField.norms());
        }
        if (!elasticField.nullValue().isEmpty()) {
            this.mapping.field("null_value", elasticField.nullValue());
        }
        //Default false
        if (elasticField.store()) {
            this.mapping.field("store", elasticField.store());
        }
        if (!elasticField.similarity().isEmpty()) {
            this.mapping.field("similarity", elasticField.similarity());
        }
        if (!elasticField.normalizer().isEmpty()) {
            this.mapping.field("normalizer", elasticField.normalizer());
        }
        if (subField) {
            //Close suffixName to subField;
            this.mapping.endObject();
        }
    }

    public XContentBuilder source() throws IOException {
        if (this.mapping == null) {
            return build();
        } else {
            return this.mapping;
        }
    }

    public String sourceAsString() throws IOException {
        return this.source().string();
    }

    XContentBuilder build() throws IOException {
        if (this.mapping == null) {
            this.mapping = XContentFactory.jsonBuilder();
            this.mapping.prettyPrint();
            //BEGIN
            this.mapping.startObject();
            this.mapping.startObject("mappings");

            for (Class clazz : this.docsClass) {
                ElasticDocument elasticDocument = (ElasticDocument) clazz.getAnnotation(ElasticDocument.class);
                this.mapping.startObject(elasticDocument.value());
                Field[] fields = clazz.getDeclaredFields();

                if (fields != null && fields.length > 0) {
                    this.mapping.startObject("properties");
                    for (Field field : fields) {
                        if (containElasticFieldAnnotation(field)) {
                            this.mapping.startObject(field.getName());
                            List<Annotation> annotationList = getElasticFieldAnnotations(field);
                            if (!annotationList.isEmpty()) {
                                //Get main Field (The First)
                                Annotation mainAnnotation = annotationList.get(0);
                                this.processElasticField(mainAnnotation, false);
                                annotationList.remove(mainAnnotation);

                                //If contains more fields.
                                if (!annotationList.isEmpty()) {
                                    this.mapping.startObject("fields");
                                    for (Annotation otherAnnotation : annotationList) {
                                        this.processElasticField(otherAnnotation, true);
                                    }
                                    //fields
                                    this.mapping.endObject();
                                }
                            }
                            //field
                            this.mapping.endObject();
                        }
                    }

                    //properties
                    this.mapping.endObject();
                }

                //ElasticDocument
                this.mapping.endObject();
            }

            //mappings
            this.mapping.endObject();
            //END
            this.mapping.endObject();
        }
        return this.mapping;
    }
}
