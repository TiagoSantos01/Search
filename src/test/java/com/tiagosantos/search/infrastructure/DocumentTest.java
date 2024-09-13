package com.tiagosantos.search.infrastructure;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentTest {

    @Test
    public void testListEmpty()
    {
        Document document = new Document("teste");

        java.io.File[] files= document.getListByFolder();

        assertNull(files);
    }
    @Test
    public void testListTwoFiles()
    {
        Document document = new Document("dataTest");

        java.io.File[] files= document.getListByFolder();

        assertEquals(2,files.length);
    }
}
