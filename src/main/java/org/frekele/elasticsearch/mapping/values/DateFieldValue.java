package org.frekele.elasticsearch.mapping.values;

import org.frekele.elasticsearch.mapping.enums.FieldType;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class DateFieldValue {

    private FieldType type;

    private String suffixName;

    private float boost;

    private boolean docValues;

    private String format;

    private String locale;

    private boolean ignoreMalformed;

    private boolean index;

    private String nullValue;

    private boolean store;

    public DateFieldValue() {
    }

    public DateFieldValue(FieldType type, String suffixName, float boost, boolean docValues, String format, String locale,
                          boolean ignoreMalformed, boolean index, String nullValue, boolean store) {
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

    public boolean getDocValues() {
        return docValues;
    }

    public void setDocValues(boolean docValues) {
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
}
