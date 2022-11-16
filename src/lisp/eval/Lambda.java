package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class Lambda implements SpecialForm {

	@Override
	public SExpression applyTo(SExpression args, Environment env) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("lambdaの引数は2つです。");
			}
			return Closure.getInstance(env, ((ConsCell) args).getCar(),
					((ConsCell) ((ConsCell) args).getCdr()).getCar());
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed lambda");
	}

	@Override
	public String toString() {
		return "#<syntax lambda>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
