package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.Errors;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.SourceFile;
import org.karaffe.compiler.base.util.config.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CheckFileTask extends AbstractOptionTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckTargetTask.class);

    @Override
    public TaskResult run(Options options) {

        List<String> notExistsList = options.arguments
                .stream()
                .map(Paths::get)
                .filter(p -> !Files.isReadable(p))
                .map(Path::toString)
                .collect(Collectors.toList());

        LOGGER.debug("Files.exists failed? : {}", notExistsList);
        if (!notExistsList.isEmpty()) {
            for (String f : notExistsList) {
                Errors.reportNoKaraffeFileFound(f);
            }
            LOGGER.debug("Failed");
            return TaskResult.FAILED;
        }
        LOGGER.debug("Passed");

        return TaskResult.SUCCESS;
    }

    @Override
    public String name() {
        return "check files";
    }
}
