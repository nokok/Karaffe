package it;

import org.junit.Test;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.visitor.DefMapBuilder;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class VisitorTest {
    @Test
    public void testPrinter() {
        final KaraffeTreeVisitor printer = new DefMapBuilder();
        final KaraffeParser parser = new KaraffeParser();
        final MatchResult result = parser.parse("class Main { public static void main(String[] args){if (true) {var a :Int;var b:Int;} else {var a :Int;}}}");
        result.getNode().get().accept(printer);
    }
}
