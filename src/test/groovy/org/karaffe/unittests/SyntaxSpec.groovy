package org.karaffe.unittests


import spock.lang.Specification
import spock.lang.Unroll

import static org.karaffe.util.Util.getParser

class SyntaxSpec extends Specification {


  def "empty source"() {
    setup:
    def parser = getParser("")
    def context = parser.sourceFile()

    expect:
    context != null
  }

  def "classDef"() {
    setup:
    def parse = getParser("class A")
    def context = parse.classDef()

    expect:
    context != null
    context.Identifier().getText() == "A"
  }

  def "classDef2"() {
    setup:
    def parse = getParser("class A {}")
    def context = parse.classDef()

    expect:
    context != null
    context.Identifier().getText() == "A"
  }

  def "entryPoint"() {
    setup:
    def parse = getParser("""class Main {
                  |  entrypoint {
                  |  }
                  |}""".stripMargin())
    def context = parse.classDef()

    expect:
    context != null
    context.Identifier().getText() == "Main"
    context.typeDefBody().statement(0).entryPointBlock().ENTRYPOINT() != null
  }

  def "nullExpr"() {
    def parse = getParser("print()")
    def context = parse.expr()

    expect:
    context != null
    context.function != null
    context.args == null
  }

  @Unroll
  def "stringLiteral #literal"() {
    def parse = getParser(literal)
    def context = parse.literal()

    expect:
    context != null
    if (context.StringLiteral() == null) {
      assert expectedText.isEmpty()
      return
    }

    context.StringLiteral().getText() == expectedText

    where:
    literal              || expectedText
    '""'                 || '""'
    '''"Hello"'''        || '"Hello"'
    '''"Hello World!"''' || '"Hello World!"'
  }

  @Unroll
  def "intLiteral #literal"() {
    def parse = getParser(literal)
    def context = parse.literal()

    expect:
    context != null
    if (context.IntegerLiteral() == null) {
      assert expectedText.isEmpty()
      return
    }

    context.IntegerLiteral().getText() == expectedText

    where:
    literal || expectedText
    "0"     || "0"
    "1"     || "1"
    "150"   || "150"
  }

  def "field"() {
    setup:
    def parse = getParser("""class Main {
                  |  def i Int
                  |}""".stripMargin())
    def context = parse.classDef()

    expect:
    context != null
    context.Identifier().getText() == "Main"
    context.typeDefBody().statement(0) != null
  }

  def "constructor"() {
    setup:
    def parse = getParser("""class Main {
                  |  init {
                  |  }
                  |}""".stripMargin())
    def context = parse.classDef()

    expect:
    context != null
    context.Identifier().getText() == "Main"
    context.typeDefBody().statement(0)
  }

  def "initialize"() {
    setup:
    def parse = getParser("""class Main {
                  |  def i Int
                  |  init {
                  |    this.i := 0
                  |  }
                  |}""".stripMargin())
    def context = parse.classDef()

    expect:
    context != null
    context.Identifier().getText() == "Main"
    context.typeDefBody().statement(0)
  }

  def "infixOp"() {
    setup:
    def parse = getParser("""class Main {
                  |  def i Int
                  |  init {
                  |    1 + 1
                  |    1 plus 1
                  |  }
                  |}""".stripMargin())
    def context = parse.classDef()

    expect:
    context != null
    context.Identifier().getText() == "Main"
    context.typeDefBody().statement(0)
  }

  def "invokeMethod"() {
    setup:
    def parse = getParser("""class Main {
                  |  entrypoint {
                  |    this.hoge()
                  |  }
                  |}""".stripMargin())
    def context = parse.classDef()

    expect:
    context != null
    context.Identifier().getText() == "Main"
    context.typeDefBody().statement(0)
  }

  def "simpleInvoke"() {
    setup:
    def parse = getParser("""class Main {
                  |  entrypoint {
                  |    doSomething()
                  |  }
                  |}""".stripMargin())
    def context = parse.classDef()

    expect:
    context != null
    context.Identifier().getText() == "Main"
    context.typeDefBody().statement(0)
  }

}
