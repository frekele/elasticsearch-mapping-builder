package org.frekele.elasticsearch.mapping;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.frekele.elasticsearch.mapping.annotations.ElasticBinaryField;
import org.frekele.elasticsearch.mapping.annotations.ElasticBooleanField;
import org.frekele.elasticsearch.mapping.annotations.ElasticByteField;
import org.frekele.elasticsearch.mapping.annotations.ElasticCompletionField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDateField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDateRangeField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticDoubleField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDoubleRangeField;
import org.frekele.elasticsearch.mapping.annotations.ElasticFloatField;
import org.frekele.elasticsearch.mapping.annotations.ElasticFloatRangeField;
import org.frekele.elasticsearch.mapping.annotations.ElasticGeoPointField;
import org.frekele.elasticsearch.mapping.annotations.ElasticGeoShapeField;
import org.frekele.elasticsearch.mapping.annotations.ElasticHalfFloatField;
import org.frekele.elasticsearch.mapping.annotations.ElasticIntegerField;
import org.frekele.elasticsearch.mapping.annotations.ElasticIntegerRangeField;
import org.frekele.elasticsearch.mapping.annotations.ElasticIpField;
import org.frekele.elasticsearch.mapping.annotations.ElasticIpRangeField;
import org.frekele.elasticsearch.mapping.annotations.ElasticKeywordField;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongRangeField;
import org.frekele.elasticsearch.mapping.annotations.ElasticNestedField;
import org.frekele.elasticsearch.mapping.annotations.ElasticObjectField;
import org.frekele.elasticsearch.mapping.annotations.ElasticPercolatorField;
import org.frekele.elasticsearch.mapping.annotations.ElasticScaledFloatField;
import org.frekele.elasticsearch.mapping.annotations.ElasticShortField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTextField;
import org.frekele.elasticsearch.mapping.annotations.ElasticTokenCountField;
import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.annotations.values.FielddataFrequencyFilterValue;
import org.frekele.elasticsearch.mapping.annotations.values.FloatValue;
import org.frekele.elasticsearch.mapping.annotations.values.IntValue;
import org.frekele.elasticsearch.mapping.enums.FieldType;
import org.frekele.elasticsearch.mapping.exceptions.InvalidDocumentClassException;
import org.frekele.elasticsearch.mapping.values.DateFieldValue;
import org.frekele.elasticsearch.mapping.values.NumericFieldValue;
import org.frekele.elasticsearch.mapping.values.RangeFieldValue;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class MappingBuilder implements Serializable {

    private int MAX_RECURSIVE_LEVEL = 50;

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
                || annotation instanceof ElasticDateField
                //Boolean datatype
                || annotation instanceof ElasticBooleanField
                //Binary datatype
                || annotation instanceof ElasticBinaryField
                //Range datatypes
                || annotation instanceof ElasticIntegerRangeField || annotation instanceof ElasticFloatRangeField
                || annotation instanceof ElasticLongRangeField || annotation instanceof ElasticDoubleRangeField
                || annotation instanceof ElasticIpRangeField || annotation instanceof ElasticDateRangeField
                //Complex datatypes
                || annotation instanceof ElasticObjectField || annotation instanceof ElasticNestedField
                //Geo datatypes
                || annotation instanceof ElasticGeoPointField || annotation instanceof ElasticGeoShapeField
                //Specialised datatypes
                || annotation instanceof ElasticIpField || annotation instanceof ElasticCompletionField
                || annotation instanceof ElasticTokenCountField || annotation instanceof ElasticPercolatorField
        );

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
        //Boolean datatype
        else if (annotation instanceof ElasticBooleanField) {
            this.processElasticField((ElasticBooleanField) annotation, subField);
        }
        //Binary datatype
        else if (annotation instanceof ElasticBinaryField) {
            this.processElasticField((ElasticBinaryField) annotation, subField);
        }
        //Range datatypes
        else if (annotation instanceof ElasticIntegerRangeField) {
            this.processElasticField((ElasticIntegerRangeField) annotation, subField);
        } else if (annotation instanceof ElasticFloatRangeField) {
            this.processElasticField((ElasticFloatRangeField) annotation, subField);
        } else if (annotation instanceof ElasticLongRangeField) {
            this.processElasticField((ElasticLongRangeField) annotation, subField);
        } else if (annotation instanceof ElasticDoubleRangeField) {
            this.processElasticField((ElasticDoubleRangeField) annotation, subField);
        } else if (annotation instanceof ElasticIpRangeField) {
            this.processElasticField((ElasticIpRangeField) annotation, subField);
        } else if (annotation instanceof ElasticDateRangeField) {
            this.processElasticField((ElasticDateRangeField) annotation, subField);
        }
        //Geo datatypes
        else if (annotation instanceof ElasticGeoPointField) {
            this.processElasticField((ElasticGeoPointField) annotation, subField);
        } else if (annotation instanceof ElasticGeoShapeField) {
            this.processElasticField((ElasticGeoShapeField) annotation, subField);
        }
        //Specialised datatypes
        else if (annotation instanceof ElasticIpField) {
            this.processElasticField((ElasticIpField) annotation, subField);
        } else if (annotation instanceof ElasticCompletionField) {
            this.processElasticField((ElasticCompletionField) annotation, subField);
        } else if (annotation instanceof ElasticTokenCountField) {
            this.processElasticField((ElasticTokenCountField) annotation, subField);
        } else if (annotation instanceof ElasticPercolatorField) {
            this.processElasticField((ElasticPercolatorField) annotation, subField);
        }
    }

    boolean isValueEnabled(BoolValue boolValue) {
        return boolValue != null && !boolValue.ignore();
    }

    boolean isValueEnabled(FloatValue floatValue) {
        return floatValue != null && !floatValue.ignore();
    }

    boolean isValueEnabled(IntValue intValue) {
        return intValue != null && !intValue.ignore();
    }

    boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    void addField(String name, BoolValue value) throws IOException {
        if (this.isValueEnabled(value)) {
            this.mapping.field(name, value.value());
        }
    }

    void addField(String name, FloatValue value) throws IOException {
        if (this.isValueEnabled(value)) {
            this.mapping.field(name, value.value());
        }
    }

    void addField(String name, IntValue value) throws IOException {
        if (this.isValueEnabled(value)) {
            this.mapping.field(name, value.value());
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

    //Direct set.
    void nested(boolean nested) throws IOException {
        this.mapping.field("nested", nested);
    }

    void dynamic(BoolValue dynamic) throws IOException {
        this.addField("dynamic", dynamic);
    }

    void enabledJson(BoolValue enabledJson) throws IOException {
        this.addField("enabled", enabledJson);
    }

    void type(FieldType type) throws IOException {
        this.mapping.field("type", type.getName());
    }

    void analyzer(String analyzer) throws IOException {
        if (!analyzer.isEmpty()) {
            this.mapping.field("analyzer", analyzer);
        }
    }

    void boost(FloatValue boost) throws IOException {
        this.addField("boost", boost);
    }

    void eagerGlobalOrdinals(BoolValue eagerGlobalOrdinals) throws IOException {
        this.addField("eager_global_ordinals", eagerGlobalOrdinals);
    }

    void fielddata(BoolValue fielddata) throws IOException {
        this.addField("fielddata", fielddata);
    }

    void fielddataFrequencyFilter(FielddataFrequencyFilterValue fielddataFrequencyFilter) throws IOException {
        if (!fielddataFrequencyFilter.ignore()) {
            this.mapping.startObject("fielddata_frequency_filter");
            if (this.isValueEnabled(fielddataFrequencyFilter.min())) {
                this.mapping.field("min", fielddataFrequencyFilter.min().value());
            }
            if (this.isValueEnabled(fielddataFrequencyFilter.max())) {
                this.mapping.field("max", fielddataFrequencyFilter.max().value());
            }
            if (this.isValueEnabled(fielddataFrequencyFilter.minSegmentSize())) {
                this.mapping.field("min_segment_size", fielddataFrequencyFilter.minSegmentSize().value());
            }
            //fielddata_frequency_filter
            this.mapping.endObject();
        }
    }

    void includeInAll(BoolValue includeInAll) throws IOException {
        this.addField("include_in_all", includeInAll);
    }

    void index(BoolValue index) throws IOException {
        this.addField("index", index);
    }

    void indexOptions(String indexOptions) throws IOException {
        if (!indexOptions.isEmpty()) {
            this.mapping.field("index_options", indexOptions);
        }
    }

    void norms(BoolValue norms) throws IOException {
        this.addField("norms", norms);
    }

    void positionIncrementGap(IntValue positionIncrementGap) throws IOException {
        this.addField("position_increment_gap", positionIncrementGap);
    }

    void store(BoolValue store) throws IOException {
        this.addField("store", store);
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

    void copyTo(String[] copyTo) throws IOException {
        if (copyTo != null && copyTo.length > 0) {
            if (copyTo.length == 1) {
                this.mapping.field("copy_to", copyTo[0]);
            } else {
                this.mapping.array("copy_to", copyTo);
            }
        }
    }

    void docValues(BoolValue docValues) throws IOException {
        this.addField("doc_values", docValues);
    }

    void ignoreAbove(IntValue ignoreAbove) throws IOException {
        this.addField("ignore_above", ignoreAbove);
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

    void coerce(BoolValue coerce) throws IOException {
        this.addField("coerce", coerce);
    }

    void ignoreMalformed(BoolValue ignoreMalformed) throws IOException {
        this.addField("ignore_malformed", ignoreMalformed);
    }

    void scalingFactor(IntValue scalingFactor) throws IOException {
        this.addField("scaling_factor", scalingFactor);
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

    void tree(String tree) throws IOException {
        if (!tree.isEmpty()) {
            this.mapping.field("tree", tree);
        }
    }

    void precision(String precision) throws IOException {
        if (!precision.isEmpty()) {
            this.mapping.field("precision", precision);
        }
    }

    void treeLevels(String treeLevels) throws IOException {
        if (!treeLevels.isEmpty()) {
            this.mapping.field("tree_levels", treeLevels);
        }
    }

    void strategy(String strategy) throws IOException {
        if (!strategy.isEmpty()) {
            this.mapping.field("strategy", strategy);
        }
    }

    void distanceErrorPct(FloatValue distanceErrorPct) throws IOException {
        this.addField("distance_error_pct", distanceErrorPct);
    }

    void orientation(String orientation) throws IOException {
        if (!orientation.isEmpty()) {
            this.mapping.field("orientation", orientation);
        }
    }

    void pointsOnly(BoolValue pointsOnly) throws IOException {
        this.addField("points_only", pointsOnly);
    }

    void preserveSeparators(BoolValue preserveSeparators) throws IOException {
        this.addField("preserve_separators", preserveSeparators);
    }

    void preservePositionIncrements(BoolValue preservePositionIncrements) throws IOException {
        this.addField("preserve_position_increments", preservePositionIncrements);
    }

    void maxInputLength(IntValue maxInputLength) throws IOException {
        this.addField("max_input_length", maxInputLength);
    }

    void enablePositionIncrements(BoolValue enablePositionIncrements) throws IOException {
        this.addField("enable_position_increments", enablePositionIncrements);
    }

    void processElasticField(ElasticTextField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.analyzer(elasticField.analyzer());
        this.boost(elasticField.boost());
        this.eagerGlobalOrdinals(elasticField.eagerGlobalOrdinals());
        this.fielddata(elasticField.fielddata());
        this.fielddataFrequencyFilter(elasticField.fielddataFrequencyFilter());
        this.includeInAll(elasticField.includeInAll());
        this.index(elasticField.index());
        this.indexOptions(elasticField.indexOptions());
        this.norms(elasticField.norms());
        this.positionIncrementGap(elasticField.positionIncrementGap());
        this.store(elasticField.store());
        this.searchAnalyzer(elasticField.searchAnalyzer());
        this.searchQuoteAnalyzer(elasticField.searchQuoteAnalyzer());
        this.similarity(elasticField.similarity());
        this.termVector(elasticField.termVector());
        this.copyTo(elasticField.copyTo());
        this.closeSuffixName(subField);
    }

    void processElasticField(ElasticKeywordField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.analyzer(elasticField.analyzer());
        this.boost(elasticField.boost());
        this.docValues(elasticField.docValues());
        this.eagerGlobalOrdinals(elasticField.eagerGlobalOrdinals());
        this.ignoreAbove(elasticField.ignoreAbove());
        this.includeInAll(elasticField.includeInAll());
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
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticIntegerField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticShortField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticByteField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticDoubleField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticFloatField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticHalfFloatField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticScaledFloatField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), elasticField.scalingFactor());
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticDateField elasticField, boolean subField) throws IOException {
        DateFieldValue vo = new DateFieldValue(elasticField.type, elasticField.suffixName(), elasticField.boost(),
            elasticField.docValues(), elasticField.format(), elasticField.locale(), elasticField.ignoreMalformed(), elasticField.includeInAll(),
            elasticField.index(), elasticField.nullValue(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticBooleanField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.boost(elasticField.boost());
        this.docValues(elasticField.docValues());
        this.index(elasticField.index());
        this.nullValue(elasticField.nullValue());
        this.store(elasticField.store());
        this.closeSuffixName(subField);
    }

    void processElasticField(ElasticBinaryField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.docValues(elasticField.docValues());
        this.store(elasticField.store());
        this.closeSuffixName(subField);
    }

    void processElasticField(RangeFieldValue vo, boolean subField) throws IOException {
        this.startSuffixName(subField, vo.getSuffixName());
        this.type(vo.getType());
        this.coerce(vo.getCoerce());
        this.boost(vo.getBoost());
        this.index(vo.getIndex());
        this.store(vo.getStore());
        this.closeSuffixName(subField);
    }

    void processElasticField(ElasticIntegerRangeField elasticField, boolean subField) throws IOException {
        RangeFieldValue vo = new RangeFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.includeInAll(), elasticField.index(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticFloatRangeField elasticField, boolean subField) throws IOException {
        RangeFieldValue vo = new RangeFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.includeInAll(), elasticField.index(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticLongRangeField elasticField, boolean subField) throws IOException {
        RangeFieldValue vo = new RangeFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.includeInAll(), elasticField.index(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticDoubleRangeField elasticField, boolean subField) throws IOException {
        RangeFieldValue vo = new RangeFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.includeInAll(), elasticField.index(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticIpRangeField elasticField, boolean subField) throws IOException {
        RangeFieldValue vo = new RangeFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.includeInAll(), elasticField.index(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    void processElasticField(DateFieldValue vo, boolean subField) throws IOException {
        this.type(vo.getType());
        this.boost(vo.getBoost());
        this.docValues(vo.getDocValues());
        this.format(vo.getFormat());
        this.locale(vo.getLocale());
        this.ignoreMalformed(vo.getIgnoreMalformed());
        this.index(vo.getIndex());
        this.nullValue(vo.getNullValue());
        this.store(vo.getStore());
        this.closeSuffixName(subField);
    }

    void processElasticField(ElasticDateRangeField elasticField, boolean subField) throws IOException {
        DateFieldValue vo = new DateFieldValue(elasticField.type, elasticField.suffixName(), elasticField.boost(),
            elasticField.docValues(), elasticField.format(), elasticField.locale(), elasticField.ignoreMalformed(), elasticField.includeInAll(),
            elasticField.index(), elasticField.nullValue(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    void processElasticField(ElasticGeoPointField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.ignoreMalformed(elasticField.ignoreMalformed());
        this.closeSuffixName(subField);
    }

    void processElasticField(ElasticGeoShapeField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.tree(elasticField.tree());
        this.precision(elasticField.precision());
        this.treeLevels(elasticField.treeLevels());
        this.strategy(elasticField.strategy());
        this.distanceErrorPct(elasticField.distanceErrorPct());
        this.orientation(elasticField.orientation());
        this.pointsOnly(elasticField.pointsOnly());
        this.closeSuffixName(subField);
    }

    void processElasticField(ElasticIpField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.boost(elasticField.boost());
        this.docValues(elasticField.docValues());
        this.includeInAll(elasticField.includeInAll());
        this.index(elasticField.index());
        this.nullValue(elasticField.nullValue());
        this.store(elasticField.store());
        this.closeSuffixName(subField);
    }

    void processElasticField(ElasticCompletionField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.analyzer(elasticField.analyzer());
        this.searchAnalyzer(elasticField.searchAnalyzer());
        this.preserveSeparators(elasticField.preserveSeparators());
        this.preservePositionIncrements(elasticField.preservePositionIncrements());
        this.maxInputLength(elasticField.maxInputLength());
        this.closeSuffixName(subField);
    }

    void processElasticField(ElasticTokenCountField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.analyzer(elasticField.analyzer());
        this.enablePositionIncrements(elasticField.enablePositionIncrements());
        this.boost(elasticField.boost());
        this.docValues(elasticField.docValues());
        this.index(elasticField.index());
        this.includeInAll(elasticField.includeInAll());
        this.nullValue(elasticField.nullValue());
        this.store(elasticField.store());
        this.closeSuffixName(subField);
    }

    void processElasticField(ElasticPercolatorField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.closeSuffixName(subField);
    }

    Field[] getInnerFields(Field field) {
        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType parameterizedType = ((ParameterizedType) field.getGenericType());
            Type type = parameterizedType.getActualTypeArguments()[0];
            try {
                return Class.forName(((Class) type).getCanonicalName()).getDeclaredFields();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new InvalidDocumentClassException(e.getCause());
            }
        } else {
            return field.getType().getDeclaredFields();
        }
    }

    void recursiveFields(Field[] fields, int level) throws IOException {
        if (level > MAX_RECURSIVE_LEVEL) {
            return;
            //  throw new MaxRecursiveLevelClassException("Max json level has reached " + MAX_RECURSIVE_LEVEL);
        }
        ++level;
        if (fields != null && fields.length > 0) {
            this.mapping.startObject("properties");
            for (Field field : fields) {
                if (containElasticFieldAnnotation(field)) {
                    this.mapping.startObject(field.getName());
                    //Object.
                    if (field.isAnnotationPresent(ElasticObjectField.class)) {
                        ElasticObjectField elasticDocument = field.getAnnotation(ElasticObjectField.class);
                        this.dynamic(elasticDocument.dynamic());
                        this.enabledJson(elasticDocument.enabledJson());
                        this.recursiveFields(this.getInnerFields(field), level);
                        this.includeInAll(elasticDocument.includeInAll());
                    }
                    //Nested.
                    else if (field.isAnnotationPresent(ElasticNestedField.class)) {
                        ElasticNestedField elasticDocument = field.getAnnotation(ElasticNestedField.class);
                        this.nested(true);
                        this.dynamic(elasticDocument.dynamic());
                        this.includeInAll(elasticDocument.includeInAll());
                        this.recursiveFields(this.getInnerFields(field), level);
                    }
                    //Fields.
                    else {
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
                    }
                    //field
                    this.mapping.endObject();

                }
            }
            //properties
            this.mapping.endObject();
        }
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

                //Parent
                if (isNotEmpty(elasticDocument.parent())) {
                    this.mapping.startObject("_parent");
                    this.mapping.field("type", elasticDocument.parent());
                    this.eagerGlobalOrdinals(elasticDocument.eagerGlobalOrdinals());
                    this.mapping.endObject();
                }

                this.dynamic(elasticDocument.dynamic());
                this.includeInAll(elasticDocument.includeInAll());

                Field[] fields = clazz.getDeclaredFields();
                this.recursiveFields(fields, 0);

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
