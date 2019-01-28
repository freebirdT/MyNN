package it.uniroma1.lcl.mynn;

/**
 * Rappresenta un'eccezione che viene lanciata quando il numero di unità di output 
 * di uno strato è diverso dal numero di unità di input dello strato successivo
 * @author Timea
 *
 */
public class OutputInputDiversiException extends Exception {

	@Override
	public String toString() {
		return "Il numero di unità di output di uno strato è diverso dal "
				+ "numero di unità di input dello strato successivo";
	}
}
