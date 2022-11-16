package lisp.eval;

import java.util.ArrayList;

import lisp.exception.SyntaxErrorException;

/**
 * Cons cell (ドット対)
 * 
 * @author tetsuya
 *
 */
public class ConsCell implements SExpression {
	private SExpression car;
	private SExpression cdr;

	public SExpression getCar() {
		return this.car;
	}

	public SExpression getCdr() {
		return this.cdr;
	}

	private ConsCell(SExpression car, SExpression cdr) {
		this.car = car;
		this.cdr = cdr;
	}

	/**
	 * Cons Cell（ドット対）を構築する。
	 * 
	 * @param car car部
	 * @param cdr cdr部
	 * @return 指定されたcar部とcdr部で構成されるCons Cell（ドット対）
	 */
	public static ConsCell getInstance(SExpression car, SExpression cdr) {
		return new ConsCell(car, cdr);
	}

	@Override
	public String toString() {
		// クォートの省略記法
		if (this.car.equals(Symbol.getInstance("quote")) && this.cdr instanceof ConsCell
				&& ((ConsCell) this.cdr).getCdr() instanceof EmptyList) {
			return "'" + ((ConsCell) this.cdr).getCar();
		}

		StringBuffer result = new StringBuffer("(");
		ConsCell conscell = this;
		while (true) {
			result.append(conscell.car);
			try {
				conscell = (ConsCell) ((ConsCell) conscell).getCdr();
				result.append(" ");
			} catch (ClassCastException e) {
				if (!(conscell.cdr instanceof EmptyList)) {
					result.append(" . ");
					result.append(conscell.cdr);
				}
				result.append(")");
				return result.toString();
			}
		}
	}

	/**
	 * S式をJavaの配列にします
	 * 
	 * @param list リストのリスト
	 * @return
	 * @throws SyntaxErrorException
	 */
	static ArrayList<ArrayList<SExpression>> readList2(SExpression list) throws SyntaxErrorException {
		ArrayList<ArrayList<SExpression>> result = new ArrayList<ArrayList<SExpression>>();
		while (!(list instanceof EmptyList)) {
			if (!(list instanceof ConsCell)) {
				throw new SyntaxErrorException("リストではありません");
			}
			result.add(readList1(((ConsCell) list).getCar()));
			list = ((ConsCell) list).getCdr();
		}
		return result;
	}

	/**
	 * S式をJavaの配列にします。
	 * 
	 * @param list リスト
	 * @return
	 * @throws SyntaxErrorException
	 */
	static ArrayList<SExpression> readList1(SExpression list) throws SyntaxErrorException { // リストを読む
		ArrayList<SExpression> result = new ArrayList<SExpression>();
		while (!(list instanceof EmptyList)) {
			if (!(list instanceof ConsCell)) {
				throw new SyntaxErrorException("リストではありません");
			}
			result.add(((ConsCell) list).getCar());
			list = ((ConsCell) list).getCdr();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof ConsCell && this.car.equals(((ConsCell) obj).getCar())
				&& this.cdr.equals(((ConsCell) obj).getCdr());
	}

	@Override
	public boolean isList() {
		return this.cdr.isList();
	}

}