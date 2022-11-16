package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class IsEqual implements Subroutine { // equal?
	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("equal?の引数は2つです。");
			}
			return Bool.valueOf(((ConsCell) args).getCar().equals(((ConsCell) ((ConsCell) args).getCdr()).getCar()));
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("equal?の引数は1つ");
	}

	@Override
	public String toString() {
		return "#<subr equal?>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
