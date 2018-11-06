package org.karaffe.compiler.base.context;

import org.karaffe.compiler.base.BackendConfiguration;
import org.karaffe.compiler.base.BackendType;

import java.util.Objects;

public class BackendContext implements BackendConfiguration {
    private BackendType backendType;

    public BackendContext(BackendType backendType) {
        this.backendType = Objects.requireNonNull(backendType);
    }

    @Override
    public BackendType getTargetBackendType() {
        return this.backendType;
    }

    @Override
    public void setTargetBackendType(BackendType backendType) {
        this.backendType = Objects.requireNonNull(backendType);
    }

    public static BackendConfiguration defaultBackend() {
        return new JavaVMConfiguration();
    }
}
