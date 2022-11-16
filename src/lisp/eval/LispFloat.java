package lisp.eval;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * 浮動小数点値
 * 
 * @author tetsuya
 *
 */
public class LispFloat implements LispReal {
	private BigDecimal value;
	public static MathContext mc = new MathContext(50, RoundingMode.HALF_UP);
	public static int max_i = 50;
	public static final BigDecimal PI = new BigDecimal(
			"3.141592653589793238462643383279502884197169399375105820974944"); // 円周率
	public static final BigDecimal E = new BigDecimal("2.71828182845904523536028747135266249775724709369995"); // 自然対数の底

	public BigDecimal getValue() {
		return value;
	}

	private LispFloat(BigDecimal value) {
		this.value = value;
	}

	/**
	 * 浮動小数点数の値のインスタンスを得る。
	 * 
	 * @param value 浮動小数点数
	 * @return 整数値のインスタンス
	 */
	public static LispFloat valueOf(BigDecimal value) {
		return new LispFloat(value);
	}

	@Override
	public LispNumber add(LispNumber value) {
		if (value instanceof LispInteger) {
			return LispFloat.valueOf(this.value.add(new BigDecimal(((LispInteger) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf(this.value.add(((LispFloat) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispFloat.valueOf(
					this.value.add(((LispRational) value).getValue().toBigDecimal(LispFloat.mc), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispComplex.valueOf(new BigComplex(this.value).add(((LispComplex) value).getValue(), LispFloat.mc));
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
			return LispFloat
					.valueOf(this.value.subtract(new BigDecimal(((LispInteger) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf(this.value.subtract(((LispFloat) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispFloat.valueOf(
					this.value.subtract(((LispRational) value).getValue().toBigDecimal(LispFloat.mc), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispComplex
					.valueOf(new BigComplex(this.value).subtract(((LispComplex) value).getValue(), LispFloat.mc));
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
			return LispFloat
					.valueOf(this.value.multiply(new BigDecimal(((LispInteger) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf(this.value.multiply(((LispFloat) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispFloat.valueOf(
					this.value.multiply(((LispRational) value).getValue().toBigDecimal(LispFloat.mc), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispComplex
					.valueOf(new BigComplex(this.value).multiply(((LispComplex) value).getValue(), LispFloat.mc));
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
			return LispFloat.valueOf(this.value.divide(new BigDecimal(((LispInteger) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf(this.value.divide(((LispFloat) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispFloat.valueOf(
					this.value.divide(((LispRational) value).getValue().toBigDecimal(LispFloat.mc), LispFloat.mc));
		}
		if (value instanceof LispComplex) {
			return LispComplex
					.valueOf(new BigComplex(this.value).divide(((LispComplex) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispQuaternion) {
			return LispQuaternion
					.valueOf(new BigQuaternion(this.value).divide(((LispQuaternion) value).getValue(), LispFloat.mc));
		}
		return null;
	}

	@Override
	public int compareTo(LispNumber value) {
		if (value instanceof LispInteger) {
			return (this.value.compareTo(new BigDecimal(((LispInteger) value).getValue())));
		}
		if (value instanceof LispFloat) {
			return this.value.compareTo(((LispFloat) value).getValue());
		}
		if (value instanceof LispRational) {
			return new BigRational(this.value).compareTo(((LispRational) value).getValue());
		}
		if (value instanceof LispComplex) {
			return new BigComplex(this.value).compareTo(((LispComplex) value).getValue());
		}
		if (value instanceof LispQuaternion) {
			return new BigQuaternion(this.value).compareTo(((LispQuaternion) value).getValue());
		}
		return Integer.MAX_VALUE;
	}

	@Override
	public LispNumber negate() {
		return valueOf(this.value.negate());
	}

	@Override
	public LispNumber inverse() {
		return LispFloat.valueOf(BigDecimal.ONE.divide(this.value, LispFloat.mc));
	}

	@Override
	public LispNumber sin() {
		return LispFloat.valueOf(sin(this.value, LispFloat.mc));
	}

	@Override
	public LispNumber cos() {
		return LispFloat.valueOf(cos(this.value, LispFloat.mc));
	}

	@Override
	public LispNumber tan() {
		return LispFloat.valueOf(tan(this.value, LispFloat.mc));
	}

	static BigDecimal sin(BigDecimal value, MathContext mc) {
		value = LispFloat.modulo(value, LispFloat.PI.multiply(new BigDecimal("2")), mc);
		if (value.compareTo(LispFloat.PI) > 0) {
			return LispFloat.sin(value.subtract(LispFloat.PI, mc), mc).negate(mc);
		}
		if (value.compareTo(LispFloat.PI.divide(new BigDecimal("2"), mc)) > 0) {
			return LispFloat.cos(value.subtract(LispFloat.PI.divide(new BigDecimal(2), mc), mc), mc);
		}
		int i = LispFloat.max_i;
		// 奇数で始める
		if ((i & 1) == 0) {
			i--;
		}
		BigDecimal result = BigDecimal.ONE;
		while (i >= 3) {
			result = result.negate(mc);
			result = result.multiply(value, mc);
			result = result.multiply(value, mc);
			result = result.divide(BigDecimal.valueOf(i--), mc);
			result = result.divide(BigDecimal.valueOf(i--), mc);
			result = result.add(BigDecimal.ONE, mc);
		}
		return result.multiply(value, mc);
	}

	static BigDecimal cos(BigDecimal value, MathContext mc) {
		value = LispFloat.modulo(value, LispFloat.PI.multiply(BigDecimal.valueOf(2L), mc), mc);
		if (value.compareTo(LispFloat.PI) > 0) {
			return LispFloat.cos(value.subtract(LispFloat.PI, mc), mc).negate(mc);
		}
		if (value.compareTo(LispFloat.PI.divide(BigDecimal.valueOf(2L), mc)) > 0) {
			return LispFloat.sin(value.subtract(LispFloat.PI.divide(BigDecimal.valueOf(2L), mc), mc), mc).negate(mc);
		}
		int i = LispFloat.max_i;// 偶数で始める
		if ((i & 1) == 1) {
			i--;
		}
		BigDecimal result = BigDecimal.ONE;
		while (i >= 2) {
			result = result.negate(mc);
			result = result.multiply(value, mc);
			result = result.multiply(value, mc);
			result = result.divide(BigDecimal.valueOf(i--), mc);
			result = result.divide(BigDecimal.valueOf(i--), mc);
			result = result.add(BigDecimal.ONE, mc);
		}
		return result;
	}

	static BigDecimal sinh(BigDecimal value, MathContext mc) {
		return LispFloat.exp(value, mc).subtract(LispFloat.exp(value.negate(), mc), mc).divide(new BigDecimal("2"));
	}

	static BigDecimal cosh(BigDecimal value, MathContext mc) {
		return LispFloat.exp(value, mc).add(LispFloat.exp(value.negate(), mc), mc).divide(new BigDecimal("2"));
	}

	static BigDecimal tan(BigDecimal value, MathContext mc) {
		return sin(value, mc).divide(cos(value, mc), mc);
	}

	@Override
	public LispReal modulo(LispReal value) {
		if (value instanceof LispInteger) {
			return LispFloat.valueOf(
					LispFloat.modulo(this.value, new BigDecimal(((LispInteger) value).getValue()), LispFloat.mc));
		}
		if (value instanceof LispFloat) {
			return LispFloat.valueOf(LispFloat.modulo(this.value, ((LispFloat) value).getValue(), LispFloat.mc));
		}
		if (value instanceof LispRational) {
			return LispFloat.valueOf(LispFloat.modulo(this.value,
					((LispRational) value).getValue().toBigDecimal(LispFloat.mc), LispFloat.mc));
		}
		return null;
	}

	static BigDecimal modulo(BigDecimal a, BigDecimal b, MathContext mc) {
		if (a.signum() < 0 && b.signum() < 0) {
			return a.remainder(b, mc).subtract(b, mc);
		}
		if (a.signum() < 0 && b.signum() > 0) {
			return a.remainder(b, mc).add(b, mc);
		}
		return a.remainder(b, mc);
	}

	static BigDecimal exp(BigDecimal value, MathContext mc) {
		BigDecimal res_int = LispInteger.exp(value.toBigInteger());
		value = value.remainder(BigDecimal.ONE, mc);
		if (value.signum() == 0) {
			return res_int;
		}
		int i = LispFloat.max_i;
		BigDecimal res_float = BigDecimal.ONE;
		while (i >= 1) {
			res_float = res_float.multiply(value, mc);
			res_float = res_float.divide(BigDecimal.valueOf(i--), mc);
			res_float = res_float.add(BigDecimal.ONE, mc);
		}
		return res_float.multiply(res_int, mc);
	}

	static BigDecimal sqrt(BigDecimal value, MathContext mc) {
		if (value.signum() < 0) {
			throw new ArithmeticException("負の平方根はありません。");
		}
		if (value.signum() == 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal result;
		BigDecimal next = value;
		do {
			result = next;
			next = result.multiply(result, mc).add(value, mc).divide(result, mc).divide(new BigDecimal("2"));
		} while (next.multiply(next, mc).subtract(value, mc).abs()
				.compareTo(result.multiply(result, mc).subtract(value, mc).abs()) < 0);
		return result;
	}

	@Override
	public String toString() {
		return this.value.round(LispFloat.mc).stripTrailingZeros().toString();
	}

	@Override
	public int hashCode() {
		return this.value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof LispFloat && this.value.equals(((LispFloat) obj).getValue());
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public LispReal sqrt() {
		return LispFloat.valueOf(LispFloat.sqrt(this.getValue(), LispFloat.mc));
	}

	@Override
	public LispReal toLispReal() {
		return this;
	}

	@Override
	public double doubleValue() {
		return this.value.round(LispFloat.mc).doubleValue();
	}

	@Override
	public long longValueExact() {
		return this.value.longValueExact();
	}

	@Override
	public BigDecimal getReal() {
		return this.value;
	}

	@Override
	public BigDecimal getImaginary1() {
		return BigDecimal.ZERO;
	}
}