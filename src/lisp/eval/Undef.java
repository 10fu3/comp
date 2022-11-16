package lisp.eval;

/**
 * 未定義値
 * 
 * @author tetsuya
 *
 */
public class Undef implements SExpression {
	private static Undef instance = new Undef();

	private Undef() {
	}

	/**
	 * 未定義値のインスタンスを得る。
	 * 
	 * @return 未定義値のインスタンス
	 */
	public static Undef getInstance() {
		return Undef.instance;
	}

	@Override
	public String toString() {
		return "#<undef>";
	}

	@Override
	public boolean isList() {
		return false;
	}
}
