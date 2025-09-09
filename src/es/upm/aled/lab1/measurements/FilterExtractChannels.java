package es.upm.aled.lab1.measurements;

/**
 * Filter that extracts the specified channels from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractChannels implements Filter {

	// SOLUCION
	private int[] validChannels;
	// SOLUCION

	/**
	 * Builds the Filter. The use from an array of valid channels.
	 * 
	 * @param validChannels The channel numbers to be extracted, starting from 0.
	 */
	public FilterExtractChannels(int[] validChannels) {
		// SOLUCION
		this.validChannels = validChannels;
		// SOLUCION
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		// SOLUCION
		Measurement[] measurements = eeg.getMeasurements();
		Measurement[] filteredMeasurements = new Measurement[measurements.length];
		// For each measurement
		for (int i = 0; i < measurements.length; i++) {
			float filteredChannels[] = new float[validChannels.length];
			int iFilteredChannels = 0;
			Measurement measurement = measurements[i];
			// For each channel of each measurement
			for (int iChannels = 0; iChannels < measurement.numChannels(); iChannels++) {
				boolean chosen = false;
				// If the channel was chosen, it's marked as such
				for (int j = 0; j < validChannels.length; j++) {
					if (iChannels == validChannels[j]) {
						chosen = true;
						break;
					}
				}
				// If the channel was chosen, it's added to the filtered channels array
				if (chosen) {
					filteredChannels[iFilteredChannels] = measurement.getChannel(iChannels);
					iFilteredChannels++;
				}
			}
			// Adds the filtered Measurement
			filteredMeasurements[i] = new Measurement(filteredChannels);
		}
		// Returns the filtered EEGModel
		EEGModel filteredModel = new EEGModel(filteredMeasurements);
		return filteredModel;
		// SOLUCION
	}

}
