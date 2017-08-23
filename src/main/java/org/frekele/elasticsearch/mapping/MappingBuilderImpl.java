package org.frekele.elasticsearch.mapping;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.frekele.elasticsearch.mapping.annotations.ElasticBinaryField;
import org.frekele.elasticsearch.mapping.annotations.ElasticBooleanField;
import org.frekele.elasticsearch.mapping.annotations.ElasticByteField;
import org.frekele.elasticsearch.mapping.annotations.ElasticCompletionField;
import org.frekele.elasticsearch.mapping.annotations.ElasticCustomJsonField;
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
import org.frekele.elasticsearch.mapping.exceptions.InvalidCustomJsonException;
import org.frekele.elasticsearch.mapping.exceptions.InvalidDocumentClassException;
import org.frekele.elasticsearch.mapping.exceptions.MappingBuilderException;
import org.frekele.elasticsearch.mapping.exceptions.MaxRecursiveLevelClassException;
import org.frekele.elasticsearch.mapping.values.DateFieldValue;
import org.frekele.elasticsearch.mapping.values.NumericFieldValue;
import org.frekele.elasticsearch.mapping.values.RangeFieldValue;

import java.io.IOException;
import java.io.InputStream;
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
public class MappingBuilderImpl implements MappingBuilder {

    private static final long serialVersionUID = 1L;

    private int MAX_RECURSIVE_LEVEL = 50;

    private List<Class> docsClass;

    private XContentBuilder mapping;

    public MappingBuilderImpl(Class... documentClass) {
        if (documentClass == null || documentClass.length == 0) {
            throw new MappingBuilderException("A Document Class is required.");
        } else {
            this.docsClass = Arrays.asList(documentClass);
        }
        this.validateElasticDocument();
    }

    private List<Class> getDocsClass() {
        return docsClass;
    }

    private XContentBuilder getMapping() {
        return mapping;
    }

    private void setMapping(XContentBuilder mapping) {
        this.mapping = mapping;
    }

    private static boolean isElasticDocument(Class documentClass) {
        return (documentClass.isAnnotationPresent(ElasticDocument.class));
    }

    private void validateElasticDocument() {
        for (Class clazz : this.getDocsClass()) {
            if (!isElasticDocument(clazz)) {
                throw new InvalidDocumentClassException("Document Class[" + clazz.getCanonicalName() + "] Invalid. @ElasticDocument must be present.");
            }
        }
    }

    private static List<Annotation> getElasticFieldAnnotations(Field field) {
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

    private static boolean containElasticFieldAnnotation(Field field) {
        List<Annotation> result = getElasticFieldAnnotations(field);
        return (result != null && !result.isEmpty());
    }

    private static boolean isElasticFieldAnnotation(Annotation annotation) {
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
                //Custom Json
                || annotation instanceof ElasticCustomJsonField
        );

    }

