package org.karaffe.compiler.base;

public interface BackendConfiguration {
    BackendType getTargetBackendType();

    void setTargetBackendType(BackendType backendType);
}
