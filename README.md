# Elasticsearch Java Mapping Builder

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.frekele.elasticsearch/elasticsearch-mapping-builder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.frekele.elasticsearch/elasticsearch-mapping-builder) 
[![Javadocs](http://www.javadoc.io/badge/org.frekele.elasticsearch/elasticsearch-mapping-builder.svg?color=blue)](http://www.javadoc.io/doc/org.frekele.elasticsearch/elasticsearch-mapping-builder)
[![Build Status](https://travis-ci.org/frekele/elasticsearch-mapping-builder.svg?branch=master)](https://travis-ci.org/frekele/elasticsearch-mapping-builder)
[![Coverage](https://codecov.io/gh/frekele/elasticsearch-mapping-builder/branch/master/graph/badge.svg)](https://codecov.io/gh/frekele/elasticsearch-mapping-builder)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/778eee87938f43ca92a94b7b613a0891)](https://www.codacy.com/app/frekele/elasticsearch-mapping-builder?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=frekele/elasticsearch-mapping-builder&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/778eee87938f43ca92a94b7b613a0891)](https://www.codacy.com/app/frekele/elasticsearch-mapping-builder?utm_source=github.com&utm_medium=referral&utm_content=frekele/elasticsearch-mapping-builder&utm_campaign=Badge_Coverage)


 Built-based on the documentation: [https://www.elastic.co/guide/en/elasticsearch/reference/5.x/mapping.html](https://www.elastic.co/guide/en/elasticsearch/reference/5.x/mapping.html)

#### Compatibility:

| Elasticsearch Mapping Builder | Elasticsearch          |
| ------------------------------| ---------------------- |
| Version: 1.0.7                | Version: 5.x.x         |


#### Maven dependency:
```xml
<dependency>
    <groupId>org.frekele.elasticsearch</groupId>
    <artifactId>elasticsearch-mapping-builder</artifactId>
    <version>1.0.7</version>
</dependency>
```

#### Gradle dependency:
```gradle
implementation 'org.frekele.elasticsearch:elasticsearch-mapping-builder:1.0.7'
```

#### Build

```java
@Inject
MappingBuilder mappingBuilder;
//or
MappingBuilder mappingBuilder = new MappingBuilderImpl();

ObjectMapping mapping = mappingBuilder.build(MyEntity1.class, MyEntity2.class, MyEntity3.class);

String jsonMapping = mapping.getContentAsString();
//or
XContentBuilder contentBuilder = mapping.getContent();
String jsonMapping =contentBuilder.string();
```

#### Example usage:

**Book:**
```java
@Inject
MappingBuilder mappingBuilder;

public String getMapping() {
    return mappingBuilder.build(BookEntity.class).getContentAsString();
}
```

```java
@ElasticDocument("book")
public class BookEntity {

    @ElasticKeywordField
    private String isbn;

    @ElasticTextField
    @ElasticKeywordField
    @ElasticCompletionField
    private String name;

    @ElasticTextField
    private String description;

    @ElasticDateField
    private OffsetDateTime releaseDate;

    @ElasticBooleanField
    private Boolean active;

    @ElasticBinaryField
    private String imageBlob;

    @ElasticObjectField
    private AuthorEntity author;
    
    .........
}

@ElasticDocument(value = "author")
public class AuthorEntity {

    @ElasticLongField
    private Long id;

    @ElasticTextField
    private String name;

    @ElasticTextField
    @ElasticKeywordField
    private String artisticName;
    
    .........
}
```

**Person parent and Employee:**
```java
@Inject
MappingBuilder mappingBuilder;

public String getMapping() {
    return mappingBuilder.build(PersonEntity.class, EmployeeEntity.class).getContentAsString();
}
```

```java
public class AddressEntity {

    @ElasticKeywordField
    private String postalCode;

    @ElasticTextField
    @ElasticKeywordField
    @ElasticCompletionField
    private String street;

    @ElasticLongField
    private Long number;

    .........
}

public class PersonEntity {

    @ElasticLongField
    private Long id;

    @ElasticTextField
    @ElasticKeywordField
    private String name;

    @ElasticTextField
    @ElasticKeywordField
    private String fullName;

    @ElasticTextField(copyTo = {"name", "fullName"})
    private String fistName;

    @ElasticTextField(copyTo = {"fullName"})
    private String lastName;

    @ElasticObjectField
    private List<AddressEntity> multipleAddress;

    .........
}

@ElasticDocument(value = "employee", parent = "person")
public class EmployeeEntity {

    @ElasticLongField
    private Long id;

    @ElasticKeywordField
    private String documentNumber;

    @ElasticTextField
    @ElasticKeywordField
    private String registerNumber;

    .........
}
```

#### Annotations parameters:

###### ElasticDocument
```java
@ElasticDocument(
    value = "my_doc_type",
    dynamic = @BoolValue(true),
    includeInAll = @BoolValue(true),
    parent = "my_parent_doc_type",
    //add eager_global_ordinals into _parent
    eagerGlobalOrdinalsParent = @BoolValue(true),
    enabledAll = @BoolValue(true),
    //add store into _all
    storeAll = @BoolValue(true),
    //add required into _routing
    requiredRouting = @BoolValue(true)
)
public class MyDocumentEntity {
    .........
```


###### ElasticBinaryField
```java
@ElasticBinaryField(
        suffixName = "binary",
        docValues = @BoolValue(true),
        store = @BoolValue(true))
private String binaryValue;
```

###### ElasticBooleanField
```java
@ElasticBooleanField(
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true))
private Boolean booleanValue;
```

###### ElasticByteField
```java
@ElasticByteField(
        suffixName = "byte",
        coerce = @BoolValue(true),
        boost = @FloatValue(0.2f),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Byte byteValue;
```

###### ElasticCompletionField
```java
@ElasticCompletionField(
        suffixName = "completion",
        analyzer = "myAnalyzer",
        searchAnalyzer = "mySearchAnalyzer",
        preserveSeparators = @BoolValue(true),
        preservePositionIncrements = @BoolValue(true),
        maxInputLength = @IntValue(50)
    )
private String completionValue;
```

###### ElasticDateField
```java
@ElasticDateField(
        suffixName = "date",
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        format = "basic_date_time",
        locale = "en-US",
        ignoreMalformed = @BoolValue(true),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Date dateValue;
```

###### ElasticDateRangeField
```java
@ElasticDateRangeField(
        suffixName = "dateRange",
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        format = "basic_date_time",
        locale = "en-US",
        ignoreMalformed = @BoolValue(true),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Date dateRangeValue;
```

###### ElasticDoubleField
```java
@ElasticDoubleField(
        suffixName = "double",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Double doubleValue;
```

###### ElasticDoubleRangeField
```java
@ElasticDoubleRangeField(
        suffixName = "doubleRange",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        store = @BoolValue(true)
    )
private Double doubleRangeValue;
```

###### ElasticFloatField
```java
@ElasticFloatField(
        suffixName = "float",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Float floatValue;
```

###### ElasticFloatRangeField
```java
@ElasticFloatRangeField(
        suffixName = "floatRange",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        store = @BoolValue(true)
    )
private Float floatRangeValue;
```

###### ElasticGeoPointField
```java
@ElasticGeoPointField(
        suffixName = "geoPoint",
        ignoreMalformed = @BoolValue(true)
    )
private String geoPointValue;
```

###### ElasticGeoShapeField
```java
@ElasticGeoShapeField(
        suffixName = "geoShape",
        tree = "geohash",
        precision = "kilometers",
        treeLevels = "50m",
        strategy = "recursive",
        distanceErrorPct = @FloatValue(0.025f),
        orientation = "ccw",
        pointsOnly = @BoolValue(false)
    )
private String geoShapeValue;
```

###### ElasticHalfFloatField
```java
@ElasticHalfFloatField(
        suffixName = "halfFloat",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Float halfFloatValue;
```

###### ElasticIntegerField
```java
@ElasticIntegerField(
        suffixName = "integer",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Integer integerValue;
```

###### ElasticIntegerRangeField
```java
@ElasticIntegerRangeField(
        suffixName = "integerRange",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        store = @BoolValue(true)
    )
private Integer integerRangeValue;
```

###### ElasticIpField
```java
@ElasticIpField(
        suffixName = "ip",
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private String ipValue;
```

###### ElasticIpRangeField
```java
@ElasticIpRangeField(
        suffixName = "ipRange",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        store = @BoolValue(true)
    )
private String ipRangeValue;
```

###### ElasticKeywordField
```java
@ElasticKeywordField(
        suffixName = "keyword",
        analyzer = "myAnalyzer",
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        eagerGlobalOrdinals = @BoolValue(true),
        ignoreAbove = @IntValue(350),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        indexOptions = "docs",
        norms = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true),
        similarity = "BM25",
        normalizer = "my_normalizer"
    )
private String keywordValue;
```

###### ElasticLongField
```java
@ElasticLongField(
        suffixName = "long",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Long longValue;
```

###### ElasticLongRangeField
```java
@ElasticLongRangeField(
        suffixName = "longRange",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        store = @BoolValue(true)
    )
private Long longRangeValue;
```

###### ElasticPercolatorField
```java
@ElasticPercolatorField(
        suffixName = "percolator"
    )
private String percolatorValue;
```

###### ElasticScaledFloatField
```java
@ElasticScaledFloatField(
        suffixName = "scaledFloat",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true),
        scalingFactor = @IntValue(100)
    )
private Float scaledFloatValue;
```

###### ElasticShortField
```java
@ElasticShortField(
        suffixName = "short",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Short shortValue;
```

###### ElasticTextField
```java
@ElasticTextField(
        suffixName = "text",
        analyzer = "myAnalyzer",
        boost = @FloatValue(1),
        eagerGlobalOrdinals = @BoolValue(true),
        fielddata = @BoolValue(true),
        fielddataFrequencyFilter = @FielddataFrequencyFilterValue(
            min = @FloatValue(0.001f),
            max = @FloatValue(0.1f),
            minSegmentSize = @IntValue(500)
        ),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        indexOptions = "",
        norms = @BoolValue(true),
        positionIncrementGap = @IntValue(100),
        store = @BoolValue(true),
        searchAnalyzer = "mySearchAnalyzer",
        searchQuoteAnalyzer = "my_analyzer",
        similarity = "BM25",
        termVector = "no",
        copyTo = {"anotherField"}
    )
private String textValue;
```

###### ElasticTokenCountField
```java
@ElasticTokenCountField(
        suffixName = "tokenCount",
        analyzer = "myAnalyzer",
        enablePositionIncrements = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        index = @BoolValue(true),
        includeInAll = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private String tokenCountValue;
```

###### ElasticNestedField
```java
@ElasticNestedField(
        dynamic = @BoolValue(true),
        includeInAll = @BoolValue(true)
    )
private InnerDocumentEntity nestedValue;
```

###### ElasticObjectField
```java
@ElasticObjectField(
        dynamic = @BoolValue(true),
        enabledJson = @BoolValue(true),
        includeInAll = @BoolValue(true)
    )
private InnerDocumentEntity objectValue;
```

###### ElasticCustomJsonField
```java
@ElasticCustomJsonField(
        path = "/custom/mapping/test-custom-field.json",
        classLoader = ClassLoader.class)
private String customValue;
```

###### Multiple Field (The first annotation is the main field!)
```java
@ElasticTextField(
        suffixName = "text",
        analyzer = "myAnalyzer",
        boost = @FloatValue(1),
        eagerGlobalOrdinals = @BoolValue(true),
        fielddata = @BoolValue(true),
        fielddataFrequencyFilter = @FielddataFrequencyFilterValue(
            min = @FloatValue(0.001f),
            max = @FloatValue(0.1f),
            minSegmentSize = @IntValue(500)
        ),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        indexOptions = "",
        norms = @BoolValue(true),
        positionIncrementGap = @IntValue(100),
        store = @BoolValue(true),
        searchAnalyzer = "mySearchAnalyzer",
        searchQuoteAnalyzer = "my_analyzer",
        similarity = "BM25",
        termVector = "no",
        copyTo = {"anotherField"}
    )
@ElasticKeywordField(
        suffixName = "keyword",
        analyzer = "myAnalyzer",
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        eagerGlobalOrdinals = @BoolValue(true),
        ignoreAbove = @IntValue(350),
        includeInAll = @BoolValue(true),
        index = @BoolValue(true),
        indexOptions = "docs",
        norms = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true),
        similarity = "BM25",
        normalizer = "my_normalizer"
    )
@ElasticCompletionField(
        suffixName = "completion",
        analyzer = "myAnalyzer",
        searchAnalyzer = "mySearchAnalyzer",
        preserveSeparators = @BoolValue(true),
        preservePositionIncrements = @BoolValue(true),
        maxInputLength = @IntValue(50)
    )
private String multiFieldValue;
```

## License
```
   Copyright © 2017-2019 - @frekele<Leandro Kersting de Freitas>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
