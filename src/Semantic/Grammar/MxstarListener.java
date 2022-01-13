// Generated from Mxstar.g4 by ANTLR 4.9.2
package Semantic.Grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxstarParser}.
 */
public interface MxstarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(MxstarParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(MxstarParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDef(MxstarParser.FunctionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDef(MxstarParser.FunctionDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#lambdaFunc}.
	 * @param ctx the parse tree
	 */
	void enterLambdaFunc(MxstarParser.LambdaFuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#lambdaFunc}.
	 * @param ctx the parse tree
	 */
	void exitLambdaFunc(MxstarParser.LambdaFuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#constructFuncDef}.
	 * @param ctx the parse tree
	 */
	void enterConstructFuncDef(MxstarParser.ConstructFuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#constructFuncDef}.
	 * @param ctx the parse tree
	 */
	void exitConstructFuncDef(MxstarParser.ConstructFuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(MxstarParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(MxstarParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockTag(MxstarParser.BlockTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockTag(MxstarParser.BlockTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfTag(MxstarParser.IfTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfTag(MxstarParser.IfTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnTag(MxstarParser.ReturnTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnTag(MxstarParser.ReturnTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileTag(MxstarParser.WhileTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileTag(MxstarParser.WhileTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForTag(MxstarParser.ForTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForTag(MxstarParser.ForTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakTag(MxstarParser.BreakTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakTag(MxstarParser.BreakTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueTag(MxstarParser.ContinueTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueTag(MxstarParser.ContinueTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarTag(MxstarParser.VarTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarTag(MxstarParser.VarTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprTag(MxstarParser.ExprTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprTag(MxstarParser.ExprTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code semiTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSemiTag(MxstarParser.SemiTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code semiTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSemiTag(MxstarParser.SemiTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#funcVarDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncVarDef(MxstarParser.FuncVarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#funcVarDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncVarDef(MxstarParser.FuncVarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(MxstarParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(MxstarParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#oneVarDef}.
	 * @param ctx the parse tree
	 */
	void enterOneVarDef(MxstarParser.OneVarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#oneVarDef}.
	 * @param ctx the parse tree
	 */
	void exitOneVarDef(MxstarParser.OneVarDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prefixTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixTag(MxstarParser.PrefixTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prefixTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixTag(MxstarParser.PrefixTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lambdaTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaTag(MxstarParser.LambdaTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lambdaTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaTag(MxstarParser.LambdaTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterObjTag(MxstarParser.ObjTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitObjTag(MxstarParser.ObjTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nvarTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNvarTag(MxstarParser.NvarTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nvarTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNvarTag(MxstarParser.NvarTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignTag(MxstarParser.AssignTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignTag(MxstarParser.AssignTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryTag(MxstarParser.BinaryTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryTag(MxstarParser.BinaryTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuffixTag(MxstarParser.SuffixTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuffixTag(MxstarParser.SuffixTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryTag(MxstarParser.PrimaryTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryTag(MxstarParser.PrimaryTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprinTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprinTag(MxstarParser.ExprinTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprinTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprinTag(MxstarParser.ExprinTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addrTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddrTag(MxstarParser.AddrTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addrTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddrTag(MxstarParser.AddrTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCallTag(MxstarParser.CallTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCallTag(MxstarParser.CallTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(MxstarParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(MxstarParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(MxstarParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(MxstarParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#forStat}.
	 * @param ctx the parse tree
	 */
	void enterForStat(MxstarParser.ForStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#forStat}.
	 * @param ctx the parse tree
	 */
	void exitForStat(MxstarParser.ForStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(MxstarParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(MxstarParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterVarType(MxstarParser.VarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitVarType(MxstarParser.VarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#newType}.
	 * @param ctx the parse tree
	 */
	void enterNewType(MxstarParser.NewTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#newType}.
	 * @param ctx the parse tree
	 */
	void exitNewType(MxstarParser.NewTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#basicType}.
	 * @param ctx the parse tree
	 */
	void enterBasicType(MxstarParser.BasicTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#basicType}.
	 * @param ctx the parse tree
	 */
	void exitBasicType(MxstarParser.BasicTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(MxstarParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(MxstarParser.PrimaryContext ctx);
}