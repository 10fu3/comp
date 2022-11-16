package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class IsEq implements Subroutine { // eq?
	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("eq?の引数は2つです。");
			}
			return Bool.valueOf(iseq(((ConsCell) args).getCar(), ((ConsCell) ((ConsCell) args).getCdr()).getCar()));
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("eq?の引数は2つ");
	}

	public static boolean iseq(SExpression obj1, SExpression obj2) {
		if (obj1 instanceof LispNumber || obj1 instanceof LispString || obj1 instanceof Symbol) {
			return (obj1).equals(obj2);
		}
		return obj1 == obj2;
	}

	@Override
	public String toString() {
		return "#subr eq?>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
