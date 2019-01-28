package it.uniroma1.lcl.mynn;

/**
 * Rappresenta la funzione di attivazione Hyperbolic Tangent (TanH)
 * @author Timea
 *
 */
public class TanH extends FunzioneDiAttivazione {

	/**
	 * Applica la funzione di attivazione TanH alla somma pesata di un neurone
	 */
	@Override
	public double formula(double sommaPesata) {return ( 2/(1 + Math.exp(-2 * sommaPesata)) ) -1; }

	/**
	 * Restituisce la derivata del risultato della funzione di attivazione TanH 
	 * applicata alla somma pesata di un neurone
	 */
	@Override
	public double deriva(double funzione) {return 1 - Math.pow(formula(funzione), 2);}

}

