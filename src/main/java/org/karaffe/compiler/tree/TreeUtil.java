package org.karaffe.compiler.tree;

public final class TreeUtil {
    private TreeUtil() {

    }

    public static String prettyAST(final String astString) {
        final StringBuilder sBuilder = new StringBuilder();
        String header = "";
        for (int i = 0; i < astString.length(); i++) {
            final char c = astString.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                header += "  ";
                sBuilder.append("\n");
            }
            sBuilder.append(header).append(c);
            if (c == ')' || c == ']' || c == '}') {
                header = header.substring(2);
                sBuilder.append("\n");
            }
        }
        return sBuilder.toString();
    }
}
