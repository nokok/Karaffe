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
		ENTRYPOINT=1, CLASS=2, INIT=3, THIS=4, DEF=5, BIND=6, LPAREN=7, RPAREN=8, 
		LBRACE=9, RBRACE=10, DOT=11, COMMA=12, StringLiteral=13, IntegerLiteral=14, 
		Identifier=15, WS=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ENTRYPOINT", "CLASS", "INIT", "THIS", "DEF", "BIND", "LPAREN", "RPAREN", 
		"LBRACE", "RBRACE", "DOT", "COMMA", "StringLiteral", "IntegerLiteral", 
		"StringChar", "Identifier", "OperatorChar", "Letter", "LetterOrDigit", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'entrypoint'", "'class'", "'init'", "'this'", "'def'", "':='", 
		"'('", "')'", "'{'", "'}'", "'.'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ENTRYPOINT", "CLASS", "INIT", "THIS", "DEF", "BIND", "LPAREN", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22\u0091\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\7\16\\\n\16\f\16\16\16_\13\16\3\16\3\16\3\17\3"+
		"\17\7\17e\n\17\f\17\16\17h\13\17\3\17\5\17k\n\17\3\20\3\20\3\21\3\21\7"+
		"\21q\n\21\f\21\16\21t\13\21\3\21\6\21w\n\21\r\21\16\21x\5\21{\n\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\24\7\24\u0084\n\24\f\24\16\24\u0087\13\24"+
		"\5\24\u0089\n\24\3\25\6\25\u008c\n\25\r\25\16\25\u008d\3\25\3\25\2\2\26"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\2!\21#\2%\2\'\2)\22\3\2\b\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\16\2#"+
		"#%%\'(,-//\61\61<<>@BB``~~\u0080\u0080\4\2C\\c|\5\2\13\f\17\17\"\"\2\u0095"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2!\3\2\2\2\2)\3\2\2\2\3+\3\2\2"+
		"\2\5\66\3\2\2\2\7<\3\2\2\2\tA\3\2\2\2\13F\3\2\2\2\rJ\3\2\2\2\17M\3\2\2"+
		"\2\21O\3\2\2\2\23Q\3\2\2\2\25S\3\2\2\2\27U\3\2\2\2\31W\3\2\2\2\33Y\3\2"+
		"\2\2\35j\3\2\2\2\37l\3\2\2\2!z\3\2\2\2#|\3\2\2\2%~\3\2\2\2\'\u0088\3\2"+
		"\2\2)\u008b\3\2\2\2+,\7g\2\2,-\7p\2\2-.\7v\2\2./\7t\2\2/\60\7{\2\2\60"+
		"\61\7r\2\2\61\62\7q\2\2\62\63\7k\2\2\63\64\7p\2\2\64\65\7v\2\2\65\4\3"+
		"\2\2\2\66\67\7e\2\2\678\7n\2\289\7c\2\29:\7u\2\2:;\7u\2\2;\6\3\2\2\2<"+
		"=\7k\2\2=>\7p\2\2>?\7k\2\2?@\7v\2\2@\b\3\2\2\2AB\7v\2\2BC\7j\2\2CD\7k"+
		"\2\2DE\7u\2\2E\n\3\2\2\2FG\7f\2\2GH\7g\2\2HI\7h\2\2I\f\3\2\2\2JK\7<\2"+
		"\2KL\7?\2\2L\16\3\2\2\2MN\7*\2\2N\20\3\2\2\2OP\7+\2\2P\22\3\2\2\2QR\7"+
		"}\2\2R\24\3\2\2\2ST\7\177\2\2T\26\3\2\2\2UV\7\60\2\2V\30\3\2\2\2WX\7."+
		"\2\2X\32\3\2\2\2Y]\7$\2\2Z\\\5\37\20\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2"+
		"]^\3\2\2\2^`\3\2\2\2_]\3\2\2\2`a\7$\2\2a\34\3\2\2\2bf\t\2\2\2ce\t\3\2"+
		"\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gk\3\2\2\2hf\3\2\2\2ik\7\62"+
		"\2\2jb\3\2\2\2ji\3\2\2\2k\36\3\2\2\2lm\n\4\2\2m \3\2\2\2nr\5%\23\2oq\5"+
		"\'\24\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2s{\3\2\2\2tr\3\2\2\2uw"+
		"\5#\22\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2y{\3\2\2\2zn\3\2\2\2z"+
		"v\3\2\2\2{\"\3\2\2\2|}\t\5\2\2}$\3\2\2\2~\177\t\6\2\2\177&\3\2\2\2\u0080"+
		"\u0089\t\6\2\2\u0081\u0085\t\2\2\2\u0082\u0084\t\3\2\2\u0083\u0082\3\2"+
		"\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086"+
		"\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u0080\3\2\2\2\u0088\u0081\3\2"+
		"\2\2\u0089(\3\2\2\2\u008a\u008c\t\7\2\2\u008b\u008a\3\2\2\2\u008c\u008d"+
		"\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\u0090\b\25\2\2\u0090*\3\2\2\2\f\2]fjrxz\u0085\u0088\u008d\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
