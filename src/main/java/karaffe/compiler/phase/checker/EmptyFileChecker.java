/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.checker;

import java.util.function.Consumer;
import karaffe.compiler.KCompiler;
import karaffe.compiler.phase.ToDo;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.compileunits.CompileUnit;

public class EmptyFileChecker implements Consumer<AST> {

    @Override
    public void accept(AST t) {
        if (t instanceof CompileUnit) {
            CompileUnit compileUnit = (CompileUnit) t;
            if (compileUnit.isEmpty()) {
                KCompiler.todoList.add(new ToDo(ToDo.Type.WARNING, "ファイルが空です。何も宣言されていないか、認識できません"));
            }
        }
    }

}
