# Elasticsearch Java Mapping Builder

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.frekele.elasticsearch/elasticsearch-mapping-builder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.frekele.elasticsearch/elasticsearch-mapping-builder) 
[![Build Status](https://travis-ci.org/frekele/elasticsearch-mapping-builder.svg?branch=master)](https://travis-ci.org/frekele/elasticsearch-mapping-builder)
[![Coverage](https://codecov.io/gh/frekele/elasticsearch-mapping-builder/branch/master/graph/badge.svg)](https://codecov.io/gh/frekele/elasticsearch-mapping-builder)


Example usage:

```
@ElasticDocument("book")
public class Book {

    @ElasticLongField
    private Long id;

    @ElasticTextField
    @ElasticKeywordField
    private String name;

    @ElasticTextField
    private String description;

    @ElasticDateField
    private OffsetDateTime releaseDate;

    @ElasticBooleanField
    private Boolean active;

    @ElasticBinaryField
    private String blob;

   .........
}
```

TODO
