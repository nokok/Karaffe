package net.nokok.karaffe;

import java.io.File;
import java.io.FileReader;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.KaraffeParser;

public class Main {

    /**
     * krfcコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String... args) throws Exception {
        args = new String[]{"Int.krf"};
        for (String arg : args) {
            File file = new File(arg);
            KaraffeParser parser = new KaraffeParser(new FileReader(file));
            ASTCompileUnit compileUnit = parser.CompileUnit();
//            BytecodeGenerator bytecodeGenerator = new BytecodeGenerator(file.getName());
//            compileUnit.jjtAccept(bytecodeGenerator, null);
//            Map<String, byte[]> classes = bytecodeGenerator.toByteArrays();
//            classes.entrySet().stream().forEach(clazz -> {
//                try {
//                    String className = clazz.getKey();
//                    byte[] bytecode = clazz.getValue();
//                    //クラスパス上にバイトコードを出力する
//                    Path path = new File(new File(Main.class.getClassLoader().getResource("").getPath()), className + ".class").toPath();
//                    if (Files.exists(path)) {
//                        Files.delete(path);
//                    }
//                    Files.write(path, bytecode, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            });
            compileUnit.dump("");
        }
    }
}
