package net.nokok.karaffe;

import java.io.IOException;
import net.nokok.karaffe.javacc.KaraffeParser;
import net.nokok.karaffe.javacc.ParseException;
import net.nokok.karaffe.javacc.Program;

public class Main {

    /**
     * krfrunコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, ParseException {
        KaraffeParser parser = KaraffeParser.createParser("type Any\ntype Hoge");
        Program program = parser.start();
    }

    public static String usage() {
        return "";
    }
}
