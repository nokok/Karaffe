package org.karaffe.compiler.frontend.karaffe.transformer;

import org.karaffe.compiler.frontend.karaffe.ast.imports.AliasImport;
import org.karaffe.compiler.frontend.karaffe.ast.imports.OnDemandImport;
import org.karaffe.compiler.frontend.karaffe.ast.imports.SimpleImport;
import org.karaffe.compiler.frontend.karaffe.transformer.util.TransformerContext;

public class PostParsePhase extends AbstractTransformer {

    private final TransformerContext context = TransformerContext.INSTANCE;

    private String fileName;

    public PostParsePhase() {
        super("post-parse");
    }

    public void onFileNameChanged(String oldFileName, String newFileName) {
        this.fileName = newFileName;
    }

    @Override
    public void onAliasImportAfter(AliasImport aliasImport) {
        this.context.addImport(this.fileName, aliasImport);
    }

    @Override
    public void onSimpleImportAfter(SimpleImport simpleImport) {
        this.context.addImport(this.fileName, simpleImport);
    }

    @Override
    public void onOnDemandImportAfter(OnDemandImport onDemandImport) {
        this.context.addImport(this.fileName, onDemandImport);
    }
}
