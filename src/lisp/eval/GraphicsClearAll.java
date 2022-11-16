package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class GraphicsClearAll implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed graphics-clear-all");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			LispWindow.getWindow(index).getCanvas().clear();
			return Undef.getInstance();
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("graphics-clear-allの引数の1つ目はキャンバスです。");
		} catch (ClassCastException e) {
		}
		if (!(args instanceof EmptyList)) {
			throw new SyntaxErrorException("malformed graphics-clear-all");
		}
		LispWindow.clear();
		return Undef.getInstance();
	}

	@Override
	public String toString() {
		return "#<subr graphics-clear-all>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
