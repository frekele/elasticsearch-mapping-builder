package org.frekele.elasticsearch.mapping;

import org.frekele.elasticsearch.mapping.entities.model.ProductEntity;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class ProductTest {

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void buildProductTest() throws Exception {
        String expected = "{\"mappings\":{\"product\":{\"properties\":{\"id\":{\"type\":\"long\"},\"size\":{\"type\":\"integer\"},\"height\":{\"type\":\"float\"},\"width\":{\"type\":\"float\"},\"depth\":{\"type\":\"float\"},\"price\":{\"type\":\"double\"}}}}}";
        MappingBuilder mappingBuilder = new MappingBuilderImpl();
        ObjectMapping result = mappingBuilder.build(ProductEntity.class);
        assertEquals(result.sourceAsString(), expected);
        //System.out.println(result.sourceAsString());
        System.out.println(mappingBuilder.build(true, ProductEntity.class).sourceAsString());
    }
}
