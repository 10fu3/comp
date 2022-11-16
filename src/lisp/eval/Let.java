package lisp.eval;

import java.util.ArrayList;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Let implements SpecialForm {

	@Override
	public SExpression applyTo(SExpression args, Environment env) throws LispException {
		try {
			if (!(((ConsCell) ((ConsCell) args).getCdr()).getCdr() instanceof EmptyList)) {
				throw new SyntaxErrorException("letの引数は2つ");
			}
			ArrayList<ArrayList<SExpression>> exp1 = ConsCell.readList2(((ConsCell) args).getCar());
			SExpression exp2 = ((ConsCell) ((ConsCell) args).getCdr()).getCar();
			ArrayList<SExpression> symbol = new ArrayList<SExpression>(); // 記号
			ArrayList<SExpression> value = new ArrayList<SExpression>(); // 値
			for (ArrayList<SExpression> item : exp1) {
				if (item.size() != 2) {
					throw new SyntaxErrorException("letの引数は2つ");
				}
			}
			for (ArrayList<SExpression> item : exp1) {
				symbol.add((Symbol) item.get(0));
				value.add(Evaluator.eval(item.get(1), env));
			}
			return Closure.getInstance(env, Map.makeList1(symbol), exp2).applyTo(Map.makeList1(value)); // クロージャーに変形
		} catch (ClassCastException e) {
		}
		throw new SyntaxErrorException("letの引数は2つ");
	}

	@Override
	public String toString() {
		return "#<syntax let>";
	}

	@Override
	public boolean isList() {
		// TODO Auto-generated method stub
		return false;
	}
}