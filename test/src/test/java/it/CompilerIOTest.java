package it;

import org.junit.Ignore;
import org.junit.Test;
import org.karaffe.compiler.base.util.Platform;
import org.karaffe.compiler.launcher.KaraffeCompilerLauncher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.fail;

public class CompilerIOTest {

    private StringBuilder errorMsgBuilder = new StringBuilder();
    private Path tmpDirPath;
    private PrintStream defaultStdOut = System.out;
    private PrintStream defaultStdErr = System.err;

    private Path setUpTmpDestDir() throws IOException {
        this.tmpDirPath = Paths.get(System.getProperty("java.io.tmpdir"), String.valueOf(System.currentTimeMillis()));
        Files.createDirectory(this.tmpDirPath);
        System.out.println("Working Directory: " + this.tmpDirPath.toAbsolutePath());
        return this.tmpDirPath;
    }

    private List<Path> listTestCases(String dirName, String... after) {
        return listTestCasesWithExtension(".case", dirName, after);
    }

    private List<Path> listTestCasesWithExtension(String extension, String dirName, String... after) {
        try {
            Path testDirectory = Paths.get(dirName, after);
            System.out.println("Search Path: " + testDirectory.toAbsolutePath());
            List<Path> testCases = Files.list(testDirectory).filter(p -> p.getFileName().toString().endsWith(extension)).collect(Collectors.toList());
            System.out.println("TestCases: " + testCases);
            return testCases;
        } catch (NoSuchFileException e) {
            return Collections.emptyList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String[] makeArguments(Path testCaseFile) throws IOException {
        String inputLine = new String(Files.readAllBytes(testCaseFile));
        return Stream.of(inputLine.split(","))
                .filter(arg -> !arg.trim().replace("\n", "").isEmpty())
                .map(arg -> arg.replace("\n", ""))
                .toArray(String[]::new);
    }

    private File createTestCaseOutFile(Path parentDir, String name) {
        return new File(parentDir.toFile(), name + ".out");
    }

    private int runStandardTests(List<Path> testCases) throws Exception {
        int failed = 0;
        for (Path testCase : testCases) {
            String[] args = makeArguments(testCase);
            failed += runTestCaseWithOption(args, testCase, (actualLines, destFile) -> {
                Path expectedOutputFile = Paths.get("tests", testCase.getFileName().toString().replace(".case", ".out"));
                List<String> expectedOutputLines = readAllLines(expectedOutputFile);
                if (isAllLineMatched(actualLines, expectedOutputLines)) {
                    printPassed(testCase);
                    return true;
                } else {
                    printFailed(testCase);
                    String expectedOutputs = String.join("\n", expectedOutputLines);
                    String actualOutputs = String.join("\n", actualLines);
                    this.errorMsgBuilder.append("Command: krfc ").append(String.join(" ", args)).append(System.lineSeparator());
                    this.errorMsgBuilder.append("Failed: ").append(destFile.getAbsolutePath()).append(System.lineSeparator());
                    this.errorMsgBuilder.append("TestCase file: ").append(testCase).append(System.lineSeparator());
                    this.errorMsgBuilder.append("Expected output file: ").append(expectedOutputFile.toAbsolutePath()).append(System.lineSeparator());
                    this.errorMsgBuilder.append("===Expected===").append(System.lineSeparator());
                    this.errorMsgBuilder.append(expectedOutputs).append(System.lineSeparator());
                    this.errorMsgBuilder.append("====Actual====").append(System.lineSeparator());
                    this.errorMsgBuilder.append(actualOutputs).append(System.lineSeparator());
                    return false;
                }
            });
        }
        return failed;
    }

    private void printPassed(Path caseName) {
        this.defaultStdOut.println("Passed : " + caseName.getFileName());
    }

    private void printFailed(Path caseName) {
        this.defaultStdErr.println("Failed : " + caseName.getFileName());
    }

    private int runTestCase(Path testCase, BiFunction<List<String>, File, Boolean> onComplete) throws Exception {
        return runTestCaseWithOption(new String[]{}, testCase, onComplete);
    }

    private int runTestCaseWithOption(String[] args, Path testCase, BiFunction<List<String>, File, Boolean> onComplete) throws Exception {
        File destFile = createTestCaseOutFile(this.tmpDirPath, testCase.getFileName().toString());
        List<String> argsL = new ArrayList<>(Arrays.asList(args));
        if (!testCase.toString().endsWith(".case")) {
            argsL.add(testCase.toString());
        }

        try (PrintStream output = new PrintStream(destFile)) {
            KaraffeCompilerLauncher launcher = new KaraffeCompilerLauncher(System.in, output, output);
            this.defaultStdOut.println("Running...: krfc " + String.join(" ", argsL));
            launcher.run(argsL.toArray(new String[]{}));
            List<String> actualOutputLines = Files.readAllLines(destFile.toPath());
            return onComplete.apply(actualOutputLines, destFile) ? 0 : 1;
        }
    }

    private int runPositiveTests(List<Path> testCases) throws Exception {
        int failed = 0;
        for (Path testCase : testCases) {
            failed += runTestCase(testCase, (actualLines, destFile) -> {
                if (actualLines.isEmpty()) {
                    printPassed(testCase);
                    return true;
                } else {
                    printFailed(testCase);
                    String actualOutputs = String.join("\n", actualLines);
                    this.errorMsgBuilder.append("Command: krfc ").append(testCase.toAbsolutePath()).append(System.lineSeparator());
                    this.errorMsgBuilder.append("Failed: ").append(destFile.getAbsolutePath()).append(System.lineSeparator());
                    this.errorMsgBuilder.append("====Actual====").append(System.lineSeparator());
                    this.errorMsgBuilder.append(actualOutputs).append(System.lineSeparator());
                    return false;
                }
            });
        }
        return failed;
    }

    private int runNegativeTests(List<Path> testCases) throws Exception {
        int failed = 0;
        for (Path testCase : testCases) {
            failed += runTestCase(testCase, (actualLines, destFile) -> {
                Path expectedOutputFile = Paths.get("tests", "test_resources", "neg", testCase.getFileName().toString().replace(".krf", ".out"));
                List<String> expectedOutputLines = readAllLines(expectedOutputFile);

                boolean allLineMatched = isAllLineMatched(actualLines, expectedOutputLines);
                if (allLineMatched) {
                    printPassed(testCase);
                    return true;
                } else {
                    printFailed(testCase);
                    String expectedOutputs = String.join("\n", expectedOutputLines);
                    String actualOutputs = String.join("\n", actualLines);
                    this.errorMsgBuilder.append("Command: krfc ").append(testCase.getFileName()).append(System.lineSeparator());
                    this.errorMsgBuilder.append("Failed: ").append(destFile.getAbsolutePath()).append(System.lineSeparator());
                    this.errorMsgBuilder.append("TestCase file: ").append(testCase.toAbsolutePath()).append(System.lineSeparator());
                    this.errorMsgBuilder.append("Expected output file: ").append(expectedOutputFile.toAbsolutePath()).append(System.lineSeparator());
                    this.errorMsgBuilder.append("===Expected===").append(System.lineSeparator());
                    this.errorMsgBuilder.append(expectedOutputs).append(System.lineSeparator());
                    this.errorMsgBuilder.append("====Actual====").append(System.lineSeparator());
                    this.errorMsgBuilder.append(actualOutputs).append(System.lineSeparator());
                    return false;
                }
            });
        }
        return failed;
    }

    private boolean isAllLineMatched(List<String> actualLines, List<String> expectedOutputLines) {
        if (expectedOutputLines.size() == actualLines.size()) {
            for (int i = 0; i < expectedOutputLines.size(); i++) {
                String expectedLine = expectedOutputLines.get(i);
                String actualLine = actualLines.get(i);
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
                    return false;
                }
            }
            return true;
        } else {
            this.defaultStdOut.printf("Line count comparison failed. expected %s ,actual %s%n", expectedOutputLines.size(), actualLines.size());
            return false;
        }
    }

    @Test
    public void runTestCase() throws Exception {
        Path tmpDirPath = setUpTmpDestDir();

        PrintStream defaultStdOut = System.out;
        PrintStream defaultStdErr = System.err;

        int failed = runStandardTests(listTestCases("tests"));
        failed += runPositiveTests(listTestCasesWithExtension(".krf", "tests", "test_resources", "pos"));
        failed += runNegativeTests(listTestCasesWithExtension(".krf", "tests", "test_resources", "neg"));

        if (failed > 0) {
            defaultStdErr.println();
            defaultStdErr.println("====Failure Report====");
            defaultStdErr.println(this.errorMsgBuilder);
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

    private List<String> readAllLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }


}
