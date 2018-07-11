package org.karaffe.compiler.base;

import org.karaffe.compiler.base.tree.def.Def;

import java.util.List;
import java.util.Map;

public interface StructuralInfoContainer {

    Map<String, List<Def>> getFileImportMap();

    Map<String, String> getPackageFileMap();
}
