package lisp.eval;

/**
 * 記号
 * 
 * @author tetsuya
 *
 */
public class Symbol implements SExpression {
	private String name;

	public String getName() {
		return name;
	}

	private Symbol(String symbol) {
		this.name = symbol;
	}

	/**
	 * 記号のインスタンスを得る。
	 * 
	 * @param name 記号名
	 * @return 記号のインスタンス
	 */
	public static Symbol getInstance(String name) {
		return new Symbol(name);
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Symbol && this.name.equals(((Symbol) obj).getName());
	}

	@Override
	public boolean isList() {
		return false;
	}
}
