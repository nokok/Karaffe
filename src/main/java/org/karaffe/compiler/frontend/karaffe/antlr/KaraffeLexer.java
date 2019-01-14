// Generated from Karaffe.g4 by ANTLR 4.7.1
package org.karaffe.compiler.frontend.karaffe.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KaraffeLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, ENTRYPOINT=2, IMPLEMENTS=3, INTERFACE=4, PROTECTED=5, ABSTRACT=6, 
		CONTINUE=7, DELEGATE=8, INTERNAL=9, OVERRIDE=10, DEFAULT=11, EXTENDS=12, 
		FINALLY=13, PACKAGE=14, PRIVATE=15, EXPORT=16, EXTEND=17, IMPORT=18, MODULE=19, 
		NAMEOF=20, PUBLIC=21, RETURN=22, SEALED=23, STATIC=24, THROWS=25, TYPEOF=26, 
		ALIAS=27, ASYNC=28, AWAIT=29, BREAK=30, CATCH=31, CLASS=32, FALSE=33, 
		FINAL=34, MACRO=35, MATCH=36, MIXIN=37, SUPER=38, THROW=39, TRAIT=40, 
		WHILE=41, YIELD=42, CASE=43, ELSE=44, ENUM=45, FUNC=46, INIT=47, LAZY=48, 
		NULL=49, THIS=50, TRUE=51, DEF=52, FOR=53, NEW=54, TRY=55, AS=56, DO=57, 
		IF=58, IN=59, IS=60, LPAREN=61, RPAREN=62, LBRACE=63, RBRACE=64, DOT=65, 
		COMMA=66, StringLiteral=67, IntegerLiteral=68, Identifier=69, WS=70;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "ENTRYPOINT", "IMPLEMENTS", "INTERFACE", "PROTECTED", "ABSTRACT", 
		"CONTINUE", "DELEGATE", "INTERNAL", "OVERRIDE", "DEFAULT", "EXTENDS", 
		"FINALLY", "PACKAGE", "PRIVATE", "EXPORT", "EXTEND", "IMPORT", "MODULE", 
		"NAMEOF", "PUBLIC", "RETURN", "SEALED", "STATIC", "THROWS", "TYPEOF", 
		"ALIAS", "ASYNC", "AWAIT", "BREAK", "CATCH", "CLASS", "FALSE", "FINAL", 
		"MACRO", "MATCH", "MIXIN", "SUPER", "THROW", "TRAIT", "WHILE", "YIELD", 
		"CASE", "ELSE", "ENUM", "FUNC", "INIT", "LAZY", "NULL", "THIS", "TRUE", 
		"DEF", "FOR", "NEW", "TRY", "AS", "DO", "IF", "IN", "IS", "LPAREN", "RPAREN", 
		"LBRACE", "RBRACE", "DOT", "COMMA", "StringLiteral", "IntegerLiteral", 
		"StringChar", "Identifier", "OperatorChar", "Letter", "LetterOrDigit", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'entrypoint'", "'implements'", "'interface'", "'protected'", 
		"'abstract'", "'continue'", "'delegate'", "'internal'", "'override'", 
		"'default'", "'extends'", "'finally'", "'package'", "'private'", "'export'", 
		"'extend'", "'import'", "'module'", "'nameof'", "'public'", "'return'", 
		"'sealed'", "'static'", "'throws'", "'typeof'", "'alias'", "'async'", 
		"'await'", "'break'", "'catch'", "'class'", "'false'", "'final'", "'macro'", 
		"'match'", "'mixin'", "'super'", "'throw'", "'trait'", "'while'", "'yield'", 
		"'case'", "'else'", "'enum'", "'func'", "'init'", "'lazy'", "'null'", 
		"'this'", "'true'", "'def'", "'for'", "'new'", "'try'", "'as'", "'do'", 
		"'if'", "'in'", "'is'", "'('", "')'", "'{'", "'}'", "'.'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "ENTRYPOINT", "IMPLEMENTS", "INTERFACE", "PROTECTED", "ABSTRACT", 
		"CONTINUE", "DELEGATE", "INTERNAL", "OVERRIDE", "DEFAULT", "EXTENDS", 
		"FINALLY", "PACKAGE", "PRIVATE", "EXPORT", "EXTEND", "IMPORT", "MODULE", 
		"NAMEOF", "PUBLIC", "RETURN", "SEALED", "STATIC", "THROWS", "TYPEOF", 
		"ALIAS", "ASYNC", "AWAIT", "BREAK", "CATCH", "CLASS", "FALSE", "FINAL", 
		"MACRO", "MATCH", "MIXIN", "SUPER", "THROW", "TRAIT", "WHILE", "YIELD", 
		"CASE", "ELSE", "ENUM", "FUNC", "INIT", "LAZY", "NULL", "THIS", "TRUE", 
		"DEF", "FOR", "NEW", "TRY", "AS", "DO", "IF", "IN", "IS", "LPAREN", "RPAREN", 
		"LBRACE", "RBRACE", "DOT", "COMMA", "StringLiteral", "IntegerLiteral", 
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


	public KaraffeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Karaffe.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2H\u025b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3"+
		"&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3"+
		")\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3"+
		"-\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61"+
		"\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\64\3\64"+
		"\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67\3\67"+
		"\3\67\38\38\38\38\39\39\39\3:\3:\3:\3;\3;\3;\3<\3<\3<\3=\3=\3=\3>\3>\3"+
		"?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3D\3D\7D\u0220\nD\fD\16D\u0223\13D\3D\3D"+
		"\3E\3E\7E\u0229\nE\fE\16E\u022c\13E\3E\5E\u022f\nE\3F\3F\3G\3G\7G\u0235"+
		"\nG\fG\16G\u0238\13G\3G\6G\u023b\nG\rG\16G\u023c\3G\3G\3G\3G\3G\3G\5G"+
		"\u0245\nG\3H\3H\3I\3I\3J\3J\3J\7J\u024e\nJ\fJ\16J\u0251\13J\5J\u0253\n"+
		"J\3K\6K\u0256\nK\rK\16K\u0257\3K\3K\2\2L\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W"+
		"-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083"+
		"C\u0085D\u0087E\u0089F\u008b\2\u008dG\u008f\2\u0091\2\u0093\2\u0095H\3"+
		"\2\b\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\16\2##%%\'(,-//\61\61<<>@BB``~"+
		"~\u0080\u0080\4\2C\\c|\5\2\13\f\17\17\"\"\2\u0261\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2"+
		"\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2"+
		"\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3"+
		"\2\2\2\2\u0089\3\2\2\2\2\u008d\3\2\2\2\2\u0095\3\2\2\2\3\u0097\3\2\2\2"+
		"\5\u0099\3\2\2\2\7\u00a4\3\2\2\2\t\u00af\3\2\2\2\13\u00b9\3\2\2\2\r\u00c3"+
		"\3\2\2\2\17\u00cc\3\2\2\2\21\u00d5\3\2\2\2\23\u00de\3\2\2\2\25\u00e7\3"+
		"\2\2\2\27\u00f0\3\2\2\2\31\u00f8\3\2\2\2\33\u0100\3\2\2\2\35\u0108\3\2"+
		"\2\2\37\u0110\3\2\2\2!\u0118\3\2\2\2#\u011f\3\2\2\2%\u0126\3\2\2\2\'\u012d"+
		"\3\2\2\2)\u0134\3\2\2\2+\u013b\3\2\2\2-\u0142\3\2\2\2/\u0149\3\2\2\2\61"+
		"\u0150\3\2\2\2\63\u0157\3\2\2\2\65\u015e\3\2\2\2\67\u0165\3\2\2\29\u016b"+
		"\3\2\2\2;\u0171\3\2\2\2=\u0177\3\2\2\2?\u017d\3\2\2\2A\u0183\3\2\2\2C"+
		"\u0189\3\2\2\2E\u018f\3\2\2\2G\u0195\3\2\2\2I\u019b\3\2\2\2K\u01a1\3\2"+
		"\2\2M\u01a7\3\2\2\2O\u01ad\3\2\2\2Q\u01b3\3\2\2\2S\u01b9\3\2\2\2U\u01bf"+
		"\3\2\2\2W\u01c5\3\2\2\2Y\u01ca\3\2\2\2[\u01cf\3\2\2\2]\u01d4\3\2\2\2_"+
		"\u01d9\3\2\2\2a\u01de\3\2\2\2c\u01e3\3\2\2\2e\u01e8\3\2\2\2g\u01ed\3\2"+
		"\2\2i\u01f2\3\2\2\2k\u01f6\3\2\2\2m\u01fa\3\2\2\2o\u01fe\3\2\2\2q\u0202"+
		"\3\2\2\2s\u0205\3\2\2\2u\u0208\3\2\2\2w\u020b\3\2\2\2y\u020e\3\2\2\2{"+
		"\u0211\3\2\2\2}\u0213\3\2\2\2\177\u0215\3\2\2\2\u0081\u0217\3\2\2\2\u0083"+
		"\u0219\3\2\2\2\u0085\u021b\3\2\2\2\u0087\u021d\3\2\2\2\u0089\u022e\3\2"+
		"\2\2\u008b\u0230\3\2\2\2\u008d\u0244\3\2\2\2\u008f\u0246\3\2\2\2\u0091"+
		"\u0248\3\2\2\2\u0093\u0252\3\2\2\2\u0095\u0255\3\2\2\2\u0097\u0098\7?"+
		"\2\2\u0098\4\3\2\2\2\u0099\u009a\7g\2\2\u009a\u009b\7p\2\2\u009b\u009c"+
		"\7v\2\2\u009c\u009d\7t\2\2\u009d\u009e\7{\2\2\u009e\u009f\7r\2\2\u009f"+
		"\u00a0\7q\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7v\2\2"+
		"\u00a3\6\3\2\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6\7o\2\2\u00a6\u00a7\7r"+
		"\2\2\u00a7\u00a8\7n\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7o\2\2\u00aa\u00ab"+
		"\7g\2\2\u00ab\u00ac\7p\2\2\u00ac\u00ad\7v\2\2\u00ad\u00ae\7u\2\2\u00ae"+
		"\b\3\2\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7p\2\2\u00b1\u00b2\7v\2\2\u00b2"+
		"\u00b3\7g\2\2\u00b3\u00b4\7t\2\2\u00b4\u00b5\7h\2\2\u00b5\u00b6\7c\2\2"+
		"\u00b6\u00b7\7e\2\2\u00b7\u00b8\7g\2\2\u00b8\n\3\2\2\2\u00b9\u00ba\7r"+
		"\2\2\u00ba\u00bb\7t\2\2\u00bb\u00bc\7q\2\2\u00bc\u00bd\7v\2\2\u00bd\u00be"+
		"\7g\2\2\u00be\u00bf\7e\2\2\u00bf\u00c0\7v\2\2\u00c0\u00c1\7g\2\2\u00c1"+
		"\u00c2\7f\2\2\u00c2\f\3\2\2\2\u00c3\u00c4\7c\2\2\u00c4\u00c5\7d\2\2\u00c5"+
		"\u00c6\7u\2\2\u00c6\u00c7\7v\2\2\u00c7\u00c8\7t\2\2\u00c8\u00c9\7c\2\2"+
		"\u00c9\u00ca\7e\2\2\u00ca\u00cb\7v\2\2\u00cb\16\3\2\2\2\u00cc\u00cd\7"+
		"e\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7p\2\2\u00cf\u00d0\7v\2\2\u00d0\u00d1"+
		"\7k\2\2\u00d1\u00d2\7p\2\2\u00d2\u00d3\7w\2\2\u00d3\u00d4\7g\2\2\u00d4"+
		"\20\3\2\2\2\u00d5\u00d6\7f\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7n\2\2\u00d8"+
		"\u00d9\7g\2\2\u00d9\u00da\7i\2\2\u00da\u00db\7c\2\2\u00db\u00dc\7v\2\2"+
		"\u00dc\u00dd\7g\2\2\u00dd\22\3\2\2\2\u00de\u00df\7k\2\2\u00df\u00e0\7"+
		"p\2\2\u00e0\u00e1\7v\2\2\u00e1\u00e2\7g\2\2\u00e2\u00e3\7t\2\2\u00e3\u00e4"+
		"\7p\2\2\u00e4\u00e5\7c\2\2\u00e5\u00e6\7n\2\2\u00e6\24\3\2\2\2\u00e7\u00e8"+
		"\7q\2\2\u00e8\u00e9\7x\2\2\u00e9\u00ea\7g\2\2\u00ea\u00eb\7t\2\2\u00eb"+
		"\u00ec\7t\2\2\u00ec\u00ed\7k\2\2\u00ed\u00ee\7f\2\2\u00ee\u00ef\7g\2\2"+
		"\u00ef\26\3\2\2\2\u00f0\u00f1\7f\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3\7"+
		"h\2\2\u00f3\u00f4\7c\2\2\u00f4\u00f5\7w\2\2\u00f5\u00f6\7n\2\2\u00f6\u00f7"+
		"\7v\2\2\u00f7\30\3\2\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7z\2\2\u00fa\u00fb"+
		"\7v\2\2\u00fb\u00fc\7g\2\2\u00fc\u00fd\7p\2\2\u00fd\u00fe\7f\2\2\u00fe"+
		"\u00ff\7u\2\2\u00ff\32\3\2\2\2\u0100\u0101\7h\2\2\u0101\u0102\7k\2\2\u0102"+
		"\u0103\7p\2\2\u0103\u0104\7c\2\2\u0104\u0105\7n\2\2\u0105\u0106\7n\2\2"+
		"\u0106\u0107\7{\2\2\u0107\34\3\2\2\2\u0108\u0109\7r\2\2\u0109\u010a\7"+
		"c\2\2\u010a\u010b\7e\2\2\u010b\u010c\7m\2\2\u010c\u010d\7c\2\2\u010d\u010e"+
		"\7i\2\2\u010e\u010f\7g\2\2\u010f\36\3\2\2\2\u0110\u0111\7r\2\2\u0111\u0112"+
		"\7t\2\2\u0112\u0113\7k\2\2\u0113\u0114\7x\2\2\u0114\u0115\7c\2\2\u0115"+
		"\u0116\7v\2\2\u0116\u0117\7g\2\2\u0117 \3\2\2\2\u0118\u0119\7g\2\2\u0119"+
		"\u011a\7z\2\2\u011a\u011b\7r\2\2\u011b\u011c\7q\2\2\u011c\u011d\7t\2\2"+
		"\u011d\u011e\7v\2\2\u011e\"\3\2\2\2\u011f\u0120\7g\2\2\u0120\u0121\7z"+
		"\2\2\u0121\u0122\7v\2\2\u0122\u0123\7g\2\2\u0123\u0124\7p\2\2\u0124\u0125"+
		"\7f\2\2\u0125$\3\2\2\2\u0126\u0127\7k\2\2\u0127\u0128\7o\2\2\u0128\u0129"+
		"\7r\2\2\u0129\u012a\7q\2\2\u012a\u012b\7t\2\2\u012b\u012c\7v\2\2\u012c"+
		"&\3\2\2\2\u012d\u012e\7o\2\2\u012e\u012f\7q\2\2\u012f\u0130\7f\2\2\u0130"+
		"\u0131\7w\2\2\u0131\u0132\7n\2\2\u0132\u0133\7g\2\2\u0133(\3\2\2\2\u0134"+
		"\u0135\7p\2\2\u0135\u0136\7c\2\2\u0136\u0137\7o\2\2\u0137\u0138\7g\2\2"+
		"\u0138\u0139\7q\2\2\u0139\u013a\7h\2\2\u013a*\3\2\2\2\u013b\u013c\7r\2"+
		"\2\u013c\u013d\7w\2\2\u013d\u013e\7d\2\2\u013e\u013f\7n\2\2\u013f\u0140"+
		"\7k\2\2\u0140\u0141\7e\2\2\u0141,\3\2\2\2\u0142\u0143\7t\2\2\u0143\u0144"+
		"\7g\2\2\u0144\u0145\7v\2\2\u0145\u0146\7w\2\2\u0146\u0147\7t\2\2\u0147"+
		"\u0148\7p\2\2\u0148.\3\2\2\2\u0149\u014a\7u\2\2\u014a\u014b\7g\2\2\u014b"+
		"\u014c\7c\2\2\u014c\u014d\7n\2\2\u014d\u014e\7g\2\2\u014e\u014f\7f\2\2"+
		"\u014f\60\3\2\2\2\u0150\u0151\7u\2\2\u0151\u0152\7v\2\2\u0152\u0153\7"+
		"c\2\2\u0153\u0154\7v\2\2\u0154\u0155\7k\2\2\u0155\u0156\7e\2\2\u0156\62"+
		"\3\2\2\2\u0157\u0158\7v\2\2\u0158\u0159\7j\2\2\u0159\u015a\7t\2\2\u015a"+
		"\u015b\7q\2\2\u015b\u015c\7y\2\2\u015c\u015d\7u\2\2\u015d\64\3\2\2\2\u015e"+
		"\u015f\7v\2\2\u015f\u0160\7{\2\2\u0160\u0161\7r\2\2\u0161\u0162\7g\2\2"+
		"\u0162\u0163\7q\2\2\u0163\u0164\7h\2\2\u0164\66\3\2\2\2\u0165\u0166\7"+
		"c\2\2\u0166\u0167\7n\2\2\u0167\u0168\7k\2\2\u0168\u0169\7c\2\2\u0169\u016a"+
		"\7u\2\2\u016a8\3\2\2\2\u016b\u016c\7c\2\2\u016c\u016d\7u\2\2\u016d\u016e"+
		"\7{\2\2\u016e\u016f\7p\2\2\u016f\u0170\7e\2\2\u0170:\3\2\2\2\u0171\u0172"+
		"\7c\2\2\u0172\u0173\7y\2\2\u0173\u0174\7c\2\2\u0174\u0175\7k\2\2\u0175"+
		"\u0176\7v\2\2\u0176<\3\2\2\2\u0177\u0178\7d\2\2\u0178\u0179\7t\2\2\u0179"+
		"\u017a\7g\2\2\u017a\u017b\7c\2\2\u017b\u017c\7m\2\2\u017c>\3\2\2\2\u017d"+
		"\u017e\7e\2\2\u017e\u017f\7c\2\2\u017f\u0180\7v\2\2\u0180\u0181\7e\2\2"+
		"\u0181\u0182\7j\2\2\u0182@\3\2\2\2\u0183\u0184\7e\2\2\u0184\u0185\7n\2"+
		"\2\u0185\u0186\7c\2\2\u0186\u0187\7u\2\2\u0187\u0188\7u\2\2\u0188B\3\2"+
		"\2\2\u0189\u018a\7h\2\2\u018a\u018b\7c\2\2\u018b\u018c\7n\2\2\u018c\u018d"+
		"\7u\2\2\u018d\u018e\7g\2\2\u018eD\3\2\2\2\u018f\u0190\7h\2\2\u0190\u0191"+
		"\7k\2\2\u0191\u0192\7p\2\2\u0192\u0193\7c\2\2\u0193\u0194\7n\2\2\u0194"+
		"F\3\2\2\2\u0195\u0196\7o\2\2\u0196\u0197\7c\2\2\u0197\u0198\7e\2\2\u0198"+
		"\u0199\7t\2\2\u0199\u019a\7q\2\2\u019aH\3\2\2\2\u019b\u019c\7o\2\2\u019c"+
		"\u019d\7c\2\2\u019d\u019e\7v\2\2\u019e\u019f\7e\2\2\u019f\u01a0\7j\2\2"+
		"\u01a0J\3\2\2\2\u01a1\u01a2\7o\2\2\u01a2\u01a3\7k\2\2\u01a3\u01a4\7z\2"+
		"\2\u01a4\u01a5\7k\2\2\u01a5\u01a6\7p\2\2\u01a6L\3\2\2\2\u01a7\u01a8\7"+
		"u\2\2\u01a8\u01a9\7w\2\2\u01a9\u01aa\7r\2\2\u01aa\u01ab\7g\2\2\u01ab\u01ac"+
		"\7t\2\2\u01acN\3\2\2\2\u01ad\u01ae\7v\2\2\u01ae\u01af\7j\2\2\u01af\u01b0"+
		"\7t\2\2\u01b0\u01b1\7q\2\2\u01b1\u01b2\7y\2\2\u01b2P\3\2\2\2\u01b3\u01b4"+
		"\7v\2\2\u01b4\u01b5\7t\2\2\u01b5\u01b6\7c\2\2\u01b6\u01b7\7k\2\2\u01b7"+
		"\u01b8\7v\2\2\u01b8R\3\2\2\2\u01b9\u01ba\7y\2\2\u01ba\u01bb\7j\2\2\u01bb"+
		"\u01bc\7k\2\2\u01bc\u01bd\7n\2\2\u01bd\u01be\7g\2\2\u01beT\3\2\2\2\u01bf"+
		"\u01c0\7{\2\2\u01c0\u01c1\7k\2\2\u01c1\u01c2\7g\2\2\u01c2\u01c3\7n\2\2"+
		"\u01c3\u01c4\7f\2\2\u01c4V\3\2\2\2\u01c5\u01c6\7e\2\2\u01c6\u01c7\7c\2"+
		"\2\u01c7\u01c8\7u\2\2\u01c8\u01c9\7g\2\2\u01c9X\3\2\2\2\u01ca\u01cb\7"+
		"g\2\2\u01cb\u01cc\7n\2\2\u01cc\u01cd\7u\2\2\u01cd\u01ce\7g\2\2\u01ceZ"+
		"\3\2\2\2\u01cf\u01d0\7g\2\2\u01d0\u01d1\7p\2\2\u01d1\u01d2\7w\2\2\u01d2"+
		"\u01d3\7o\2\2\u01d3\\\3\2\2\2\u01d4\u01d5\7h\2\2\u01d5\u01d6\7w\2\2\u01d6"+
		"\u01d7\7p\2\2\u01d7\u01d8\7e\2\2\u01d8^\3\2\2\2\u01d9\u01da\7k\2\2\u01da"+
		"\u01db\7p\2\2\u01db\u01dc\7k\2\2\u01dc\u01dd\7v\2\2\u01dd`\3\2\2\2\u01de"+
		"\u01df\7n\2\2\u01df\u01e0\7c\2\2\u01e0\u01e1\7|\2\2\u01e1\u01e2\7{\2\2"+
		"\u01e2b\3\2\2\2\u01e3\u01e4\7p\2\2\u01e4\u01e5\7w\2\2\u01e5\u01e6\7n\2"+
		"\2\u01e6\u01e7\7n\2\2\u01e7d\3\2\2\2\u01e8\u01e9\7v\2\2\u01e9\u01ea\7"+
		"j\2\2\u01ea\u01eb\7k\2\2\u01eb\u01ec\7u\2\2\u01ecf\3\2\2\2\u01ed\u01ee"+
		"\7v\2\2\u01ee\u01ef\7t\2\2\u01ef\u01f0\7w\2\2\u01f0\u01f1\7g\2\2\u01f1"+
		"h\3\2\2\2\u01f2\u01f3\7f\2\2\u01f3\u01f4\7g\2\2\u01f4\u01f5\7h\2\2\u01f5"+
		"j\3\2\2\2\u01f6\u01f7\7h\2\2\u01f7\u01f8\7q\2\2\u01f8\u01f9\7t\2\2\u01f9"+
		"l\3\2\2\2\u01fa\u01fb\7p\2\2\u01fb\u01fc\7g\2\2\u01fc\u01fd\7y\2\2\u01fd"+
		"n\3\2\2\2\u01fe\u01ff\7v\2\2\u01ff\u0200\7t\2\2\u0200\u0201\7{\2\2\u0201"+
		"p\3\2\2\2\u0202\u0203\7c\2\2\u0203\u0204\7u\2\2\u0204r\3\2\2\2\u0205\u0206"+
		"\7f\2\2\u0206\u0207\7q\2\2\u0207t\3\2\2\2\u0208\u0209\7k\2\2\u0209\u020a"+
		"\7h\2\2\u020av\3\2\2\2\u020b\u020c\7k\2\2\u020c\u020d\7p\2\2\u020dx\3"+
		"\2\2\2\u020e\u020f\7k\2\2\u020f\u0210\7u\2\2\u0210z\3\2\2\2\u0211\u0212"+
		"\7*\2\2\u0212|\3\2\2\2\u0213\u0214\7+\2\2\u0214~\3\2\2\2\u0215\u0216\7"+
		"}\2\2\u0216\u0080\3\2\2\2\u0217\u0218\7\177\2\2\u0218\u0082\3\2\2\2\u0219"+
		"\u021a\7\60\2\2\u021a\u0084\3\2\2\2\u021b\u021c\7.\2\2\u021c\u0086\3\2"+
		"\2\2\u021d\u0221\7$\2\2\u021e\u0220\5\u008bF\2\u021f\u021e\3\2\2\2\u0220"+
		"\u0223\3\2\2\2\u0221\u021f\3\2\2\2\u0221\u0222\3\2\2\2\u0222\u0224\3\2"+
		"\2\2\u0223\u0221\3\2\2\2\u0224\u0225\7$\2\2\u0225\u0088\3\2\2\2\u0226"+
		"\u022a\t\2\2\2\u0227\u0229\t\3\2\2\u0228\u0227\3\2\2\2\u0229\u022c\3\2"+
		"\2\2\u022a\u0228\3\2\2\2\u022a\u022b\3\2\2\2\u022b\u022f\3\2\2\2\u022c"+
		"\u022a\3\2\2\2\u022d\u022f\7\62\2\2\u022e\u0226\3\2\2\2\u022e\u022d\3"+
		"\2\2\2\u022f\u008a\3\2\2\2\u0230\u0231\n\4\2\2\u0231\u008c\3\2\2\2\u0232"+
		"\u0236\5\u0091I\2\u0233\u0235\5\u0093J\2\u0234\u0233\3\2\2\2\u0235\u0238"+
		"\3\2\2\2\u0236\u0234\3\2\2\2\u0236\u0237\3\2\2\2\u0237\u0245\3\2\2\2\u0238"+
		"\u0236\3\2\2\2\u0239\u023b\5\u008fH\2\u023a\u0239\3\2\2\2\u023b\u023c"+
		"\3\2\2\2\u023c\u023a\3\2\2\2\u023c\u023d\3\2\2\2\u023d\u0245\3\2\2\2\u023e"+
		"\u023f\7\60\2\2\u023f\u0240\7\60\2\2\u0240\u0245\7\60\2\2\u0241\u0242"+
		"\7\60\2\2\u0242\u0243\7\60\2\2\u0243\u0245\7>\2\2\u0244\u0232\3\2\2\2"+
		"\u0244\u023a\3\2\2\2\u0244\u023e\3\2\2\2\u0244\u0241\3\2\2\2\u0245\u008e"+
		"\3\2\2\2\u0246\u0247\t\5\2\2\u0247\u0090\3\2\2\2\u0248\u0249\t\6\2\2\u0249"+
		"\u0092\3\2\2\2\u024a\u0253\t\6\2\2\u024b\u024f\t\2\2\2\u024c\u024e\t\3"+
		"\2\2\u024d\u024c\3\2\2\2\u024e\u0251\3\2\2\2\u024f\u024d\3\2\2\2\u024f"+
		"\u0250\3\2\2\2\u0250\u0253\3\2\2\2\u0251\u024f\3\2\2\2\u0252\u024a\3\2"+
		"\2\2\u0252\u024b\3\2\2\2\u0253\u0094\3\2\2\2\u0254\u0256\t\7\2\2\u0255"+
		"\u0254\3\2\2\2\u0256\u0257\3\2\2\2\u0257\u0255\3\2\2\2\u0257\u0258\3\2"+
		"\2\2\u0258\u0259\3\2\2\2\u0259\u025a\bK\2\2\u025a\u0096\3\2\2\2\f\2\u0221"+
		"\u022a\u022e\u0236\u023c\u0244\u024f\u0252\u0257\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
