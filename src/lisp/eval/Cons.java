package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class Cons implements Subroutine {
	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed cons");
			}
			return ConsCell.getInstance(((ConsCell) args).getCar(), ((ConsCell) ((ConsCell) args).getCdr()).getCar());
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed cons");
	}

	@Override
	public String toString() {
		return "#<subr cons>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
