package net.nokok.karaffe;

import java.io.IOException;
import net.nokok.karaffe.javacc.ParseException;
import org.junit.Test;

public class MainTest {

    @Test(expected = NullPointerException.class)
    public void testMainMethod() throws IOException, ParseException {
        Main.main(null);
    }
}
