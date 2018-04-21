package it;

import org.junit.Test;
import org.karaffe.compiler.launcher.KaraffeCompilerLauncher;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.fail;

public class CompilerIOTest {

    @Test
    public void runTestCase() throws Exception {
        Path tmpDirPath = Paths.get(System.getProperty("java.io.tmpdir"), String.valueOf(System.currentTimeMillis()));
        Files.createDirectory(tmpDirPath);
        System.out.println("Working Directory: " + tmpDirPath.toAbsolutePath());

        Path testDirectory = Paths.get("tests");
        System.out.println("Search Path: " + testDirectory.toAbsolutePath());
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

                boolean allLineMatched = true;
                if (expectedOutputLines.size() == actualOutputLines.size()) {
                    for (int i = 0; i < expectedOutputLines.size(); i++) {
                        String expectedLine = expectedOutputLines.get(i);
                        String actualLine = actualOutputLines.get(i);
                        boolean lineMatched;
                        if (expectedLine.startsWith("#REGEX ")) {
                            Pattern pattern = Pattern.compile(expectedLine.replace("#REGEX ", ""));
                            Matcher matcher = pattern.matcher(actualLine);
                            boolean findSuccess = matcher.find();
                            boolean matched = matcher.replaceAll("").equals("");
                            lineMatched = findSuccess && matched;
                        } else {
                            lineMatched = expectedLine.equals(actualLine);
                        }
                        if (!lineMatched) {
                            allLineMatched = false;
                        }
                    }
                } else {
                    defaultStdOut.printf("Line count comparison failed. expected %s ,actual %s%n", expectedOutputLines.size(), actualOutputLines.size());
                    allLineMatched = false;
                }
                if (allLineMatched) {
                    defaultStdOut.println("Passed : " + testCaseFile.getFileName().toString());
                } else {
                    String expectedOutputs = String.join("\n", expectedOutputLines);
                    String actualOutputs = String.join("\n", actualOutputLines);
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
