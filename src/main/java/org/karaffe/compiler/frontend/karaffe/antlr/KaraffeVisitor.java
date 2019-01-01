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
	 * Visit a parse tree produced by {@link KaraffeParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(KaraffeParser.CompilationUnitContext ctx);
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
	 * Visit a parse tree produced by {@link KaraffeParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(KaraffeParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(KaraffeParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#additiveExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(KaraffeParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(KaraffeParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(KaraffeParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#printFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintFunction(KaraffeParser.PrintFunctionContext ctx);
}
