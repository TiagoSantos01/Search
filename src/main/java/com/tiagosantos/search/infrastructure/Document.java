package com.tiagosantos.search.infrastructure;

public class Document {

    private final String pathFolder;
    public Document(String pathFolder){
        this.pathFolder=pathFolder;
    }

    public java.io.File[] getListByFolder(){
        java.io.File folder =new java.io.File(this.pathFolder);
        return folder.listFiles();
    }

}
