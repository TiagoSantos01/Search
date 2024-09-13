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
        if(key.isEmpty()){
            this.log.Warn(Messages.EMPTY_KEY.getValue());
            return false;
        }
        if(!this.values.containsKey(key))
            this.values.put(key,new ArrayList<>());
        return this.values.get(key).add(value);
    }
    public boolean add(Integer key, String value) {
        return add(Integer.toString(key),value);
    }
    public void deleteKey(String key){
    this.values.remove(key);
    }
    public boolean deleteValue(String key, String value){
        if(this.values.containsKey(key)){
            return this.values.get(key).remove(value);
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
        if(this.values.containsKey(search))
            return this.values.get(search);
        return new ArrayList<>();
    }
}
