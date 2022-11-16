package lisp.eval;

/**
 * 記号
 * 
 * @author tetsuya
 *
 */
public class LispString implements SExpression {
	private StringBuffer value;

	public StringBuffer getName() {
		return value;
	}

	private LispString(StringBuffer value) {
		this.value = value;
	}

	/**
	 * 記号のインスタンスを得る。
	 * 
	 * @param name 記号名
	 * @return 記号のインスタンス
	 */
	public static LispString valueOf(StringBuffer value) {
		return new LispString(value);
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

	@Override
	public int hashCode() {
		return this.value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof LispString && this.value.equals(((LispString) obj).getName());
	}

	@Override
	public boolean isList() {
		return false;
	}
}