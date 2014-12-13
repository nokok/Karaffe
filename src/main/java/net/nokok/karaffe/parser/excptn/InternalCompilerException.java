/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.excptn;

import net.nokok.karaffe.parser.SimpleNode;

public class InternalCompilerException extends RuntimeException {

    public InternalCompilerException(SimpleNode node, String message) {
        super("この構文木を解析中に予期せぬエラーが発生しました。" + message);
        node.dump("");
    }

}
