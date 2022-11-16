package lisp.eval;

/**
 * 空リスト
 * 
 * @author tetsuya
 *
 */
public class EmptyList implements SExpression {
	private static final EmptyList emptyList = new EmptyList();

	private EmptyList() {
	}

	/**
	 * 空リストのインスタンスを得る。
	 * 
	 * @return 空リストのインスタンス
	 */
	public static EmptyList getInstance() {
		return EmptyList.emptyList;
	}

	@Override
	public String toString() {
		return "()";
	}

	@Override
	public boolean isList() {
		return true;
	}
}
