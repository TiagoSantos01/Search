package com.tiagosantos.search.infrastructure;

import com.tiagosantos.search.domain.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    private final String regex;
    private final String value;

    public Regex(String regex, String value) {
        this.regex = regex;
        this.value = value;
    }

    public String[] explodeString()
    {
        return value.split(regex);
    }

    public String matchStringGetFrist()
    {
        Pattern pattern = Pattern.compile(this.regex);

        Matcher matcher = pattern.matcher(this.value);

        if(!matcher.find())
            return "";
        return matcher.group();
    }
    public List<String> matchString()
    {
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        List<String> matchs= new ArrayList<>();
        while (matcher.find()) {
            matchs.add(matcher.group().trim());
        }
        return matchs;
    }
}
