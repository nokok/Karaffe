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

import ch.qos.logback.classic.Level;

public class Main {
    public static void main(final String[] args) {
        new Main().run(args);
    }

    private void run(final String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.err.println("\nKaraffeCompiler: UncaughtException: " + e.getMessage() + " , Thread: " + t.getName());
            System.err.println("=====STACK TRACE=====");
            e.printStackTrace();
            System.err.println("=====================");
            System.err.println(DiagnosticInfo.INSTANCE.toString());
        });
        System.out.println("Launching Karaffe Tools...");


        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("> ");
            while (scanner.hasNextLine()) {
                final ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
                final String line = scanner.nextLine();
                if (line.equals("exit")) {
                    break;
                }
                switch (line) {
                case "compile":
                    System.out.println("Compiling...");
                    final KaraffeCompiler compiler = CompilerFactory.createCompiler(args);
                    try {
                        final int v = compiler.call();
                        if (v != 0) {
                            // Show Warning
                        }
                    } catch (final Exception ex) {
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
