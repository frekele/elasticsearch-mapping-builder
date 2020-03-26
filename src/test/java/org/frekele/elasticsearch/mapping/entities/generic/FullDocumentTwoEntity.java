package org.frekele.elasticsearch.mapping.entities.generic;

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

import java.util.Date;

@ElasticDocument("full_two")
public class FullDocumentTwoEntity {

    @ElasticBinaryField(
        suffixName = "binary",
        docValues = @BoolValue(true),
        store = @BoolValue(true))
    private String binaryValue;

    @ElasticBooleanField(
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true))
    private Boolean booleanValue;

    @ElasticByteField(
        suffixName = "byte",
        coerce = @BoolValue(true),
        boost = @FloatValue(0.2f),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
    private Byte byteValue;

    @ElasticCompletionField(
        suffixName = "completion",
        analyzer = "myAnalyzer",
        searchAnalyzer = "mySearchAnalyzer",
        preserveSeparators = @BoolValue(true),
        preservePositionIncrements = @BoolValue(true),
        maxInputLength = @IntValue(50)
    )
    private String completionValue;

    @ElasticDateField(
        suffixName = "date",
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        format = "basic_date_time",
        locale = "en-US",
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
    private Date dateValue;

    @ElasticDateRangeField(
        suffixName = "dateRange",
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        format = "basic_date_time",
        locale = "en-US",
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
    private Date dateRangeValue;

    @ElasticDoubleField(
        suffixName = "double",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
    private Double doubleValue;

    @ElasticDoubleRangeField(
        suffixName = "doubleRange",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        index = @BoolValue(true),
        store = @BoolValue(true)
    )
    private Double doubleRangeValue;

    @ElasticFloatField(
        suffixName = "float",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
    private Float floatValue;

    @ElasticFloatRangeField(
        suffixName = "floatRange",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        index = @BoolValue(true),
        store = @BoolValue(true)
    )
    private Float floatRangeValue;

    @ElasticGeoPointField(
        suffixName = "geoPoint",
        ignoreMalformed = @BoolValue(true)
    )
    private String geoPointValue;

    @ElasticGeoShapeField(
        suffixName = "geoShape",
        tree = "geohash",
        precision = "kilometers",
        treeLevels = "50m",
        strategy = "recursive",
        distanceErrorPct = @FloatValue(0.025f),
        orientation = "ccw",
        pointsOnly = @BoolValue(false)
    )
    private String geoShapeValue;

    @ElasticHalfFloatField(
        suffixName = "halfFloat",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
    private Float halfFloatValue;

    @ElasticIntegerField(
        suffixName = "integer",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
    private Integer integerValue;

    @ElasticIntegerRangeField(
        suffixName = "integerRange",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        index = @BoolValue(true),
        store = @BoolValue(true)
    )
    private Integer integerRangeValue;

    @ElasticIpField(
        suffixName = "ip",
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
    private String ipValue;

    @ElasticIpRangeField(
        suffixName = "ipRange",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        index = @BoolValue(true),
        store = @BoolValue(true)
    )
    private String ipRangeValue;

    @ElasticKeywordField(
        suffixName = "keyword",
        analyzer = "myAnalyzer",
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        eagerGlobalOrdinals = @BoolValue(true),
        ignoreAbove = @IntValue(350),
        index = @BoolValue(true),
        indexOptions = "docs",
        norms = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true),
        similarity = "BM25",
        normalizer = "my_normalizer"
    )
    private String keywordValue;

    @ElasticLongField(
        suffixName = "long",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
    private Long longValue;

    @ElasticLongRangeField(
        suffixName = "longRange",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        index = @BoolValue(true),
        store = @BoolValue(true)
    )
    private Long longRangeValue;

    @ElasticPercolatorField(
        suffixName = "percolator"
    )
    private String percolatorValue;

    @ElasticScaledFloatField(
        suffixName = "scaledFloat",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true),
        scalingFactor = @IntValue(100)
    )
    private Float scaledFloatValue;

    @ElasticShortField(
        suffixName = "short",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
    private Short shortValue;

    @ElasticTextField(
        suffixName = "text",
        analyzer = "myAnalyzer",
        boost = @FloatValue(1),
        eagerGlobalOrdinals = @BoolValue(true),
        fielddata = @BoolValue(true),
        fielddataFrequencyFilter = @FielddataFrequencyFilterValue(
            min = @FloatValue(0.001f),
            max = @FloatValue(0.1f),
            minSegmentSize = @IntValue(500)
        ),
        index = @BoolValue(true),
        indexOptions = "",
        norms = @BoolValue(true),
        positionIncrementGap = @IntValue(100),
        store = @BoolValue(true),
        searchAnalyzer = "mySearchAnalyzer",
        searchQuoteAnalyzer = "my_analyzer",
        similarity = "BM25",
        termVector = "no",
        copyTo = {"anotherField"}
    )
    private String textValue;

    @ElasticTokenCountField(
        suffixName = "tokenCount",
        analyzer = "myAnalyzer",
        enablePositionIncrements = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
    private String tokenCountValue;

    @ElasticNestedField(
        dynamic = @BoolValue(true),
        includeInAll = @BoolValue(true)
    )
    private InnerDocumentEntity nestedValue;

    @ElasticObjectField(
        dynamic = @BoolValue(true),
        enabledJson = @BoolValue(true),
        includeInAll = @BoolValue(true)
    )
    private InnerDocumentEntity objectValue;

    @ElasticCustomJsonField(
        path = "/custom/mapping/test-custom-field.json",
        classLoader = ClassLoader.class)
    private String customValue;

    @ElasticTextField(
        suffixName = "text",
        analyzer = "myAnalyzer",
        boost = @FloatValue(1),
        eagerGlobalOrdinals = @BoolValue(true),
        fielddata = @BoolValue(true),
        fielddataFrequencyFilter = @FielddataFrequencyFilterValue(
            min = @FloatValue(0.001f),
            max = @FloatValue(0.1f),
            minSegmentSize = @IntValue(500)
        ),
        index = @BoolValue(true),
        indexOptions = "",
        norms = @BoolValue(true),
        positionIncrementGap = @IntValue(100),
        store = @BoolValue(true),
        searchAnalyzer = "mySearchAnalyzer",
        searchQuoteAnalyzer = "my_analyzer",
        similarity = "BM25",
        termVector = "no",
        copyTo = {"anotherField"}
    )
    @ElasticKeywordField(
        suffixName = "keyword",
        analyzer = "myAnalyzer",
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        eagerGlobalOrdinals = @BoolValue(true),
        ignoreAbove = @IntValue(350),
        index = @BoolValue(true),
        indexOptions = "docs",
        norms = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true),
        similarity = "BM25",
        normalizer = "my_normalizer"
    )
    @ElasticCompletionField(
        suffixName = "completion",
        analyzer = "myAnalyzer",
        searchAnalyzer = "mySearchAnalyzer",
        preserveSeparators = @BoolValue(true),
        preservePositionIncrements = @BoolValue(true),
        maxInputLength = @IntValue(50)
    )
    private String multiFieldValue;

    public FullDocumentTwoEntity() {
    }
}
