package org.frekele.elasticsearch.mapping;

import org.frekele.elasticsearch.mapping.exceptions.InvalidCustomJsonException;
import org.frekele.elasticsearch.mapping.exceptions.InvalidDocumentClassException;
import org.frekele.elasticsearch.mapping.exceptions.MappingBuilderException;
import org.frekele.elasticsearch.mapping.exceptions.MaxRecursiveLevelClassException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class ExceptionTest {

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    //InvalidCustomJsonException
    @Test(expectedExceptions = InvalidCustomJsonException.class)
    public void InvalidCustomJsonExceptionTest1() throws Exception {
        throw new InvalidCustomJsonException("InvalidCustomJsonException");
    }

    @Test(expectedExceptions = InvalidCustomJsonException.class)
    public void InvalidCustomJsonExceptionTest2() throws Exception {
        IOException ex = new IOException("IO error");
        throw new InvalidCustomJsonException(ex);
    }

    @Test(expectedExceptions = InvalidCustomJsonException.class)
    public void InvalidCustomJsonExceptionTest3() throws Exception {
        IOException ex = new IOException("IO error");
        throw new InvalidCustomJsonException("InvalidCustomJsonException", ex);
    }

    @Test(expectedExceptions = InvalidCustomJsonException.class)
    public void InvalidCustomJsonExceptionTest4() throws Exception {
        IOException ex = new IOException("IO error");
        throw new InvalidCustomJsonException("InvalidCustomJsonException", ex, true, true);
    }

    @Test(expectedExceptions = InvalidCustomJsonException.class)
    public void InvalidCustomJsonExceptionTest5() throws Exception {
        IOException ex = new IOException("IO error");
        throw new InvalidCustomJsonException("InvalidCustomJsonException", ex, false, false);
    }

    //InvalidDocumentClassException
    @Test(expectedExceptions = InvalidDocumentClassException.class)
    public void InvalidDocumentClassExceptionTest1() throws Exception {
        throw new InvalidDocumentClassException("InvalidDocumentClassException");
    }

    @Test(expectedExceptions = InvalidDocumentClassException.class)
    public void InvalidDocumentClassExceptionTest2() throws Exception {
        IOException ex = new IOException("IO error");
        throw new InvalidDocumentClassException(ex);
    }

    @Test(expectedExceptions = InvalidDocumentClassException.class)
    public void InvalidDocumentClassExceptionTest3() throws Exception {
        IOException ex = new IOException("IO error");
        throw new InvalidDocumentClassException("InvalidDocumentClassException", ex);
    }

    @Test(expectedExceptions = InvalidDocumentClassException.class)
    public void InvalidDocumentClassExceptionTest4() throws Exception {
        IOException ex = new IOException("IO error");
        throw new InvalidDocumentClassException("InvalidDocumentClassException", ex, true, true);
    }

    @Test(expectedExceptions = InvalidDocumentClassException.class)
    public void InvalidDocumentClassExceptionTest5() throws Exception {
        IOException ex = new IOException("IO error");
        throw new InvalidDocumentClassException("InvalidDocumentClassException", ex, false, false);
    }

    //MappingBuilderException
    @Test(expectedExceptions = MappingBuilderException.class)
    public void MappingBuilderExceptionTest1() throws Exception {
        throw new MappingBuilderException("MappingBuilderException");
    }

    @Test(expectedExceptions = MappingBuilderException.class)
    public void MappingBuilderExceptionTest2() throws Exception {
        IOException ex = new IOException("IO error");
        throw new MappingBuilderException(ex);
    }

    @Test(expectedExceptions = MappingBuilderException.class)
    public void MappingBuilderExceptionTest3() throws Exception {
        IOException ex = new IOException("IO error");
        throw new MappingBuilderException("MappingBuilderException", ex);
    }

    @Test(expectedExceptions = MappingBuilderException.class)
    public void MappingBuilderExceptionTest4() throws Exception {
        IOException ex = new IOException("IO error");
        throw new MappingBuilderException("MappingBuilderException", ex, true, true);
    }

    @Test(expectedExceptions = MappingBuilderException.class)
    public void MappingBuilderExceptionTest5() throws Exception {
        IOException ex = new IOException("IO error");
        throw new MappingBuilderException("MappingBuilderException", ex, false, false);
    }

    //MaxRecursiveLevelClassException
    @Test(expectedExceptions = MaxRecursiveLevelClassException.class)
    public void MaxRecursiveLevelClassExceptionTest1() throws Exception {
        throw new MaxRecursiveLevelClassException("MaxRecursiveLevelClassException");
    }

    @Test(expectedExceptions = MaxRecursiveLevelClassException.class)
    public void MaxRecursiveLevelClassExceptionTest2() throws Exception {
        IOException ex = new IOException("IO error");
        throw new MaxRecursiveLevelClassException(ex);
    }

    @Test(expectedExceptions = MaxRecursiveLevelClassException.class)
    public void MaxRecursiveLevelClassExceptionTest3() throws Exception {
        IOException ex = new IOException("IO error");
        throw new MaxRecursiveLevelClassException("MaxRecursiveLevelClassException", ex);
    }

    @Test(expectedExceptions = MaxRecursiveLevelClassException.class)
    public void MaxRecursiveLevelClassExceptionTest4() throws Exception {
        IOException ex = new IOException("IO error");
        throw new MaxRecursiveLevelClassException("MaxRecursiveLevelClassException", ex, true, true);
    }

    @Test(expectedExceptions = MaxRecursiveLevelClassException.class)
    public void MaxRecursiveLevelClassExceptionTest5() throws Exception {
        IOException ex = new IOException("IO error");
        throw new MaxRecursiveLevelClassException("MaxRecursiveLevelClassException", ex, false, false);
    }
}
