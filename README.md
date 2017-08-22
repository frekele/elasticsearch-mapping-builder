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
    <version>1.0.0.Alpha1</version>
</dependency>
```
Gradle:
```
compile 'org.frekele.elasticsearch:elasticsearch-mapping-builder:1.0.0.Alpha1'
```


Example usage:

```
@ElasticDocument("book")
public class BookEntity {

    @ElasticKeywordField
    private String isbn;

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

#### Build

```
MappingBuilder mappingBuilder = new MappingBuilder(BookEntity.class, AuthorEntity.class);
ObjectMapping mapping = mappingBuilder.build();

String jsonMapping = mapping.sourceAsString();
//or
XContentBuilder contentBuilder = mapping.source();

```
