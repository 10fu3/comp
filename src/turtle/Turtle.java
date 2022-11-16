package turtle;

/**
 * タートルグラフィックスの亀
 * 
 * 座標系
 * 
 * <pre>
 * +----->X
 * |
 * |
 * V
 * Y
 * </pre>
 * 
 * @author tetsuya
 *
 */
public class Turtle {
	/**
	 * X座標
	 */
	private double x = 0.0;

	/**
	 * Y座標
	 */
	private double y = 0.0;

	/**
	 * 進行方向
	 */
	private double direction = 0.0;

	/**
	 * ペンの状態(true:ペンが下りている, false:ペンが上がっている）
	 */
	private boolean pendown = false;

	public Turtle() {
	}

	/**
	 * 亀のコンストラクタ
	 * 
	 * @param x         X座標
	 * @param y         Y座標
	 * @param direction 進行方向(0-360)
	 */
	public Turtle(double x, double y, double direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getDirection() {
		return this.direction;
	}

	public boolean isPendown() {
		return this.pendown;
	}

	public void setPendown(boolean pendown) {
		this.pendown = pendown;
	}

	/**
	 * 指定した角度だけ左を向く。
	 * 
	 * @param degree 角度(0-360)
	 */
	public void turn(double degree) {
		this.direction -= degree;
	}

	/**
	 * 指定した距離だけ前進する。
	 * 
	 * @param distance 距離
	 */
	public void forward(double distance) {
		this.x += distance * Math.cos(Math.PI * direction / 180);
		this.y += distance * Math.sin(Math.PI * direction / 180);
	}
}