package lisp.eval;

import java.util.ArrayList;

import lisp.exception.LispException;

public class Or implements SpecialForm {
	@Override
	public SExpression applyTo(SExpression args, Environment env) throws LispException {
		ArrayList<SExpression> input = ConsCell.readList1(args);
		for (SExpression item : input) {
			if (!Evaluator.eval(item, env).equals(Bool.valueOf(false))) {
				return Bool.valueOf(true);
			}
		}
		return Bool.valueOf(false);
	}

	@Override
	public String toString() {
		return "#<syntax or>";
	}

	@Override
	public boolean isList() {
		return false;
	}

}
