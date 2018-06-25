import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.base.task.TaskResult
import org.karaffe.compiler.base.util.config.Options
import org.karaffe.compiler.launcher.tasks.TaskNameCheckTask
import spock.lang.Specification
import spock.lang.Unroll

class TaskSpec extends Specification {

    @Unroll
    def "TaskNameCheckTask #taskName"() {
        setup:
        def context = Stub(CompilerContext)
        def options = Spy(Options)
        context.getCmdLineOptions() >> options
        options.stopTaskName = taskName

        def task = new TaskNameCheckTask()
        def r = task.run(context)

        expect:
        options.stopTaskName == taskName
        context.getCmdLineOptions().stopTaskName == taskName
        r == result

        where:
        taskName || result
        null     || TaskResult.SUCCESS
        ""       || TaskResult.SUCCESS
        "lexing" || TaskResult.SUCCESS
        "lexer"  || TaskResult.FAILED

    }
}