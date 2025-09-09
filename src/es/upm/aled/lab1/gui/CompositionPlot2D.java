package es.upm.aled.lab1.gui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * Composition to draw several plots in the same window
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class CompositionPlot2D {

	public static boolean tests = false;
	private JFrame frame;
	private Plot2D[] plots;

	/**
	 * Builds the window as a composition of plots
	 * 
	 * @param plots los diagramas que representan todas las sondas
	 */
	public CompositionPlot2D(Plot2D[] plots) {
		this.plots = plots;
		for (Plot2D plot : plots)
			plot.setContainer(this);
		init();
	}

	public static void setTests(boolean t) {
		tests = t;
	}

	/**
	 * Updates the plot matching the sampling time.
	 * 
	 * @param t Sampling time
	 */
	public void show(int t) {
		draw();
		try {
			if (!tests)
				Thread.sleep(t);
		} catch (InterruptedException e) {
			System.out.println("Error sleeping");
		}
	}

	private void draw() {
		for (Plot2D plot : plots)
			plot.draw();
		if (!tests)
			frame.repaint();
	}

	private void init() {
		if (!tests) {
			if (this.frame != null)
				this.frame.setVisible(false);
			frame = new JFrame();
			addComponentsToPane(frame.getContentPane());
			// frame.setContentPane(draw);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes all windows
			// frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes only
			// current window
			frame.setTitle("Standard Draw");
			frame.setJMenuBar(createMenuBar());
			frame.pack();
			frame.requestFocusInWindow();
			frame.setLocationByPlatform(true);
			frame.setLocation(0, 0);
			frame.setVisible(true);
		}
	}

	private void addComponentsToPane(Container pane) {
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		boolean first = true;
		for (Plot2D grafico : plots) {
			if (!first)
				pane.add(Box.createRigidArea(new Dimension(0, 2)));
			pane.add(grafico.getDraw());
			first = false;
		}
	}

	protected static JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		return menuBar;
	}

	public static void main(String[] args) {
		Plot2D plot2D = new Plot2D();
		Plot2D plot2D2 = new Plot2D(512, 256);
		Plot2D[] plotsToCompose = { plot2D, plot2D2 };
		CompositionPlot2D container = new CompositionPlot2D(plotsToCompose);
		plot2D.square(.2, .8, .1);
		plot2D.filledSquare(.8, .8, .2);
		plot2D.circle(.8, .2, .2);

		plot2D.setPenColor(Plot2D.BOOK_RED);
		plot2D.setPenRadius(.02);
		plot2D.arc(.8, .2, .1, 200, 45);

		// Draw a blue diamond
		plot2D.setPenRadius();
		plot2D.setPenColor(Plot2D.BOOK_BLUE);
		double[] x = { .1, .2, .3, .2 };
		double[] y = { .2, .3, .2, .1 };
		plot2D.filledPolygon(x, y);

		// Text
		plot2D.setPenColor(Plot2D.BLACK);
		plot2D.text(0.2, 0.5, "black text");
		plot2D.setPenColor(Plot2D.WHITE);
		plot2D.text(0.8, 0.8, "white text");

		plot2D2.square(.1, .4, .05);
		plot2D2.filledSquare(.4, .4, .1);
		plot2D2.circle(.4, .1, .1);
	}
}
