package com.tiagosantos.search.infrastructure;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegexTest {

    @Test
    public void testExplodeStringWithSpace(){
        Regex regex= new Regex("\\s+","a b c d  e");
        String[] result=regex.explodeString();
        assertEquals(5,result.length);
        assertEquals(Arrays.stream(new String[]{"a", "b", "c", "d", "e"}).toList(), Arrays.stream(result).toList());
    }
    @Test
    public void testExplodeStringWithNumber(){
        Regex regex= new Regex("[0-9]","abc1def");
        String[] result=regex.explodeString();
        assertEquals(2,result.length);
        assertEquals(Arrays.stream(new String[]{"abc", "def"}).toList(), Arrays.stream(result).toList());
    }

    @Test
    public void testNotMatchStringGetFrist(){
        Regex regex= new Regex("[0-9]{3}","abc def ghi");
        String result=regex.matchStringGetFrist();
        assertEquals("", result);
    }
    @Test
    public void testMatchStringGetFrist(){
        Regex regex= new Regex("[a-z]{3}","abc def ghi");
        String result=regex.matchStringGetFrist();
        assertEquals("abc", result);
    }

    @Test
    public void testNotMatchString(){
        Regex regex= new Regex("[0-9]{3}","abc def ghi");
        List<String> result=regex.matchString();
        assertEquals(0,result.size());
    }
    @Test
    public void testMatchString(){
        Regex regex= new Regex("[a-z]{3}","abc def ghi");
        List<String> result=regex.matchString();
        assertEquals(3,result.size());
        assertEquals(List.of("abc","def","ghi"), result);
    }
}
