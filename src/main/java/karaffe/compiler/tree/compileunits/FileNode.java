/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree.compileunits;

import java.io.File;
import karaffe.compiler.tree.AbstractNode;
import karaffe.compiler.visitor.Visitor;

public class FileNode extends AbstractNode {

    private final File file;

    public FileNode(File file) {
        this.file = file;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.fileNode(this);
    }

    @Override
    public String toString() {
        return "(FileNode:" + file.toString() + ")";
    }

    public String fileName() {
        return file.getName();
    }

}
