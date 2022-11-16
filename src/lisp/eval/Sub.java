package lisp.eval;

import java.util.ArrayList;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Sub implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		if (args instanceof EmptyList) {
			throw new SyntaxErrorException("演算'-'の引数は1個以上です。");
		}
		ArrayList<SExpression> input = ConsCell.readList1(args);
		int size = input.size();
		try {
			LispNumber result = (LispNumber) input.get(0);
			if (size == 1) {
				return (SExpression) result.negate();
			}
			for (int i = 1; i < size; i++) {
				SExpression item = input.get(i);
				result = result.subtract((LispNumber) item);
			}
			return (SExpression) result;
		} catch (ClassCastException e) {
			throw new SyntaxErrorException("演算'-'の引数は数値である必要があります");
		}
	}

	@Override
	public String toString() {
		return "#<subr ->";
	}

	@Override
	public boolean isList() {
		return false;
	}
}