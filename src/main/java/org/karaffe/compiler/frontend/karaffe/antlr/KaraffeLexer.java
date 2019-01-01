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
		T__0=1, T__1=2, T__2=3, ENTRYPOINT=4, CLASS=5, INIT=6, THIS=7, DEF=8, 
		EQUAL=9, LBRACE=10, RBRACE=11, PLUS=12, MINUS=13, DOT=14, StringLiteral=15, 
		IntegerLiteral=16, Identifier=17, WS=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "ENTRYPOINT", "CLASS", "INIT", "THIS", "DEF", 
		"EQUAL", "LBRACE", "RBRACE", "PLUS", "MINUS", "DOT", "StringLiteral", 
		"IntegerLiteral", "StringChar", "Identifier", "Letter", "LetterOrDigit", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'print'", "'('", "')'", "'entrypoint'", "'class'", "'init'", "'this'", 
		"'def'", "'='", "'{'", "'}'", "'+'", "'-'", "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "ENTRYPOINT", "CLASS", "INIT", "THIS", "DEF", 
		"EQUAL", "LBRACE", "RBRACE", "PLUS", "MINUS", "DOT", "StringLiteral", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u0091\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\7\20"+
		"e\n\20\f\20\16\20h\13\20\3\20\3\20\3\21\3\21\7\21n\n\21\f\21\16\21q\13"+
		"\21\3\21\5\21t\n\21\3\22\3\22\3\23\3\23\7\23z\n\23\f\23\16\23}\13\23\3"+
		"\24\3\24\3\25\3\25\3\25\7\25\u0084\n\25\f\25\16\25\u0087\13\25\5\25\u0089"+
		"\n\25\3\26\6\26\u008c\n\26\r\26\16\26\u008d\3\26\3\26\2\2\27\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\2%\23\'\2)\2+\24\3\2\7\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\4\2C\\c|\5"+
		"\2\13\f\17\17\"\"\2\u0094\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2%\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5\63\3\2\2\2\7\65\3"+
		"\2\2\2\t\67\3\2\2\2\13B\3\2\2\2\rH\3\2\2\2\17M\3\2\2\2\21R\3\2\2\2\23"+
		"V\3\2\2\2\25X\3\2\2\2\27Z\3\2\2\2\31\\\3\2\2\2\33^\3\2\2\2\35`\3\2\2\2"+
		"\37b\3\2\2\2!s\3\2\2\2#u\3\2\2\2%w\3\2\2\2\'~\3\2\2\2)\u0088\3\2\2\2+"+
		"\u008b\3\2\2\2-.\7r\2\2./\7t\2\2/\60\7k\2\2\60\61\7p\2\2\61\62\7v\2\2"+
		"\62\4\3\2\2\2\63\64\7*\2\2\64\6\3\2\2\2\65\66\7+\2\2\66\b\3\2\2\2\678"+
		"\7g\2\289\7p\2\29:\7v\2\2:;\7t\2\2;<\7{\2\2<=\7r\2\2=>\7q\2\2>?\7k\2\2"+
		"?@\7p\2\2@A\7v\2\2A\n\3\2\2\2BC\7e\2\2CD\7n\2\2DE\7c\2\2EF\7u\2\2FG\7"+
		"u\2\2G\f\3\2\2\2HI\7k\2\2IJ\7p\2\2JK\7k\2\2KL\7v\2\2L\16\3\2\2\2MN\7v"+
		"\2\2NO\7j\2\2OP\7k\2\2PQ\7u\2\2Q\20\3\2\2\2RS\7f\2\2ST\7g\2\2TU\7h\2\2"+
		"U\22\3\2\2\2VW\7?\2\2W\24\3\2\2\2XY\7}\2\2Y\26\3\2\2\2Z[\7\177\2\2[\30"+
		"\3\2\2\2\\]\7-\2\2]\32\3\2\2\2^_\7/\2\2_\34\3\2\2\2`a\7\60\2\2a\36\3\2"+
		"\2\2bf\7$\2\2ce\5#\22\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2"+
		"\2\2hf\3\2\2\2ij\7$\2\2j \3\2\2\2ko\t\2\2\2ln\t\3\2\2ml\3\2\2\2nq\3\2"+
		"\2\2om\3\2\2\2op\3\2\2\2pt\3\2\2\2qo\3\2\2\2rt\7\62\2\2sk\3\2\2\2sr\3"+
		"\2\2\2t\"\3\2\2\2uv\n\4\2\2v$\3\2\2\2w{\5\'\24\2xz\5)\25\2yx\3\2\2\2z"+
		"}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|&\3\2\2\2}{\3\2\2\2~\177\t\5\2\2\177(\3"+
		"\2\2\2\u0080\u0089\t\5\2\2\u0081\u0085\t\2\2\2\u0082\u0084\t\3\2\2\u0083"+
		"\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2"+
		"\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u0080\3\2\2\2\u0088"+
		"\u0081\3\2\2\2\u0089*\3\2\2\2\u008a\u008c\t\6\2\2\u008b\u008a\3\2\2\2"+
		"\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f"+
		"\3\2\2\2\u008f\u0090\b\26\2\2\u0090,\3\2\2\2\n\2fos{\u0085\u0088\u008d"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
