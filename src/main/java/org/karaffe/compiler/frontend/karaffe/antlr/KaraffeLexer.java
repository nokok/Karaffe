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
		ENTRYPOINT=1, CLASS=2, PRINT=3, THIS=4, DEF=5, EQUAL=6, LPAREN=7, RPAREN=8, 
		LBRACE=9, RBRACE=10, PLUS=11, MINUS=12, StringLiteral=13, IntegerLiteral=14, 
		Identifier=15, WS=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ENTRYPOINT", "CLASS", "PRINT", "THIS", "DEF", "EQUAL", "LPAREN", "RPAREN", 
		"LBRACE", "RBRACE", "PLUS", "MINUS", "StringLiteral", "IntegerLiteral", 
		"StringChar", "Identifier", "Letter", "LetterOrDigit", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'entrypoint'", "'class'", "'print'", "'this'", "'def'", "'='", 
		"'('", "')'", "'{'", "'}'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ENTRYPOINT", "CLASS", "PRINT", "THIS", "DEF", "EQUAL", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "PLUS", "MINUS", "StringLiteral", "IntegerLiteral", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22\u0086\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3"+
		"\r\3\16\3\16\7\16Z\n\16\f\16\16\16]\13\16\3\16\3\16\3\17\3\17\7\17c\n"+
		"\17\f\17\16\17f\13\17\3\17\5\17i\n\17\3\20\3\20\3\21\3\21\7\21o\n\21\f"+
		"\21\16\21r\13\21\3\22\3\22\3\23\3\23\3\23\7\23y\n\23\f\23\16\23|\13\23"+
		"\5\23~\n\23\3\24\6\24\u0081\n\24\r\24\16\24\u0082\3\24\3\24\2\2\25\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\2"+
		"!\21#\2%\2\'\22\3\2\7\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\4\2C\\c|\5\2\13"+
		"\f\17\17\"\"\2\u0089\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2!\3\2\2\2"+
		"\2\'\3\2\2\2\3)\3\2\2\2\5\64\3\2\2\2\7:\3\2\2\2\t@\3\2\2\2\13E\3\2\2\2"+
		"\rI\3\2\2\2\17K\3\2\2\2\21M\3\2\2\2\23O\3\2\2\2\25Q\3\2\2\2\27S\3\2\2"+
		"\2\31U\3\2\2\2\33W\3\2\2\2\35h\3\2\2\2\37j\3\2\2\2!l\3\2\2\2#s\3\2\2\2"+
		"%}\3\2\2\2\'\u0080\3\2\2\2)*\7g\2\2*+\7p\2\2+,\7v\2\2,-\7t\2\2-.\7{\2"+
		"\2./\7r\2\2/\60\7q\2\2\60\61\7k\2\2\61\62\7p\2\2\62\63\7v\2\2\63\4\3\2"+
		"\2\2\64\65\7e\2\2\65\66\7n\2\2\66\67\7c\2\2\678\7u\2\289\7u\2\29\6\3\2"+
		"\2\2:;\7r\2\2;<\7t\2\2<=\7k\2\2=>\7p\2\2>?\7v\2\2?\b\3\2\2\2@A\7v\2\2"+
		"AB\7j\2\2BC\7k\2\2CD\7u\2\2D\n\3\2\2\2EF\7f\2\2FG\7g\2\2GH\7h\2\2H\f\3"+
		"\2\2\2IJ\7?\2\2J\16\3\2\2\2KL\7*\2\2L\20\3\2\2\2MN\7+\2\2N\22\3\2\2\2"+
		"OP\7}\2\2P\24\3\2\2\2QR\7\177\2\2R\26\3\2\2\2ST\7-\2\2T\30\3\2\2\2UV\7"+
		"/\2\2V\32\3\2\2\2W[\7$\2\2XZ\5\37\20\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2["+
		"\\\3\2\2\2\\^\3\2\2\2][\3\2\2\2^_\7$\2\2_\34\3\2\2\2`d\t\2\2\2ac\t\3\2"+
		"\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2ei\3\2\2\2fd\3\2\2\2gi\7\62"+
		"\2\2h`\3\2\2\2hg\3\2\2\2i\36\3\2\2\2jk\n\4\2\2k \3\2\2\2lp\5#\22\2mo\5"+
		"%\23\2nm\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\"\3\2\2\2rp\3\2\2\2st"+
		"\t\5\2\2t$\3\2\2\2u~\t\5\2\2vz\t\2\2\2wy\t\3\2\2xw\3\2\2\2y|\3\2\2\2z"+
		"x\3\2\2\2z{\3\2\2\2{~\3\2\2\2|z\3\2\2\2}u\3\2\2\2}v\3\2\2\2~&\3\2\2\2"+
		"\177\u0081\t\6\2\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3"+
		"\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\b\24\2\2\u0085"+
		"(\3\2\2\2\n\2[dhpz}\u0082\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
