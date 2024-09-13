package com.tiagosantos.search.infrastructure;

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
                throw new NullPointerException("Nenhum caminho informado");
            File file= new File(this.cache);
            if(!file.createNewFile()){
                this.log.Warn("Arquivo de cache ja criado.");
                return;
            }
            this.log.Info("Arquivo de cache criado com sucesso.");
        } catch (Exception error) {
            this.log.Error(error.getMessage());
        }
    }

    public void write(HashMap<String, List<String>> document, Boolean append) {
        try {
            if (!new File(this.cache).exists())
                throw new Exception("Arquivo não existe.");
            try (FileWriter file = new FileWriter(this.cache, append)) {
                for (Map.Entry<String, List<String>> entry : document.entrySet()) {
                    file.write(entry.getKey() + ";" + String.join(",", entry.getValue()) + "\n");
                }
            } catch (IOException errorIO) {
                this.log.Error("Erro ao escrever no arquivo do cache");
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
                throw new Exception("Cache Vazio");

            try (BufferedReader br = new BufferedReader(fileReader)) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] line = linha.split(";");
                    if (line.length != 2)
                        throw new Exception("Cache com valores invalido");
                    document.put(line[0], Arrays.stream(line[1].split(",")).toList());
                }
            } catch (IOException errorIO) {
                this.log.Error(errorIO.getMessage());
            }
            return document;
        }catch (IOException errorIO)
        {
            this.log.Error(String.format("Arquivo %s não existe",this.cache));


        } catch (Exception error) {
            this.log.Error( error.getMessage());
        }
        return new HashMap<>();
    }

    public void clear(){
        try {
        if (!new File(this.cache).exists())
            throw new Exception("Arquivo não existe.");
        try (FileWriter file = new FileWriter(this.cache, false)) {
                file.write("");
        } catch (IOException errorIO) {
            this.log.Error("Error ao limpar cache");
        }

        } catch (Exception error) {
            this.log.Error(error.getMessage());
        }
    }
}
