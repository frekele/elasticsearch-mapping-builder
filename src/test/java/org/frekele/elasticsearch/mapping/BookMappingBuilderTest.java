package org.frekele.elasticsearch.mapping;

import org.frekele.elasticsearch.mapping.entities.model.Book;
import org.frekele.elasticsearch.mapping.entities.model.Person;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class BookMappingBuilderTest {

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void sourceAsStringTestBook() throws Exception {
        String expected = "{\"mappings\":{\"book\":{\"properties\":{\"id\":{\"type\":\"long\"},\"name\":{\"type\":\"text\"}}}}}";
        MappingBuilder mappingBuilder = new MappingBuilder(Book.class);
        String result = mappingBuilder.build(true).sourceAsString();
        // assertEquals(result, expected);
        System.out.println(result);
    }

    @Test
    public void sourceAsStringTestPerson() throws Exception {
        MappingBuilder mappingBuilder = new MappingBuilder(Person.class);
        String result = mappingBuilder.build(true).sourceAsString();
        //System.out.println(result);
    }
}
