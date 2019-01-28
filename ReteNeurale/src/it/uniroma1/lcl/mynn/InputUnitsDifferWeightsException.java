package it.uniroma1.lcl.mynn;

/**
 * Rappresenta un'eccezione lanciata quando in uno strato il numero delle
 * unit� di input (inputUnits) differisce dal numero dei pesi di un neurone
 * @author Timea
 *
 */
public class InputUnitsDifferWeightsException extends Exception {
	
	@Override
	public String toString() {
		return "In uno strato le unit� di input (inputUnits) differiscono dal numero"
				+ " dei pesi di un neurone!";
	}
}
