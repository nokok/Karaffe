import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.mir.Instructions
import org.karaffe.compiler.base.task.Task
import org.karaffe.compiler.base.task.TaskResult
import org.karaffe.compiler.base.util.SourceFile
import org.karaffe.compiler.frontend.karaffe.FrontendType
import org.karaffe.compiler.frontend.karaffe.KaraffeCompilerFrontend
import spock.lang.Specification

class MIRSpec extends Specification {

    private Instructions parseAndGenerateInstructions(String source) {
        Task frontend = KaraffeCompilerFrontend.getFrontend(FrontendType.KARAFFE)
        CompilerContext context = new CompilerContextImpl()
        context.addSourceFile(SourceFile.fromLiteral(source))
        def result = frontend.run(context)
        if (result != TaskResult.SUCCESS) {
            throw new RuntimeException()
        }
        return context.getInstructions()
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
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       <no-pos>] BeginClass #A
[        2:2~4:2] [public, static] BeginMethod #A#main(args Array[String]):void
[       <no-pos>] [ParameterName] ValDef #A#main(args Array[String]):void#args Array[String]
[       <no-pos>] EndMethod #A#main(args Array[String]):void
[       <no-pos>] EndClass #A
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
[       <no-pos>] EndBlock #"""
    }

    def "mainMethod"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """|class Main {
                   |  main {
                   |  }
                   |}""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       <no-pos>] BeginClass #Main
[        2:2~3:2] [public, static] BeginMethod #Main#main(args Array[String]):void
[       <no-pos>] [ParameterName] ValDef #Main#main(args Array[String]):void#args Array[String]
[       <no-pos>] EndMethod #Main#main(args Array[String]):void
[       <no-pos>] EndClass #Main
[       <no-pos>] EndBlock #s"""
    }

    def "block"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """{}""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       <no-pos>] BeginBlock #0
[       <no-pos>] EndBlock #0
[       <no-pos>] EndBlock #"""
    }

    def "apply without args"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """doSomething()""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       1:0~1:12] [InvokingSet#0] Invoke doSomething
[       <no-pos>] EndBlock #"""
    }

    def "apply with arg"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """doSomething(123)""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[           1:12] [InvokingSet#0] Const INTEGER 123
[       1:0~1:15] [InvokingSet#0] Invoke doSomething
[       <no-pos>] EndBlock #"""
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
        instructions.toString() == """[       <no-pos>] BeginBlock #
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
[       <no-pos>] EndBlock #"""
    }

    def "simpleIfExpr"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """if(a < b) { 1 } else { 2 }""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[            1:3] [InvokingSet#1] Load #a
[            1:7] [InvokingSet#1] Load #b
[        1:3~1:7] [InvokingSet#1] Invoke lessThan
[       <no-pos>] IfJumpFalse #else0
[       <no-pos>] BeginBlock #then0
[           1:12] Const INTEGER 1
[       <no-pos>] Jump #end0
[       <no-pos>] EndBlock #then0
[       <no-pos>] BeginBlock #else0
[       <no-pos>] JumpTarget #else0
[           1:23] Const INTEGER 2
[       <no-pos>] EndBlock #else0
[       <no-pos>] JumpTarget #end0
[       <no-pos>] EndBlock #"""
    }

    def "simpleWhileExpr"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """while(true) { 1 } 2""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       <no-pos>] BeginBlock #whileBlock0
[       <no-pos>] JumpTarget #beginWhile0
[            1:6] Load #true
[       <no-pos>] IfJumpFalse #endWhile0
[           1:14] Const INTEGER 1
[       <no-pos>] Jump #beginWhile0
[       <no-pos>] JumpTarget #endWhile0
[       <no-pos>] EndBlock #whileBlock0
[           1:18] Const INTEGER 2
[       <no-pos>] EndBlock #"""
    }

    def "fieldDef"() {
        setup:
        def instructions = parseAndGenerateInstructions(
                """class Main {
let a Int = 0
}""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       <no-pos>] BeginClass #Main
[       2:0~2:12] ValDef #Main#a Int
[           2:12] Const INTEGER 0
[            2:4] Store #Main#a
[       <no-pos>] End #Main
[       <no-pos>] End #"""
    }

}
