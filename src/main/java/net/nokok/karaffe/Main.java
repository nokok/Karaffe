package net.nokok.karaffe;

import java.io.IOException;
import net.nokok.karaffe.javacc.ast.KaraffeParser;
import net.nokok.karaffe.javacc.ast.ParseException;
import net.nokok.karaffe.javacc.ast.Program;

public class Main {

    /**
     * krfrunコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, ParseException {
        KaraffeParser parser = new KaraffeParser("type Any\ntype Hoge");
        Program program = parser.parse();
    }
}
