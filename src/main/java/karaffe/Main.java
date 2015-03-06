package karaffe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import karaffe.compiler.phase.parser.ParserPhase;
import karaffe.compiler.phase.resolvers.ResolvePhase;
import karaffe.compiler.phase.scope.ScopePhase;
import karaffe.core.Either;

public class Main {

    private static final List<Exception> errors = new ArrayList<>(1);

    /**
     * krfcコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String... args) throws IOException {
        if (args == null || args.length == 0) {
            args = new String[]{"Int.krf"}; //debug
        }
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
        System.out.println(errors);
    }

    private static <L extends Exception, R> R addListIfError(Either<L, R> e) {
        if (e.isLeft()) {
            errors.add(e.left());
            return null;
        }
        return e.right();
    }
}
