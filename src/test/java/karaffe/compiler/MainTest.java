package karaffe.compiler;

import org.junit.Test;

public class MainTest {

    @Test(expected = NullPointerException.class)
    public void testNullArgs() {
        Main.main(null);
    }
}
