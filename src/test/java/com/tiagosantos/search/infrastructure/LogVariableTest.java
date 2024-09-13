package com.tiagosantos.search.infrastructure;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogVariableTest {
    @Test
    public void  testLogInfo(){
        LogVariable log= new LogVariable();
        log.Info("Teste");
        assertEquals(1,log.getLogs().size());
        assertEquals("INFO: Teste",log.getLogs().get(0));
    }
    @Test
    public void  testLogWarn(){
        LogVariable log= new LogVariable();
        log.Warn("Teste");
        assertEquals(1,log.getLogs().size());
        assertEquals("WARN: Teste",log.getLogs().get(0));
    }
    @Test
    public void  testLogError(){
        LogVariable log= new LogVariable();
        log.Error("Teste");
        assertEquals(1,log.getLogs().size());
        assertEquals("Error: Teste",log.getLogs().get(0));
    }

}
