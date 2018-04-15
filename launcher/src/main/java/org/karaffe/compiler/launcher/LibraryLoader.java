package org.karaffe.compiler.launcher;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class LibraryLoader {
    private final List<String> libraryPathList;
    private final List<JarFile> jars = new ArrayList<>();

    public LibraryLoader() {
        this(Arrays.asList(System.getProperty("java.library.path").split(System.getProperty("path.separator"))));
    }

    public LibraryLoader(List<String> libraryPathList) {
        this.libraryPathList = libraryPathList;
    }

    public void loadJars() {
        this.libraryPathList.forEach(this::loadJarFiles);
    }

    private void loadJarFiles(String path) {
        try {
            Path libraryPath = Paths.get(path);
            List<JarFile> jars = Files
                    .list(libraryPath)
                    .filter(p -> p.endsWith(".jar"))
                    .map(Path::toFile)
                    .map(file -> {
                        try {
                            return new JarFile(file);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    })
                    .collect(Collectors.toList());
            this.jars.addAll(jars);
        } catch (IOException e) {
        }
    }
}
