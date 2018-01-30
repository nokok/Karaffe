package org.karaffe.compiler.context;

public class TypeInferenceContext {
    private final String query;

    private TypeInferenceContext() {
        this("/");
    }

    private TypeInferenceContext(String query) {
        this.query = query;
    }

    public static TypeInferenceContext create() {
        return new TypeInferenceContext();
    }

    public static TypeInferenceContext withQuery(String query) {
        return new TypeInferenceContext(query);
    }
}
