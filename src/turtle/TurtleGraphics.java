package turtle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

/**
 * タートルグラフィックス
 * 
 * @author tetsuya
 *
 */
public class TurtleGraphics extends JFrame {
	/**
	 * タートルグラフィックスの描画場所
	 */
	private Image buffer;
	/**
	 * Imageの横幅
	 */
	private int width = 480;
	/**
	 * Imageの高さ
	 */
	private int height = 480;
	/**
	 * ペンの色
	 */
	private Color penColor = Color.BLACK;

	/**
	 * 亀
	 */
	private Turtle turtle;
	/**
	 * 亀の色
	 */
	private Color turtleColor = Color.RED;
	/**
	 * 亀の半径
	 */
	private double turtleRadius = 10;

	public TurtleGraphics() {
		super("Turtle Graphics");
		this.turtle = new Turtle(this.width / 2, this.height / 2, 0);

		setSize(this.width, this.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		this.buffer = createImage(this.width, this.height);
		Graphics g = this.buffer.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.width, this.height);

		repaint();
	}

	/**
	 * pauseの長さ(ミリ秒)
	 */
	private long delay = 50;

	/**
	 * delay ミリ秒間、動作を止める。
	 */
	private void pause() {
		try {
			Thread.sleep(delay);
		} catch (Exception e) {
		}
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	/**
	 * 亀を描画する。
	 * 
	 * @param g 描画に使用するGraphics
	 */
	private void paintTurtle(Graphics g) {
		g.setColor(this.turtleColor);

		// 円
		int x = (int) (this.turtle.getX() - this.turtleRadius);
		int y = (int) (this.turtle.getY() - this.turtleRadius);
		g.drawOval(x, y, (int) (2.0 * this.turtleRadius), (int) (2.0 * this.turtleRadius));

		// 進行方向
		int x1 = (int) this.turtle.getX();
		int y1 = (int) this.turtle.getY();
		double radian = Math.PI * this.turtle.getDirection() / 180;
		int x2 = (int) (this.turtle.getX() + this.turtleRadius * Math.cos(radian));
		int y2 = (int) (this.turtle.getY() + this.turtleRadius * Math.sin(radian));
		g.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(this.buffer, 0, 0, this);
		paintTurtle(g);
	}

	/**
	 * ペンを下ろす。
	 */
	public void pendown() {
		this.turtle.setPendown(true);
	}

	/**
	 * ペンをあげる。
	 */
	public void penup() {
		this.turtle.setPendown(false);
	}

	/**
	 * 指定した距離だけ進む。
	 * 
	 * @param distance
	 */
	public void forward(double distance) {
		int x1 = (int) this.turtle.getX();
		int y1 = (int) this.turtle.getY();
		this.turtle.forward(distance);
		int x2 = (int) this.turtle.getX();
		int y2 = (int) this.turtle.getY();

		if (this.turtle.isPendown()) {
			Graphics g = this.buffer.getGraphics();
			g.setColor(this.penColor);
			g.drawLine(x1, y1, x2, y2);
		}
		repaint();
		pause();
	}

	/**
	 * 指定した角度だけ左を向く。
	 * 
	 * @param degree 角度(0-360)
	 */
	public void turn(double degree) {
		this.turtle.turn(degree);
		repaint();
		pause();
	}
}