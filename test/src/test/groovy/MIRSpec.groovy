import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.pos.Position
import org.karaffe.compiler.base.util.SourceFile
import org.karaffe.compiler.frontend.karaffe.FrontendType
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend
import org.karaffe.compiler.mir.InstructionType
import org.karaffe.compiler.mir.Instructions
import org.karaffe.compiler.mir.block.Begin
import org.karaffe.compiler.mir.block.End
import org.karaffe.compiler.mir.constant.Const
import org.karaffe.compiler.mir.io.Store
import org.karaffe.compiler.mir.util.InstructionList
import org.karaffe.compiler.mir.util.Label
import org.karaffe.compiler.mir.variable.ValDef
import spock.lang.Specification

class MIRSpec extends Specification {

    private Instructions parseAndGenerateInstructions(String source) {
        KaraffeCompilerFrontend frontend = KaraffeCompilerFrontend.getFrontend(FrontendType.KARAFFE)
        CompilerContext context = new CompilerContextImpl()
        context.addSourceFile(SourceFile.fromLiteral(source))
        def mayBeInstructions = frontend.exec(context)
        return mayBeInstructions.get()
    }

    def "Minimum Program"() {
        setup:
        def expected = new InstructionList()
        def rootLabel = Label.createRootLabel()
        expected.add(new Begin(InstructionType.PROGRAM, rootLabel))
        expected.add(new End(rootLabel))
        def actual = parseAndGenerateInstructions("")

        expect:
        actual == expected
        actual.toString() == """[       <no-pos>] Begin PROGRAM #
[       <no-pos>] End #"""

    }

    def "simpleLet1"() {
        setup:
        def expected = new InstructionList()
        def rootLabel = Label.createRootLabel()
        def beginProgram = new Begin(InstructionType.PROGRAM, rootLabel)
        def valName = new Label(rootLabel, "a")
        def valDef = new ValDef(valName, "Int")
        def constant0 = new Const("0", "INTEGER")
        def store0 = new Store(valName)
        def endProgram = new End(rootLabel)
        expected.add(beginProgram)
        expected.add(valDef)
        expected.add(constant0)
        expected.add(store0)
        expected.add(endProgram)
        def actual = parseAndGenerateInstructions("let a Int = 0")

        expect:
        actual == expected
        ((Begin) actual.get(0)).getLabel() == new Label("#")
        ValDef actualValDef = actual.get(1)
        actualValDef.getPosition() == Position.ofRange(Position.of("<unknown>", 1, 0), Position.of("<unknown>", 1, 12))
        actualValDef.getValName() == new Label("#a")
        actualValDef.getTypeName() == "Int"
        Const actualConst0 = actual.get(2)
        actualConst0.getPosition() == Position.of("<unknown>", 1, 12)
    }

    def "simpleClass"() {
        setup:
        def instructions = parseAndGenerateInstructions("""class A {
  main {

  }
}
let a Int = 1 + 2
let b Int = 1 + 2 / 3
println(a + b)""")

        expect:
        instructions.toString() == """[       <no-pos>] Begin PROGRAM #
[       <no-pos>] Begin CLASS #A
[       <no-pos>] Begin METHOD #A#main(args Array[String]):void
[       <no-pos>] [ParameterName] ValDef #A#main(args Array[String]):void#args Array[String]
[       <no-pos>] End #A#main(args Array[String]):void
[       <no-pos>] End #A
[       6:0~6:16] ValDef #a Int
[           6:12] [InvokingSet#0] Const INTEGER 1
[           6:16] [InvokingSet#0] Const INTEGER 2
[      6:12~6:16] [InvokingSet#0] Invoke plus
[            6:4] Store #a
[       7:0~7:20] ValDef #b Int
[           7:12] [InvokingSet#2] Const INTEGER 1
[           7:16] [InvokingSet#1, InvokingSet#2] Const INTEGER 2
[           7:20] [InvokingSet#1, InvokingSet#2] Const INTEGER 3
[      7:16~7:20] [InvokingSet#1, InvokingSet#2] Invoke div
[      7:12~7:20] [InvokingSet#2] Invoke plus
[            7:4] Store #b
[            8:8] [InvokingSet#3, InvokingSet#4] Load #a
[           8:12] [InvokingSet#3, InvokingSet#4] Load #b
[       8:8~8:12] [InvokingSet#3, InvokingSet#4] Invoke plus
[       8:0~8:13] [InvokingSet#4] Invoke println
[       <no-pos>] End #"""
    }

