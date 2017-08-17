package org.frekele.elasticsearch.mapping.values;

import org.frekele.elasticsearch.mapping.enums.FieldType;

public class RangeFieldValue {

    private FieldType type;

    private String suffixName;

    private boolean coerce;

    private float boost;

    private boolean index;

    private boolean store;

    public RangeFieldValue() {
    }

    public RangeFieldValue(FieldType type, String suffixName, boolean coerce, float boost, boolean index, boolean store) {
        this.type = type;
        this.suffixName = suffixName;
        this.coerce = coerce;
        this.boost = boost;
        this.index = index;
        this.store = store;
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

    public boolean getIndex() {
        return index;
    }

    public void setIndex(boolean index) {
        this.index = index;
    }

    public boolean getStore() {
        return store;
    }

    public void setStore(boolean store) {
        this.store = store;
    }
}
