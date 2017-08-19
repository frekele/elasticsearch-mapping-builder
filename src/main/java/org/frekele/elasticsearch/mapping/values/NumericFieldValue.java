package org.frekele.elasticsearch.mapping.values;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;
import org.frekele.elasticsearch.mapping.enums.FieldType;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class NumericFieldValue {

    private FieldType type;

    private String suffixName;

    private Bool coerce;

    private float boost;

    private Bool docValues;

    private Bool ignoreMalformed;

    private Bool index;

    private String nullValue;

    private Bool store;

    private int scalingFactor;

    public NumericFieldValue() {
    }

    public NumericFieldValue(FieldType type, String suffixName, Bool coerce, float boost, Bool docValues, Bool ignoreMalformed,
                             Bool index, String nullValue, Bool store, int scalingFactor) {
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

    public void setType(FieldType type) {
        this.type = type;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public Bool getCoerce() {
        return coerce;
    }

    public void setCoerce(Bool coerce) {
        this.coerce = coerce;
    }

    public float getBoost() {
        return boost;
    }

    public void setBoost(float boost) {
        this.boost = boost;
    }

    public Bool getDocValues() {
        return docValues;
    }

    public void setDocValues(Bool docValues) {
        this.docValues = docValues;
    }

    public Bool getIgnoreMalformed() {
        return ignoreMalformed;
    }

    public void setIgnoreMalformed(Bool ignoreMalformed) {
        this.ignoreMalformed = ignoreMalformed;
    }

    public Bool getIndex() {
        return index;
    }

    public void setIndex(Bool index) {
        this.index = index;
    }

    public String getNullValue() {
        return nullValue;
    }

    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }

    public Bool getStore() {
        return store;
    }

    public void setStore(Bool store) {
        this.store = store;
    }

    public int getScalingFactor() {
        return scalingFactor;
    }

    public void setScalingFactor(int scalingFactor) {
        this.scalingFactor = scalingFactor;
    }
}
