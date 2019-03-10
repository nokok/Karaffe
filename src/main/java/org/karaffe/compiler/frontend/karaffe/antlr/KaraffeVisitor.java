// Generated from Karaffe.g4 by ANTLR 4.7.1
package org.karaffe.compiler.frontend.karaffe.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link KaraffeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface KaraffeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#sourceFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceFile(KaraffeParser.SourceFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(KaraffeParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#typeDefBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefBody(KaraffeParser.TypeDefBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(KaraffeParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#entryPointBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntryPointBlock(KaraffeParser.EntryPointBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#initBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitBlock(KaraffeParser.InitBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(KaraffeParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(KaraffeParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#binding}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinding(KaraffeParser.BindingContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(KaraffeParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(KaraffeParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#opExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpExpr(KaraffeParser.OpExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#binaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperator(KaraffeParser.BinaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(KaraffeParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(KaraffeParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#nl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNl(KaraffeParser.NlContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#newLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewLine(KaraffeParser.NewLineContext ctx);
}
