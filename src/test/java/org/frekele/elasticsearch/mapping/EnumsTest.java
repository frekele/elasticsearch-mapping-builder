package org.frekele.elasticsearch.mapping;

import org.frekele.elasticsearch.mapping.enums.DateFormat;
import org.frekele.elasticsearch.mapping.enums.FieldType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class EnumsTest {

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void FieldTypeTest() throws Exception {
        FieldType[] fieldsType = FieldType.values();
        for (FieldType type : fieldsType) {
            System.out.println(type.getName());
        }
    }

    @Test
    public void DateFormatTest() throws Exception {
        DateFormat[] dateFormats = DateFormat.values();
        for (DateFormat dateFormat : dateFormats) {
            System.out.println(dateFormat);
        }
    }
}
