package com.tiagosantos.search.infrastructure;

public class StdOut implements Log {
    public void Info(String message)
    {
        System.out.println(String.format("INFO: %s",message));
    }
    public void Warn(String message)
    {
        System.out.println(String.format("WARN: %s",message));
    }
    public void Error(String message)
    {
        System.out.println(String.format("Error: %s",message));

    }
}
