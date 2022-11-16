package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Cos implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed cos");
			}
			return ((LispNumber) ((ConsCell) args).getCar()).cos();
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed cos");
	}

	@Override
	public String toString() {
		return "#<subr cos>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
