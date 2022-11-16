package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class NewLine implements Subroutine {
	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		if (!(args instanceof EmptyList)) {
			throw new SyntaxErrorException("malformed newline");
		}
		System.out.println();
		return Undef.getInstance();
	}

	@Override
	public String toString() {
		return "#<subr newline>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
