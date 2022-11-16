package lisp.exception;

/**
 * 入力が尽きたことを表す例外
 * 
 * @author tetsuya
 *
 */
@SuppressWarnings("serial")
public class EndOfFileException extends LispException {
	/**
	 * 空のエラーメッセージを持つ例外を構築する。
	 */
	public EndOfFileException() {
		super("");
	}

	/**
	 * 指定されたエラーメッセージを持つ例外を構築する。
	 * 
	 * @param msg エラーメッセージ
	 */
	public EndOfFileException(String msg) {
		super(msg);
	}
}
