package karaffe;

import java.util.List;
import java.util.stream.Stream;
import karaffe.compiler.phase.checker.PackagePathChecker;
import karaffe.compiler.phase.gencode.ClassGen;
import karaffe.compiler.phase.gencode.KClassWriter;
import karaffe.compiler.phase.parser.ParserPhase;
import karaffe.compiler.phase.resolvers.ResolvePhase;
import karaffe.compiler.phase.scope.ScopePhase;

public class Main {

    public static final String VERSION = "0.0.0";

    /**
     * krfcコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String... args) throws Exception {
        //Parser.main(args);

        args = new String[]{"Int.krf"};
        Stream.of(args)
                .map(new ParserPhase())
                .map(new ScopePhase())
                .map(new ResolvePhase())
                .map(new PackagePathChecker())
                .map(new ClassGen())
                .flatMap(List::stream)
                .forEach(new KClassWriter());
    }
}
