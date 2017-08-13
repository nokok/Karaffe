package org.karaffe.compiler.constraints;

import java.util.function.Predicate;

public class CmdLineOptionValidator implements Predicate<String[]> {

    @Override
    public boolean test(final String[] t) {
        if (t == null) {
            return false;
        }
        if (t.length == 0) {
            return false;
        }
        return true;
    }
}