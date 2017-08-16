package org.frekele.elasticsearch.mapping.enums;

public enum FieldType {

    //simple type
    TEXT,
    KEYWORD,
    DATE,
    LONG,
    DOUBLE,
    BOOLEAN,
    IP,

    //hierarchical type
    OBJECT,
    NESTED,

    //specialised type
    GEO_POINT,
    GEO_SHAPE,
    COMPLETION

}
