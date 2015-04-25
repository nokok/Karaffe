/**
 * Karaffe Programming Language
 */
package karaffe.compiler;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;
import karaffe.compiler.output.CompileError;
import karaffe.compiler.phase.checker.Warnings;
import karaffe.compiler.phase.gencode.ClassGen;
import karaffe.compiler.phase.gencode.KClassWriter;
import karaffe.compiler.phase.parser.ParserPhase;
import karaffe.compiler.phase.resolvers.ResolvePhase;
import karaffe.compiler.phase.scope.ScopePhase;

public class KCompiler {

    public static final String VERSION = "0.0.0";

    private final File file;

    public KCompiler(File file) {
        this.file = file;
        if (!file.exists()) {
            throw new RuntimeException(
                    new CompileError(
                            "ファイルが見つかりません",
                            file.getAbsolutePath() + " が渡されました。"
                            + "ファイルが存在しないか、アクセスできません。\n"
                            + "ファイルが存在することと、ソースファイルのパーミッションが正しいか確認して下さい。\n"
                            + "続行できません。コンパイラを終了します。").toString());
        }
    }

    public void compile() {
        compile(file.getAbsolutePath());
    }

    private void compile(String path) {
        Stream.of(path)
                .map(new ParserPhase())
                .map(new ScopePhase())
                .map(new ResolvePhase())
                .map(new Warnings())
                .map(new ClassGen())
                .flatMap(List::stream)
                .forEach(new KClassWriter());
    }

    public static void compileBySource(String src) {
        new KCompiler(new File(".")).compile(src);
    }
}