    def "mainMethod"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """|class Main {
                   |  main {
                   |  }
                   |}""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] Begin PROGRAM #
[       <no-pos>] Begin CLASS #Main
[       <no-pos>] Begin METHOD #Main#main(args Array[String]):void
[       <no-pos>] [ParameterName] ValDef #Main#main(args Array[String]):void#args Array[String]
[       <no-pos>] End #Main#main(args Array[String]):void
[       <no-pos>] End #Main
[       <no-pos>] End #"""
    }

    def "block"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """{}""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] Begin PROGRAM #
[       <no-pos>] Begin BLOCK #0
[       <no-pos>] End #0
[       <no-pos>] End #"""
    }

    def "apply without args"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """doSomething()""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] Begin PROGRAM #
[       1:0~1:12] [InvokingSet#0] Invoke doSomething
[       <no-pos>] End #"""
    }

    def "apply with arg"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """doSomething(123)""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] Begin PROGRAM #
[           1:12] [InvokingSet#0] Const INTEGER 123
[       1:0~1:15] [InvokingSet#0] Invoke doSomething
[       <no-pos>] End #"""
    }

    def "compareExpr"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """a < b
a <= b
a >= b
a > b
a == b""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] Begin PROGRAM #
[            1:0] [InvokingSet#0] Load #a
[            1:4] [InvokingSet#0] Load #b
[        1:0~1:4] [InvokingSet#0] Invoke lessThan
[            2:0] [InvokingSet#1] Load #a
[            2:5] [InvokingSet#1] Load #b
[        2:0~2:5] [InvokingSet#1] Invoke lessThanEquals
[            3:0] [InvokingSet#2] Load #a
[            3:5] [InvokingSet#2] Load #b
[        3:0~3:5] [InvokingSet#2] Invoke greaterThanEquals
[            4:0] [InvokingSet#3] Load #a
[            4:4] [InvokingSet#3] Load #b
[        4:0~4:4] [InvokingSet#3] Invoke greaterThan
[            5:0] [InvokingSet#4] Load #a
[            5:5] [InvokingSet#4] Load #b
[        5:0~5:5] [InvokingSet#4] Invoke equalsTo
[       <no-pos>] End #"""
    }

    def "simpleIfExpr"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """if(a < b) { 1 } else { 2 }""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] Begin PROGRAM #
[            1:3] [InvokingSet#1] Load #a
[            1:7] [InvokingSet#1] Load #b
[        1:3~1:7] [InvokingSet#1] Invoke lessThan
[       <no-pos>] IfJumpFalse #else0
[       <no-pos>] Begin BLOCK #then0
[           1:12] Const INTEGER 1
[       <no-pos>] Jump #end0
[       <no-pos>] End #then0
[       <no-pos>] Begin BLOCK #else0
[       <no-pos>] JumpTarget #else0
[           1:23] Const INTEGER 2
[       <no-pos>] End #else0
[       <no-pos>] JumpTarget #end0
[       <no-pos>] End #"""
    }

    def "simpleWhileExpr"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """while(true) { 1 } 2""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] Begin PROGRAM #
[       <no-pos>] Begin BLOCK #whileBlock0
[       <no-pos>] JumpTarget #beginWhile0
[            1:6] Load #true
[       <no-pos>] IfJumpFalse #endWhile0
[           1:14] Const INTEGER 1
[       <no-pos>] Jump #beginWhile0
[       <no-pos>] JumpTarget #endWhile0
[       <no-pos>] End #whileBlock0
[           1:18] Const INTEGER 2
[       <no-pos>] End #"""
    }

}
