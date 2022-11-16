package lisp.eval;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigQuaternion implements Comparable<BigQuaternion> {
	private BigDecimal real; // 実部
	private BigDecimal imaginary1;
	private BigDecimal imaginary2;
	private BigDecimal imaginary3;

	public static final BigQuaternion ZERO = new BigQuaternion(BigDecimal.ZERO);
	public static final BigQuaternion ONE = new BigQuaternion(BigDecimal.ONE);
	public static final BigQuaternion I = new BigQuaternion(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO,
			BigDecimal.ZERO);
	public static final BigQuaternion J = new BigQuaternion(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE,
			BigDecimal.ZERO);
	public static final BigQuaternion K = new BigQuaternion(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
			BigDecimal.ONE);

	public BigQuaternion(BigDecimal real, BigDecimal imaginary1, BigDecimal imaginary2, BigDecimal imaginary3) {
		this.real = real;
		this.imaginary1 = imaginary1;
		this.imaginary2 = imaginary2;
		this.imaginary3 = imaginary3;
	}

	public BigQuaternion(BigComplex value) {
		this.real = value.getReal();
		this.imaginary1 = value.getImaginary1();
		this.imaginary2 = BigDecimal.ZERO;
		this.imaginary3 = BigDecimal.ZERO;
	}

	public BigQuaternion(BigDecimal value) {
		this.real = value;
		this.imaginary1 = BigDecimal.ZERO;
		this.imaginary2 = BigDecimal.ZERO;
		this.imaginary3 = BigDecimal.ZERO;
	}

	public BigDecimal getReal() {
		return this.real;
	}

	public BigDecimal getImaginary1() {
		return this.imaginary1;
	}

	public BigDecimal getImaginary2() {
		return this.imaginary2;
	}

	public BigDecimal getImaginary3() {
		return this.imaginary3;
	}

	public BigQuaternion getImaginary() {
		return new BigQuaternion(BigDecimal.ZERO, this.imaginary1, this.imaginary2, this.imaginary3);
	}

	/**
	 * 
	 * 足し算
	 * 
	 * @param value 足す値
	 * @return 足した値
	 */
	public BigQuaternion add(BigQuaternion value, MathContext mc) {
		return new BigQuaternion(this.real.add(value.getReal(), mc), this.imaginary1.add(value.imaginary1, mc),
				this.imaginary2.add(value.imaginary2, mc), this.imaginary3.add(value.imaginary3, mc));
	}

	/**
	 * 
	 * 引き算
	 * 
	 * @param value 引く値
	 * @return 引いた値
	 */
	public BigQuaternion subtract(BigQuaternion value, MathContext mc) {
		return this.add(value.negate(mc), mc);
	}

	/**
	 * 
	 * 符号を反転させる
	 * 
	 * @return 符号を反転させた結果
	 */
	public BigQuaternion negate(MathContext mc) {
		return new BigQuaternion(this.real.negate(mc), this.imaginary1.negate(mc), this.imaginary2.negate(mc),
				this.imaginary3.negate(mc));
	}

	/**
	 * 
	 * 掛け算
	 * 
	 * @param value かける値
	 * @return かけた値
	 */
	public BigQuaternion multiply(BigQuaternion value, MathContext mc) {
		return new BigQuaternion(
				this.real.multiply(value.real, mc).subtract(this.imaginary1.multiply(value.imaginary1, mc), mc)
						.subtract(this.imaginary2.multiply(value.imaginary2, mc), mc)
						.subtract(this.imaginary3.multiply(value.imaginary3), mc),
				this.real.multiply(value.imaginary1, mc).add(this.imaginary1.multiply(value.real, mc), mc)
						.add(this.imaginary2.multiply(value.imaginary3, mc), mc)
						.subtract(this.imaginary3.multiply(value.imaginary2), mc),
				this.real.multiply(value.imaginary2, mc).subtract(this.imaginary1.multiply(value.imaginary3, mc), mc)
						.add(this.imaginary2.multiply(value.real, mc), mc)
						.add(this.imaginary3.multiply(value.imaginary1), mc),
				this.real.multiply(value.imaginary3, mc).add(this.imaginary1.multiply(value.imaginary2, mc), mc)
						.subtract(this.imaginary2.multiply(value.imaginary1, mc), mc)
						.add(this.imaginary3.multiply(value.real), mc));
	}

	/**
	 * 
	 * 割り算
	 * 
	 * @param value 割る値
	 * @return 割った値
	 */
	public BigQuaternion divide(BigQuaternion value, MathContext mc) {
		return this.multiply(value.inverse(mc), mc);
	}

	public BigQuaternion inverse(MathContext mc) {
		BigDecimal abs_square = this.real.multiply(this.real, mc).add(this.imaginary1.multiply(this.imaginary1, mc), mc)
				.add(this.imaginary2.multiply(this.imaginary2, mc), mc)
				.add(this.imaginary3.multiply(this.imaginary3, mc), mc);
		return new BigQuaternion(this.real.divide(abs_square, mc), this.imaginary1.negate(mc).divide(abs_square, mc),
				this.imaginary2.negate(mc).divide(abs_square, mc), this.imaginary3.negate(mc).divide(abs_square, mc));
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		// 1
		if (this.real.signum() != 0) {
			result.append(this.real.stripTrailingZeros().toString());
		}
		// i
		if (this.real.signum() != 0 && this.imaginary1.signum() > 0) {
			result.append("+");
		}
		if (this.imaginary1.signum() != 0 && !this.imaginary1.abs().equals(BigDecimal.ONE)) {
			result.append(this.imaginary1.stripTrailingZeros().toString());
		}
		if (this.imaginary1.equals(BigDecimal.ONE.negate())) {
			result.append("-");
		}
		if (this.imaginary1.signum() != 0) {
			result.append("i");
		}
		// j
		if ((this.real.signum() != 0 || this.imaginary1.signum() != 0) && this.imaginary2.signum() > 0) {
			result.append("+");
		}
		if (this.imaginary2.signum() != 0 && !this.imaginary2.abs().equals(BigDecimal.ONE)) {
			result.append(this.imaginary2.stripTrailingZeros().toString());
		}
		if (this.imaginary2.equals(BigDecimal.ONE.negate())) {
			result.append("-");
		}
		if (this.imaginary2.signum() != 0) {
			result.append("j");
		}
		// k
		if ((this.real.signum() != 0 || this.imaginary1.signum() != 0 || this.imaginary2.signum() != 0)
				&& this.imaginary3.signum() > 0) {
			result.append("+");
		}
		if (this.imaginary3.signum() != 0 && !this.imaginary3.abs().equals(BigDecimal.ONE)) {
			result.append(this.imaginary3.stripTrailingZeros().toString());
		}
		if (this.imaginary3.equals(BigDecimal.ONE.negate())) {
			result.append("-");
		}
		if (this.imaginary3.signum() != 0) {
			result.append("k");
		}
		// 0
		if (result.length() == 0) {
			return "0";
		}
		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof BigQuaternion) && this.real.equals(((BigQuaternion) obj).real)
				&& this.imaginary1.equals(((BigQuaternion) obj).imaginary1)
				&& this.imaginary2.equals(((BigQuaternion) obj).imaginary2)
				&& this.imaginary3.equals(((BigQuaternion) obj).imaginary3);
	}

	@Override
	public int compareTo(BigQuaternion value) {
		if (this.getImaginary().equals(value.getImaginary())) {
			return this.real.compareTo(value.real);
		}
		throw new ArithmeticException("等しくないので比較できません");
	}
}