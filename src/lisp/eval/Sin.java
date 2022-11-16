package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Sin implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed sin");
			}
			return ((LispNumber) ((ConsCell) args).getCar()).sin();
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed sin");
	}

	@Override
	public String toString() {
		return "#<subr sin>";
	}

	@Override
	public boolean isList() {
		return false;
	}

}
