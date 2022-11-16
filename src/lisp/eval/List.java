package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class List implements Subroutine {
	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		if (args instanceof EmptyList) {
			return args;
		}
		if (args instanceof ConsCell) {
			return ConsCell.getInstance(((ConsCell) args).getCar(), applyTo(((ConsCell) args).getCdr()));
		}
		throw new SyntaxErrorException("malformed list");
	}

	@Override
	public String toString() {
		return "#<subr list>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
