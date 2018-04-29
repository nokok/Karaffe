package org.karaffe.compiler.frontend.karaffe.transformer;

import karaffe.core.Any;
import org.karaffe.compiler.frontend.karaffe.ast.PackageDef;
import org.karaffe.compiler.frontend.karaffe.ast.api.ImportStatement;
import org.karaffe.compiler.frontend.karaffe.ast.imports.OnDemandImport;
import org.karaffe.compiler.frontend.karaffe.ast.imports.SimpleImport;
import org.karaffe.compiler.frontend.karaffe.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.frontend.karaffe.ast.names.PackageName;

import java.io.Reader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.chrono.JapaneseEra;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultImportTransformer extends AbstractTransformer {

    private final List<ImportStatement> defaultImports;

    public DefaultImportTransformer() {
        super("default-imports");
        this.defaultImports = new ArrayList<>();
//        this.defaultImports.add(new OnDemandImport(new PackageName("java", "lang")));
//        this.defaultImports.add(new OnDemandImport(new PackageName("java", "io")));
//        this.defaultImports.add(new OnDemandImport(new PackageName("java", "net")));
//        this.defaultImports.add(new OnDemandImport(new PackageName("java", "util")));
//        this.defaultImports.add(new OnDemandImport(new PackageName("java", "time")));
//        this.defaultImports.add(new OnDemandImport(new PackageName("java", "time", "chrono")));
//        this.defaultImports.add(new SimpleImport(new FullyQualifiedTypeName("java", "time", "LocalDateTime")));
//        this.defaultImports.add(new SimpleImport(new FullyQualifiedTypeName("java", "time", "chrono", "JapaneseEra")));
//        this.defaultImports.add(new OnDemandImport(new PackageName("karaffe", "core")));
        this.defaultImports.add(new SimpleImport(new FullyQualifiedTypeName("java", "lang", "Object")));
        this.defaultImports.add(new SimpleImport(new FullyQualifiedTypeName("karaffe","core", "Any")));
        this.defaultImports.add(new SimpleImport(new FullyQualifiedTypeName("karaffe","core", "Console")));
        this.defaultImports.add(new SimpleImport(new FullyQualifiedTypeName("karaffe","core", "Int")));
        this.defaultImports.add(new SimpleImport(new FullyQualifiedTypeName("karaffe","core", "String")));

        // NPE回避の読み込み
        // Packageを動的ロードするときにここで読み込んでおかないとPackage.getPackage(...)したときにnullが返ってくるため
        Arrays.asList(
                Object.class,
                Reader.class,
                URL.class,
                List.class,
                LocalDateTime.class,
                JapaneseEra.class,
                Any.class);
    }

    @Override
    public PackageDef transformBody(PackageDef packageDef) {
        this.defaultImports.forEach(packageDef::addImportStatement);
        return packageDef;
    }

}
