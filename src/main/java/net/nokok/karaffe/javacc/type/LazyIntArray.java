package net.nokok.karaffe.javacc.type;

import java.util.Iterator;
import java.util.function.IntUnaryOperator;

/**
 * 遅延評価を行うInt型の配列を定義します。
 * beginからendまで、指定されたIntUnaryOperatorのステップ幅に従い配列を生成します。
 * このクラスではend値の値未満まで配列を生成します。
 *
 * @author noko
 */
public class LazyIntArray implements Iterator<Integer> {

    private final IntUnaryOperator operator;
    private final int end;

    private int currentValue;

    public LazyIntArray(int begin, int end, IntUnaryOperator operator) {
        this.operator = operator;
        this.end = end;
        this.currentValue = begin;
    }

    @Override
    public boolean hasNext() {
        return currentValue < end;
    }

    @Override
    public Integer next() {
        int old = currentValue;
        currentValue = operator.applyAsInt(currentValue);
        return old;
    }

    public int get(int index) {
        return operator.applyAsInt(index);
    }
}
