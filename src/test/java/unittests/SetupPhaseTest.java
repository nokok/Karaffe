package unittests;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.phases.SetupPhase;

public class SetupPhaseTest {
    @Test
    public void testTransform() {
        final SetupPhase phase = new SetupPhase();
        final Optional<CompilerConfig> contextOpt = phase.transform(new String[] {});
        Assert.assertTrue(contextOpt.isPresent());
        final CompilerConfig context = contextOpt.get();
    }
}
