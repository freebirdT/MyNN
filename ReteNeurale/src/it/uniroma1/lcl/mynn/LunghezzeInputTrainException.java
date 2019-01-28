package it.uniroma1.lcl.mynn;

/**
 * Eccezione lanciata quando le lunghezze degli input del metodo train 
 * sono diverse tra loro
 * @author Timea
 *
 */
public class LunghezzeInputTrainException extends Exception {

	@Override
	public String toString() {
		return "Le lunghezze degli input del metodo train sono diverse tra loro";
	}
}
