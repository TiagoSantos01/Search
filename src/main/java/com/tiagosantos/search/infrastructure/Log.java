package com.tiagosantos.search.infrastructure;

public interface Log {
    public default void Info(String message)
    {
    }
    public default void Warn(String message)
    {
    }
    public default void Error(String message)
    {
    }
    public default void println(String message)
    {
    }
}
