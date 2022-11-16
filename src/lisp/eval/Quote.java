package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Quote implements SpecialForm {

	@Override
	public SExpression applyTo(SExpression args, Environment env) throws LispException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed quote");
			}
			return ((ConsCell) args).getCar();
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed quote");
	}

	@Override
	public String toString() {
		return "#<syntax quote>";
	}

	@Override
	public boolean isList() {
		return false;
	}

}
