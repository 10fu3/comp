package lisp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import lisp.eval.Environment;
import lisp.eval.Evaluator;
import lisp.eval.SExpression;
import lisp.eval.Symbol;
import lisp.exception.LispException;
import lisp.reader.Reader;

public class CarTest {
	Reader createReader(String sexp) {
		StringReader sr = new StringReader(sexp);
		BufferedReader br = new BufferedReader(sr);
		return new Reader(br);
	}

	@Test(expected = LispException.class)
	public void 空リストのCar() throws IOException, LispException {
		String sexp = "(car . ((quote . (() . ())) . ()))"; // (car '())
		Environment env = SetUp.getGlobalEnvironment();

		Reader reader = createReader(sexp);
		SExpression exp = reader.read();
		SExpression actual = Evaluator.eval(exp, env); // throws LispException
	}

	@Test
	public void ドット対のCar() throws IOException, LispException {
		String sexp = "(car . ((quote . ((a . b) . ())) . ()))"; // (car '(a . b))
		Environment env = SetUp.getGlobalEnvironment();

		Reader reader = createReader(sexp);
		SExpression exp = reader.read();
		SExpression actual = Evaluator.eval(exp, env);

		SExpression expected = Symbol.getInstance("a"); // a
		assertThat(actual, is(expected));
	}

}
