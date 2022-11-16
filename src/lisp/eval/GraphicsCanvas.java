package lisp.eval;

import java.math.BigInteger;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class GraphicsCanvas implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(args instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed graphics-canvas");
			}
			return LispInteger.valueOf(BigInteger.valueOf(LispWindow.makeInstance()));
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed graphics-canvas");
	}

	@Override
	public String toString() {
		return "#<subr graphics-canvas>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}