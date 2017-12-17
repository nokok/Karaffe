package org.karaffe;

import java.util.Scanner;

import org.karaffe.compiler.util.DiagnosticInfo;

public class Main {
    public static void main(String[] args) {
        new Main().run(args);
    }

    private void run(String[] args) {

        System.out.println("Launching Karaffe Tools...");
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println("\nKaraffeCompiler: UncaughtException: " + e.getMessage() + " , Thread: " + t.getName());
            System.out.println("=====STACK TRACE=====");
            e.printStackTrace();
            System.out.println("=====================");
            DiagnosticInfo.INSTANCE.toString();
        });

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.equals("exit") || line.equals("quit")) {
                    break;
                }
                switch (line) {
                case "compile":
                    System.out.println("Compiling...");
                    System.out.println("Done.");
                    break;
                case "clean":
                    System.out.println("Done.");
                    break;
                case "run":
                    System.out.println("Loading MainClass...");
                    break;
                }
            }
        } finally {
            System.out.println("Bye");
        }
    }

}
