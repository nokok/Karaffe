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
                return TaskResult.SUCCESSFUL
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
                return TaskResult.SUCCESSFUL
            }
        })

        RunnerResult result = taskRunner.runAll()

        then:
        result == RunnerResult.SUCCESS_ALL
        count == 1
        executed
    }

    def "disabled"() {
        setup:
        def cc = new CompilerContextImpl()
        def taskRunner = TaskRunner.newDefaultTaskRunner(cc)
        def ab1 = new PlainTask("ab1") {
            @Override
            TaskResult run(CompilerContext context) {
                super.run(context)
                return TaskResult.SUCCESSFUL
            }
        }
        def ac1 = new PlainTask("ac1") {
            @Override
            TaskResult run(CompilerContext context) {
                super.run(context)
                return TaskResult.SUCCESSFUL
            }
        }
        taskRunner.standBy(ab1)
        taskRunner.standBy(ac1)
        taskRunner.disable("ac.*")
        def result = taskRunner.runAll().toTaskResult()

        expect:
        result == TaskResult.SUCCESSFUL
        cc.state == "ab1"
    }
}