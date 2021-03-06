package org.karaffe.unittests

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.karaffe.compiler.frontend.karaffe.antlr.KaraffeLexer
import org.karaffe.util.Util
import spock.lang.Specification
import spock.lang.Unroll

class LexerSpec extends Specification {

  @Unroll
  def "token Type #source"() {
    setup:
    def lexer = new KaraffeLexer(CharStreams.fromString(source))
    lexer.removeErrorListeners()
    lexer.addErrorListener(Util.DEFULT_ERROR_LISTENER)
    def stream = new CommonTokenStream(lexer)
    stream.consume()
    def token = stream.get(0)
    def tokenText = token.getText()

    expect:
    tokenText == source
    token.getType() == tokenType

    where:
    source       || tokenType
    "A"          || KaraffeLexer.Identifier
    "Hello"      || KaraffeLexer.Identifier
    "a"          || KaraffeLexer.Identifier
    "z"          || KaraffeLexer.Identifier
    "HB"         || KaraffeLexer.Identifier
    "H1"         || KaraffeLexer.Identifier
    "+"          || KaraffeLexer.Identifier
    "-"          || KaraffeLexer.Identifier
    "*"          || KaraffeLexer.Identifier
    "/"          || KaraffeLexer.Identifier
    "!"          || KaraffeLexer.Identifier
    "%"          || KaraffeLexer.Identifier
    "&"          || KaraffeLexer.Identifier
    ">"          || KaraffeLexer.Identifier
    "<"          || KaraffeLexer.Identifier
    "^"          || KaraffeLexer.Identifier
    "~"          || KaraffeLexer.Identifier
    ">>"         || KaraffeLexer.Identifier
    "1"          || KaraffeLexer.IntegerLiteral
    '"Hello"'    || KaraffeLexer.StringLiteral
    "{"          || KaraffeLexer.LBRACE
    "}"          || KaraffeLexer.RBRACE
    "entrypoint" || KaraffeLexer.ENTRYPOINT
    "class"      || KaraffeLexer.CLASS
    "CLASS"      || KaraffeLexer.Identifier
  }

  @Unroll
  def "keyword is not Identifier #source"() {
    setup:
    def lexer = new KaraffeLexer(CharStreams.fromString(source))
    lexer.removeErrorListeners()
    lexer.addErrorListener(Util.DEFULT_ERROR_LISTENER)
    def stream = new CommonTokenStream(lexer)
    stream.consume()
    def token = stream.get(0)
    def tokenText = token.getText()

    expect:
    tokenText == source
    token.getType() != KaraffeLexer.Identifier

    where:
    source << [
      "entrypoint",
      "class",
      "def",
      "init",
      "this"
    ]
  }

  @Unroll
  def "invalid token Type #source"() {
    setup:
    def lexer = new KaraffeLexer(CharStreams.fromString(source))
    lexer.removeErrorListeners()
    lexer.addErrorListener(Util.DEFULT_ERROR_LISTENER)
    def stream = new CommonTokenStream(lexer)
    String errorMessage = ""
    try {
      stream.consume()
      assert false: "assertion failed ${source}"
    } catch (RuntimeException e) {
      errorMessage = e.getMessage()
    }

    expect:
    errorMessage == expectedErrorMessage

    where:
    source      || expectedErrorMessage
    "_"         || "Syntax Error at 1 : 0 , token recognition error at: '_'"
    "HOGE_FUGA" || "Syntax Error at 1 : 4 , token recognition error at: '_'"
  }

  @Unroll
  def "test StringLiteral #source"() {
    setup:
    def lexer = new KaraffeLexer(CharStreams.fromString(source))
    lexer.removeErrorListeners()
    lexer.addErrorListener(Util.DEFULT_ERROR_LISTENER)
    def stream = new CommonTokenStream(lexer)
    stream.consume()
    def token = stream.get(0)
    def tokenText = token.getText()

    expect:
    tokenText == source
    token.getType() == KaraffeLexer.StringLiteral

    where:
    source << [
      '""',
      '"1234"',
      '"Hello"'
    ]
  }
}
