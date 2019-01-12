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
		ENTRYPOINT=1, IMPLEMENTS=2, INTERFACE=3, PROTECTED=4, ABSTRACT=5, CONTINUE=6, 
		DELEGATE=7, INTERNAL=8, OVERRIDE=9, DEFAULT=10, EXTENDS=11, FINALLY=12, 
		PACKAGE=13, PRIVATE=14, EXPORT=15, EXTEND=16, IMPORT=17, MODULE=18, NAMEOF=19, 
		PUBLIC=20, RETURN=21, SEALED=22, STATIC=23, THROWS=24, TYPEOF=25, ALIAS=26, 
		ASYNC=27, AWAIT=28, BREAK=29, CATCH=30, CLASS=31, FALSE=32, FINAL=33, 
		MACRO=34, MATCH=35, MIXIN=36, SUPER=37, THROW=38, TRAIT=39, WHILE=40, 
		YIELD=41, CASE=42, ELSE=43, ENUM=44, FUNC=45, INIT=46, LAZY=47, NULL=48, 
		THIS=49, TRUE=50, DEF=51, FOR=52, NEW=53, TRY=54, AS=55, DO=56, IF=57, 
		IN=58, IS=59, BIND=60, LPAREN=61, RPAREN=62, LBRACE=63, RBRACE=64, DOT=65, 
		COMMA=66, StringLiteral=67, IntegerLiteral=68, Identifier=69, WS=70;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ENTRYPOINT", "IMPLEMENTS", "INTERFACE", "PROTECTED", "ABSTRACT", "CONTINUE", 
		"DELEGATE", "INTERNAL", "OVERRIDE", "DEFAULT", "EXTENDS", "FINALLY", "PACKAGE", 
		"PRIVATE", "EXPORT", "EXTEND", "IMPORT", "MODULE", "NAMEOF", "PUBLIC", 
		"RETURN", "SEALED", "STATIC", "THROWS", "TYPEOF", "ALIAS", "ASYNC", "AWAIT", 
		"BREAK", "CATCH", "CLASS", "FALSE", "FINAL", "MACRO", "MATCH", "MIXIN", 
		"SUPER", "THROW", "TRAIT", "WHILE", "YIELD", "CASE", "ELSE", "ENUM", "FUNC", 
		"INIT", "LAZY", "NULL", "THIS", "TRUE", "DEF", "FOR", "NEW", "TRY", "AS", 
		"DO", "IF", "IN", "IS", "BIND", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
		"DOT", "COMMA", "StringLiteral", "IntegerLiteral", "StringChar", "Identifier", 
		"OperatorChar", "Letter", "LetterOrDigit", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'entrypoint'", "'implements'", "'interface'", "'protected'", "'abstract'", 
		"'continue'", "'delegate'", "'internal'", "'override'", "'default'", "'extends'", 
		"'finally'", "'package'", "'private'", "'export'", "'extend'", "'import'", 
		"'module'", "'nameof'", "'public'", "'return'", "'sealed'", "'static'", 
		"'throws'", "'typeof'", "'alias'", "'async'", "'await'", "'break'", "'catch'", 
		"'class'", "'false'", "'final'", "'macro'", "'match'", "'mixin'", "'super'", 
		"'throw'", "'trait'", "'while'", "'yield'", "'case'", "'else'", "'enum'", 
		"'func'", "'init'", "'lazy'", "'null'", "'this'", "'true'", "'def'", "'for'", 
		"'new'", "'try'", "'as'", "'do'", "'if'", "'in'", "'is'", "':='", "'('", 
		"')'", "'{'", "'}'", "'.'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ENTRYPOINT", "IMPLEMENTS", "INTERFACE", "PROTECTED", "ABSTRACT", 
		"CONTINUE", "DELEGATE", "INTERNAL", "OVERRIDE", "DEFAULT", "EXTENDS", 
		"FINALLY", "PACKAGE", "PRIVATE", "EXPORT", "EXTEND", "IMPORT", "MODULE", 
		"NAMEOF", "PUBLIC", "RETURN", "SEALED", "STATIC", "THROWS", "TYPEOF", 
		"ALIAS", "ASYNC", "AWAIT", "BREAK", "CATCH", "CLASS", "FALSE", "FINAL", 
		"MACRO", "MATCH", "MIXIN", "SUPER", "THROW", "TRAIT", "WHILE", "YIELD", 
		"CASE", "ELSE", "ENUM", "FUNC", "INIT", "LAZY", "NULL", "THIS", "TRUE", 
		"DEF", "FOR", "NEW", "TRY", "AS", "DO", "IF", "IN", "IS", "BIND", "LPAREN", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2H\u0256\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!"+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%"+
		"\3%\3%\3%\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3("+
		"\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3-"+
		"\3-\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3"+
		"\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3"+
		"\63\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3"+
		"\67\3\67\3\67\38\38\38\39\39\39\3:\3:\3:\3;\3;\3;\3<\3<\3<\3=\3=\3=\3"+
		">\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3D\3D\7D\u0221\nD\fD\16D\u0224\13D"+
		"\3D\3D\3E\3E\7E\u022a\nE\fE\16E\u022d\13E\3E\5E\u0230\nE\3F\3F\3G\3G\7"+
		"G\u0236\nG\fG\16G\u0239\13G\3G\6G\u023c\nG\rG\16G\u023d\5G\u0240\nG\3"+
		"H\3H\3I\3I\3J\3J\3J\7J\u0249\nJ\fJ\16J\u024c\13J\5J\u024e\nJ\3K\6K\u0251"+
		"\nK\rK\16K\u0252\3K\3K\2\2L\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a"+
		"\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083C\u0085D\u0087"+
		"E\u0089F\u008b\2\u008dG\u008f\2\u0091\2\u0093\2\u0095H\3\2\b\3\2\63;\3"+
		"\2\62;\6\2\f\f\17\17$$^^\16\2##%%\'(,-//\61\61<<>@BB``~~\u0080\u0080\4"+
		"\2C\\c|\5\2\13\f\17\17\"\"\2\u025a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
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
		"\3\2\2\2\2\u008d\3\2\2\2\2\u0095\3\2\2\2\3\u0097\3\2\2\2\5\u00a2\3\2\2"+
		"\2\7\u00ad\3\2\2\2\t\u00b7\3\2\2\2\13\u00c1\3\2\2\2\r\u00ca\3\2\2\2\17"+
		"\u00d3\3\2\2\2\21\u00dc\3\2\2\2\23\u00e5\3\2\2\2\25\u00ee\3\2\2\2\27\u00f6"+
		"\3\2\2\2\31\u00fe\3\2\2\2\33\u0106\3\2\2\2\35\u010e\3\2\2\2\37\u0116\3"+
		"\2\2\2!\u011d\3\2\2\2#\u0124\3\2\2\2%\u012b\3\2\2\2\'\u0132\3\2\2\2)\u0139"+
		"\3\2\2\2+\u0140\3\2\2\2-\u0147\3\2\2\2/\u014e\3\2\2\2\61\u0155\3\2\2\2"+
		"\63\u015c\3\2\2\2\65\u0163\3\2\2\2\67\u0169\3\2\2\29\u016f\3\2\2\2;\u0175"+
		"\3\2\2\2=\u017b\3\2\2\2?\u0181\3\2\2\2A\u0187\3\2\2\2C\u018d\3\2\2\2E"+
		"\u0193\3\2\2\2G\u0199\3\2\2\2I\u019f\3\2\2\2K\u01a5\3\2\2\2M\u01ab\3\2"+
		"\2\2O\u01b1\3\2\2\2Q\u01b7\3\2\2\2S\u01bd\3\2\2\2U\u01c3\3\2\2\2W\u01c8"+
		"\3\2\2\2Y\u01cd\3\2\2\2[\u01d2\3\2\2\2]\u01d7\3\2\2\2_\u01dc\3\2\2\2a"+
		"\u01e1\3\2\2\2c\u01e6\3\2\2\2e\u01eb\3\2\2\2g\u01f0\3\2\2\2i\u01f4\3\2"+
		"\2\2k\u01f8\3\2\2\2m\u01fc\3\2\2\2o\u0200\3\2\2\2q\u0203\3\2\2\2s\u0206"+
		"\3\2\2\2u\u0209\3\2\2\2w\u020c\3\2\2\2y\u020f\3\2\2\2{\u0212\3\2\2\2}"+
		"\u0214\3\2\2\2\177\u0216\3\2\2\2\u0081\u0218\3\2\2\2\u0083\u021a\3\2\2"+
		"\2\u0085\u021c\3\2\2\2\u0087\u021e\3\2\2\2\u0089\u022f\3\2\2\2\u008b\u0231"+
		"\3\2\2\2\u008d\u023f\3\2\2\2\u008f\u0241\3\2\2\2\u0091\u0243\3\2\2\2\u0093"+
		"\u024d\3\2\2\2\u0095\u0250\3\2\2\2\u0097\u0098\7g\2\2\u0098\u0099\7p\2"+
		"\2\u0099\u009a\7v\2\2\u009a\u009b\7t\2\2\u009b\u009c\7{\2\2\u009c\u009d"+
		"\7r\2\2\u009d\u009e\7q\2\2\u009e\u009f\7k\2\2\u009f\u00a0\7p\2\2\u00a0"+
		"\u00a1\7v\2\2\u00a1\4\3\2\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7o\2\2\u00a4"+
		"\u00a5\7r\2\2\u00a5\u00a6\7n\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7o\2\2"+
		"\u00a8\u00a9\7g\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ab\7v\2\2\u00ab\u00ac"+
		"\7u\2\2\u00ac\6\3\2\2\2\u00ad\u00ae\7k\2\2\u00ae\u00af\7p\2\2\u00af\u00b0"+
		"\7v\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7t\2\2\u00b2\u00b3\7h\2\2\u00b3"+
		"\u00b4\7c\2\2\u00b4\u00b5\7e\2\2\u00b5\u00b6\7g\2\2\u00b6\b\3\2\2\2\u00b7"+
		"\u00b8\7r\2\2\u00b8\u00b9\7t\2\2\u00b9\u00ba\7q\2\2\u00ba\u00bb\7v\2\2"+
		"\u00bb\u00bc\7g\2\2\u00bc\u00bd\7e\2\2\u00bd\u00be\7v\2\2\u00be\u00bf"+
		"\7g\2\2\u00bf\u00c0\7f\2\2\u00c0\n\3\2\2\2\u00c1\u00c2\7c\2\2\u00c2\u00c3"+
		"\7d\2\2\u00c3\u00c4\7u\2\2\u00c4\u00c5\7v\2\2\u00c5\u00c6\7t\2\2\u00c6"+
		"\u00c7\7c\2\2\u00c7\u00c8\7e\2\2\u00c8\u00c9\7v\2\2\u00c9\f\3\2\2\2\u00ca"+
		"\u00cb\7e\2\2\u00cb\u00cc\7q\2\2\u00cc\u00cd\7p\2\2\u00cd\u00ce\7v\2\2"+
		"\u00ce\u00cf\7k\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7w\2\2\u00d1\u00d2"+
		"\7g\2\2\u00d2\16\3\2\2\2\u00d3\u00d4\7f\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d6"+
		"\7n\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7i\2\2\u00d8\u00d9\7c\2\2\u00d9"+
		"\u00da\7v\2\2\u00da\u00db\7g\2\2\u00db\20\3\2\2\2\u00dc\u00dd\7k\2\2\u00dd"+
		"\u00de\7p\2\2\u00de\u00df\7v\2\2\u00df\u00e0\7g\2\2\u00e0\u00e1\7t\2\2"+
		"\u00e1\u00e2\7p\2\2\u00e2\u00e3\7c\2\2\u00e3\u00e4\7n\2\2\u00e4\22\3\2"+
		"\2\2\u00e5\u00e6\7q\2\2\u00e6\u00e7\7x\2\2\u00e7\u00e8\7g\2\2\u00e8\u00e9"+
		"\7t\2\2\u00e9\u00ea\7t\2\2\u00ea\u00eb\7k\2\2\u00eb\u00ec\7f\2\2\u00ec"+
		"\u00ed\7g\2\2\u00ed\24\3\2\2\2\u00ee\u00ef\7f\2\2\u00ef\u00f0\7g\2\2\u00f0"+
		"\u00f1\7h\2\2\u00f1\u00f2\7c\2\2\u00f2\u00f3\7w\2\2\u00f3\u00f4\7n\2\2"+
		"\u00f4\u00f5\7v\2\2\u00f5\26\3\2\2\2\u00f6\u00f7\7g\2\2\u00f7\u00f8\7"+
		"z\2\2\u00f8\u00f9\7v\2\2\u00f9\u00fa\7g\2\2\u00fa\u00fb\7p\2\2\u00fb\u00fc"+
		"\7f\2\2\u00fc\u00fd\7u\2\2\u00fd\30\3\2\2\2\u00fe\u00ff\7h\2\2\u00ff\u0100"+
		"\7k\2\2\u0100\u0101\7p\2\2\u0101\u0102\7c\2\2\u0102\u0103\7n\2\2\u0103"+
		"\u0104\7n\2\2\u0104\u0105\7{\2\2\u0105\32\3\2\2\2\u0106\u0107\7r\2\2\u0107"+
		"\u0108\7c\2\2\u0108\u0109\7e\2\2\u0109\u010a\7m\2\2\u010a\u010b\7c\2\2"+
		"\u010b\u010c\7i\2\2\u010c\u010d\7g\2\2\u010d\34\3\2\2\2\u010e\u010f\7"+
		"r\2\2\u010f\u0110\7t\2\2\u0110\u0111\7k\2\2\u0111\u0112\7x\2\2\u0112\u0113"+
		"\7c\2\2\u0113\u0114\7v\2\2\u0114\u0115\7g\2\2\u0115\36\3\2\2\2\u0116\u0117"+
		"\7g\2\2\u0117\u0118\7z\2\2\u0118\u0119\7r\2\2\u0119\u011a\7q\2\2\u011a"+
		"\u011b\7t\2\2\u011b\u011c\7v\2\2\u011c \3\2\2\2\u011d\u011e\7g\2\2\u011e"+
		"\u011f\7z\2\2\u011f\u0120\7v\2\2\u0120\u0121\7g\2\2\u0121\u0122\7p\2\2"+
		"\u0122\u0123\7f\2\2\u0123\"\3\2\2\2\u0124\u0125\7k\2\2\u0125\u0126\7o"+
		"\2\2\u0126\u0127\7r\2\2\u0127\u0128\7q\2\2\u0128\u0129\7t\2\2\u0129\u012a"+
		"\7v\2\2\u012a$\3\2\2\2\u012b\u012c\7o\2\2\u012c\u012d\7q\2\2\u012d\u012e"+
		"\7f\2\2\u012e\u012f\7w\2\2\u012f\u0130\7n\2\2\u0130\u0131\7g\2\2\u0131"+
		"&\3\2\2\2\u0132\u0133\7p\2\2\u0133\u0134\7c\2\2\u0134\u0135\7o\2\2\u0135"+
		"\u0136\7g\2\2\u0136\u0137\7q\2\2\u0137\u0138\7h\2\2\u0138(\3\2\2\2\u0139"+
		"\u013a\7r\2\2\u013a\u013b\7w\2\2\u013b\u013c\7d\2\2\u013c\u013d\7n\2\2"+
		"\u013d\u013e\7k\2\2\u013e\u013f\7e\2\2\u013f*\3\2\2\2\u0140\u0141\7t\2"+
		"\2\u0141\u0142\7g\2\2\u0142\u0143\7v\2\2\u0143\u0144\7w\2\2\u0144\u0145"+
		"\7t\2\2\u0145\u0146\7p\2\2\u0146,\3\2\2\2\u0147\u0148\7u\2\2\u0148\u0149"+
		"\7g\2\2\u0149\u014a\7c\2\2\u014a\u014b\7n\2\2\u014b\u014c\7g\2\2\u014c"+
		"\u014d\7f\2\2\u014d.\3\2\2\2\u014e\u014f\7u\2\2\u014f\u0150\7v\2\2\u0150"+
		"\u0151\7c\2\2\u0151\u0152\7v\2\2\u0152\u0153\7k\2\2\u0153\u0154\7e\2\2"+
		"\u0154\60\3\2\2\2\u0155\u0156\7v\2\2\u0156\u0157\7j\2\2\u0157\u0158\7"+
		"t\2\2\u0158\u0159\7q\2\2\u0159\u015a\7y\2\2\u015a\u015b\7u\2\2\u015b\62"+
		"\3\2\2\2\u015c\u015d\7v\2\2\u015d\u015e\7{\2\2\u015e\u015f\7r\2\2\u015f"+
		"\u0160\7g\2\2\u0160\u0161\7q\2\2\u0161\u0162\7h\2\2\u0162\64\3\2\2\2\u0163"+
		"\u0164\7c\2\2\u0164\u0165\7n\2\2\u0165\u0166\7k\2\2\u0166\u0167\7c\2\2"+
		"\u0167\u0168\7u\2\2\u0168\66\3\2\2\2\u0169\u016a\7c\2\2\u016a\u016b\7"+
		"u\2\2\u016b\u016c\7{\2\2\u016c\u016d\7p\2\2\u016d\u016e\7e\2\2\u016e8"+
		"\3\2\2\2\u016f\u0170\7c\2\2\u0170\u0171\7y\2\2\u0171\u0172\7c\2\2\u0172"+
		"\u0173\7k\2\2\u0173\u0174\7v\2\2\u0174:\3\2\2\2\u0175\u0176\7d\2\2\u0176"+
		"\u0177\7t\2\2\u0177\u0178\7g\2\2\u0178\u0179\7c\2\2\u0179\u017a\7m\2\2"+
		"\u017a<\3\2\2\2\u017b\u017c\7e\2\2\u017c\u017d\7c\2\2\u017d\u017e\7v\2"+
		"\2\u017e\u017f\7e\2\2\u017f\u0180\7j\2\2\u0180>\3\2\2\2\u0181\u0182\7"+
		"e\2\2\u0182\u0183\7n\2\2\u0183\u0184\7c\2\2\u0184\u0185\7u\2\2\u0185\u0186"+
		"\7u\2\2\u0186@\3\2\2\2\u0187\u0188\7h\2\2\u0188\u0189\7c\2\2\u0189\u018a"+
		"\7n\2\2\u018a\u018b\7u\2\2\u018b\u018c\7g\2\2\u018cB\3\2\2\2\u018d\u018e"+
		"\7h\2\2\u018e\u018f\7k\2\2\u018f\u0190\7p\2\2\u0190\u0191\7c\2\2\u0191"+
		"\u0192\7n\2\2\u0192D\3\2\2\2\u0193\u0194\7o\2\2\u0194\u0195\7c\2\2\u0195"+
		"\u0196\7e\2\2\u0196\u0197\7t\2\2\u0197\u0198\7q\2\2\u0198F\3\2\2\2\u0199"+
		"\u019a\7o\2\2\u019a\u019b\7c\2\2\u019b\u019c\7v\2\2\u019c\u019d\7e\2\2"+
		"\u019d\u019e\7j\2\2\u019eH\3\2\2\2\u019f\u01a0\7o\2\2\u01a0\u01a1\7k\2"+
		"\2\u01a1\u01a2\7z\2\2\u01a2\u01a3\7k\2\2\u01a3\u01a4\7p\2\2\u01a4J\3\2"+
		"\2\2\u01a5\u01a6\7u\2\2\u01a6\u01a7\7w\2\2\u01a7\u01a8\7r\2\2\u01a8\u01a9"+
		"\7g\2\2\u01a9\u01aa\7t\2\2\u01aaL\3\2\2\2\u01ab\u01ac\7v\2\2\u01ac\u01ad"+
		"\7j\2\2\u01ad\u01ae\7t\2\2\u01ae\u01af\7q\2\2\u01af\u01b0\7y\2\2\u01b0"+
		"N\3\2\2\2\u01b1\u01b2\7v\2\2\u01b2\u01b3\7t\2\2\u01b3\u01b4\7c\2\2\u01b4"+
		"\u01b5\7k\2\2\u01b5\u01b6\7v\2\2\u01b6P\3\2\2\2\u01b7\u01b8\7y\2\2\u01b8"+
		"\u01b9\7j\2\2\u01b9\u01ba\7k\2\2\u01ba\u01bb\7n\2\2\u01bb\u01bc\7g\2\2"+
		"\u01bcR\3\2\2\2\u01bd\u01be\7{\2\2\u01be\u01bf\7k\2\2\u01bf\u01c0\7g\2"+
		"\2\u01c0\u01c1\7n\2\2\u01c1\u01c2\7f\2\2\u01c2T\3\2\2\2\u01c3\u01c4\7"+
		"e\2\2\u01c4\u01c5\7c\2\2\u01c5\u01c6\7u\2\2\u01c6\u01c7\7g\2\2\u01c7V"+
		"\3\2\2\2\u01c8\u01c9\7g\2\2\u01c9\u01ca\7n\2\2\u01ca\u01cb\7u\2\2\u01cb"+
		"\u01cc\7g\2\2\u01ccX\3\2\2\2\u01cd\u01ce\7g\2\2\u01ce\u01cf\7p\2\2\u01cf"+
		"\u01d0\7w\2\2\u01d0\u01d1\7o\2\2\u01d1Z\3\2\2\2\u01d2\u01d3\7h\2\2\u01d3"+
		"\u01d4\7w\2\2\u01d4\u01d5\7p\2\2\u01d5\u01d6\7e\2\2\u01d6\\\3\2\2\2\u01d7"+
		"\u01d8\7k\2\2\u01d8\u01d9\7p\2\2\u01d9\u01da\7k\2\2\u01da\u01db\7v\2\2"+
		"\u01db^\3\2\2\2\u01dc\u01dd\7n\2\2\u01dd\u01de\7c\2\2\u01de\u01df\7|\2"+
		"\2\u01df\u01e0\7{\2\2\u01e0`\3\2\2\2\u01e1\u01e2\7p\2\2\u01e2\u01e3\7"+
		"w\2\2\u01e3\u01e4\7n\2\2\u01e4\u01e5\7n\2\2\u01e5b\3\2\2\2\u01e6\u01e7"+
		"\7v\2\2\u01e7\u01e8\7j\2\2\u01e8\u01e9\7k\2\2\u01e9\u01ea\7u\2\2\u01ea"+
		"d\3\2\2\2\u01eb\u01ec\7v\2\2\u01ec\u01ed\7t\2\2\u01ed\u01ee\7w\2\2\u01ee"+
		"\u01ef\7g\2\2\u01eff\3\2\2\2\u01f0\u01f1\7f\2\2\u01f1\u01f2\7g\2\2\u01f2"+
		"\u01f3\7h\2\2\u01f3h\3\2\2\2\u01f4\u01f5\7h\2\2\u01f5\u01f6\7q\2\2\u01f6"+
		"\u01f7\7t\2\2\u01f7j\3\2\2\2\u01f8\u01f9\7p\2\2\u01f9\u01fa\7g\2\2\u01fa"+
		"\u01fb\7y\2\2\u01fbl\3\2\2\2\u01fc\u01fd\7v\2\2\u01fd\u01fe\7t\2\2\u01fe"+
		"\u01ff\7{\2\2\u01ffn\3\2\2\2\u0200\u0201\7c\2\2\u0201\u0202\7u\2\2\u0202"+
		"p\3\2\2\2\u0203\u0204\7f\2\2\u0204\u0205\7q\2\2\u0205r\3\2\2\2\u0206\u0207"+
		"\7k\2\2\u0207\u0208\7h\2\2\u0208t\3\2\2\2\u0209\u020a\7k\2\2\u020a\u020b"+
		"\7p\2\2\u020bv\3\2\2\2\u020c\u020d\7k\2\2\u020d\u020e\7u\2\2\u020ex\3"+
		"\2\2\2\u020f\u0210\7<\2\2\u0210\u0211\7?\2\2\u0211z\3\2\2\2\u0212\u0213"+
		"\7*\2\2\u0213|\3\2\2\2\u0214\u0215\7+\2\2\u0215~\3\2\2\2\u0216\u0217\7"+
		"}\2\2\u0217\u0080\3\2\2\2\u0218\u0219\7\177\2\2\u0219\u0082\3\2\2\2\u021a"+
		"\u021b\7\60\2\2\u021b\u0084\3\2\2\2\u021c\u021d\7.\2\2\u021d\u0086\3\2"+
		"\2\2\u021e\u0222\7$\2\2\u021f\u0221\5\u008bF\2\u0220\u021f\3\2\2\2\u0221"+
		"\u0224\3\2\2\2\u0222\u0220\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0225\3\2"+
		"\2\2\u0224\u0222\3\2\2\2\u0225\u0226\7$\2\2\u0226\u0088\3\2\2\2\u0227"+
		"\u022b\t\2\2\2\u0228\u022a\t\3\2\2\u0229\u0228\3\2\2\2\u022a\u022d\3\2"+
		"\2\2\u022b\u0229\3\2\2\2\u022b\u022c\3\2\2\2\u022c\u0230\3\2\2\2\u022d"+
		"\u022b\3\2\2\2\u022e\u0230\7\62\2\2\u022f\u0227\3\2\2\2\u022f\u022e\3"+
		"\2\2\2\u0230\u008a\3\2\2\2\u0231\u0232\n\4\2\2\u0232\u008c\3\2\2\2\u0233"+
		"\u0237\5\u0091I\2\u0234\u0236\5\u0093J\2\u0235\u0234\3\2\2\2\u0236\u0239"+
		"\3\2\2\2\u0237\u0235\3\2\2\2\u0237\u0238\3\2\2\2\u0238\u0240\3\2\2\2\u0239"+
		"\u0237\3\2\2\2\u023a\u023c\5\u008fH\2\u023b\u023a\3\2\2\2\u023c\u023d"+
		"\3\2\2\2\u023d\u023b\3\2\2\2\u023d\u023e\3\2\2\2\u023e\u0240\3\2\2\2\u023f"+
		"\u0233\3\2\2\2\u023f\u023b\3\2\2\2\u0240\u008e\3\2\2\2\u0241\u0242\t\5"+
		"\2\2\u0242\u0090\3\2\2\2\u0243\u0244\t\6\2\2\u0244\u0092\3\2\2\2\u0245"+
		"\u024e\t\6\2\2\u0246\u024a\t\2\2\2\u0247\u0249\t\3\2\2\u0248\u0247\3\2"+
		"\2\2\u0249\u024c\3\2\2\2\u024a\u0248\3\2\2\2\u024a\u024b\3\2\2\2\u024b"+
		"\u024e\3\2\2\2\u024c\u024a\3\2\2\2\u024d\u0245\3\2\2\2\u024d\u0246\3\2"+
		"\2\2\u024e\u0094\3\2\2\2\u024f\u0251\t\7\2\2\u0250\u024f\3\2\2\2\u0251"+
		"\u0252\3\2\2\2\u0252\u0250\3\2\2\2\u0252\u0253\3\2\2\2\u0253\u0254\3\2"+
		"\2\2\u0254\u0255\bK\2\2\u0255\u0096\3\2\2\2\f\2\u0222\u022b\u022f\u0237"+
		"\u023d\u023f\u024a\u024d\u0252\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
