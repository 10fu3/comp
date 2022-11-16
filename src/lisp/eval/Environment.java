package lisp.eval;

import java.util.HashMap;

import lisp.exception.LispException;
import lisp.exception.UndefinedErrorException;

/**
 * 環境
 * 
 * @author tetsuya
 *
 */
public class Environment {
	private java.util.Map<Symbol, SExpression> frame = new HashMap<>(); // 現在のフレーム
	private Environment next = null; // 前のフレーム

	public void setNext(Environment next) {
		this.next = next;
	}

	/**
	 * 変数（シンボル）が束縛されている値を返す。
	 * 
	 * @param symbol シンボル
	 * @return シンボルが束縛されている値
	 */
	public SExpression getValueOf(Symbol symbol) throws LispException {
		if (this.frame.containsKey(symbol)) {
			return this.frame.get(symbol);
		} else if (this.next != null) {
			return this.next.getValueOf(symbol);
		} else {
			throw new UndefinedErrorException("'" + symbol + "' には値が束縛されていません");
		}
	}

	/**
	 * 変数束縛
	 * 
	 * @param symbol シンボル
	 * @param sexp   束縛する値
	 */
	public void define(Symbol symbol, SExpression sexp) {
		frame.put(symbol, sexp);
	}

	/**
	 * 変数の値の再定義
	 * 
	 * @param symbol シンボル
	 * @param sexp   束縛する値
	 */
	public void set(Symbol symbol, SExpression sexp) throws LispException {
		if (frame.containsKey(symbol)) {
			frame.put(symbol, sexp);
		} else if (next != null) {
			next.set(symbol, sexp);
		} else {
			throw new UndefinedErrorException("'" + symbol + "' には値が束縛されていません");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this.next != null) {
			return obj instanceof Environment && this.frame.equals(((Environment) obj).frame)
					&& this.next.equals(((Environment) obj).next);
		}
		return obj instanceof Environment && this.frame.equals(((Environment) obj).frame)
				&& ((Environment) obj).next == null;
	}
}