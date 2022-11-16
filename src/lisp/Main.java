package lisp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lisp.eval.Add;
import lisp.eval.And;
import lisp.eval.Append;
import lisp.eval.Apply;
import lisp.eval.Assoc;
import lisp.eval.Begin;
import lisp.eval.BigComplex;
import lisp.eval.BigQuaternion;
import lisp.eval.Bigger;
import lisp.eval.Car;
import lisp.eval.Cdr;
import lisp.eval.Cons;
import lisp.eval.Cos;
import lisp.eval.Define;
import lisp.eval.Div;
import lisp.eval.Environment;
import lisp.eval.Equals;
import lisp.eval.Evaluator;
import lisp.eval.Exit;
import lisp.eval.Factorial;
import lisp.eval.GraphicsCanvas;
import lisp.eval.GraphicsCircle;
import lisp.eval.GraphicsClear;
import lisp.eval.GraphicsClearAll;
import lisp.eval.GraphicsColor;
import lisp.eval.GraphicsLine;
import lisp.eval.GraphicsRange;
import lisp.eval.GraphicsTitle;
import lisp.eval.GraphicsVisible;
import lisp.eval.If;
import lisp.eval.IsEq;
import lisp.eval.IsEqual;
import lisp.eval.IsList;
import lisp.eval.IsNull;
import lisp.eval.IsPair;
import lisp.eval.Lambda;
import lisp.eval.Let;
import lisp.eval.LispComplex;
import lisp.eval.LispFloat;
import lisp.eval.LispQuaternion;
import lisp.eval.List;
import lisp.eval.Map;
import lisp.eval.Mod;
import lisp.eval.Mul;
import lisp.eval.NewLine;
import lisp.eval.Not;
import lisp.eval.Or;
import lisp.eval.OrLess;
import lisp.eval.OrMore;
import lisp.eval.Quote;
import lisp.eval.SExpression;
import lisp.eval.Set;
import lisp.eval.Sin;
import lisp.eval.Sleep;
import lisp.eval.Smaller;
import lisp.eval.Sqrt;
import lisp.eval.Sub;
import lisp.eval.Symbol;
import lisp.eval.Tan;
import lisp.eval.Write;
import lisp.exception.EndOfFileException;
import lisp.exception.LispException;
import lisp.reader.Reader;

/**
 * Lispインタプリタ
 * 
 * @author tetsuya
 *
 */
public class Main {

	/**
	 * 言語処理系の名前などを表示する。
	 * 
	 * @return なし
	 */
	static void printGreetingMessage() {
		System.out.println("*************************************");
		System.out.println("");
		System.out.println("           A simple Lisp");
		System.out.println("");
		System.out.println("*************************************");
	}

