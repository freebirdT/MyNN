package it.uniroma1.lcl.mynn;

/**
 * Rappresenta un'eccezione lanciata quando la cardinalità degli output della rete 
 * non coincide con la cardinalità degli output ideali 
 * ( |outputReale| != |outputIdeale| )
 * @author Timea
 *
 */
public class CardinalitaOutputException extends Exception {

	@Override
	public String toString() {
		return "La cardinalità dell'output della rete è diversa dalla cardinalità"
				+ "dell'array di output ideale";
	}
}
