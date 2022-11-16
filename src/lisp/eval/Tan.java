package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Tan implements Subroutine {
	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed tan");
			}
			return ((LispNumber) ((ConsCell) args).getCar()).tan();
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed tan");
	}

	@Override
	public String toString() {
		return "#<subr tan>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}