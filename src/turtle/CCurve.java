package turtle;

/**
 * C曲線
 * 
 * @author tetsuya
 *
 */
public class CCurve {
	private TurtleGraphics turtleGraphics;

	public CCurve() {
		this.turtleGraphics = new TurtleGraphics();
		this.turtleGraphics.pendown();
	}

	/**
	 * 図形を描画する。
	 * 
	 * @param length 置換する線分の長さ
	 * @param level  置換レベル
	 */
	public void draw(double length, int level) {
		if (level == 0) {
			this.turtleGraphics.forward(length);
			return;
		}
		double nextLength = length * Math.sqrt(2.0) / 2;

		this.turtleGraphics.turn(45);
		draw(nextLength, level - 1);

		this.turtleGraphics.turn(-90);
		draw(nextLength, level - 1);

		this.turtleGraphics.turn(45);
	}

	public static void main(String[] args) {
		CCurve cCurve = new CCurve();
		cCurve.draw(120, 8);
	}
}