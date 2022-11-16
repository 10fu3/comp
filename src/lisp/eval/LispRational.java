package lisp.eval;

import java.math.BigDecimal;

/**
 * 有理数値
 * 
 * @author koizumi yuhi
 *
 */
public class LispRational implements LispReal {
	private BigRational value;

	public BigRational getValue() {
		return value;
	}

	private LispRational(BigRational value) {
		this.value = value;
	}

	/**
	 * 有理数値のインスタンスを得る。
	 * 
	 * @param value 有理数値
	 * @return 有理数値のインスタンス
	 * 
	 */
	public static LispRational valueOf(BigRational value) {
		return new LispRational(value);
	}

	@Override
	public LispNumber add(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispRational.valueOf(this.value.add(new BigRational(((LispInteger) value).getValue())));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf(this.value.toBigDecimal(LispFloat.mc).add(((LispFloat) value).getValue()));
		}
		if (value instanceof LispRational) {
			return LispRational.valueOf((this.value).add(((LispRational) value).getValue()));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(new BigComplex(this.value.toBigDecimal(LispFloat.mc))
					.add(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(new BigQuaternion(this.value.toBigDecimal(LispFloat.mc))
					.add(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber subtract(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispRational.valueOf(this.value.subtract(new BigRational(((LispInteger) value).getValue())));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf(this.value.toBigDecimal(LispFloat.mc).subtract(((LispFloat) value).getValue()));
		}
		if (value instanceof LispRational) {
			return LispRational.valueOf((this.value).subtract(((LispRational) value).getValue()));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(new BigComplex(this.value.toBigDecimal(LispFloat.mc))
					.subtract(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(new BigQuaternion(this.value.toBigDecimal(LispFloat.mc))
					.subtract(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber multiply(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispRational.valueOf(this.value.multiply(new BigRational(((LispInteger) value).getValue())));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf(this.value.toBigDecimal(LispFloat.mc).multiply(((LispFloat) value).getValue()));
		}
		if (value instanceof LispRational) {
			return LispRational.valueOf((this.value).multiply(((LispRational) value).getValue()));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(new BigComplex(this.value.toBigDecimal(LispFloat.mc))
					.multiply(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(new BigQuaternion(this.value.toBigDecimal(LispFloat.mc))
					.multiply(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber divide(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispRational.valueOf(this.value.divide(new BigRational(((LispInteger) value).getValue())));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf(this.value.toBigDecimal(LispFloat.mc).divide(((LispFloat) value).getValue()));
		}
		if (value instanceof LispRational) {
			return LispRational.valueOf((this.value).divide(((LispRational) value).getValue()));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(new BigComplex(this.value.toBigDecimal(LispFloat.mc))
					.divide(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion.valueOf(new BigQuaternion(this.value.toBigDecimal(LispFloat.mc))
					.divide(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public LispNumber negate() {
		return LispRational.valueOf(this.value.negate());
	}

	@Override
	public LispNumber inverse() {
		return LispRational.valueOf(this.value.inverse());
	}

	@Override
	public int compareTo(LispNumber value) {
		if (value instanceof LispInteger) {
			return this.value.compareTo(new BigRational(((LispInteger) value).getValue()));
		}
		if (value instanceof LispFloat) {
			return this.value.compareTo(new BigRational(((LispFloat) value).getValue()));
		}
		if (value instanceof LispRational) {
			return this.value.compareTo(((LispRational) value).getValue());
		}
		if (value instanceof LispQuaternion) {
			return (new BigQuaternion(this.value.toBigDecimal(LispFloat.mc)))
					.compareTo(((LispQuaternion) value).getValue());
		}
		return -2;
	}

	@Override
	public LispNumber sin() {
		return LispFloat.valueOf(LispFloat.sin(this.value.toBigDecimal(LispFloat.mc), LispFloat.mc));
	}

	@Override
	public LispNumber cos() {
		return LispFloat.valueOf(LispFloat.cos(this.value.toBigDecimal(LispFloat.mc), LispFloat.mc));
	}

	@Override
	public LispNumber tan() {
		return LispFloat.valueOf(LispFloat.tan(this.value.toBigDecimal(LispFloat.mc), LispFloat.mc));
	}

	@Override
	public LispReal modulo(LispReal value) {
		if (value instanceof LispInteger) {
			return LispRational
					.valueOf(LispRational.modulo(this.value, new BigRational(((LispInteger) value).getValue())));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf(LispFloat.modulo(this.value.toBigDecimal(LispFloat.mc),
					((LispFloat) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispRational.valueOf(LispRational.modulo(this.value, ((LispRational) value).getValue()));
		}
		return null;
	}

	static BigRational modulo(BigRational a, BigRational b) {
		if (a.signum() < 0 && b.signum() < 0) {
			return a.remainder(b).subtract(b);
		}
		if (a.signum() < 0 && b.signum() > 0) {
			return a.remainder(b).add(b);
		}
		return a.remainder(b);
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
		return obj instanceof LispRational && this.value.equals(((LispRational) obj).getValue());
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public LispReal sqrt() {
		return LispFloat.valueOf(LispFloat.sqrt(this.getValue().toBigDecimal(LispFloat.mc), LispFloat.mc));
	}

	@Override
	public double doubleValue() {
		return this.value.toBigDecimal(LispFloat.mc).doubleValue();
	}

	@Override
	public long longValueExact() {
		return this.value.toBigIntegerExact().longValueExact();
	}

	@Override
	public LispReal toLispReal() {
		return this;
	}

	@Override
	public BigDecimal getReal() {
		return this.value.toBigDecimal(LispFloat.mc);
	}

	@Override
	public BigDecimal getImaginary1() {
		return BigDecimal.ZERO;
	}

}