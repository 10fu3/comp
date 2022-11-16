package lisp.eval;

import java.util.ArrayList;

import lisp.exception.LispException;

public class And implements SpecialForm {
	@Override
	public SExpression applyTo(SExpression args, Environment env) throws LispException {
		ArrayList<SExpression> input = ConsCell.readList1(args);
		for (SExpression item : input) {
			if (Evaluator.eval(item, env).equals(Bool.valueOf(false))) {
				return Bool.valueOf(false);
			}
		}
		return Bool.valueOf(true);
	}

	@Override
	public String toString() {
		return "#<syntax and>";
	}

	@Override
	public boolean isList() {
		return false;
	}

}
