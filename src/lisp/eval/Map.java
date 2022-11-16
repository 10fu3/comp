package lisp.eval;

import java.util.ArrayList;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Map implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			SExpression proc = ((ConsCell) args).getCar();
			ArrayList<ArrayList<SExpression>> input = ConsCell.readList2(((ConsCell) args).getCdr());

			int size1 = check(input);
			int size2 = input.size();
			ArrayList<ArrayList<SExpression>> result = new ArrayList<ArrayList<SExpression>>(size1);
			for (int i = 0; i < size1; i++) {
				result.add(i, new ArrayList<SExpression>(size2));
				for (int j = 0; j < size2; j++) {
					result.get(i).add(j, input.get(j).get(i));
				}
			}
			return makeList2(proc, result);
		} catch (ClassCastException e) {
			throw new SyntaxErrorException("Mapの引数は2つ。");
		}
	}

	private static int check(ArrayList<ArrayList<SExpression>> list) throws SyntaxErrorException {
		int size = list.get(0).size();
		for (ArrayList<SExpression> item : list) {
			if (size != item.size()) {
				throw new SyntaxErrorException("リストの大きさが異なります(Map)");
			}
		}
		return size;
	}

	static SExpression makeList2(SExpression proc, ArrayList<ArrayList<SExpression>> array) throws LispException {
		if (array.isEmpty()) {
			return EmptyList.getInstance();
		}
		return ConsCell.getInstance(Evaluator.eval(ConsCell.getInstance(proc, makeList1(array.remove(0))), null),
				makeList2(proc, array));
	}

	static SExpression makeList1(ArrayList<SExpression> array) {
		if (array.isEmpty()) {
			return EmptyList.getInstance();
		}
		return ConsCell.getInstance(array.remove(0), makeList1(array));
	}

	@Override
	public String toString() {
		return "#<subr map>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}