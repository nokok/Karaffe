package it;

import org.junit.Test;
import org.karaffe.compiler.launcher.KaraffeCompilerLauncher;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CompilerIOTest {

    @Test
    public void runTestCase() throws Exception {
        Path tmpDirPath = Paths.get(System.getProperty("java.io.tmpdir"), String.valueOf(System.currentTimeMillis()));
        Files.createDirectory(tmpDirPath);
        System.out.println("Working Directory: " + tmpDirPath.toAbsolutePath());

        Path testDirectory = Paths.get("tests");
        System.out.println("Search Path: " + testDirectory.toAbsolutePath());
        System.out.println(Files.list(testDirectory).collect(Collectors.toList()));
        List<Path> testCases = Files.list(testDirectory).filter(p -> p.getFileName().toString().endsWith(".case")).collect(Collectors.toList());
        System.out.println("TestCases: " + testCases);

        PrintStream defaultStdOut = System.out;
        PrintStream defaultStdErr = System.err;
        StringBuilder errorMsgBuilder = new StringBuilder();
        int failed = 0;
        for (Path testCaseFile : testCases) {
            String inputLine = new String(Files.readAllBytes(testCaseFile));
            String[] args = Stream.of(inputLine.split(","))
                    .filter(arg -> arg.trim().replace("\n", "").length() != 0)
                    .map(arg -> arg.replace("\n", ""))
                    .toArray(String[]::new);
            File file = new File(tmpDirPath.toFile(), testCaseFile.getFileName().toString() + ".out");

            try (PrintStream output = new PrintStream(file)) {
                KaraffeCompilerLauncher launcher = new KaraffeCompilerLauncher(System.in, output, output);
                launcher.run(args);
                List<String> actualOutputLines = Files.readAllLines(file.toPath());
                String outputTestFileName = testCaseFile.getFileName().toString().replace(".case", ".out");
                List<String> expectedOutputLines = Files.readAllLines(Paths.get("tests", outputTestFileName));
                String expectedOutputs = String.join("\n", expectedOutputLines);
                String actualOutputs = String.join("\n", actualOutputLines);
                if (expectedOutputs.equals(actualOutputs)) {
                    defaultStdOut.println("Passed : " + testCaseFile.getFileName().toString());
                } else {
                    defaultStdErr.println("Failed : " + testCaseFile.getFileName().toString());
                    errorMsgBuilder.append("Command: krfc ").append(String.join(" ", args)).append(System.lineSeparator());
                    errorMsgBuilder.append("Failed: ").append(file.getAbsolutePath()).append(System.lineSeparator());
                    errorMsgBuilder.append("===Expected===").append(System.lineSeparator());
                    errorMsgBuilder.append(expectedOutputs).append(System.lineSeparator());
                    errorMsgBuilder.append("====Actual====").append(System.lineSeparator());
                    errorMsgBuilder.append(actualOutputs).append(System.lineSeparator());
                    failed++;
                }
            }
        }
        if (failed > 0) {
            defaultStdErr.println();
            defaultStdErr.println("====Failure Report====");
            defaultStdErr.println(errorMsgBuilder);
            defaultStdErr.println("====END");
            fail();
        } else {
            defaultStdOut.println("Cleanup...");
            Files.list(tmpDirPath).forEach(p -> {
                try {
                    Files.delete(p);
                } catch (Exception e) {
                    //ignore
                }
            });
            Files.delete(tmpDirPath);
            defaultStdOut.println("Cleanup completed.");
        }
    }
}
