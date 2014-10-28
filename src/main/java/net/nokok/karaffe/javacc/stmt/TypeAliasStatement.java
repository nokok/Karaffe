package net.nokok.karaffe.javacc.stmt;

import java.util.Set;
import net.nokok.karaffe.javacc.CompileErrorMessage;
import net.nokok.karaffe.javacc.identifier.TypeId;
import net.nokok.karaffe.javacc.modifier.*;

public class TypeAliasStatement implements Statement {

    private final Set<Modifier> modifiers;
    private final TypeId baseType;
    private final TypeId newType;

    public TypeAliasStatement(Set<Modifier> modifiers, TypeId newType) {
        this(modifiers, TypeId.Any, newType);
    }

    /**
     * Type Aliasステートメントを生成する。
     *
     * @param baseType
     * @param newType
     */
    public TypeAliasStatement(Set<Modifier> modifiers, TypeId baseType, TypeId newType) {
        if ( baseType.isTypeParameter() || newType.isTypeParameter() ) {
            throw new IllegalArgumentException(CompileErrorMessage.TYPE_ALIAS_TYPE_PARAMETER.toString());
        }
        this.modifiers = modifiers;
        this.baseType = baseType;
        this.newType = newType;
    }

    @Override
    public Object accept(StatementListener listener) {
        return listener.onTypeAliasStatement(this);
    }

    @Override
    public StatementType getType() {
        return StatementType.TYPE_ALIAS;
    }

    /**
     * @return 元の型
     */
    public TypeId baseType() {
        return baseType;
    }

    /**
     * @return 新しい型
     */
    public TypeId newType() {
        return newType;
    }

}
