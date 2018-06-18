package org.karaffe.compiler.base.task.util;

public class ProcessTimer {
    private final long startTime;

    public ProcessTimer() {
        this.startTime = System.currentTimeMillis();
    }

    public long stop() {
        return System.currentTimeMillis() - this.startTime;
    }
}
