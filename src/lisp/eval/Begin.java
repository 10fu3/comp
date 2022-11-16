package lisp.eval;

import java.math.BigInteger;
import java.util.ArrayList;

import lisp.exception.LispException;

public class Begin implements SpecialForm {

	@Override
	public SExpression applyTo(SExpression args, Environment env) throws LispException {
		ArrayList<SExpression> input = ConsCell.readList1(args);
		SExpression result = LispInteger.valueOf(BigInteger.ZERO);
		for (SExpression item : input) {
			result = Evaluator.eval(item, env);
		}
		return result;
	}

	@Override
	public String toString() {
		return "#<syntax begin>";
	}

	@Override
	public boolean isList() {
		return false;
	}

}
