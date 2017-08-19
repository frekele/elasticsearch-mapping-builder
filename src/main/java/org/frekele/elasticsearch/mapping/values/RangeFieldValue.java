package org.frekele.elasticsearch.mapping.values;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;
import org.frekele.elasticsearch.mapping.enums.FieldType;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class RangeFieldValue {

    private FieldType type;

    private String suffixName;

    private Bool coerce;

    private float boost;

    private Bool index;

    private Bool store;

    public RangeFieldValue() {
    }

    public RangeFieldValue(FieldType type, String suffixName, Bool coerce, float boost, Bool index, Bool store) {
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

    public Bool getIndex() {
        return index;
    }

    public void setIndex(Bool index) {
        this.index = index;
    }

    public Bool getStore() {
        return store;
    }

    public void setStore(Bool store) {
        this.store = store;
    }
}
