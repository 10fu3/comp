package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 平方根
 * 
 * @author user
 *
 */
public class Sqrt implements Subroutine {
	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed sqrt");
			}
			return ((LispNumber) ((ConsCell) args).getCar()).toLispReal().sqrt();
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed sqrt");
	}

	@Override
	public String toString() {
		return "#<subr sqrt>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}