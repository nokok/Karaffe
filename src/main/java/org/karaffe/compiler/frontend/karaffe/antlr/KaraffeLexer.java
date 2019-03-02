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
		T__0=1, ENTRYPOINT=2, IMPLEMENTS=3, INTERFACE=4, NAMESPACE=5, PROTECTED=6, 
		ABSTRACT=7, CONTINUE=8, DELEGATE=9, INTERNAL=10, OVERRIDE=11, DEFAULT=12, 
		EXTENDS=13, FINALLY=14, PACKAGE=15, PRIVATE=16, EXPORT=17, EXTEND=18, 
		IMPORT=19, MODULE=20, NAMEOF=21, PUBLIC=22, RETURN=23, SEALED=24, STATIC=25, 
		THROWS=26, TYPEOF=27, ALIAS=28, ASYNC=29, AWAIT=30, BREAK=31, CATCH=32, 
		CLASS=33, FALSE=34, FINAL=35, MACRO=36, MATCH=37, MIXIN=38, SUPER=39, 
		THROW=40, TRAIT=41, WHILE=42, YIELD=43, CASE=44, ELSE=45, ENUM=46, FUNC=47, 
		INIT=48, LAZY=49, NULL=50, THIS=51, TRUE=52, DEF=53, FOR=54, NEW=55, TRY=56, 
		AS=57, DO=58, IF=59, IN=60, IS=61, LPAREN=62, RPAREN=63, LBRACE=64, RBRACE=65, 
		DOT=66, COMMA=67, StringLiteral=68, IntegerLiteral=69, Identifier=70, 
		WS=71;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "ENTRYPOINT", "IMPLEMENTS", "INTERFACE", "NAMESPACE", "PROTECTED", 
		"ABSTRACT", "CONTINUE", "DELEGATE", "INTERNAL", "OVERRIDE", "DEFAULT", 
		"EXTENDS", "FINALLY", "PACKAGE", "PRIVATE", "EXPORT", "EXTEND", "IMPORT", 
		"MODULE", "NAMEOF", "PUBLIC", "RETURN", "SEALED", "STATIC", "THROWS", 
		"TYPEOF", "ALIAS", "ASYNC", "AWAIT", "BREAK", "CATCH", "CLASS", "FALSE", 
		"FINAL", "MACRO", "MATCH", "MIXIN", "SUPER", "THROW", "TRAIT", "WHILE", 
		"YIELD", "CASE", "ELSE", "ENUM", "FUNC", "INIT", "LAZY", "NULL", "THIS", 
		"TRUE", "DEF", "FOR", "NEW", "TRY", "AS", "DO", "IF", "IN", "IS", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "DOT", "COMMA", "StringLiteral", "IntegerLiteral", 
		"StringChar", "Identifier", "OperatorChar", "Letter", "LetterOrDigit", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'entrypoint'", "'implements'", "'interface'", "'namespace'", 
		"'protected'", "'abstract'", "'continue'", "'delegate'", "'internal'", 
		"'override'", "'default'", "'extends'", "'finally'", "'package'", "'private'", 
		"'export'", "'extend'", "'import'", "'module'", "'nameof'", "'public'", 
		"'return'", "'sealed'", "'static'", "'throws'", "'typeof'", "'alias'", 
		"'async'", "'await'", "'break'", "'catch'", "'class'", "'false'", "'final'", 
		"'macro'", "'match'", "'mixin'", "'super'", "'throw'", "'trait'", "'while'", 
		"'yield'", "'case'", "'else'", "'enum'", "'func'", "'init'", "'lazy'", 
		"'null'", "'this'", "'true'", "'def'", "'for'", "'new'", "'try'", "'as'", 
		"'do'", "'if'", "'in'", "'is'", "'('", "')'", "'{'", "'}'", "'.'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "ENTRYPOINT", "IMPLEMENTS", "INTERFACE", "NAMESPACE", "PROTECTED", 
		"ABSTRACT", "CONTINUE", "DELEGATE", "INTERNAL", "OVERRIDE", "DEFAULT", 
		"EXTENDS", "FINALLY", "PACKAGE", "PRIVATE", "EXPORT", "EXTEND", "IMPORT", 
		"MODULE", "NAMEOF", "PUBLIC", "RETURN", "SEALED", "STATIC", "THROWS", 
		"TYPEOF", "ALIAS", "ASYNC", "AWAIT", "BREAK", "CATCH", "CLASS", "FALSE", 
		"FINAL", "MACRO", "MATCH", "MIXIN", "SUPER", "THROW", "TRAIT", "WHILE", 
		"YIELD", "CASE", "ELSE", "ENUM", "FUNC", "INIT", "LAZY", "NULL", "THIS", 
		"TRUE", "DEF", "FOR", "NEW", "TRY", "AS", "DO", "IF", "IN", "IS", "LPAREN", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2I\u0267\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3"+
		"!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3"+
		"$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3"+
		"(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\3"+
		",\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3"+
		"\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63\3"+
		"\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3"+
		"\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\38\38\38\38\39\39\39\39\3:\3:\3"+
		":\3;\3;\3;\3<\3<\3<\3=\3=\3=\3>\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3"+
		"D\3D\3E\3E\7E\u022c\nE\fE\16E\u022f\13E\3E\3E\3F\3F\7F\u0235\nF\fF\16"+
		"F\u0238\13F\3F\5F\u023b\nF\3G\3G\3H\3H\7H\u0241\nH\fH\16H\u0244\13H\3"+
		"H\6H\u0247\nH\rH\16H\u0248\3H\3H\3H\3H\3H\3H\5H\u0251\nH\3I\3I\3J\3J\3"+
		"K\3K\3K\7K\u025a\nK\fK\16K\u025d\13K\5K\u025f\nK\3L\6L\u0262\nL\rL\16"+
		"L\u0263\3L\3L\2\2M\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63"+
		"e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083C\u0085D\u0087E\u0089"+
		"F\u008bG\u008d\2\u008fH\u0091\2\u0093\2\u0095\2\u0097I\3\2\b\3\2\63;\3"+
		"\2\62;\6\2\f\f\17\17$$^^\16\2##%%\'(,-//\61\61<<>@BB``~~\u0080\u0080\4"+
		"\2C\\c|\5\2\13\f\17\17\"\"\2\u026d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2"+
		"O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3"+
		"\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2"+
		"\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2"+
		"u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2"+
		"\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089"+
		"\3\2\2\2\2\u008b\3\2\2\2\2\u008f\3\2\2\2\2\u0097\3\2\2\2\3\u0099\3\2\2"+
		"\2\5\u009b\3\2\2\2\7\u00a6\3\2\2\2\t\u00b1\3\2\2\2\13\u00bb\3\2\2\2\r"+
		"\u00c5\3\2\2\2\17\u00cf\3\2\2\2\21\u00d8\3\2\2\2\23\u00e1\3\2\2\2\25\u00ea"+
		"\3\2\2\2\27\u00f3\3\2\2\2\31\u00fc\3\2\2\2\33\u0104\3\2\2\2\35\u010c\3"+
		"\2\2\2\37\u0114\3\2\2\2!\u011c\3\2\2\2#\u0124\3\2\2\2%\u012b\3\2\2\2\'"+
		"\u0132\3\2\2\2)\u0139\3\2\2\2+\u0140\3\2\2\2-\u0147\3\2\2\2/\u014e\3\2"+
		"\2\2\61\u0155\3\2\2\2\63\u015c\3\2\2\2\65\u0163\3\2\2\2\67\u016a\3\2\2"+
		"\29\u0171\3\2\2\2;\u0177\3\2\2\2=\u017d\3\2\2\2?\u0183\3\2\2\2A\u0189"+
		"\3\2\2\2C\u018f\3\2\2\2E\u0195\3\2\2\2G\u019b\3\2\2\2I\u01a1\3\2\2\2K"+
		"\u01a7\3\2\2\2M\u01ad\3\2\2\2O\u01b3\3\2\2\2Q\u01b9\3\2\2\2S\u01bf\3\2"+
		"\2\2U\u01c5\3\2\2\2W\u01cb\3\2\2\2Y\u01d1\3\2\2\2[\u01d6\3\2\2\2]\u01db"+
		"\3\2\2\2_\u01e0\3\2\2\2a\u01e5\3\2\2\2c\u01ea\3\2\2\2e\u01ef\3\2\2\2g"+
		"\u01f4\3\2\2\2i\u01f9\3\2\2\2k\u01fe\3\2\2\2m\u0202\3\2\2\2o\u0206\3\2"+
		"\2\2q\u020a\3\2\2\2s\u020e\3\2\2\2u\u0211\3\2\2\2w\u0214\3\2\2\2y\u0217"+
		"\3\2\2\2{\u021a\3\2\2\2}\u021d\3\2\2\2\177\u021f\3\2\2\2\u0081\u0221\3"+
		"\2\2\2\u0083\u0223\3\2\2\2\u0085\u0225\3\2\2\2\u0087\u0227\3\2\2\2\u0089"+
		"\u0229\3\2\2\2\u008b\u023a\3\2\2\2\u008d\u023c\3\2\2\2\u008f\u0250\3\2"+
		"\2\2\u0091\u0252\3\2\2\2\u0093\u0254\3\2\2\2\u0095\u025e\3\2\2\2\u0097"+
		"\u0261\3\2\2\2\u0099\u009a\7?\2\2\u009a\4\3\2\2\2\u009b\u009c\7g\2\2\u009c"+
		"\u009d\7p\2\2\u009d\u009e\7v\2\2\u009e\u009f\7t\2\2\u009f\u00a0\7{\2\2"+
		"\u00a0\u00a1\7r\2\2\u00a1\u00a2\7q\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4"+
		"\7p\2\2\u00a4\u00a5\7v\2\2\u00a5\6\3\2\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8"+
		"\7o\2\2\u00a8\u00a9\7r\2\2\u00a9\u00aa\7n\2\2\u00aa\u00ab\7g\2\2\u00ab"+
		"\u00ac\7o\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7p\2\2\u00ae\u00af\7v\2\2"+
		"\u00af\u00b0\7u\2\2\u00b0\b\3\2\2\2\u00b1\u00b2\7k\2\2\u00b2\u00b3\7p"+
		"\2\2\u00b3\u00b4\7v\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7t\2\2\u00b6\u00b7"+
		"\7h\2\2\u00b7\u00b8\7c\2\2\u00b8\u00b9\7e\2\2\u00b9\u00ba\7g\2\2\u00ba"+
		"\n\3\2\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd\7c\2\2\u00bd\u00be\7o\2\2\u00be"+
		"\u00bf\7g\2\2\u00bf\u00c0\7u\2\2\u00c0\u00c1\7r\2\2\u00c1\u00c2\7c\2\2"+
		"\u00c2\u00c3\7e\2\2\u00c3\u00c4\7g\2\2\u00c4\f\3\2\2\2\u00c5\u00c6\7r"+
		"\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7q\2\2\u00c8\u00c9\7v\2\2\u00c9\u00ca"+
		"\7g\2\2\u00ca\u00cb\7e\2\2\u00cb\u00cc\7v\2\2\u00cc\u00cd\7g\2\2\u00cd"+
		"\u00ce\7f\2\2\u00ce\16\3\2\2\2\u00cf\u00d0\7c\2\2\u00d0\u00d1\7d\2\2\u00d1"+
		"\u00d2\7u\2\2\u00d2\u00d3\7v\2\2\u00d3\u00d4\7t\2\2\u00d4\u00d5\7c\2\2"+
		"\u00d5\u00d6\7e\2\2\u00d6\u00d7\7v\2\2\u00d7\20\3\2\2\2\u00d8\u00d9\7"+
		"e\2\2\u00d9\u00da\7q\2\2\u00da\u00db\7p\2\2\u00db\u00dc\7v\2\2\u00dc\u00dd"+
		"\7k\2\2\u00dd\u00de\7p\2\2\u00de\u00df\7w\2\2\u00df\u00e0\7g\2\2\u00e0"+
		"\22\3\2\2\2\u00e1\u00e2\7f\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4\7n\2\2\u00e4"+
		"\u00e5\7g\2\2\u00e5\u00e6\7i\2\2\u00e6\u00e7\7c\2\2\u00e7\u00e8\7v\2\2"+
		"\u00e8\u00e9\7g\2\2\u00e9\24\3\2\2\2\u00ea\u00eb\7k\2\2\u00eb\u00ec\7"+
		"p\2\2\u00ec\u00ed\7v\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7t\2\2\u00ef\u00f0"+
		"\7p\2\2\u00f0\u00f1\7c\2\2\u00f1\u00f2\7n\2\2\u00f2\26\3\2\2\2\u00f3\u00f4"+
		"\7q\2\2\u00f4\u00f5\7x\2\2\u00f5\u00f6\7g\2\2\u00f6\u00f7\7t\2\2\u00f7"+
		"\u00f8\7t\2\2\u00f8\u00f9\7k\2\2\u00f9\u00fa\7f\2\2\u00fa\u00fb\7g\2\2"+
		"\u00fb\30\3\2\2\2\u00fc\u00fd\7f\2\2\u00fd\u00fe\7g\2\2\u00fe\u00ff\7"+
		"h\2\2\u00ff\u0100\7c\2\2\u0100\u0101\7w\2\2\u0101\u0102\7n\2\2\u0102\u0103"+
		"\7v\2\2\u0103\32\3\2\2\2\u0104\u0105\7g\2\2\u0105\u0106\7z\2\2\u0106\u0107"+
		"\7v\2\2\u0107\u0108\7g\2\2\u0108\u0109\7p\2\2\u0109\u010a\7f\2\2\u010a"+
		"\u010b\7u\2\2\u010b\34\3\2\2\2\u010c\u010d\7h\2\2\u010d\u010e\7k\2\2\u010e"+
		"\u010f\7p\2\2\u010f\u0110\7c\2\2\u0110\u0111\7n\2\2\u0111\u0112\7n\2\2"+
		"\u0112\u0113\7{\2\2\u0113\36\3\2\2\2\u0114\u0115\7r\2\2\u0115\u0116\7"+
		"c\2\2\u0116\u0117\7e\2\2\u0117\u0118\7m\2\2\u0118\u0119\7c\2\2\u0119\u011a"+
		"\7i\2\2\u011a\u011b\7g\2\2\u011b \3\2\2\2\u011c\u011d\7r\2\2\u011d\u011e"+
		"\7t\2\2\u011e\u011f\7k\2\2\u011f\u0120\7x\2\2\u0120\u0121\7c\2\2\u0121"+
		"\u0122\7v\2\2\u0122\u0123\7g\2\2\u0123\"\3\2\2\2\u0124\u0125\7g\2\2\u0125"+
		"\u0126\7z\2\2\u0126\u0127\7r\2\2\u0127\u0128\7q\2\2\u0128\u0129\7t\2\2"+
		"\u0129\u012a\7v\2\2\u012a$\3\2\2\2\u012b\u012c\7g\2\2\u012c\u012d\7z\2"+
		"\2\u012d\u012e\7v\2\2\u012e\u012f\7g\2\2\u012f\u0130\7p\2\2\u0130\u0131"+
		"\7f\2\2\u0131&\3\2\2\2\u0132\u0133\7k\2\2\u0133\u0134\7o\2\2\u0134\u0135"+
		"\7r\2\2\u0135\u0136\7q\2\2\u0136\u0137\7t\2\2\u0137\u0138\7v\2\2\u0138"+
		"(\3\2\2\2\u0139\u013a\7o\2\2\u013a\u013b\7q\2\2\u013b\u013c\7f\2\2\u013c"+
		"\u013d\7w\2\2\u013d\u013e\7n\2\2\u013e\u013f\7g\2\2\u013f*\3\2\2\2\u0140"+
		"\u0141\7p\2\2\u0141\u0142\7c\2\2\u0142\u0143\7o\2\2\u0143\u0144\7g\2\2"+
		"\u0144\u0145\7q\2\2\u0145\u0146\7h\2\2\u0146,\3\2\2\2\u0147\u0148\7r\2"+
		"\2\u0148\u0149\7w\2\2\u0149\u014a\7d\2\2\u014a\u014b\7n\2\2\u014b\u014c"+
		"\7k\2\2\u014c\u014d\7e\2\2\u014d.\3\2\2\2\u014e\u014f\7t\2\2\u014f\u0150"+
		"\7g\2\2\u0150\u0151\7v\2\2\u0151\u0152\7w\2\2\u0152\u0153\7t\2\2\u0153"+
		"\u0154\7p\2\2\u0154\60\3\2\2\2\u0155\u0156\7u\2\2\u0156\u0157\7g\2\2\u0157"+
		"\u0158\7c\2\2\u0158\u0159\7n\2\2\u0159\u015a\7g\2\2\u015a\u015b\7f\2\2"+
		"\u015b\62\3\2\2\2\u015c\u015d\7u\2\2\u015d\u015e\7v\2\2\u015e\u015f\7"+
		"c\2\2\u015f\u0160\7v\2\2\u0160\u0161\7k\2\2\u0161\u0162\7e\2\2\u0162\64"+
		"\3\2\2\2\u0163\u0164\7v\2\2\u0164\u0165\7j\2\2\u0165\u0166\7t\2\2\u0166"+
		"\u0167\7q\2\2\u0167\u0168\7y\2\2\u0168\u0169\7u\2\2\u0169\66\3\2\2\2\u016a"+
		"\u016b\7v\2\2\u016b\u016c\7{\2\2\u016c\u016d\7r\2\2\u016d\u016e\7g\2\2"+
		"\u016e\u016f\7q\2\2\u016f\u0170\7h\2\2\u01708\3\2\2\2\u0171\u0172\7c\2"+
		"\2\u0172\u0173\7n\2\2\u0173\u0174\7k\2\2\u0174\u0175\7c\2\2\u0175\u0176"+
		"\7u\2\2\u0176:\3\2\2\2\u0177\u0178\7c\2\2\u0178\u0179\7u\2\2\u0179\u017a"+
		"\7{\2\2\u017a\u017b\7p\2\2\u017b\u017c\7e\2\2\u017c<\3\2\2\2\u017d\u017e"+
		"\7c\2\2\u017e\u017f\7y\2\2\u017f\u0180\7c\2\2\u0180\u0181\7k\2\2\u0181"+
		"\u0182\7v\2\2\u0182>\3\2\2\2\u0183\u0184\7d\2\2\u0184\u0185\7t\2\2\u0185"+
		"\u0186\7g\2\2\u0186\u0187\7c\2\2\u0187\u0188\7m\2\2\u0188@\3\2\2\2\u0189"+
		"\u018a\7e\2\2\u018a\u018b\7c\2\2\u018b\u018c\7v\2\2\u018c\u018d\7e\2\2"+
		"\u018d\u018e\7j\2\2\u018eB\3\2\2\2\u018f\u0190\7e\2\2\u0190\u0191\7n\2"+
		"\2\u0191\u0192\7c\2\2\u0192\u0193\7u\2\2\u0193\u0194\7u\2\2\u0194D\3\2"+
		"\2\2\u0195\u0196\7h\2\2\u0196\u0197\7c\2\2\u0197\u0198\7n\2\2\u0198\u0199"+
		"\7u\2\2\u0199\u019a\7g\2\2\u019aF\3\2\2\2\u019b\u019c\7h\2\2\u019c\u019d"+
		"\7k\2\2\u019d\u019e\7p\2\2\u019e\u019f\7c\2\2\u019f\u01a0\7n\2\2\u01a0"+
		"H\3\2\2\2\u01a1\u01a2\7o\2\2\u01a2\u01a3\7c\2\2\u01a3\u01a4\7e\2\2\u01a4"+
		"\u01a5\7t\2\2\u01a5\u01a6\7q\2\2\u01a6J\3\2\2\2\u01a7\u01a8\7o\2\2\u01a8"+
		"\u01a9\7c\2\2\u01a9\u01aa\7v\2\2\u01aa\u01ab\7e\2\2\u01ab\u01ac\7j\2\2"+
		"\u01acL\3\2\2\2\u01ad\u01ae\7o\2\2\u01ae\u01af\7k\2\2\u01af\u01b0\7z\2"+
		"\2\u01b0\u01b1\7k\2\2\u01b1\u01b2\7p\2\2\u01b2N\3\2\2\2\u01b3\u01b4\7"+
		"u\2\2\u01b4\u01b5\7w\2\2\u01b5\u01b6\7r\2\2\u01b6\u01b7\7g\2\2\u01b7\u01b8"+
		"\7t\2\2\u01b8P\3\2\2\2\u01b9\u01ba\7v\2\2\u01ba\u01bb\7j\2\2\u01bb\u01bc"+
		"\7t\2\2\u01bc\u01bd\7q\2\2\u01bd\u01be\7y\2\2\u01beR\3\2\2\2\u01bf\u01c0"+
		"\7v\2\2\u01c0\u01c1\7t\2\2\u01c1\u01c2\7c\2\2\u01c2\u01c3\7k\2\2\u01c3"+
		"\u01c4\7v\2\2\u01c4T\3\2\2\2\u01c5\u01c6\7y\2\2\u01c6\u01c7\7j\2\2\u01c7"+
		"\u01c8\7k\2\2\u01c8\u01c9\7n\2\2\u01c9\u01ca\7g\2\2\u01caV\3\2\2\2\u01cb"+
		"\u01cc\7{\2\2\u01cc\u01cd\7k\2\2\u01cd\u01ce\7g\2\2\u01ce\u01cf\7n\2\2"+
		"\u01cf\u01d0\7f\2\2\u01d0X\3\2\2\2\u01d1\u01d2\7e\2\2\u01d2\u01d3\7c\2"+
		"\2\u01d3\u01d4\7u\2\2\u01d4\u01d5\7g\2\2\u01d5Z\3\2\2\2\u01d6\u01d7\7"+
		"g\2\2\u01d7\u01d8\7n\2\2\u01d8\u01d9\7u\2\2\u01d9\u01da\7g\2\2\u01da\\"+
		"\3\2\2\2\u01db\u01dc\7g\2\2\u01dc\u01dd\7p\2\2\u01dd\u01de\7w\2\2\u01de"+
		"\u01df\7o\2\2\u01df^\3\2\2\2\u01e0\u01e1\7h\2\2\u01e1\u01e2\7w\2\2\u01e2"+
		"\u01e3\7p\2\2\u01e3\u01e4\7e\2\2\u01e4`\3\2\2\2\u01e5\u01e6\7k\2\2\u01e6"+
		"\u01e7\7p\2\2\u01e7\u01e8\7k\2\2\u01e8\u01e9\7v\2\2\u01e9b\3\2\2\2\u01ea"+
		"\u01eb\7n\2\2\u01eb\u01ec\7c\2\2\u01ec\u01ed\7|\2\2\u01ed\u01ee\7{\2\2"+
		"\u01eed\3\2\2\2\u01ef\u01f0\7p\2\2\u01f0\u01f1\7w\2\2\u01f1\u01f2\7n\2"+
		"\2\u01f2\u01f3\7n\2\2\u01f3f\3\2\2\2\u01f4\u01f5\7v\2\2\u01f5\u01f6\7"+
		"j\2\2\u01f6\u01f7\7k\2\2\u01f7\u01f8\7u\2\2\u01f8h\3\2\2\2\u01f9\u01fa"+
		"\7v\2\2\u01fa\u01fb\7t\2\2\u01fb\u01fc\7w\2\2\u01fc\u01fd\7g\2\2\u01fd"+
		"j\3\2\2\2\u01fe\u01ff\7f\2\2\u01ff\u0200\7g\2\2\u0200\u0201\7h\2\2\u0201"+
		"l\3\2\2\2\u0202\u0203\7h\2\2\u0203\u0204\7q\2\2\u0204\u0205\7t\2\2\u0205"+
		"n\3\2\2\2\u0206\u0207\7p\2\2\u0207\u0208\7g\2\2\u0208\u0209\7y\2\2\u0209"+
		"p\3\2\2\2\u020a\u020b\7v\2\2\u020b\u020c\7t\2\2\u020c\u020d\7{\2\2\u020d"+
		"r\3\2\2\2\u020e\u020f\7c\2\2\u020f\u0210\7u\2\2\u0210t\3\2\2\2\u0211\u0212"+
		"\7f\2\2\u0212\u0213\7q\2\2\u0213v\3\2\2\2\u0214\u0215\7k\2\2\u0215\u0216"+
		"\7h\2\2\u0216x\3\2\2\2\u0217\u0218\7k\2\2\u0218\u0219\7p\2\2\u0219z\3"+
		"\2\2\2\u021a\u021b\7k\2\2\u021b\u021c\7u\2\2\u021c|\3\2\2\2\u021d\u021e"+
		"\7*\2\2\u021e~\3\2\2\2\u021f\u0220\7+\2\2\u0220\u0080\3\2\2\2\u0221\u0222"+
		"\7}\2\2\u0222\u0082\3\2\2\2\u0223\u0224\7\177\2\2\u0224\u0084\3\2\2\2"+
		"\u0225\u0226\7\60\2\2\u0226\u0086\3\2\2\2\u0227\u0228\7.\2\2\u0228\u0088"+
		"\3\2\2\2\u0229\u022d\7$\2\2\u022a\u022c\5\u008dG\2\u022b\u022a\3\2\2\2"+
		"\u022c\u022f\3\2\2\2\u022d\u022b\3\2\2\2\u022d\u022e\3\2\2\2\u022e\u0230"+
		"\3\2\2\2\u022f\u022d\3\2\2\2\u0230\u0231\7$\2\2\u0231\u008a\3\2\2\2\u0232"+
		"\u0236\t\2\2\2\u0233\u0235\t\3\2\2\u0234\u0233\3\2\2\2\u0235\u0238\3\2"+
		"\2\2\u0236\u0234\3\2\2\2\u0236\u0237\3\2\2\2\u0237\u023b\3\2\2\2\u0238"+
		"\u0236\3\2\2\2\u0239\u023b\7\62\2\2\u023a\u0232\3\2\2\2\u023a\u0239\3"+
		"\2\2\2\u023b\u008c\3\2\2\2\u023c\u023d\n\4\2\2\u023d\u008e\3\2\2\2\u023e"+
		"\u0242\5\u0093J\2\u023f\u0241\5\u0095K\2\u0240\u023f\3\2\2\2\u0241\u0244"+
		"\3\2\2\2\u0242\u0240\3\2\2\2\u0242\u0243\3\2\2\2\u0243\u0251\3\2\2\2\u0244"+
		"\u0242\3\2\2\2\u0245\u0247\5\u0091I\2\u0246\u0245\3\2\2\2\u0247\u0248"+
		"\3\2\2\2\u0248\u0246\3\2\2\2\u0248\u0249\3\2\2\2\u0249\u0251\3\2\2\2\u024a"+
		"\u024b\7\60\2\2\u024b\u024c\7\60\2\2\u024c\u0251\7\60\2\2\u024d\u024e"+
		"\7\60\2\2\u024e\u024f\7\60\2\2\u024f\u0251\7>\2\2\u0250\u023e\3\2\2\2"+
		"\u0250\u0246\3\2\2\2\u0250\u024a\3\2\2\2\u0250\u024d\3\2\2\2\u0251\u0090"+
		"\3\2\2\2\u0252\u0253\t\5\2\2\u0253\u0092\3\2\2\2\u0254\u0255\t\6\2\2\u0255"+
		"\u0094\3\2\2\2\u0256\u025f\t\6\2\2\u0257\u025b\t\2\2\2\u0258\u025a\t\3"+
		"\2\2\u0259\u0258\3\2\2\2\u025a\u025d\3\2\2\2\u025b\u0259\3\2\2\2\u025b"+
		"\u025c\3\2\2\2\u025c\u025f\3\2\2\2\u025d\u025b\3\2\2\2\u025e\u0256\3\2"+
		"\2\2\u025e\u0257\3\2\2\2\u025f\u0096\3\2\2\2\u0260\u0262\t\7\2\2\u0261"+
		"\u0260\3\2\2\2\u0262\u0263\3\2\2\2\u0263\u0261\3\2\2\2\u0263\u0264\3\2"+
		"\2\2\u0264\u0265\3\2\2\2\u0265\u0266\bL\2\2\u0266\u0098\3\2\2\2\f\2\u022d"+
		"\u0236\u023a\u0242\u0248\u0250\u025b\u025e\u0263\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
