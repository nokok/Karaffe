package org.karaffe.unittests

import org.karaffe.compiler.args.ArgsParser
import org.karaffe.compiler.args.Flag
import org.karaffe.compiler.args.ParameterName
import spock.lang.Specification

class ArgSpec extends Specification {
    def "flagConfiguration"() {
        setup:
        def flags = Flag.values()

        expect:
        for (flag in flags) {
            flag.fullName.ifPresent({ fullName ->
                assert fullName.startsWith("--")
            })
            flag.shortName.ifPresent({ shortName ->
                assert shortName.startsWith("-")
                assert shortName.charAt(1) != '-'.toCharacter()
            })
        }
    }

    def "parameterConfiguration"() {
        setup:
        def parameters = ParameterName.values()

        expect:
        for (parameter in parameters) {
            parameter.fullName.ifPresent({ fullName ->
                assert fullName.startsWith("--")
            })
            parameter.shortName.ifPresent({ shortName ->
                assert shortName.startsWith("-")
                assert shortName.charAt(1) != '-'.toCharacter()
            })
        }
    }

    def "testParse"() {
        setup:
        def parser = new ArgsParser()

        expect:
        parser.parse(input as String[]).toString() == output

        where:
        input                             || output
        []                                || "Optional[Options{flags=[], parameterValues={}, sources=[]}]"
        ["--help"]                        || "Optional[Options{flags=[HELP], parameterValues={}, sources=[]}]"
        ["--version"]                     || "Optional[Options{flags=[VERSION], parameterValues={}, sources=[]}]"
        ["--emit", "ast"]                 || "Optional[Options{flags=[], parameterValues={EMIT=ast}, sources=[]}]"
        ["--emit", "ast", "--emit", "ir"] || "Optional.empty"
        ["--emit"]                        || "Optional.empty"
        ["Main.krf"]                      || "Optional.empty"
        ["-hogehoge"]                     || "Optional.empty"
    }

}
