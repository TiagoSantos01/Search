package com.tiagosantos.search.domain;

public enum Color {
    RESET("\033[0m"),
    BLACK("\033[30m"),
    RED("\033[31m"),
    GREEN("\033[32m"),
    YELLOW("\033[33m"),
    BLUE("\033[34m"),
    MAGENTA("\033[35m"),
    CYAN("\033[36m"),
    WHITE("\033[37m");

    private final String value;
    Color(String value) {
        this.value= value;
    }

    public String getValue() {
        return this.value;
    }
}
