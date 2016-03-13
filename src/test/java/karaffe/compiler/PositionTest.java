package karaffe.compiler;

import java.util.Objects;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class PositionTest {

    @Test
    public void testHashCode() {
        Position position = new Position(1, 2);
        assertThat(position.hashCode(), is(Objects.hash(1, 2)));
    }
}
