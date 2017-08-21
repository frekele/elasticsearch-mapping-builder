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

    private BoolValue includeInAll;

    private BoolValue index;

    private String nullValue;

    private BoolValue store;

    public DateFieldValue(FieldType type, String suffixName, FloatValue boost, BoolValue docValues,
                          String format, String locale, BoolValue ignoreMalformed, BoolValue includeInAll,
                          BoolValue index, String nullValue, BoolValue store) {
        this.type = type;
        this.suffixName = suffixName;
        this.boost = boost;
        this.docValues = docValues;
        this.format = format;
        this.locale = locale;
        this.ignoreMalformed = ignoreMalformed;
        this.includeInAll = includeInAll;
        this.index = index;
        this.nullValue = nullValue;
        this.store = store;
    }

    public FieldType getType() {
        return type;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public FloatValue getBoost() {
        return boost;
    }

    public BoolValue getDocValues() {
        return docValues;
    }

    public String getFormat() {
        return format;
    }

    public String getLocale() {
        return locale;
    }

    public BoolValue getIgnoreMalformed() {
        return ignoreMalformed;
    }

    public BoolValue getIncludeInAll() {
        return includeInAll;
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
}
