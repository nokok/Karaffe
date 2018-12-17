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
		T__0=1, T__1=2, T__2=3, ENTRYPOINT=4, CLASS=5, DEF=6, EQUAL=7, LBRACE=8, 
		RBRACE=9, PLUS=10, MINUS=11, StringLiteral=12, IntegerLiteral=13, Identifier=14, 
		WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "ENTRYPOINT", "CLASS", "DEF", "EQUAL", "LBRACE", 
		"RBRACE", "PLUS", "MINUS", "StringLiteral", "IntegerLiteral", "StringChar", 
		"Identifier", "Letter", "LetterOrDigit", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'print'", "'('", "')'", "'entrypoint'", "'class'", "'def'", "'='", 
		"'{'", "'}'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "ENTRYPOINT", "CLASS", "DEF", "EQUAL", "LBRACE", 
		"RBRACE", "PLUS", "MINUS", "StringLiteral", "IntegerLiteral", "Identifier", 
		"WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21\177\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\7\rS\n\r\f\r\16\rV\13\r\3"+
		"\r\3\r\3\16\3\16\7\16\\\n\16\f\16\16\16_\13\16\3\16\5\16b\n\16\3\17\3"+
		"\17\3\20\3\20\7\20h\n\20\f\20\16\20k\13\20\3\21\3\21\3\22\3\22\3\22\7"+
		"\22r\n\22\f\22\16\22u\13\22\5\22w\n\22\3\23\6\23z\n\23\r\23\16\23{\3\23"+
		"\3\23\2\2\24\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\2\37\20!\2#\2%\21\3\2\7\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\4"+
		"\2C\\c|\5\2\13\f\17\17\"\"\2\u0082\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\37\3\2\2"+
		"\2\2%\3\2\2\2\3\'\3\2\2\2\5-\3\2\2\2\7/\3\2\2\2\t\61\3\2\2\2\13<\3\2\2"+
		"\2\rB\3\2\2\2\17F\3\2\2\2\21H\3\2\2\2\23J\3\2\2\2\25L\3\2\2\2\27N\3\2"+
		"\2\2\31P\3\2\2\2\33a\3\2\2\2\35c\3\2\2\2\37e\3\2\2\2!l\3\2\2\2#v\3\2\2"+
		"\2%y\3\2\2\2\'(\7r\2\2()\7t\2\2)*\7k\2\2*+\7p\2\2+,\7v\2\2,\4\3\2\2\2"+
		"-.\7*\2\2.\6\3\2\2\2/\60\7+\2\2\60\b\3\2\2\2\61\62\7g\2\2\62\63\7p\2\2"+
		"\63\64\7v\2\2\64\65\7t\2\2\65\66\7{\2\2\66\67\7r\2\2\678\7q\2\289\7k\2"+
		"\29:\7p\2\2:;\7v\2\2;\n\3\2\2\2<=\7e\2\2=>\7n\2\2>?\7c\2\2?@\7u\2\2@A"+
		"\7u\2\2A\f\3\2\2\2BC\7f\2\2CD\7g\2\2DE\7h\2\2E\16\3\2\2\2FG\7?\2\2G\20"+
		"\3\2\2\2HI\7}\2\2I\22\3\2\2\2JK\7\177\2\2K\24\3\2\2\2LM\7-\2\2M\26\3\2"+
		"\2\2NO\7/\2\2O\30\3\2\2\2PT\7$\2\2QS\5\35\17\2RQ\3\2\2\2SV\3\2\2\2TR\3"+
		"\2\2\2TU\3\2\2\2UW\3\2\2\2VT\3\2\2\2WX\7$\2\2X\32\3\2\2\2Y]\t\2\2\2Z\\"+
		"\t\3\2\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^b\3\2\2\2_]\3\2\2\2"+
		"`b\7\62\2\2aY\3\2\2\2a`\3\2\2\2b\34\3\2\2\2cd\n\4\2\2d\36\3\2\2\2ei\5"+
		"!\21\2fh\5#\22\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2j \3\2\2\2ki\3"+
		"\2\2\2lm\t\5\2\2m\"\3\2\2\2nw\t\5\2\2os\t\2\2\2pr\t\3\2\2qp\3\2\2\2ru"+
		"\3\2\2\2sq\3\2\2\2st\3\2\2\2tw\3\2\2\2us\3\2\2\2vn\3\2\2\2vo\3\2\2\2w"+
		"$\3\2\2\2xz\t\6\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|}\3\2\2\2"+
		"}~\b\23\2\2~&\3\2\2\2\n\2T]aisv{\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
