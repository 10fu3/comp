package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class Not implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed not");
			}
			return Bool.valueOf(((ConsCell) args).getCar().equals(Bool.valueOf(false)));
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed not");
	}

	@Override
	public String toString() {
		return "#<subr not>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
