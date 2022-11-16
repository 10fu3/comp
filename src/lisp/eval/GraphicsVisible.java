package lisp.eval;

import java.math.BigInteger;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class GraphicsVisible implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("graphics-visibleの引数が多すぎます。");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			long shape = ((LispNumber) ((ConsCell) ((ConsCell) args).getCdr()).getCar()).toLispReal().longValueExact();
			boolean visible = !((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCar()
					.equals(Bool.valueOf(false));
			LispWindow.getWindow(index).getCanvas().setVisible(shape, visible);
			return LispInteger.valueOf(BigInteger.valueOf(shape));
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("graphics-visibleの引数の1つ目はキャンバスです。");
		} catch (ClassCastException e) {
		}
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("malformed graphics-visible");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			boolean visible = !((ConsCell) ((ConsCell) args).getCdr()).getCar().equals(Bool.valueOf(false));
			LispWindow.getWindow(index).setVisible(visible);
			return LispInteger.valueOf(BigInteger.valueOf(index));
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("graphics-visibleの引数の1つ目はキャンバスです。");
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("walformed graphics-visible");
	}

	@Override
	public String toString() {
		return "#<subr graphics-visible>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}