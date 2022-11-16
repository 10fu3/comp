package lisp.reader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import lisp.eval.Int;
import lisp.exception.LispException;

public class LexerTest {
	final int NUMBER = 123;
	final String SYMBOL = "X0";
	final String TRUE = "#t";
	final String FALSE = "#f";

	@Test
	public void 左括弧の認識() {
		String input = "(";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			Token expected = new Token(Token.Kind.LPAREN);
			assertThat(actual.getKind(), is(expected.getKind()));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 右括弧の認識() {
		String input = ")";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.RPAREN;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 整数値の認識_字句の種類が正しい() {
		String input = Int.valueOf(this.NUMBER).toString();
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.NUMBER;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 整数値の認識_字句の値が正しい() {
		String input = Int.valueOf(this.NUMBER).toString();
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			int actual = lexer.getNextToken().getIntValue();
			int expected = this.NUMBER;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 整数値_符号プラス付きの認識_字句の種類が正しい() {
		String input = "+" + Int.valueOf(this.NUMBER).toString();
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.NUMBER;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 整数値_符号プラス付きの認識_字句の値が正しい() {
		String input = "+" + Int.valueOf(this.NUMBER).toString();
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			int actual = lexer.getNextToken().getIntValue();
			int expected = this.NUMBER;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 整数値_符号マイナス付きの認識_字句の種類が正しい() {
		String input = "-" + Int.valueOf(this.NUMBER).toString();
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.NUMBER;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 整数値_符号マイナス付きの認識_字句の値が正しい() {
		String input = "-" + Int.valueOf(this.NUMBER).toString();
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			int actual = lexer.getNextToken().getIntValue();
			int expected = -this.NUMBER;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void ドットの認識() {
		String input = ".";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.DOT;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void シンボルの認識_字句の種類が正しい() {
		String input = this.SYMBOL;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.SYMBOL;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void シンボルの認識_字句の値が正しい() {
		String input = this.SYMBOL;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			String actual = lexer.getNextToken().getSymbolValue();
			String expected = this.SYMBOL;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 真値の認識_字句の種類が正しい() {
		String input = this.TRUE;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.BOOLEAN;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 真値の認識_字句の値が正しい() {
		String input = this.TRUE;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			boolean actual = lexer.getNextToken().getBooleanValue();
			boolean expected = true;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 偽値の認識_字句の種類が正しい() {
		String input = this.FALSE;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.BOOLEAN;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 偽値の認識_字句の値が正しい() {
		String input = this.FALSE;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			boolean actual = lexer.getNextToken().getBooleanValue();
			boolean expected = false;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	public void 不正な整数() {
		String input = "1x2";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			Token token = lexer.getNextToken();
			System.out.println(token);
		} catch (IOException e) {
			fail("Exception thrown");
		} catch (LispException e) {
			return;
		}
		fail();
	}

	@Test
	public void 不正な定数() {
		String input = "#tt";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			Token token = lexer.getNextToken();
			System.out.println(token);
		} catch (IOException e) {
			fail("Exception thrown");
		} catch (LispException e) {
			return;
		}
		fail();
	}

	@Test
	public void 連続した字句の認識() {
		String input = "( )";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Lexer lexer = new Lexer(br);
		try {
			lexer.getNextToken();
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.RPAREN;
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

}
