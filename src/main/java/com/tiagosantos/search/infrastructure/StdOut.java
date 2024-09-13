package com.tiagosantos.search.infrastructure;

import com.tiagosantos.search.domain.Color;

public class StdOut implements Log {
    public void Info(String message)
    {
        System.out.println(String.format("%s[INFO]%s %s", Color.BLUE.getValue(),Color.RESET.getValue(),message));
    }
    public void Warn(String message)
    {
        System.out.println(String.format("%s[WARN]%s %s",Color.YELLOW.getValue(),Color.RESET.getValue(),message));
    }
    public void Error(String message)
    {
        System.out.println(String.format("%s[ERROR]%s %s",Color.RED.getValue(),Color.RESET.getValue(),message));

    }
    public  void println(String message)
    {
        System.out.println(message);
    }
}
