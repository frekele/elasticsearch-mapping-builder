package org.frekele.elasticsearch.mapping.values;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.enums.FieldType;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class RangeFieldValue {

    private FieldType type;

    private String suffixName;

    private BoolValue coerce;

    private float boost;

    private BoolValue index;

    private BoolValue store;

    public RangeFieldValue() {
    }

    public RangeFieldValue(FieldType type, String suffixName, BoolValue coerce, float boost, BoolValue index, BoolValue store) {
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

    public BoolValue getCoerce() {
        return coerce;
    }

    public void setCoerce(BoolValue coerce) {
        this.coerce = coerce;
    }

    public float getBoost() {
        return boost;
    }

    public void setBoost(float boost) {
        this.boost = boost;
    }

    public BoolValue getIndex() {
        return index;
    }

    public void setIndex(BoolValue index) {
        this.index = index;
    }

    public BoolValue getStore() {
        return store;
    }

    public void setStore(BoolValue store) {
        this.store = store;
    }
}
