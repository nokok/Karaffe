package net.nokok.karaffe.javacc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Program implements Iterable<Statement> {

    private final List<Statement> statements = new ArrayList<>();

    public void addStatement(Statement s) {
        statements.add(s);
    }

    public Statement get(int line) {
        return statements.get(line);
    }

    @Override
    public Iterator<Statement> iterator() {
        return statements.iterator();
    }

    public int programSize() {
        return statements.size();
    }
}
