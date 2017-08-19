package org.frekele.elasticsearch.mapping.values;

import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;
import org.frekele.elasticsearch.mapping.annotations.values.FloatValue;
import org.frekele.elasticsearch.mapping.enums.FieldType;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class DateFieldValue {

    private FieldType type;

    private String suffixName;

    private FloatValue boost;

    private BoolValue docValues;

    private String format;

    private String locale;

    private BoolValue ignoreMalformed;

    private BoolValue index;

    private String nullValue;

    private BoolValue store;

    public DateFieldValue() {
    }

    public DateFieldValue(FieldType type, String suffixName, FloatValue boost, BoolValue docValues, String format, String locale,
                          BoolValue ignoreMalformed, BoolValue index, String nullValue, BoolValue store) {
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

    public FloatValue getBoost() {
        return boost;
    }

    public void setBoost(FloatValue boost) {
        this.boost = boost;
    }

    public BoolValue getDocValues() {
        return docValues;
    }

    public void setDocValues(BoolValue docValues) {
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

    public BoolValue getIgnoreMalformed() {
        return ignoreMalformed;
    }

    public void setIgnoreMalformed(BoolValue ignoreMalformed) {
        this.ignoreMalformed = ignoreMalformed;
    }

    public BoolValue getIndex() {
        return index;
    }

    public void setIndex(BoolValue index) {
        this.index = index;
    }

    public String getNullValue() {
        return nullValue;
    }

    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }

    public BoolValue getStore() {
        return store;
    }

    public void setStore(BoolValue store) {
        this.store = store;
    }
}
