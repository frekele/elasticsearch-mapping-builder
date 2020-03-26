package org.frekele.elasticsearch.mapping.entities.generic;

import org.frekele.elasticsearch.mapping.annotations.ElasticBinaryField;
import org.frekele.elasticsearch.mapping.annotations.ElasticBooleanField;
import org.frekele.elasticsearch.mapping.annotations.ElasticByteField;
import org.frekele.elasticsearch.mapping.annotations.ElasticCompletionField;
import org.frekele.elasticsearch.mapping.annotations.ElasticCustomJsonField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDateField;
import org.frekele.elasticsearch.mapping.annotations.ElasticDateRangeField;
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
import org.frekele.elasticsearch.mapping.annotations.values.IntValue;

import java.util.Date;

public class FullDocumentEntity {

    @ElasticBinaryField
    private String binaryValue;

    @ElasticBooleanField
    private Boolean booleanValue;

    @ElasticByteField
    private Byte byteValue;

    @ElasticCompletionField
    private String completionValue;

    @ElasticDateField
    private Date dateValue;

    @ElasticDateRangeField
    private Date dateRangeValue;

    @ElasticDoubleField
    private Double doubleValue;

    @ElasticDoubleRangeField
    private Double doubleRangeValue;

    @ElasticFloatField
    private Float floatValue;

    @ElasticFloatRangeField
    private Float floatRangeValue;

    @ElasticGeoPointField
    private String geoPointValue;

    @ElasticGeoShapeField
    private String geoShapeValue;

    @ElasticHalfFloatField
    private Float halfFloatValue;

    @ElasticIntegerField
    private Integer integerValue;

    @ElasticIntegerRangeField
    private Integer integerRangeValue;

    @ElasticIpField
    private String ipValue;

    @ElasticIpRangeField
    private String ipRangeValue;

    @ElasticKeywordField
    private String keywordValue;

    @ElasticLongField
    private Long longValue;

    @ElasticLongRangeField
    private Long longRangeValue;

    @ElasticPercolatorField
    private String percolatorValue;

    @ElasticScaledFloatField(scalingFactor = @IntValue(100))
    private Float scaledFloatValue;

    @ElasticShortField
    private Short shortValue;

    @ElasticTextField
    private String textValue;

    @ElasticTokenCountField
    private String tokenCountValue;

    @ElasticNestedField
    private InnerDocumentEntity nestedValue;

    @ElasticObjectField
    private InnerDocumentEntity objectValue;

    @ElasticCustomJsonField(path = "/custom/mapping/test-custom-field.json")
    private String customValue;

    @ElasticTextField
    @ElasticKeywordField
    @ElasticCompletionField
    private String multiFieldValue;

    public FullDocumentEntity() {
    }
}