    private void processElasticAnnotationField(Annotation annotation, boolean subField) throws IOException {
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

    private boolean isValueEnabled(BoolValue boolValue) {
        return boolValue != null && !boolValue.ignore();
    }

    private boolean isValueEnabled(FloatValue floatValue) {
        return floatValue != null && !floatValue.ignore();
    }

    private boolean isValueEnabled(IntValue intValue) {
        return intValue != null && !intValue.ignore();
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    private void addField(String name, BoolValue value) throws IOException {
        if (this.isValueEnabled(value)) {
            this.getMapping().field(name, value.value());
        }
    }

    private void addField(String name, FloatValue value) throws IOException {
        if (this.isValueEnabled(value)) {
            this.getMapping().field(name, value.value());
        }
    }

    private void addField(String name, IntValue value) throws IOException {
        if (this.isValueEnabled(value)) {
            this.getMapping().field(name, value.value());
        }
    }

    private void addField(String name, String value) throws IOException {
        if (this.isNotEmpty(value)) {
            this.getMapping().field(name, value);
        }
    }

    private void startSuffixName(boolean subField, String suffixName) throws IOException {
        if (subField) {
            //Add suffixName to subField;
            this.getMapping().startObject(suffixName);
        }
    }

    private void closeSuffixName(boolean subField) throws IOException {
        if (subField) {
            //Add suffixName to subField;
            this.getMapping().endObject();
        }
    }

    //Direct set.
    private void nested(boolean nested) throws IOException {
        this.getMapping().field("nested", nested);
    }

    private void dynamic(BoolValue dynamic) throws IOException {
        this.addField("dynamic", dynamic);
    }

    private void enabledJson(BoolValue enabledJson) throws IOException {
        this.addField("enabled", enabledJson);
    }

    private void type(FieldType type) throws IOException {
        this.getMapping().field("type", type.getName());
    }

    private void analyzer(String analyzer) throws IOException {
        if (this.isNotEmpty(analyzer)) {
            this.getMapping().field("analyzer", analyzer);
        }
    }

    private void boost(FloatValue boost) throws IOException {
        this.addField("boost", boost);
    }

    private void eagerGlobalOrdinals(BoolValue eagerGlobalOrdinals) throws IOException {
        this.addField("eager_global_ordinals", eagerGlobalOrdinals);
    }

    private void fielddata(BoolValue fielddata) throws IOException {
        this.addField("fielddata", fielddata);
    }

    private void fielddataFrequencyFilter(FielddataFrequencyFilterValue fielddataFrequencyFilter) throws IOException {
        if (!fielddataFrequencyFilter.ignore()) {
            this.getMapping().startObject("fielddata_frequency_filter");
            if (this.isValueEnabled(fielddataFrequencyFilter.min())) {
                this.getMapping().field("min", fielddataFrequencyFilter.min().value());
            }
            if (this.isValueEnabled(fielddataFrequencyFilter.max())) {
                this.getMapping().field("max", fielddataFrequencyFilter.max().value());
            }
            if (this.isValueEnabled(fielddataFrequencyFilter.minSegmentSize())) {
                this.getMapping().field("min_segment_size", fielddataFrequencyFilter.minSegmentSize().value());
            }
            //fielddata_frequency_filter
            this.getMapping().endObject();
        }
    }

    private void includeInAll(BoolValue includeInAll) throws IOException {
        this.addField("include_in_all", includeInAll);
    }

    private void index(BoolValue index) throws IOException {
        this.addField("index", index);
    }

    private void indexOptions(String indexOptions) throws IOException {
        if (this.isNotEmpty(indexOptions)) {
            this.getMapping().field("index_options", indexOptions);
        }
    }

    private void norms(BoolValue norms) throws IOException {
        this.addField("norms", norms);
    }

    private void positionIncrementGap(IntValue positionIncrementGap) throws IOException {
        this.addField("position_increment_gap", positionIncrementGap);
    }

    private void store(BoolValue store) throws IOException {
        this.addField("store", store);
    }

    private void searchAnalyzer(String searchAnalyzer) throws IOException {
        this.addField("search_analyzer", searchAnalyzer);
    }

    private void searchQuoteAnalyzer(String searchQuoteAnalyzer) throws IOException {
        this.addField("search_quote_analyzer", searchQuoteAnalyzer);
    }

    private void similarity(String similarity) throws IOException {
        this.addField("similarity", similarity);
    }

    private void termVector(String termVector) throws IOException {
        this.addField("term_vector", termVector);
    }

    private void copyTo(String[] copyTo) throws IOException {
        if (copyTo != null && copyTo.length > 0) {
            if (copyTo.length == 1) {
                this.getMapping().field("copy_to", copyTo[0]);
            } else {
                this.getMapping().array("copy_to", copyTo);
            }
        }
    }

    private void docValues(BoolValue docValues) throws IOException {
        this.addField("doc_values", docValues);
    }

    private void ignoreAbove(IntValue ignoreAbove) throws IOException {
        this.addField("ignore_above", ignoreAbove);
    }

    private void nullValue(String nullValue) throws IOException {
        this.addField("null_value", nullValue);
    }

    private void normalizer(String normalizer) throws IOException {
        this.addField("normalizer", normalizer);
    }

    private void coerce(BoolValue coerce) throws IOException {
        this.addField("coerce", coerce);
    }

    private void ignoreMalformed(BoolValue ignoreMalformed) throws IOException {
        this.addField("ignore_malformed", ignoreMalformed);
    }

    private void scalingFactor(IntValue scalingFactor) throws IOException {
        this.addField("scaling_factor", scalingFactor);
    }

    private void format(String format) throws IOException {
        this.addField("format", format);
    }

    private void locale(String locale) throws IOException {
        this.addField("locale", locale);
    }

    private void tree(String tree) throws IOException {
        this.addField("tree", tree);
    }

    private void precision(String precision) throws IOException {
        this.addField("precision", precision);
    }

    private void treeLevels(String treeLevels) throws IOException {
        this.addField("tree_levels", treeLevels);
    }

    private void strategy(String strategy) throws IOException {
        this.addField("strategy", strategy);
    }

    private void distanceErrorPct(FloatValue distanceErrorPct) throws IOException {
        this.addField("distance_error_pct", distanceErrorPct);
    }

    private void orientation(String orientation) throws IOException {
        this.addField("orientation", orientation);
    }

    private void pointsOnly(BoolValue pointsOnly) throws IOException {
        this.addField("points_only", pointsOnly);
    }

    private void preserveSeparators(BoolValue preserveSeparators) throws IOException {
        this.addField("preserve_separators", preserveSeparators);
    }

    private void preservePositionIncrements(BoolValue preservePositionIncrements) throws IOException {
        this.addField("preserve_position_increments", preservePositionIncrements);
    }

    private void maxInputLength(IntValue maxInputLength) throws IOException {
        this.addField("max_input_length", maxInputLength);
    }

    private void enablePositionIncrements(BoolValue enablePositionIncrements) throws IOException {
        this.addField("enable_position_increments", enablePositionIncrements);
    }

    private void processElasticField(ElasticTextField elasticField, boolean subField) throws IOException {
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

    private void processElasticField(ElasticKeywordField elasticField, boolean subField) throws IOException {
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

    private void processElasticField(NumericFieldValue vo, boolean subField) throws IOException {
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

    private void processElasticField(ElasticLongField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticIntegerField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticShortField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticByteField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticDoubleField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticFloatField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticHalfFloatField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), null);
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticScaledFloatField elasticField, boolean subField) throws IOException {
        NumericFieldValue vo = new NumericFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.docValues(), elasticField.ignoreMalformed(),
            elasticField.index(), elasticField.nullValue(), elasticField.store(), elasticField.scalingFactor());
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticDateField elasticField, boolean subField) throws IOException {
        DateFieldValue vo = new DateFieldValue(elasticField.type, elasticField.suffixName(), elasticField.boost(),
            elasticField.docValues(), elasticField.format(), elasticField.locale(), elasticField.ignoreMalformed(), elasticField.includeInAll(),
            elasticField.index(), elasticField.nullValue(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticBooleanField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.boost(elasticField.boost());
        this.docValues(elasticField.docValues());
        this.index(elasticField.index());
        this.nullValue(elasticField.nullValue());
        this.store(elasticField.store());
        this.closeSuffixName(subField);
    }

    private void processElasticField(ElasticBinaryField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.docValues(elasticField.docValues());
        this.store(elasticField.store());
        this.closeSuffixName(subField);
    }

    private void processElasticField(RangeFieldValue vo, boolean subField) throws IOException {
        this.startSuffixName(subField, vo.getSuffixName());
        this.type(vo.getType());
        this.coerce(vo.getCoerce());
        this.boost(vo.getBoost());
        this.index(vo.getIndex());
        this.store(vo.getStore());
        this.closeSuffixName(subField);
    }

    private void processElasticField(ElasticIntegerRangeField elasticField, boolean subField) throws IOException {
        RangeFieldValue vo = new RangeFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.includeInAll(), elasticField.index(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticFloatRangeField elasticField, boolean subField) throws IOException {
        RangeFieldValue vo = new RangeFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.includeInAll(), elasticField.index(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticLongRangeField elasticField, boolean subField) throws IOException {
        RangeFieldValue vo = new RangeFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.includeInAll(), elasticField.index(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticDoubleRangeField elasticField, boolean subField) throws IOException {
        RangeFieldValue vo = new RangeFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.includeInAll(), elasticField.index(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticIpRangeField elasticField, boolean subField) throws IOException {
        RangeFieldValue vo = new RangeFieldValue(elasticField.type, elasticField.suffixName(), elasticField.coerce(),
            elasticField.boost(), elasticField.includeInAll(), elasticField.index(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    private void processElasticField(DateFieldValue vo, boolean subField) throws IOException {
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

    private void processElasticField(ElasticDateRangeField elasticField, boolean subField) throws IOException {
        DateFieldValue vo = new DateFieldValue(elasticField.type, elasticField.suffixName(), elasticField.boost(),
            elasticField.docValues(), elasticField.format(), elasticField.locale(), elasticField.ignoreMalformed(), elasticField.includeInAll(),
            elasticField.index(), elasticField.nullValue(), elasticField.store());
        this.processElasticField(vo, subField);
    }

    private void processElasticField(ElasticGeoPointField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.ignoreMalformed(elasticField.ignoreMalformed());
        this.closeSuffixName(subField);
    }

    private void processElasticField(ElasticGeoShapeField elasticField, boolean subField) throws IOException {
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

    private void processElasticField(ElasticIpField elasticField, boolean subField) throws IOException {
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

    private void processElasticField(ElasticCompletionField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.analyzer(elasticField.analyzer());
        this.searchAnalyzer(elasticField.searchAnalyzer());
        this.preserveSeparators(elasticField.preserveSeparators());
        this.preservePositionIncrements(elasticField.preservePositionIncrements());
        this.maxInputLength(elasticField.maxInputLength());
        this.closeSuffixName(subField);
    }

    private void processElasticField(ElasticTokenCountField elasticField, boolean subField) throws IOException {
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

    private void processElasticField(ElasticPercolatorField elasticField, boolean subField) throws IOException {
        this.startSuffixName(subField, elasticField.suffixName());
        this.type(elasticField.type);
        this.closeSuffixName(subField);
    }

    private void addCustomJsonField(Field field) throws IOException {
        ElasticCustomJsonField elasticField = field.getAnnotation(ElasticCustomJsonField.class);
        try {
            InputStream inputStream = this.getCustomJson(elasticField);
            this.getMapping().rawField(field.getName(), inputStream, XContentType.JSON);
        } catch (Exception e) {
            throw new InvalidCustomJsonException("Path into ElasticCustomJsonField is incorrect!", e);
        }
    }

    private InputStream getCustomJson(ElasticCustomJsonField elasticField) {
        Class classLoader = elasticField.classLoader();
        return classLoader.getResourceAsStream(elasticField.path());
    }

    private Field[] getInnerFields(Field field) {
        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType parameterizedType = ((ParameterizedType) field.getGenericType());
            Type type = parameterizedType.getActualTypeArguments()[0];
            try {
                return Class.forName(((Class) type).getCanonicalName()).getDeclaredFields();
            } catch (ClassNotFoundException e) {
                throw new InvalidDocumentClassException(e.getCause());
            }
        } else {
            return field.getType().getDeclaredFields();
        }
    }

    private void recursiveFields(Field[] fields, int level) throws IOException {
        if (level > MAX_RECURSIVE_LEVEL) {
            throw new MaxRecursiveLevelClassException("Max json level has reached " + MAX_RECURSIVE_LEVEL);
        }
        ++level;
        if (fields != null && fields.length > 0) {
            this.getMapping().startObject("properties");
            for (Field field : fields) {
                if (containElasticFieldAnnotation(field)) {
                    //Custom Json from path.
                    if (field.isAnnotationPresent(ElasticCustomJsonField.class)) {
                        this.addCustomJsonField(field);
                    } else {
                        this.getMapping().startObject(field.getName());
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
                                    this.getMapping().startObject("fields");
                                    for (Annotation otherAnnotation : annotationList) {
                                        this.processElasticAnnotationField(otherAnnotation, true);
                                    }
                                    //fields
                                    this.getMapping().endObject();
                                }
                            }
                        }
                        //field
                        this.getMapping().endObject();
                    }
                }
            }
            //properties
            this.getMapping().endObject();
        }
    }

    private XContentBuilder innerBuild(boolean pretty) throws IOException {
        this.setMapping(XContentFactory.jsonBuilder());
        if (pretty) {
            this.getMapping().prettyPrint();
        }
        //BEGIN
        this.getMapping().startObject();
        this.getMapping().startObject("mappings");

        for (Class clazz : this.getDocsClass()) {
            ElasticDocument elasticDocument = (ElasticDocument) clazz.getAnnotation(ElasticDocument.class);
            this.getMapping().startObject(elasticDocument.value());

            //_parent
            if (isNotEmpty(elasticDocument.parent())) {
                this.getMapping().startObject("_parent");
                this.getMapping().field("type", elasticDocument.parent());
                this.eagerGlobalOrdinals(elasticDocument.eagerGlobalOrdinalsParent());
                this.getMapping().endObject();
            }

            //_all
            if (isValueEnabled(elasticDocument.enabledAll()) || isValueEnabled(elasticDocument.storeAll())) {
                this.getMapping().startObject("_all");
                if (isValueEnabled(elasticDocument.enabledAll())) {
                    this.getMapping().field("enabled", elasticDocument.enabledAll().value());
                }
                if (isValueEnabled(elasticDocument.enabledAll())) {
                    this.getMapping().field("store", elasticDocument.storeAll().value());
                }
                this.getMapping().endObject();
            }

            //_routing
            if (isNotEmpty(elasticDocument.parent())) {
                this.getMapping().startObject("_routing");
                this.getMapping().field("required", elasticDocument.requiredRouting().value());
                this.getMapping().endObject();
            }

            this.dynamic(elasticDocument.dynamic());
            this.includeInAll(elasticDocument.includeInAll());

            Field[] fields = clazz.getDeclaredFields();
            this.recursiveFields(fields, 0);

            //ElasticDocument
            this.getMapping().endObject();
        }
        //mappings
        this.getMapping().endObject();
        //END
        this.getMapping().endObject();

        return this.getMapping();
    }

    @Override
    public ObjectMapping build() {
        return this.build(false);
    }

    @Override
    public ObjectMapping build(boolean pretty) {
        XContentBuilder xContentBuilder;
        try {
            xContentBuilder = this.innerBuild(pretty);
        } catch (IOException e) {
            throw new MappingBuilderException(e);
        }
        return new ObjectMapping(xContentBuilder);
    }

}
