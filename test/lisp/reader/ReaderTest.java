package lisp.reader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import lisp.eval.Bool;
import lisp.eval.ConsCell;
import lisp.eval.EmptyList;
import lisp.eval.Int;
import lisp.eval.Symbol;
import lisp.exception.LispException;

public class ReaderTest {

	@Test
	public void 整数値() {
		String input = "123";
		int value = 123;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			Int actual = (Int) reader.read();
			Int expected = Int.valueOf(value);
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void 真値() {
		String input = "#t";
		boolean value = true;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			Bool actual = (Bool) reader.read();
			Bool expected = Bool.valueOf(value);
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void 偽値() {
		String input = "#f";
		boolean value = false;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			Bool actual = (Bool) reader.read();
			Bool expected = Bool.valueOf(value);
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void 記号() {
		String input = "xyz";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			Symbol actual = (Symbol) reader.read();
			Symbol expected = Symbol.getInstance(input);
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void 空リスト() {
		String input = "()";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			EmptyList actual = (EmptyList) reader.read();
			EmptyList expected = EmptyList.getInstance();
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void ドット対のcar() {
		String car = "a";
		String cdr = "()";
		String input = "(" + car + " . " + cdr + ")";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			ConsCell cell = (ConsCell) reader.read();
			Symbol actual = (Symbol) cell.getCar();
			Symbol expected = Symbol.getInstance(car);
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void ドット対のcdr() {
		String car = "a";
		String cdr = "()";
		String input = "(" + car + " . " + cdr + ")";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			ConsCell cell = (ConsCell) reader.read();
			EmptyList actual = (EmptyList) cell.getCdr();
			EmptyList expected = EmptyList.getInstance();
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}
}
