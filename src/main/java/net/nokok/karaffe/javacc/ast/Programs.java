package net.nokok.karaffe.javacc.ast;

import java.util.ArrayList;
import java.util.List;

public class Programs implements ASTNode {

    private final List<Program> programs = new ArrayList<>();

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onPrograms(this);
    }

    public void addProgram(Program p) {
        this.programs.add(p);
    }

    public boolean contains(Program p) {
        return programs.contains(p);
    }

    @Override
    public String nodeIdentifier() {
        return "Programs";
    }

    public int size() {
        return programs.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return programs.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        programs
                .stream()
                .map(p -> p.toString())
                .forEach(sb::append);
        return sb.toString();
    }
}
