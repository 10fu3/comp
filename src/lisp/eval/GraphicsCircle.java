package lisp.eval;

import java.math.BigInteger;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class GraphicsCircle implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("graphics-circleの引数は3つです。");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			LispNumber location = (LispNumber) ((ConsCell) ((ConsCell) args).getCdr()).getCar();
			double radius = ((LispNumber) ((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCar())
					.toLispReal().doubleValue();

			return LispInteger.valueOf(BigInteger.valueOf(LispWindow.getWindow(index).getCanvas() // キャンバスの番号
					.drawCircle(location.getReal().doubleValue(), // X座標
							location.getImaginary1().doubleValue(), // Y座標
							radius)));// Y座標
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("graphics-circleの引数の1つ目はキャンバスです。");
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("graphics-circleの引数は数値です。");
	}

	@Override
	public String toString() {
		return "#<subr graphics-circle>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}