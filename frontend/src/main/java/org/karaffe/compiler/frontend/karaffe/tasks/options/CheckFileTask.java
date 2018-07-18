package org.karaffe.compiler.frontend.karaffe.tasks.options;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.base.task.TaskResult;
import org.karaffe.compiler.base.util.Errors;
import org.karaffe.compiler.base.util.config.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CheckFileTask extends AbstractOptionTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckFileTask.class);

    @Override
    public TaskResult run(Options options, CompilerContext context) {

        List<String> dirList = options.arguments
                .stream()
                .map(Paths::get)
                .filter(Files::isDirectory)
                .map(Path::toString)
                .collect(Collectors.toList());

        LOGGER.debug("dirList : {}", dirList);

        if (!dirList.isEmpty()) {
            for (String f : dirList) {
                context.addReport(Errors.unexpectedDirectory(f));
            }
            LOGGER.debug("Failed");
            return TaskResult.FAILED;
        }

        List<String> notExistsList = options.arguments
                .stream()
                .map(Paths::get)
                .filter(p -> !Files.isReadable(p))
                .map(Path::toString)
                .collect(Collectors.toList());

        LOGGER.debug("notExistsList : {}", notExistsList);

        LOGGER.debug("Files.exists failed? : {}", notExistsList);
        if (!notExistsList.isEmpty()) {
            for (String f : notExistsList) {
                context.addReport(Errors.reportNoKaraffeFileFound(f));
            }
            LOGGER.debug("Failed");
            return TaskResult.FAILED;
        }
        LOGGER.debug("Passed");

        return TaskResult.SUCCESSFUL;
    }

    @Override
    public String name() {
        return "check-files";
    }
}
