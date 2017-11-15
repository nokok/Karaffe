package org.karaffe.compiler.phases;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.CompilerContext;

public class SetupPhaseTest {
    @Test
    public void testTransform() {
        final SetupPhase phase = new SetupPhase();
        final Optional<CompilerContext> contextOpt = phase.transform(new String[] {});
        Assert.assertTrue(contextOpt.isPresent());
        final CompilerContext context = contextOpt.get();
        Assert.assertFalse(context.hasSource());
    }
}
