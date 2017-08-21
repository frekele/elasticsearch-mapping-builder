package org.frekele.elasticsearch.mapping;

import org.frekele.elasticsearch.mapping.entities.Book;
import org.frekele.elasticsearch.mapping.entities.FullDocumentEntity;
import org.frekele.elasticsearch.mapping.entities.NoDocumentEntity;
import org.frekele.elasticsearch.mapping.exceptions.InvalidDocumentClassException;
import org.frekele.elasticsearch.mapping.exceptions.MappingBuilderException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class MappingBuilderTest {

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void isElasticDocumentTest() throws Exception {
        boolean result = MappingBuilder.isElasticDocument(FullDocumentEntity.class);
        assertEquals(result, true);

        result = MappingBuilder.isElasticDocument(NoDocumentEntity.class);
        assertEquals(result, false);
    }

    @Test
    public void validateWithoutErrorTest() throws Exception {
        MappingBuilder mappingBuilder = new MappingBuilder(Book.class);
        mappingBuilder.validateElasticDocument();
    }

    @Test(expectedExceptions = InvalidDocumentClassException.class)
    public void validateWithErrorTest() throws Exception {
        MappingBuilder mappingBuilder = new MappingBuilder(NoDocumentEntity.class);
        mappingBuilder.validateElasticDocument();
    }

    @Test(expectedExceptions = MappingBuilderException.class)
    public void validateWithErrorTest2() throws Exception {
        MappingBuilder mappingBuilder = new MappingBuilder();
        mappingBuilder.validateElasticDocument();
    }

    @Test
    public void buildTest() throws Exception {
        String expected = "{\"mappings\":{\"full\":{\"properties\":{\"binaryValue\":{\"type\":\"binary\"},\"booleanValue\":{\"type\":\"boolean\"},\"byteValue\":{\"type\":\"byte\"},\"completionValue\":{\"type\":\"completion\"},\"dateValue\":{\"type\":\"date\"},\"dateRangeValue\":{\"type\":\"date_range\"},\"doubleValue\":{\"type\":\"double\"},\"doubleRangeValue\":{\"type\":\"double_range\"},\"floatValue\":{\"type\":\"float\"},\"floatRangeValue\":{\"type\":\"float_range\"},\"geoPointValue\":{\"type\":\"geo_point\"},\"geoShapeValue\":{\"type\":\"geo_shape\"},\"halfFloatValue\":{\"type\":\"half_float\"},\"integerValue\":{\"type\":\"integer\"},\"integerRangeValue\":{\"type\":\"integer_range\"},\"ipValue\":{\"type\":\"ip\"},\"ipRangeValue\":{\"type\":\"ip_range\"},\"keywordValue\":{\"type\":\"keyword\"},\"longValue\":{\"type\":\"long\"},\"longRangeValue\":{\"type\":\"long_range\"},\"percolatorValue\":{\"type\":\"percolator\"},\"scaledFloatValue\":{\"type\":\"scaled_float\"},\"shortValue\":{\"type\":\"short\"},\"textValue\":{\"type\":\"text\"},\"tokenCountValue\":{\"type\":\"token_count\"},\"nestedValue\":{\"nested\":true,\"properties\":{\"binaryValue\":{\"type\":\"binary\"},\"booleanValue\":{\"type\":\"boolean\"},\"byteValue\":{\"type\":\"byte\"},\"completionValue\":{\"type\":\"completion\"},\"dateValue\":{\"type\":\"date\"},\"dateRangeValue\":{\"type\":\"date_range\"},\"doubleValue\":{\"type\":\"double\"},\"doubleRangeValue\":{\"type\":\"double_range\"},\"floatValue\":{\"type\":\"float\"},\"floatRangeValue\":{\"type\":\"float_range\"},\"geoPointValue\":{\"type\":\"geo_point\"},\"geoShapeValue\":{\"type\":\"geo_shape\"},\"halfFloatValue\":{\"type\":\"half_float\"},\"integerValue\":{\"type\":\"integer\"},\"integerRangeValue\":{\"type\":\"integer_range\"},\"ipValue\":{\"type\":\"ip\"},\"ipRangeValue\":{\"type\":\"ip_range\"},\"keywordValue\":{\"type\":\"keyword\"},\"longValue\":{\"type\":\"long\"},\"longRangeValue\":{\"type\":\"long_range\"},\"percolatorValue\":{\"type\":\"percolator\"},\"scaledFloatValue\":{\"type\":\"scaled_float\"},\"shortValue\":{\"type\":\"short\"},\"textValue\":{\"type\":\"text\"},\"tokenCountValue\":{\"type\":\"token_count\"}}},\"objectValue\":{\"properties\":{\"binaryValue\":{\"type\":\"binary\"},\"booleanValue\":{\"type\":\"boolean\"},\"byteValue\":{\"type\":\"byte\"},\"completionValue\":{\"type\":\"completion\"},\"dateValue\":{\"type\":\"date\"},\"dateRangeValue\":{\"type\":\"date_range\"},\"doubleValue\":{\"type\":\"double\"},\"doubleRangeValue\":{\"type\":\"double_range\"},\"floatValue\":{\"type\":\"float\"},\"floatRangeValue\":{\"type\":\"float_range\"},\"geoPointValue\":{\"type\":\"geo_point\"},\"geoShapeValue\":{\"type\":\"geo_shape\"},\"halfFloatValue\":{\"type\":\"half_float\"},\"integerValue\":{\"type\":\"integer\"},\"integerRangeValue\":{\"type\":\"integer_range\"},\"ipValue\":{\"type\":\"ip\"},\"ipRangeValue\":{\"type\":\"ip_range\"},\"keywordValue\":{\"type\":\"keyword\"},\"longValue\":{\"type\":\"long\"},\"longRangeValue\":{\"type\":\"long_range\"},\"percolatorValue\":{\"type\":\"percolator\"},\"scaledFloatValue\":{\"type\":\"scaled_float\"},\"shortValue\":{\"type\":\"short\"},\"textValue\":{\"type\":\"text\"},\"tokenCountValue\":{\"type\":\"token_count\"}}}}}}}";
        MappingBuilder mappingBuilder = new MappingBuilder(FullDocumentEntity.class);
        ObjectMapping result = mappingBuilder.build();
         assertEquals(result.sourceAsString(), expected);
        System.out.println(result);
    }
}
