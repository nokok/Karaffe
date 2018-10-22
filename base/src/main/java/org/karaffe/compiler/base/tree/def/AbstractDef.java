package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;

import java.util.List;

public abstract class AbstractDef extends AbstractTree implements Def {

    @Override
    public void addBody(Tree body) {
        this.addChild(body);
    }

    @Override
    public List<Tree> getBody() {
        return this.getChildren();
    }
}
