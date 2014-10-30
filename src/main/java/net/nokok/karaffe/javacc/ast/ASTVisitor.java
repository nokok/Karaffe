package net.nokok.karaffe.javacc.ast;

public interface ASTVisitor {

    public Object onBoolLiteral(BoolLiteral aThis);

    public Object onFixedSizeArrayLiteral(FixedSizeArrayLiteral aThis);

    public Object onFloatLiteral(FloatLiteral aThis);

    public Object onFunctionLiteral(FunctionLiteral aThis);

    public Object onIntLiteral(IntLiteral aThis);

    public Object onJavaTypeLiteral(JavaTypeLiteral aThis);

    public Object onKaraffeTypeLiteral(KaraffeTypeLiteral aThis);

    public Object onProgram(Program aThis);

    public Object onPrograms(Programs aThis);

    public Object onStringLiteral(StringLiteral aThis);

    public Object onTypeId(TypeId aThis);

    public Object onTypeParameterId(TypeParameterId aThis);

    public Object onTypeParameters(TypeParameters aThis);

    public Object onUndefinedLiteral(UndefinedLiteral aThis);

    public Object onVaraibleId(VariableId aThis);

}
