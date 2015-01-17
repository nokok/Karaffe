/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.excptn;

import net.nokok.karaffe.parser.SimpleNode;

public class InternalCompilerException extends RuntimeException {

    public InternalCompilerException(SimpleNode node, String message) {
        super("この構文木を解析中に予期せぬエラーが発生しました。" + message);
        node.dump("");
    }

}
