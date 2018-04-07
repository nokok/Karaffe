package org.karaffe.compiler.base;

import org.karaffe.compiler.base.util.SystemPropertyConfigurator;

import java.util.ArrayList;
import java.util.List;

public class KaraffeCompilerLauncher {
    public static void main(String[] args) {
        new KaraffeCompilerLauncher().run(args);
    }

    private void run(String[] args) {
        if (args.length == 0) {
            System.out.println(usage());
            return;
        }
        new LibraryLoader().loadJars();
        new SystemPropertyConfigurator(args).updateSystemProperty();
    }

    private String usage() {
        List<String> output = new ArrayList<>();
        output.add("Usage: krfc [source files]");
        return String.join("\n", output);
    }
}
