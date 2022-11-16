package lisp.eval;

import lisp.exception.LispException;

/**
 * 特殊形式
 * 
 * @author tetsuya
 *
 */
public interface SpecialForm extends SExpression {
	public SExpression applyTo(SExpression args, Environment env) throws LispException;
}
