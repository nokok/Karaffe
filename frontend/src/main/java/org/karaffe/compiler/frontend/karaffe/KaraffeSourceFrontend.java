package org.karaffe.compiler.frontend.karaffe;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.AbstractTask;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.base.task.util.TaskCanceledException;
import org.karaffe.compiler.frontend.karaffe.tasks.GenASTTask;
import org.karaffe.compiler.frontend.karaffe.tasks.GenMIRTask;
import org.karaffe.compiler.frontend.karaffe.tasks.LexerTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ParserTask;
import org.karaffe.compiler.frontend.karaffe.tasks.PrintLastTreeTask;
import org.karaffe.compiler.frontend.karaffe.tasks.PrintTreeTask;
import org.karaffe.compiler.frontend.karaffe.tasks.postmir.PostMIRTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class KaraffeSourceFrontend extends AbstractTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(KaraffeSourceFrontend.class);

    private static final Set<Task> frontendTasks = new LinkedHashSet<>(Arrays.asList(
            new LexerTask(),
            new ParserTask(),
            new GenASTTask(),
            //new PostParseTask(),
            new GenMIRTask(),
            new PostMIRTask()
    ));

    @Override
    public String name() {
        return "frontend-karaffe";
    }

    @Override
    public String description() {
        return "Frontend implementation for Karaffe source code.";
    }

    @Override
    public TaskResult run(CompilerContext context) {
        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(context);
        try {
            for (Task task : getSubTask(context)) {
                taskRunner.standBy(task);
            }
            RunnerResult result = taskRunner.runAll();
            return result.toTaskResult();
        } catch (TaskCanceledException e) {
            LOGGER.info("Task Canceled");
            return TaskResult.FAILED;
        }
    }

    @Override
    public Set<Task> getSubTask(CompilerContext context) {
        if (context.getCmdLineOptions().showLastTree) {
            frontendTasks.add(new PrintLastTreeTask());
        }
        if (context.getCmdLineOptions().printTree) {
            frontendTasks.add(new PrintTreeTask());
        }
        return frontendTasks;
    }

    @Override
    public boolean changed() {
        return true;
    }

}
