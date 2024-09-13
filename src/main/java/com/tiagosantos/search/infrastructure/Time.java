package com.tiagosantos.search.infrastructure;

public class Time {

    private final Log log;

    private long timeStart;
    private long timeEnd;
    public Time(Log log){
        this.log= log;
    }
    public void start(){
        this.timeStart=System.nanoTime();
    }
    public void end(){
        this.timeEnd=System.nanoTime();
    }
    public long getNanoSegunds(){
        return this.timeEnd-this.timeStart;
    }
    public void getResult() {
        long timeNs= getNanoSegunds();
        if (timeNs < 10) {
            this.log.Info(String.format("Tempo de execução: %d ns",timeNs));
            this.log.Info("Bateu a meta!");
        } else if (timeNs < 1_000_000) {
            double timeMs = timeNs / 1_000_000.0;
            this.log.Info(String.format("Tempo de execução: %.3f ms%n", timeMs));
            if (timeMs < 0.01) {
                this.log.Info("Bateu a meta!");
            }
        } else if (timeNs < 1_000_000_000) {
            double timeMs = timeNs / 1_000_000.0;
            this.log.Info(String.format("Tempo de execução: %.3f ms%n", timeMs));
        } else {
            double timeS = timeNs / 1_000_000_000.0;
            this.log.Info(String.format("Tempo de execução: %.3f s%n", timeS));
        }
    }
}
