package com.tiagosantos.search.cli;

import com.tiagosantos.search.application.CacheUseCase;

public class Cache {
    public static void main(String[] args) {

        CacheUseCase cacheUseCase= new CacheUseCase();
        cacheUseCase.execute();
    }
}
