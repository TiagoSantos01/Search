package com.tiagosantos.search.application;

import com.tiagosantos.search.infrastructure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchUseCaseTest {
    Document document;
    Cache cache;
    Data data;
    Time time;
    LogVariable log;
    @BeforeAll
    public static void setupAll(){
        LogVariable log = new LogVariable();
        Document document = new Document("./dataTest");
        Data data = new Data(log);
        Cache cache = new Cache(log, "cache.test");
        CacheUseCase cacheUseCase= new CacheUseCase(log,document,data,cache);
        cacheUseCase.execute();
    }
    @BeforeEach
    public void setup(){
        this.log = new LogVariable();
        this.document = new Document("./dataTest");
        this.data = new Data(this.log);
        this.time= new Time(this.log);
        this.cache = new Cache(this.log, "cache.test");
    }

    @Test
    public void testMatchSearchTitleMovie(){

        SeachUseCase seachUseCase= new SeachUseCase(this.log,this.data,this.time,this.cache);
        seachUseCase.execute("30 rock: a xxx parody");

        assertEquals(5,this.log.getLogs().size());

        assertEquals("Foram encontradas 1 ocorrências pelo termo \"30 rock: a xxx parody\"",this.log.getLogs().get(2));
        assertEquals("Os arquivos que possuem \"30 rock: a xxx parody\" são:",this.log.getLogs().get(3));
        assertEquals("./dataTest/30-rock-a-xxx-parody.txt",this.log.getLogs().get(4));
    }
    @Test
    public void testMatchSearchCastFistName(){

        SeachUseCase seachUseCase= new SeachUseCase(this.log,this.data,this.time,this.cache);
        seachUseCase.execute("lisa");

        assertEquals(5,this.log.getLogs().size());

        assertEquals("Foram encontradas 1 ocorrências pelo termo \"lisa\"",this.log.getLogs().get(2));
        assertEquals("Os arquivos que possuem \"lisa\" são:",this.log.getLogs().get(3));
        assertEquals("./dataTest/30-rock-a-xxx-parody.txt",this.log.getLogs().get(4));
    }
    @Test
    public void testMatchSearchCastLastName(){

        SeachUseCase seachUseCase= new SeachUseCase(this.log,this.data,this.time,this.cache);
        seachUseCase.execute("ann");

        assertEquals(5,this.log.getLogs().size());

        assertEquals("Foram encontradas 1 ocorrências pelo termo \"ann\"",this.log.getLogs().get(2));
        assertEquals("Os arquivos que possuem \"ann\" são:",this.log.getLogs().get(3));
        assertEquals("./dataTest/30-rock-a-xxx-parody.txt",this.log.getLogs().get(4));
    }

    @Test
    public void testMatchSearchCastFullName(){

        SeachUseCase seachUseCase= new SeachUseCase(this.log,this.data,this.time,this.cache);
        seachUseCase.execute("lisa ann");

        assertEquals(5,this.log.getLogs().size());

        assertEquals("Foram encontradas 1 ocorrências pelo termo \"lisa ann\"",this.log.getLogs().get(2));
        assertEquals("Os arquivos que possuem \"lisa ann\" são:",this.log.getLogs().get(3));
        assertEquals("./dataTest/30-rock-a-xxx-parody.txt",this.log.getLogs().get(4));
    }

    @Test
    public void testMatchSearchCastLastAndFristName(){

        SeachUseCase seachUseCase= new SeachUseCase(this.log,this.data,this.time,this.cache);
        seachUseCase.execute("ann lisa");

        assertEquals(4,this.log.getLogs().size());

        assertEquals("Foram encontradas 1 ocorrências pelo termo \"ann lisa\"",this.log.getLogs().get(1));
        assertEquals("Os arquivos que possuem \"ann lisa\" são:",this.log.getLogs().get(2));
        assertEquals("./dataTest/30-rock-a-xxx-parody.txt",this.log.getLogs().get(3));
    }

    @Test
    public void testMatchSearchYear(){

        SeachUseCase seachUseCase= new SeachUseCase(this.log,this.data,this.time,this.cache);
        seachUseCase.execute("2009");

        assertEquals(4,this.log.getLogs().size());

        assertEquals("Foram encontradas 1 ocorrências pelo termo \"2009\"",this.log.getLogs().get(1));
        assertEquals("Os arquivos que possuem \"2009\" são:",this.log.getLogs().get(2));
        assertEquals("./dataTest/30-rock-a-xxx-parody.txt",this.log.getLogs().get(3));
    }

    @Test
    public void testMatchSearchLowerCase(){

        SeachUseCase seachUseCase= new SeachUseCase(this.log,this.data,this.time,this.cache);
        seachUseCase.execute("Lisa Ann");

        assertEquals(5,this.log.getLogs().size());

        assertEquals("Foram encontradas 1 ocorrências pelo termo \"Lisa Ann\"",this.log.getLogs().get(2));
        assertEquals("Os arquivos que possuem \"Lisa Ann\" são:",this.log.getLogs().get(3));
        assertEquals("./dataTest/30-rock-a-xxx-parody.txt",this.log.getLogs().get(4));
    }
}
