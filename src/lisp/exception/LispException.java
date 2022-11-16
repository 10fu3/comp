package lisp.exception;

/**
 * プログラムの誤りを表す例外
 * 
 * @author tetsuya
 *
 */
@SuppressWarnings("serial")
public class LispException extends Exception {
	/**
	 * エラーメッセージを持つ例外を構築する。
	 * 
	 * @param msg エラーメッセージ
	 */
	LispException(String msg) {
		super(msg);
	}
}
