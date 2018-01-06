package unittests;

import org.junit.Test;
import org.karaffe.compiler.pos.LineColPos;
import org.karaffe.compiler.pos.NoPos;
import org.karaffe.compiler.pos.Range;

import nl.jqno.equalsverifier.EqualsVerifier;

public class EqualsContractTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(NoPos.class).verify();
        EqualsVerifier.forClass(LineColPos.class).verify();
        EqualsVerifier.forClass(Range.class).verify();
    }
}
