package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class Cdr implements Subroutine {
	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed cdr");
			}
			return ((ConsCell) ((ConsCell) args).getCar()).getCdr();
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed cdr");
	}

	@Override
	public String toString() {
		return "#<subr cdr>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
