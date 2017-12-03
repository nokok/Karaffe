package org.karaffe.core;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.Test;

public class LocationTest {
    @Test
    public void testCtor() {
        Location location = new Location(Paths.get("path"), Pos.fromLineAndColumn(1, 1));
        assertEquals(Paths.get("path").toAbsolutePath() + ":1:1", location.toString());
    }
}
