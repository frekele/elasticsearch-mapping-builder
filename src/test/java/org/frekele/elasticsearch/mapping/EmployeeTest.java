package org.frekele.elasticsearch.mapping;

import org.frekele.elasticsearch.mapping.entities.model.EmployeeEntity;
import org.frekele.elasticsearch.mapping.entities.model.PersonEntity;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class EmployeeTest {

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void buildEmployeeTest() throws Exception {
        String expected = "{\"mappings\":{\"_parent\":{\"type\":\"person\"},\"properties\":{\"id\":{\"type\":\"long\"},\"documentNumber\":{\"type\":\"keyword\"},\"registerNumber\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\"}}}}}}";
        MappingBuilderImpl mappingBuilder = new MappingBuilderImpl();
        ObjectMapping result = mappingBuilder.build(EmployeeEntity.class);
        assertEquals(result.getContentAsString().replaceAll("[\\r\\n]", ""), expected);
        //System.out.println(result.sourceAsString());
        System.out.println(mappingBuilder.build(true, EmployeeEntity.class).getContentAsString());
    }
}
