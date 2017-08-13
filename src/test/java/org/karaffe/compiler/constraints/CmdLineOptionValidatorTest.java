package org.karaffe.compiler.constraints;

import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;

public class CmdLineOptionValidatorTest {

    private final Predicate<String[]> target = new CmdLineOptionValidator();

    @Test
    public void nullTest() {
        Assert.assertFalse(this.target.test(null));
    }

    @Test
    public void emptyTest() {
        Assert.assertFalse(this.target.test(new String[0]));
    }

    @Test
    public void simpleTest() {
        Assert.assertTrue(this.target.test(new String[] { "karaffe.krf" }));
    }

}
