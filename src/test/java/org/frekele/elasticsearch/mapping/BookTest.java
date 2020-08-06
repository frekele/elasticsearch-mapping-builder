package org.frekele.elasticsearch.mapping;

import org.frekele.elasticsearch.mapping.entities.model.AuthorEntity;
import org.frekele.elasticsearch.mapping.entities.model.BookEntity;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class BookTest {

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void buildBookTest() throws Exception {
        String expected = "{\"mappings\":{\"properties\":{\"isbn\":{\"type\":\"keyword\"},\"name\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\"}}},\"description\":{\"type\":\"text\"},\"releaseDate\":{\"type\":\"date\"},\"active\":{\"type\":\"boolean\"},\"imageBlob\":{\"type\":\"binary\"},\"author\":{\"enabled\":false,\"properties\":{\"id\":{\"type\":\"long\"},\"name\":{\"type\":\"text\"},\"artisticName\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\"}}},\"address\":{\"type\":\"nested\",\"properties\":{\"postalCode\":{\"type\":\"keyword\"},\"street\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\"},\"completion\":{\"type\":\"completion\"}}},\"number\":{\"type\":\"long\"}}}}}}}}";
        MappingBuilder mappingBuilder = new MappingBuilderImpl();
        ObjectMapping result = mappingBuilder.build(BookEntity.class);
        assertEquals(result.getContentAsString().replaceAll("[\\r\\n]", ""), expected);
        //System.out.println(result.sourceAsString());
        System.out.println(mappingBuilder.build(true, BookEntity.class).getContentAsString());
    }
}
