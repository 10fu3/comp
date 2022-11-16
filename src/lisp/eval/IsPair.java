package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class IsPair implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("pair?の引数は1つ");
			}
			return Bool.valueOf(((ConsCell) args).getCar() instanceof ConsCell);
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("pair?の引数は1つ");
	}

	@Override
	public String toString() {
		return "#<subr pair?>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
