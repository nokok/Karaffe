package it;

import org.junit.Test;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.visitor.DefMapBuilder;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class VisitorTest {
    @Test
    public void testPrinter() {
        KaraffeTreeVisitor printer = new DefMapBuilder();
        KaraffeParser parser = new KaraffeParser();
        MatchResult result = parser.parse("class Main { public static void main(String[] args){if (true) {var a :Int;var b:Int;} else {var a :Int;}}}");
        result.getNode().get().accept(printer);
    }
}
