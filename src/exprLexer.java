// Generated from /Users/jamesbayley/Documents/uni/y3/CE305/Assignment1/src/expr.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class exprLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, TRUE=2, FALSE=3, ASSIGN=4, SEMICOLON=5, LPAREN=6, RPAREN=7, ADD=8, 
		SUB=9, MUL=10, DIV=11, LT=12, GT=13, LE=14, GE=15, EQ=16, NEQ=17, AND=18, 
		OR=19, NOT=20, ID=21, INT=22, WS=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"VAR", "TRUE", "FALSE", "ASSIGN", "SEMICOLON", "LPAREN", "RPAREN", "ADD", 
			"SUB", "MUL", "DIV", "LT", "GT", "LE", "GE", "EQ", "NEQ", "AND", "OR", 
			"NOT", "ID", "INT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'var'", "'true'", "'false'", "'='", "';'", "'('", "')'", "'+'", 
			"'-'", "'*'", "'/'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!='", "'&&'", 
			"'||'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "VAR", "TRUE", "FALSE", "ASSIGN", "SEMICOLON", "LPAREN", "RPAREN", 
			"ADD", "SUB", "MUL", "DIV", "LT", "GT", "LE", "GE", "EQ", "NEQ", "AND", 
			"OR", "NOT", "ID", "INT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public exprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "expr.g4"; }

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
		"\u0004\u0000\u0017y\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0005\u0014i\b\u0014\n\u0014\f\u0014"+
		"l\t\u0014\u0001\u0015\u0004\u0015o\b\u0015\u000b\u0015\f\u0015p\u0001"+
		"\u0016\u0004\u0016t\b\u0016\u000b\u0016\f\u0016u\u0001\u0016\u0001\u0016"+
		"\u0000\u0000\u0017\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016-\u0017\u0001\u0000\u0004\u0002\u0000AZaz\u0003\u000009AZaz\u0001"+
		"\u000009\u0003\u0000\t\n\r\r  {\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-"+
		"\u0001\u0000\u0000\u0000\u0001/\u0001\u0000\u0000\u0000\u00033\u0001\u0000"+
		"\u0000\u0000\u00058\u0001\u0000\u0000\u0000\u0007>\u0001\u0000\u0000\u0000"+
		"\t@\u0001\u0000\u0000\u0000\u000bB\u0001\u0000\u0000\u0000\rD\u0001\u0000"+
		"\u0000\u0000\u000fF\u0001\u0000\u0000\u0000\u0011H\u0001\u0000\u0000\u0000"+
		"\u0013J\u0001\u0000\u0000\u0000\u0015L\u0001\u0000\u0000\u0000\u0017N"+
		"\u0001\u0000\u0000\u0000\u0019P\u0001\u0000\u0000\u0000\u001bR\u0001\u0000"+
		"\u0000\u0000\u001dU\u0001\u0000\u0000\u0000\u001fX\u0001\u0000\u0000\u0000"+
		"![\u0001\u0000\u0000\u0000#^\u0001\u0000\u0000\u0000%a\u0001\u0000\u0000"+
		"\u0000\'d\u0001\u0000\u0000\u0000)f\u0001\u0000\u0000\u0000+n\u0001\u0000"+
		"\u0000\u0000-s\u0001\u0000\u0000\u0000/0\u0005v\u0000\u000001\u0005a\u0000"+
		"\u000012\u0005r\u0000\u00002\u0002\u0001\u0000\u0000\u000034\u0005t\u0000"+
		"\u000045\u0005r\u0000\u000056\u0005u\u0000\u000067\u0005e\u0000\u0000"+
		"7\u0004\u0001\u0000\u0000\u000089\u0005f\u0000\u00009:\u0005a\u0000\u0000"+
		":;\u0005l\u0000\u0000;<\u0005s\u0000\u0000<=\u0005e\u0000\u0000=\u0006"+
		"\u0001\u0000\u0000\u0000>?\u0005=\u0000\u0000?\b\u0001\u0000\u0000\u0000"+
		"@A\u0005;\u0000\u0000A\n\u0001\u0000\u0000\u0000BC\u0005(\u0000\u0000"+
		"C\f\u0001\u0000\u0000\u0000DE\u0005)\u0000\u0000E\u000e\u0001\u0000\u0000"+
		"\u0000FG\u0005+\u0000\u0000G\u0010\u0001\u0000\u0000\u0000HI\u0005-\u0000"+
		"\u0000I\u0012\u0001\u0000\u0000\u0000JK\u0005*\u0000\u0000K\u0014\u0001"+
		"\u0000\u0000\u0000LM\u0005/\u0000\u0000M\u0016\u0001\u0000\u0000\u0000"+
		"NO\u0005<\u0000\u0000O\u0018\u0001\u0000\u0000\u0000PQ\u0005>\u0000\u0000"+
		"Q\u001a\u0001\u0000\u0000\u0000RS\u0005<\u0000\u0000ST\u0005=\u0000\u0000"+
		"T\u001c\u0001\u0000\u0000\u0000UV\u0005>\u0000\u0000VW\u0005=\u0000\u0000"+
		"W\u001e\u0001\u0000\u0000\u0000XY\u0005=\u0000\u0000YZ\u0005=\u0000\u0000"+
		"Z \u0001\u0000\u0000\u0000[\\\u0005!\u0000\u0000\\]\u0005=\u0000\u0000"+
		"]\"\u0001\u0000\u0000\u0000^_\u0005&\u0000\u0000_`\u0005&\u0000\u0000"+
		"`$\u0001\u0000\u0000\u0000ab\u0005|\u0000\u0000bc\u0005|\u0000\u0000c"+
		"&\u0001\u0000\u0000\u0000de\u0005!\u0000\u0000e(\u0001\u0000\u0000\u0000"+
		"fj\u0007\u0000\u0000\u0000gi\u0007\u0001\u0000\u0000hg\u0001\u0000\u0000"+
		"\u0000il\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000jk\u0001\u0000"+
		"\u0000\u0000k*\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000mo\u0007"+
		"\u0002\u0000\u0000nm\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000"+
		"pn\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000q,\u0001\u0000\u0000"+
		"\u0000rt\u0007\u0003\u0000\u0000sr\u0001\u0000\u0000\u0000tu\u0001\u0000"+
		"\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000vw\u0001"+
		"\u0000\u0000\u0000wx\u0006\u0016\u0000\u0000x.\u0001\u0000\u0000\u0000"+
		"\u0004\u0000jpu\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}