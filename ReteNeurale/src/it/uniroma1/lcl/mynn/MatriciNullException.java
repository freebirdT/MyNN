package it.uniroma1.lcl.mynn;

/**
 * Eccezione lanciata quando gli array all'interno delle matrici prese in input nel metodo train"
				+ " non sono tutti diversi da null
 * @author Timea
 *
 */
public class MatriciNullException extends Exception {

	@Override
	public String toString() {
		return "Gli array all'interno delle matrici prese in input nel metodo train"
				+ " non sono tutti diversi da null.";
	}
}
