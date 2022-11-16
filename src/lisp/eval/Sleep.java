package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Sleep implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed sleep");
			}
			long time = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			Thread.sleep(time);
			return Undef.getInstance();
		} catch (ClassCastException | InterruptedException e) {
		}
		throw new SyntaxErrorException("malformed sleep");
	}

	@Override
	public String toString() {
		return "#<subr sleep>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
