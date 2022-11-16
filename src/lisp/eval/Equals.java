package lisp.eval;

import java.util.ArrayList;

import lisp.exception.SyntaxErrorException;

public class Equals implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		ArrayList<SExpression> input = ConsCell.readList1(args);
		int size = input.size();
		if (size <= 1) {
			throw new SyntaxErrorException("=は2つの引数が必要です");
		}
		try {
			LispNumber result = (LispNumber) input.get(0);
			for (int i = 1; i < size; i++) {
				if (result.compareTo((LispNumber) input.get(i)) != 0) {
					return Bool.valueOf(false);
				}
			}
			return Bool.valueOf(true);
		} catch (ClassCastException e) {
			throw new SyntaxErrorException("演算'='の引数は数値である必要があります");
		}
	}

	@Override
	public String toString() {
		return "#<subr =>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}