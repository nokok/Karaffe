package net.nokok.karaffe.javacc.stmt;

import net.nokok.karaffe.javacc.ast.Node;

public interface Statement extends Node<StatementType> {

    /**
     * @return seffect演算子を許可する場合true
     */
    public boolean isAllowingSideEffectModifier();
}
