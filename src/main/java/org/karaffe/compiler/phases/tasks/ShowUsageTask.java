package org.karaffe.compiler.phases.tasks;

import java.util.concurrent.Callable;

public class ShowUsageTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("krf [subcommand]");
        return sb.toString();
    }
}
