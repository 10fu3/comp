package lisp.eval;

import java.math.BigInteger;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class GraphicsTitle implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed graphics-title");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			String title = ((ConsCell) ((ConsCell) args).getCdr()).getCar().toString();
			LispWindow.getWindow(index).setTitle(title);
			return LispInteger.valueOf(BigInteger.valueOf(index));
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("malformed graphics-title");
		} catch (ClassCastException e) {
		}
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed graphics-title");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			return LispString.valueOf(new StringBuffer(LispWindow.getWindow(index).getTitle()));
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("malformed graphics-title");
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed graphics-title");
	}

	@Override
	public String toString() {
		return "#<subr graphics-title>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
