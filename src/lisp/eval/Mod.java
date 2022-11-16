package lisp.eval;

import lisp.exception.SyntaxErrorException;

public class Mod implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("moduloの引数は2つです。");
			}
			return ((LispNumber) ((ConsCell) args).getCar()).toLispReal()
					.modulo(((LispReal) ((ConsCell) ((ConsCell) args).getCdr()).getCar()).toLispReal());
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("演算'modulo'の引数は数値である必要があります");
	}

	@Override
	public String toString() {
		return "#<closure modulo>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}