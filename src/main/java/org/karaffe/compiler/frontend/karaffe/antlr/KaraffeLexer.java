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
		ENTRYPOINT=1, CLASS=2, PRINT=3, INIT=4, THIS=5, DEF=6, LPAREN=7, RPAREN=8, 
		LBRACE=9, RBRACE=10, DOT=11, StringLiteral=12, IntegerLiteral=13, Identifier=14, 
		WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ENTRYPOINT", "CLASS", "PRINT", "INIT", "THIS", "DEF", "LPAREN", "RPAREN", 
		"LBRACE", "RBRACE", "DOT", "StringLiteral", "IntegerLiteral", "StringChar", 
		"Identifier", "OperatorChar", "Letter", "LetterOrDigit", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21\u0090\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\f\3\f\3\r\3\r\7\r[\n\r\f\r\16\r^\13\r\3\r\3\r\3\16\3\16\7\16d\n\16\f"+
		"\16\16\16g\13\16\3\16\5\16j\n\16\3\17\3\17\3\20\3\20\7\20p\n\20\f\20\16"+
		"\20s\13\20\3\20\6\20v\n\20\r\20\16\20w\5\20z\n\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\23\7\23\u0083\n\23\f\23\16\23\u0086\13\23\5\23\u0088\n\23"+
		"\3\24\6\24\u008b\n\24\r\24\16\24\u008c\3\24\3\24\2\2\25\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\2\37\20!\2#\2%\2"+
		"\'\21\3\2\b\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\n\2##\'(,-//\61\61>@``\u0080"+
		"\u0080\4\2C\\c|\5\2\13\f\17\17\"\"\2\u0094\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\37\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2\5\64\3\2\2\2\7:\3\2\2\2\t@\3\2\2\2"+
		"\13E\3\2\2\2\rJ\3\2\2\2\17N\3\2\2\2\21P\3\2\2\2\23R\3\2\2\2\25T\3\2\2"+
		"\2\27V\3\2\2\2\31X\3\2\2\2\33i\3\2\2\2\35k\3\2\2\2\37y\3\2\2\2!{\3\2\2"+
		"\2#}\3\2\2\2%\u0087\3\2\2\2\'\u008a\3\2\2\2)*\7g\2\2*+\7p\2\2+,\7v\2\2"+
		",-\7t\2\2-.\7{\2\2./\7r\2\2/\60\7q\2\2\60\61\7k\2\2\61\62\7p\2\2\62\63"+
		"\7v\2\2\63\4\3\2\2\2\64\65\7e\2\2\65\66\7n\2\2\66\67\7c\2\2\678\7u\2\2"+
		"89\7u\2\29\6\3\2\2\2:;\7r\2\2;<\7t\2\2<=\7k\2\2=>\7p\2\2>?\7v\2\2?\b\3"+
		"\2\2\2@A\7k\2\2AB\7p\2\2BC\7k\2\2CD\7v\2\2D\n\3\2\2\2EF\7v\2\2FG\7j\2"+
		"\2GH\7k\2\2HI\7u\2\2I\f\3\2\2\2JK\7f\2\2KL\7g\2\2LM\7h\2\2M\16\3\2\2\2"+
		"NO\7*\2\2O\20\3\2\2\2PQ\7+\2\2Q\22\3\2\2\2RS\7}\2\2S\24\3\2\2\2TU\7\177"+
		"\2\2U\26\3\2\2\2VW\7\60\2\2W\30\3\2\2\2X\\\7$\2\2Y[\5\35\17\2ZY\3\2\2"+
		"\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2\2^\\\3\2\2\2_`\7$\2\2`\32\3"+
		"\2\2\2ae\t\2\2\2bd\t\3\2\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fj\3"+
		"\2\2\2ge\3\2\2\2hj\7\62\2\2ia\3\2\2\2ih\3\2\2\2j\34\3\2\2\2kl\n\4\2\2"+
		"l\36\3\2\2\2mq\5#\22\2np\5%\23\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2"+
		"\2rz\3\2\2\2sq\3\2\2\2tv\5!\21\2ut\3\2\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2"+
		"\2xz\3\2\2\2ym\3\2\2\2yu\3\2\2\2z \3\2\2\2{|\t\5\2\2|\"\3\2\2\2}~\t\6"+
		"\2\2~$\3\2\2\2\177\u0088\t\6\2\2\u0080\u0084\t\2\2\2\u0081\u0083\t\3\2"+
		"\2\u0082\u0081\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085"+
		"\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0087\177\3\2\2\2\u0087"+
		"\u0080\3\2\2\2\u0088&\3\2\2\2\u0089\u008b\t\7\2\2\u008a\u0089\3\2\2\2"+
		"\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e"+
		"\3\2\2\2\u008e\u008f\b\24\2\2\u008f(\3\2\2\2\f\2\\eiqwy\u0084\u0087\u008c"+
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
