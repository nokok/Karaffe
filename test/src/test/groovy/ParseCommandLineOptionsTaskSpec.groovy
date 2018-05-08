import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.frontend.karaffe.tasks.ParseCommandLineOptionsTask
import spock.lang.Specification
import spock.lang.Unroll

class ParseCommandLineOptionsTaskSpec extends Specification {

    def "ログレベルのオプションが矛盾する場合に不正なオプションとして検出できる"() {
        setup:
        def task = new ParseCommandLineOptionsTask()
        def context = new CompilerContext()

        expect:
        context.setArgs((String[]) args)
        task.run(context)
        context.hasInvalidArg() == invalidArg

        where:
        args                   || invalidArg
        ["--debug"]            || (false)
        ["--debug", "--trace"] || (true)
        ["--debug", "--info"]  || (true)
        ["--info", "--debug"]  || (true)
        ["--info", "--trace"]  || (true)
        ["--trace", "--info"]  || (true)
        ["--trace", "--debug"] || (true)
    }

    @Unroll
    def "有効なオプションが一つも存在しない場合は不正なオプションとして検出できる #args"() {
        setup:
        def task = new ParseCommandLineOptionsTask()
        def context = new CompilerContext()

        expect:
        context.setArgs((String[]) args)
        task.run(context)
        context.hasInvalidArg() == invalidArg

        where:
        args                         || invalidArg
        []                           || (true)
        ["-foo"]                     || (true)
        ["-val"]                     || (true)
        ["--debug"]                  || (false)
        ["--debug", "--show-phases"] || (false)
    }

    @Unroll
    def "読み込むことのできないソースファイルがある場合は不正なオプションとして検出できる"() {
        setup:
        def task = new ParseCommandLineOptionsTask()
        def context = new CompilerContext()

        expect:
        context.setArgs((String[]) args)
        task.run(context)
        context.hasInvalidArg() == invalidArg

        where:
        args                                               || invalidArg
        ["foo.krf"]                                        || (true)
        ["tests/test_resources/neg/InvalidExpr1.krf"] || (false)
    }
}