package org.karaffe.compiler.base.mir.tacs;

public class Assign implements Tac {
    private Label label;
    private Reference ref;
    private Tac source;
}
