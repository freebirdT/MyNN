package it.uniroma1.lcl.mynn;

/**
 * Rappresenta un'eccezione lanciata quando almeno uno dei parametri di input 
 * del metodo train è null
 * @author Timea
 *
 */
public class InputOutputNullException extends Exception {

	@Override
	public String toString() {
		return "Almeno uno dei parametri di input del metodo train è null";
	}
}
