package karaffe;

import java.io.File;
import java.util.stream.Stream;
import karaffe.compiler.KCompiler;

public class Main {

    public static void main(String... args) throws Exception {
        Stream.of(args)
                .map(File::new)
                .map(KCompiler::new)
                .map(KCompiler::compile)
                .forEach(l -> {
                    l.forEach(System.out::println);
                });
    }
}
