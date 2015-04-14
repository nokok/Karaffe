package karaffe;

import java.io.File;
import java.util.stream.Stream;
import karaffe.compiler.KCompiler;

public class Main {

    /**
     * krfcコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String... args) throws Exception {
        //Parser.main(args);

        args = new String[]{"Int.krf"}; //debug
        Stream.of(args)
                .map(File::new)
                .map(KCompiler::new)
                .forEach(KCompiler::compile);
    }
}
