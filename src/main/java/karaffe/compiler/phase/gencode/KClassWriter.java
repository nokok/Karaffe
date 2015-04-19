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

    @Override
    public Void apply(ByteCode t) {
        String outputPath;
        if (t.packagePrefix().isEmpty()) {
            outputPath = "";
        } else {
            File outputDir = new File(t.packagePrefix().replaceAll("/", File.separator));
            if (!outputDir.exists()) {
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
