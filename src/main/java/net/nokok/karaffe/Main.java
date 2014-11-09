package net.nokok.karaffe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import net.nokok.karaffe.javacc.visitors.Java8CodeGenerator;
import net.nokok.karaffe.javacc.ast.KaraffeParser;
import net.nokok.karaffe.javacc.ast.ParseException;
import net.nokok.karaffe.javacc.ast.Program;
import net.nokok.karaffe.javacc.ast.Token;
import net.nokok.karaffe.javacc.ast.TokenMgrError;

public class Main {

    /**
     * krfrunコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String[] args) {
        final String OUTPUT_FILENAME = "Krf_Main.java";
        for (String path : args) {
            try {
                byte[] lines = Files.readAllBytes(new File(path).toPath());
                KaraffeParser parser = new KaraffeParser(new String(lines));
                try {
                    Program program = parser.parse();
                    String code = (String) program.accept(new Java8CodeGenerator());
                    Path filePath = new File(OUTPUT_FILENAME).toPath();
                    Files.write(filePath, code.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
                    ProcessBuilder builder = new ProcessBuilder("javac", "-cp", "Karaffe-Lang-0.1.0.jar", OUTPUT_FILENAME);
                    builder.redirectErrorStream(true);
                    Process process = builder.start();
                    InputStream errorStream = process.getErrorStream();
                    printInputStream(errorStream);
                    InputStream inputStream = process.getInputStream();
                    printInputStream(inputStream);
                } catch (FileNotFoundException e) {
                    System.out.println("File Not Found:" + e.getMessage());
                } catch (ParseException ex) {
                    Token token = ex.currentToken;
                    System.out.println("Syntax error : " + token.beginLine + " column : " + token.endColumn);
                    printErrorInfomation(byteArrayToLines(lines), ex.currentToken);
                } catch (TokenMgrError e) {
                    Token token = parser.token;
                    System.out.println("Token error : " + token.beginLine + " column : " + token.endColumn);
                    printErrorInfomation(byteArrayToLines(lines), parser.token);
                }
            } catch (IOException e) {
                if (e instanceof FileAlreadyExistsException) {
                    System.out.println("File already exists " + OUTPUT_FILENAME);
                    return;
                }
                System.out.println("IOError:" + e);
            }
        }
    }

    public static List<String> byteArrayToLines(byte[] bytes) {
        String string = new String(bytes, Charset.forName("UTF-8"));
        String[] split = string.split("[\n\r]");
        List<String> sources = new ArrayList<>();
        Stream.of(split).forEach(sources::add);
        return sources;
    }

    public static void printErrorInfomation(List<String> lines, Token token) {
        if (token.image == null && !lines.isEmpty()) {
            System.out.println(lines.get(0));
            System.out.println("^");
            return;
        }
        System.out.println("");
        System.out.println(lines.get(token.beginLine - 1));
        for (int i = 0; i <= token.endColumn + 1; i++) {
            if (token.beginColumn <= i && i <= token.endColumn) {
                System.out.print("^");
            } else if (i + 1 < token.beginColumn) {
                System.out.print(" ");
            }
        }
        System.out.println("");
    }

    public static void printErrorInfomation(List<String> lines, Token token, int[][] expectedSequences) {
        printErrorInfomation(lines, token);
    }

    public static void printInputStream(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
