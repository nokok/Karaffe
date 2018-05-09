package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.Errors;
import org.karaffe.compiler.base.util.config.Options;
import org.karaffe.compiler.frontend.karaffe.tasks.util.TaskResult;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CheckFileTask extends AbstractOptionTask {
    @Override
    public TaskResult run(Options options) {
        List<String> notExistsList = options.arguments
                .stream()
                .filter(arg -> !Files.exists(Paths.get(arg)))
                .collect(Collectors.toList());

        if (!notExistsList.isEmpty()) {
            for (String f : notExistsList) {
                Errors.reportNoKaraffeFileFound(f);
            }
            triggerFailure();
            return TaskResult.NON_RECOVERABLE;
        }

        triggerSuccess();
        return TaskResult.SUCCESS;
    }

    @Override
    public String name() {
        return "check files";
    }
}
