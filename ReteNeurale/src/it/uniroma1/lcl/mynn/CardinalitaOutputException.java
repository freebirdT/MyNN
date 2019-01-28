package it.uniroma1.lcl.mynn;

/**
 * Rappresenta un'eccezione lanciata quando la cardinalit� degli output della rete 
 * non coincide con la cardinalit� degli output ideali 
 * ( |outputReale| != |outputIdeale| )
 * @author Timea
 *
 */
public class CardinalitaOutputException extends Exception {

	@Override
	public String toString() {
		return "La cardinalit� dell'output della rete � diversa dalla cardinalit�"
				+ "dell'array di output ideale";
	}
}
