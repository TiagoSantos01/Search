package com.tiagosantos.search.infrastructure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheTest {

    LogVariable log;
    String file="test.data";
    @BeforeEach
    public void setup(){
        this.log=new LogVariable();

    }
    @AfterEach
    public void cleanup(){
        new File(this.file).delete();
    }

@Test
    public void testFailCreateFilePathIsNull(){
        Cache cache =new Cache(this.log,null);
        cache.Create();
    assertEquals(1,log.getLogs().size());
    assertEquals("Error: Nenhum caminho informado", log.getLogs().get(0));

}
    @Test
    public void testFileExistNotCreate(){
        Cache cacheprepare =new Cache(this.log,this.file);
        cacheprepare.Create();

        this.log= new LogVariable();
        Cache cache =new Cache(this.log,this.file);
        cache.Create();
        assertEquals(1,log.getLogs().size());
        assertEquals("WARN: Arquivo de cache ja criado.", log.getLogs().get(0));
    }
    @Test
    public void testSuccessCreateFile(){
        Cache cache =new Cache(this.log,this.file);
        cache.Create();
        assertEquals(1,log.getLogs().size());
        assertEquals("INFO: Arquivo de cache criado com sucesso.", log.getLogs().get(0));
    }

    @Test
    public void testFailFileNotExist(){
        Cache cache =new Cache(this.log,this.file);
        cache.Create();

        HashMap<String, List<String>> texts=new HashMap<>();
        texts.put("Test", List.of("Text1"));
        new File(this.file).delete();
        cache.Write(texts);

        assertEquals(2,log.getLogs().size());
        assertEquals("Error: Arquivo não existe.", log.getLogs().get(1));
    }

    @Test
    public void testSuccessWrite(){
        Cache cache =new Cache(this.log,this.file);
        cache.Create();

        HashMap<String, List<String>> texts=new HashMap<>();
        texts.put("Test", List.of("Text1"));
        cache.Write(texts);

        assertEquals(1,log.getLogs().size());
    }

    @Test
    public void testSuccessWriteAppendTextInFile(){
        Cache cache =new Cache(this.log,this.file);
        cache.Create();

        HashMap<String, List<String>> texts=new HashMap<>();
        texts.put("Test", List.of("Text1"));
        cache.Write(texts);
        cache.Write(texts,true);

        assertEquals(1,log.getLogs().size());
    }

    @Test
    public void testFailCacheNotExist(){
        Cache cache =new Cache(this.log,this.file);
        cache.Read();

        assertEquals(1,log.getLogs().size());
        assertEquals("Error: Arquivo test.data não existe", log.getLogs().get(0));
    }
    @Test
    public void testFailFileEmpty(){
        Cache cache =new Cache(this.log,this.file);
        cache.Create();

        HashMap<String, List<String>> texts=new HashMap<>();
        cache.Read();

        assertEquals(2,log.getLogs().size());
        assertEquals("Error: Cache Vazio", log.getLogs().get(1));
    }
    @Test
    public void testFailValuesInvalids(){
        Cache cache =new Cache(this.log,this.file);
        cache.Create();

        HashMap<String, List<String>> texts=new HashMap<>();
        texts.put("Test",new ArrayList<>());
        cache.Write(texts);
        cache.Read();

        assertEquals(2,log.getLogs().size());
        assertEquals("Error: Cache com valores invalido", log.getLogs().get(1));

    }
    @Test
    public void testSuccessReader(){
        Cache cache =new Cache(this.log,this.file);
        cache.Create();

        HashMap<String, List<String>> texts=new HashMap<>();
        texts.put("Test",List.of("abc123"));
        cache.Write(texts);
        HashMap<String,List<String>> values= cache.Read();

        assertEquals(1,log.getLogs().size());
        assertEquals(1, values.size());
        assertTrue( values.containsKey("Test"));
        assertTrue( values.containsValue(List.of("abc123")));

    }
}
