package org.frekele.elasticsearch.mapping;

import org.frekele.elasticsearch.mapping.entities.generic.FullDocumentEntity;
import org.frekele.elasticsearch.mapping.entities.generic.FullDocumentTwoEntity;
import org.frekele.elasticsearch.mapping.entities.generic.IncorrectCustomJsonFieldEntity;
import org.frekele.elasticsearch.mapping.entities.generic.NoDocumentEntity;
import org.frekele.elasticsearch.mapping.entities.generic.ParentDocumentEntity;
import org.frekele.elasticsearch.mapping.entities.generic.RecursiveErrorDocumentEntity;
import org.frekele.elasticsearch.mapping.exceptions.InvalidCustomJsonException;
import org.frekele.elasticsearch.mapping.exceptions.InvalidDocumentClassException;
import org.frekele.elasticsearch.mapping.exceptions.MappingBuilderException;
import org.frekele.elasticsearch.mapping.exceptions.MaxRecursiveLevelClassException;
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
        boolean result = MappingBuilderImpl.isElasticDocument(FullDocumentEntity.class);
        assertEquals(result, true);

        result = MappingBuilderImpl.isElasticDocument(NoDocumentEntity.class);
        assertEquals(result, false);
    }

    @Test
    public void validateWithoutErrorTest() throws Exception {
        MappingBuilderImpl mappingBuilder = new MappingBuilderImpl();
        mappingBuilder.build(FullDocumentEntity.class);
        mappingBuilder.validateElasticDocument();
    }

    @Test(expectedExceptions = InvalidDocumentClassException.class)
    public void validateWithErrorTest() throws Exception {
        MappingBuilderImpl mappingBuilder = new MappingBuilderImpl();
        mappingBuilder.build(NoDocumentEntity.class);
        mappingBuilder.validateElasticDocument();
    }

    @Test(expectedExceptions = MappingBuilderException.class)
    public void validateWithErrorTest2() throws Exception {
        MappingBuilderImpl mappingBuilder = new MappingBuilderImpl();
        mappingBuilder.build();
        mappingBuilder.validateElasticDocument();
    }

    @Test(expectedExceptions = InvalidCustomJsonException.class)
    public void buildWithErrorElasticCustomJsonFieldTest() throws Exception {
        MappingBuilder mappingBuilder = new MappingBuilderImpl();
        mappingBuilder.build(IncorrectCustomJsonFieldEntity.class);
    }

    @Test(expectedExceptions = MaxRecursiveLevelClassException.class)
    public void buildWithRecursiveErrorTest() throws Exception {
        MappingBuilder mappingBuilder = new MappingBuilderImpl();
        mappingBuilder.build(RecursiveErrorDocumentEntity.class);
    }

    @Test
    public void buildTest() throws Exception {
        String expected = "{\"mappings\":{\"full\":{\"properties\":{\"binaryValue\":{\"type\":\"binary\"},\"booleanValue\":{\"type\":\"boolean\"},\"byteValue\":{\"type\":\"byte\"},\"completionValue\":{\"type\":\"completion\"},\"dateValue\":{\"type\":\"date\"},\"dateRangeValue\":{\"type\":\"date_range\"},\"doubleValue\":{\"type\":\"double\"},\"doubleRangeValue\":{\"type\":\"double_range\"},\"floatValue\":{\"type\":\"float\"},\"floatRangeValue\":{\"type\":\"float_range\"},\"geoPointValue\":{\"type\":\"geo_point\"},\"geoShapeValue\":{\"type\":\"geo_shape\"},\"halfFloatValue\":{\"type\":\"half_float\"},\"integerValue\":{\"type\":\"integer\"},\"integerRangeValue\":{\"type\":\"integer_range\"},\"ipValue\":{\"type\":\"ip\"},\"ipRangeValue\":{\"type\":\"ip_range\"},\"keywordValue\":{\"type\":\"keyword\"},\"longValue\":{\"type\":\"long\"},\"longRangeValue\":{\"type\":\"long_range\"},\"percolatorValue\":{\"type\":\"percolator\"},\"scaledFloatValue\":{\"type\":\"scaled_float\"},\"shortValue\":{\"type\":\"short\"},\"textValue\":{\"type\":\"text\"},\"tokenCountValue\":{\"type\":\"token_count\"},\"nestedValue\":{\"nested\":true,\"properties\":{\"binaryValue\":{\"type\":\"binary\"},\"booleanValue\":{\"type\":\"boolean\"},\"byteValue\":{\"type\":\"byte\"},\"completionValue\":{\"type\":\"completion\"},\"dateValue\":{\"type\":\"date\"},\"dateRangeValue\":{\"type\":\"date_range\"},\"doubleValue\":{\"type\":\"double\"},\"doubleRangeValue\":{\"type\":\"double_range\"},\"floatValue\":{\"type\":\"float\"},\"floatRangeValue\":{\"type\":\"float_range\"},\"geoPointValue\":{\"type\":\"geo_point\"},\"geoShapeValue\":{\"type\":\"geo_shape\"},\"halfFloatValue\":{\"type\":\"half_float\"},\"integerValue\":{\"type\":\"integer\"},\"integerRangeValue\":{\"type\":\"integer_range\"},\"ipValue\":{\"type\":\"ip\"},\"ipRangeValue\":{\"type\":\"ip_range\"},\"keywordValue\":{\"type\":\"keyword\"},\"longValue\":{\"type\":\"long\"},\"longRangeValue\":{\"type\":\"long_range\"},\"percolatorValue\":{\"type\":\"percolator\"},\"scaledFloatValue\":{\"type\":\"scaled_float\"},\"shortValue\":{\"type\":\"short\"},\"textValue\":{\"type\":\"text\"},\"tokenCountValue\":{\"type\":\"token_count\"}}},\"objectValue\":{\"properties\":{\"binaryValue\":{\"type\":\"binary\"},\"booleanValue\":{\"type\":\"boolean\"},\"byteValue\":{\"type\":\"byte\"},\"completionValue\":{\"type\":\"completion\"},\"dateValue\":{\"type\":\"date\"},\"dateRangeValue\":{\"type\":\"date_range\"},\"doubleValue\":{\"type\":\"double\"},\"doubleRangeValue\":{\"type\":\"double_range\"},\"floatValue\":{\"type\":\"float\"},\"floatRangeValue\":{\"type\":\"float_range\"},\"geoPointValue\":{\"type\":\"geo_point\"},\"geoShapeValue\":{\"type\":\"geo_shape\"},\"halfFloatValue\":{\"type\":\"half_float\"},\"integerValue\":{\"type\":\"integer\"},\"integerRangeValue\":{\"type\":\"integer_range\"},\"ipValue\":{\"type\":\"ip\"},\"ipRangeValue\":{\"type\":\"ip_range\"},\"keywordValue\":{\"type\":\"keyword\"},\"longValue\":{\"type\":\"long\"},\"longRangeValue\":{\"type\":\"long_range\"},\"percolatorValue\":{\"type\":\"percolator\"},\"scaledFloatValue\":{\"type\":\"scaled_float\"},\"shortValue\":{\"type\":\"short\"},\"textValue\":{\"type\":\"text\"},\"tokenCountValue\":{\"type\":\"token_count\"}}},\"customValue\":{\"type\": \"text\",\"fields\":{\"customKeyword\":{\"type\": \"keyword\",\"ignore_above\":256 }}}\n" +
            ",\"multiFieldValue\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\"},\"completion\":{\"type\":\"completion\"}}}}}}}";
        MappingBuilder mappingBuilder = new MappingBuilderImpl();
        ObjectMapping result = mappingBuilder.build(FullDocumentEntity.class);
        assertEquals(result.getContentAsString(), expected);
        //System.out.println(result.sourceAsString());
        System.out.println(mappingBuilder.build(true, FullDocumentEntity.class).getContentAsString());
    }

    @Test
    public void buildTest2() throws Exception {
        String expected = "{\"mappings\":{\"full_two\":{\"properties\":{\"binaryValue\":{\"type\":\"binary\",\"doc_values\":true,\"store\":true},\"booleanValue\":{\"type\":\"boolean\",\"boost\":1.0,\"doc_values\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true},\"byteValue\":{\"type\":\"byte\",\"coerce\":true,\"boost\":0.2,\"doc_values\":true,\"ignore_malformed\":true,\"include_in_all\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true},\"completionValue\":{\"type\":\"completion\",\"analyzer\":\"myAnalyzer\",\"search_analyzer\":\"mySearchAnalyzer\",\"preserve_separators\":true,\"preserve_position_increments\":true,\"max_input_length\":50},\"dateValue\":{\"type\":\"date\",\"boost\":1.0,\"doc_values\":true,\"format\":\"basic_date_time\",\"locale\":\"en-US\",\"ignore_malformed\":true,\"include_in_all\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true},\"dateRangeValue\":{\"type\":\"date_range\",\"boost\":1.0,\"doc_values\":true,\"format\":\"basic_date_time\",\"locale\":\"en-US\",\"ignore_malformed\":true,\"include_in_all\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true},\"doubleValue\":{\"type\":\"double\",\"coerce\":true,\"boost\":1.0,\"doc_values\":true,\"ignore_malformed\":true,\"include_in_all\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true},\"doubleRangeValue\":{\"type\":\"double_range\",\"coerce\":true,\"boost\":1.0,\"include_in_all\":true,\"index\":true,\"store\":true},\"floatValue\":{\"type\":\"float\",\"coerce\":true,\"boost\":1.0,\"doc_values\":true,\"ignore_malformed\":true,\"include_in_all\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true},\"floatRangeValue\":{\"type\":\"float_range\",\"coerce\":true,\"boost\":1.0,\"include_in_all\":true,\"index\":true,\"store\":true},\"geoPointValue\":{\"type\":\"geo_point\",\"ignore_malformed\":true},\"geoShapeValue\":{\"type\":\"geo_shape\",\"tree\":\"geohash\",\"precision\":\"kilometers\",\"tree_levels\":\"50m\",\"strategy\":\"recursive\",\"distance_error_pct\":0.025,\"orientation\":\"ccw\",\"points_only\":false},\"halfFloatValue\":{\"type\":\"half_float\",\"coerce\":true,\"boost\":1.0,\"doc_values\":true,\"ignore_malformed\":true,\"include_in_all\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true},\"integerValue\":{\"type\":\"integer\",\"coerce\":true,\"boost\":1.0,\"doc_values\":true,\"ignore_malformed\":true,\"include_in_all\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true},\"integerRangeValue\":{\"type\":\"integer_range\",\"coerce\":true,\"boost\":1.0,\"include_in_all\":true,\"index\":true,\"store\":true},\"ipValue\":{\"type\":\"ip\",\"boost\":1.0,\"doc_values\":true,\"include_in_all\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true},\"ipRangeValue\":{\"type\":\"ip_range\",\"coerce\":true,\"boost\":1.0,\"include_in_all\":true,\"index\":true,\"store\":true},\"keywordValue\":{\"type\":\"keyword\",\"analyzer\":\"myAnalyzer\",\"boost\":1.0,\"doc_values\":true,\"eager_global_ordinals\":true,\"ignore_above\":350,\"include_in_all\":true,\"index\":true,\"index_options\":\"docs\",\"norms\":true,\"null_value\":\"NULL\",\"store\":true,\"similarity\":\"BM25\",\"normalizer\":\"my_normalizer\"},\"longValue\":{\"type\":\"long\",\"coerce\":true,\"boost\":1.0,\"doc_values\":true,\"ignore_malformed\":true,\"include_in_all\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true},\"longRangeValue\":{\"type\":\"long_range\",\"coerce\":true,\"boost\":1.0,\"include_in_all\":true,\"index\":true,\"store\":true},\"percolatorValue\":{\"type\":\"percolator\"},\"scaledFloatValue\":{\"type\":\"scaled_float\",\"coerce\":true,\"boost\":1.0,\"doc_values\":true,\"ignore_malformed\":true,\"include_in_all\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true,\"scaling_factor\":100},\"shortValue\":{\"type\":\"short\",\"coerce\":true,\"boost\":1.0,\"doc_values\":true,\"ignore_malformed\":true,\"include_in_all\":true,\"index\":true,\"null_value\":\"NULL\",\"store\":true},\"textValue\":{\"type\":\"text\",\"analyzer\":\"myAnalyzer\",\"boost\":1.0,\"eager_global_ordinals\":true,\"fielddata\":true,\"fielddata_frequency_filter\":{\"min\":0.001,\"max\":0.1,\"min_segment_size\":500},\"include_in_all\":true,\"index\":true,\"norms\":true,\"position_increment_gap\":100,\"store\":true,\"search_analyzer\":\"mySearchAnalyzer\",\"search_quote_analyzer\":\"my_analyzer\",\"similarity\":\"BM25\",\"term_vector\":\"no\",\"copy_to\":\"anotherField\"},\"tokenCountValue\":{\"type\":\"token_count\",\"analyzer\":\"myAnalyzer\",\"enable_position_increments\":true,\"boost\":1.0,\"doc_values\":true,\"index\":true,\"include_in_all\":true,\"null_value\":\"NULL\",\"store\":true},\"nestedValue\":{\"nested\":true,\"dynamic\":true,\"include_in_all\":true,\"properties\":{\"binaryValue\":{\"type\":\"binary\"},\"booleanValue\":{\"type\":\"boolean\"},\"byteValue\":{\"type\":\"byte\"},\"completionValue\":{\"type\":\"completion\"},\"dateValue\":{\"type\":\"date\"},\"dateRangeValue\":{\"type\":\"date_range\"},\"doubleValue\":{\"type\":\"double\"},\"doubleRangeValue\":{\"type\":\"double_range\"},\"floatValue\":{\"type\":\"float\"},\"floatRangeValue\":{\"type\":\"float_range\"},\"geoPointValue\":{\"type\":\"geo_point\"},\"geoShapeValue\":{\"type\":\"geo_shape\"},\"halfFloatValue\":{\"type\":\"half_float\"},\"integerValue\":{\"type\":\"integer\"},\"integerRangeValue\":{\"type\":\"integer_range\"},\"ipValue\":{\"type\":\"ip\"},\"ipRangeValue\":{\"type\":\"ip_range\"},\"keywordValue\":{\"type\":\"keyword\"},\"longValue\":{\"type\":\"long\"},\"longRangeValue\":{\"type\":\"long_range\"},\"percolatorValue\":{\"type\":\"percolator\"},\"scaledFloatValue\":{\"type\":\"scaled_float\"},\"shortValue\":{\"type\":\"short\"},\"textValue\":{\"type\":\"text\"},\"tokenCountValue\":{\"type\":\"token_count\"}}},\"objectValue\":{\"dynamic\":true,\"enabled\":true,\"include_in_all\":true,\"properties\":{\"binaryValue\":{\"type\":\"binary\"},\"booleanValue\":{\"type\":\"boolean\"},\"byteValue\":{\"type\":\"byte\"},\"completionValue\":{\"type\":\"completion\"},\"dateValue\":{\"type\":\"date\"},\"dateRangeValue\":{\"type\":\"date_range\"},\"doubleValue\":{\"type\":\"double\"},\"doubleRangeValue\":{\"type\":\"double_range\"},\"floatValue\":{\"type\":\"float\"},\"floatRangeValue\":{\"type\":\"float_range\"},\"geoPointValue\":{\"type\":\"geo_point\"},\"geoShapeValue\":{\"type\":\"geo_shape\"},\"halfFloatValue\":{\"type\":\"half_float\"},\"integerValue\":{\"type\":\"integer\"},\"integerRangeValue\":{\"type\":\"integer_range\"},\"ipValue\":{\"type\":\"ip\"},\"ipRangeValue\":{\"type\":\"ip_range\"},\"keywordValue\":{\"type\":\"keyword\"},\"longValue\":{\"type\":\"long\"},\"longRangeValue\":{\"type\":\"long_range\"},\"percolatorValue\":{\"type\":\"percolator\"},\"scaledFloatValue\":{\"type\":\"scaled_float\"},\"shortValue\":{\"type\":\"short\"},\"textValue\":{\"type\":\"text\"},\"tokenCountValue\":{\"type\":\"token_count\"}}},\"customValue\":{\"type\": \"text\",\"fields\":{\"customKeyword\":{\"type\": \"keyword\",\"ignore_above\":256 }}}\n" +
            ",\"multiFieldValue\":{\"type\":\"text\",\"analyzer\":\"myAnalyzer\",\"boost\":1.0,\"eager_global_ordinals\":true,\"fielddata\":true,\"fielddata_frequency_filter\":{\"min\":0.001,\"max\":0.1,\"min_segment_size\":500},\"include_in_all\":true,\"index\":true,\"norms\":true,\"position_increment_gap\":100,\"store\":true,\"search_analyzer\":\"mySearchAnalyzer\",\"search_quote_analyzer\":\"my_analyzer\",\"similarity\":\"BM25\",\"term_vector\":\"no\",\"copy_to\":\"anotherField\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"analyzer\":\"myAnalyzer\",\"boost\":1.0,\"doc_values\":true,\"eager_global_ordinals\":true,\"ignore_above\":350,\"include_in_all\":true,\"index\":true,\"index_options\":\"docs\",\"norms\":true,\"null_value\":\"NULL\",\"store\":true,\"similarity\":\"BM25\",\"normalizer\":\"my_normalizer\"},\"completion\":{\"type\":\"completion\",\"analyzer\":\"myAnalyzer\",\"search_analyzer\":\"mySearchAnalyzer\",\"preserve_separators\":true,\"preserve_position_increments\":true,\"max_input_length\":50}}}}}}}";
        MappingBuilder mappingBuilder = new MappingBuilderImpl();
        ObjectMapping result = mappingBuilder.build(FullDocumentTwoEntity.class);
        assertEquals(result.getContentAsString(), expected);
        //System.out.println(result.sourceAsString());
        System.out.println(mappingBuilder.build(true, FullDocumentTwoEntity.class).getContentAsString());
    }

    @Test
    public void buildTest3() throws Exception {
        String expected = "{\"mappings\":{\"my_doc_type\":{\"_parent\":{\"type\":\"my_parent_doc_type\",\"eager_global_ordinals\":true},\"_all\":{\"enabled\":true,\"store\":true},\"_routing\":{\"required\":true},\"dynamic\":true,\"include_in_all\":true,\"properties\":{\"id\":{\"type\":\"long\"}}}}}";
        MappingBuilder mappingBuilder = new MappingBuilderImpl();
        ObjectMapping result = mappingBuilder.build(ParentDocumentEntity.class);
        assertEquals(result.getContentAsString(), expected);
        //System.out.println(result.sourceAsString());
        System.out.println(mappingBuilder.build(true, ParentDocumentEntity.class).getContentAsString());
    }

}
