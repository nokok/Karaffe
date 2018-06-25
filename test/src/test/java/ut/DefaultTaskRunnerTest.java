package ut;

import org.junit.Test;
import org.karaffe.compiler.base.CompilerContextImpl;
import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.frontend.karaffe.tasks.AbstractReadOnlyTask;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class DefaultTaskRunnerTest {
    abstract class AnonymousTask extends AbstractReadOnlyTask {
        @Override
        public String name() {
            return "";
        }

        @Override
        public String description() {
            return "";
        }

    }

    @Test
    public void runAllした結果新たに挿入されたタスクはすべてのタスクが正常終了し後に実行される() {
        CompilerContext cc = new CompilerContextImpl();
        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(cc);
        AtomicInteger count = new AtomicInteger();
        AtomicBoolean executed = new AtomicBoolean(false);

        taskRunner.standBy(new AnonymousTask() {
            @Override
            public TaskResult run(CompilerContext context) {
                assertEquals(2, count.incrementAndGet());
                TaskRunner subTaskRunner = TaskRunner.newDefaultTaskRunner(context);
                subTaskRunner.standBy(new AnonymousTask() {
                    @Override
                    public TaskResult run(CompilerContext c) {
                        assertEquals(4, count.incrementAndGet());
                        executed.set(true);
                        return TaskResult.SUCCESS;
                    }
                });
                assertEquals(3, count.incrementAndGet());
                subTaskRunner.runAll();
                assertEquals(5, count.incrementAndGet());
                return TaskResult.SUCCESS;
            }
        });

        taskRunner.standBy(new AnonymousTask() {
            @Override
            public TaskResult run(CompilerContext context) {
                assertEquals(6, count.incrementAndGet());
                return TaskResult.SUCCESS;
            }
        });
        assertEquals(1, count.incrementAndGet());
        RunnerResult result = taskRunner.runAll();
        assertEquals(RunnerResult.SUCCESS_ALL, result);
        assertEquals(6, count.intValue());
    }
}
