package com.tiagosantos.search.domain;

public enum Messages {
      FILE_IS_NULL ("Nenhum arquivo encontrado."),
    FILE_TYPE_SUPPORTED("Tipo de arquivos suportado: .txt"),
    NOT_FILE("Não é um arquivo.");

    private final String value;
    Messages(String value) {
         this.value= value;
    }

    public String getValue() {
        return this.value;
    }
}
