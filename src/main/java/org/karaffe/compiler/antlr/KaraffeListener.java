// Generated from Karaffe.g4 by ANTLR 4.7
package org.karaffe.compiler.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KaraffeParser}.
 */
public interface KaraffeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(KaraffeParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(KaraffeParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void enterMainClass(KaraffeParser.MainClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void exitMainClass(KaraffeParser.MainClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void enterClassDecl(KaraffeParser.ClassDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void exitClassDecl(KaraffeParser.ClassDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(KaraffeParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(KaraffeParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void enterMethodDecl(KaraffeParser.MethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void exitMethodDecl(KaraffeParser.MethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#formalList}.
	 * @param ctx the parse tree
	 */
	void enterFormalList(KaraffeParser.FormalListContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#formalList}.
	 * @param ctx the parse tree
	 */
	void exitFormalList(KaraffeParser.FormalListContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#formalRest}.
	 * @param ctx the parse tree
	 */
	void enterFormalRest(KaraffeParser.FormalRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#formalRest}.
	 * @param ctx the parse tree
	 */
	void exitFormalRest(KaraffeParser.FormalRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(KaraffeParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(KaraffeParser.TypeContext ctx);
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
	 * Enter a parse tree produced by {@link KaraffeParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(KaraffeParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(KaraffeParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#expList}.
	 * @param ctx the parse tree
	 */
	void enterExpList(KaraffeParser.ExpListContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#expList}.
	 * @param ctx the parse tree
	 */
	void exitExpList(KaraffeParser.ExpListContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaraffeParser#expRest}.
	 * @param ctx the parse tree
	 */
	void enterExpRest(KaraffeParser.ExpRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaraffeParser#expRest}.
	 * @param ctx the parse tree
	 */
	void exitExpRest(KaraffeParser.ExpRestContext ctx);
}