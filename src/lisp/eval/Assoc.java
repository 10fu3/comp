package lisp.eval;

import java.util.ArrayList;

import lisp.exception.SyntaxErrorException;

public class Assoc implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws SyntaxErrorException {
		try {
			SExpression key = ((ConsCell) args).getCar();
			ArrayList<SExpression> list = ConsCell.readList1(((ConsCell) ((ConsCell) args).getCdr()).getCar());
			for (SExpression item : list) { // ドット対かどうかチェック
				if (!(item instanceof ConsCell)) {
					throw new SyntaxErrorException("assocの2番目の引数はドット対のリストです。");
				}
			}
			for (SExpression item : list) {
				if (((ConsCell) item).getCar().equals(key)) {
					return item;
				}
			}
			return Bool.valueOf(false);
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("malformed assoc");
	}

	@Override
	public String toString() {
		return "#<closure assoc>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}