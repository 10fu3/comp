package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class IsList implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("list?の引数は1つ");
			}
			return Bool.valueOf(((ConsCell) args).getCar().isList());
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("list?の引数は1つ");
	}

	@Override
	public String toString() {
		return "#<subr list?>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
