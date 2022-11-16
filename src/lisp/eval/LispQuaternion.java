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
public class LispQuaternion implements LispNumber {
	private BigQuaternion value;

	public BigQuaternion getValue() {
		return value;
	}

	private LispQuaternion(BigQuaternion value) {
		this.value = value;
	}

	/**
	 * 整数値のインスタンスを得る。
	 * 
	 * @param value 整数値
	 * @return 整数値のインスタンス
	 */
	public static LispQuaternion valueOf(BigQuaternion value) {
		return new LispQuaternion(value);
	}

	@Override
	public LispNumber add(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispQuaternion.valueOf(
					this.value.add(new BigQuaternion(new BigDecimal(((LispInteger) value).getValue())), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispQuaternion
					.valueOf(this.value.add(new BigQuaternion(((LispFloat) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispQuaternion.valueOf(this.value.add(
					new BigQuaternion(((LispRational) value).getValue().toBigDecimal(LispFloat.mc)), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispQuaternion
					.valueOf(this.value.add(new BigQuaternion(((LispComplex) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(this.value.add(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber subtract(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispQuaternion.valueOf(this.value
					.subtract(new BigQuaternion(new BigDecimal(((LispInteger) value).getValue())), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispQuaternion
					.valueOf(this.value.subtract(new BigQuaternion(((LispFloat) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispQuaternion.valueOf(this.value.subtract(
					new BigQuaternion(((LispRational) value).getValue().toBigDecimal(LispFloat.mc)), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispQuaternion
					.valueOf(this.value.subtract(new BigQuaternion(((LispComplex) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(this.value.subtract(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber multiply(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispQuaternion.valueOf(this.value
					.multiply(new BigQuaternion(new BigDecimal(((LispInteger) value).getValue())), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispQuaternion
					.valueOf(this.value.multiply(new BigQuaternion(((LispFloat) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispQuaternion.valueOf(this.value.multiply(
					new BigQuaternion(((LispRational) value).getValue().toBigDecimal(LispFloat.mc)), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispQuaternion
					.valueOf(this.value.multiply(new BigQuaternion(((LispComplex) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(this.value.multiply(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber divide(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispQuaternion.valueOf(this.value
					.divide(new BigQuaternion(new BigDecimal(((LispInteger) value).getValue())), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispQuaternion
					.valueOf(this.value.divide(new BigQuaternion(((LispFloat) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispQuaternion.valueOf(this.value.divide(
					new BigQuaternion(((LispRational) value).getValue().toBigDecimal(LispFloat.mc)), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispQuaternion
					.valueOf(this.value.divide(new BigQuaternion(((LispComplex) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(this.value.divide(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber negate() {
		return LispQuaternion.valueOf(this.value.negate(LispFloat.mc));
	}

	@Override
	public LispNumber inverse() {
		return LispQuaternion.valueOf(this.value.inverse(LispFloat.mc));
	}

	@Override
	public LispNumber sin() {
		return LispQuaternion.valueOf(LispQuaternion.sin(this.value, LispFloat.mc));
	}

	@Override
	public LispNumber cos() {
		return LispQuaternion.valueOf(LispQuaternion.cos(this.value, LispFloat.mc));
	}

	static BigQuaternion sin(BigQuaternion value, MathContext mc) {
		if (value.getImaginary1().signum() == 0 && value.getImaginary2().signum() == 0
				&& value.getImaginary3().signum() == 0) {
			return new BigQuaternion(LispFloat.sin(value.getReal(), mc));
		}
		BigDecimal abs = LispQuaternion.abs(value.getImaginary(), mc);
		return new BigQuaternion(LispFloat.sin(value.getReal(), mc).multiply(LispFloat.cosh(abs, mc), mc)).add(
				new BigQuaternion(LispFloat.cos(value.getReal(), mc).multiply(LispFloat.sinh(abs, mc).divide(abs, mc)))
						.multiply(value.getImaginary(), mc),
				mc);
	}

	static BigQuaternion cos(BigQuaternion value, MathContext mc) {
		if (value.getImaginary1().signum() == 0 && value.getImaginary2().signum() == 0
				&& value.getImaginary3().signum() == 0) {
			return new BigQuaternion(LispFloat.cos(value.getReal(), mc));
		}
		BigDecimal abs;
		abs = LispQuaternion.abs(value.getImaginary(), mc);
		return new BigQuaternion(LispFloat.cos(value.getReal(), mc).multiply(LispFloat.cosh(abs, mc), mc)).subtract(
				new BigQuaternion(LispFloat.sin(value.getReal(), mc).multiply(LispFloat.sinh(abs, mc).divide(abs, mc)))
						.multiply(value.getImaginary(), mc),
				mc);
	}

	static BigQuaternion tan(BigQuaternion value, MathContext mc) {
		// tan x = sin x / cos x
		return sin(value, mc).divide(cos(value, mc), mc);
	}

	@Override
	public LispNumber tan() {
		return LispQuaternion.valueOf(LispQuaternion.tan(this.value, LispFloat.mc));
	}

	static BigQuaternion exp(BigQuaternion value, MathContext mc) throws LispException {
		if (value.getImaginary1().signum() == 0 && value.getImaginary2().signum() == 0
				&& value.getImaginary3().signum() == 0) {
			return new BigQuaternion(LispFloat.exp(value.getReal(), mc));
		}
		BigDecimal abs = LispQuaternion.abs(value.getImaginary(), mc);
		return new BigQuaternion(LispFloat.exp(value.getReal(), mc)).multiply(
				(new BigQuaternion(LispFloat.cos(abs, mc))).add(value.getImaginary().divide(new BigQuaternion(abs), mc)
						.multiply(new BigQuaternion(LispFloat.sin(abs, mc)), mc), mc),
				mc);
	}

	static BigDecimal abs(BigQuaternion value, MathContext mc) {
		return LispFloat.sqrt(value.getReal().multiply(value.getReal(), mc)
				.add(value.getImaginary1().multiply(value.getImaginary1(), mc), mc)
				.add(value.getImaginary2().multiply(value.getImaginary2(), mc), mc)
				.add(value.getImaginary3().multiply(value.getImaginary3(), mc), mc), mc);
	}

	public LispReal toLispReal() {
		if (this.value.getImaginary1().signum() == 0 && this.value.getImaginary2().signum() == 0
				&& this.value.getImaginary3().signum() == 0) {
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
		return obj instanceof LispQuaternion && this.value.equals(((LispQuaternion) obj).getValue());
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public int compareTo(LispNumber value) {
		if (value instanceof LispInteger) {
			return this.value.compareTo(new BigQuaternion(new BigDecimal(((LispInteger) value).getValue())));
		}
		if (value instanceof LispFloat) {
			return this.value.compareTo(new BigQuaternion(((LispFloat) value).getValue()));
		}
		if (value instanceof LispRational) {
			return this.value
					.compareTo(new BigQuaternion(((LispRational) value).getValue().toBigDecimal(LispFloat.mc)));
		}
		if (value instanceof LispComplex) {
			return this.value.compareTo(new BigQuaternion(((LispComplex) value).getValue()));
		}
		if (value instanceof LispQuaternion) {
			return this.value.compareTo(((LispQuaternion) value).getValue());
		}
		return Integer.MAX_VALUE;
	}

	@Override
	public BigDecimal getReal() {
		return this.value.getReal();
	}

	@Override
	public BigDecimal getImaginary1() {
		return this.value.getImaginary1();
	}

}