package lisp.eval;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class BigRational implements Comparable<BigRational> {
	private BigInteger numerator; // 分子
	private BigInteger denominator; // 分母
	public static final BigRational ZERO = new BigRational(BigInteger.ZERO);
	public static final BigRational ONE = new BigRational(BigInteger.ONE);

	public BigRational(BigInteger numerator, BigInteger denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		if (denominator.signum() == 0) {
			throw new ArithmeticException();
		}
		reduce();
	}

	public BigRational(BigInteger value) {
		this.numerator = value;
		this.denominator = BigInteger.ONE;
	}

	public BigRational(BigDecimal value) {
		this.numerator = value.unscaledValue();
		int scale = value.scale();
		if (scale < 0) {
			this.numerator = this.numerator.pow(-value.scale());
			this.denominator = BigInteger.ONE;
		} else {
			this.denominator = BigInteger.TEN.pow(value.scale());
		}
		reduce();
	}

	public BigInteger getDenominator() {
		return this.denominator;
	}

	public BigInteger getNumerator() {
		return this.numerator;
	}

	public void reduce() {
		if (this.numerator.signum() == 0) {
			this.numerator = BigInteger.ZERO;
			this.denominator = BigInteger.ONE;
			return;
		}
		if (this.denominator.signum() < 0) {
			this.numerator = this.numerator.negate();
			this.denominator = this.denominator.negate();
		}
		BigInteger gcd = this.denominator.gcd(this.numerator);
		this.denominator = this.denominator.divide(gcd);
		this.numerator = this.numerator.divide(gcd);
	}

	public BigDecimal toBigDecimal(MathContext mc) {
		return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator), mc);
	}

	public BigInteger toBigInteger() {
		return this.numerator.divide(this.denominator);
	}

	public BigInteger toBigIntegerExact() {
		reduce();
		if (this.denominator.equals(BigInteger.ONE)) {
			return this.numerator;
		}
		throw new ArithmeticException();
	}

	/**
	 * 
	 * 足し算
	 * 
	 * @param value 足す値
	 * @return 足した値
	 */
	public BigRational add(BigRational value) {
		reduce();
		value.reduce();
		BigRational result = new BigRational(
				this.numerator.multiply(value.getDenominator()).add(this.denominator.multiply(value.getNumerator())),
				this.denominator.multiply(value.getDenominator()));
		result.reduce();
		return result;
	}

	/**
	 * 
	 * 引き算
	 * 
	 * @param value 引く値
	 * @return 引いた値
	 */
	public BigRational subtract(BigRational value) {
		reduce();
		value.reduce();
		value = value.negate();
		BigRational result = new BigRational(
				this.numerator.multiply(value.denominator).add(this.denominator.multiply(value.numerator)),
				this.denominator.multiply(value.denominator));
		result.reduce();
		return result;
	}

	/**
	 * 
	 * 符号を反転させる
	 * 
	 * @return 符号を反転させた結果
	 */
	public BigRational negate() {
		reduce();
		BigRational result = new BigRational(this.numerator.negate(), this.denominator);
		result.reduce();
		return result;
	}

	/**
	 * 
	 * 掛け算
	 * 
	 * @param value かける値
	 * @return かけた値
	 */
	public BigRational multiply(BigRational value) {
		reduce();
		value.reduce();
		BigInteger gcd1 = this.numerator.gcd(value.getDenominator());
		BigInteger gcd2 = this.denominator.gcd(value.getNumerator());
		BigRational result = new BigRational(this.numerator.divide(gcd1).multiply(value.numerator.divide(gcd2)),
				this.denominator.divide(gcd2).multiply(value.denominator).divide(gcd1));
		result.reduce();
		return result;
	}

	/**
	 * 
	 * 割り算
	 * 
	 * @param value 割る値
	 * @return 割った値
	 */
	public BigRational divide(BigRational value) {
		reduce();
		if (value.signum() == 0) {
			throw new ArithmeticException("0で割りました。");
		}
		BigInteger gcd1 = this.numerator.gcd(value.numerator);
		BigInteger gcd2 = this.denominator.gcd(value.denominator);
		BigRational result = new BigRational(this.numerator.divide(gcd1).multiply(value.denominator).divide(gcd2),
				this.denominator.divide(gcd2).multiply(value.numerator).divide(gcd1));
		result.reduce();
		return result;
	}

	public int signum() {
		reduce();
		return this.numerator.signum();
	}

	public BigRational abs() {
		reduce();
		return new BigRational(this.numerator.abs(), this.denominator);
	}

	public BigRational remainder(BigRational value) {
		reduce();
		value.reduce();
		value = value.abs();
		return this.subtract(new BigRational(this.divide(value).toBigInteger()).multiply(value));
	}

	public BigRational inverse() {
		return new BigRational(this.denominator, this.numerator);
	}

	public int compareTo(BigRational value) {
		reduce();
		value.reduce();
		return this.numerator.multiply(value.denominator).compareTo(this.denominator.multiply(value.numerator));
	}

	@Override
	public String toString() {
		this.reduce();
		if (this.denominator.equals(BigInteger.ONE)) {
			return this.numerator.toString();
		}
		return this.numerator.toString() + "/" + this.denominator.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof BigRational && this.numerator.equals(((BigRational) obj).numerator)
				&& this.denominator.equals(((BigRational) obj).denominator);
	}
}