package lisp.eval;

/**
 * 整数値
 * 
 * @author tetsuya
 *
 */
public class Int implements SExpression {
	private Integer value;

	public Integer getValue() {
		return value;
	}

	private Int(Integer value) {
		this.value = value;
	}

	/**
	 * 整数値のインスタンスを得る。
	 * 
	 * @param value 整数値
	 * @return 整数値のインスタンス
	 */
	public static Int valueOf(int value) {
		return new Int(value);
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
		if ((obj instanceof Int) == false) {
			return false;
		}
		return this.value.equals(((Int) obj).getValue());
	}
}
