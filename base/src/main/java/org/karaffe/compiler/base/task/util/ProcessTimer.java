package org.karaffe.compiler.base.task.util;

public class ProcessTimer {
    private final long startTime;

    public ProcessTimer() {
        this.startTime = System.nanoTime();
    }

    public long stop() {
        return System.nanoTime() - this.startTime;
    }
}
