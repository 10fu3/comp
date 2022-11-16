package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class Write implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("引数が多すぎます。");
			}
			System.out.print(((ConsCell) args).getCar());
			return Undef.getInstance();
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("引数が少なすぎます。");
	}

	@Override
	public String toString() {
		return "#<subr write>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
