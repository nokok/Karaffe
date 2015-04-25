/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.gencode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Consumer;
import karaffe.compiler.phase.Phase;

public class KClassWriter extends Phase<ByteCode, Void> implements Consumer<ByteCode> {

    public KClassWriter() {
        super("classwriter");
    }

    /**
     * 渡されたByteCodeオブジェクトをclassファイルに書き込みます。
     *
     * パッケージ宣言されていた場合は
     * その宣言通りのディレクトリを作成する。
     *
     * 例:
     * {@code package hoge.fuga.piyo}と宣言されていた場合は
     * {@code ./hoge/fuga/piyo}のディレクトリを作成後、piyoへclassファイルを書き込む。
     *
     * @param t
     * @return
     */
    @Override
    public Void apply(ByteCode t) {
        String outputPath;
        if (t.packagePrefix().isEmpty()) {
            //パッケージプレフィックスが空=パッケージ宣言がされていないなら
            //出力先はカレントディレクトリ
            outputPath = "";
        } else {
            //パッケージプレフィックスが空じゃない=パッケージ宣言がされている
            //パッケージ宣言された通りにディレクトリを作成し、作成したディレクトリに出力する。
            File outputDir = new File(t.packagePrefix().replaceAll("/", File.separator));
            if (!outputDir.exists()) {
                //出力先が無いなら作成する
                outputDir.mkdirs();
            }
            outputPath = t.packagePrefix().replaceAll("/", File.separator) + File.separator;
        }
        try (FileOutputStream stream = new FileOutputStream(outputPath + t.fileName())) {
            stream.write(t.get());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void accept(ByteCode t) {
        apply(t);
    }

}
