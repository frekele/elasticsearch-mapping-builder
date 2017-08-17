package org.frekele.elasticsearch.mapping.enums;

public enum FieldType {

    //###################################################################
    //# [Core datatypes]
    //## String datatypes - (https://www.elastic.co/guide/en/elasticsearch/reference/current/text.html)
    //##                  - (https://www.elastic.co/guide/en/elasticsearch/reference/current/keyword.html)
    //### A field to index full-text values, such as the body of an email or the description of a product.
    TEXT("text"),
    //### (https://www.elastic.co/guide/en/elasticsearch/reference/current/keyword.html)
    //### A field to index structured content such as email addresses, hostnames, status codes, zip codes or tags.
    KEYWORD("keyword"),

    //## Numeric datatypes - (https://www.elastic.co/guide/en/elasticsearch/reference/current/number.html)
    //### A signed 64-bit integer with a minimum value of -263 and a maximum value of 263-1.
    LONG("long"),
    //### A signed 32-bit integer with a minimum value of -231 and a maximum value of 231-1.
    INTEGER("integer"),
    //### A signed 16-bit integer with a minimum value of -32,768 and a maximum value of 32,767.
    SHORT("short"),
    //### A signed 8-bit integer with a minimum value of -128 and a maximum value of 127.
    BYTE("byte"),
    //### A double-precision 64-bit IEEE 754 floating point.
    DOUBLE("double"),
    //### A single-precision 32-bit IEEE 754 floating point.
    FLOAT("float"),
    //### A half-precision 16-bit IEEE 754 floating point.
    HALF_FLOAT("half_float"),
    //### A floating point that is backed by a long and a fixed scaling factor.
    SCALED_FLOAT("scaled_float"),

    //## Date datatype - (https://www.elastic.co/guide/en/elasticsearch/reference/current/date.html)
    //### JSON doesn’t have a date datatype, so dates in Elasticsearch can either be:
    //### - strings containing formatted dates, e.g. "2015-01-01" or "2015/01/01 12:10:30".
    //### - a long number representing milliseconds-since-the-epoch.
    //### - an integer representing seconds-since-the-epoch.
    DATE("date"),

    //## Boolean datatype - (https://www.elastic.co/guide/en/elasticsearch/reference/current/boolean.html)
    //### Boolean fields accept JSON true and false values, but can also accept strings and numbers which are interpreted as either true or false:
    //### - False values: false, "false", "off", "no", "0", "" (empty string), 0, 0.0
    BOOLEAN("boolean"),

    //## Binary datatype - (https://www.elastic.co/guide/en/elasticsearch/reference/current/binary.html)
    //### The binary type accepts a binary value as a Base64 encoded string. The field is not stored by default and is not searchable.
    BINARY("binary"),

    //## Range datatypes - (https://www.elastic.co/guide/en/elasticsearch/reference/current/range.html)
    //### A range of signed 32-bit integers with a minimum value of -231 and maximum of 231-1.
    INTEGER_RANGE("integer_range"),
    //### A range of single-precision 32-bit IEEE 754 floating point values.
    FLOAT_RANGE("float_range"),
    //### A range of signed 64-bit integers with a minimum value of -263 and maximum of 263-1
    LONG_RANGE("long_range"),
    //### A range of double-precision 64-bit IEEE 754 floating point values.
    DOUBLE_RANGE("double_range"),
    //### A range of date values represented as unsigned 64-bit integer milliseconds elapsed since system epoch.
    DATE_RANGE("date_range"),
    //### A range of ip values supporting either IPv4 or IPv6 (or mixed) addresses.
    IP_RANGE("ip_range"),
    //####################################################################

    //###################################################################
    //# [Complex datatypes]
    //## Array datatype - (https://www.elastic.co/guide/en/elasticsearch/reference/current/array.html)
    //### Any field can contain zero or more values by default, however, all values in the array must be of the same datatype. For instance:
    //### - an array of strings: [ "one", "two" ]
    //### - an array of integers: [ 1, 2 ]
    //### - an array of arrays: [ 1, [ 2, 3 ]] which is the equivalent of [ 1, 2, 3 ]
    //### - an array of objects: [ { "name": "Mary", "age": 12 }, { "name": "John", "age": 10 }]
    ARRAY(""),

    //## Object datatype - (https://www.elastic.co/guide/en/elasticsearch/reference/current/object.html)
    //### JSON documents are hierarchical in nature: the document may contain inner objects which, in turn, may contain inner objects themselves.
    OBJECT(""),

    //## Nested datatype - (https://www.elastic.co/guide/en/elasticsearch/reference/current/nested.html)
    //### The nested type is a specialised version of the object datatype that allows arrays of objects to be indexed and queried independently of each other.
    NESTED("nested"),
    //###################################################################

    //###################################################################
    //# [Geo datatypes]
    //## Geo-point datatype - (https://www.elastic.co/guide/en/elasticsearch/reference/current/geo-point.html)
    //### Fields of type geo_point accept latitude-longitude pairs, which can be used.
    //### - to find geo-points within a bounding box, within a certain distance of a central point, or within a polygon.
    //### - to aggregate documents by geographically or by distance from a central point.
    //### - to integrate distance into a document’s relevance score.
    //### - to sort documents by distance.
    GEO_POINT("geo_point"),

    //## Geo-Shape datatype - (https://www.elastic.co/guide/en/elasticsearch/reference/current/geo-shape.html)
    //### The geo_shape datatype facilitates the indexing of and searching with arbitrary geo shapes such as rectangles and polygons.
    //### It should be used when either the data being indexed or the queries being executed contain shapes other than just points.
    GEO_SHAPE("geo_shape"),
    //###################################################################

    //###################################################################
    //# [Specialised datatypes]
    //## IP datatype - (https://www.elastic.co/guide/en/elasticsearch/reference/current/ip.html)
    //### An ip field can index/store either IPv4 or IPv6 addresses.
    IP("ip"),

    //## Completion datatype - (https://www.elastic.co/guide/en/elasticsearch/reference/current/search-suggesters-completion.html)
    //### The completion suggester provides auto-complete/search-as-you-type functionality.
    //### This is a navigational feature to guide users to relevant results as they are typing, improving search precision. It is not meant for spell correction or did-you-mean functionality like the term or phrase suggesters.
    COMPLETION("completion"),

    //## Token count datatype - (https://www.elastic.co/guide/en/elasticsearch/reference/current/token-count.html)
    //### A field of type token_count is really an integer field which accepts string values, analyzes them, then indexes the number of tokens in the string.
    TOKEN_COUNT("token_count"),

    //## mapper-size - (https://www.elastic.co/guide/en/elasticsearch/plugins/current/mapper-size.html)
    //### The mapper-size plugin provides the _size meta field which, when enabled, indexes the size in bytes of the original _source field.
    //MAPPER_SIZE(""),

    //## Attachment datatype - (https://www.elastic.co/guide/en/elasticsearch/plugins/current/mapper-attachments.html)
    //MAPPER_ATTACHMENT(""),

    //## Percolator type - (https://www.elastic.co/guide/en/elasticsearch/reference/current/percolator.html)
    //### The percolator field type parses a json structure into a native query and stores that query, so that the percolate query can use it to match provided documents.
    PERCOLATO("percolator");
    //###################################################################

    private String name;

    FieldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

