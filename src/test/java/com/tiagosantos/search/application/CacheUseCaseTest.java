package com.tiagosantos.search.application;

import com.tiagosantos.search.infrastructure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheUseCaseTest {
    Document document;
    Cache cache;
    Data data;
    LogVariable log;
    @BeforeEach
    public void setup(){
        this.log = new LogVariable();
        this.document = new Document("./dataTest");
        this.data = new Data(this.log);
        this.cache = new Cache(this.log, "cache.test");
    }
    @Test
    public void testNotFindFold(){
        this.document = new Document("./notExist");

    CacheUseCase cacheUseCase= new CacheUseCase(this.log,this.document,this.data,this.cache);
    cacheUseCase.execute();

        assertEquals(2,this.log.getLogs().size());
        assertEquals("Error: Nenhum arquivo encontrado. Tipo de arquivos suportado: .txt",this.log.getLogs().get(1));
    }

    @Test
    public void testIsNotFile(){

        CacheUseCase cacheUseCase= new CacheUseCase(this.log,this.document,this.data,this.cache);
        cacheUseCase.execute();

        assertEquals(16,this.log.getLogs().size());
        assertEquals("Error: Não é um arquivo. \n ./dataTest/empty",this.log.getLogs().get(2));
    }

    @Test
    public void testFileIsEmpty(){

        CacheUseCase cacheUseCase= new CacheUseCase(this.log,this.document,this.data,this.cache);
        cacheUseCase.execute();

        assertEquals(16,this.log.getLogs().size());
        assertEquals("WARN: Nenhum arquivo encontrado. ./dataTest/test",this.log.getLogs().get(4));
    }
    @Test
    public void testFileIsValuesInvalid(){

        CacheUseCase cacheUseCase= new CacheUseCase(this.log,this.document,this.data,this.cache);
        cacheUseCase.execute();

        assertEquals(16,this.log.getLogs().size());
        assertEquals("Error: Arquivo inválido para criação de cache. O formato esperado é: título ano autor1 autor2 ...",this.log.getLogs().get(7));
    }
    @Test
    public void testNotFoundCast(){

        CacheUseCase cacheUseCase= new CacheUseCase(this.log,this.document,this.data,this.cache);
        cacheUseCase.execute();

        assertEquals(16,this.log.getLogs().size());
        assertEquals("WARN: Nenhum autor encontrado",this.log.getLogs().get(9));
    }
    @Test
    public void testProgress(){

        CacheUseCase cacheUseCase= new CacheUseCase(this.log,this.document,this.data,this.cache);
        cacheUseCase.execute();

        assertEquals(16,this.log.getLogs().size());
        assertEquals("INFO: Progresso: 6 de 6 Error: 2 Alerta 1",this.log.getLogs().get(10));
    }
    @Test
    public void testAddValueEmptyLastnameInKey(){

        CacheUseCase cacheUseCase= new CacheUseCase(this.log,this.document,this.data,this.cache);
        cacheUseCase.execute();

        assertEquals(16,this.log.getLogs().size());
        assertEquals("WARN: Chave vazia",this.log.getLogs().get(11));
    }
    @Test
    public void testSuccessCreateCache(){

        CacheUseCase cacheUseCase= new CacheUseCase(this.log,this.document,this.data,this.cache);
        cacheUseCase.execute();

        assertEquals(16,this.log.getLogs().size());
        System.out.println(String.join("\n",this.log.getLogs()));
        assertEquals("INFO: O cache foi criado com sucesso.",this.log.getLogs().get(12));
        assertEquals("INFO: Error: 2 Alerta 1 ",this.log.getLogs().get(13));
        assertEquals("WARN: [./dataTest/test]",this.log.getLogs().get(14));
        assertEquals("Error: [./dataTest/empty, ./dataTest/title.txt]",this.log.getLogs().get(15));
        assertTrue(new File("cache.test").exists());
    }
}
