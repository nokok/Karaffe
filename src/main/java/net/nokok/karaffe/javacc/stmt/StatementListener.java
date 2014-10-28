package net.nokok.karaffe.javacc.stmt;

import net.nokok.karaffe.javacc.literal.BoolLiteral;
import net.nokok.karaffe.javacc.literal.FloatLiteral;
import net.nokok.karaffe.javacc.literal.IntLiteral;
import net.nokok.karaffe.javacc.literal.UndefinedLiteral;

public interface StatementListener {

    public Object onBooleanLiteral(BoolLiteral statement);

    public Object onFloatLiteral(FloatLiteral statement);

    public Object onFuncDefStatement(FuncDefStatement statement);

    public Object onIntLiteral(IntLiteral statement);

    public Object onNewLineStatement(NewLineStatement statement);

    public Object onTypeAliasStatement(TypeAliasStatement statement);

    public Object onUndefinedLiteral(UndefinedLiteral statement);

    public Object onVariableDeclaration(VariableDeclaration statement);
}
