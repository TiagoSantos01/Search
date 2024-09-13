package com.tiagosantos.search.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessagesTest {

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
        assertEquals("O cache foi criado com sucesso.", Messages.CACHE_CREATED_SUCCESS.getValue());
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

    @Test
    public void testInvalidCacheFile() {
        assertEquals("Arquivo inválido para criação de cache. O formato esperado é: título ano autor1 autor2 ...", Messages.INVALID_CACHE_FILE.getValue());
    }

    @Test
    public void testNoAuthorsFound() {
        assertEquals("Nenhum autor encontrado", Messages.NO_AUTHORS_FOUND.getValue());
    }

    @Test
    public void testErrorCreatingCache() {
        assertEquals("Error ao criar cache %s %s", Messages.ERROR_CREATING_CACHE.getValue());
    }

    @Test
    public void testProgressStatus() {
        assertEquals("Progresso: %s de %s Error: %s Alerta %s", Messages.PROGRESS_STATUS.getValue());
    }

    @Test
    public void testErrorAndWarningStatus() {
        assertEquals("Error: %s Alerta %s ", Messages.ERROR_AND_WARNING_STATUS.getValue());
    }

    @Test
    public void testOccurrencesFound() {
        assertEquals("Foram encontradas %s ocorrências pelo termo \"%s\"", Messages.OCCURRENCES_FOUND.getValue());
    }

    @Test
    public void testFilesContainingTerm() {
        assertEquals("Os arquivos que possuem \"%s\" são:", Messages.FILES_CONTAINING_TERM.getValue());
    }
}
