package net.nokok.karaffe.javacc.type;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class LazyIntArrayTest {

    @Test
    public void test0to10onStep1() {
        LazyIntArray array = new LazyIntArray(0, 10, i -> i + 1);
        List<Integer> results = new ArrayList<>();
        while ( array.hasNext() ) {
            results.add(array.next());
        }
        assertThat(results.size(), is(10));
        for ( int i = 0; i < 10; i++ ) {
            assertThat(results.get(i), is(i));
        }
    }

    @Test
    public void testMinus5toPlus10onStep1() {
        LazyIntArray array = new LazyIntArray(-5, 10, i -> i + 1);
        List<Integer> results = new ArrayList<>();
        while ( array.hasNext() ) {
            results.add(array.next());
        }
        assertThat(results.size(), is(15));
        assertThat(results.get(0), is(-5));
        //ClosedRangeでないため9
        assertThat(results.get(14), is(9));
    }

    @Test
    public void testGet1() {
        LazyIntArray array = new LazyIntArray(0, 10, i -> i + 1);
        assertThat(array.get(5), is(6));

    }

    @Test
    public void testGet2() {
        LazyIntArray array = new LazyIntArray(0, 5, i -> i + 2);
        assertThat(array.get(3), is(5));
    }
}
