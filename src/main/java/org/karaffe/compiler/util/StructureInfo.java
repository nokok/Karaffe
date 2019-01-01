package org.karaffe.compiler.util;

import org.karaffe.compiler.platform.Platform;

import java.util.ArrayList;
import java.util.List;

public class StructureInfo {
    private String targetSourceName;
    private List<Class<?>> importedClasses;
    private List<ClassDefinition> declaredClasses;
    private List<Class<?>> dependentClasses;

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        lines.add(targetSourceName + " {");
        lines.add("  Imported Class {");
        if (Platform.hasModuleSystem()) {
            // JDK 9+
        } else {

        }
        lines.add("  }");
        lines.add("  Declared Class {");
        lines.add("  }");
        lines.add("  Dependent Class {");
        lines.add("  }");
        lines.add("}");
        return String.join("\n", lines);
    }
}
