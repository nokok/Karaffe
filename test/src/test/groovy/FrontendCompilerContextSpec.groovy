import spock.lang.Specification
import util.FrontendUtil

class FrontendCompilerContextSpec extends Specification {

    def "SimpleClass"() {
        setup:
        def json = FrontendUtil.parseAndGenerateCompilerContextJSON("""
class A {
  main {
    let i Int = 0
  }
}
""")
        expect:
        json instanceof Map
        def compilationUnit = json.compilationUnit
        compilationUnit != null
        compilationUnit.mods == []
        compilationUnit.kind == "COMPILE_UNIT"
        def body = compilationUnit.body
        body.size() == 1
        def mainClassDef = body.get(0)
        mainClassDef.mods == []
        mainClassDef.kind == "DEF"
        def mainMethod = mainClassDef
                .body.get(0) // Template
                .body.get(0) // mainMethod
        mainMethod.kind == "DEF"
        mainMethod.name == "main"
    }
}
