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
		T__0=1, T__1=2, T__2=3, ENTRYPOINT=4, CLASS=5, LBRACE=6, RBRACE=7, PLUS=8, 
		MINUS=9, StringLiteral=10, IntegerLiteral=11, Identifier=12, WS=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "ENTRYPOINT", "CLASS", "LBRACE", "RBRACE", "PLUS", 
		"MINUS", "StringLiteral", "IntegerLiteral", "StringChar", "Identifier", 
		"Letter", "LetterOrDigit", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'print'", "'('", "')'", "'entrypoint'", "'class'", "'{'", "'}'", 
		"'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "ENTRYPOINT", "CLASS", "LBRACE", "RBRACE", "PLUS", 
		"MINUS", "StringLiteral", "IntegerLiteral", "Identifier", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\17r\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\7\13I\n\13\f\13\16\13L\13\13\3\13\3\13\3\f\3\f\7\fR\n\f\f\f\16\fU\13"+
		"\f\3\r\3\r\3\16\3\16\7\16[\n\16\f\16\16\16^\13\16\3\17\3\17\3\20\3\20"+
		"\3\20\7\20e\n\20\f\20\16\20h\13\20\5\20j\n\20\3\21\6\21m\n\21\r\21\16"+
		"\21n\3\21\3\21\2\2\22\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\2\33\16\35\2\37\2!\17\3\2\7\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\4"+
		"\2C\\c|\5\2\13\f\17\17\"\"\2t\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\33\3\2\2\2\2!\3\2\2\2\3#\3\2\2\2\5)\3\2"+
		"\2\2\7+\3\2\2\2\t-\3\2\2\2\138\3\2\2\2\r>\3\2\2\2\17@\3\2\2\2\21B\3\2"+
		"\2\2\23D\3\2\2\2\25F\3\2\2\2\27O\3\2\2\2\31V\3\2\2\2\33X\3\2\2\2\35_\3"+
		"\2\2\2\37i\3\2\2\2!l\3\2\2\2#$\7r\2\2$%\7t\2\2%&\7k\2\2&\'\7p\2\2\'(\7"+
		"v\2\2(\4\3\2\2\2)*\7*\2\2*\6\3\2\2\2+,\7+\2\2,\b\3\2\2\2-.\7g\2\2./\7"+
		"p\2\2/\60\7v\2\2\60\61\7t\2\2\61\62\7{\2\2\62\63\7r\2\2\63\64\7q\2\2\64"+
		"\65\7k\2\2\65\66\7p\2\2\66\67\7v\2\2\67\n\3\2\2\289\7e\2\29:\7n\2\2:;"+
		"\7c\2\2;<\7u\2\2<=\7u\2\2=\f\3\2\2\2>?\7}\2\2?\16\3\2\2\2@A\7\177\2\2"+
		"A\20\3\2\2\2BC\7-\2\2C\22\3\2\2\2DE\7/\2\2E\24\3\2\2\2FJ\7$\2\2GI\5\31"+
		"\r\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2\2MN\7$"+
		"\2\2N\26\3\2\2\2OS\t\2\2\2PR\t\3\2\2QP\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3"+
		"\2\2\2T\30\3\2\2\2US\3\2\2\2VW\n\4\2\2W\32\3\2\2\2X\\\5\35\17\2Y[\5\37"+
		"\20\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\34\3\2\2\2^\\\3\2\2\2"+
		"_`\t\5\2\2`\36\3\2\2\2aj\t\5\2\2bf\t\2\2\2ce\t\3\2\2dc\3\2\2\2eh\3\2\2"+
		"\2fd\3\2\2\2fg\3\2\2\2gj\3\2\2\2hf\3\2\2\2ia\3\2\2\2ib\3\2\2\2j \3\2\2"+
		"\2km\t\6\2\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2\2op\3\2\2\2pq\b\21"+
		"\2\2q\"\3\2\2\2\t\2JS\\fin\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
