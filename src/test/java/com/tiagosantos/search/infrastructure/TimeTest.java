package com.tiagosantos.search.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {
    LogVariable log;
    @BeforeEach
    public void setup(){
        this.log=new LogVariable();
    }

    @Test
    public void testMicroTime() throws InterruptedException {
        Time time =new Time(this.log);
        time.start();
sleep(2);
        time.end();
        time.getResult();
        double timeNs=time.getNanoSegunds()/ 1_000_000.0;

        assertEquals(1,this.log.getLogs().size());
        assertEquals(String.format("INFO: Tempo de execução: %.3f ms%n",timeNs),this.log.getLogs().get(0));

    }
    @Test
    public void testMicroTimeWithMeta(){
        Time time =new Time(this.log);
        time.start();
        time.end();
        time.getResult();
        double timeNs=time.getNanoSegunds()/ 1_000_000.0;

        assertEquals(2,this.log.getLogs().size());
        assertEquals(String.format("INFO: Tempo de execução: %.3f ms%n",timeNs),this.log.getLogs().get(0));
        assertEquals("INFO: Bateu a meta!",this.log.getLogs().get(1));

    }
    @Test
    public void testSegundsTime() throws InterruptedException {
        Time time =new Time(this.log);
        time.start();
        sleep(1000);
        time.end();
        time.getResult();
        double timeNs=time.getNanoSegunds()/ 1_000_000_000.0;

        assertEquals(1,this.log.getLogs().size());
        assertEquals(String.format("INFO: Tempo de execução: %.3f s%n",timeNs),this.log.getLogs().get(0));

    }
}
