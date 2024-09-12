package com.tiagosantos.search.infrastructure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {

    private final String pathFolder;
    public Document(String pathFolder){
        this.pathFolder=pathFolder;
    }

    public File[] getListByFolder(){
        File folder =new File(this.pathFolder);
        return folder.listFiles();
    }

    public void Save(HashMap<String, List<String>> document){

        try (FileWriter writer = new FileWriter("cache.data")) {
            // Escrever as chaves e valores no arquivo
            for (Map.Entry<String, List<String>> entry : document.entrySet()) {
                writer.write(entry.getKey() + ";" + String.join(",", entry.getValue()) + "\n");
            }
            System.out.println("Arquivo de cache criado com sucesso.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar o arquivo.");
            e.printStackTrace();
        }
    }
}
