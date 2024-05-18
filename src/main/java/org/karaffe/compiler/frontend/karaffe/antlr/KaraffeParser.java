// Generated from Karaffe.g4 by ANTLR 4.13.1
package org.karaffe.compiler.frontend.karaffe.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class KaraffeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, ENTRYPOINT=3, IMPLEMENTS=4, INTERFACE=5, NAMESPACE=6, 
		PROTECTED=7, ABSTRACT=8, CONTINUE=9, DELEGATE=10, INTERNAL=11, OVERRIDE=12, 
		DEFAULT=13, EXTENDS=14, FINALLY=15, PACKAGE=16, PRIVATE=17, EXPORT=18, 
		EXTEND=19, IMPORT=20, MODULE=21, NAMEOF=22, PUBLIC=23, RETURN=24, SEALED=25, 
		STATIC=26, THROWS=27, TYPEOF=28, ALIAS=29, ASYNC=30, AWAIT=31, BREAK=32, 
		CATCH=33, CLASS=34, FALSE=35, FINAL=36, MACRO=37, MATCH=38, MIXIN=39, 
		SUPER=40, THROW=41, TRAIT=42, WHILE=43, YIELD=44, CASE=45, ELSE=46, ENUM=47, 
		FUNC=48, INIT=49, LAZY=50, NULL=51, THIS=52, TRUE=53, DEF=54, FOR=55, 
		NEW=56, TRY=57, AS=58, DO=59, IF=60, IN=61, IS=62, LPAREN=63, RPAREN=64, 
		LBRACE=65, RBRACE=66, DOT=67, COMMA=68, SEMI=69, StringLiteral=70, IntegerLiteral=71, 
		Identifier=72, WS=73;
	public static final int
		RULE_sourceFile = 0, RULE_classDef = 1, RULE_typeDefBody = 2, RULE_statement = 3, 
		RULE_entryPointBlock = 4, RULE_initBlock = 5, RULE_assign = 6, RULE_varDef = 7, 
		RULE_binding = 8, RULE_typeName = 9, RULE_expr = 10, RULE_opExpr = 11, 
		RULE_binaryOperator = 12, RULE_exprList = 13, RULE_literal = 14, RULE_nl = 15, 
		RULE_newLine = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"sourceFile", "classDef", "typeDefBody", "statement", "entryPointBlock", 
			"initBlock", "assign", "varDef", "binding", "typeName", "expr", "opExpr", 
			"binaryOperator", "exprList", "literal", "nl", "newLine"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'\\n'", "'entrypoint'", "'implements'", "'interface'", 
			"'namespace'", "'protected'", "'abstract'", "'continue'", "'delegate'", 
			"'internal'", "'override'", "'default'", "'extends'", "'finally'", "'package'", 
			"'private'", "'export'", "'extend'", "'import'", "'module'", "'nameof'", 
			"'public'", "'return'", "'sealed'", "'static'", "'throws'", "'typeof'", 
			"'alias'", "'async'", "'await'", "'break'", "'catch'", "'class'", "'false'", 
			"'final'", "'macro'", "'match'", "'mixin'", "'super'", "'throw'", "'trait'", 
			"'while'", "'yield'", "'case'", "'else'", "'enum'", "'func'", "'init'", 
			"'lazy'", "'null'", "'this'", "'true'", "'def'", "'for'", "'new'", "'try'", 
			"'as'", "'do'", "'if'", "'in'", "'is'", "'('", "')'", "'{'", "'}'", "'.'", 
			"','", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "ENTRYPOINT", "IMPLEMENTS", "INTERFACE", "NAMESPACE", 
			"PROTECTED", "ABSTRACT", "CONTINUE", "DELEGATE", "INTERNAL", "OVERRIDE", 
			"DEFAULT", "EXTENDS", "FINALLY", "PACKAGE", "PRIVATE", "EXPORT", "EXTEND", 
			"IMPORT", "MODULE", "NAMEOF", "PUBLIC", "RETURN", "SEALED", "STATIC", 
			"THROWS", "TYPEOF", "ALIAS", "ASYNC", "AWAIT", "BREAK", "CATCH", "CLASS", 
			"FALSE", "FINAL", "MACRO", "MATCH", "MIXIN", "SUPER", "THROW", "TRAIT", 
			"WHILE", "YIELD", "CASE", "ELSE", "ENUM", "FUNC", "INIT", "LAZY", "NULL", 
			"THIS", "TRUE", "DEF", "FOR", "NEW", "TRY", "AS", "DO", "IF", "IN", "IS", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "DOT", "COMMA", "SEMI", "StringLiteral", 
			"IntegerLiteral", "Identifier", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Karaffe.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public KaraffeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceFileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(KaraffeParser.EOF, 0); }
		public List<ClassDefContext> classDef() {
			return getRuleContexts(ClassDefContext.class);
		}
		public ClassDefContext classDef(int i) {
			return getRuleContext(ClassDefContext.class,i);
		}
		public SourceFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterSourceFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitSourceFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitSourceFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceFileContext sourceFile() throws RecognitionException {
		SourceFileContext _localctx = new SourceFileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sourceFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CLASS) {
				{
				{
				setState(34);
				classDef();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(40);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(KaraffeParser.CLASS, 0); }
		public TerminalNode Identifier() { return getToken(KaraffeParser.Identifier, 0); }
		public List<NlContext> nl() {
			return getRuleContexts(NlContext.class);
		}
		public NlContext nl(int i) {
			return getRuleContext(NlContext.class,i);
		}
		public TypeDefBodyContext typeDefBody() {
			return getRuleContext(TypeDefBodyContext.class,0);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(CLASS);
			setState(43);
			match(Identifier);
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(44);
				nl();
				}
				break;
			}
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(47);
				typeDefBody();
				}
			}

			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1 || _la==SEMI) {
				{
				setState(50);
				nl();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeDefBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(KaraffeParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(KaraffeParser.RBRACE, 0); }
		public List<NlContext> nl() {
			return getRuleContexts(NlContext.class);
		}
		public NlContext nl(int i) {
			return getRuleContext(NlContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TypeDefBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterTypeDefBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitTypeDefBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitTypeDefBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefBodyContext typeDefBody() throws RecognitionException {
		TypeDefBodyContext _localctx = new TypeDefBodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_typeDefBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(LBRACE);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1 || _la==SEMI) {
				{
				setState(54);
				nl();
				}
			}

			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9200291088764502008L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 7L) != 0)) {
				{
				{
				setState(57);
				statement();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
			match(RBRACE);
			setState(65);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(64);
				nl();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public EntryPointBlockContext entryPointBlock() {
			return getRuleContext(EntryPointBlockContext.class,0);
		}
		public NlContext nl() {
			return getRuleContext(NlContext.class,0);
		}
		public InitBlockContext initBlock() {
			return getRuleContext(InitBlockContext.class,0);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		int _la;
		try {
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				entryPointBlock();
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1 || _la==SEMI) {
					{
					setState(68);
					nl();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				initBlock();
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1 || _la==SEMI) {
					{
					setState(72);
					nl();
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				varDef();
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1 || _la==SEMI) {
					{
					setState(76);
					nl();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(79);
				assign();
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1 || _la==SEMI) {
					{
					setState(80);
					nl();
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(83);
				expr(0);
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1 || _la==SEMI) {
					{
					setState(84);
					nl();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EntryPointBlockContext extends ParserRuleContext {
		public TerminalNode ENTRYPOINT() { return getToken(KaraffeParser.ENTRYPOINT, 0); }
		public TerminalNode LBRACE() { return getToken(KaraffeParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(KaraffeParser.RBRACE, 0); }
		public List<NlContext> nl() {
			return getRuleContexts(NlContext.class);
		}
		public NlContext nl(int i) {
			return getRuleContext(NlContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public EntryPointBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entryPointBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterEntryPointBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitEntryPointBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitEntryPointBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntryPointBlockContext entryPointBlock() throws RecognitionException {
		EntryPointBlockContext _localctx = new EntryPointBlockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_entryPointBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(ENTRYPOINT);
			setState(90);
			match(LBRACE);
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1 || _la==SEMI) {
				{
				setState(91);
				nl();
				}
			}

			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9200291088764502008L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 7L) != 0)) {
				{
				{
				setState(94);
				statement();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			match(RBRACE);
			setState(102);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(101);
				nl();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InitBlockContext extends ParserRuleContext {
		public TerminalNode INIT() { return getToken(KaraffeParser.INIT, 0); }
		public TerminalNode LBRACE() { return getToken(KaraffeParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(KaraffeParser.RBRACE, 0); }
		public List<NlContext> nl() {
			return getRuleContexts(NlContext.class);
		}
		public NlContext nl(int i) {
			return getRuleContext(NlContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public InitBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterInitBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitInitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitInitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitBlockContext initBlock() throws RecognitionException {
		InitBlockContext _localctx = new InitBlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_initBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(INIT);
			setState(105);
			match(LBRACE);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1 || _la==SEMI) {
				{
				setState(106);
				nl();
				}
			}

			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9200291088764502008L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 7L) != 0)) {
				{
				{
				setState(109);
				statement();
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
			match(RBRACE);
			setState(117);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(116);
				nl();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public ExprContext target;
		public ExprContext initializer;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public NlContext nl() {
			return getRuleContext(NlContext.class,0);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			((AssignContext)_localctx).target = expr(0);
			setState(120);
			match(T__0);
			setState(121);
			((AssignContext)_localctx).initializer = expr(0);
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(122);
				nl();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDefContext extends ParserRuleContext {
		public ExprContext initializer;
		public TerminalNode DEF() { return getToken(KaraffeParser.DEF, 0); }
		public BindingContext binding() {
			return getRuleContext(BindingContext.class,0);
		}
		public NlContext nl() {
			return getRuleContext(NlContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(DEF);
			setState(126);
			binding();
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(127);
				match(T__0);
				setState(128);
				((VarDefContext)_localctx).initializer = expr(0);
				}
			}

			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(131);
				nl();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BindingContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(KaraffeParser.Identifier, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public NlContext nl() {
			return getRuleContext(NlContext.class,0);
		}
		public BindingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitBinding(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitBinding(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BindingContext binding() throws RecognitionException {
		BindingContext _localctx = new BindingContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_binding);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(Identifier);
			setState(135);
			typeName();
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(136);
				nl();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(KaraffeParser.Identifier, 0); }
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext function;
		public ExprContext left;
		public ExprContext target;
		public Token id;
		public LiteralContext lit;
		public Token t;
		public ExprContext inExpr;
		public ExprListContext args;
		public OpExprContext right;
		public Token name;
		public TerminalNode Identifier() { return getToken(KaraffeParser.Identifier, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode THIS() { return getToken(KaraffeParser.THIS, 0); }
		public TerminalNode LPAREN() { return getToken(KaraffeParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(KaraffeParser.RPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NlContext nl() {
			return getRuleContext(NlContext.class,0);
		}
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public List<OpExprContext> opExpr() {
			return getRuleContexts(OpExprContext.class);
		}
		public OpExprContext opExpr(int i) {
			return getRuleContext(OpExprContext.class,i);
		}
		public TerminalNode DOT() { return getToken(KaraffeParser.DOT, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				{
				setState(142);
				((ExprContext)_localctx).id = match(Identifier);
				}
				break;
			case StringLiteral:
			case IntegerLiteral:
				{
				setState(143);
				((ExprContext)_localctx).lit = literal();
				}
				break;
			case THIS:
				{
				setState(144);
				((ExprContext)_localctx).t = match(THIS);
				}
				break;
			case LPAREN:
				{
				setState(145);
				match(LPAREN);
				setState(146);
				((ExprContext)_localctx).inExpr = expr(0);
				setState(147);
				match(RPAREN);
				setState(149);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(148);
					nl();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(179);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(177);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.function = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(153);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(154);
						match(LPAREN);
						setState(156);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (((((_la - 52)) & ~0x3f) == 0 && ((1L << (_la - 52)) & 1837057L) != 0)) {
							{
							setState(155);
							((ExprContext)_localctx).args = exprList();
							}
						}

						setState(158);
						match(RPAREN);
						setState(160);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
						case 1:
							{
							setState(159);
							nl();
							}
							break;
						}
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(162);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(164); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(163);
								((ExprContext)_localctx).right = opExpr();
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(166); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						setState(169);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
						case 1:
							{
							setState(168);
							nl();
							}
							break;
						}
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.target = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(171);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(172);
						match(DOT);
						setState(173);
						((ExprContext)_localctx).name = match(Identifier);
						setState(175);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
						case 1:
							{
							setState(174);
							nl();
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(181);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OpExprContext extends ParserRuleContext {
		public BinaryOperatorContext op;
		public ExprContext right;
		public BinaryOperatorContext binaryOperator() {
			return getRuleContext(BinaryOperatorContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public OpExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterOpExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitOpExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpExprContext opExpr() throws RecognitionException {
		OpExprContext _localctx = new OpExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_opExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			((OpExprContext)_localctx).op = binaryOperator();
			setState(183);
			((OpExprContext)_localctx).right = expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BinaryOperatorContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(KaraffeParser.Identifier, 0); }
		public BinaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterBinaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitBinaryOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperatorContext binaryOperator() throws RecognitionException {
		BinaryOperatorContext _localctx = new BinaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_binaryOperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KaraffeParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KaraffeParser.COMMA, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitExprList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			expr(0);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(188);
				match(COMMA);
				setState(189);
				expr(0);
				}
				}
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode StringLiteral() { return getToken(KaraffeParser.StringLiteral, 0); }
		public TerminalNode IntegerLiteral() { return getToken(KaraffeParser.IntegerLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			_la = _input.LA(1);
			if ( !(_la==StringLiteral || _la==IntegerLiteral) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NlContext extends ParserRuleContext {
		public List<NewLineContext> newLine() {
			return getRuleContexts(NewLineContext.class);
		}
		public NewLineContext newLine(int i) {
			return getRuleContext(NewLineContext.class,i);
		}
		public NlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterNl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitNl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitNl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NlContext nl() throws RecognitionException {
		NlContext _localctx = new NlContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_nl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(198); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(197);
					newLine();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(200); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NewLineContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(KaraffeParser.SEMI, 0); }
		public NewLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterNewLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitNewLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitNewLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewLineContext newLine() throws RecognitionException {
		NewLineContext _localctx = new NewLineContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_newLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_la = _input.LA(1);
			if ( !(_la==T__1 || _la==SEMI) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001I\u00cd\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0001\u0000\u0005\u0000$\b\u0000\n\u0000\f\u0000"+
		"\'\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001.\b\u0001\u0001\u0001\u0003\u00011\b\u0001\u0001\u0001\u0003"+
		"\u00014\b\u0001\u0001\u0002\u0001\u0002\u0003\u00028\b\u0002\u0001\u0002"+
		"\u0005\u0002;\b\u0002\n\u0002\f\u0002>\t\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002B\b\u0002\u0001\u0003\u0001\u0003\u0003\u0003F\b\u0003\u0001"+
		"\u0003\u0001\u0003\u0003\u0003J\b\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003N\b\u0003\u0001\u0003\u0001\u0003\u0003\u0003R\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003V\b\u0003\u0003\u0003X\b\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0003\u0004]\b\u0004\u0001\u0004\u0005\u0004`\b\u0004"+
		"\n\u0004\f\u0004c\t\u0004\u0001\u0004\u0001\u0004\u0003\u0004g\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005l\b\u0005\u0001\u0005"+
		"\u0005\u0005o\b\u0005\n\u0005\f\u0005r\t\u0005\u0001\u0005\u0001\u0005"+
		"\u0003\u0005v\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006|\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u0082\b\u0007\u0001\u0007\u0003\u0007\u0085\b\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0003\b\u008a\b\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0096\b\n\u0003\n\u0098"+
		"\b\n\u0001\n\u0001\n\u0001\n\u0003\n\u009d\b\n\u0001\n\u0001\n\u0003\n"+
		"\u00a1\b\n\u0001\n\u0001\n\u0004\n\u00a5\b\n\u000b\n\f\n\u00a6\u0001\n"+
		"\u0003\n\u00aa\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00b0\b\n\u0005"+
		"\n\u00b2\b\n\n\n\f\n\u00b5\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0005\r\u00bf\b\r\n\r\f\r\u00c2\t\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0004\u000f\u00c7\b\u000f\u000b\u000f"+
		"\f\u000f\u00c8\u0001\u0010\u0001\u0010\u0001\u0010\u0000\u0001\u0014\u0011"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \u0000\u0002\u0001\u0000FG\u0002\u0000\u0002\u0002EE\u00e3"+
		"\u0000%\u0001\u0000\u0000\u0000\u0002*\u0001\u0000\u0000\u0000\u00045"+
		"\u0001\u0000\u0000\u0000\u0006W\u0001\u0000\u0000\u0000\bY\u0001\u0000"+
		"\u0000\u0000\nh\u0001\u0000\u0000\u0000\fw\u0001\u0000\u0000\u0000\u000e"+
		"}\u0001\u0000\u0000\u0000\u0010\u0086\u0001\u0000\u0000\u0000\u0012\u008b"+
		"\u0001\u0000\u0000\u0000\u0014\u0097\u0001\u0000\u0000\u0000\u0016\u00b6"+
		"\u0001\u0000\u0000\u0000\u0018\u00b9\u0001\u0000\u0000\u0000\u001a\u00bb"+
		"\u0001\u0000\u0000\u0000\u001c\u00c3\u0001\u0000\u0000\u0000\u001e\u00c6"+
		"\u0001\u0000\u0000\u0000 \u00ca\u0001\u0000\u0000\u0000\"$\u0003\u0002"+
		"\u0001\u0000#\"\u0001\u0000\u0000\u0000$\'\u0001\u0000\u0000\u0000%#\u0001"+
		"\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&(\u0001\u0000\u0000\u0000"+
		"\'%\u0001\u0000\u0000\u0000()\u0005\u0000\u0000\u0001)\u0001\u0001\u0000"+
		"\u0000\u0000*+\u0005\"\u0000\u0000+-\u0005H\u0000\u0000,.\u0003\u001e"+
		"\u000f\u0000-,\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000.0\u0001"+
		"\u0000\u0000\u0000/1\u0003\u0004\u0002\u00000/\u0001\u0000\u0000\u0000"+
		"01\u0001\u0000\u0000\u000013\u0001\u0000\u0000\u000024\u0003\u001e\u000f"+
		"\u000032\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u00004\u0003\u0001"+
		"\u0000\u0000\u000057\u0005A\u0000\u000068\u0003\u001e\u000f\u000076\u0001"+
		"\u0000\u0000\u000078\u0001\u0000\u0000\u00008<\u0001\u0000\u0000\u0000"+
		"9;\u0003\u0006\u0003\u0000:9\u0001\u0000\u0000\u0000;>\u0001\u0000\u0000"+
		"\u0000<:\u0001\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000=?\u0001\u0000"+
		"\u0000\u0000><\u0001\u0000\u0000\u0000?A\u0005B\u0000\u0000@B\u0003\u001e"+
		"\u000f\u0000A@\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000B\u0005"+
		"\u0001\u0000\u0000\u0000CE\u0003\b\u0004\u0000DF\u0003\u001e\u000f\u0000"+
		"ED\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000FX\u0001\u0000\u0000"+
		"\u0000GI\u0003\n\u0005\u0000HJ\u0003\u001e\u000f\u0000IH\u0001\u0000\u0000"+
		"\u0000IJ\u0001\u0000\u0000\u0000JX\u0001\u0000\u0000\u0000KM\u0003\u000e"+
		"\u0007\u0000LN\u0003\u001e\u000f\u0000ML\u0001\u0000\u0000\u0000MN\u0001"+
		"\u0000\u0000\u0000NX\u0001\u0000\u0000\u0000OQ\u0003\f\u0006\u0000PR\u0003"+
		"\u001e\u000f\u0000QP\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000"+
		"RX\u0001\u0000\u0000\u0000SU\u0003\u0014\n\u0000TV\u0003\u001e\u000f\u0000"+
		"UT\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VX\u0001\u0000\u0000"+
		"\u0000WC\u0001\u0000\u0000\u0000WG\u0001\u0000\u0000\u0000WK\u0001\u0000"+
		"\u0000\u0000WO\u0001\u0000\u0000\u0000WS\u0001\u0000\u0000\u0000X\u0007"+
		"\u0001\u0000\u0000\u0000YZ\u0005\u0003\u0000\u0000Z\\\u0005A\u0000\u0000"+
		"[]\u0003\u001e\u000f\u0000\\[\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000"+
		"\u0000]a\u0001\u0000\u0000\u0000^`\u0003\u0006\u0003\u0000_^\u0001\u0000"+
		"\u0000\u0000`c\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000ab\u0001"+
		"\u0000\u0000\u0000bd\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000"+
		"df\u0005B\u0000\u0000eg\u0003\u001e\u000f\u0000fe\u0001\u0000\u0000\u0000"+
		"fg\u0001\u0000\u0000\u0000g\t\u0001\u0000\u0000\u0000hi\u00051\u0000\u0000"+
		"ik\u0005A\u0000\u0000jl\u0003\u001e\u000f\u0000kj\u0001\u0000\u0000\u0000"+
		"kl\u0001\u0000\u0000\u0000lp\u0001\u0000\u0000\u0000mo\u0003\u0006\u0003"+
		"\u0000nm\u0001\u0000\u0000\u0000or\u0001\u0000\u0000\u0000pn\u0001\u0000"+
		"\u0000\u0000pq\u0001\u0000\u0000\u0000qs\u0001\u0000\u0000\u0000rp\u0001"+
		"\u0000\u0000\u0000su\u0005B\u0000\u0000tv\u0003\u001e\u000f\u0000ut\u0001"+
		"\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000v\u000b\u0001\u0000\u0000"+
		"\u0000wx\u0003\u0014\n\u0000xy\u0005\u0001\u0000\u0000y{\u0003\u0014\n"+
		"\u0000z|\u0003\u001e\u000f\u0000{z\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|\r\u0001\u0000\u0000\u0000}~\u00056\u0000\u0000~\u0081\u0003"+
		"\u0010\b\u0000\u007f\u0080\u0005\u0001\u0000\u0000\u0080\u0082\u0003\u0014"+
		"\n\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000"+
		"\u0000\u0082\u0084\u0001\u0000\u0000\u0000\u0083\u0085\u0003\u001e\u000f"+
		"\u0000\u0084\u0083\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000"+
		"\u0000\u0085\u000f\u0001\u0000\u0000\u0000\u0086\u0087\u0005H\u0000\u0000"+
		"\u0087\u0089\u0003\u0012\t\u0000\u0088\u008a\u0003\u001e\u000f\u0000\u0089"+
		"\u0088\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a"+
		"\u0011\u0001\u0000\u0000\u0000\u008b\u008c\u0005H\u0000\u0000\u008c\u0013"+
		"\u0001\u0000\u0000\u0000\u008d\u008e\u0006\n\uffff\uffff\u0000\u008e\u0098"+
		"\u0005H\u0000\u0000\u008f\u0098\u0003\u001c\u000e\u0000\u0090\u0098\u0005"+
		"4\u0000\u0000\u0091\u0092\u0005?\u0000\u0000\u0092\u0093\u0003\u0014\n"+
		"\u0000\u0093\u0095\u0005@\u0000\u0000\u0094\u0096\u0003\u001e\u000f\u0000"+
		"\u0095\u0094\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000"+
		"\u0096\u0098\u0001\u0000\u0000\u0000\u0097\u008d\u0001\u0000\u0000\u0000"+
		"\u0097\u008f\u0001\u0000\u0000\u0000\u0097\u0090\u0001\u0000\u0000\u0000"+
		"\u0097\u0091\u0001\u0000\u0000\u0000\u0098\u00b3\u0001\u0000\u0000\u0000"+
		"\u0099\u009a\n\u0004\u0000\u0000\u009a\u009c\u0005?\u0000\u0000\u009b"+
		"\u009d\u0003\u001a\r\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009c\u009d"+
		"\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u00a0"+
		"\u0005@\u0000\u0000\u009f\u00a1\u0003\u001e\u000f\u0000\u00a0\u009f\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00b2\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a4\n\u0003\u0000\u0000\u00a3\u00a5\u0003\u0016"+
		"\u000b\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a9\u0001\u0000\u0000\u0000\u00a8\u00aa\u0003\u001e"+
		"\u000f\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000"+
		"\u0000\u0000\u00aa\u00b2\u0001\u0000\u0000\u0000\u00ab\u00ac\n\u0002\u0000"+
		"\u0000\u00ac\u00ad\u0005C\u0000\u0000\u00ad\u00af\u0005H\u0000\u0000\u00ae"+
		"\u00b0\u0003\u001e\u000f\u0000\u00af\u00ae\u0001\u0000\u0000\u0000\u00af"+
		"\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b2\u0001\u0000\u0000\u0000\u00b1"+
		"\u0099\u0001\u0000\u0000\u0000\u00b1\u00a2\u0001\u0000\u0000\u0000\u00b1"+
		"\u00ab\u0001\u0000\u0000\u0000\u00b2\u00b5\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4"+
		"\u0015\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b6"+
		"\u00b7\u0003\u0018\f\u0000\u00b7\u00b8\u0003\u0014\n\u0000\u00b8\u0017"+
		"\u0001\u0000\u0000\u0000\u00b9\u00ba\u0005H\u0000\u0000\u00ba\u0019\u0001"+
		"\u0000\u0000\u0000\u00bb\u00c0\u0003\u0014\n\u0000\u00bc\u00bd\u0005D"+
		"\u0000\u0000\u00bd\u00bf\u0003\u0014\n\u0000\u00be\u00bc\u0001\u0000\u0000"+
		"\u0000\u00bf\u00c2\u0001\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u001b\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u00c4\u0007\u0000\u0000"+
		"\u0000\u00c4\u001d\u0001\u0000\u0000\u0000\u00c5\u00c7\u0003 \u0010\u0000"+
		"\u00c6\u00c5\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000"+
		"\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000"+
		"\u00c9\u001f\u0001\u0000\u0000\u0000\u00ca\u00cb\u0007\u0001\u0000\u0000"+
		"\u00cb!\u0001\u0000\u0000\u0000\"%-037<AEIMQUW\\afkpu{\u0081\u0084\u0089"+
		"\u0095\u0097\u009c\u00a0\u00a6\u00a9\u00af\u00b1\u00b3\u00c0\u00c8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
