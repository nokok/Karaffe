import org.karaffe.compiler.base.mir.instructions.block.BeginMethod

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
        instructions.toString() == """"""
    }

    def "mainMethod"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """|class Main {
                   |  main {
                   |  }
                   |}""".stripMargin())

        expect:
        instructions.toString() == """"""
    }

    def "block"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """{}""".stripMargin())

        expect:
        instructions.toString() == """"""
    }

    def "apply without args"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """doSomething()""".stripMargin())

        expect:
        instructions.toString() == """"""
    }

    def "apply with arg"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """doSomething(123)""".stripMargin())

        expect:
        instructions.toString() == """"""
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
        instructions.toString() == """"""
    }

    def "simpleIfExpr"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """if(a < b) { 1 } else { 2 }""".stripMargin())

        expect:
        instructions.toString() == """"""
    }

    def "simpleWhileExpr"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """while(true) { 1 } 2""".stripMargin())

        expect:
        instructions.toString() == """"""
    }

    def "fieldDef"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions(
                """class Main {
let a Int = 0
}""".stripMargin())

        expect:
        instructions.toString() == """"""
    }

    def "simpleMethod"() {
        setup:
        def instructions = FrontendUtil.parseAndGenerateInstructions("""
class A {
  def doSomething() void = 1
}
""")
        expect:
        instructions.toString() == """"""

        BeginMethod beginMethod = instructions.get(2)
        beginMethod.returnTypeName == "void"
        beginMethod.parameters == ""
        beginMethod.methodName == "doSomething"
    }


}
