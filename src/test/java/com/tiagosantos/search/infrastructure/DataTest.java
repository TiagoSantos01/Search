package com.tiagosantos.search.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataTest {
    LogVariable log;
    @BeforeEach
    public void setup(){
        this.log=new LogVariable();
    }
    @Test
    public void testFailKeyIsEmpty(){
        Data data= new Data(this.log);
        boolean result= data.add("","");

        assertFalse(result);
        assertEquals(1,this.log.getLogs().size());
        assertEquals("WARN: Chave vazia",this.log.getLogs().get(0));
    }
    @Test
    public void testSuccessAddKeyAndValue(){
        Data data= new Data(this.log);
        boolean result= data.add("Key","Value");

        assertTrue(result);
        assertEquals(0,this.log.getLogs().size());
        assertTrue(data.getValues().containsKey("key"));
        assertTrue(data.getValues().containsValue(List.of("Value")));
    }
    @Test
    public void testSuccessAddKeyIntAndValue(){
        Data data= new Data(this.log);
        boolean result= data.add(1,"Value");

        assertTrue(result);
        assertEquals(0,this.log.getLogs().size());
        assertTrue(data.getValues().containsKey("1"));
        assertTrue(data.getValues().containsValue(List.of("Value")));
    }

    @Test
    public void testSuccessDeleteKey(){
        Data data= new Data(this.log);
        data.add("Key","Value");

        assertEquals(0,this.log.getLogs().size());
        assertTrue(data.getValues().containsKey("key"));
        assertTrue(data.getValues().containsValue(List.of("Value")));

        data.deleteKey("Key");
        assertFalse(data.getValues().containsKey("Key"));
    }
    @Test
    public void testFailDeleteValueNotExistKey(){
        Data data= new Data(this.log);
        data.add("Key","Value");

        assertEquals(0,this.log.getLogs().size());
        assertTrue(data.getValues().containsKey("key"));
        assertTrue(data.getValues().containsValue(List.of("Value")));

        boolean result= data.deleteValue("NotKey","Value");
        assertFalse(result);
        assertEquals(1,this.log.getLogs().size());
        assertEquals("WARN: Chave não encontrada",this.log.getLogs().get(0));
    }
    @Test
    public void testSuccessDeleteValueInKey(){
        Data data= new Data(this.log);
        data.add("Key","Value");

        assertEquals(0,this.log.getLogs().size());
        assertTrue(data.getValues().containsKey("key"));
        assertTrue(data.getValues().containsValue(List.of("Value")));

        boolean result= data.deleteValue("Key","Value");
        assertTrue(result);
        assertTrue(data.getValues().containsKey("key"));
        assertEquals(0,data.getValues().get("key").size());

    }
    @Test
    public void testSuccessSetValues(){
        HashMap<String,List<String>>values = new HashMap<>();
        values.put("key",List.of("Value"));
        Data data= new Data(this.log);
        data.setValues(values);

        assertEquals(0,this.log.getLogs().size());
        assertTrue(data.getValues().containsKey("key"));
        assertTrue(data.getValues().containsValue(List.of("Value")));


    }
    @Test
    public void testSuccessGetValues(){
        HashMap<String,List<String>>values = new HashMap<>();
        values.put("key",List.of("Value"));
        Data data= new Data(this.log);
        data.setValues(values);

        assertEquals(0,this.log.getLogs().size());
        assertTrue(data.getValues().containsKey("key"));
        assertTrue(data.getValues().containsValue(List.of("Value")));
    }
    @Test
    public void testFailFind(){
        HashMap<String,List<String>>values = new HashMap<>();
        values.put("Key",List.of("Value"));
        Data data= new Data(this.log);
        data.setValues(values);

        List<String>result =data.find("KeyNotExist");
        assertEquals(0,this.log.getLogs().size());
        assertEquals(0,result.size());
    }
    @Test
    public void testSuccessFind(){
        HashMap<String,List<String>>values = new HashMap<>();
        values.put("key",List.of("Value"));
        Data data= new Data(this.log);
        data.setValues(values);

        List<String>result =data.find("Key");
        assertEquals(0,this.log.getLogs().size());
        assertEquals(1,result.size());
        assertTrue(result.contains("Value"));
    }
}
