package org.frekele.elasticsearch.mapping;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.frekele.elasticsearch.mapping.annotations.ElasticByteField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDateField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticDoubleField;
import org.frekele.elasticsearch.mapping.annotations.ElasticFloatField;
import org.frekele.elasticsearch.mapping.annotations.ElasticHalfFloatField;
import org.frekele.elasticsearch.mapping.annotations.ElasticIntegerField;
import org.frekele.elasticsearch.mapping.annotations.ElasticKeywordField;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.ElasticScaledFloatField;
import org.frekele.elasticsearch.mapping.annotations.ElasticShortField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;
import org.frekele.elasticsearch.mapping.annotations.values.ElasticFielddataFrequencyFilter;
import org.frekele.elasticsearch.mapping.enums.FieldType;
import org.frekele.elasticsearch.mapping.exceptions.InvalidDocumentClassException;
import org.frekele.elasticsearch.mapping.values.NumericFieldValue;

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

    static boolean containElasticFieldAnnotation(Field field) {
        List<Annotation> result = getElasticFieldAnnotations(field);
        return (result != null && !result.isEmpty());
    }

    static boolean isElasticFieldAnnotation(Annotation annotation) {
        return (
            //String datatypes
            annotation instanceof ElasticTextField || annotation instanceof ElasticKeywordField
                //Numeric datatypes
                || annotation instanceof ElasticLongField || annotation instanceof ElasticIntegerField
                || annotation instanceof ElasticShortField || annotation instanceof ElasticByteField
                || annotation instanceof ElasticDoubleField || annotation instanceof ElasticFloatField
                || annotation instanceof ElasticHalfFloatField || annotation instanceof ElasticScaledFloatField
                //Date datatype
                || annotation instanceof ElasticDateField);
    }

    void processElasticAnnotationField(Annotation annotation, boolean subField) throws IOException {
        //String datatypes
        if (annotation instanceof ElasticTextField) {
            this.processElasticField((ElasticTextField) annotation, subField);
        } else if (annotation instanceof ElasticKeywordField) {
            this.processElasticField((ElasticKeywordField) annotation, subField);
        }
        //Numeric datatypes
        else if (annotation instanceof ElasticLongField) {
            this.processElasticField((ElasticLongField) annotation, subField);
        } else if (annotation instanceof ElasticIntegerField) {
            this.processElasticField((ElasticIntegerField) annotation, subField);
        } else if (annotation instanceof ElasticShortField) {
            this.processElasticField((ElasticShortField) annotation, subField);
        } else if (annotation instanceof ElasticByteField) {
            this.processElasticField((ElasticByteField) annotation, subField);
        } else if (annotation instanceof ElasticDoubleField) {
            this.processElasticField((ElasticDoubleField) annotation, subField);
        } else if (annotation instanceof ElasticFloatField) {
            this.processElasticField((ElasticFloatField) annotation, subField);
        } else if (annotation instanceof ElasticHalfFloatField) {
            this.processElasticField((ElasticHalfFloatField) annotation, subField);
        } else if (annotation instanceof ElasticScaledFloatField) {
            this.processElasticField((ElasticScaledFloatField) annotation, subField);
        }
        //Date datatype
        else if (annotation instanceof ElasticDateField) {
            this.processElasticField((ElasticDateField) annotation, subField);
        }
    }

    void startSuffixName(boolean subField, String suffixName) throws IOException {
        if (subField) {
            //Add suffixName to subField;
            this.mapping.startObject(suffixName);
        }
    }

    void closeSuffixName(boolean subField) throws IOException {
        if (subField) {
            //Add suffixName to subField;
            this.mapping.endObject();
        }
    }

    void type(FieldType type) throws IOException {
        this.mapping.field("type", type.getName());
    }

    void analyzer(String analyzer) throws IOException {
        if (!analyzer.isEmpty()) {
            this.mapping.field("analyzer", analyzer);
        }
    }

    void boost(float boost) throws IOException {
        //default 1.0
        if (boost != 1.0f) {
            this.mapping.field("boost", boost);
        }
    }

    void fielddata(boolean fielddata) throws IOException {
        //Default false
        if (fielddata) {
            this.mapping.field("fielddata", fielddata);
        }
    }

    void fielddataFrequencyFilter(ElasticFielddataFrequencyFilter fielddataFrequencyFilter) throws IOException {
        if (fielddataFrequencyFilter.enabled()) {
            this.mapping.startObject("fielddata_frequency_filter");
            this.mapping.field("min", fielddataFrequencyFilter.min());
            this.mapping.field("max", fielddataFrequencyFilter.max());
            this.mapping.field("min_segment_size", fielddataFrequencyFilter.minSegmentSize());
            //fielddata_frequency_filter
            this.mapping.endObject();
        }
    }

    void index(boolean index) throws IOException {
        //Default true
        if (!index) {
            this.mapping.field("index", index);
        }
    }

    void indexOptions(String indexOptions) throws IOException {
        if (!indexOptions.isEmpty()) {
            this.mapping.field("index_options", indexOptions);
        }
    }

    void norms(boolean norms) throws IOException {
        //Default true
        if (!norms) {
            this.mapping.field("norms", norms);
        }
    }

    void positionIncrementGap(int positionIncrementGap) throws IOException {
        //default 100
        if (positionIncrementGap != 100) {
            this.mapping.field("positionIncrementGap", positionIncrementGap);
        }
    }

    void store(boolean store) throws IOException {
        //Default false
        if (store) {
            this.mapping.field("store", store);
        }
    }

    void searchAnalyzer(String searchAnalyzer) throws IOException {
        if (!searchAnalyzer.isEmpty()) {
            this.mapping.field("search_analyzer", searchAnalyzer);
        }
    }

    void searchQuoteAnalyzer(String searchQuoteAnalyzer) throws IOException {
        if (!searchQuoteAnalyzer.isEmpty()) {
            this.mapping.field("search_quote_analyzer", searchQuoteAnalyzer);
        }
    }

    void similarity(String similarity) throws IOException {
        if (!similarity.isEmpty()) {
            this.mapping.field("similarity", similarity);
        }
    }

    void termVector(String termVector) throws IOException {
        if (!termVector.isEmpty()) {
            this.mapping.field("term_vector", termVector);
        }
    }

    void docValues(boolean docValues) throws IOException {
        //Default false
        if (docValues) {
            this.mapping.field("doc_values", docValues);
        }
    }

    void ignoreAbove(int ignoreAbove) throws IOException {
        //default 256
        if (ignoreAbove != 256) {
            this.mapping.field("ignore_above", ignoreAbove);
        }
    }

    void nullValue(String nullValue) throws IOException {
        if (!nullValue.isEmpty()) {
            this.mapping.field("null_value", nullValue);
        }
    }

    void normalizer(String normalizer) throws IOException {
        if (!normalizer.isEmpty()) {
            this.mapping.field("normalizer", normalizer);
        }
    }

    void coerce(boolean coerce) throws IOException {
        //Default true
        if (!coerce) {
            this.mapping.field("coerce", coerce);
        }
    }

    void ignoreMalformed(boolean ignoreMalformed) throws IOException {
        //Default false
        if (ignoreMalformed) {
            this.mapping.field("ignore_malformed", ignoreMalformed);
        }
    }

    void scalingFactor(int scalingFactor) throws IOException {
        if (scalingFactor != -1) {
            this.mapping.field("scaling_factor", scalingFactor);
        }
    }

    void format(String format) throws IOException {
        if (!format.isEmpty()) {
            this.mapping.field("format", format);
        }
    }

    void locale(String locale) throws IOException {
        if (!locale.isEmpty()) {
            this.mapping.field("locale", locale);
        }
    }

    void processElasticField(ElasticTextField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.analyzer(elasticField.analyzer());
        this.boost(elasticField.boost());
        this.fielddata(elasticField.fielddata());
        this.fielddataFrequencyFilter(elasticField.fielddataFrequencyFilter());
        this.index(elasticField.index());
        this.indexOptions(elasticField.indexOptions());
        this.norms(elasticField.norms());
        this.positionIncrementGap(elasticField.positionIncrementGap());
        this.store(elasticField.store());
        this.searchAnalyzer(elasticField.searchAnalyzer());
        this.searchQuoteAnalyzer(elasticField.searchQuoteAnalyzer());
        this.similarity(elasticField.similarity());
        this.termVector(elasticField.termVector());
        this.closeSuffixName(subField);
    }

    void processElasticField(ElasticKeywordField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.analyzer(elasticField.analyzer());
        this.boost(elasticField.boost());
        this.docValues(elasticField.docValues());
        this.ignoreAbove(elasticField.ignoreAbove());
        this.index(elasticField.index());
        this.indexOptions(elasticField.indexOptions());
        this.norms(elasticField.norms());
        this.nullValue(elasticField.nullValue());
        this.store(elasticField.store());
        this.similarity(elasticField.similarity());
        this.normalizer(elasticField.normalizer());
        this.closeSuffixName(subField);
    }

    void processElasticField(NumericFieldValue vo, boolean subField) throws IOException {
        this.startSuffixName(subField, vo.getSuffixName());
        this.type(vo.getType());
        this.coerce(vo.getCoerce());
        this.boost(vo.getBoost());
        this.docValues(vo.getDocValues());
        this.ignoreMalformed(vo.getIgnoreMalformed());
        this.index(vo.getIndex());
        this.nullValue(vo.getNullValue());
        this.store(vo.getStore());
        this.scalingFactor(vo.getScalingFactor());
        this.closeSuffixName(subField);
    }

    void processElasticField(ElasticLongField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), -1);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticIntegerField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), -1);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticShortField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), -1);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticByteField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), -1);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticDoubleField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), -1);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticFloatField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), -1);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticHalfFloatField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), -1);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticScaledFloatField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), elasticField.scalingFactor());
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticDateField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.boost(elasticField.boost());
        this.docValues(elasticField.docValues());
        this.format(elasticField.format());
        this.locale(elasticField.locale());
        this.ignoreMalformed(elasticField.ignoreMalformed());
        this.index(elasticField.index());
        this.nullValue(elasticField.nullValue());
        this.store(elasticField.store());
        this.closeSuffixName(subField);
    }

    public XContentBuilder source() throws IOException {
        return build(false);
    }

    public XContentBuilder source(boolean pretty) throws IOException {
        return build(pretty);
    }

    public String sourceAsString() throws IOException {
        return this.source().string();
    }

    public String sourceAsString(boolean pretty) throws IOException {
        return this.source(pretty).string();
    }

    XContentBuilder build(boolean pretty) throws IOException {
        if (this.mapping == null) {
            this.mapping = XContentFactory.jsonBuilder();
            if (pretty) {
                this.mapping.prettyPrint();
            }
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
                                this.processElasticAnnotationField(mainAnnotation, false);
                                annotationList.remove(mainAnnotation);

                                //If contains more fields.
                                if (!annotationList.isEmpty()) {
                                    this.mapping.startObject("fields");
                                    for (Annotation otherAnnotation : annotationList) {
                                        this.processElasticAnnotationField(otherAnnotation, true);
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
