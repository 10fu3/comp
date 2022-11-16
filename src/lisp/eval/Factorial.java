package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Factorial implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("演算'factorial'の引数は1つ。");
			}
			return ((LispInteger) ((ConsCell) args).getCar()).factorial();
		} catch (ClassCastException e) {
			throw new SyntaxErrorException("演算'factorial'の引数は数値である必要があります");
		}
	}

	@Override
	public String toString() {
		return "#<subr factorial>";
	}

	@Override
	public boolean isList() {
		return false;
	}

}
