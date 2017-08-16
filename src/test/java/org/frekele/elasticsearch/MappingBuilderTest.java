package org.frekele.elasticsearch;

import org.elasticsearch.search.SearchHit;
import org.frekele.elasticsearch.entities.Book;
import org.frekele.elasticsearch.exceptions.InvalidDocumentClassException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MappingBuilderTest {

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void isElasticDocumentTest() throws Exception {
        boolean result = MappingBuilder.isElasticDocument(Book.class);
        assertEquals(result, true);

        result = MappingBuilder.isElasticDocument(SearchHit.class);
        assertEquals(result, false);
    }

    @Test
    public void validateElasticDocumentTestWithoutError() throws Exception {
        MappingBuilder mappingBuilder = new MappingBuilder(Book.class);
        mappingBuilder.validateElasticDocument();
    }

    @Test(expectedExceptions = InvalidDocumentClassException.class)
    public void validateElasticDocumentTestWithError() throws Exception {
        MappingBuilder mappingBuilder = new MappingBuilder(SearchHit.class);
        mappingBuilder.validateElasticDocument();
    }

//    @Test
//    public void getAnnotationTest() throws Exception {
//        //Book book = new Book();
//
//        System.out.println("------Class Processing Begin---------");
//
//        System.out.println("Class: " + Book.class.getName());
//        if (Book.class.isAnnotationPresent(ElasticDocument.class)) {
//            ElasticDocument elasticDocument = Book.class.getAnnotation(ElasticDocument.class);
//            System.out.println("ElasticDocument.value: " + elasticDocument.value());
//
//            System.out.println("------Field Processing---------");
//            Field[] fields = Book.class.getDeclaredFields();
//            for (Field field : fields) {
//                if (field.isAnnotationPresent(ElasticField.class)) {
//                    ElasticField elasticField = field.getAnnotation(ElasticField.class);
//                    System.out.println("Field: " + field.getName());
//                    System.out.println("elasticField.type:" + elasticField.type());
//                }
//            }
//        }
//        System.out.println("------Class Processing End---------");
//
//        assertEquals(0, 0);
//    }

}
