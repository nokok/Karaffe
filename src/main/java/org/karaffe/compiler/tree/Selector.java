package org.karaffe.compiler.tree;

import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.NodeD;

public interface Selector extends NodeD {

    public static class RootPackage implements Selector {
        public RootPackage() {

        }

        @Override
        public String toString() {
            return String.format("(RootPackage)", "<root>");
        }

    }

    public static class DefaultSelector implements Selector {
        private final List<String> path;

        public DefaultSelector(final String path) {
            if (path == null) {
                throw new NullPointerException("null path");
            }
            if (path.isEmpty()) {
                throw new IllegalArgumentException("empty path");
            }
            this.path = Arrays.asList(path.split("\\."));
        }

        @Override
        public String toString() {
            return String.format("(PathSelector %s)", this.path);
        }

        public String toString(final String delimiter) {
            return String.join(delimiter, this.path);
        }

    }

}
