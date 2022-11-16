package lisp.eval;

import java.math.BigDecimal;

public interface LispNumber extends SExpression, Comparable<LispNumber> {

	public LispNumber add(LispNumber value);

	public LispNumber subtract(LispNumber value);

	public LispNumber multiply(LispNumber value);

	public LispNumber divide(LispNumber value);

	public LispNumber negate();

	public LispNumber inverse();

	public LispNumber sin();

	public LispNumber cos();

	public LispNumber tan();

	public LispReal toLispReal();

	public BigDecimal getReal();

	public BigDecimal getImaginary1();
}