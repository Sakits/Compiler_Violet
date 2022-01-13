// Generated from Mxstar.g4 by ANTLR 4.9.2
package Semantic.Grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxstarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, Int=34, Bool=35, String=36, Null=37, Void=38, True=39, 
		False=40, If=41, Else=42, For=43, While=44, Break=45, Continue=46, Return=47, 
		New=48, Class=49, This=50, ConstString=51, Identifier=52, Number=53, Indent=54, 
		Newline=55, Comment=56;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"Int", "Bool", "String", "Null", "Void", "True", "False", "If", "Else", 
			"For", "While", "Break", "Continue", "Return", "New", "Class", "This", 
			"ConstString", "Identifier", "Number", "Indent", "Newline", "Comment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'{'", "'}'", "'('", "','", "')'", "'['", "'&'", "']'", 
			"'->'", "'='", "'.'", "'!'", "'~'", "'++'", "'--'", "'-'", "'+'", "'*'", 
			"'/'", "'%'", "'<<'", "'>>'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", 
			"'^'", "'|'", "'&&'", "'||'", "'int'", "'bool'", "'string'", "'null'", 
			"'void'", "'true'", "'false'", "'if'", "'else'", "'for'", "'while'", 
			"'break'", "'continue'", "'return'", "'new'", "'class'", "'this'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "Int", "Bool", 
			"String", "Null", "Void", "True", "False", "If", "Else", "For", "While", 
			"Break", "Continue", "Return", "New", "Class", "This", "ConstString", 
			"Identifier", "Number", "Indent", "Newline", "Comment"
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


	public MxstarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mxstar.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2:\u0162\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36"+
		"\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3"+
		"$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3("+
		"\3(\3(\3)\3)\3)\3)\3)\3)\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3-\3-\3-"+
		"\3-\3-\3-\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62"+
		"\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\7\64\u0123\n\64"+
		"\f\64\16\64\u0126\13\64\3\64\3\64\3\65\3\65\7\65\u012c\n\65\f\65\16\65"+
		"\u012f\13\65\3\66\3\66\7\66\u0133\n\66\f\66\16\66\u0136\13\66\3\66\5\66"+
		"\u0139\n\66\3\67\6\67\u013c\n\67\r\67\16\67\u013d\3\67\3\67\38\38\58\u0144"+
		"\n8\38\58\u0147\n8\38\38\39\39\39\39\79\u014f\n9\f9\169\u0152\139\39\3"+
		"9\39\39\79\u0158\n9\f9\169\u015b\139\39\39\59\u015f\n9\39\39\4\u0124\u0159"+
		"\2:\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67"+
		"m8o9q:\3\2\b\4\2C\\c|\6\2\62;C\\aac|\3\2\63;\3\2\62;\4\2\13\13\"\"\4\2"+
		"\f\f\17\17\2\u016d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2"+
		"\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k"+
		"\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\3s\3\2\2\2\5u\3\2\2\2\7w\3\2"+
		"\2\2\ty\3\2\2\2\13{\3\2\2\2\r}\3\2\2\2\17\177\3\2\2\2\21\u0081\3\2\2\2"+
		"\23\u0083\3\2\2\2\25\u0085\3\2\2\2\27\u0088\3\2\2\2\31\u008a\3\2\2\2\33"+
		"\u008c\3\2\2\2\35\u008e\3\2\2\2\37\u0090\3\2\2\2!\u0093\3\2\2\2#\u0096"+
		"\3\2\2\2%\u0098\3\2\2\2\'\u009a\3\2\2\2)\u009c\3\2\2\2+\u009e\3\2\2\2"+
		"-\u00a0\3\2\2\2/\u00a3\3\2\2\2\61\u00a6\3\2\2\2\63\u00a8\3\2\2\2\65\u00ab"+
		"\3\2\2\2\67\u00ad\3\2\2\29\u00b0\3\2\2\2;\u00b3\3\2\2\2=\u00b6\3\2\2\2"+
		"?\u00b8\3\2\2\2A\u00ba\3\2\2\2C\u00bd\3\2\2\2E\u00c0\3\2\2\2G\u00c4\3"+
		"\2\2\2I\u00c9\3\2\2\2K\u00d0\3\2\2\2M\u00d5\3\2\2\2O\u00da\3\2\2\2Q\u00df"+
		"\3\2\2\2S\u00e5\3\2\2\2U\u00e8\3\2\2\2W\u00ed\3\2\2\2Y\u00f1\3\2\2\2["+
		"\u00f7\3\2\2\2]\u00fd\3\2\2\2_\u0106\3\2\2\2a\u010d\3\2\2\2c\u0111\3\2"+
		"\2\2e\u0117\3\2\2\2g\u011c\3\2\2\2i\u0129\3\2\2\2k\u0138\3\2\2\2m\u013b"+
		"\3\2\2\2o\u0146\3\2\2\2q\u015e\3\2\2\2st\7=\2\2t\4\3\2\2\2uv\7}\2\2v\6"+
		"\3\2\2\2wx\7\177\2\2x\b\3\2\2\2yz\7*\2\2z\n\3\2\2\2{|\7.\2\2|\f\3\2\2"+
		"\2}~\7+\2\2~\16\3\2\2\2\177\u0080\7]\2\2\u0080\20\3\2\2\2\u0081\u0082"+
		"\7(\2\2\u0082\22\3\2\2\2\u0083\u0084\7_\2\2\u0084\24\3\2\2\2\u0085\u0086"+
		"\7/\2\2\u0086\u0087\7@\2\2\u0087\26\3\2\2\2\u0088\u0089\7?\2\2\u0089\30"+
		"\3\2\2\2\u008a\u008b\7\60\2\2\u008b\32\3\2\2\2\u008c\u008d\7#\2\2\u008d"+
		"\34\3\2\2\2\u008e\u008f\7\u0080\2\2\u008f\36\3\2\2\2\u0090\u0091\7-\2"+
		"\2\u0091\u0092\7-\2\2\u0092 \3\2\2\2\u0093\u0094\7/\2\2\u0094\u0095\7"+
		"/\2\2\u0095\"\3\2\2\2\u0096\u0097\7/\2\2\u0097$\3\2\2\2\u0098\u0099\7"+
		"-\2\2\u0099&\3\2\2\2\u009a\u009b\7,\2\2\u009b(\3\2\2\2\u009c\u009d\7\61"+
		"\2\2\u009d*\3\2\2\2\u009e\u009f\7\'\2\2\u009f,\3\2\2\2\u00a0\u00a1\7>"+
		"\2\2\u00a1\u00a2\7>\2\2\u00a2.\3\2\2\2\u00a3\u00a4\7@\2\2\u00a4\u00a5"+
		"\7@\2\2\u00a5\60\3\2\2\2\u00a6\u00a7\7>\2\2\u00a7\62\3\2\2\2\u00a8\u00a9"+
		"\7>\2\2\u00a9\u00aa\7?\2\2\u00aa\64\3\2\2\2\u00ab\u00ac\7@\2\2\u00ac\66"+
		"\3\2\2\2\u00ad\u00ae\7@\2\2\u00ae\u00af\7?\2\2\u00af8\3\2\2\2\u00b0\u00b1"+
		"\7?\2\2\u00b1\u00b2\7?\2\2\u00b2:\3\2\2\2\u00b3\u00b4\7#\2\2\u00b4\u00b5"+
		"\7?\2\2\u00b5<\3\2\2\2\u00b6\u00b7\7`\2\2\u00b7>\3\2\2\2\u00b8\u00b9\7"+
		"~\2\2\u00b9@\3\2\2\2\u00ba\u00bb\7(\2\2\u00bb\u00bc\7(\2\2\u00bcB\3\2"+
		"\2\2\u00bd\u00be\7~\2\2\u00be\u00bf\7~\2\2\u00bfD\3\2\2\2\u00c0\u00c1"+
		"\7k\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\7v\2\2\u00c3F\3\2\2\2\u00c4\u00c5"+
		"\7d\2\2\u00c5\u00c6\7q\2\2\u00c6\u00c7\7q\2\2\u00c7\u00c8\7n\2\2\u00c8"+
		"H\3\2\2\2\u00c9\u00ca\7u\2\2\u00ca\u00cb\7v\2\2\u00cb\u00cc\7t\2\2\u00cc"+
		"\u00cd\7k\2\2\u00cd\u00ce\7p\2\2\u00ce\u00cf\7i\2\2\u00cfJ\3\2\2\2\u00d0"+
		"\u00d1\7p\2\2\u00d1\u00d2\7w\2\2\u00d2\u00d3\7n\2\2\u00d3\u00d4\7n\2\2"+
		"\u00d4L\3\2\2\2\u00d5\u00d6\7x\2\2\u00d6\u00d7\7q\2\2\u00d7\u00d8\7k\2"+
		"\2\u00d8\u00d9\7f\2\2\u00d9N\3\2\2\2\u00da\u00db\7v\2\2\u00db\u00dc\7"+
		"t\2\2\u00dc\u00dd\7w\2\2\u00dd\u00de\7g\2\2\u00deP\3\2\2\2\u00df\u00e0"+
		"\7h\2\2\u00e0\u00e1\7c\2\2\u00e1\u00e2\7n\2\2\u00e2\u00e3\7u\2\2\u00e3"+
		"\u00e4\7g\2\2\u00e4R\3\2\2\2\u00e5\u00e6\7k\2\2\u00e6\u00e7\7h\2\2\u00e7"+
		"T\3\2\2\2\u00e8\u00e9\7g\2\2\u00e9\u00ea\7n\2\2\u00ea\u00eb\7u\2\2\u00eb"+
		"\u00ec\7g\2\2\u00ecV\3\2\2\2\u00ed\u00ee\7h\2\2\u00ee\u00ef\7q\2\2\u00ef"+
		"\u00f0\7t\2\2\u00f0X\3\2\2\2\u00f1\u00f2\7y\2\2\u00f2\u00f3\7j\2\2\u00f3"+
		"\u00f4\7k\2\2\u00f4\u00f5\7n\2\2\u00f5\u00f6\7g\2\2\u00f6Z\3\2\2\2\u00f7"+
		"\u00f8\7d\2\2\u00f8\u00f9\7t\2\2\u00f9\u00fa\7g\2\2\u00fa\u00fb\7c\2\2"+
		"\u00fb\u00fc\7m\2\2\u00fc\\\3\2\2\2\u00fd\u00fe\7e\2\2\u00fe\u00ff\7q"+
		"\2\2\u00ff\u0100\7p\2\2\u0100\u0101\7v\2\2\u0101\u0102\7k\2\2\u0102\u0103"+
		"\7p\2\2\u0103\u0104\7w\2\2\u0104\u0105\7g\2\2\u0105^\3\2\2\2\u0106\u0107"+
		"\7t\2\2\u0107\u0108\7g\2\2\u0108\u0109\7v\2\2\u0109\u010a\7w\2\2\u010a"+
		"\u010b\7t\2\2\u010b\u010c\7p\2\2\u010c`\3\2\2\2\u010d\u010e\7p\2\2\u010e"+
		"\u010f\7g\2\2\u010f\u0110\7y\2\2\u0110b\3\2\2\2\u0111\u0112\7e\2\2\u0112"+
		"\u0113\7n\2\2\u0113\u0114\7c\2\2\u0114\u0115\7u\2\2\u0115\u0116\7u\2\2"+
		"\u0116d\3\2\2\2\u0117\u0118\7v\2\2\u0118\u0119\7j\2\2\u0119\u011a\7k\2"+
		"\2\u011a\u011b\7u\2\2\u011bf\3\2\2\2\u011c\u0124\7$\2\2\u011d\u011e\7"+
		"^\2\2\u011e\u0123\7$\2\2\u011f\u0120\7^\2\2\u0120\u0123\7^\2\2\u0121\u0123"+
		"\13\2\2\2\u0122\u011d\3\2\2\2\u0122\u011f\3\2\2\2\u0122\u0121\3\2\2\2"+
		"\u0123\u0126\3\2\2\2\u0124\u0125\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0127"+
		"\3\2\2\2\u0126\u0124\3\2\2\2\u0127\u0128\7$\2\2\u0128h\3\2\2\2\u0129\u012d"+
		"\t\2\2\2\u012a\u012c\t\3\2\2\u012b\u012a\3\2\2\2\u012c\u012f\3\2\2\2\u012d"+
		"\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012ej\3\2\2\2\u012f\u012d\3\2\2\2"+
		"\u0130\u0134\t\4\2\2\u0131\u0133\t\5\2\2\u0132\u0131\3\2\2\2\u0133\u0136"+
		"\3\2\2\2\u0134\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0139\3\2\2\2\u0136"+
		"\u0134\3\2\2\2\u0137\u0139\7\62\2\2\u0138\u0130\3\2\2\2\u0138\u0137\3"+
		"\2\2\2\u0139l\3\2\2\2\u013a\u013c\t\6\2\2\u013b\u013a\3\2\2\2\u013c\u013d"+
		"\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013f\3\2\2\2\u013f"+
		"\u0140\b\67\2\2\u0140n\3\2\2\2\u0141\u0143\7\17\2\2\u0142\u0144\7\f\2"+
		"\2\u0143\u0142\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0147\3\2\2\2\u0145\u0147"+
		"\7\f\2\2\u0146\u0141\3\2\2\2\u0146\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148"+
		"\u0149\b8\2\2\u0149p\3\2\2\2\u014a\u014b\7\61\2\2\u014b\u014c\7\61\2\2"+
		"\u014c\u0150\3\2\2\2\u014d\u014f\n\7\2\2\u014e\u014d\3\2\2\2\u014f\u0152"+
		"\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u015f\3\2\2\2\u0152"+
		"\u0150\3\2\2\2\u0153\u0154\7\61\2\2\u0154\u0155\7,\2\2\u0155\u0159\3\2"+
		"\2\2\u0156\u0158\13\2\2\2\u0157\u0156\3\2\2\2\u0158\u015b\3\2\2\2\u0159"+
		"\u015a\3\2\2\2\u0159\u0157\3\2\2\2\u015a\u015c\3\2\2\2\u015b\u0159\3\2"+
		"\2\2\u015c\u015d\7,\2\2\u015d\u015f\7\61\2\2\u015e\u014a\3\2\2\2\u015e"+
		"\u0153\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0161\b9\2\2\u0161r\3\2\2\2\16"+
		"\2\u0122\u0124\u012d\u0134\u0138\u013d\u0143\u0146\u0150\u0159\u015e\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}