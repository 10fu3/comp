package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class If implements SpecialForm {

	@Override
	public SExpression applyTo(SExpression args, Environment env) throws LispException {
		try {
			if (!(((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("Ifの引数が多すぎます");
			}
			// 引数が3個
			if (Evaluator.eval(((ConsCell) args).getCar(), env).equals(Bool.valueOf(false))) {
				return Evaluator.eval(((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCar(), env);
			}
			return Evaluator.eval(((ConsCell) ((ConsCell) args).getCdr()).getCar(), env);
		} catch (ClassCastException e) {
		}
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("ifの引数がリストではありません");
			}
			// 引数が2個
			if (Evaluator.eval(((ConsCell) args).getCar(), env).equals(Bool.valueOf(false))) {
				return Undef.getInstance();
			}
			return Evaluator.eval(((ConsCell) ((ConsCell) args).getCdr()).getCar(), env);
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("ifの引数が足りません");
	}

	@Override
	public String toString() {
		return "#<syntax if>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}