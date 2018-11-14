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
		ENTRYPOINT=1, CLASS=2, LBRACE=3, RBRACE=4, Identifier=5, WS=6;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ENTRYPOINT", "CLASS", "LBRACE", "RBRACE", "Identifier", "Letter", "LetterOrDigit", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'entrypoint'", "'class'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ENTRYPOINT", "CLASS", "LBRACE", "RBRACE", "Identifier", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\bB\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\7\6+\n\6\f\6\16\6.\13\6\3\7\3\7\3\b\3\b\3\b\7\b\65\n\b\f\b\16\b"+
		"8\13\b\5\b:\n\b\3\t\6\t=\n\t\r\t\16\t>\3\t\3\t\2\2\n\3\3\5\4\7\5\t\6\13"+
		"\7\r\2\17\2\21\b\3\2\6\4\2C\\c|\3\2\63;\3\2\62;\5\2\13\f\17\17\"\"\2C"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\21\3\2"+
		"\2\2\3\23\3\2\2\2\5\36\3\2\2\2\7$\3\2\2\2\t&\3\2\2\2\13(\3\2\2\2\r/\3"+
		"\2\2\2\179\3\2\2\2\21<\3\2\2\2\23\24\7g\2\2\24\25\7p\2\2\25\26\7v\2\2"+
		"\26\27\7t\2\2\27\30\7{\2\2\30\31\7r\2\2\31\32\7q\2\2\32\33\7k\2\2\33\34"+
		"\7p\2\2\34\35\7v\2\2\35\4\3\2\2\2\36\37\7e\2\2\37 \7n\2\2 !\7c\2\2!\""+
		"\7u\2\2\"#\7u\2\2#\6\3\2\2\2$%\7}\2\2%\b\3\2\2\2&\'\7\177\2\2\'\n\3\2"+
		"\2\2(,\5\r\7\2)+\5\17\b\2*)\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\f\3"+
		"\2\2\2.,\3\2\2\2/\60\t\2\2\2\60\16\3\2\2\2\61:\t\2\2\2\62\66\t\3\2\2\63"+
		"\65\t\4\2\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67:"+
		"\3\2\2\28\66\3\2\2\29\61\3\2\2\29\62\3\2\2\2:\20\3\2\2\2;=\t\5\2\2<;\3"+
		"\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?@\3\2\2\2@A\b\t\2\2A\22\3\2\2\2\7"+
		"\2,\669>\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
