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
		ENTRYPOINT=1, CLASS=2, PRINT=3, INIT=4, THIS=5, DEF=6, LPAREN=7, RPAREN=8, 
		LBRACE=9, RBRACE=10, DOT=11, StringLiteral=12, IntegerLiteral=13, Identifier=14, 
		WS=15;
	public static final int
		RULE_sourceFile = 0, RULE_classDef = 1, RULE_typeDefBody = 2, RULE_statement = 3, 
		RULE_entryPointBlock = 4, RULE_initBlock = 5, RULE_varDef = 6, RULE_expr = 7, 
		RULE_binaryOperator = 8, RULE_literal = 9, RULE_printFunction = 10;
	public static final String[] ruleNames = {
		"sourceFile", "classDef", "typeDefBody", "statement", "entryPointBlock", 
		"initBlock", "varDef", "expr", "binaryOperator", "literal", "printFunction"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'entrypoint'", "'class'", "'print'", "'init'", "'this'", "'def'", 
		"'('", "')'", "'{'", "'}'", "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ENTRYPOINT", "CLASS", "PRINT", "INIT", "THIS", "DEF", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "DOT", "StringLiteral", "IntegerLiteral", 
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
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CLASS) {
				{
				{
				setState(22);
				classDef();
				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(28);
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
			setState(30);
			match(CLASS);
			setState(31);
			match(Identifier);
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(32);
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
			setState(35);
			match(LBRACE);
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ENTRYPOINT) | (1L << PRINT) | (1L << INIT) | (1L << THIS) | (1L << DEF) | (1L << StringLiteral) | (1L << IntegerLiteral))) != 0)) {
				{
				{
				setState(36);
				statement();
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(42);
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
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ENTRYPOINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				entryPointBlock();
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				printFunction();
				}
				break;
			case INIT:
				enterOuterAlt(_localctx, 3);
				{
				setState(46);
				initBlock();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 4);
				{
				setState(47);
				varDef();
				}
				break;
			case THIS:
			case StringLiteral:
			case IntegerLiteral:
				enterOuterAlt(_localctx, 5);
				{
				setState(48);
				expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
			setState(51);
			match(ENTRYPOINT);
			setState(52);
			match(LBRACE);
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ENTRYPOINT) | (1L << PRINT) | (1L << INIT) | (1L << THIS) | (1L << DEF) | (1L << StringLiteral) | (1L << IntegerLiteral))) != 0)) {
				{
				{
				setState(53);
				statement();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
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
			setState(61);
			match(INIT);
			setState(62);
			match(LBRACE);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ENTRYPOINT) | (1L << PRINT) | (1L << INIT) | (1L << THIS) | (1L << DEF) | (1L << StringLiteral) | (1L << IntegerLiteral))) != 0)) {
				{
				{
				setState(63);
				statement();
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(69);
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
		enterRule(_localctx, 12, RULE_varDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(DEF);
			setState(72);
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
		public ExprContext left;
		public LiteralContext lit;
		public Token t;
		public BinaryOperatorContext op;
		public ExprContext right;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode THIS() { return getToken(KaraffeParser.THIS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BinaryOperatorContext binaryOperator() {
			return getRuleContext(BinaryOperatorContext.class,0);
		}
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
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case StringLiteral:
			case IntegerLiteral:
				{
				setState(75);
				((ExprContext)_localctx).lit = literal();
				}
				break;
			case THIS:
				{
				setState(76);
				((ExprContext)_localctx).t = match(THIS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(85);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					_localctx.left = _prevctx;
					_localctx.left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(79);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(80);
					((ExprContext)_localctx).op = binaryOperator();
					setState(81);
					((ExprContext)_localctx).right = expr(2);
					}
					} 
				}
				setState(87);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
		enterRule(_localctx, 16, RULE_binaryOperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
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
		enterRule(_localctx, 18, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
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
		enterRule(_localctx, 20, RULE_printFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(PRINT);
			setState(93);
			match(LPAREN);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << THIS) | (1L << StringLiteral) | (1L << IntegerLiteral))) != 0)) {
				{
				setState(94);
				((PrintFunctionContext)_localctx).body = expr(0);
				}
			}

			setState(97);
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
		case 7:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21f\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\3\2\3\3\3\3\3\3\5\3$\n\3\3"+
		"\4\3\4\7\4(\n\4\f\4\16\4+\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\5\5\64\n\5"+
		"\3\6\3\6\3\6\7\69\n\6\f\6\16\6<\13\6\3\6\3\6\3\7\3\7\3\7\7\7C\n\7\f\7"+
		"\16\7F\13\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\5\tP\n\t\3\t\3\t\3\t\3\t\7"+
		"\tV\n\t\f\t\16\tY\13\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\5\fb\n\f\3\f\3\f"+
		"\3\f\2\3\20\r\2\4\6\b\n\f\16\20\22\24\26\2\3\3\2\16\17\2f\2\33\3\2\2\2"+
		"\4 \3\2\2\2\6%\3\2\2\2\b\63\3\2\2\2\n\65\3\2\2\2\f?\3\2\2\2\16I\3\2\2"+
		"\2\20O\3\2\2\2\22Z\3\2\2\2\24\\\3\2\2\2\26^\3\2\2\2\30\32\5\4\3\2\31\30"+
		"\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\36\3\2\2\2\35\33"+
		"\3\2\2\2\36\37\7\2\2\3\37\3\3\2\2\2 !\7\4\2\2!#\7\20\2\2\"$\5\6\4\2#\""+
		"\3\2\2\2#$\3\2\2\2$\5\3\2\2\2%)\7\13\2\2&(\5\b\5\2\'&\3\2\2\2(+\3\2\2"+
		"\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2+)\3\2\2\2,-\7\f\2\2-\7\3\2\2\2.\64\5"+
		"\n\6\2/\64\5\26\f\2\60\64\5\f\7\2\61\64\5\16\b\2\62\64\5\20\t\2\63.\3"+
		"\2\2\2\63/\3\2\2\2\63\60\3\2\2\2\63\61\3\2\2\2\63\62\3\2\2\2\64\t\3\2"+
		"\2\2\65\66\7\3\2\2\66:\7\13\2\2\679\5\b\5\28\67\3\2\2\29<\3\2\2\2:8\3"+
		"\2\2\2:;\3\2\2\2;=\3\2\2\2<:\3\2\2\2=>\7\f\2\2>\13\3\2\2\2?@\7\6\2\2@"+
		"D\7\13\2\2AC\5\b\5\2BA\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2EG\3\2\2\2"+
		"FD\3\2\2\2GH\7\f\2\2H\r\3\2\2\2IJ\7\b\2\2JK\7\20\2\2K\17\3\2\2\2LM\b\t"+
		"\1\2MP\5\24\13\2NP\7\7\2\2OL\3\2\2\2ON\3\2\2\2PW\3\2\2\2QR\f\3\2\2RS\5"+
		"\22\n\2ST\5\20\t\4TV\3\2\2\2UQ\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X"+
		"\21\3\2\2\2YW\3\2\2\2Z[\7\20\2\2[\23\3\2\2\2\\]\t\2\2\2]\25\3\2\2\2^_"+
		"\7\5\2\2_a\7\t\2\2`b\5\20\t\2a`\3\2\2\2ab\3\2\2\2bc\3\2\2\2cd\7\n\2\2"+
		"d\27\3\2\2\2\13\33#)\63:DOWa";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
