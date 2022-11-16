package lisp.eval;

public class Exit implements Subroutine {

	@Override
	public SExpression applyTo(SExpression args) {
		System.exit(0);
		return null;
	}

	@Override
	public String toString() {
		return "#<subr exit>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
