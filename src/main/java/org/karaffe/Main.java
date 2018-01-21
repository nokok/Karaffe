package org.karaffe;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.karaffe.compiler.CompilerFactory;
import org.karaffe.compiler.KaraffeCompiler;
import org.karaffe.compiler.util.DiagnosticInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import ch.qos.logback.classic.Level;

public class Main {
    public static void main(String[] args) {
        new Main().run(args);
    }

    private void run(String[] args) {
        System.out.println("Launching Karaffe Tools...");
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.err.println("\nKaraffeCompiler: UncaughtException: " + e.getMessage() + " , Thread: " + t.getName());
            System.err.println("=====STACK TRACE=====");
            e.printStackTrace();
            System.err.println("=====================");
            System.err.println(DiagnosticInfo.INSTANCE.toString());
        });

        Config config = ConfigFactory.parseResources("compiler.conf");
        final List<String> terminateCommands;
        if (config.hasPath("buildtool.terminateCommands")) {
            terminateCommands = config.getStringList("buildtool.terminateCommands");
        } else {
            terminateCommands = Arrays.asList("exit", "quit");
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("> ");
            while (scanner.hasNextLine()) {
                ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
                String line = scanner.nextLine();
                if (terminateCommands.contains(line)) {
                    break;
                }
                switch (line) {
                case "compile":
                    System.out.println("Compiling...");
                    KaraffeCompiler compiler = CompilerFactory.createCompiler(args);
                    try {
                        int v = compiler.call();
                        if (v != 0) {
                            // Show Warning
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("Done.");
                    break;
                case "clean":
                    System.out.println("Done.");
                    break;
                case "run":
                    System.out.println("Loading MainClass...");
                    break;
                case "\f":
                    if (Platform.isWindows()) {
                        try {
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        } catch (IOException | InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        System.out.println("");
                    }
                    break;
                case "nolog":
                    rootLogger.setLevel(Level.OFF);
                    break;
                case "debug":
                    rootLogger.setLevel(Level.DEBUG);
                    break;
                case "info":
                    rootLogger.setLevel(Level.INFO);
                    break;
                case "throw":
                    throw new RuntimeException("Crash Requested");
                }
                System.out.print("> ");
            }
        } finally {
            System.out.println("Bye");
        }
    }

}
