package it.uniroma1.lcl.mynn;

/**
 * Rappresenta la funzione di attivazione Step
 * @author Timea
 *
 */
public class Step extends FunzioneDiAttivazione {

	/**
	 * Applica la funzione di attivazione Step alla somma pesata di un neurone
	 */
	@Override
	public double formula(double sommaPesata) {return sommaPesata < 0 ? 0 : 1;}

	/**
	 * Restituisce la derivata del risultato della funzione di attivazione Step 
	 * applicata alla somma pesata di un neurone
	 */
	@Override
	public double deriva(double funzione) {return 0;}

}
