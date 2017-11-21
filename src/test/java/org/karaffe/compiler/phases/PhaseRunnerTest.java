package org.karaffe.compiler.phases;

import java.util.Optional;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

public class PhaseRunnerTest {
    class PreConditionFail extends AbstractTransformer<String, String> {

        public PreConditionFail() {
            super(String.class, String.class);
        }

        @Override
        public boolean checkPreCondition(final String input) {
            return false;
        }

        @Override
        public Optional<String> transform(final String input) {
            Assert.fail();
            return Optional.of("FAIL");
        }

    }

    class LowerCaseToUpperCase extends AbstractTransformer<String, String> {
        public LowerCaseToUpperCase() {
            super(String.class, String.class);
        }

        @Override
        public Optional<String> transform(final String input) {
            return Optional.of(input.toUpperCase());
        }

    }

    class PostConditionFail extends AbstractTransformer<String, String> {

        public PostConditionFail() {
            super(String.class, String.class);
        }

        @Override
        public boolean checkPostCondition(final String output) {
            return false;
        }

        @Override
        public Optional<String> transform(final String input) {
            return Optional.of("FAIL");
        }

    }

    @Test
    public void testFirstCheckPreCondition() {
        final Function<String, Optional<String>> result = PhaseRunner.first(new PreConditionFail());
        final Optional<String> r = result.apply("input");
        Assert.assertFalse(r.isPresent());
    }

    @Test
    public void testNull() {
        final Function<String, Optional<String>> function = PhaseRunner.first(new LowerCaseToUpperCase());
        final Optional<String> result = function.apply(null);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testTransform() {
        final Function<String, Optional<String>> function = PhaseRunner.first(new LowerCaseToUpperCase());
        final Optional<String> result = function.apply("abc");
        Assert.assertEquals("ABC", result.get());
    }

    @Test
    public void testFirstCheckPostCondition() {
        final Function<String, Optional<String>> result = PhaseRunner.first(new PostConditionFail());
        final Optional<String> r = result.apply("input");
        Assert.assertFalse(r.isPresent());
    }

    @Test
    public void testAfter() {
        final Function<Optional<String>, Optional<String>> after = PhaseRunner.after(new LowerCaseToUpperCase());
        Assert.assertFalse(after.apply(Optional.empty()).isPresent());
        Assert.assertTrue(after.apply(Optional.of("SOME")).isPresent());

    }

}
