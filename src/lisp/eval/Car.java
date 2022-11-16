package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class Car implements Subroutine {
	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed car");
			}
			return ((ConsCell) ((ConsCell) args).getCar()).getCar();
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed car");
	}

	@Override
	public String toString() {
		return "#<subr car>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
