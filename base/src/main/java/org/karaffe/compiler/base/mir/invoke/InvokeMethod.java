package org.karaffe.compiler.base.mir.invoke;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.Instructions;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InvokeMethod extends AbstractInstruction {

    private Instructions target;
    private String methodName;
    private List<Instructions> parameterInstructions;

    public InvokeMethod(Instructions target, String methodName, List<Instructions> parameterInstructions) {
        this.target = Objects.requireNonNull(target);
        this.methodName = Objects.requireNonNull(methodName);
        this.parameterInstructions = Objects.requireNonNull(parameterInstructions);
    }

    public Instructions getTarget() {
        return target;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<Instructions> getParameterInstructions() {
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
