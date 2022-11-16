package lisp.eval;

import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;

/**
 * タートルグラフィックス
 * 
 * @author utsumi
 *
 */
public class LispWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Long index = 0L;
	private static java.util.Map<Long, LispWindow> window = new ConcurrentHashMap<Long, LispWindow>();
	private LispCanvas canvas = new LispCanvas();

	private LispWindow() {
		super();
		add(this.canvas);
		setSize(600, 400);
	}

	public static long makeInstance() {
		LispWindow.window.put(LispWindow.index, new LispWindow());
		return LispWindow.index++;
	}

	public LispCanvas getCanvas() {
		return this.canvas;
	}

	public static LispWindow getWindow(long index) {
		return LispWindow.window.get(index);
	}

	public static void clear(long index) {
		LispWindow.window.remove(index);
	}

	public static void clear() {
		LispWindow.window.clear();
	}
}