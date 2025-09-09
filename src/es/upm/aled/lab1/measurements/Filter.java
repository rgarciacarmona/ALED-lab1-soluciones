package es.upm.aled.lab1.measurements;

/**
 * Interface used to define a filter that can be applied over an EEG model.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public interface Filter {

	/**
	 * Applies the filter over a given EEG model and returns a new one.
	 * 
	 * @param eeg Model to be filtered.
	 * @return Filtered model.
	 */
	EEGModel applyFilter(EEGModel eeg);
}
