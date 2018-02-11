package org.karaffe.compiler.tree.meta;

import org.karaffe.compiler.tree.Name;

public class MethodRenameRule extends AbstractMetaNode {

    private final Name before;
    private final Name after;

    public MethodRenameRule(Name before, Name after) {
        super(before, after);
        this.before = before;
        this.after = after;
    }

    @Override
    public String vSource() {
        return String.format("#RENAME %s -> %s", this.before, this.after);
    }

    @Override
    public MetaNodeType getMetaNodeType() {
        return MetaNodeType.RENAME_RULE;
    }

}
