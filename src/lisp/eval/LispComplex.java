package lisp.eval;

import java.math.BigDecimal;
import java.math.MathContext;

import lisp.exception.LispException;

/**
 * 複素数値
 * 
 * @author koizumi
 *
 */
public class LispComplex implements LispNumber {
	private BigComplex value;

	public BigComplex getValue() {
		return value;
	}

	private LispComplex(BigComplex value) {
		this.value = value;
	}

	/**
	 * 整数値のインスタンスを得る。
	 * 
	 * @param value 整数値
	 * @return 整数値のインスタンス
	 */
	public static LispComplex valueOf(BigComplex value) {
		return new LispComplex(value);
	}

	@Override
	public LispNumber add(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispComplex.valueOf(
					this.value.add(new BigComplex(new BigDecimal(((LispInteger) value).getValue())), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispComplex.valueOf(this.value.add(new BigComplex(((LispFloat) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispComplex.valueOf(this.value
					.add(new BigComplex(((LispRational) value).getValue().toBigDecimal(LispFloat.mc)), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(this.value.add(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion
					.valueOf(new BigQuaternion(this.value).add(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber subtract(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispComplex.valueOf(this.value
					.subtract(new BigComplex(new BigDecimal(((LispInteger) value).getValue())), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispComplex
					.valueOf(this.value.subtract(new BigComplex(((LispFloat) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispComplex.valueOf(this.value.subtract(
					new BigComplex(((LispRational) value).getValue().toBigDecimal(LispFloat.mc)), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(this.value.subtract(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion
					.valueOf(new BigQuaternion(this.value).subtract(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber multiply(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispComplex.valueOf(this.value
					.multiply(new BigComplex(new BigDecimal(((LispInteger) value).getValue())), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispComplex
					.valueOf(this.value.multiply(new BigComplex(((LispFloat) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispComplex.valueOf(this.value.multiply(
					new BigComplex(((LispRational) value).getValue().toBigDecimal(LispFloat.mc)), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(this.value.multiply(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion
					.valueOf(new BigQuaternion(this.value).multiply(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber divide(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispComplex.valueOf(
					this.value.divide(new BigComplex(new BigDecimal(((LispInteger) value).getValue())), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispComplex.valueOf(this.value.divide(new BigComplex(((LispFloat) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispComplex.valueOf(this.value.divide(
					new BigComplex(((LispRational) value).getValue().toBigDecimal(LispFloat.mc)), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(this.value.divide(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion
					.valueOf(new BigQuaternion(this.value).divide(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber negate() {
		return LispComplex.valueOf(this.value.negate(LispFloat.mc));
	}

	@Override
	public LispNumber inverse() {
		return LispComplex.valueOf(this.value.inverse(LispFloat.mc));
	}

	@Override
	public LispNumber sin() {
		return LispComplex.valueOf(LispComplex.sin(this.value, LispFloat.mc));
	}

	@Override
	public LispNumber cos() {
		return LispComplex.valueOf(LispComplex.cos(this.value, LispFloat.mc));
	}

	static BigComplex sin(BigComplex value, MathContext mc) {
		if (value.getImaginary1().signum() == 0) {
			return new BigComplex(LispFloat.sin(value.getReal(), mc));
		}
		BigDecimal abs = LispComplex.abs(value.getImaginary(), mc);
		return new BigComplex(LispFloat.sin(value.getReal(), mc).multiply(LispFloat.cosh(abs, mc), mc)).add(
				new BigComplex(LispFloat.cos(value.getReal(), mc).multiply(LispFloat.sinh(abs, mc).divide(abs, mc)))
						.multiply(value.getImaginary(), mc),
				mc);
	}

	static BigComplex cos(BigComplex value, MathContext mc) {
		if (value.getImaginary1().signum() == 0) {
			return new BigComplex(LispFloat.cos(value.getReal(), mc));
		}
		BigDecimal abs;
		abs = LispComplex.abs(value.getImaginary(), mc);
		return new BigComplex(LispFloat.cos(value.getReal(), mc).multiply(LispFloat.cosh(abs, mc), mc)).subtract(
				new BigComplex(LispFloat.sin(value.getReal(), mc).multiply(LispFloat.sinh(abs, mc).divide(abs, mc)))
						.multiply(value.getImaginary(), mc),
				mc);
	}

	static BigComplex tan(BigComplex value, MathContext mc) {
		// tan x = sin x / cos x
		return sin(value, mc).divide(cos(value, mc), mc);
	}

	@Override
	public LispNumber tan() {
		return LispComplex.valueOf(LispComplex.tan(this.value, LispFloat.mc));
	}

	static BigComplex exp(BigComplex value, MathContext mc) throws LispException {
		if (value.getImaginary1().signum() == 0) {
			return new BigComplex(LispFloat.exp(value.getReal(), mc));
		}
		BigDecimal abs = LispComplex.abs(value.getImaginary(), mc);
		return new BigComplex(LispFloat.exp(value.getReal(), mc))
				.multiply((new BigComplex(LispFloat.cos(abs, mc))).add(value.getImaginary()
						.divide(new BigComplex(abs), mc).multiply(new BigComplex(LispFloat.sin(abs, mc)), mc), mc), mc);
	}

	static BigDecimal abs(BigComplex value, MathContext mc) {
		return LispFloat.sqrt(value.getReal().multiply(value.getReal(), mc)
				.add(value.getImaginary1().multiply(value.getImaginary1(), mc), mc), mc);
	}

	public LispReal toLispReal() {
		if (this.value.getImaginary1().signum() == 0) {
			return LispFloat.valueOf(this.value.getReal());
		}
		throw new ArithmeticException();
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
		return obj instanceof LispComplex && this.value.equals(((LispComplex) obj).getValue());
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public int compareTo(LispNumber value) {
		if (value instanceof LispInteger) {
			return this.value.compareTo(new BigComplex(new BigDecimal(((LispInteger) value).getValue())));
		}
		if (value instanceof LispFloat) {
			return this.value.compareTo(new BigComplex(((LispFloat) value).getValue()));
		}
		if (value instanceof LispRational) {
			return this.value.compareTo(new BigComplex(((LispRational) value).getValue().toBigDecimal(LispFloat.mc)));
		}
		if (value instanceof LispComplex) {
			return this.value.compareTo(((LispComplex) value).getValue());
		}
		if (value instanceof LispQuaternion) {
			return new BigQuaternion(this.value).compareTo(((LispQuaternion) value).getValue());
		}
		return Integer.MAX_VALUE;
	}

	@Override
	public BigDecimal getReal() {
		return this.value.getReal();
	}

	@Override
	public BigDecimal getImaginary1() {
		// TODO Auto-generated method stub
		return this.value.getImaginary1();
	}

}