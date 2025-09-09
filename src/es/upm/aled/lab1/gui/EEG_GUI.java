package es.upm.aled.lab1.gui;

import java.awt.Color;
import java.awt.Font;

import es.upm.aled.lab1.measurements.Measurement;

/**
 * Plots an EEGModel in a 2D window, which one plot per channel. Measurements are updated at a configurable rate.
 * 
 * @author mmiguel
 *
 */
public class EEG_GUI {

	private Plot2D[] plots;
	final private int X, Y;
	private float[] x_actual;
	private float[] y_actual;
	private float[] maxY, minY;
	private int sRate;
	private float t = 0.0f;
	CompositionPlot2D container;
	private float period;

	/**
	 * Builds a GUI to plot the channels of an EEGModel. Each channel is shown in a
	 * different box, scaled to the max and min values of that channel over all
	 * measurements. The Measurements are shown at the rate specified by sRate. When
	 * the plot is filled, it is erased and the Measurements start again from the
	 * left side.
	 * 
	 * @param minY      Min value for each channel.
	 * @param maxY      Max value for each channel.
	 * @param nChannels Number of channels per Measurement. The length of minY, maxY and nChannels muys be the same.
	 * @param frequency Frequency at which the Measurements were sampled.
	 * @param sRate     Rate at which the Measurements are plotted (in ms).
	 */
	public EEG_GUI(float[] minY, float[] maxY, int nChannels, float frequency, int sRate) {
		this.Y = 600 / nChannels;
		this.X = 1250;
		plots = new Plot2D[nChannels];
		y_actual = new float[nChannels];
		x_actual = new float[nChannels];
		for (int i = 0; i < nChannels; i++)
			plots[i] = new Plot2D(X, Y);
		this.period = X / frequency;
		this.maxY = maxY;
		this.minY = minY;
		this.sRate = sRate;
		for (int i = 0; i < plots.length; i++) {
			plots[i].setFont(new Font(null, 0, 12));
			plots[i].setXscale(0, X);
			if (Math.abs(maxY[i] - minY[i]) == 0.0)
				if (maxY[i] != 0.0)
					plots[i].setYscale(minY[i], maxY[i] * 1.1);
				else
					;
			else if (maxY[i] != 0.0)
				plots[i].setYscale(minY[i], maxY[i]);
			initCanvas(i, 0);
			y_actual[i] = (minY[i] + maxY[i]) / 2.0F;
			x_actual[i] = 0.0F;
		}
		container = new CompositionPlot2D(plots);
		container.show(sRate);
	}

	/**
	 * Creates a Plot2D for each channel.
	 * 
	 * @return An array with a Plot2D for each channel.
	 */
	public Plot2D[] getGraficos() {
		return plots;
	}

	private void initCanvas(int i, float t) {
		plots[i].setPenColor(Color.RED);
		plots[i].line(0, (minY[i] + maxY[i]) / 2.0F, X, (minY[i] + maxY[i]) / 2.0F);
		plots[i].text(-10, (minY[i] + maxY[i]) / 2.0F, "[" + i + "]");
		float y = 0.0f;
		if (Math.abs(minY[i]) > 0.1f)
			y = (2 * minY[i] + maxY[i]) / 3.0F;
		else
			y = (minY[i] + maxY[i]) / 4.0F;
		plots[i].text(-10, y, "" + t);
		plots[i].setPenColor(Color.BLACK);
	}

	/**
	 * Draws all the channels of a Measurement, updating the channels' Plot2Ds.
	 * 
	 * @param m Measurement to be drawn.
	 */
	public void plotMeasurement(Measurement m) {
		boolean b = false;
		for (int i = 0; i < plots.length && i < m.numChannels(); i++) {
			float y = m.getChannel(i);
			if (y > maxY[i])
				y = maxY[i];
			if (y < minY[i])
				y = minY[i];

			plots[i].line(x_actual[i], y_actual[i], x_actual[i] = x_actual[i] + period, y_actual[i] = y);
			if (x_actual[i] > X) {
				x_actual[i] = 0.0f;
				plots[i].clear();
				if (!b)
					t = t + X / period;
				b = true;
				initCanvas(i, t);
			}
		}
		container.show(sRate);
	}
}
