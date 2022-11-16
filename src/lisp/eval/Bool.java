package lisp.eval;

/**
 * 真理値
 * 
 * @author tetsuya
 *
 */
public class Bool implements SExpression {
	private static final Bool TRUE = new Bool(true);
	private static final Bool FALSE = new Bool(false);

	private boolean value;

	public boolean isValid() {
		return this.value;
	}

	private Bool(boolean value) {
		this.value = value;
	}

	/**
	 * 真理値のインスタンスを得る。
	 * 
	 * @param value true or false
	 * @return TRUE or FALSE
	 */
	public static Bool valueOf(boolean value) {
		if (value == true) {
			return Bool.TRUE;
		}
		return Bool.FALSE;
	}

	@Override
	public String toString() {
		return this.value ? "#t" : "#f";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
