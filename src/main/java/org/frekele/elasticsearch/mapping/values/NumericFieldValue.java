package org.frekele.elasticsearch.mapping.values;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.annotations.values.FloatValue;
import org.frekele.elasticsearch.mapping.annotations.values.IntValue;
import org.frekele.elasticsearch.mapping.enums.FieldType;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class NumericFieldValue {

    private FieldType type;

    private String suffixName;

    private BoolValue coerce;

    private FloatValue boost;

    private BoolValue docValues;

    private BoolValue ignoreMalformed;

    private BoolValue index;

    private String nullValue;

    private BoolValue store;

    private IntValue scalingFactor;

    public NumericFieldValue(FieldType type, String suffixName, BoolValue coerce, FloatValue boost,
                             BoolValue docValues, BoolValue ignoreMalformed, BoolValue index,
                             String nullValue, BoolValue store, IntValue scalingFactor) {
        this.type = type;
        this.suffixName = suffixName;
        this.coerce = coerce;
        this.boost = boost;
        this.docValues = docValues;
        this.ignoreMalformed = ignoreMalformed;
        this.index = index;
        this.nullValue = nullValue;
        this.store = store;
        this.scalingFactor = scalingFactor;
    }

    public FieldType getType() {
        return type;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public BoolValue getCoerce() {
        return coerce;
    }

    public FloatValue getBoost() {
        return boost;
    }

    public BoolValue getDocValues() {
        return docValues;
    }

    public BoolValue getIgnoreMalformed() {
        return ignoreMalformed;
    }

    public BoolValue getIndex() {
        return index;
    }

    public String getNullValue() {
        return nullValue;
    }

    public BoolValue getStore() {
        return store;
    }

    public IntValue getScalingFactor() {
        return scalingFactor;
    }
}
