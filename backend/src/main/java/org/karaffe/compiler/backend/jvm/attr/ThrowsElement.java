package org.karaffe.compiler.backend.jvm.attr;

import java.util.List;

public interface ThrowsElement {
    List<Class<?>> getThrows();
}
