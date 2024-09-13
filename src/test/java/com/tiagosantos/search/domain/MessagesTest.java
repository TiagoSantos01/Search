package com.tiagosantos.search.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessagesTest {

    @Test
    void testFileIsNullMessage() {
        assertEquals("Nenhum arquivo encontrado.", Messages.FILE_IS_NULL.getValue());
    }

    @Test
    void testFileTypeSupportedMessage() {
        assertEquals("Tipo de arquivos suportado: .txt", Messages.FILE_TYPE_SUPPORTED.getValue());
    }

    @Test
    void testNotFileMessage() {
        assertEquals("Não é um arquivo.", Messages.NOT_FILE.getValue());
    }

    @Test
    public void testNoPathProvided() {
        assertEquals("Nenhum caminho informado.", Messages.NO_PATH_PROVIDED.getValue());
    }

    @Test
    public void testCacheAlreadyCreated() {
        assertEquals("Arquivo de cache já criado.", Messages.CACHE_ALREADY_CREATED.getValue());
    }

    @Test
    public void testCacheCreatedSuccess() {
        assertEquals("Arquivo de cache criado com sucesso.", Messages.CACHE_CREATED_SUCCESS.getValue());
    }

    @Test
    public void testFileNotExists() {
        assertEquals("Arquivo não existe.", Messages.FILE_NOT_EXISTS.getValue());
    }

    @Test
    public void testErrorWritingCacheFile() {
        assertEquals("Erro ao escrever no arquivo do cache", Messages.ERROR_WRITING_CACHE_FILE.getValue());
    }

    @Test
    public void testEmptyCache() {
        assertEquals("Cache vazio", Messages.EMPTY_CACHE.getValue());
    }

    @Test
    public void testInvalidCacheValues() {
        assertEquals("Cache com valores inválidos", Messages.INVALID_CACHE_VALUES.getValue());
    }

    @Test
    public void testFileNotExistsFormat() {
        assertEquals("Arquivo %s não existe.", Messages.FILE_NOT_EXISTS_FORMAT.getValue());
    }

    @Test
    public void testErrorCleaningCache() {
        assertEquals("Erro ao limpar cache", Messages.ERROR_CLEANING_CACHE.getValue());
    }

    @Test
    public void testEmptyKey() {
        assertEquals("Chave vazia", Messages.EMPTY_KEY.getValue());
    }

    @Test
    public void testKeyNotFound() {
        assertEquals("Chave não encontrada", Messages.KEY_NOT_FOUND.getValue());
    }
}
