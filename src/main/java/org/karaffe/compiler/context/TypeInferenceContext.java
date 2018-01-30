package org.karaffe.compiler.context;

public class TypeInferenceContext {
    public static TypeInferenceContext create() {
        return new TypeInferenceContext();
    }

    public static TypeInferenceContext withQuery(final String query) {
        return new TypeInferenceContext(query);
    }

    private final String query;

    private TypeInferenceContext() {
        this("/");
    }

    private TypeInferenceContext(final String query) {
        this.query = query;
    }
}
