// Generated from Karaffe.g4 by ANTLR 4.7
package org.karaffe.compiler.antlr;
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
	 * Visit a parse tree produced by {@link KaraffeParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(KaraffeParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#mainClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainClass(KaraffeParser.MainClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#classDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDecl(KaraffeParser.ClassDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(KaraffeParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#methodDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDecl(KaraffeParser.MethodDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#formalList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalList(KaraffeParser.FormalListContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#formalRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalRest(KaraffeParser.FormalRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(KaraffeParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(KaraffeParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(KaraffeParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#expList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpList(KaraffeParser.ExpListContext ctx);
	/**
	 * Visit a parse tree produced by {@link KaraffeParser#expRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpRest(KaraffeParser.ExpRestContext ctx);
}