package turtle;

/**
 * コッホ曲線
 * 
 * @author tetsuya
 *
 */
public class KochCurve {
	private TurtleGraphics turtleGraphics;

	public KochCurve() {
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

		draw(length / 3, level - 1);

		this.turtleGraphics.turn(60);
		draw(length / 3, level - 1);

		this.turtleGraphics.turn(-120);
		draw(length / 3, level - 1);

		this.turtleGraphics.turn(60);
		draw(length / 3, level - 1);
	}

	public static void main(String[] args) {
		KochCurve koch = new KochCurve();
		koch.draw(200, 3);
	}
}
