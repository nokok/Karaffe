import org.karaffe.compiler.base.CompilerContext
import org.karaffe.compiler.base.CompilerContextImpl
import org.karaffe.compiler.base.task.*
import spock.lang.Specification

class DefaultTaskRunnerSpec extends Specification {
    abstract class AnonymousTask extends AbstractTask implements ReadOnlyTask {
        @Override
        String name() {
            return ""
        }

        @Override
        String description() {
            return ""
        }

        @Override
        boolean changed() {
            return super.changed()
        }
    }

    def "The task specified in standBy is not executed until runAll is executed"() {
        setup:
        def cc = new CompilerContextImpl()
        def taskRunner = TaskRunner.newDefaultTaskRunner(cc)

        when:
        taskRunner.standBy(new AnonymousTask() {
            @Override
            TaskResult run(CompilerContext context) {
                throw new IllegalStateException()
            }
        })

        then:
        notThrown(IllegalStateException.class)
    }

    def "runAllした時点でRunnableでないタスクは繰り返し実行をリトライする"() {
        setup:
        def cc = new CompilerContextImpl()
        def taskRunner = TaskRunner.newDefaultTaskRunner(cc)
        def count = 0
        def executed = false

        when:
        taskRunner.standBy(new AnonymousTask() {
            @Override
            TaskResult run(CompilerContext context) {
                executed = true
                return TaskResult.SUCCESS
            }

            @Override
            boolean isRunnable(CompilerContext context) {
                return count == 1
            }
        })
        taskRunner.standBy(new AnonymousTask() {
            @Override
            TaskResult run(CompilerContext context) {
                count++
                return TaskResult.SUCCESS
            }
        })

        RunnerResult result = taskRunner.runAll()

        then:
        result == RunnerResult.SUCCESS_ALL
        count == 1
        executed
    }

}