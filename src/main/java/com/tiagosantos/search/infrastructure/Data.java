package com.tiagosantos.search.infrastructure;

import com.tiagosantos.search.domain.Messages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {

    private HashMap<String, List<String>> values;
    private final Log log;
    public Data(Log log ){
        this.log=log;
        this.values=new HashMap<>();
    }
    public boolean add(String key, String value){
        String keyLowerCase=key.toLowerCase();
        if(keyLowerCase.isEmpty()){
            this.log.Warn(Messages.EMPTY_KEY.getValue());
            return false;
        }
        if(!this.values.containsKey(keyLowerCase.toLowerCase()))
            this.values.put(keyLowerCase.toLowerCase(),new ArrayList<>());
        return this.values.get(keyLowerCase.toLowerCase()).add(value);
    }
    public boolean add(Integer key, String value) {
        return add(Integer.toString(key),value);
    }
    public void deleteKey(String key){
    this.values.remove(key);
    }
    public boolean deleteValue(String key, String value){
        String keyLowerCase=key.toLowerCase();
        if(this.values.containsKey(keyLowerCase)){
            return this.values.get(keyLowerCase).remove(value);
        }
        this.log.Warn(Messages.KEY_NOT_FOUND.getValue());
        return false;
    }
    public void setValues(HashMap<String, List<String>> values) {
        this.values=values;
    }
    public HashMap<String, List<String>> getValues() {
        return this.values;
    }

    public List<String> find(String search){
        String searchLowerCase=search.toLowerCase();
        if(this.values.containsKey(searchLowerCase))
            return this.values.get(searchLowerCase);
        return new ArrayList<>();
    }
}
