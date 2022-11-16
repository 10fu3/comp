package lisp.eval;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigComplex implements Comparable<BigComplex> {
	private BigDecimal real; // 実部
	private BigDecimal imaginary1; // 虚部

	public static final BigComplex ZERO = new BigComplex(BigDecimal.ZERO);
	public static final BigComplex ONE = new BigComplex(BigDecimal.ONE);
	public static final BigComplex I = new BigComplex(BigDecimal.ZERO, BigDecimal.ONE);

	public BigComplex(BigDecimal real, BigDecimal imaginary1) {
		this.real = real;
		this.imaginary1 = imaginary1;
	}

	public BigComplex(BigDecimal value) {
		this.real = value;
		this.imaginary1 = BigDecimal.ZERO;
	}

	public BigDecimal getReal() {
		return this.real;
	}

	public BigDecimal getImaginary1() {
		return this.imaginary1;
	}

	public BigComplex getImaginary() {
		return new BigComplex(BigDecimal.ZERO, this.imaginary1);
	}

	/**
	 * 
	 * 足し算
	 * 
	 * @param value 足す値
	 * @return 足した値
	 */
	public BigComplex add(BigComplex value, MathContext mc) {
		return new BigComplex(this.real.add(value.getReal(), mc), this.imaginary1.add(value.imaginary1, mc));
	}

	/**
	 * 
	 * 引き算
	 * 
	 * @param value 引く値
	 * @return 引いた値
	 */
	public BigComplex subtract(BigComplex value, MathContext mc) {
		return this.add(value.negate(mc), mc);
	}

	/**
	 * 
	 * 符号を反転させる
	 * 
	 * @return 符号を反転させた結果
	 */
	public BigComplex negate(MathContext mc) {
		return new BigComplex(this.real.negate(mc), this.imaginary1.negate(mc));
	}

	/**
	 * 
	 * 掛け算
	 * 
	 * @param value かける値
	 * @return かけた値
	 */
	public BigComplex multiply(BigComplex value, MathContext mc) {
		return new BigComplex(
				this.real.multiply(value.real, mc).subtract(this.imaginary1.multiply(value.imaginary1, mc), mc),
				this.real.multiply(value.imaginary1, mc).add(this.imaginary1.multiply(value.real, mc), mc));
	}

	/**
	 * 
	 * 割り算
	 * 
	 * @param value 割る値
	 * @return 割った値
	 */
	public BigComplex divide(BigComplex value, MathContext mc) {
		return this.multiply(value.inverse(mc), mc);
	}

	public BigComplex inverse(MathContext mc) {
		BigDecimal abs_square = this.real.multiply(this.real, mc).add(this.imaginary1.multiply(this.imaginary1, mc),
				mc);
		return new BigComplex(this.real.divide(abs_square, mc), this.imaginary1.negate(mc).divide(abs_square, mc));
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
		// 0
		if (result.length() == 0) {
			return "0";
		}
		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof BigComplex) && this.real.equals(((BigComplex) obj).real)
				&& this.imaginary1.equals(((BigComplex) obj).imaginary1);
	}

	@Override
	public int compareTo(BigComplex value) {
		if (this.getImaginary().equals(value.getImaginary())) {
			return this.real.compareTo(value.real);
		}
		throw new ArithmeticException("等しくないので比較できません");
	}
}