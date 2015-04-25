/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.checker;

import java.util.function.Consumer;
import karaffe.compiler.output.Warning;
import karaffe.compiler.tree.AST;
import karaffe.compiler.tree.compileunits.CompileUnit;

public class EmptyFileChecker implements Consumer<AST> {

    @Override
    public void accept(AST t) {
        if (t instanceof CompileUnit) {
            CompileUnit compileUnit = (CompileUnit) t;
            if (compileUnit.isEmpty()) {
                System.err.println(new Warning("ファイルが空です", "何も宣言がされていないか、認識できません。"));
            }
        }
    }

}
