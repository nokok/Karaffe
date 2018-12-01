package org.karaffe.compiler;

public class SemanticAnalysisException extends KaraffeCompilerRuntimeException {
    public SemanticAnalysisException(String message) {
        super(message);
    }

    public SemanticAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }

}
