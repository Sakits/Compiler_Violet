package Semantic.Grammar;
// Generated from Mxstar.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxstarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxstarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(MxstarParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#functionDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDef(MxstarParser.FunctionDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#lambdaFunc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaFunc(MxstarParser.LambdaFuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#constructFuncDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructFuncDef(MxstarParser.ConstructFuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(MxstarParser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockTag(MxstarParser.BlockTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfTag(MxstarParser.IfTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnTag(MxstarParser.ReturnTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileTag(MxstarParser.WhileTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForTag(MxstarParser.ForTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakTag(MxstarParser.BreakTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueTag(MxstarParser.ContinueTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarTag(MxstarParser.VarTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprTag(MxstarParser.ExprTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lambdaTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaTag(MxstarParser.LambdaTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code semiTag}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSemiTag(MxstarParser.SemiTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#funcVarDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncVarDef(MxstarParser.FuncVarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(MxstarParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#oneVarDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOneVarDef(MxstarParser.OneVarDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prefixTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixTag(MxstarParser.PrefixTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjTag(MxstarParser.ObjTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nvarTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNvarTag(MxstarParser.NvarTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignTag(MxstarParser.AssignTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryTag(MxstarParser.BinaryTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code suffixTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuffixTag(MxstarParser.SuffixTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryTag(MxstarParser.PrimaryTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprinTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprinTag(MxstarParser.ExprinTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addrTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddrTag(MxstarParser.AddrTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callTag}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallTag(MxstarParser.CallTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#ifStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(MxstarParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#whileStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(MxstarParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#forStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStat(MxstarParser.ForStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(MxstarParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarType(MxstarParser.VarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#newType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewType(MxstarParser.NewTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#basicType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicType(MxstarParser.BasicTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(MxstarParser.PrimaryContext ctx);
}