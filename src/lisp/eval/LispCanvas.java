package lisp.eval;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JPanel;

import lisp.exception.SyntaxErrorException;

/**
 * タートルグラフィックス
 * 
 * @author utsumi
 *
 */
public class LispCanvas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long index = 0L;
	private java.util.Map<Long, Shape> shape = new ConcurrentHashMap<Long, Shape>();
	private java.util.Map<Long, Color> color = new ConcurrentHashMap<Long, Color>();
	private java.util.Map<Long, Boolean> visible = new ConcurrentHashMap<Long, Boolean>();
	/**
	 * 座標の範囲
	 */

	private double min_x = 0;
	private double max_x = this.getSize().getWidth();
	private double min_y = 0;
	private double max_y = this.getSize().getHeight();

	public LispCanvas() {
		super();
	}

	public void setRange(double min_x, double min_y, double max_x, double max_y) {
		this.min_x = min_x;
		this.max_x = max_x;
		this.min_y = min_y;
		this.max_y = max_y;
		repaint();
	}

	public void setRange() {
		this.min_x = 0;
		this.max_x = this.getSize().getWidth();
		this.min_y = 0;
		this.max_y = this.getSize().getHeight();
		repaint();
	}

	/**
	 * 直線を書く
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Long drawLine(double x1, double y1, double x2, double y2) {
		this.shape.put(this.index, new Line2D.Double(x1, y1, x2, y2));
		repaint();
		return this.index++;
	}

	public Long drawCircle(double x1, double y1, double radius) {
		this.shape.put(this.index, new Ellipse2D.Double(x1 - radius, y1 - radius, radius * 2, radius * 2));
		repaint();
		return this.index++;
	}

	public void setColor(long index, long color) throws SyntaxErrorException {
		if (!(this.shape.containsKey(index))) {
			throw new NullPointerException();
		}
		int red = (int) ((color & 0xFF000000L) >> 24);
		int green = (int) ((color & 0x00FF0000L) >> 16);
		int blue = (int) ((color & 0x0000FF00L) >> 8);
		int alpha = (int) (color & 0x000000FFL);
		repaint();
		this.color.put(index, new Color(red, green, blue, alpha));
	}

	public void setVisible(long index, boolean visible) {
		if (!(this.shape.containsKey(index))) {
			throw new NullPointerException();
		}
		this.visible.put(index, visible);
	}

	public void setBackground(long color) throws SyntaxErrorException {
		int red = (int) ((color & 0xFF000000L) >> 24);
		int green = (int) ((color & 0x00FF0000L) >> 16);
		int blue = (int) ((color & 0x0000FF00L) >> 8);
		int alpha = (int) (color & 0x000000FFL);
		setBackground(new Color(red, green, blue, alpha));
		repaint();
	}

	public void clear(long index) {
		this.shape.remove(index);
		this.color.remove(index);
		this.visible.remove(index);
		repaint();
	}

	public void clear() {
		this.shape.clear();
		this.color.clear();
		this.visible.clear();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.transform(new AffineTransform());
		g2.scale(this.getSize().getWidth() / (this.max_x - this.min_x),
				-this.getSize().getHeight() / (this.max_y - this.min_y));
		g2.translate(-this.min_x, -this.max_y);
		this.shape.keySet().forEach(a -> {
			if (this.visible.getOrDefault(a, false)) {
				g2.setColor(this.color.get(a));
				g2.draw(this.shape.get(a));
			}
		});
	}
}