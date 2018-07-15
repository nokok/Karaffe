package org.karaffe.compiler.base.mir.interpret;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Scope {
    private Stack<List<String>> nameStack = new Stack<>();

    public void newScope() {
        this.nameStack.push(new ArrayList<>());
    }

    public void discardCurrentScope() {
        this.nameStack.pop();
    }

    public void newName(String name) {
        this.nameStack.peek().add(name);
    }
}
