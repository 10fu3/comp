package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class IsNull implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("null?の引数は1つ");
			}
			return Bool.valueOf(((ConsCell) args).getCar() instanceof EmptyList);
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("null?の引数は1つ");
	}

	@Override
	public String toString() {
		return "#<subr null?>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
