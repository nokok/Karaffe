package org.karaffe.compiler.base;

import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public interface BytecodeContainer {
    void addBytecode(Path filePath, byte[] bytecode);

    Set<Map.Entry<Path, byte[]>> getBytecodes();
}
