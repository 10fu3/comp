package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class Append implements Subroutine {
	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		if (args instanceof EmptyList) {
			return args;
		}
		if (!(args instanceof ConsCell)) {
			throw new SyntaxErrorException("malformed append");
		}
		if (((ConsCell) args).getCdr() instanceof EmptyList) {
			return ((ConsCell) args).getCar();
		}
		if (((ConsCell) args).getCar() instanceof EmptyList) {
			return applyTo(((ConsCell) args).getCdr());
		}
		if (((ConsCell) args).getCar() instanceof ConsCell) {
			return ConsCell.getInstance(((ConsCell) ((ConsCell) args).getCar()).getCar(), applyTo(ConsCell
					.getInstance(((ConsCell) ((ConsCell) args).getCar()).getCdr(), ((ConsCell) args).getCdr())));
		}
		throw new SyntaxErrorException("malformed append");
	}

	@Override
	public String toString() {
		return "#<subr append>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}