package net.nokok.karaffe.javacc.type;

import java.util.function.IntUnaryOperator;

/**
 * 遅延評価を行うInt型の配列を定義します。
 * beginからendまで、指定されたIntUnaryOperatorのステップ幅に従い配列を生成します。
 * このクラスではend値の値以下まで配列を生成します。
 *
 * @author noko
 */
public class LazyClosedRangeIntArray extends LazyIntArray {

    public LazyClosedRangeIntArray(int begin, int end, IntUnaryOperator operator) {
        super(begin, end + 1, operator);
    }

}
