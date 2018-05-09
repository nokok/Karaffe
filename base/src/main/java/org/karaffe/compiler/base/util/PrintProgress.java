package org.karaffe.compiler.base.util;

import java.util.Objects;

public class PrintProgress {

    private int total;
    private String currentTaskName;
    private int last = 0;

    public PrintProgress(int total) {
        this.total = total;
    }

    public void setTaskName(String currentTaskName) {
        this.currentTaskName = Objects.requireNonNull(currentTaskName);
        this.progress(last);
    }

    public void setTaskAndIncrement(String currentTaskName) {
        this.last++;
        this.setTaskName(currentTaskName);
    }

    public void resetTotalCount(int total) {
        this.total = total;
    }

    public void progress(int current) {
        this.last = current;
        double perc = (double) current / total;
        String bar = "";
        for (int i = 0; i < (perc * 10); i++) {
            bar += "#";
        }
        Platform.getStdOut().print(String.format("\r%-10s [%10s] %3.1f%%", currentTaskName, bar, perc * 100));
    }
}
