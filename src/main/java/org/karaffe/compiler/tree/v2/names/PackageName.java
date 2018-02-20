package org.karaffe.compiler.tree.v2.names;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;

public class PackageName extends AbstractTree {
    private final List<? extends SimpleName> packageName;

    private PackageName() {
        this(new SimpleName("<root>"));
    }

    public PackageName(SimpleName... packageNames) {
        this(new ArrayList<>(Arrays.asList(packageNames)));
    }

    public PackageName(List<? extends SimpleName> packageName) {
        this.packageName = new ArrayList<>(packageName);
        if (this.packageName.isEmpty()) {
            throw new IllegalArgumentException("empty packageName");
        }
    }

    public PackageName(PackageName other) {
        this(new ArrayList<>(other.packageName.stream().map(SimpleName::new).collect(Collectors.toList())));
    }

    private PackageName(Position position) {
        this(position, new SimpleName("<root>"));
    }

    public PackageName(Position position, SimpleName... packageNames) {
        this(position, new ArrayList<>(Arrays.asList(packageNames)));
    }

    public PackageName(Position position, List<? extends SimpleName> packageName) {
        super(position);
        this.packageName = new ArrayList<>(packageName);
        if (this.packageName.isEmpty()) {
            throw new IllegalArgumentException("empty packageName");
        }
    }

    public PackageName(Position position, PackageName other) {
        this(position, new ArrayList<>(other.packageName.stream().map(SimpleName::new).collect(Collectors.toList())));
    }

    public static PackageName ofRoot() {
        return new PackageName();
    }

    @Override
    public String toString() {
        return String.join(".", this.packageName);
    }

}
