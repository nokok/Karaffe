package org.karaffe.compiler.base.ir;

import java.util.List;
import java.util.stream.Stream;

public interface IR {
    void add(Module module);

    void add(Function function);

    void add(Module module, Function function);

    void add(String moduleName, Function function);

    void add(Block block);

    Stream<Module> moduleStream();

    default Stream<Function> functionStream() {
        return this.moduleStream().flatMap(Module::functionStream);
    }

    default Stream<Block> blockStream() {
        return this.functionStream().flatMap(Function::blockStream);
    }

    Stream<Block> globalBlockStream();

    default Stream<Instruction> instructionStream() {
        return this.blockStream().flatMap(Block::instructionStream);
    }

    List<Module> getModules();

    List<Function> getFunctions();
}
