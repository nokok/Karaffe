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
		T__0=1, ENTRYPOINT=2, CLASS=3, PRINT=4, INIT=5, THIS=6, DEF=7, LPAREN=8, 
		RPAREN=9, LBRACE=10, RBRACE=11, DOT=12, COMMA=13, StringLiteral=14, IntegerLiteral=15, 
		Identifier=16, WS=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "ENTRYPOINT", "CLASS", "PRINT", "INIT", "THIS", "DEF", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "DOT", "COMMA", "StringLiteral", "IntegerLiteral", 
		"StringChar", "Identifier", "OperatorChar", "Letter", "LetterOrDigit", 
		"WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23\u0099\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\7\17d\n\17"+
		"\f\17\16\17g\13\17\3\17\3\17\3\20\3\20\7\20m\n\20\f\20\16\20p\13\20\3"+
		"\20\5\20s\n\20\3\21\3\21\3\22\3\22\7\22y\n\22\f\22\16\22|\13\22\3\22\6"+
		"\22\177\n\22\r\22\16\22\u0080\5\22\u0083\n\22\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\25\7\25\u008c\n\25\f\25\16\25\u008f\13\25\5\25\u0091\n\25\3\26"+
		"\6\26\u0094\n\26\r\26\16\26\u0095\3\26\3\26\2\2\27\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\2#\22%\2\'\2"+
		")\2+\23\3\2\b\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\16\2##%%\'(,-//\61\61"+
		"<<>@BB``~~\u0080\u0080\4\2C\\c|\5\2\13\f\17\17\"\"\2\u009d\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2#\3\2\2\2\2+\3\2\2\2\3-\3\2\2"+
		"\2\5\60\3\2\2\2\7;\3\2\2\2\tA\3\2\2\2\13G\3\2\2\2\rL\3\2\2\2\17Q\3\2\2"+
		"\2\21U\3\2\2\2\23W\3\2\2\2\25Y\3\2\2\2\27[\3\2\2\2\31]\3\2\2\2\33_\3\2"+
		"\2\2\35a\3\2\2\2\37r\3\2\2\2!t\3\2\2\2#\u0082\3\2\2\2%\u0084\3\2\2\2\'"+
		"\u0086\3\2\2\2)\u0090\3\2\2\2+\u0093\3\2\2\2-.\7<\2\2./\7?\2\2/\4\3\2"+
		"\2\2\60\61\7g\2\2\61\62\7p\2\2\62\63\7v\2\2\63\64\7t\2\2\64\65\7{\2\2"+
		"\65\66\7r\2\2\66\67\7q\2\2\678\7k\2\289\7p\2\29:\7v\2\2:\6\3\2\2\2;<\7"+
		"e\2\2<=\7n\2\2=>\7c\2\2>?\7u\2\2?@\7u\2\2@\b\3\2\2\2AB\7r\2\2BC\7t\2\2"+
		"CD\7k\2\2DE\7p\2\2EF\7v\2\2F\n\3\2\2\2GH\7k\2\2HI\7p\2\2IJ\7k\2\2JK\7"+
		"v\2\2K\f\3\2\2\2LM\7v\2\2MN\7j\2\2NO\7k\2\2OP\7u\2\2P\16\3\2\2\2QR\7f"+
		"\2\2RS\7g\2\2ST\7h\2\2T\20\3\2\2\2UV\7*\2\2V\22\3\2\2\2WX\7+\2\2X\24\3"+
		"\2\2\2YZ\7}\2\2Z\26\3\2\2\2[\\\7\177\2\2\\\30\3\2\2\2]^\7\60\2\2^\32\3"+
		"\2\2\2_`\7.\2\2`\34\3\2\2\2ae\7$\2\2bd\5!\21\2cb\3\2\2\2dg\3\2\2\2ec\3"+
		"\2\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hi\7$\2\2i\36\3\2\2\2jn\t\2\2\2km"+
		"\t\3\2\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2os\3\2\2\2pn\3\2\2\2q"+
		"s\7\62\2\2rj\3\2\2\2rq\3\2\2\2s \3\2\2\2tu\n\4\2\2u\"\3\2\2\2vz\5\'\24"+
		"\2wy\5)\25\2xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{\u0083\3\2\2\2|z"+
		"\3\2\2\2}\177\5%\23\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082v\3\2\2\2\u0082~\3\2\2\2\u0083"+
		"$\3\2\2\2\u0084\u0085\t\5\2\2\u0085&\3\2\2\2\u0086\u0087\t\6\2\2\u0087"+
		"(\3\2\2\2\u0088\u0091\t\6\2\2\u0089\u008d\t\2\2\2\u008a\u008c\t\3\2\2"+
		"\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e"+
		"\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0088\3\2\2\2\u0090"+
		"\u0089\3\2\2\2\u0091*\3\2\2\2\u0092\u0094\t\7\2\2\u0093\u0092\3\2\2\2"+
		"\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097"+
		"\3\2\2\2\u0097\u0098\b\26\2\2\u0098,\3\2\2\2\f\2enrz\u0080\u0082\u008d"+
		"\u0090\u0095\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
