package lisp.eval;

import java.math.BigInteger;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class GraphicsLine implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("graphics-lineの引数は3つです。");
			}
			long canvas = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			LispNumber point1 = (LispNumber) ((ConsCell) ((ConsCell) args).getCdr()).getCar();
			LispNumber point2 = (LispNumber) ((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCar();
			return LispInteger.valueOf(BigInteger.valueOf(LispWindow.getWindow(canvas).getCanvas().drawLine(
					point1.getReal().doubleValue(), point1.getImaginary1().doubleValue(),
					point2.getReal().doubleValue(), point2.getImaginary1().doubleValue())));
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("graphics-lineの引数の1つ目はキャンバスです。");
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("graphics-lineの引数は数値です。");
	}

	@Override
	public String toString() {
		return "#<subr graphics-line>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}