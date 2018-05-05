package org.karaffe.compiler.frontend.karaffe.transformer;

public interface Pass<I, O> {

    public String passName();

    public String passDescription();

    O run(I input);

    boolean changed();

}
