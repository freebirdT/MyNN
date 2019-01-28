package it.uniroma1.lcl.mynn;

/**
 * Rappresenta un'eccezione che viene lanciata quando il numero di unit� di output 
 * di uno strato � diverso dal numero di unit� di input dello strato successivo
 * @author Timea
 *
 */
public class OutputInputDiversiException extends Exception {

	@Override
	public String toString() {
		return "Il numero di unit� di output di uno strato � diverso dal "
				+ "numero di unit� di input dello strato successivo";
	}
}
