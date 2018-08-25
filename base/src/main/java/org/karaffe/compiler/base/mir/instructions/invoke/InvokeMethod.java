package org.karaffe.compiler.base.mir.instructions.invoke;

import org.karaffe.compiler.base.mir.instructions.AbstractInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstructions;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InvokeMethod extends AbstractInstruction {

    private DeprecatedInstructions target;
    private String methodName;
    private List<DeprecatedInstructions> parameterInstructions;

    public InvokeMethod(DeprecatedInstructions target, String methodName, List<DeprecatedInstructions> parameterInstructions) {
        this.target = Objects.requireNonNull(target);
        this.methodName = Objects.requireNonNull(methodName);
        this.parameterInstructions = Objects.requireNonNull(parameterInstructions);
    }

    public DeprecatedInstructions getTarget() {
        return target;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<DeprecatedInstructions> getParameterInstructions() {
        return parameterInstructions;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.INVOKEMETHOD;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invoke (").append(target.stream().map(Object::toString).collect(Collectors.joining(", "))).append(").").append(methodName).append("(").append(parameterInstructions.stream().flatMap(Collection::stream).map(Object::toString).collect(Collectors.joining())).append(")");
        return stringBuilder.toString();
    }
}
