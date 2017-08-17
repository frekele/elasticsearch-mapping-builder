package org.frekele.elasticsearch.mapping.values;

import org.frekele.elasticsearch.mapping.enums.FieldType;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class NumericFieldValue {

    private FieldType type;

    private String suffixName;

    private boolean coerce;

    private float boost;

    private boolean docValues;

    private boolean ignoreMalformed;

    private boolean index;

    private String nullValue;

    private boolean store;

    private int scalingFactor;

    public NumericFieldValue() {
    }

    public NumericFieldValue(FieldType type, String suffixName, boolean coerce, float boost, boolean docValues, boolean ignoreMalformed,
                             boolean index, String nullValue, boolean store, int scalingFactor) {
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

    public boolean getCoerce() {
        return coerce;
    }

    public void setCoerce(boolean coerce) {
        this.coerce = coerce;
    }

    public float getBoost() {
        return boost;
    }

    public void setBoost(float boost) {
        this.boost = boost;
    }

    public boolean getDocValues() {
        return docValues;
    }

    public void setDocValues(boolean docValues) {
        this.docValues = docValues;
    }

    public boolean getIgnoreMalformed() {
        return ignoreMalformed;
    }

    public void setIgnoreMalformed(boolean ignoreMalformed) {
        this.ignoreMalformed = ignoreMalformed;
    }

    public boolean getIndex() {
        return index;
    }

    public void setIndex(boolean index) {
        this.index = index;
    }

    public String getNullValue() {
        return nullValue;
    }

    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }

    public boolean getStore() {
        return store;
    }

    public void setStore(boolean store) {
        this.store = store;
    }

    public int getScalingFactor() {
        return scalingFactor;
    }

    public void setScalingFactor(int scalingFactor) {
        this.scalingFactor = scalingFactor;
    }
}
