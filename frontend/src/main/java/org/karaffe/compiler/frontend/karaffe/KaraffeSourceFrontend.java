package org.karaffe.compiler.frontend.karaffe;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.RunnerResult;
import org.karaffe.compiler.base.task.Task;
import org.karaffe.compiler.base.task.TaskRunner;
import org.karaffe.compiler.base.task.util.TaskCanceledException;
import org.karaffe.compiler.frontend.karaffe.tasks.GenASTTask;
import org.karaffe.compiler.frontend.karaffe.tasks.LexerTask;
import org.karaffe.compiler.frontend.karaffe.tasks.ParserTask;
import org.karaffe.compiler.frontend.karaffe.tasks.PrintLastTreeTask;
import org.karaffe.compiler.frontend.karaffe.tasks.PrintTreeTask;
import org.karaffe.compiler.frontend.karaffe.tasks.postparse.PostParseTask;
import org.karaffe.compiler.mir.Instructions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class KaraffeSourceFrontend implements KaraffeCompilerFrontend {

    private static final Logger LOGGER = LoggerFactory.getLogger(KaraffeSourceFrontend.class);

    private static final Set<Task> standByTaskList = new LinkedHashSet<>(Arrays.asList(

            new PrintTreeTask(),
            new PrintLastTreeTask(),
            new LexerTask(),
            new ParserTask(),
            new GenASTTask(),
            new PostParseTask()
    ));

    @Override
    public Optional<Instructions> exec(CompilerContext context) {
        TaskRunner taskRunner = TaskRunner.newDefaultTaskRunner(context);
        try {

            for (Task task : standByTaskList) {
                taskRunner.standBy(task);
            }
            RunnerResult result = taskRunner.runAll();
            return Optional.empty();
        } catch (TaskCanceledException e) {
            LOGGER.info("Task Canceled");
            return Optional.empty();
        }
    }


}
