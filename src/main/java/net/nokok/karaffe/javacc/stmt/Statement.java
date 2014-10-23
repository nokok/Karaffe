package net.nokok.karaffe.javacc.stmt;

public interface Statement {

    /**
     * @return ステートメントの種類
     */
    public StatementType getType();

    /**
     * @return seffect演算子を許可する場合true
     */
    public boolean isAllowingSideEffectModifier();
}
