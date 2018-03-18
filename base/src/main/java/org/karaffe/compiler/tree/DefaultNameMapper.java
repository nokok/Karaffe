package org.karaffe.compiler.tree;

public class DefaultNameMapper implements NameMapper {

    @Override
    public String convert(Name name) {
        String n = name.getText();
        switch (n) {
        case "+":
            return "plus";
        case "-":
            return "minus";
        case "*":
            return "star";
        case "/":
            return "slash";
        default:
            return n;
        }
    }

}
