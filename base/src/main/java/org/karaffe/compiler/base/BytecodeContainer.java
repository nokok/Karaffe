package org.karaffe.compiler.base;

import java.nio.file.Path;
import java.util.Map;

public interface BytecodeContainer {
    void addBytecode(Path filePath, byte[] bytecode);

    Map<Path, byte[]> getBytecodes();
}