	/*
	 * 起動時の大域的なフレームにおける 束縛.
	 */
	private static void init_define(Environment env) {
		env.define(Symbol.getInstance("pi"), LispFloat.valueOf(LispFloat.PI));
		env.define(Symbol.getInstance("e"), LispFloat.valueOf(LispFloat.E));
		env.define(Symbol.getInstance("i"), LispComplex.valueOf(BigComplex.I));
		env.define(Symbol.getInstance("j"), LispQuaternion.valueOf(BigQuaternion.J));
		env.define(Symbol.getInstance("k"), LispQuaternion.valueOf(BigQuaternion.K));
		env.define(Symbol.getInstance("factorial"), new Factorial());
		env.define(Symbol.getInstance("assoc"), new Assoc());
		env.define(Symbol.getInstance("quote"), new Quote());
		env.define(Symbol.getInstance("begin"), new Begin());
		env.define(Symbol.getInstance("define"), new Define());
		env.define(Symbol.getInstance("set!"), new Set());
		env.define(Symbol.getInstance("let"), new Let());
		env.define(Symbol.getInstance("if"), new If());
		env.define(Symbol.getInstance("and"), new And());
		env.define(Symbol.getInstance("or"), new Or());
		env.define(Symbol.getInstance("not"), new Not());
		env.define(Symbol.getInstance("apply"), new Apply());
		env.define(Symbol.getInstance("+"), new Add());
		env.define(Symbol.getInstance("-"), new Sub());
		env.define(Symbol.getInstance("*"), new Mul());
		env.define(Symbol.getInstance("/"), new Div());
		env.define(Symbol.getInstance("modulo"), new Mod());
		env.define(Symbol.getInstance("sqrt"), new Sqrt());
		env.define(Symbol.getInstance("sin"), new Sin());
		env.define(Symbol.getInstance("cos"), new Cos());
		env.define(Symbol.getInstance("tan"), new Tan());
		env.define(Symbol.getInstance("car"), new Car());
		env.define(Symbol.getInstance("cdr"), new Cdr());
		env.define(Symbol.getInstance("cons"), new Cons());
		env.define(Symbol.getInstance("="), new Equals());
		env.define(Symbol.getInstance("<"), new Bigger());
		env.define(Symbol.getInstance(">"), new Smaller());
		env.define(Symbol.getInstance(">="), new OrLess());
		env.define(Symbol.getInstance("<="), new OrMore());
		env.define(Symbol.getInstance("equal?"), new IsEqual());
		env.define(Symbol.getInstance("eq?"), new IsEq());
		env.define(Symbol.getInstance("null?"), new IsNull());
		env.define(Symbol.getInstance("list?"), new IsList());
		env.define(Symbol.getInstance("pair?"), new IsPair());
		env.define(Symbol.getInstance("list"), new List());
		env.define(Symbol.getInstance("map"), new Map());
		env.define(Symbol.getInstance("append"), new Append());
		env.define(Symbol.getInstance("newline"), new NewLine());
		env.define(Symbol.getInstance("write"), new Write());
		env.define(Symbol.getInstance("display"), new Write());
		env.define(Symbol.getInstance("lambda"), new Lambda());
		env.define(Symbol.getInstance("exit"), new Exit());
		env.define(Symbol.getInstance("sleep"), new Sleep());

		env.define(Symbol.getInstance("graphics-canvas"), new GraphicsCanvas());
		env.define(Symbol.getInstance("graphics-title"), new GraphicsTitle());
		env.define(Symbol.getInstance("graphics-range"), new GraphicsRange());
		env.define(Symbol.getInstance("graphics-visible"), new GraphicsVisible());
		env.define(Symbol.getInstance("graphics-line"), new GraphicsLine());
		env.define(Symbol.getInstance("graphics-circle"), new GraphicsCircle());
		env.define(Symbol.getInstance("graphics-color"), new GraphicsColor());
		env.define(Symbol.getInstance("graphics-clear"), new GraphicsClear());
		env.define(Symbol.getInstance("graphics-clear-all"), new GraphicsClearAll());
	}

	/**
	 * Lispインタプリタの対話的実行
	 * 
	 * @param args コマンドライン引数。与えられても無視される。
	 * @throws IOException 入出力エラー
	 */

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			printGreetingMessage();
		}
		if (args.length >= 2) {
			return;
		}
		try (BufferedReader in = (args.length == 0) ? new BufferedReader(new InputStreamReader(System.in))
				: new BufferedReader(new FileReader(args[0])); Reader reader = new Reader(in);) {
			Environment env = new Environment();
			init_define(env);
			// REPL(Read-Eval-Print-Loop)
			while (true) {
				try {
					if (args.length == 0) {
						System.out.print("lisp> ");
					}
					SExpression exp = reader.read();
					SExpression value = Evaluator.eval(exp, env);
					if (args.length == 0) {
						System.out.println(value);
					}
				} catch (EndOfFileException e) {
					return;
				} catch (ArithmeticException e) {
					if (args.length != 0) {
						return;
					}
				} catch (LispException e) {
					System.err.println(e.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}