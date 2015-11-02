package karaffe.compiler.asm;

import java.util.List;
import karaffe.compiler.ClassDef;
import karaffe.compiler.Program;

public class BytecodeGenerator implements BytecodeGen {

    @Override
    public Bytecode gen(Program program) {
        Bytecode byteCode = new Bytecode();
        List<ClassDef> classDefList = program.classDefList();
        ClassDef classDef = classDefList.get(0);
        return byteCode;
    }

}
