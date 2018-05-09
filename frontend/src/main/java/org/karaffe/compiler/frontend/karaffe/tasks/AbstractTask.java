package org.karaffe.compiler.frontend.karaffe.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractTask implements Task {

    private final List<Runnable> onFailureListeners = new ArrayList<>();
    private final List<Runnable> onSuccessListeners = new ArrayList<>();

    protected void triggerSuccess() {
        this.onSuccessListeners.forEach(Runnable::run);
    }

    protected void triggerFailure() {
        this.onFailureListeners.forEach(Runnable::run);
    }

    @Override
    public List<Runnable> getOnFailureListeners() {
        return this.onFailureListeners;
    }

    @Override
    public List<Runnable> getOnSuccessListeners() {
        return this.onSuccessListeners;
    }

    @Override
    public void addOnFailureListener(Runnable runnable) {
        this.onFailureListeners.add(Objects.requireNonNull(runnable));
    }

    @Override
    public void addOnSuccessListener(Runnable runnable) {
        this.onSuccessListeners.add(Objects.requireNonNull(runnable));
    }
}
