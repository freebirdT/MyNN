package it.uniroma1.lcl.mynn;

/**
 * Rappresenta un'eccezione lanciata quando il numero degli elementi all'interno 
 * dei parametri di input del metodo train non sono uguali
 * @author Timea
 *
 */
public class IODiTrainException extends Exception {

	@Override
	public String toString() {
		return "Le cardinalità delle matrici inputs e outputs del metodo train non sono uguali.";
	}
}
