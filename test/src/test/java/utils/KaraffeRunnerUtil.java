package utils;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KaraffeRunnerUtil {
    public static List<String> compileAndExec(String mainClassName, String... args) throws Exception {
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("-cp");
        command.add("corelib/build/libs/corelib.jar");
        command.add(mainClassName);
        command.addAll(Arrays.asList(args));

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        File f = File.createTempFile("", ".out");
        processBuilder.redirectOutput(f);
        processBuilder.redirectError(f);
        processBuilder.start();
        return Files.readAllLines(f.toPath());
    }
}
