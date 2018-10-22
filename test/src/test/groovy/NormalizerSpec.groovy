import org.karaffe.compiler.base.attr.NormalizedTree
import org.karaffe.compiler.base.generator.Generator
import org.karaffe.compiler.base.pos.Position
import org.karaffe.compiler.base.tree.Tree
import org.karaffe.compiler.base.tree.expr.Exprs
import org.karaffe.compiler.base.tree.term.Terms
import org.karaffe.compiler.frontend.karaffe.visitor.NormalizeVisitor
import spock.lang.Specification

class NormalizerSpec extends Specification {

    private NormalizeVisitor visitor = new NormalizeVisitor()

    def "null tree"() {
        expect:
        !visitor.isNormalizable(null)
    }

    def "Normalizable group"() {
        expect:
        visitor.isNormalizable(Exprs.ifExpr(Position.noPos(), Terms.emptyTree(), Terms.emptyTree(), Terms.emptyTree()))
        visitor.isNormalizable(Exprs.intValue("1"))
        visitor.isNormalizable(Exprs.stringValue(Position.noPos(), "ABC"))
        visitor.isNormalizable(Exprs.exprName(Position.noPos(), "i"))
    }

    def "Not normalizable group"() {
        expect:
        !visitor.isNormalizable(null)
        !visitor.isNormalizable(Exprs.block(new ArrayList<Tree>(0)))
        !visitor.isNormalizable(Exprs.pathToTree(Position.noPos(), Terms.typeName("Integer")))

        def ifExpr = Exprs.ifExpr(Position.noPos(), Terms.emptyTree(), Terms.emptyTree(), Terms.emptyTree())
        visitor.isNormalizable(ifExpr)
        ifExpr.addAttribute(new NormalizedTree())
        !visitor.isNormalizable(ifExpr)
    }

    def "VarName"() {
        def id = Terms.varName(Position.noPos(), "i")
        def b = id.toString()
        expect:
        visitor.visitVarName(id, null).toString() == b
    }

    def "apply 1"() {
        // Integer.valueOf(args.get(0))
        def zero = Exprs.intValue("0")
        def argsId = Exprs.pathToTree(Position.noPos(), Terms.varName(Position.noPos(), "args"))
        def argsGet = Exprs.apply(Position.noPos(), argsId, Terms.varName(Position.noPos(), "get"), zero)
        def integer = Exprs.pathToTree(Position.noPos(), Terms.typeName("Integer"))
        def integerValueOf = Exprs.apply(Position.noPos(), integer, Terms.varName(Position.noPos(), "valueOf"), argsGet)
        def before = integerValueOf.toString()
        def visited = integerValueOf.accept(visitor, Generator.defaultElementIdGenerator(true))
        def after = visited.toString()

        expect:
        before == "Integer.valueOf(args.get(0))"
        """
        {
          let _2 = {
            let _0 = 0;
            let _1 = args.get(_0);
            return _1;
          };
          let _3 = Integer.valueOf(_2);
          return _3;
        }
        """
        after == "{let _2 = {let _0 = 0;let _1 = args.get(_0);return _1;};let _3 = Integer.valueOf(_2);return _3;}"
    }

    def "apply 2"() {
        // (1 + 2).toString()
        def one = Exprs.intValue("1")
        def two = Exprs.intValue("2")
        def onePlusTwo = Exprs.apply(Position.noPos(), one, Terms.varName(Position.noPos(), "+"), two)
        def onePlusTwoToString = Exprs.apply(Position.noPos(), onePlusTwo, Terms.varName(Position.noPos(), "toString"), Terms.emptyTree())
        def before = onePlusTwoToString.toString()
        def after = onePlusTwoToString.accept(visitor, Generator.defaultElementIdGenerator(true)).toString()

        expect:
        before == "1.+(2).toString()"
        """
        {
          let _3 = {
            let _0 = 1;
            let _1 = 2;
            let _2 = _0.+(_1);
            return _2;
          };
          let _4 = _3.toString();
          return _4;
        }
        """
        after == "{let _3 = {let _0 = 1;let _1 = 2;let _2 = _0.+(_1);return _2;};let _4 = _3.toString();return _4;}"
    }

    def "emptyTuple"() {
        def emptyTuple = Exprs.tuple()
        def before = emptyTuple.toString()
        def after = emptyTuple.accept(visitor, Generator.defaultElementIdGenerator(true)).toString()

        expect:
        before == "()"
        after == "()"
    }

    def "tuple"() {
        List<Tree> t = new ArrayList<>()
        t.add(Exprs.intValue("1"))
        t.add(Exprs.intValue("2"))
        t.add(Exprs.intValue("3"))
        def tuple = Exprs.tuple(t)
        def before = tuple.toString()
        def after = tuple.accept(visitor, Generator.defaultElementIdGenerator(true)).toString()

        expect:
        before == "(1,2,3)"
        after == "{let _0 = 1;let _1 = 2;let _2 = 3;return (_0,_1,_2);}"
    }
}
