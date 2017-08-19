package org.frekele.elasticsearch.mapping.values;

import org.frekele.elasticsearch.mapping.annotations.values.Bool;
import org.frekele.elasticsearch.mapping.enums.FieldType;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class DateFieldValue {

    private FieldType type;

    private String suffixName;

    private float boost;

    private Bool docValues;

    private String format;

    private String locale;

    private Bool ignoreMalformed;

    private Bool index;

    private String nullValue;

    private Bool store;

    public DateFieldValue() {
    }

    public DateFieldValue(FieldType type, String suffixName, float boost, Bool docValues, String format, String locale,
                          Bool ignoreMalformed, Bool index, String nullValue, Bool store) {
        this.type = type;
        this.suffixName = suffixName;
        this.boost = boost;
        this.docValues = docValues;
        this.format = format;
        this.locale = locale;
        this.ignoreMalformed = ignoreMalformed;
        this.index = index;
        this.nullValue = nullValue;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
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
}
