package lisp.eval;

import java.math.BigInteger;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class GraphicsRange implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!((((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCdr()) instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed graphics-range");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			LispNumber min = ((LispNumber) ((ConsCell) ((ConsCell) args).getCdr()).getCar());
			LispNumber max = ((LispNumber) ((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCar());
			LispWindow.getWindow(index).getCanvas().setRange(min.getReal().doubleValue(),
					min.getImaginary1().doubleValue(), max.getReal().doubleValue(), max.getImaginary1().doubleValue());
			return LispInteger.valueOf(BigInteger.valueOf(index));
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("graphics-rangeの引数の1つ目はキャンバスです。");
		} catch (ClassCastException e) {
		}
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed graphics-range");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			LispWindow.getWindow(index).getCanvas().setRange();
			return LispInteger.valueOf(BigInteger.valueOf(index));
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("graphics-rangeの引数の1つ目はキャンバスです。");
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed graphics-range");
	}

	@Override
	public String toString() {
		return "#<subr graphics-range>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}