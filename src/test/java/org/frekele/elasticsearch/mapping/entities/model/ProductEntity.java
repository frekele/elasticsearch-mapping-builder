package org.frekele.elasticsearch.mapping.entities.model;

import org.frekele.elasticsearch.mapping.annotations.ElasticDoubleField;
import org.frekele.elasticsearch.mapping.annotations.ElasticFloatField;
import org.frekele.elasticsearch.mapping.annotations.ElasticIntegerField;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;

import java.util.List;

public class ProductEntity {

    @ElasticLongField
    private Long id;

    //The Array is not included in the mapping.
    private List<String> categories;

    @ElasticIntegerField
    private Integer size;

    @ElasticFloatField
    private Float height;

    @ElasticFloatField
    private Float width;

    @ElasticFloatField
    private Float depth;

    @ElasticDoubleField
    private Float price;

    public ProductEntity() {
    }
}
