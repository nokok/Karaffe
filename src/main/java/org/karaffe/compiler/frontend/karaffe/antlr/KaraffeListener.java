// Generated from Karaffe.g4 by ANTLR 4.7.1
package org.karaffe.compiler.frontend.karaffe.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KaraffeParser}.
 */
public interface KaraffeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(KaraffeParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(KaraffeParser.CompilationUnitContext ctx);
}
