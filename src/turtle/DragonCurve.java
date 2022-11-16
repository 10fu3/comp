package turtle;

/**
 * ドラゴン曲線
 * 
 * @author tetsuya
 *
 */
public class DragonCurve {
	private TurtleGraphics turtleGraphics;

	public DragonCurve() {
		this.turtleGraphics = new TurtleGraphics();
		this.turtleGraphics.pendown();
	}

	/**
	 * 図形を描画する。
	 * 
	 * @param length 置換する線分の長さ
	 * @param level  置換レベル
	 * @param rot    回転方向 (1 or -1)
	 */
	public void draw(double length, int level, int rot) {
		if (level == 0) {
			this.turtleGraphics.forward(length);
			return;
		}
		double nextLength = length * Math.sqrt(2.0) / 2;

		this.turtleGraphics.turn(45 * rot);
		draw(nextLength, level - 1, 1);

		this.turtleGraphics.turn(-90 * rot);
		draw(nextLength, level - 1, -1);

		this.turtleGraphics.turn(45 * rot);
	}

	public static void main(String[] args) {
		DragonCurve dragonCurve = new DragonCurve();
		dragonCurve.draw(120, 9, 1);
	}
}
