package com.tiagosantos.search.infrastructure;

import com.tiagosantos.search.domain.Messages;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {

    public String cache;
    private final Log log;

    public Cache(){
        this.log= new StdOut();
        this.cache="cache.data";
    }
    public Cache(Log log,String cache){
        this.log= log;
        this.cache=cache;
    }
    public boolean isExist() {
        return new File(this.cache).exists();
    }
    public void create() {

        try {
            if(this.cache==null || this.cache.isEmpty())
                throw new NullPointerException(Messages.NO_PATH_PROVIDED.getValue());
            File file= new File(this.cache);
            if(!file.createNewFile()){
                this.log.Warn(Messages.CACHE_ALREADY_CREATED.getValue());
                return;
            }
            this.log.Info(Messages.CACHE_FILE_CREATED_SUCCESS.getValue());
        } catch (Exception error) {
            this.log.Error(error.getMessage());
        }
    }

    public void write(HashMap<String, List<String>> document, Boolean append) {
        try {
            if (!new File(this.cache).exists())
                throw new Exception(Messages.FILE_NOT_EXISTS.getValue());
            try (FileWriter file = new FileWriter(this.cache, append)) {
                for (Map.Entry<String, List<String>> entry : document.entrySet()) {
                    file.write(entry.getKey() + ";" + String.join(",", entry.getValue()) + "\n");
                }
            } catch (IOException errorIO) {
                this.log.Error(Messages.ERROR_WRITING_CACHE_FILE.getValue());
            }
        }catch (Exception error)
        {
            this.log.Error(error.getMessage());
        }
    }

    public void write(HashMap<String, List<String>> document) {
        write(document,false);
    }

    public HashMap<String, List<String>> read() {
        HashMap<String, List<String>> document = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(this.cache);
            if (!fileReader.ready())
                throw new Exception(Messages.EMPTY_CACHE.getValue());

            try (BufferedReader br = new BufferedReader(fileReader)) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] line = linha.split(";");
                    if (line.length != 2)
                        throw new Exception(Messages.INVALID_CACHE_VALUES.getValue());
                    document.put(line[0], Arrays.stream(line[1].split(",")).toList());
                }
            } catch (IOException errorIO) {
                this.log.Error(errorIO.getMessage());
            }
            return document;
        }catch (IOException errorIO)
        {
            this.log.Error(String.format(Messages.FILE_NOT_EXISTS_FORMAT.getValue(),this.cache));


        } catch (Exception error) {
            this.log.Error( error.getMessage());
        }
        return new HashMap<>();
    }

    public void clear(){
        try {
        if (!new File(this.cache).exists())
            throw new Exception(Messages.FILE_NOT_EXISTS.getValue());
        try (FileWriter file = new FileWriter(this.cache, false)) {
                file.write("");
        } catch (IOException errorIO) {
            this.log.Error(Messages.ERROR_CLEANING_CACHE.getValue());
        }

        } catch (Exception error) {
            this.log.Error(error.getMessage());
        }
    }
}
