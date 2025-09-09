package es.upm.aled.lab1.measurements;

/**
 * Filter that extracts the specified period from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractPeriod implements Filter {

	// SOLUCION
	private int min;
	private int max;
	// SOLUCION

	/**
	 * Builds the Filter from the [min, max] range defining the period that needs to
	 * be extracted. min and max are the indexes of the first and last measurements
	 * of the array obtained by calling the getMeasurements() method of EEGModel,
	 * and represent the starting and ending point of the period to be extracted.
	 * Both indexes are included and max-min must be less than the length of the
	 * Measurements array of the EGG Model.
	 * 
	 * @param min Start of the period to be extracted.
	 * @param max End of the period to be extracted.
	 */
	public FilterExtractPeriod(int min, int max) {
		// SOLUCION
		this.min = min;
		this.max = max;
		// SOLUCION
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		// SOLUCION
		Measurement[] measurements = eeg.getMeasurements();
		Measurement[] filteredMeasurements = new Measurement[max - min];
		int iFiltered = 0;
		// For each Measurement
		for (int i = 0; i < measurements.length; i++) {
			// If the index is between the min and max, the whole Measurement is added to
			// the filtered Measurements array
			if (i >= min && i < max) {
				filteredMeasurements[iFiltered] = measurements[i];
				iFiltered++;
			}
		}
		// Returns the filtered EEGModel
		EEGModel filteredModel = new EEGModel(filteredMeasurements);
		return filteredModel;
		// SOLUCION
	}
}
