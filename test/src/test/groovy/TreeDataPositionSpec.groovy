import org.karaffe.compiler.base.pos.Position
import org.karaffe.compiler.base.tree.TreeKind
import org.karaffe.compiler.base.tree.def.DefKind
import org.karaffe.compiler.base.tree.def.Defs
import org.karaffe.compiler.base.tree.expr.Binding
import org.karaffe.compiler.base.tree.expr.Exprs
import org.karaffe.compiler.base.tree.expr.Tuple
import org.karaffe.compiler.base.tree.modifier.Modifiers
import org.karaffe.compiler.base.tree.term.Terms
import spock.lang.Specification

class TreeDataPositionSpec extends Specification {

    def "packageDef"() {
        setup:
        def d = Defs.packageDef("java.lang")
        def name = d.getName()

        expect:
        d.getKind() == TreeKind.DEF
        d.getDefKind() == DefKind.PACKAGE
        d.getParent() == null
        d.getChildren().isEmpty()
        d.getModifiers().isEmpty()
        d.getPos() == Position.noPos()
        d.toString() == "package java.lang"
        name.asFullName() == "java.lang"
        name.asSimpleName() == "lang"
        name.delimiterRegex() == "\\."
        name.toString() == "java.lang"
    }

    def "importDef"() {
        setup:
        def d = Defs.importDef("java.lang.Integer")
        def name = d.getName()

        expect:
        d.getKind() == TreeKind.DEF
        d.getDefKind() == DefKind.SIMPLE_IMPORT
        d.getParent() == null
        d.getChildren().isEmpty()
        d.getModifiers().isEmpty()
        d.getPos() == Position.noPos()
        d.toString() == "import java.lang.Integer"
        name.asFullName() == "java.lang.Integer"
        name.asSimpleName() == "Integer"
        name.delimiterRegex() == "\\."
        name.toString() == "java.lang.Integer"
    }

    def "onDemandImportDef"() {
        setup:
        def d = Defs.onDemandImportDef("java.lang")
        def name = d.getName()

        expect:
        d.getKind() == TreeKind.DEF
        d.getDefKind() == DefKind.ONDEMAND_IMPORT
        d.getParent() == null
        d.getChildren().isEmpty()
        d.getModifiers().isEmpty()
        d.getPos() == Position.noPos()
        d.toString() == "import java.lang.*"
        name.asFullName() == "java.lang"
        name.asSimpleName() == "lang"
        name.delimiterRegex() == "\\."
        name.toString() == "java.lang"
    }

    def "classDef"() {
        setup:
        def d = Defs.classDef("Main")
        def name = d.getName()

        expect:
        d.getKind() == TreeKind.DEF
        d.getDefKind() == DefKind.CLASS
        d.getParent() == null
        d.getChildren().isEmpty()
        d.getModifiers().isEmpty()
        d.getPos() == Position.noPos()
        d.getTypeName() == Terms.emptyName()
        d.toString() == "class Main"
        name.asFullName() == "Main"
        name.asSimpleName() == "Main"
        name.delimiterRegex() == "\\."
        name.toString() == "Main"
    }

    def "methodDef"() {
        setup:
        def d = Defs.methodDef(null, "doSomething", Terms.primitiveVoid(), Exprs.tuple())
        def name = d.getName()

        expect:
        d.getKind() == TreeKind.DEF
        d.getDefKind() == DefKind.METHOD
        d.getParent() == null
        d.getChildren().size() == 1
        d.getChild(0) == Exprs.tuple()
        d.getModifiers().isEmpty()
        d.getPos() == Position.noPos()
        d.getTypeName() == Terms.primitiveVoid()
        d.toString() == "void doSomething()"
        name.asFullName() == "doSomething"
        name.asSimpleName() == "doSomething"
        name.delimiterRegex() == "\$^"
        name.toString() == "doSomething"
    }

    def "mainMethodDef"() {
        setup:
        def d = Defs.mainMethodDef()
        def name = d.getName()

        expect:
        d.getKind() == TreeKind.DEF
        d.getDefKind() == DefKind.METHOD
        d.getParent() == null
        d.getChildren().size() == 1
        def b = new Binding()
        b.setName(Terms.varName("args"))
        b.setTypeName(Terms.arrayTypeName(Terms.typeName("String")))
        def t = new Tuple()
        t.addChild(b)
        d.getChild(0) == t
        d.getModifiers() == [Modifiers.modPublic(null), Modifiers.modStatic()]
        d.getPos() == Position.noPos()
        d.getTypeName() == Terms.primitiveVoid()
        d.toString() == "public static void main(args Array[String])"
        name.asFullName() == "main"
        name.asSimpleName() == "main"
        name.delimiterRegex() == "\$^"
        name.toString() == "main"
    }

    def "letDef"() {
        setup:
        def d = Defs.letDef(Terms.varName("l"), Terms.typeName("Int"), Terms.emptyTree())
        def name = d.getName()

        expect:
        d.getKind() == TreeKind.DEF
        d.getDefKind() == DefKind.LET
        d.getParent() == null
        d.getChildren().size() == 1
        d.getChild(0) == Terms.emptyTree()
        d.getModifiers().isEmpty()
        d.getTypeName() == Terms.typeName("Int")
        d.getPos() == Position.noPos()
        d.toString() == "let l Int"
        name.asFullName() == "l"
        name.asSimpleName() == "l"
        name.delimiterRegex() == "\$^"
        name.toString() == "l"
    }

    def "assignmentdef"() {
        setup:
        def d = Defs.assignment(Terms.varName("l"), Exprs.intValue("1"))
        def name = d.getName()

        expect:
        d.getKind() == TreeKind.DEF
        d.getDefKind() == DefKind.ASSIGNMENT
        d.getParent() == null
        d.getChildren().size() == 1
        d.getChild(0) == Exprs.intValue("1")
        d.getModifiers().isEmpty()
        d.getTypeName() == Terms.emptyName()
        d.getPos() == Position.noPos()
        d.toString() == "l = 1"
        name.asFullName() == "l"
        name.asSimpleName() == "l"
        name.delimiterRegex() == "\$^"
        name.toString() == "l"
    }
}
