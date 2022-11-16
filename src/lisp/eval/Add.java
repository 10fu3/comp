package lisp.eval;

import java.math.BigInteger;
import java.util.ArrayList;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 足し算
 * 
 * @author user
 *
 */
public class Add implements Subroutine {
	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		if (args instanceof EmptyList) {
			return LispInteger.valueOf(BigInteger.ZERO);
		}
		ArrayList<SExpression> input = ConsCell.readList1(args);
		int size = input.size();
		try {
			LispNumber result = (LispNumber) input.get(0);
			for (int i = 1; i < size; i++) {
				SExpression item = input.get(i);
				result = result.add((LispNumber) item);
			}
			return result;
		} catch (ClassCastException e) {
			throw new SyntaxErrorException("演算'+'の引数は数値である必要があります");
		}
	}

	@Override
	public String toString() {
		return "#<subr +>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}