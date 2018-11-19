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
		StringLiteral=9, IntegerLiteral=10, Identifier=11, WS=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "ENTRYPOINT", "CLASS", "LBRACE", "RBRACE", "PLUS", 
		"StringLiteral", "IntegerLiteral", "StringChar", "Identifier", "Letter", 
		"LetterOrDigit", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'print'", "'('", "')'", "'entrypoint'", "'class'", "'{'", "'}'", 
		"'+'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "ENTRYPOINT", "CLASS", "LBRACE", "RBRACE", "PLUS", 
		"StringLiteral", "IntegerLiteral", "Identifier", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16n\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\7\nE\n\n\f\n\16\nH"+
		"\13\n\3\n\3\n\3\13\3\13\7\13N\n\13\f\13\16\13Q\13\13\3\f\3\f\3\r\3\r\7"+
		"\rW\n\r\f\r\16\rZ\13\r\3\16\3\16\3\17\3\17\3\17\7\17a\n\17\f\17\16\17"+
		"d\13\17\5\17f\n\17\3\20\6\20i\n\20\r\20\16\20j\3\20\3\20\2\2\21\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\2\31\r\33\2\35\2\37\16\3\2"+
		"\7\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\4\2C\\c|\5\2\13\f\17\17\"\"\2p\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\31\3\2\2\2\2"+
		"\37\3\2\2\2\3!\3\2\2\2\5\'\3\2\2\2\7)\3\2\2\2\t+\3\2\2\2\13\66\3\2\2\2"+
		"\r<\3\2\2\2\17>\3\2\2\2\21@\3\2\2\2\23B\3\2\2\2\25K\3\2\2\2\27R\3\2\2"+
		"\2\31T\3\2\2\2\33[\3\2\2\2\35e\3\2\2\2\37h\3\2\2\2!\"\7r\2\2\"#\7t\2\2"+
		"#$\7k\2\2$%\7p\2\2%&\7v\2\2&\4\3\2\2\2\'(\7*\2\2(\6\3\2\2\2)*\7+\2\2*"+
		"\b\3\2\2\2+,\7g\2\2,-\7p\2\2-.\7v\2\2./\7t\2\2/\60\7{\2\2\60\61\7r\2\2"+
		"\61\62\7q\2\2\62\63\7k\2\2\63\64\7p\2\2\64\65\7v\2\2\65\n\3\2\2\2\66\67"+
		"\7e\2\2\678\7n\2\289\7c\2\29:\7u\2\2:;\7u\2\2;\f\3\2\2\2<=\7}\2\2=\16"+
		"\3\2\2\2>?\7\177\2\2?\20\3\2\2\2@A\7-\2\2A\22\3\2\2\2BF\7$\2\2CE\5\27"+
		"\f\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\7$"+
		"\2\2J\24\3\2\2\2KO\t\2\2\2LN\t\3\2\2ML\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3"+
		"\2\2\2P\26\3\2\2\2QO\3\2\2\2RS\n\4\2\2S\30\3\2\2\2TX\5\33\16\2UW\5\35"+
		"\17\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\32\3\2\2\2ZX\3\2\2\2[\\"+
		"\t\5\2\2\\\34\3\2\2\2]f\t\5\2\2^b\t\2\2\2_a\t\3\2\2`_\3\2\2\2ad\3\2\2"+
		"\2b`\3\2\2\2bc\3\2\2\2cf\3\2\2\2db\3\2\2\2e]\3\2\2\2e^\3\2\2\2f\36\3\2"+
		"\2\2gi\t\6\2\2hg\3\2\2\2ij\3\2\2\2jh\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\b\20"+
		"\2\2m \3\2\2\2\t\2FOXbej\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
