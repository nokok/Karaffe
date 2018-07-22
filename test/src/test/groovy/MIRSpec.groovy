import org.karaffe.compiler.base.mir.Instruction
import org.karaffe.compiler.base.mir.Instructions
import org.karaffe.compiler.base.mir.block.BeginMethod
import spock.lang.Specification
import util.FrontendUtil

class MIRSpec extends Specification {


    def "simpleClass"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions("""class A {
  main {

  }
}
let a Int = 1 + 2
let b Int = 1 + 2 / 3
println(a + b)""")

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       <no-pos>] BeginClass #A
[        2:2~4:2] [public, static] BeginMethod #A#main(Array[String]):void
[       <no-pos>] [ParameterName] ValDef #A#main(Array[String]):void#args Array[String]
[       <no-pos>] Return
[       <no-pos>] EndMethod #A#main(Array[String]):void
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
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """|class Main {
                   |  main {
                   |  }
                   |}""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       <no-pos>] BeginClass #Main
[        2:2~3:2] [public, static] BeginMethod #Main#main(Array[String]):void
[       <no-pos>] [ParameterName] ValDef #Main#main(Array[String]):void#args Array[String]
[       <no-pos>] Return
[       <no-pos>] EndMethod #Main#main(Array[String]):void
[       <no-pos>] EndClass #Main
[       <no-pos>] EndBlock #"""
    }

    def "block"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """{}""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       <no-pos>] BeginBlock #0
[       <no-pos>] EndBlock #0
[       <no-pos>] EndBlock #"""
    }

    def "apply without args"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """doSomething()""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       1:0~1:12] [InvokingSet#0] Invoke doSomething
[       <no-pos>] EndBlock #"""
    }

    def "apply with arg"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """doSomething(123)""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[           1:12] [InvokingSet#0] Const INTEGER 123
[       1:0~1:15] [InvokingSet#0] Invoke doSomething
[       <no-pos>] EndBlock #"""
    }

    def "compareExpr"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions(
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
        def instructions = FrontendUtil.parseAndGenerateInstructions(
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
        def instructions = FrontendUtil.parseAndGenerateInstructions(
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
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """class Main {
let a Int = 0
}""".stripMargin())

        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       <no-pos>] BeginClass #Main
[       2:0~2:12] ValDef #Main#a Int
[           2:12] Const INTEGER 0
[            2:4] Store #Main#a
[       <no-pos>] EndClass #Main
[       <no-pos>] EndBlock #"""
    }

    def "simpleMethod"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions("""
class A {
  def doSomething() void = 1
}
""")
        expect:
        instructions.toString() == """[       <no-pos>] BeginBlock #
[       <no-pos>] BeginClass #A
[       3:2~3:27] BeginMethod #A#doSomething():void
[       <no-pos>] Return
[       <no-pos>] EndMethod #A#doSomething():void
[       <no-pos>] EndClass #A
[       <no-pos>] EndBlock #"""

        BeginMethod beginMethod = instructions.get(2)
        beginMethod.returnTypeName == "void"
        beginMethod.parameters == ""
        beginMethod.methodName == "doSomething"
    }

}
