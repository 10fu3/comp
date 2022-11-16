package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Define implements SpecialForm {

	@Override
	public SExpression applyTo(SExpression args, Environment env) throws LispException {
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("defineの引数は2つです。");
			}
		} catch (ClassCastException e) {
			throw new SyntaxErrorException("defineの引数は2つです。");
		}
		SExpression exp1 = ((ConsCell) args).getCar();
		SExpression exp2 = ((ConsCell) ((ConsCell) args).getCdr()).getCar();
		try {
			env.define((Symbol) exp1, Evaluator.eval(exp2, env));
			return exp1;
		} catch (ClassCastException e) { // 省略された場合
		}
		try {
			if (((ConsCell) exp1).getCdr() instanceof ConsCell) {
				env.define((Symbol) ((ConsCell) exp1).getCar(),
						Closure.getInstance(env, ((ConsCell) exp1).getCdr(), exp2));
			} else {
				env.define((Symbol) ((ConsCell) exp1).getCar(), Closure.getInstance(env,
						ConsCell.getInstance(EmptyList.getInstance(), ((ConsCell) exp1).getCdr()), exp2));
			}
			return ((ConsCell) exp1).getCar();
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("defineの引数は記号です。");
	}

	@Override
	public String toString() {
		return "#<syntax define>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
