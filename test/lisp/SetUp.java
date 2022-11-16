package lisp;

import lisp.eval.Environment;

public class SetUp {
	/**
	 * 大域的な環境を構築する。 TODO Lisp処理系の実装に合わせて変更する。
	 * 
	 * @return 大域的な環境
	 */
	public static Environment getGlobalEnvironment() {
		return new Environment();
	}
}
