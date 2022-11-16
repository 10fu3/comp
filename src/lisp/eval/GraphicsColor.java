package lisp.eval;

import java.math.BigInteger;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 描画したものに色を付ける
 * 
 * @author utsumi
 *
 */
public class GraphicsColor implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("graphics-colorの引数が多すぎます。");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			long shape = ((LispNumber) ((ConsCell) ((ConsCell) args).getCdr()).getCar()).toLispReal().longValueExact();
			long color = ((LispNumber) ((ConsCell) ((ConsCell) ((ConsCell) args).getCdr()).getCdr()).getCar())
					.toLispReal().longValueExact();
			LispWindow.getWindow(index).getCanvas().setColor(shape, color);
			return LispInteger.valueOf(BigInteger.valueOf(shape));
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("graphics-colorの引数の1つ目はキャンバスです。");
		} catch (ClassCastException e) {
		}
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("graphics-colorの引数がリストではありません。");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			long color = ((LispNumber) ((ConsCell) ((ConsCell) args).getCdr()).getCar()).toLispReal().longValueExact();
			LispWindow.getWindow(index).getCanvas().setBackground(color);
			return LispInteger.valueOf(BigInteger.valueOf(index));
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("graphics-colorの引数の1つ目はキャンバスです。");
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("graphics-colorの引数が少なすぎます。");
	}

	@Override
	public String toString() {
		return "#<subr graphics-color>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}