import org.karaffe.compiler.base.context.CommandLineOptions
import org.kohsuke.args4j.CmdLineException
import spock.lang.Specification

class CommandLineOptionsSpec extends Specification {

    def "isEmptyArgsで渡されたオプションが空かどうかが判別できる"() {
        expect:
        new CommandLineOptions(option as String[]).isEmptyArgs() == expect

        where:
        option     || expect
        []         || (true)
        ["--help"] || (false)
    }

    def "サポートしているオプションはパースできる"() {
        setup:
        def op = new CommandLineOptions(["--help"] as String[])

        when:
        op.parseArgs()

        then:
        notThrown(CmdLineException)
    }

    def "サポートしていないオプションが渡された場合はCmdLineExceptionがスローされる"() {
        setup:
        def op = new CommandLineOptions(["--foo"] as String[])

        when:
        op.parseArgs()

        then:
        thrown(CmdLineException)
    }
}
