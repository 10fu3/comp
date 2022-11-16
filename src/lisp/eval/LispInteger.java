package lisp.eval;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 整数値
 * 
 * @author tetsuya
 *
 */
public class LispInteger implements LispReal {
	private BigInteger value;

	public BigInteger getValue() {
		return value;
	}

	private LispInteger(BigInteger value) {
		this.value = value;
	}

	/**
	 * 整数値のインスタンスを得る。
	 * 
	 * @param value 整数値
	 * @return 整数値のインスタンス
	 */
	public static LispInteger valueOf(BigInteger value) {
		return new LispInteger(value);
	}

	@Override
	public LispNumber add(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispInteger.valueOf(this.value.add(((LispInteger) value).getValue()));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf((new BigDecimal(this.value)).add(((LispFloat) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispRational.valueOf((new BigRational(this.value)).add(((LispRational) value).getValue()));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(
					new BigComplex(new BigDecimal(this.value)).add(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(new BigQuaternion(new BigDecimal(this.value))
					.add(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber subtract(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispInteger.valueOf(this.value.subtract(((LispInteger) value).getValue()));
		}
		if (value instanceof LispFloat) {
			return LispFloat
					.valueOf((new BigDecimal(this.value)).subtract(((LispFloat) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispRational.valueOf((new BigRational(this.value)).subtract(((LispRational) value).getValue()));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(new BigComplex(new BigDecimal(this.value))
					.subtract(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(new BigQuaternion(new BigDecimal(this.value))
					.subtract(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber multiply(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispInteger.valueOf(this.value.multiply(((LispInteger) value).getValue()));
		}
		if (value instanceof LispFloat) {
			return LispFloat
					.valueOf((new BigDecimal(this.value)).multiply(((LispFloat) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispRational.valueOf((new BigRational(this.value)).multiply(((LispRational) value).getValue()));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(new BigComplex(new BigDecimal(this.value))
					.multiply(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(new BigQuaternion(new BigDecimal(this.value))
					.multiply(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber divide(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispRational.valueOf(new BigRational(this.value, ((LispInteger) value).getValue()));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf((new BigDecimal(this.value)).divide(((LispFloat) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispRational.valueOf((new BigRational(this.value)).divide(((LispRational) value).getValue()));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(
					new BigComplex(new BigDecimal(this.value)).divide(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(new BigQuaternion(new BigDecimal(this.value))
					.divide(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public int compareTo(LispNumber value) {
		if (value instanceof LispInteger) {
			return this.value.compareTo(((LispInteger) value).getValue());
		}
		if (value instanceof LispFloat) {
			return (new BigDecimal(this.value)).compareTo(((LispFloat) value).getValue());
		}
		if (value instanceof LispRational) {
			return (new BigRational(this.value)).compareTo(((LispRational) value).getValue());
		}
		if (value instanceof LispComplex) {
			return (new BigComplex(new BigDecimal(this.value)).compareTo(((LispComplex) value).getValue()));
		}
		if (value instanceof LispQuaternion) {
			return (new BigQuaternion(new BigDecimal(this.value))).compareTo(((LispQuaternion) value).getValue());
		}
		return Integer.MAX_VALUE;
	}

	static BigInteger factorial(int value) {
		if (value < 0) {
			throw new ArithmeticException();
		}
		if (value == 0) {
			return BigInteger.ONE;
		}
		// 分割統治法
		Queue<BigInteger> que = new ArrayDeque<BigInteger>(value);
		for (int i = 1; i <= value; i++) {
			que.add(BigInteger.valueOf(i));
		}
		for (int i = 1; i < value; i++) {
			BigInteger b1 = que.poll();
			BigInteger b2 = que.poll();
			que.add(b1.multiply(b2));
		}
		return que.poll();
	}

	@Override
	public LispReal modulo(LispReal value) {
		if (value instanceof LispInteger) {
			return LispInteger.valueOf(LispInteger.modulo(this.value, ((LispInteger) value).getValue()));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf(
					LispFloat.modulo(new BigDecimal(this.value), ((LispFloat) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispRational
					.valueOf(LispRational.modulo(new BigRational(this.value), ((LispRational) value).getValue()));
		}
		return null;
	}

	static BigInteger modulo(BigInteger a, BigInteger b) {
		if (a.signum() < 0 && b.signum() < 0) {
			return a.remainder(b).subtract(b);
		}
		if (a.signum() < 0 && b.signum() > 0) {
			return a.remainder(b).add(b);
		}
		return a.remainder(b);
	}

	static BigDecimal exp(BigInteger value) {
		return LispFloat.E.pow(value.intValueExact(), LispFloat.mc);
	}

	@Override
	public LispNumber negate() {
		return LispInteger.valueOf(this.value.negate());
	}

	@Override
	public LispNumber inverse() {
		return LispRational.valueOf(new BigRational(BigInteger.ONE, this.value));
	}

	@Override
	public LispNumber sin() {
		return LispFloat.valueOf(LispFloat.sin(new BigDecimal(this.value), LispFloat.mc));
	}

	@Override
	public LispNumber cos() {
		return LispFloat.valueOf(LispFloat.cos(new BigDecimal(this.value), LispFloat.mc));
	}

	@Override
	public LispNumber tan() {
		return LispFloat.valueOf(LispFloat.tan(new BigDecimal(this.value), LispFloat.mc));
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

	@Override
	public int hashCode() {
		return this.value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof LispInteger && this.value.equals(((LispInteger) obj).getValue());
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public LispReal sqrt() {
		return LispFloat.valueOf(LispFloat.sqrt(new BigDecimal(this.value), LispFloat.mc));
	}

	public LispInteger factorial() {
		return LispInteger.valueOf(LispInteger.factorial(this.value.intValueExact()));
	}

	@Override
	public double doubleValue() {
		return this.value.doubleValue();
	}

	@Override
	public LispReal toLispReal() {
		return this;
	}

	@Override
	public long longValueExact() {
		return this.value.longValueExact();
	}

	@Override
	public BigDecimal getReal() {
		return new BigDecimal(this.value);
	}

	@Override
	public BigDecimal getImaginary1() {
		return BigDecimal.ZERO;
	}

}