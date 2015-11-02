package karaffe.compiler.asm;

import karaffe.compiler.Program;

public interface BytecodeGen {

    public Bytecode gen(Program program);
}
