package es.upm.aled.lab1.measurements;

/**
 * Class representing one measurement. Each measurement is made up of an
 * arbitrary number of channels, each containing a float value representing a
 * sample.
 * 
 * @author mmiguel, rgarciacarmona
 */
public class Measurement {

	private float[] channels;

	/**
	 * Builds a new measurement from an array of floats containing the values
	 * measured by each channel.
	 * 
	 * @param channels The values measured by each channel.
	 */
	public Measurement(float[] channels) {
		this.channels = channels;
	}

	/**
	 * Returns the value taken by the specified channel.
	 * 
	 * @param numChannel The channel number, starting from 0.
	 * @return The value measured.
	 */
	public float getChannel(int numChannel) {
		return channels[numChannel];
	}

	/**
	 * Returns how many channels the measurement has.
	 * 
	 * @return The number of channels.
	 */
	public int numChannels() {
		return channels.length;
	}
}
