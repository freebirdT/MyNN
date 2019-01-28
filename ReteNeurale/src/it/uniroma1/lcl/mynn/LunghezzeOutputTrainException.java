package it.uniroma1.lcl.mynn;

/**
 * Eccezione lanciata quando le lunghezze degli output del metodo train 
 * sono diverse tra loro
 * @author Timea
 *
 */
public class LunghezzeOutputTrainException extends Exception {

	@Override
	public String toString() {
		return "Le lunghezze degli output del metodo train sono diverse tra loro";
	}
}
