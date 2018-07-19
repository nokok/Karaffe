package org.karaffe.compiler.base.mir.invoke;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class InvokeSpecial extends AbstractInstruction {

    private Label label;
    private String owner;
    private String methodName;
    private String parameters;
    private String returnType;

    public InvokeSpecial(Label label) {
        // owner#methodName(parameters):ReturnType
        // Ljava/lang/Object;#<init>():V
        this.label = Objects.requireNonNull(label);
        String s = this.label.toString();
        String[] names = s.split("#");
        this.owner = names[0];
        this.methodName = names[1].substring(0, names[1].indexOf("("));
        this.parameters = names[1].substring(
                names[1].indexOf("(") + 1,
                names[1].indexOf(")"));
        this.returnType = s.split(":")[1].replace(":", "");
    }

    public String getOwner() {
        return owner;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getParameters() {
        return parameters;
    }

    public String getReturnType() {
        return returnType;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.INVOKESPECIAL;
    }

    @Override
    public String toString() {
        return "InvokeSpecial " + this.label;
    }
}
