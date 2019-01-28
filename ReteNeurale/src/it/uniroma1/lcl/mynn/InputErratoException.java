package it.uniroma1.lcl.mynn;

/**
 * Rappresenta un'eccezione lanciata quando il numero di valori double preso in input
 * è errato e non coincide con il numero di pesi di un neurone di uno strato
 * @author Timea
 *
 */
public class InputErratoException extends Exception {

	@Override
	public String toString() {
		return "Il numero di valori double preso in input è errato"
				+ " e non coincide con il numero di pesi di un neurone di uno strato!";
	}
}
