package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 評価器
 * 
 * @author tetsuya
 *
 */
public class Evaluator {

	/**
	 * <p>
	 * 引数の環境の下で引数のS式を評価する。
	 * </p>
	 * 
	 * <p>
	 * S式xの評価値をE[x]とすると、E[x]は次のように定義される。
	 * </p>
	 * <ul>
	 * <li>xが整数値の場合、E[x] = x</li>
	 * <li>xが真理値の場合、E[x] = x</li>
	 * <li>xが空リストの場合、E[x] = 空リスト</li>
	 * <li>xが未定義値の場合、E[x] = 未定義値</li>
	 * <li>xがクロージャの場合、E[x] = x</li>
	 * <li>xがサブルーチンの場合、E[x] = x</li>
	 * <li>xが特殊形式の場合、E[x] = x</li>
	 * <li>xが記号の場合、E[x] = 環境の下で記号xに束縛された値</li>
	 * <li>xが空リストではないリスト(x0 x1 ... xn)の場合
	 * <ul>
	 * <li>E[x0]が特殊形式の場合、特殊形式を引数x1, ..., xn に適用した結果</li>
	 * <li>E[x0]がサブルーチン（組み込み手続き）の場合、サブルーチンを引数E[x1], ..., E[xn]に適用した結果</li>
	 * <li>E[x0]がクロージャの場合、クロージャを引数E[x1], ..., E[xn]に適用した結果</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * 
	 * @param sexp S式
	 * @param env  環境
	 * @return 評価値(S式)
	 */
	public static SExpression eval(SExpression sexp, Environment env) throws LispException {
		if (sexp instanceof LispNumber || sexp instanceof Bool || sexp instanceof LispString
				|| sexp instanceof EmptyList || sexp instanceof Undef || sexp instanceof Closure
				|| sexp instanceof Subroutine || sexp instanceof SpecialForm) {
			return sexp;
		}
		if (sexp instanceof Symbol) {
			return env.getValueOf((Symbol) sexp);
		}
		if (sexp instanceof ConsCell) {
			SExpression car = ((ConsCell) sexp).getCar();
			SExpression cdr = ((ConsCell) sexp).getCdr();
			SExpression result = eval(car, env);
			if (result instanceof SpecialForm) {
				return ((SpecialForm) result).applyTo(cdr, env);
			}
			if (result instanceof Subroutine) {
				return ((Subroutine) result).applyTo(evalList(cdr, env));
			}
			if (result instanceof Closure) {
				return ((Closure) result).applyTo(evalList(cdr, env));
			}
		}
		throw new SyntaxErrorException("評価されていません (in Evaluator.java)");
	}

//リストをすべて評価して返します
	private static SExpression evalList(SExpression args, Environment env) throws LispException {
		if (args instanceof EmptyList) {
			return eval(args, env);
		}
		if (args instanceof ConsCell) {
			return ConsCell.getInstance(eval(((ConsCell) args).getCar(), env),
					evalList(((ConsCell) args).getCdr(), env));
		}
		throw new SyntaxErrorException("error");
	}
}
