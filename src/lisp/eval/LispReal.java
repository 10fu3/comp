package lisp.eval;

public interface LispReal extends LispNumber {

	public LispReal modulo(LispReal value);

	public LispReal sqrt();

	public double doubleValue();

	public long longValueExact();
}