package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class AbstractTask implements Task, Future<Result> {

    @Override
    public boolean cancel(boolean b) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Result get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Result get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public Result run(CompilerContext context) {
        return null;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
