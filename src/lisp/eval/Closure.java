package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * クロージャ
 * 
 * @author tetsuya
 *
 */
public class Closure implements SExpression {
	private Environment env;
	private SExpression formals;
	private SExpression body;

	public Environment getEnv() {
		return this.env;
	}

	public SExpression getFormals() {
		return this.formals;
	}

	public SExpression getBody() {
		return this.body;
	}

	private Closure(Environment env, SExpression formals, SExpression body) {
		this.env = env;
		this.formals = formals;
		this.body = body;
	}

	public static Closure getInstance(Environment env, SExpression formals, SExpression body) {
		return new Closure(env, formals, body);
	}

	/*
	 * あとで名前・中身考える 名前:evalとか評価っぽい名前をつける。
	 */
	SExpression applyTo(SExpression args) throws LispException {
		Environment env = new Environment();
		SExpression formals = this.formals;
		env.setNext(this.env);
		try {
			if ((((ConsCell) formals).getCar() instanceof EmptyList)) {
				if (((ConsCell) formals).getCdr() instanceof Symbol) {
					env.define((Symbol) ((ConsCell) formals).getCdr(), args);
				}
				return Evaluator.eval(this.body, env);
			}
		} catch (ClassCastException e) {
		}
		try {
			while (!(formals instanceof EmptyList)) {
				if (!(formals instanceof ConsCell)) { // formalsが終わりの場合
					env.define((Symbol) formals, args);
					return Evaluator.eval(this.body, env);
				}
				env.define((Symbol) ((ConsCell) formals).getCar(), ((ConsCell) args).getCar());
				formals = ((ConsCell) formals).getCdr();
				args = ((ConsCell) args).getCdr();
			}
			if (!(args instanceof EmptyList)) { // 引数が多すぎる
				throw new SyntaxErrorException("malformed closure");
			}
			return Evaluator.eval(this.body, env);
		} catch (ClassCastException e) {
			throw new SyntaxErrorException("malformed closure");
		}
	}

	@Override
	public String toString() {
		return "#<closure #f>";
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Closure && this.env.equals(((Closure) obj).env)
				&& this.formals.equals(((Closure) obj).formals) && this.body.equals(((Closure) obj).body);
	}

	@Override
	public boolean isList() {
		return false;
	}
}