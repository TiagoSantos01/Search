package com.tiagosantos.search.cli;

import com.tiagosantos.search.application.CacheUseCase;
import com.tiagosantos.search.application.SeachUseCase;

public class Search {
    public static void main(String[] args) {
        SeachUseCase seachUseCase= new SeachUseCase();
        seachUseCase.execute(args[0]);
    }
}
