package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Apply implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) throws LispException {
		try {
			SExpression proc = ((ConsCell) args).getCar();
			args = ((ConsCell) args).getCdr();
			return Evaluator.eval(ConsCell.getInstance(proc, getList((ConsCell) args)), null);
		} catch (ClassCastException e) {
			throw new SyntaxErrorException("applyの引数はリストです。");
		}
	}

	static SExpression getList(ConsCell list) throws ClassCastException, LispException {
		if (list.getCdr() instanceof EmptyList && !list.getCar().isList()) {
			throw new SyntaxErrorException("最後がリストではありません。");
		}
		if (list.getCdr() instanceof EmptyList) {
			return list.getCar();
		}
		return ConsCell.getInstance(list.getCar(), getList((ConsCell) list.getCdr()));
	}

	@Override
	public String toString() {
		return "#<subr apply>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}