# Elasticsearch Java Mapping Builder

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.frekele.elasticsearch/elasticsearch-mapping-builder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.frekele.elasticsearch/elasticsearch-mapping-builder) 
[![Build Status](https://travis-ci.org/frekele/elasticsearch-mapping-builder.svg?branch=master)](https://travis-ci.org/frekele/elasticsearch-mapping-builder)
[![Coverage](https://codecov.io/gh/frekele/elasticsearch-mapping-builder/branch/master/graph/badge.svg)](https://codecov.io/gh/frekele/elasticsearch-mapping-builder)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/778eee87938f43ca92a94b7b613a0891)](https://www.codacy.com/app/frekele/elasticsearch-mapping-builder?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=frekele/elasticsearch-mapping-builder&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/778eee87938f43ca92a94b7b613a0891)](https://www.codacy.com/app/frekele/elasticsearch-mapping-builder?utm_source=github.com&utm_medium=referral&utm_content=frekele/elasticsearch-mapping-builder&utm_campaign=Badge_Coverage)

Maven:
```
<dependency>
    <groupId>org.frekele.elasticsearch</groupId>
    <artifactId>elasticsearch-mapping-builder</artifactId>
    <version>1.0.0.Beta1</version>
</dependency>
```
Gradle:
```
compile 'org.frekele.elasticsearch:elasticsearch-mapping-builder:1.0.0.Beta1'
```

#### Build

```
@Inject
MappingBuilder mappingBuilder;
//or
MappingBuilder mappingBuilder = new MappingBuilderImpl();

ObjectMapping mapping = mappingBuilder.build(BookEntity.class, AuthorEntity.class);

String jsonMapping = mapping.sourceAsString();
//or
XContentBuilder contentBuilder = mapping.source();
```

#### Example usage:

```
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
```

```
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

#### Annotations parameters:

###### ElasticDocument
```
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
```
@ElasticBinaryField(
        suffixName = "binary",
        docValues = @BoolValue(true),
        store = @BoolValue(true))
private String binaryValue;
```

###### ElasticBooleanField
```
@ElasticBooleanField(
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true))
private Boolean booleanValue;
```

###### ElasticByteField
```
@ElasticByteField(
        suffixName = "byte",
        coerce = @BoolValue(true),
        boost = @FloatValue(0.2f),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Byte byteValue;
```

###### ElasticCompletionField
```
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
```
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
```
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
```
@ElasticDoubleField(
        suffixName = "double",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Double doubleValue;
```

###### ElasticDoubleRangeField
```
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
```
@ElasticFloatField(
        suffixName = "float",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Float floatValue;
```

###### ElasticFloatRangeField
```
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
```
@ElasticGeoPointField(
        suffixName = "geoPoint",
        ignoreMalformed = @BoolValue(true)
    )
private String geoPointValue;
```

###### ElasticGeoShapeField
```
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
```
@ElasticHalfFloatField(
        suffixName = "halfFloat",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Float halfFloatValue;
```

###### ElasticIntegerField
```
@ElasticIntegerField(
        suffixName = "integer",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Integer integerValue;
```

###### ElasticIntegerRangeField
```
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
```
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
```
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
```
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
```
@ElasticLongField(
        suffixName = "long",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Long longValue;
```

###### ElasticLongRangeField
```
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
```
@ElasticPercolatorField(
        suffixName = "percolator"
    )
private String percolatorValue;
```

###### ElasticScaledFloatField
```
@ElasticScaledFloatField(
        suffixName = "scaledFloat",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true),
        scalingFactor = @IntValue(100)
    )
private Float scaledFloatValue;
```

###### ElasticShortField
```
@ElasticShortField(
        suffixName = "short",
        coerce = @BoolValue(true),
        boost = @FloatValue(1),
        docValues = @BoolValue(true),
        ignoreMalformed = @BoolValue(true),
        index = @BoolValue(true),
        nullValue = "NULL",
        store = @BoolValue(true)
    )
private Short shortValue;
```

###### ElasticTextField
```
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
```
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
```
@ElasticNestedField(
        dynamic = @BoolValue(true),
        includeInAll = @BoolValue(true)
    )
private InnerDocumentEntity nestedValue;
```

###### ElasticObjectField
```
@ElasticObjectField(
        dynamic = @BoolValue(true),
        enabledJson = @BoolValue(true),
        includeInAll = @BoolValue(true)
    )
private InnerDocumentEntity objectValue;
```

###### ElasticCustomJsonField
```
@ElasticCustomJsonField(
        path = "/custom/mapping/test-custom-field.json",
        classLoader = ClassLoader.class)
private String customValue;
```

###### ElasticTextField
```
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
   Copyright © 2017 {frekele Leandro Kersting de Freitas}

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
