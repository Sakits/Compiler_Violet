package Semantic.Grammar;
// Generated from Mxstar.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxstarParser extends Parser {
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
	public static final int
		RULE_program = 0, RULE_classDef = 1, RULE_functionDef = 2, RULE_lambdaFunc = 3, 
		RULE_constructFuncDef = 4, RULE_suite = 5, RULE_statement = 6, RULE_funcVarDef = 7, 
		RULE_varDef = 8, RULE_oneVarDef = 9, RULE_expression = 10, RULE_ifStat = 11, 
		RULE_whileStat = 12, RULE_forStat = 13, RULE_returnType = 14, RULE_varType = 15, 
		RULE_newType = 16, RULE_basicType = 17, RULE_primary = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "classDef", "functionDef", "lambdaFunc", "constructFuncDef", 
			"suite", "statement", "funcVarDef", "varDef", "oneVarDef", "expression", 
			"ifStat", "whileStat", "forStat", "returnType", "varType", "newType", 
			"basicType", "primary"
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

	@Override
	public String getGrammarFileName() { return "Mxstar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxstarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<FunctionDefContext> functionDef() {
			return getRuleContexts(FunctionDefContext.class);
		}
		public FunctionDefContext functionDef(int i) {
			return getRuleContext(FunctionDefContext.class,i);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<ClassDefContext> classDef() {
			return getRuleContexts(ClassDefContext.class);
		}
		public ClassDefContext classDef(int i) {
			return getRuleContext(ClassDefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				setState(43);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(38);
					functionDef();
					}
					break;
				case 2:
					{
					setState(39);
					varDef();
					setState(40);
					match(T__0);
					}
					break;
				case 3:
					{
					setState(42);
					classDef();
					}
					break;
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxstarParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public List<FunctionDefContext> functionDef() {
			return getRuleContexts(FunctionDefContext.class);
		}
		public FunctionDefContext functionDef(int i) {
			return getRuleContext(FunctionDefContext.class,i);
		}
		public List<ConstructFuncDefContext> constructFuncDef() {
			return getRuleContexts(ConstructFuncDefContext.class);
		}
		public ConstructFuncDefContext constructFuncDef(int i) {
			return getRuleContext(ConstructFuncDefContext.class,i);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(Class);
			setState(49);
			match(Identifier);
			setState(50);
			match(T__1);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(56);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(51);
					functionDef();
					}
					break;
				case 2:
					{
					setState(52);
					constructFuncDef();
					}
					break;
				case 3:
					{
					setState(53);
					varDef();
					setState(54);
					match(T__0);
					}
					break;
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61);
			match(T__2);
			setState(62);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefContext extends ParserRuleContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public List<FuncVarDefContext> funcVarDef() {
			return getRuleContexts(FuncVarDefContext.class);
		}
		public FuncVarDefContext funcVarDef(int i) {
			return getRuleContext(FuncVarDefContext.class,i);
		}
		public FunctionDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFunctionDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFunctionDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFunctionDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefContext functionDef() throws RecognitionException {
		FunctionDefContext _localctx = new FunctionDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			returnType();
			setState(65);
			match(Identifier);
			setState(66);
			match(T__3);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(67);
				funcVarDef();
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(68);
					match(T__4);
					setState(69);
					funcVarDef();
					}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(77);
			match(T__5);
			setState(78);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaFuncContext extends ParserRuleContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<FuncVarDefContext> funcVarDef() {
			return getRuleContexts(FuncVarDefContext.class);
		}
		public FuncVarDefContext funcVarDef(int i) {
			return getRuleContext(FuncVarDefContext.class,i);
		}
		public LambdaFuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaFunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterLambdaFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitLambdaFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLambdaFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaFuncContext lambdaFunc() throws RecognitionException {
		LambdaFuncContext _localctx = new LambdaFuncContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_lambdaFunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__6);
			setState(81);
			match(T__7);
			setState(82);
			match(T__8);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(83);
				match(T__3);
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
					{
					setState(84);
					funcVarDef();
					setState(89);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__4) {
						{
						{
						setState(85);
						match(T__4);
						setState(86);
						funcVarDef();
						}
						}
						setState(91);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(94);
				match(T__5);
				}
			}

			setState(97);
			match(T__9);
			setState(98);
			suite();
			setState(99);
			match(T__3);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << ConstString) | (1L << Identifier) | (1L << Number))) != 0)) {
				{
				setState(100);
				expression(0);
				}
			}

			setState(103);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructFuncDefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public List<FuncVarDefContext> funcVarDef() {
			return getRuleContexts(FuncVarDefContext.class);
		}
		public FuncVarDefContext funcVarDef(int i) {
			return getRuleContext(FuncVarDefContext.class,i);
		}
		public ConstructFuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructFuncDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterConstructFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitConstructFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitConstructFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructFuncDefContext constructFuncDef() throws RecognitionException {
		ConstructFuncDefContext _localctx = new ConstructFuncDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constructFuncDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(Identifier);
			setState(106);
			match(T__3);
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(107);
				funcVarDef();
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(108);
					match(T__4);
					setState(109);
					funcVarDef();
					}
					}
					setState(114);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(117);
			match(T__5);
			setState(118);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(T__1);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__3) | (1L << T__6) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << Null) | (1L << True) | (1L << False) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << This) | (1L << ConstString) | (1L << Identifier) | (1L << Number))) != 0)) {
				{
				{
				setState(121);
				statement();
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(127);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BreakTagContext extends StatementContext {
		public TerminalNode Break() { return getToken(MxstarParser.Break, 0); }
		public BreakTagContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBreakTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBreakTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBreakTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaTagContext extends StatementContext {
		public LambdaFuncContext lambdaFunc() {
			return getRuleContext(LambdaFuncContext.class,0);
		}
		public LambdaTagContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterLambdaTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitLambdaTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLambdaTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarTagContext extends StatementContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public VarTagContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileTagContext extends StatementContext {
		public WhileStatContext whileStat() {
			return getRuleContext(WhileStatContext.class,0);
		}
		public WhileTagContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterWhileTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitWhileTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitWhileTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnTagContext extends StatementContext {
		public TerminalNode Return() { return getToken(MxstarParser.Return, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnTagContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterReturnTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitReturnTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitReturnTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfTagContext extends StatementContext {
		public IfStatContext ifStat() {
			return getRuleContext(IfStatContext.class,0);
		}
		public IfTagContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterIfTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitIfTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitIfTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SemiTagContext extends StatementContext {
		public SemiTagContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterSemiTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitSemiTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitSemiTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockTagContext extends StatementContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public BlockTagContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBlockTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBlockTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBlockTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForTagContext extends StatementContext {
		public ForStatContext forStat() {
			return getRuleContext(ForStatContext.class,0);
		}
		public ForTagContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterForTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitForTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitForTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueTagContext extends StatementContext {
		public TerminalNode Continue() { return getToken(MxstarParser.Continue, 0); }
		public ContinueTagContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterContinueTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitContinueTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitContinueTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprTagContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprTagContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterExprTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitExprTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExprTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		int _la;
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new BlockTagContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				suite();
				}
				break;
			case 2:
				_localctx = new IfTagContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				ifStat();
				}
				break;
			case 3:
				_localctx = new ReturnTagContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				match(Return);
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << ConstString) | (1L << Identifier) | (1L << Number))) != 0)) {
					{
					setState(132);
					expression(0);
					}
				}

				setState(135);
				match(T__0);
				}
				break;
			case 4:
				_localctx = new WhileTagContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(136);
				whileStat();
				}
				break;
			case 5:
				_localctx = new ForTagContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(137);
				forStat();
				}
				break;
			case 6:
				_localctx = new BreakTagContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(138);
				match(Break);
				setState(139);
				match(T__0);
				}
				break;
			case 7:
				_localctx = new ContinueTagContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(140);
				match(Continue);
				setState(141);
				match(T__0);
				}
				break;
			case 8:
				_localctx = new VarTagContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(142);
				varDef();
				setState(143);
				match(T__0);
				}
				break;
			case 9:
				_localctx = new ExprTagContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(145);
				expression(0);
				setState(146);
				match(T__0);
				}
				break;
			case 10:
				_localctx = new LambdaTagContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(148);
				lambdaFunc();
				}
				break;
			case 11:
				_localctx = new SemiTagContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(149);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncVarDefContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public OneVarDefContext oneVarDef() {
			return getRuleContext(OneVarDefContext.class,0);
		}
		public FuncVarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcVarDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFuncVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFuncVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFuncVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncVarDefContext funcVarDef() throws RecognitionException {
		FuncVarDefContext _localctx = new FuncVarDefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcVarDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			varType();
			setState(153);
			oneVarDef();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public List<OneVarDefContext> oneVarDef() {
			return getRuleContexts(OneVarDefContext.class);
		}
		public OneVarDefContext oneVarDef(int i) {
			return getRuleContext(OneVarDefContext.class,i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			varType();
			setState(156);
			oneVarDef();
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(157);
				match(T__4);
				setState(158);
				oneVarDef();
				}
				}
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OneVarDefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OneVarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oneVarDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterOneVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitOneVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitOneVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OneVarDefContext oneVarDef() throws RecognitionException {
		OneVarDefContext _localctx = new OneVarDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_oneVarDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(Identifier);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(165);
				match(T__10);
				setState(166);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PrefixTagContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrefixTagContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterPrefixTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitPrefixTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitPrefixTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjTagContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public ObjTagContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterObjTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitObjTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitObjTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NvarTagContext extends ExpressionContext {
		public TerminalNode New() { return getToken(MxstarParser.New, 0); }
		public NewTypeContext newType() {
			return getRuleContext(NewTypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NvarTagContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterNvarTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitNvarTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitNvarTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignTagContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssignTagContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterAssignTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitAssignTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitAssignTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryTagContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryTagContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBinaryTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBinaryTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBinaryTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SuffixTagContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SuffixTagContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterSuffixTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitSuffixTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitSuffixTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimaryTagContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public PrimaryTagContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterPrimaryTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitPrimaryTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitPrimaryTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprinTagContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprinTagContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterExprinTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitExprinTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExprinTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddrTagContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AddrTagContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterAddrTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitAddrTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitAddrTag(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallTagContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CallTagContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterCallTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitCallTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitCallTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Null:
			case True:
			case False:
			case This:
			case ConstString:
			case Identifier:
			case Number:
				{
				_localctx = new PrimaryTagContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(170);
				primary();
				}
				break;
			case T__3:
				{
				_localctx = new ExprinTagContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(171);
				match(T__3);
				setState(172);
				expression(0);
				setState(173);
				match(T__5);
				}
				break;
			case New:
				{
				_localctx = new NvarTagContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(175);
				match(New);
				setState(176);
				newType();
				setState(182);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(177);
					match(T__3);
					setState(179);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << ConstString) | (1L << Identifier) | (1L << Number))) != 0)) {
						{
						setState(178);
						expression(0);
						}
					}

					setState(181);
					match(T__5);
					}
					break;
				}
				}
				break;
			case T__12:
				{
				_localctx = new PrefixTagContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(184);
				((PrefixTagContext)_localctx).op = match(T__12);
				setState(185);
				expression(17);
				}
				break;
			case T__13:
				{
				_localctx = new PrefixTagContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(186);
				((PrefixTagContext)_localctx).op = match(T__13);
				setState(187);
				expression(16);
				}
				break;
			case T__14:
			case T__15:
				{
				_localctx = new PrefixTagContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(188);
				((PrefixTagContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__14 || _la==T__15) ) {
					((PrefixTagContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(189);
				expression(15);
				}
				break;
			case T__16:
				{
				_localctx = new PrefixTagContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(190);
				((PrefixTagContext)_localctx).op = match(T__16);
				setState(191);
				expression(14);
				}
				break;
			case T__17:
				{
				_localctx = new PrefixTagContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(192);
				((PrefixTagContext)_localctx).op = match(T__17);
				setState(193);
				expression(13);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(254);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(252);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(196);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(197);
						((BinaryTagContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) ) {
							((BinaryTagContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(198);
						expression(12);
						}
						break;
					case 2:
						{
						_localctx = new BinaryTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(199);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(200);
						((BinaryTagContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__17) ) {
							((BinaryTagContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(201);
						expression(11);
						}
						break;
					case 3:
						{
						_localctx = new BinaryTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(202);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(203);
						((BinaryTagContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__21 || _la==T__22) ) {
							((BinaryTagContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(204);
						expression(10);
						}
						break;
					case 4:
						{
						_localctx = new BinaryTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(205);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(206);
						((BinaryTagContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) ) {
							((BinaryTagContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(207);
						expression(9);
						}
						break;
					case 5:
						{
						_localctx = new BinaryTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(208);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(209);
						((BinaryTagContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__27 || _la==T__28) ) {
							((BinaryTagContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(210);
						expression(8);
						}
						break;
					case 6:
						{
						_localctx = new BinaryTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(211);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(212);
						((BinaryTagContext)_localctx).op = match(T__7);
						setState(213);
						expression(7);
						}
						break;
					case 7:
						{
						_localctx = new BinaryTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(214);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(215);
						((BinaryTagContext)_localctx).op = match(T__29);
						setState(216);
						expression(6);
						}
						break;
					case 8:
						{
						_localctx = new BinaryTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(217);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(218);
						((BinaryTagContext)_localctx).op = match(T__30);
						setState(219);
						expression(5);
						}
						break;
					case 9:
						{
						_localctx = new BinaryTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(220);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(221);
						((BinaryTagContext)_localctx).op = match(T__31);
						setState(222);
						expression(4);
						}
						break;
					case 10:
						{
						_localctx = new BinaryTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(223);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(224);
						((BinaryTagContext)_localctx).op = match(T__32);
						setState(225);
						expression(3);
						}
						break;
					case 11:
						{
						_localctx = new AssignTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(226);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(227);
						match(T__10);
						setState(228);
						expression(1);
						}
						break;
					case 12:
						{
						_localctx = new AddrTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(229);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(230);
						match(T__6);
						setState(231);
						expression(0);
						setState(232);
						match(T__8);
						}
						break;
					case 13:
						{
						_localctx = new CallTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(234);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(235);
						match(T__3);
						setState(244);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << ConstString) | (1L << Identifier) | (1L << Number))) != 0)) {
							{
							setState(236);
							expression(0);
							setState(241);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__4) {
								{
								{
								setState(237);
								match(T__4);
								setState(238);
								expression(0);
								}
								}
								setState(243);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(246);
						match(T__5);
						}
						break;
					case 14:
						{
						_localctx = new ObjTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(247);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(248);
						match(T__11);
						setState(249);
						match(Identifier);
						}
						break;
					case 15:
						{
						_localctx = new SuffixTagContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(250);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(251);
						((SuffixTagContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__14 || _la==T__15) ) {
							((SuffixTagContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(256);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class IfStatContext extends ParserRuleContext {
		public StatementContext trueSt;
		public StatementContext falseSt;
		public TerminalNode If() { return getToken(MxstarParser.If, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxstarParser.Else, 0); }
		public IfStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterIfStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitIfStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitIfStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatContext ifStat() throws RecognitionException {
		IfStatContext _localctx = new IfStatContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_ifStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(If);
			setState(258);
			match(T__3);
			setState(259);
			expression(0);
			setState(260);
			match(T__5);
			setState(261);
			((IfStatContext)_localctx).trueSt = statement();
			setState(264);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(262);
				match(Else);
				setState(263);
				((IfStatContext)_localctx).falseSt = statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(MxstarParser.While, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterWhileStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitWhileStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitWhileStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatContext whileStat() throws RecognitionException {
		WhileStatContext _localctx = new WhileStatContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_whileStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(While);
			setState(267);
			match(T__3);
			setState(268);
			expression(0);
			setState(269);
			match(T__5);
			setState(270);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatContext extends ParserRuleContext {
		public ExpressionContext init;
		public ExpressionContext cond;
		public ExpressionContext next;
		public TerminalNode For() { return getToken(MxstarParser.For, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterForStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitForStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitForStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatContext forStat() throws RecognitionException {
		ForStatContext _localctx = new ForStatContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_forStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(For);
			setState(273);
			match(T__3);
			setState(276);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(274);
				varDef();
				}
				break;
			case 2:
				{
				setState(275);
				((ForStatContext)_localctx).init = expression(0);
				}
				break;
			}
			setState(278);
			match(T__0);
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << ConstString) | (1L << Identifier) | (1L << Number))) != 0)) {
				{
				setState(279);
				((ForStatContext)_localctx).cond = expression(0);
				}
			}

			setState(282);
			match(T__0);
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << This) | (1L << ConstString) | (1L << Identifier) | (1L << Number))) != 0)) {
				{
				setState(283);
				((ForStatContext)_localctx).next = expression(0);
				}
			}

			setState(286);
			match(T__5);
			setState(287);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypeContext extends ParserRuleContext {
		public TerminalNode Void() { return getToken(MxstarParser.Void, 0); }
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_returnType);
		try {
			setState(291);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Void:
				enterOuterAlt(_localctx, 1);
				{
				setState(289);
				match(Void);
				}
				break;
			case Int:
			case Bool:
			case String:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				varType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarTypeContext extends ParserRuleContext {
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public VarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarTypeContext varType() throws RecognitionException {
		VarTypeContext _localctx = new VarTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_varType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			basicType();
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(294);
				match(T__6);
				setState(295);
				match(T__8);
				}
				}
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewTypeContext extends ParserRuleContext {
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NewTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterNewType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitNewType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitNewType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewTypeContext newType() throws RecognitionException {
		NewTypeContext _localctx = new NewTypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_newType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			basicType();
			setState(308);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(302);
					match(T__6);
					setState(303);
					expression(0);
					setState(304);
					match(T__8);
					}
					} 
				}
				setState(310);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			setState(315);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(311);
					match(T__6);
					setState(312);
					match(T__8);
					}
					} 
				}
				setState(317);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BasicTypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode Bool() { return getToken(MxstarParser.Bool, 0); }
		public TerminalNode Int() { return getToken(MxstarParser.Int, 0); }
		public TerminalNode String() { return getToken(MxstarParser.String, 0); }
		public BasicTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBasicType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBasicType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBasicType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BasicTypeContext basicType() throws RecognitionException {
		BasicTypeContext _localctx = new BasicTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_basicType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode Number() { return getToken(MxstarParser.Number, 0); }
		public TerminalNode Null() { return getToken(MxstarParser.Null, 0); }
		public TerminalNode This() { return getToken(MxstarParser.This, 0); }
		public TerminalNode True() { return getToken(MxstarParser.True, 0); }
		public TerminalNode False() { return getToken(MxstarParser.False, 0); }
		public TerminalNode ConstString() { return getToken(MxstarParser.ConstString, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_primary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << This) | (1L << ConstString) | (1L << Identifier) | (1L << Number))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 20);
		case 12:
			return precpred(_ctx, 19);
		case 13:
			return precpred(_ctx, 18);
		case 14:
			return precpred(_ctx, 12);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u0145\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\7\2.\n\2\f\2\16\2\61\13\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3;\n\3\f\3\16\3>\13\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\7\4I\n\4\f\4\16\4L\13\4\5\4N\n\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\7\5Z\n\5\f\5\16\5]\13\5\5\5_\n\5\3\5\5\5b\n\5\3"+
		"\5\3\5\3\5\3\5\5\5h\n\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\7\6q\n\6\f\6\16\6"+
		"t\13\6\5\6v\n\6\3\6\3\6\3\6\3\7\3\7\7\7}\n\7\f\7\16\7\u0080\13\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\5\b\u0088\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\5\b\u0099\n\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u00a2"+
		"\n\n\f\n\16\n\u00a5\13\n\3\13\3\13\3\13\5\13\u00aa\n\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00b6\n\f\3\f\5\f\u00b9\n\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c5\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\7\f\u00f2\n\f\f\f\16\f\u00f5\13\f\5\f\u00f7\n\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\7\f\u00ff\n\f\f\f\16\f\u0102\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5"+
		"\r\u010b\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u0117"+
		"\n\17\3\17\3\17\5\17\u011b\n\17\3\17\3\17\5\17\u011f\n\17\3\17\3\17\3"+
		"\17\3\20\3\20\5\20\u0126\n\20\3\21\3\21\3\21\7\21\u012b\n\21\f\21\16\21"+
		"\u012e\13\21\3\22\3\22\3\22\3\22\3\22\7\22\u0135\n\22\f\22\16\22\u0138"+
		"\13\22\3\22\3\22\7\22\u013c\n\22\f\22\16\22\u013f\13\22\3\23\3\23\3\24"+
		"\3\24\3\24\2\3\26\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\n\3"+
		"\2\21\22\3\2\25\27\3\2\23\24\3\2\30\31\3\2\32\35\3\2\36\37\4\2$&\66\66"+
		"\5\2\'\')*\64\67\2\u0170\2/\3\2\2\2\4\62\3\2\2\2\6B\3\2\2\2\bR\3\2\2\2"+
		"\nk\3\2\2\2\fz\3\2\2\2\16\u0098\3\2\2\2\20\u009a\3\2\2\2\22\u009d\3\2"+
		"\2\2\24\u00a6\3\2\2\2\26\u00c4\3\2\2\2\30\u0103\3\2\2\2\32\u010c\3\2\2"+
		"\2\34\u0112\3\2\2\2\36\u0125\3\2\2\2 \u0127\3\2\2\2\"\u012f\3\2\2\2$\u0140"+
		"\3\2\2\2&\u0142\3\2\2\2(.\5\6\4\2)*\5\22\n\2*+\7\3\2\2+.\3\2\2\2,.\5\4"+
		"\3\2-(\3\2\2\2-)\3\2\2\2-,\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60"+
		"\3\3\2\2\2\61/\3\2\2\2\62\63\7\63\2\2\63\64\7\66\2\2\64<\7\4\2\2\65;\5"+
		"\6\4\2\66;\5\n\6\2\678\5\22\n\289\7\3\2\29;\3\2\2\2:\65\3\2\2\2:\66\3"+
		"\2\2\2:\67\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=?\3\2\2\2><\3\2\2\2?"+
		"@\7\5\2\2@A\7\3\2\2A\5\3\2\2\2BC\5\36\20\2CD\7\66\2\2DM\7\6\2\2EJ\5\20"+
		"\t\2FG\7\7\2\2GI\5\20\t\2HF\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KN\3"+
		"\2\2\2LJ\3\2\2\2ME\3\2\2\2MN\3\2\2\2NO\3\2\2\2OP\7\b\2\2PQ\5\f\7\2Q\7"+
		"\3\2\2\2RS\7\t\2\2ST\7\n\2\2Ta\7\13\2\2U^\7\6\2\2V[\5\20\t\2WX\7\7\2\2"+
		"XZ\5\20\t\2YW\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\_\3\2\2\2][\3\2"+
		"\2\2^V\3\2\2\2^_\3\2\2\2_`\3\2\2\2`b\7\b\2\2aU\3\2\2\2ab\3\2\2\2bc\3\2"+
		"\2\2cd\7\f\2\2de\5\f\7\2eg\7\6\2\2fh\5\26\f\2gf\3\2\2\2gh\3\2\2\2hi\3"+
		"\2\2\2ij\7\b\2\2j\t\3\2\2\2kl\7\66\2\2lu\7\6\2\2mr\5\20\t\2no\7\7\2\2"+
		"oq\5\20\t\2pn\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2sv\3\2\2\2tr\3\2\2"+
		"\2um\3\2\2\2uv\3\2\2\2vw\3\2\2\2wx\7\b\2\2xy\5\f\7\2y\13\3\2\2\2z~\7\4"+
		"\2\2{}\5\16\b\2|{\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081"+
		"\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\7\5\2\2\u0082\r\3\2\2\2\u0083\u0099"+
		"\5\f\7\2\u0084\u0099\5\30\r\2\u0085\u0087\7\61\2\2\u0086\u0088\5\26\f"+
		"\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0099"+
		"\7\3\2\2\u008a\u0099\5\32\16\2\u008b\u0099\5\34\17\2\u008c\u008d\7/\2"+
		"\2\u008d\u0099\7\3\2\2\u008e\u008f\7\60\2\2\u008f\u0099\7\3\2\2\u0090"+
		"\u0091\5\22\n\2\u0091\u0092\7\3\2\2\u0092\u0099\3\2\2\2\u0093\u0094\5"+
		"\26\f\2\u0094\u0095\7\3\2\2\u0095\u0099\3\2\2\2\u0096\u0099\5\b\5\2\u0097"+
		"\u0099\7\3\2\2\u0098\u0083\3\2\2\2\u0098\u0084\3\2\2\2\u0098\u0085\3\2"+
		"\2\2\u0098\u008a\3\2\2\2\u0098\u008b\3\2\2\2\u0098\u008c\3\2\2\2\u0098"+
		"\u008e\3\2\2\2\u0098\u0090\3\2\2\2\u0098\u0093\3\2\2\2\u0098\u0096\3\2"+
		"\2\2\u0098\u0097\3\2\2\2\u0099\17\3\2\2\2\u009a\u009b\5 \21\2\u009b\u009c"+
		"\5\24\13\2\u009c\21\3\2\2\2\u009d\u009e\5 \21\2\u009e\u00a3\5\24\13\2"+
		"\u009f\u00a0\7\7\2\2\u00a0\u00a2\5\24\13\2\u00a1\u009f\3\2\2\2\u00a2\u00a5"+
		"\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\23\3\2\2\2\u00a5"+
		"\u00a3\3\2\2\2\u00a6\u00a9\7\66\2\2\u00a7\u00a8\7\r\2\2\u00a8\u00aa\5"+
		"\26\f\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\25\3\2\2\2\u00ab"+
		"\u00ac\b\f\1\2\u00ac\u00c5\5&\24\2\u00ad\u00ae\7\6\2\2\u00ae\u00af\5\26"+
		"\f\2\u00af\u00b0\7\b\2\2\u00b0\u00c5\3\2\2\2\u00b1\u00b2\7\62\2\2\u00b2"+
		"\u00b8\5\"\22\2\u00b3\u00b5\7\6\2\2\u00b4\u00b6\5\26\f\2\u00b5\u00b4\3"+
		"\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\7\b\2\2\u00b8"+
		"\u00b3\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00c5\3\2\2\2\u00ba\u00bb\7\17"+
		"\2\2\u00bb\u00c5\5\26\f\23\u00bc\u00bd\7\20\2\2\u00bd\u00c5\5\26\f\22"+
		"\u00be\u00bf\t\2\2\2\u00bf\u00c5\5\26\f\21\u00c0\u00c1\7\23\2\2\u00c1"+
		"\u00c5\5\26\f\20\u00c2\u00c3\7\24\2\2\u00c3\u00c5\5\26\f\17\u00c4\u00ab"+
		"\3\2\2\2\u00c4\u00ad\3\2\2\2\u00c4\u00b1\3\2\2\2\u00c4\u00ba\3\2\2\2\u00c4"+
		"\u00bc\3\2\2\2\u00c4\u00be\3\2\2\2\u00c4\u00c0\3\2\2\2\u00c4\u00c2\3\2"+
		"\2\2\u00c5\u0100\3\2\2\2\u00c6\u00c7\f\r\2\2\u00c7\u00c8\t\3\2\2\u00c8"+
		"\u00ff\5\26\f\16\u00c9\u00ca\f\f\2\2\u00ca\u00cb\t\4\2\2\u00cb\u00ff\5"+
		"\26\f\r\u00cc\u00cd\f\13\2\2\u00cd\u00ce\t\5\2\2\u00ce\u00ff\5\26\f\f"+
		"\u00cf\u00d0\f\n\2\2\u00d0\u00d1\t\6\2\2\u00d1\u00ff\5\26\f\13\u00d2\u00d3"+
		"\f\t\2\2\u00d3\u00d4\t\7\2\2\u00d4\u00ff\5\26\f\n\u00d5\u00d6\f\b\2\2"+
		"\u00d6\u00d7\7\n\2\2\u00d7\u00ff\5\26\f\t\u00d8\u00d9\f\7\2\2\u00d9\u00da"+
		"\7 \2\2\u00da\u00ff\5\26\f\b\u00db\u00dc\f\6\2\2\u00dc\u00dd\7!\2\2\u00dd"+
		"\u00ff\5\26\f\7\u00de\u00df\f\5\2\2\u00df\u00e0\7\"\2\2\u00e0\u00ff\5"+
		"\26\f\6\u00e1\u00e2\f\4\2\2\u00e2\u00e3\7#\2\2\u00e3\u00ff\5\26\f\5\u00e4"+
		"\u00e5\f\3\2\2\u00e5\u00e6\7\r\2\2\u00e6\u00ff\5\26\f\3\u00e7\u00e8\f"+
		"\26\2\2\u00e8\u00e9\7\t\2\2\u00e9\u00ea\5\26\f\2\u00ea\u00eb\7\13\2\2"+
		"\u00eb\u00ff\3\2\2\2\u00ec\u00ed\f\25\2\2\u00ed\u00f6\7\6\2\2\u00ee\u00f3"+
		"\5\26\f\2\u00ef\u00f0\7\7\2\2\u00f0\u00f2\5\26\f\2\u00f1\u00ef\3\2\2\2"+
		"\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f7"+
		"\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00ee\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8\u00ff\7\b\2\2\u00f9\u00fa\f\24\2\2\u00fa\u00fb\7"+
		"\16\2\2\u00fb\u00ff\7\66\2\2\u00fc\u00fd\f\16\2\2\u00fd\u00ff\t\2\2\2"+
		"\u00fe\u00c6\3\2\2\2\u00fe\u00c9\3\2\2\2\u00fe\u00cc\3\2\2\2\u00fe\u00cf"+
		"\3\2\2\2\u00fe\u00d2\3\2\2\2\u00fe\u00d5\3\2\2\2\u00fe\u00d8\3\2\2\2\u00fe"+
		"\u00db\3\2\2\2\u00fe\u00de\3\2\2\2\u00fe\u00e1\3\2\2\2\u00fe\u00e4\3\2"+
		"\2\2\u00fe\u00e7\3\2\2\2\u00fe\u00ec\3\2\2\2\u00fe\u00f9\3\2\2\2\u00fe"+
		"\u00fc\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2"+
		"\2\2\u0101\27\3\2\2\2\u0102\u0100\3\2\2\2\u0103\u0104\7+\2\2\u0104\u0105"+
		"\7\6\2\2\u0105\u0106\5\26\f\2\u0106\u0107\7\b\2\2\u0107\u010a\5\16\b\2"+
		"\u0108\u0109\7,\2\2\u0109\u010b\5\16\b\2\u010a\u0108\3\2\2\2\u010a\u010b"+
		"\3\2\2\2\u010b\31\3\2\2\2\u010c\u010d\7.\2\2\u010d\u010e\7\6\2\2\u010e"+
		"\u010f\5\26\f\2\u010f\u0110\7\b\2\2\u0110\u0111\5\16\b\2\u0111\33\3\2"+
		"\2\2\u0112\u0113\7-\2\2\u0113\u0116\7\6\2\2\u0114\u0117\5\22\n\2\u0115"+
		"\u0117\5\26\f\2\u0116\u0114\3\2\2\2\u0116\u0115\3\2\2\2\u0116\u0117\3"+
		"\2\2\2\u0117\u0118\3\2\2\2\u0118\u011a\7\3\2\2\u0119\u011b\5\26\f\2\u011a"+
		"\u0119\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e\7\3"+
		"\2\2\u011d\u011f\5\26\f\2\u011e\u011d\3\2\2\2\u011e\u011f\3\2\2\2\u011f"+
		"\u0120\3\2\2\2\u0120\u0121\7\b\2\2\u0121\u0122\5\16\b\2\u0122\35\3\2\2"+
		"\2\u0123\u0126\7(\2\2\u0124\u0126\5 \21\2\u0125\u0123\3\2\2\2\u0125\u0124"+
		"\3\2\2\2\u0126\37\3\2\2\2\u0127\u012c\5$\23\2\u0128\u0129\7\t\2\2\u0129"+
		"\u012b\7\13\2\2\u012a\u0128\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a\3"+
		"\2\2\2\u012c\u012d\3\2\2\2\u012d!\3\2\2\2\u012e\u012c\3\2\2\2\u012f\u0136"+
		"\5$\23\2\u0130\u0131\7\t\2\2\u0131\u0132\5\26\f\2\u0132\u0133\7\13\2\2"+
		"\u0133\u0135\3\2\2\2\u0134\u0130\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0134"+
		"\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u013d\3\2\2\2\u0138\u0136\3\2\2\2\u0139"+
		"\u013a\7\t\2\2\u013a\u013c\7\13\2\2\u013b\u0139\3\2\2\2\u013c\u013f\3"+
		"\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e#\3\2\2\2\u013f\u013d"+
		"\3\2\2\2\u0140\u0141\t\b\2\2\u0141%\3\2\2\2\u0142\u0143\t\t\2\2\u0143"+
		"\'\3\2\2\2\"-/:<JM[^agru~\u0087\u0098\u00a3\u00a9\u00b5\u00b8\u00c4\u00f3"+
		"\u00f6\u00fe\u0100\u010a\u0116\u011a\u011e\u0125\u012c\u0136\u013d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}