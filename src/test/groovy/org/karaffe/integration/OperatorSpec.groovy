package org.karaffe.integration

import org.karaffe.compiler.KaraffeCompiler
import org.karaffe.compiler.util.CompilerContext
import org.karaffe.compiler.util.KaraffeSource
import spock.lang.Specification
import spock.lang.Unroll

class OperatorSpec extends Specification {

  @Unroll
  def "operator applicability #source"() {
    setup:
    def context = new CompilerContext()
    context.parseRawArgs(["--dry-run"] as String[])
    context.add(KaraffeSource.fromString("class Main { entrypoint { print($source)}}"))
    def compiler = new KaraffeCompiler(context)
    compiler.run()

    expect:
    context.getOutputText() == outputText

    where:
    source        || outputText
    "1 + 1"       || ""
    "1 - 1"       || ""
//        '1 + "Hoge"'  || """[ERROR] 'karaffe.core.Int'+'karaffe.core.String' is not applicable at 1:34:<unknown>
//                           |class Main { entrypoint { print(1 + "Hoge")}}
//                           |                                  ^""".stripMargin()
//        '"Hoge" + 1'  || """[ERROR] 'karaffe.core.String'+'karaffe.core.Int' is not applicable at 1:39:<unknown>
//                           |class Main { entrypoint { print("Hoge" + 1)}}
//                           |                                       ^""".stripMargin()
    '"Ho" + "ge"' || ""
  }
}
