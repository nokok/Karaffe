package org.karaffe.compiler.tree.transform.namer;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.tree.transform.AbstractTransformer;
import org.karaffe.compiler.tree.v2.PackageDef;
import org.karaffe.compiler.tree.v2.api.ImportStatement;
import org.karaffe.compiler.tree.v2.imports.OnDemandImport;
import org.karaffe.compiler.tree.v2.imports.SimpleImport;
import org.karaffe.compiler.tree.v2.names.FullyQualifiedTypeName;
import org.karaffe.compiler.tree.v2.names.PackageName;

public class DefaultImportTransformer extends AbstractTransformer {

    private final List<ImportStatement> defaultImports;

    public DefaultImportTransformer() {
        super("default-imports");
        this.defaultImports = new ArrayList<>();
        this.defaultImports.add(new OnDemandImport(new PackageName("java", "lang")));
        this.defaultImports.add(new OnDemandImport(new PackageName("java", "io")));
        this.defaultImports.add(new OnDemandImport(new PackageName("java", "net")));
        this.defaultImports.add(new OnDemandImport(new PackageName("java", "util")));
        this.defaultImports.add(new OnDemandImport(new PackageName("java", "time")));
        this.defaultImports.add(new OnDemandImport(new PackageName("java", "time", "chrono")));
        this.defaultImports.add(new SimpleImport(new FullyQualifiedTypeName("java", "time", "LocalDateTime")));
        this.defaultImports.add(new SimpleImport(new FullyQualifiedTypeName("java", "time", "chrono", "JapaneseEra")));
        this.defaultImports.add(new OnDemandImport(new PackageName("karaffe", "core")));
    }

    @Override
    public PackageDef transformBody(PackageDef packageDef) {
        this.defaultImports.forEach(packageDef::addImportStatement);
        return packageDef;
    }

}
