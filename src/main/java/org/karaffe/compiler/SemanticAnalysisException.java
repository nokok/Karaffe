package org.karaffe.compiler;

public class SemanticAnalysisException extends KaraffeCompilerRuntimeException {
    private static final long serialVersionUID = 1L;

    public SemanticAnalysisException(String message) {
        super(message);
    }

    public SemanticAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }

}
