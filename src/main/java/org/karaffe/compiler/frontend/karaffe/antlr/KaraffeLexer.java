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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "ENTRYPOINT", "IMPLEMENTS", "INTERFACE", "NAMESPACE", 
		"PROTECTED", "ABSTRACT", "CONTINUE", "DELEGATE", "INTERNAL", "OVERRIDE", 
		"DEFAULT", "EXTENDS", "FINALLY", "PACKAGE", "PRIVATE", "EXPORT", "EXTEND", 
		"IMPORT", "MODULE", "NAMEOF", "PUBLIC", "RETURN", "SEALED", "STATIC", 
		"THROWS", "TYPEOF", "ALIAS", "ASYNC", "AWAIT", "BREAK", "CATCH", "CLASS", 
		"FALSE", "FINAL", "MACRO", "MATCH", "MIXIN", "SUPER", "THROW", "TRAIT", 
		"WHILE", "YIELD", "CASE", "ELSE", "ENUM", "FUNC", "INIT", "LAZY", "NULL", 
		"THIS", "TRUE", "DEF", "FOR", "NEW", "TRY", "AS", "DO", "IF", "IN", "IS", 
		"LPAREN", "RPAREN", "LBRACE", "RBRACE", "DOT", "COMMA", "SEMI", "StringLiteral", 
		"IntegerLiteral", "StringChar", "Identifier", "OperatorChar", "Letter", 
		"LetterOrDigit", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'\n'", "'entrypoint'", "'implements'", "'interface'", "'namespace'", 
		"'protected'", "'abstract'", "'continue'", "'delegate'", "'internal'", 
		"'override'", "'default'", "'extends'", "'finally'", "'package'", "'private'", 
		"'export'", "'extend'", "'import'", "'module'", "'nameof'", "'public'", 
		"'return'", "'sealed'", "'static'", "'throws'", "'typeof'", "'alias'", 
		"'async'", "'await'", "'break'", "'catch'", "'class'", "'false'", "'final'", 
		"'macro'", "'match'", "'mixin'", "'super'", "'throw'", "'trait'", "'while'", 
		"'yield'", "'case'", "'else'", "'enum'", "'func'", "'init'", "'lazy'", 
		"'null'", "'this'", "'true'", "'def'", "'for'", "'new'", "'try'", "'as'", 
		"'do'", "'if'", "'in'", "'is'", "'('", "')'", "'{'", "'}'", "'.'", "','", 
		"';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2K\u026f\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3"+
		"!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3"+
		"$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3"+
		"(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\3"+
		",\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60"+
		"\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63"+
		"\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65"+
		"\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\38\38\38\38\39\39\39\39"+
		"\3:\3:\3:\3:\3;\3;\3;\3<\3<\3<\3=\3=\3=\3>\3>\3>\3?\3?\3?\3@\3@\3A\3A"+
		"\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\7G\u0234\nG\fG\16G\u0237\13G\3G\3"+
		"G\3H\3H\7H\u023d\nH\fH\16H\u0240\13H\3H\5H\u0243\nH\3I\3I\3J\3J\7J\u0249"+
		"\nJ\fJ\16J\u024c\13J\3J\6J\u024f\nJ\rJ\16J\u0250\3J\3J\3J\3J\3J\3J\5J"+
		"\u0259\nJ\3K\3K\3L\3L\3M\3M\3M\7M\u0262\nM\fM\16M\u0265\13M\5M\u0267\n"+
		"M\3N\6N\u026a\nN\rN\16N\u026b\3N\3N\2\2O\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W"+
		"-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083"+
		"C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091\2\u0093J\u0095\2\u0097"+
		"\2\u0099\2\u009bK\3\2\b\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\16\2##%%\'("+
		",-//\61\61<<>@BB``~~\u0080\u0080\4\2C\\c|\4\2\13\13\"\"\2\u0275\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"+
		"\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2"+
		"\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2"+
		"\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o"+
		"\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2"+
		"\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085"+
		"\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2"+
		"\2\2\u008f\3\2\2\2\2\u0093\3\2\2\2\2\u009b\3\2\2\2\3\u009d\3\2\2\2\5\u009f"+
		"\3\2\2\2\7\u00a1\3\2\2\2\t\u00ac\3\2\2\2\13\u00b7\3\2\2\2\r\u00c1\3\2"+
		"\2\2\17\u00cb\3\2\2\2\21\u00d5\3\2\2\2\23\u00de\3\2\2\2\25\u00e7\3\2\2"+
		"\2\27\u00f0\3\2\2\2\31\u00f9\3\2\2\2\33\u0102\3\2\2\2\35\u010a\3\2\2\2"+
		"\37\u0112\3\2\2\2!\u011a\3\2\2\2#\u0122\3\2\2\2%\u012a\3\2\2\2\'\u0131"+
		"\3\2\2\2)\u0138\3\2\2\2+\u013f\3\2\2\2-\u0146\3\2\2\2/\u014d\3\2\2\2\61"+
		"\u0154\3\2\2\2\63\u015b\3\2\2\2\65\u0162\3\2\2\2\67\u0169\3\2\2\29\u0170"+
		"\3\2\2\2;\u0177\3\2\2\2=\u017d\3\2\2\2?\u0183\3\2\2\2A\u0189\3\2\2\2C"+
		"\u018f\3\2\2\2E\u0195\3\2\2\2G\u019b\3\2\2\2I\u01a1\3\2\2\2K\u01a7\3\2"+
		"\2\2M\u01ad\3\2\2\2O\u01b3\3\2\2\2Q\u01b9\3\2\2\2S\u01bf\3\2\2\2U\u01c5"+
		"\3\2\2\2W\u01cb\3\2\2\2Y\u01d1\3\2\2\2[\u01d7\3\2\2\2]\u01dc\3\2\2\2_"+
		"\u01e1\3\2\2\2a\u01e6\3\2\2\2c\u01eb\3\2\2\2e\u01f0\3\2\2\2g\u01f5\3\2"+
		"\2\2i\u01fa\3\2\2\2k\u01ff\3\2\2\2m\u0204\3\2\2\2o\u0208\3\2\2\2q\u020c"+
		"\3\2\2\2s\u0210\3\2\2\2u\u0214\3\2\2\2w\u0217\3\2\2\2y\u021a\3\2\2\2{"+
		"\u021d\3\2\2\2}\u0220\3\2\2\2\177\u0223\3\2\2\2\u0081\u0225\3\2\2\2\u0083"+
		"\u0227\3\2\2\2\u0085\u0229\3\2\2\2\u0087\u022b\3\2\2\2\u0089\u022d\3\2"+
		"\2\2\u008b\u022f\3\2\2\2\u008d\u0231\3\2\2\2\u008f\u0242\3\2\2\2\u0091"+
		"\u0244\3\2\2\2\u0093\u0258\3\2\2\2\u0095\u025a\3\2\2\2\u0097\u025c\3\2"+
		"\2\2\u0099\u0266\3\2\2\2\u009b\u0269\3\2\2\2\u009d\u009e\7?\2\2\u009e"+
		"\4\3\2\2\2\u009f\u00a0\7\f\2\2\u00a0\6\3\2\2\2\u00a1\u00a2\7g\2\2\u00a2"+
		"\u00a3\7p\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6\7{\2\2"+
		"\u00a6\u00a7\7r\2\2\u00a7\u00a8\7q\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa"+
		"\7p\2\2\u00aa\u00ab\7v\2\2\u00ab\b\3\2\2\2\u00ac\u00ad\7k\2\2\u00ad\u00ae"+
		"\7o\2\2\u00ae\u00af\7r\2\2\u00af\u00b0\7n\2\2\u00b0\u00b1\7g\2\2\u00b1"+
		"\u00b2\7o\2\2\u00b2\u00b3\7g\2\2\u00b3\u00b4\7p\2\2\u00b4\u00b5\7v\2\2"+
		"\u00b5\u00b6\7u\2\2\u00b6\n\3\2\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7p"+
		"\2\2\u00b9\u00ba\7v\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7t\2\2\u00bc\u00bd"+
		"\7h\2\2\u00bd\u00be\7c\2\2\u00be\u00bf\7e\2\2\u00bf\u00c0\7g\2\2\u00c0"+
		"\f\3\2\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\7c\2\2\u00c3\u00c4\7o\2\2\u00c4"+
		"\u00c5\7g\2\2\u00c5\u00c6\7u\2\2\u00c6\u00c7\7r\2\2\u00c7\u00c8\7c\2\2"+
		"\u00c8\u00c9\7e\2\2\u00c9\u00ca\7g\2\2\u00ca\16\3\2\2\2\u00cb\u00cc\7"+
		"r\2\2\u00cc\u00cd\7t\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7v\2\2\u00cf\u00d0"+
		"\7g\2\2\u00d0\u00d1\7e\2\2\u00d1\u00d2\7v\2\2\u00d2\u00d3\7g\2\2\u00d3"+
		"\u00d4\7f\2\2\u00d4\20\3\2\2\2\u00d5\u00d6\7c\2\2\u00d6\u00d7\7d\2\2\u00d7"+
		"\u00d8\7u\2\2\u00d8\u00d9\7v\2\2\u00d9\u00da\7t\2\2\u00da\u00db\7c\2\2"+
		"\u00db\u00dc\7e\2\2\u00dc\u00dd\7v\2\2\u00dd\22\3\2\2\2\u00de\u00df\7"+
		"e\2\2\u00df\u00e0\7q\2\2\u00e0\u00e1\7p\2\2\u00e1\u00e2\7v\2\2\u00e2\u00e3"+
		"\7k\2\2\u00e3\u00e4\7p\2\2\u00e4\u00e5\7w\2\2\u00e5\u00e6\7g\2\2\u00e6"+
		"\24\3\2\2\2\u00e7\u00e8\7f\2\2\u00e8\u00e9\7g\2\2\u00e9\u00ea\7n\2\2\u00ea"+
		"\u00eb\7g\2\2\u00eb\u00ec\7i\2\2\u00ec\u00ed\7c\2\2\u00ed\u00ee\7v\2\2"+
		"\u00ee\u00ef\7g\2\2\u00ef\26\3\2\2\2\u00f0\u00f1\7k\2\2\u00f1\u00f2\7"+
		"p\2\2\u00f2\u00f3\7v\2\2\u00f3\u00f4\7g\2\2\u00f4\u00f5\7t\2\2\u00f5\u00f6"+
		"\7p\2\2\u00f6\u00f7\7c\2\2\u00f7\u00f8\7n\2\2\u00f8\30\3\2\2\2\u00f9\u00fa"+
		"\7q\2\2\u00fa\u00fb\7x\2\2\u00fb\u00fc\7g\2\2\u00fc\u00fd\7t\2\2\u00fd"+
		"\u00fe\7t\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7f\2\2\u0100\u0101\7g\2\2"+
		"\u0101\32\3\2\2\2\u0102\u0103\7f\2\2\u0103\u0104\7g\2\2\u0104\u0105\7"+
		"h\2\2\u0105\u0106\7c\2\2\u0106\u0107\7w\2\2\u0107\u0108\7n\2\2\u0108\u0109"+
		"\7v\2\2\u0109\34\3\2\2\2\u010a\u010b\7g\2\2\u010b\u010c\7z\2\2\u010c\u010d"+
		"\7v\2\2\u010d\u010e\7g\2\2\u010e\u010f\7p\2\2\u010f\u0110\7f\2\2\u0110"+
		"\u0111\7u\2\2\u0111\36\3\2\2\2\u0112\u0113\7h\2\2\u0113\u0114\7k\2\2\u0114"+
		"\u0115\7p\2\2\u0115\u0116\7c\2\2\u0116\u0117\7n\2\2\u0117\u0118\7n\2\2"+
		"\u0118\u0119\7{\2\2\u0119 \3\2\2\2\u011a\u011b\7r\2\2\u011b\u011c\7c\2"+
		"\2\u011c\u011d\7e\2\2\u011d\u011e\7m\2\2\u011e\u011f\7c\2\2\u011f\u0120"+
		"\7i\2\2\u0120\u0121\7g\2\2\u0121\"\3\2\2\2\u0122\u0123\7r\2\2\u0123\u0124"+
		"\7t\2\2\u0124\u0125\7k\2\2\u0125\u0126\7x\2\2\u0126\u0127\7c\2\2\u0127"+
		"\u0128\7v\2\2\u0128\u0129\7g\2\2\u0129$\3\2\2\2\u012a\u012b\7g\2\2\u012b"+
		"\u012c\7z\2\2\u012c\u012d\7r\2\2\u012d\u012e\7q\2\2\u012e\u012f\7t\2\2"+
		"\u012f\u0130\7v\2\2\u0130&\3\2\2\2\u0131\u0132\7g\2\2\u0132\u0133\7z\2"+
		"\2\u0133\u0134\7v\2\2\u0134\u0135\7g\2\2\u0135\u0136\7p\2\2\u0136\u0137"+
		"\7f\2\2\u0137(\3\2\2\2\u0138\u0139\7k\2\2\u0139\u013a\7o\2\2\u013a\u013b"+
		"\7r\2\2\u013b\u013c\7q\2\2\u013c\u013d\7t\2\2\u013d\u013e\7v\2\2\u013e"+
		"*\3\2\2\2\u013f\u0140\7o\2\2\u0140\u0141\7q\2\2\u0141\u0142\7f\2\2\u0142"+
		"\u0143\7w\2\2\u0143\u0144\7n\2\2\u0144\u0145\7g\2\2\u0145,\3\2\2\2\u0146"+
		"\u0147\7p\2\2\u0147\u0148\7c\2\2\u0148\u0149\7o\2\2\u0149\u014a\7g\2\2"+
		"\u014a\u014b\7q\2\2\u014b\u014c\7h\2\2\u014c.\3\2\2\2\u014d\u014e\7r\2"+
		"\2\u014e\u014f\7w\2\2\u014f\u0150\7d\2\2\u0150\u0151\7n\2\2\u0151\u0152"+
		"\7k\2\2\u0152\u0153\7e\2\2\u0153\60\3\2\2\2\u0154\u0155\7t\2\2\u0155\u0156"+
		"\7g\2\2\u0156\u0157\7v\2\2\u0157\u0158\7w\2\2\u0158\u0159\7t\2\2\u0159"+
		"\u015a\7p\2\2\u015a\62\3\2\2\2\u015b\u015c\7u\2\2\u015c\u015d\7g\2\2\u015d"+
		"\u015e\7c\2\2\u015e\u015f\7n\2\2\u015f\u0160\7g\2\2\u0160\u0161\7f\2\2"+
		"\u0161\64\3\2\2\2\u0162\u0163\7u\2\2\u0163\u0164\7v\2\2\u0164\u0165\7"+
		"c\2\2\u0165\u0166\7v\2\2\u0166\u0167\7k\2\2\u0167\u0168\7e\2\2\u0168\66"+
		"\3\2\2\2\u0169\u016a\7v\2\2\u016a\u016b\7j\2\2\u016b\u016c\7t\2\2\u016c"+
		"\u016d\7q\2\2\u016d\u016e\7y\2\2\u016e\u016f\7u\2\2\u016f8\3\2\2\2\u0170"+
		"\u0171\7v\2\2\u0171\u0172\7{\2\2\u0172\u0173\7r\2\2\u0173\u0174\7g\2\2"+
		"\u0174\u0175\7q\2\2\u0175\u0176\7h\2\2\u0176:\3\2\2\2\u0177\u0178\7c\2"+
		"\2\u0178\u0179\7n\2\2\u0179\u017a\7k\2\2\u017a\u017b\7c\2\2\u017b\u017c"+
		"\7u\2\2\u017c<\3\2\2\2\u017d\u017e\7c\2\2\u017e\u017f\7u\2\2\u017f\u0180"+
		"\7{\2\2\u0180\u0181\7p\2\2\u0181\u0182\7e\2\2\u0182>\3\2\2\2\u0183\u0184"+
		"\7c\2\2\u0184\u0185\7y\2\2\u0185\u0186\7c\2\2\u0186\u0187\7k\2\2\u0187"+
		"\u0188\7v\2\2\u0188@\3\2\2\2\u0189\u018a\7d\2\2\u018a\u018b\7t\2\2\u018b"+
		"\u018c\7g\2\2\u018c\u018d\7c\2\2\u018d\u018e\7m\2\2\u018eB\3\2\2\2\u018f"+
		"\u0190\7e\2\2\u0190\u0191\7c\2\2\u0191\u0192\7v\2\2\u0192\u0193\7e\2\2"+
		"\u0193\u0194\7j\2\2\u0194D\3\2\2\2\u0195\u0196\7e\2\2\u0196\u0197\7n\2"+
		"\2\u0197\u0198\7c\2\2\u0198\u0199\7u\2\2\u0199\u019a\7u\2\2\u019aF\3\2"+
		"\2\2\u019b\u019c\7h\2\2\u019c\u019d\7c\2\2\u019d\u019e\7n\2\2\u019e\u019f"+
		"\7u\2\2\u019f\u01a0\7g\2\2\u01a0H\3\2\2\2\u01a1\u01a2\7h\2\2\u01a2\u01a3"+
		"\7k\2\2\u01a3\u01a4\7p\2\2\u01a4\u01a5\7c\2\2\u01a5\u01a6\7n\2\2\u01a6"+
		"J\3\2\2\2\u01a7\u01a8\7o\2\2\u01a8\u01a9\7c\2\2\u01a9\u01aa\7e\2\2\u01aa"+
		"\u01ab\7t\2\2\u01ab\u01ac\7q\2\2\u01acL\3\2\2\2\u01ad\u01ae\7o\2\2\u01ae"+
		"\u01af\7c\2\2\u01af\u01b0\7v\2\2\u01b0\u01b1\7e\2\2\u01b1\u01b2\7j\2\2"+
		"\u01b2N\3\2\2\2\u01b3\u01b4\7o\2\2\u01b4\u01b5\7k\2\2\u01b5\u01b6\7z\2"+
		"\2\u01b6\u01b7\7k\2\2\u01b7\u01b8\7p\2\2\u01b8P\3\2\2\2\u01b9\u01ba\7"+
		"u\2\2\u01ba\u01bb\7w\2\2\u01bb\u01bc\7r\2\2\u01bc\u01bd\7g\2\2\u01bd\u01be"+
		"\7t\2\2\u01beR\3\2\2\2\u01bf\u01c0\7v\2\2\u01c0\u01c1\7j\2\2\u01c1\u01c2"+
		"\7t\2\2\u01c2\u01c3\7q\2\2\u01c3\u01c4\7y\2\2\u01c4T\3\2\2\2\u01c5\u01c6"+
		"\7v\2\2\u01c6\u01c7\7t\2\2\u01c7\u01c8\7c\2\2\u01c8\u01c9\7k\2\2\u01c9"+
		"\u01ca\7v\2\2\u01caV\3\2\2\2\u01cb\u01cc\7y\2\2\u01cc\u01cd\7j\2\2\u01cd"+
		"\u01ce\7k\2\2\u01ce\u01cf\7n\2\2\u01cf\u01d0\7g\2\2\u01d0X\3\2\2\2\u01d1"+
		"\u01d2\7{\2\2\u01d2\u01d3\7k\2\2\u01d3\u01d4\7g\2\2\u01d4\u01d5\7n\2\2"+
		"\u01d5\u01d6\7f\2\2\u01d6Z\3\2\2\2\u01d7\u01d8\7e\2\2\u01d8\u01d9\7c\2"+
		"\2\u01d9\u01da\7u\2\2\u01da\u01db\7g\2\2\u01db\\\3\2\2\2\u01dc\u01dd\7"+
		"g\2\2\u01dd\u01de\7n\2\2\u01de\u01df\7u\2\2\u01df\u01e0\7g\2\2\u01e0^"+
		"\3\2\2\2\u01e1\u01e2\7g\2\2\u01e2\u01e3\7p\2\2\u01e3\u01e4\7w\2\2\u01e4"+
		"\u01e5\7o\2\2\u01e5`\3\2\2\2\u01e6\u01e7\7h\2\2\u01e7\u01e8\7w\2\2\u01e8"+
		"\u01e9\7p\2\2\u01e9\u01ea\7e\2\2\u01eab\3\2\2\2\u01eb\u01ec\7k\2\2\u01ec"+
		"\u01ed\7p\2\2\u01ed\u01ee\7k\2\2\u01ee\u01ef\7v\2\2\u01efd\3\2\2\2\u01f0"+
		"\u01f1\7n\2\2\u01f1\u01f2\7c\2\2\u01f2\u01f3\7|\2\2\u01f3\u01f4\7{\2\2"+
		"\u01f4f\3\2\2\2\u01f5\u01f6\7p\2\2\u01f6\u01f7\7w\2\2\u01f7\u01f8\7n\2"+
		"\2\u01f8\u01f9\7n\2\2\u01f9h\3\2\2\2\u01fa\u01fb\7v\2\2\u01fb\u01fc\7"+
		"j\2\2\u01fc\u01fd\7k\2\2\u01fd\u01fe\7u\2\2\u01fej\3\2\2\2\u01ff\u0200"+
		"\7v\2\2\u0200\u0201\7t\2\2\u0201\u0202\7w\2\2\u0202\u0203\7g\2\2\u0203"+
		"l\3\2\2\2\u0204\u0205\7f\2\2\u0205\u0206\7g\2\2\u0206\u0207\7h\2\2\u0207"+
		"n\3\2\2\2\u0208\u0209\7h\2\2\u0209\u020a\7q\2\2\u020a\u020b\7t\2\2\u020b"+
		"p\3\2\2\2\u020c\u020d\7p\2\2\u020d\u020e\7g\2\2\u020e\u020f\7y\2\2\u020f"+
		"r\3\2\2\2\u0210\u0211\7v\2\2\u0211\u0212\7t\2\2\u0212\u0213\7{\2\2\u0213"+
		"t\3\2\2\2\u0214\u0215\7c\2\2\u0215\u0216\7u\2\2\u0216v\3\2\2\2\u0217\u0218"+
		"\7f\2\2\u0218\u0219\7q\2\2\u0219x\3\2\2\2\u021a\u021b\7k\2\2\u021b\u021c"+
		"\7h\2\2\u021cz\3\2\2\2\u021d\u021e\7k\2\2\u021e\u021f\7p\2\2\u021f|\3"+
		"\2\2\2\u0220\u0221\7k\2\2\u0221\u0222\7u\2\2\u0222~\3\2\2\2\u0223\u0224"+
		"\7*\2\2\u0224\u0080\3\2\2\2\u0225\u0226\7+\2\2\u0226\u0082\3\2\2\2\u0227"+
		"\u0228\7}\2\2\u0228\u0084\3\2\2\2\u0229\u022a\7\177\2\2\u022a\u0086\3"+
		"\2\2\2\u022b\u022c\7\60\2\2\u022c\u0088\3\2\2\2\u022d\u022e\7.\2\2\u022e"+
		"\u008a\3\2\2\2\u022f\u0230\7=\2\2\u0230\u008c\3\2\2\2\u0231\u0235\7$\2"+
		"\2\u0232\u0234\5\u0091I\2\u0233\u0232\3\2\2\2\u0234\u0237\3\2\2\2\u0235"+
		"\u0233\3\2\2\2\u0235\u0236\3\2\2\2\u0236\u0238\3\2\2\2\u0237\u0235\3\2"+
		"\2\2\u0238\u0239\7$\2\2\u0239\u008e\3\2\2\2\u023a\u023e\t\2\2\2\u023b"+
		"\u023d\t\3\2\2\u023c\u023b\3\2\2\2\u023d\u0240\3\2\2\2\u023e\u023c\3\2"+
		"\2\2\u023e\u023f\3\2\2\2\u023f\u0243\3\2\2\2\u0240\u023e\3\2\2\2\u0241"+
		"\u0243\7\62\2\2\u0242\u023a\3\2\2\2\u0242\u0241\3\2\2\2\u0243\u0090\3"+
		"\2\2\2\u0244\u0245\n\4\2\2\u0245\u0092\3\2\2\2\u0246\u024a\5\u0097L\2"+
		"\u0247\u0249\5\u0099M\2\u0248\u0247\3\2\2\2\u0249\u024c\3\2\2\2\u024a"+
		"\u0248\3\2\2\2\u024a\u024b\3\2\2\2\u024b\u0259\3\2\2\2\u024c\u024a\3\2"+
		"\2\2\u024d\u024f\5\u0095K\2\u024e\u024d\3\2\2\2\u024f\u0250\3\2\2\2\u0250"+
		"\u024e\3\2\2\2\u0250\u0251\3\2\2\2\u0251\u0259\3\2\2\2\u0252\u0253\7\60"+
		"\2\2\u0253\u0254\7\60\2\2\u0254\u0259\7\60\2\2\u0255\u0256\7\60\2\2\u0256"+
		"\u0257\7\60\2\2\u0257\u0259\7>\2\2\u0258\u0246\3\2\2\2\u0258\u024e\3\2"+
		"\2\2\u0258\u0252\3\2\2\2\u0258\u0255\3\2\2\2\u0259\u0094\3\2\2\2\u025a"+
		"\u025b\t\5\2\2\u025b\u0096\3\2\2\2\u025c\u025d\t\6\2\2\u025d\u0098\3\2"+
		"\2\2\u025e\u0267\t\6\2\2\u025f\u0263\t\2\2\2\u0260\u0262\t\3\2\2\u0261"+
		"\u0260\3\2\2\2\u0262\u0265\3\2\2\2\u0263\u0261\3\2\2\2\u0263\u0264\3\2"+
		"\2\2\u0264\u0267\3\2\2\2\u0265\u0263\3\2\2\2\u0266\u025e\3\2\2\2\u0266"+
		"\u025f\3\2\2\2\u0267\u009a\3\2\2\2\u0268\u026a\t\7\2\2\u0269\u0268\3\2"+
		"\2\2\u026a\u026b\3\2\2\2\u026b\u0269\3\2\2\2\u026b\u026c\3\2\2\2\u026c"+
		"\u026d\3\2\2\2\u026d\u026e\bN\2\2\u026e\u009c\3\2\2\2\f\2\u0235\u023e"+
		"\u0242\u024a\u0250\u0258\u0263\u0266\u026b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
