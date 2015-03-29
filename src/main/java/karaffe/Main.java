package karaffe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import karaffe.compiler.phase.parser.Parser;
import karaffe.compiler.phase.parser.ParserPhase;
import karaffe.compiler.phase.resolvers.ResolvePhase;
import karaffe.compiler.phase.scope.ScopePhase;
import karaffe.core.Either;

public class Main {

    private static final List<Exception> errors = new ArrayList<>(1);
    public static final String VERSION = "0.0.0";

    /**
     * krfcコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String... args) throws Exception {
        Parser.main(args);
        Stream.of(args)
                .map(ParserPhase::apply)
                .map(Main::addListIfError)
                .filter(Objects::nonNull)
                //
                .map(ScopePhase::apply)
                .map(Main::addListIfError)
                .filter(Objects::nonNull)
                //
                .map(ResolvePhase::apply)
                .map(Main::addListIfError)
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    private static <L extends Exception, R> R addListIfError(Either<L, R> e) {
        if (e.isLeft()) {
            errors.add(e.left());
            return null;
        }
        return e.right();
    }
}
