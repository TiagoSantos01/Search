package com.tiagosantos.search.domain;

public enum Messages {
      FILE_IS_NULL ("Nenhum arquivo encontrado."),
    FILE_TYPE_SUPPORTED("Tipo de arquivos suportado: .txt"),
    NOT_FILE("Não é um arquivo."),
    NO_PATH_PROVIDED("Nenhum caminho informado."),
    CACHE_ALREADY_CREATED("Arquivo de cache já criado."),
    CACHE_CREATED_SUCCESS("Arquivo de cache criado com sucesso."),
    FILE_NOT_EXISTS("Arquivo não existe."),
    ERROR_WRITING_CACHE_FILE("Erro ao escrever no arquivo do cache"),
    EMPTY_CACHE("Cache vazio"),
    INVALID_CACHE_VALUES("Cache com valores inválidos"),
    FILE_NOT_EXISTS_FORMAT("Arquivo %s não existe."),
    ERROR_CLEANING_CACHE("Erro ao limpar cache"),
    EMPTY_KEY("Chave vazia"),
    KEY_NOT_FOUND("Chave não encontrada");

    private final String value;
    Messages(String value) {
         this.value= value;
    }

    public String getValue() {
        return this.value;
    }
}
