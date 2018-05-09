import org.karaffe.compiler.base.util.config.Options
import org.karaffe.compiler.frontend.karaffe.tasks.options.CheckLogLevelTask
import org.karaffe.compiler.frontend.karaffe.tasks.util.TaskResult
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
        task.run(option) == result

        where:

        isTraceLog | isDebugLog | isInfoLog || result
        (true)     | (true)     | (true)    || TaskResult.NON_RECOVERABLE
        (true)     | (true)     | (false)   || TaskResult.NON_RECOVERABLE
        (true)     | (false)    | (true)    || TaskResult.NON_RECOVERABLE
        (true)     | (false)    | (false)   || TaskResult.SUCCESS
        (false)    | (true)     | (true)    || TaskResult.NON_RECOVERABLE
        (false)    | (true)     | (false)   || TaskResult.SUCCESS
        (false)    | (false)    | (true)    || TaskResult.SUCCESS
        (false)    | (false)    | (false)   || TaskResult.SUCCESS

    }
}
