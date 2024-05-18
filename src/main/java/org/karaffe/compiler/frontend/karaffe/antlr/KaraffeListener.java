// Generated from Karaffe.g4 by ANTLR 4.13.1
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
	 * Enter a parse tree produced by {@link KaraffeParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(KaraffeParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(KaraffeParser.AssignContext ctx);
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
	 * Enter a parse tree produced by {@link KaraffeParser#binding}.
	 * @param ctx the parse tree
	 */
	void enterBinding(KaraffeParser.BindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#binding}.
	 * @param ctx the parse tree
	 */
	void exitBinding(KaraffeParser.BindingContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(KaraffeParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(KaraffeParser.TypeNameContext ctx);
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
	 * Enter a parse tree produced by {@link KaraffeParser#opExpr}.
	 * @param ctx the parse tree
	 */
	void enterOpExpr(KaraffeParser.OpExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#opExpr}.
	 * @param ctx the parse tree
	 */
	void exitOpExpr(KaraffeParser.OpExprContext ctx);
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
	 * Enter a parse tree produced by {@link KaraffeParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(KaraffeParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(KaraffeParser.ExprListContext ctx);
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
	 * Enter a parse tree produced by {@link KaraffeParser#nl}.
	 * @param ctx the parse tree
	 */
	void enterNl(KaraffeParser.NlContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#nl}.
	 * @param ctx the parse tree
	 */
	void exitNl(KaraffeParser.NlContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#newLine}.
	 * @param ctx the parse tree
	 */
	void enterNewLine(KaraffeParser.NewLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#newLine}.
	 * @param ctx the parse tree
	 */
	void exitNewLine(KaraffeParser.NewLineContext ctx);
}
