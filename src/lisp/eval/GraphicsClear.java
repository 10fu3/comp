package lisp.eval;

import java.math.BigInteger;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 描画したものを消す
 * 
 * @author utsumi
 *
 */
public class GraphicsClear implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("graphics-clearの引数が多すぎます。");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			long shape = ((LispNumber) ((ConsCell) ((ConsCell) args).getCdr()).getCar()).toLispReal().longValueExact();
			LispWindow.getWindow(index).getCanvas().clear(shape);
			return LispInteger.valueOf(BigInteger.valueOf(index));
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("graphics-clearの引数の1つ目はキャンバスです。");
		} catch (ClassCastException e) {
		}
		try {
			if (!(((ConsCell) args).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("graphics-clearの引数がリストではありません。");
			}
			long index = ((LispNumber) ((ConsCell) args).getCar()).toLispReal().longValueExact();
			LispWindow.clear(index);
			return Undef.getInstance();
		} catch (NullPointerException e) {
			throw new SyntaxErrorException("graphics-clearの引数の1つ目はキャンバスです。");
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("graphics-clearの引数が少なすぎます。");
	}

	@Override
	public String toString() {
		return "#<subr graphics-clear>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}