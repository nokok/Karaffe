// Generated from Karaffe.g4 by ANTLR 4.7.1
package org.karaffe.compiler.frontend.karaffe.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KaraffeParser}.
 */
public interface KaraffeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#sourceFile}.
	 * @param ctx the parse tree
	 */
	void enterSourceFile(KaraffeParser.SourceFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#sourceFile}.
	 * @param ctx the parse tree
	 */
	void exitSourceFile(KaraffeParser.SourceFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(KaraffeParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(KaraffeParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#typeDefBody}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefBody(KaraffeParser.TypeDefBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#typeDefBody}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefBody(KaraffeParser.TypeDefBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(KaraffeParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(KaraffeParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#entryPointBlock}.
	 * @param ctx the parse tree
	 */
	void enterEntryPointBlock(KaraffeParser.EntryPointBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#entryPointBlock}.
	 * @param ctx the parse tree
	 */
	void exitEntryPointBlock(KaraffeParser.EntryPointBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#initBlock}.
	 * @param ctx the parse tree
	 */
	void enterInitBlock(KaraffeParser.InitBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#initBlock}.
	 * @param ctx the parse tree
	 */
	void exitInitBlock(KaraffeParser.InitBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(KaraffeParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(KaraffeParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(KaraffeParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(KaraffeParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#binaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOperator(KaraffeParser.BinaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#binaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOperator(KaraffeParser.BinaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(KaraffeParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(KaraffeParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#printFunction}.
	 * @param ctx the parse tree
	 */
	void enterPrintFunction(KaraffeParser.PrintFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#printFunction}.
	 * @param ctx the parse tree
	 */
	void exitPrintFunction(KaraffeParser.PrintFunctionContext ctx);
}
