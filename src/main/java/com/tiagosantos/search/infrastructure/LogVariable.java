package com.tiagosantos.search.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class LogVariable implements Log {

    List<String> logs= new ArrayList<>();
    public void Info(String message)
    {
        logs.add(String.format("INFO: %s",message));
    }
    public void Warn(String message)
    {
        logs.add(String.format("WARN: %s",message));
    }
    public void Error(String message)
    {
        logs.add(String.format("Error: %s",message));
    }
    public  void println(String message)
    {
        logs.add(message);
    }
    public List<String> getLogs() {
        return logs;
    }
}
