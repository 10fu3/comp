package lisp.exception;

/**
 * 例外
 * 
 * 
 *
 */
@SuppressWarnings("serial")
public class UndefinedErrorException extends LispException {
	private static final String prefix = "Undefined Error";

	/**
	 * エラーメッセージを持つ例外を構築する。
	 * 
	 * @param msg エラーメッセージ
	 */
	public UndefinedErrorException(String msg) {
		super(prefix + ":" + msg);
	}
}
