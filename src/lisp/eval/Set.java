package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Set implements SpecialForm {

	@Override
	public SExpression applyTo(SExpression args, Environment env) throws LispException {
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed set!");
			}
			SExpression symbol = ((ConsCell) args).getCar();
			SExpression exp = Evaluator.eval(((ConsCell) ((ConsCell) args).getCdr()).getCar(), env);
			env.set((Symbol) symbol, exp);
			return exp;
		} catch (ClassCastException e) {
			throw new SyntaxErrorException("set!の1つ目の引数は記号");
		}
	}

	@Override
	public String toString() {
		return "#<syntax set!>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
