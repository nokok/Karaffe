package net.nokok.karaffe.javacc.ast;

public interface ASTVisitor {

    public Object onProgram(Program aThis);

    public Object onPrograms(Programs aThis);

}
