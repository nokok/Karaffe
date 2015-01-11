/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm;

public class ClassAlias {

    private final String beforeName;
    private final String afterName;

    public ClassAlias(String beforeName, String afterName) {
        this.beforeName = beforeName;
        this.afterName = afterName;
    }
}
