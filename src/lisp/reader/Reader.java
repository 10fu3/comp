package lisp.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import lisp.eval.Bool;
import lisp.eval.ConsCell;
import lisp.eval.EmptyList;
import lisp.eval.LispFloat;
import lisp.eval.LispInteger;
import lisp.eval.LispString;
import lisp.eval.SExpression;
import lisp.eval.Symbol;
import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 構文解析器
 * 
 * @author tetsuya
 *
 */
public class Reader implements AutoCloseable {
	/**
	 * 字句解析器
	 */
	private Lexer lexer;

	/**
	 * 先読みの字句
	 */
	private Token token = null;

	/**
	 * 括弧の入れ子レベル。式を読み終わった時にnestingLevelが0ならば、そこまでの式を評価する。
	 */
	private int nestingLevel = 0;

	/**
	 * コンストラクタ
	 * 
	 * @param in 入力
	 */
	public Reader(BufferedReader in) {
		this.lexer = new Lexer(in);
	}

	/**
	 * <pre>
	 * {@literal <S式>} ::= {@literal <整数値>} | {@literal <記号>} | {@literal <真理値>} | '(' ({@literal <S式>} '.' {@literal <S式>})? ')'
	 * </pre>
	 * 
	 * @return S式
	 * @throws LispException
	 * @throws IOException
	 */
	SExpression sExpression() throws IOException, LispException {
		// 整数値
		if (this.token.getKind() == Token.Kind.NUMBER) {
			BigInteger value = this.token.getIntValue();
			if (this.nestingLevel != 0) { // 式が未完成
				next();
			}
			return LispInteger.valueOf(value);
		}
		// 浮動小数点数
		if (this.token.getKind() == Token.Kind.DECIMAL) {
			BigDecimal value = this.token.getDoubleValue();
			if (this.nestingLevel != 0) { // 式が未完成
				next();
			}
			return LispFloat.valueOf(value);
		}
		// 文字列値
		if (this.token.getKind() == Token.Kind.STRING) {
			StringBuffer value = this.token.getStringValue();
			if (this.nestingLevel != 0) { // 式が未完成
				next();
			}
			return LispString.valueOf(value);
		}
		// 記号
		if (this.token.getKind() == Token.Kind.SYMBOL) {
			String value = this.token.getSymbolValue();
			if (this.nestingLevel != 0) { // 式が未完成
				next();
			}
			return Symbol.getInstance(value);
		}
		// 真理値
		if (this.token.getKind() == Token.Kind.BOOLEAN) {
			boolean value = this.token.getBooleanValue();
			if (this.nestingLevel != 0) { // 式が未完成
				next();
			}
			return Bool.valueOf(value);
		}
		// クォート
		if (this.token.getKind() == Token.Kind.QUOTE) {
			next();
			return ConsCell.getInstance(Symbol.getInstance("quote"),
					ConsCell.getInstance(sExpression(), EmptyList.getInstance()));
		}
		// '(' ')' or '(' <S式> . <S式> ')'
		if (this.token.getKind() == Token.Kind.LPAREN) {
			this.nestingLevel++;
			next();
			// 空リスト
			if (this.token.getKind() == Token.Kind.RPAREN) {
				this.nestingLevel--;
				if (this.nestingLevel != 0) { // 式が未完成
					next();
				}
				return EmptyList.getInstance();
			}
			// car
			SExpression car = sExpression();
			// cdr
			SExpression cdr = getCdr();
			this.nestingLevel--;
			if (this.nestingLevel != 0) { // 式が未完成
				next();
			}
			return ConsCell.getInstance(car, cdr);
		}
		throw new SyntaxErrorException("Invalid expression:" + this.token.getKind());
	}

	private SExpression getCdr() throws IOException, LispException {
		if (this.token.getKind() == Token.Kind.RPAREN) {
			return EmptyList.getInstance();
		}
		if (this.token.getKind() == Token.Kind.DOT) {
			next();
			return sExpression();
		}
		return ConsCell.getInstance(sExpression(), getCdr());
	}

	private void next() throws IOException, LispException {
		this.token = this.lexer.getNextToken();
	}

	public SExpression read() throws IOException, LispException {
		this.nestingLevel = 0;
		next();
		return sExpression();
	}

	@Override
	public void close() throws Exception {
		this.lexer.close();
	}
}