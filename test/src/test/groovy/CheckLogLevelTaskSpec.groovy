import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.task.TaskResult
import org.karaffe.compiler.base.util.config.Options
import org.karaffe.compiler.frontend.karaffe.tasks.options.CheckLogLevelTask
import spock.lang.Specification
import spock.lang.Unroll

class CheckLogLevelTaskSpec extends Specification {

    @Unroll
    def "ログレベルの指定がtrace: #isTraceLog , debug: #isDebugLog, info:#isInfoLog のときは #result を返す"() {
        setup:
        def task = new CheckLogLevelTask()
        def option = new Options()
        option.isTraceLog = isTraceLog
        option.isDebugLog = isDebugLog
        option.isInfoLog = isInfoLog

        expect:
        task.run(option, new CompilerContextImpl()) == result

        where:

        isTraceLog | isDebugLog | isInfoLog || result
        (true)     | (true)     | (true)    || TaskResult.FAILED
        (true)     | (true)     | (false)   || TaskResult.FAILED
        (true)     | (false)    | (true)    || TaskResult.FAILED
        (true)     | (false)    | (false)   || TaskResult.SUCCESSFUL
        (false)    | (true)     | (true)    || TaskResult.FAILED
        (false)    | (true)     | (false)   || TaskResult.SUCCESSFUL
        (false)    | (false)    | (true)    || TaskResult.SUCCESSFUL
        (false)    | (false)    | (false)   || TaskResult.SUCCESSFUL

    }
}
