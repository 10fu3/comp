
package lisp.eval;

import lisp.exception.LispException;

/**
 * 組み込み手続き
 * 
 * @author tetsuya
 *
 */
public interface Subroutine extends SExpression {
	public SExpression applyTo(SExpression args) throws LispException;
}
