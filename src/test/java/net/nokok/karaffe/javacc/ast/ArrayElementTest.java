/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.javacc.ast;

import java.util.Objects;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ArrayElementTest {

    private ArrayElement element;
    private final ASTNode TEST_NODE_A = new ASTNode() {

        @Override
        public Object accept(ASTVisitor visitor) {
            return "TestAccept";
        }

        @Override
        public String nodeIdentifier() {
            return "TestNode";
        }

        @Override
        public String toString() {
            return "ASTNode";
        }
    };

    private final ASTNode TEST_NODE_B = new ASTNode() {

        @Override
        public Object accept(ASTVisitor visitor) {
            return "OtherObject";
        }

        @Override
        public String nodeIdentifier() {
            return "TestNode";
        }

        @Override
        public String toString() {
            return "ASTNode";
        }
    };

    @Before
    public void setUp() {
        element = new ArrayElement(TEST_NODE_A);
    }

    /**
     * ArrayElementのtoStringは文字列"ArrayElement:"にASTNodeインターフェースの実装クラスのtoStringの結果
     */
    @Test
    public void testToString() {
        assertThat(element.toString(), is("ArrayElement:" + TEST_NODE_A.toString()));
    }

    @Test
    public void testHashCode1() {
        assertThat(element.hashCode(), is(Objects.hash(element.getValue(), "ArrayElement")));
    }

    @Test
    public void testHashCode2() {
        assertTrue(element.hashCode() == new ArrayElement(TEST_NODE_A).hashCode());
    }

    /**
     * 反射性のテスト
     */
    @Test
    public void testEqualsReflexive() {
        assertTrue(element.equals(element));
    }

    /**
     * 対称性のテスト
     */
    @Test
    public void testEqualsSymmetric() {
        ArrayElement y = new ArrayElement(TEST_NODE_A);
        ArrayElement z = new ArrayElement(TEST_NODE_B);
        assertTrue(element.equals(y));
        assertTrue(y.equals(element));
        assertFalse(element.equals(z));
    }

    @Test
    public void testEqualsTransitive() {
        ArrayElement y = new ArrayElement(TEST_NODE_A);
        ArrayElement z = new ArrayElement(TEST_NODE_A);
        assertTrue(element.equals(y));
        assertTrue(y.equals(z));
        assertTrue(z.equals(element));
    }

}
