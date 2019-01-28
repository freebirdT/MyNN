package it.uniroma1.lcl.mynn;

/**
 * Rappresenta la funzione di attivazione Rectified Linear Unit (ReLU)
 * @author Timea
 *
 */
public class ReLU extends FunzioneDiAttivazione {

	/**
	 * Applica la funzione di attivazione ReLU alla somma pesata di un neurone
	 */
	@Override
	public double formula(double sommaPesata) {return sommaPesata < 0 ? 0 : sommaPesata;}

	/**
	 * Restituisce la derivata del risultato della funzione di attivazione ReLU 
	 * applicata alla somma pesata di un neurone
	 */
	@Override
	public double deriva(double funzione) {return funzione < 0 ? 0 : 1;}

	
}
