package org.karaffe.compiler.base.mir.block;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class BeginMethod extends AbstractInstruction {
    private Label label;

    public BeginMethod(Label label) {
        // #parent#methodName(parameters):ReturnType
        this.label = Objects.requireNonNull(label);
    }

    public Label getLabel() {
        return label;
    }

    public String getMethodName() {
        String[] names = label.toString().split("#");
        String methodDesc = names[names.length - 1];
        return methodDesc.substring(0, methodDesc.indexOf("("));
    }

    public String getReturnTypeName() {
        String[] names = label.toString().split("#");
        String methodDesc = names[names.length - 1];
        String[] m = methodDesc.split(":");
        return m[m.length - 1];
    }

    public String getParameters() {
        String[] names = label.toString().split("#");
        String methodDesc = names[names.length - 1];
        return methodDesc.substring(methodDesc.indexOf("(") + 1, methodDesc.indexOf(":") - 1);
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.BEGINMETHOD;
    }

    @Override
    public String toString() {
        return "BeginMethod " + label;
    }
}
