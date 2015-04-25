/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.gencode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.compileunits.CompileUnit;

public class ClassGen implements Function<AST, List<ByteCode>> {

    private final List<ByteCode> bytecodes = new ArrayList<>();

    public List<ByteCode> getBytecodes() {
        return bytecodes;
    }

    @Override
    public List<ByteCode> apply(AST ast) {
        CompileUnit compileUnit = (CompileUnit) ast;
        return compileUnit.toNode();
    }

}
