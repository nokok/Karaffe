// Generated from Karaffe.g4 by ANTLR 4.7.1
package org.karaffe.compiler.frontend.karaffe.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KaraffeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, ENTRYPOINT=2, CLASS=3, PRINT=4, INIT=5, THIS=6, DEF=7, LPAREN=8, 
		RPAREN=9, LBRACE=10, RBRACE=11, DOT=12, COMMA=13, StringLiteral=14, IntegerLiteral=15, 
		Identifier=16, WS=17;
	public static final int
		RULE_sourceFile = 0, RULE_classDef = 1, RULE_typeDefBody = 2, RULE_statement = 3, 
		RULE_entryPointBlock = 4, RULE_initBlock = 5, RULE_assign = 6, RULE_varDef = 7, 
		RULE_expr = 8, RULE_opExpr = 9, RULE_binaryOperator = 10, RULE_exprList = 11, 
		RULE_atom = 12, RULE_literal = 13, RULE_printFunction = 14;
	public static final String[] ruleNames = {
		"sourceFile", "classDef", "typeDefBody", "statement", "entryPointBlock", 
		"initBlock", "assign", "varDef", "expr", "opExpr", "binaryOperator", "exprList", 
		"atom", "literal", "printFunction"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':='", "'entrypoint'", "'class'", "'print'", "'init'", "'this'", 
		"'def'", "'('", "')'", "'{'", "'}'", "'.'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "ENTRYPOINT", "CLASS", "PRINT", "INIT", "THIS", "DEF", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "DOT", "COMMA", "StringLiteral", "IntegerLiteral", 
		"Identifier", "WS"
	};
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
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CLASS) {
				{
				{
				setState(30);
				classDef();
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36);
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

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(KaraffeParser.CLASS, 0); }
		public TerminalNode Identifier() { return getToken(KaraffeParser.Identifier, 0); }
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
			setState(38);
			match(CLASS);
			setState(39);
			match(Identifier);
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(40);
				typeDefBody();
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

	public static class TypeDefBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(KaraffeParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(KaraffeParser.RBRACE, 0); }
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
			setState(43);
			match(LBRACE);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ENTRYPOINT) | (1L << PRINT) | (1L << INIT) | (1L << THIS) | (1L << DEF) | (1L << LPAREN) | (1L << StringLiteral) | (1L << IntegerLiteral))) != 0)) {
				{
				{
				setState(44);
				statement();
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
			match(RBRACE);
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

	public static class StatementContext extends ParserRuleContext {
		public EntryPointBlockContext entryPointBlock() {
			return getRuleContext(EntryPointBlockContext.class,0);
		}
		public PrintFunctionContext printFunction() {
			return getRuleContext(PrintFunctionContext.class,0);
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
		try {
			setState(58);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				entryPointBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				printFunction();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
				initBlock();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(55);
				varDef();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(56);
				assign();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(57);
				expr(0);
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

	public static class EntryPointBlockContext extends ParserRuleContext {
		public TerminalNode ENTRYPOINT() { return getToken(KaraffeParser.ENTRYPOINT, 0); }
		public TerminalNode LBRACE() { return getToken(KaraffeParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(KaraffeParser.RBRACE, 0); }
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
			setState(60);
			match(ENTRYPOINT);
			setState(61);
			match(LBRACE);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ENTRYPOINT) | (1L << PRINT) | (1L << INIT) | (1L << THIS) | (1L << DEF) | (1L << LPAREN) | (1L << StringLiteral) | (1L << IntegerLiteral))) != 0)) {
				{
				{
				setState(62);
				statement();
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(68);
			match(RBRACE);
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

	public static class InitBlockContext extends ParserRuleContext {
		public TerminalNode INIT() { return getToken(KaraffeParser.INIT, 0); }
		public TerminalNode LBRACE() { return getToken(KaraffeParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(KaraffeParser.RBRACE, 0); }
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
			setState(70);
			match(INIT);
			setState(71);
			match(LBRACE);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ENTRYPOINT) | (1L << PRINT) | (1L << INIT) | (1L << THIS) | (1L << DEF) | (1L << LPAREN) | (1L << StringLiteral) | (1L << IntegerLiteral))) != 0)) {
				{
				{
				setState(72);
				statement();
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(78);
			match(RBRACE);
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

	public static class AssignContext extends ParserRuleContext {
		public ExprContext target;
		public ExprContext initializer;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
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
			setState(80);
			((AssignContext)_localctx).target = expr(0);
			setState(81);
			match(T__0);
			setState(82);
			((AssignContext)_localctx).initializer = expr(0);
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

	public static class VarDefContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(KaraffeParser.DEF, 0); }
		public TerminalNode Identifier() { return getToken(KaraffeParser.Identifier, 0); }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(DEF);
			setState(85);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext function;
		public ExprContext left;
		public ExprContext target;
		public LiteralContext lit;
		public Token t;
		public ExprContext inExpr;
		public ExprListContext args;
		public OpExprContext right;
		public Token name;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode THIS() { return getToken(KaraffeParser.THIS, 0); }
		public TerminalNode LPAREN() { return getToken(KaraffeParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(KaraffeParser.RPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		public TerminalNode Identifier() { return getToken(KaraffeParser.Identifier, 0); }
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
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case StringLiteral:
			case IntegerLiteral:
				{
				setState(88);
				((ExprContext)_localctx).lit = literal();
				}
				break;
			case THIS:
				{
				setState(89);
				((ExprContext)_localctx).t = match(THIS);
				}
				break;
			case LPAREN:
				{
				setState(90);
				match(LPAREN);
				setState(91);
				((ExprContext)_localctx).inExpr = expr(0);
				setState(92);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(113);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(111);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.function = _prevctx;
						_localctx.function = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(96);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(97);
						match(LPAREN);
						setState(99);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << THIS) | (1L << LPAREN) | (1L << StringLiteral) | (1L << IntegerLiteral))) != 0)) {
							{
							setState(98);
							((ExprContext)_localctx).args = exprList();
							}
						}

						setState(101);
						match(RPAREN);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(102);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(104); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(103);
								((ExprContext)_localctx).right = opExpr();
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(106); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.target = _prevctx;
						_localctx.target = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(108);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(109);
						match(DOT);
						setState(110);
						((ExprContext)_localctx).name = match(Identifier);
						}
						break;
					}
					} 
				}
				setState(115);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
		enterRule(_localctx, 18, RULE_opExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			((OpExprContext)_localctx).op = binaryOperator();
			setState(117);
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
		enterRule(_localctx, 20, RULE_binaryOperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
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
		enterRule(_localctx, 22, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			expr(0);
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(122);
				match(COMMA);
				setState(123);
				expr(0);
				}
				}
				setState(128);
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

	public static class AtomContext extends ParserRuleContext {
		public LiteralContext lit;
		public Token t;
		public Token idt;
		public Token id;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode THIS() { return getToken(KaraffeParser.THIS, 0); }
		public TerminalNode DOT() { return getToken(KaraffeParser.DOT, 0); }
		public TerminalNode Identifier() { return getToken(KaraffeParser.Identifier, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_atom);
		try {
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				((AtomContext)_localctx).lit = literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				((AtomContext)_localctx).t = match(THIS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				((AtomContext)_localctx).idt = match(Identifier);
				setState(132);
				match(DOT);
				setState(133);
				((AtomContext)_localctx).t = match(THIS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(134);
				((AtomContext)_localctx).id = match(Identifier);
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
		enterRule(_localctx, 26, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
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

	public static class PrintFunctionContext extends ParserRuleContext {
		public ExprContext body;
		public TerminalNode PRINT() { return getToken(KaraffeParser.PRINT, 0); }
		public TerminalNode LPAREN() { return getToken(KaraffeParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(KaraffeParser.RPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).enterPrintFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaraffeListener ) ((KaraffeListener)listener).exitPrintFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KaraffeVisitor ) return ((KaraffeVisitor<? extends T>)visitor).visitPrintFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintFunctionContext printFunction() throws RecognitionException {
		PrintFunctionContext _localctx = new PrintFunctionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_printFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(PRINT);
			setState(140);
			match(LPAREN);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << THIS) | (1L << LPAREN) | (1L << StringLiteral) | (1L << IntegerLiteral))) != 0)) {
				{
				setState(141);
				((PrintFunctionContext)_localctx).body = expr(0);
				}
			}

			setState(144);
			match(RPAREN);
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
		case 8:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\23\u0095\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\7\2\"\n\2\f\2"+
		"\16\2%\13\2\3\2\3\2\3\3\3\3\3\3\5\3,\n\3\3\4\3\4\7\4\60\n\4\f\4\16\4\63"+
		"\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5=\n\5\3\6\3\6\3\6\7\6B\n\6\f"+
		"\6\16\6E\13\6\3\6\3\6\3\7\3\7\3\7\7\7L\n\7\f\7\16\7O\13\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\na\n\n\3\n\3\n"+
		"\3\n\5\nf\n\n\3\n\3\n\3\n\6\nk\n\n\r\n\16\nl\3\n\3\n\3\n\7\nr\n\n\f\n"+
		"\16\nu\13\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\7\r\177\n\r\f\r\16\r\u0082"+
		"\13\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u008a\n\16\3\17\3\17\3\20\3\20"+
		"\3\20\5\20\u0091\n\20\3\20\3\20\3\20\2\3\22\21\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36\2\3\3\2\20\21\2\u009b\2#\3\2\2\2\4(\3\2\2\2\6-\3\2\2\2"+
		"\b<\3\2\2\2\n>\3\2\2\2\fH\3\2\2\2\16R\3\2\2\2\20V\3\2\2\2\22`\3\2\2\2"+
		"\24v\3\2\2\2\26y\3\2\2\2\30{\3\2\2\2\32\u0089\3\2\2\2\34\u008b\3\2\2\2"+
		"\36\u008d\3\2\2\2 \"\5\4\3\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2"+
		"$&\3\2\2\2%#\3\2\2\2&\'\7\2\2\3\'\3\3\2\2\2()\7\5\2\2)+\7\22\2\2*,\5\6"+
		"\4\2+*\3\2\2\2+,\3\2\2\2,\5\3\2\2\2-\61\7\f\2\2.\60\5\b\5\2/.\3\2\2\2"+
		"\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64"+
		"\65\7\r\2\2\65\7\3\2\2\2\66=\5\n\6\2\67=\5\36\20\28=\5\f\7\29=\5\20\t"+
		"\2:=\5\16\b\2;=\5\22\n\2<\66\3\2\2\2<\67\3\2\2\2<8\3\2\2\2<9\3\2\2\2<"+
		":\3\2\2\2<;\3\2\2\2=\t\3\2\2\2>?\7\4\2\2?C\7\f\2\2@B\5\b\5\2A@\3\2\2\2"+
		"BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FG\7\r\2\2G\13\3\2\2"+
		"\2HI\7\7\2\2IM\7\f\2\2JL\5\b\5\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2"+
		"\2NP\3\2\2\2OM\3\2\2\2PQ\7\r\2\2Q\r\3\2\2\2RS\5\22\n\2ST\7\3\2\2TU\5\22"+
		"\n\2U\17\3\2\2\2VW\7\t\2\2WX\7\22\2\2X\21\3\2\2\2YZ\b\n\1\2Za\5\34\17"+
		"\2[a\7\b\2\2\\]\7\n\2\2]^\5\22\n\2^_\7\13\2\2_a\3\2\2\2`Y\3\2\2\2`[\3"+
		"\2\2\2`\\\3\2\2\2as\3\2\2\2bc\f\6\2\2ce\7\n\2\2df\5\30\r\2ed\3\2\2\2e"+
		"f\3\2\2\2fg\3\2\2\2gr\7\13\2\2hj\f\5\2\2ik\5\24\13\2ji\3\2\2\2kl\3\2\2"+
		"\2lj\3\2\2\2lm\3\2\2\2mr\3\2\2\2no\f\4\2\2op\7\16\2\2pr\7\22\2\2qb\3\2"+
		"\2\2qh\3\2\2\2qn\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2t\23\3\2\2\2us\3"+
		"\2\2\2vw\5\26\f\2wx\5\22\n\2x\25\3\2\2\2yz\7\22\2\2z\27\3\2\2\2{\u0080"+
		"\5\22\n\2|}\7\17\2\2}\177\5\22\n\2~|\3\2\2\2\177\u0082\3\2\2\2\u0080~"+
		"\3\2\2\2\u0080\u0081\3\2\2\2\u0081\31\3\2\2\2\u0082\u0080\3\2\2\2\u0083"+
		"\u008a\5\34\17\2\u0084\u008a\7\b\2\2\u0085\u0086\7\22\2\2\u0086\u0087"+
		"\7\16\2\2\u0087\u008a\7\b\2\2\u0088\u008a\7\22\2\2\u0089\u0083\3\2\2\2"+
		"\u0089\u0084\3\2\2\2\u0089\u0085\3\2\2\2\u0089\u0088\3\2\2\2\u008a\33"+
		"\3\2\2\2\u008b\u008c\t\2\2\2\u008c\35\3\2\2\2\u008d\u008e\7\6\2\2\u008e"+
		"\u0090\7\n\2\2\u008f\u0091\5\22\n\2\u0090\u008f\3\2\2\2\u0090\u0091\3"+
		"\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\7\13\2\2\u0093\37\3\2\2\2\20#+"+
		"\61<CM`elqs\u0080\u0089\u0090";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
