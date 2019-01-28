package it.uniroma1.lcl.mynn;

/**
 * Rappresenta la funzione di attivazione
 * @author Timea
 *
 */
public abstract class FunzioneDiAttivazione {

	/**
	 * Applica la funzione di attivazione alla somma pesata di un neurone
	 * @param sommaPesata Somma pesata di un neurone
	 * @return Il risultato della funzione di attivazione applicata alla somma pesata
	 */
	public abstract double formula(double sommaPesata);
	
	/**
	 * Calcola la derivata del risultato della funzione di attivazione applicata alla somma pesata
	 * @param funzione Funzione di attivazione
	 * @return La derivata
	 */
	public abstract double deriva(double funzione);
		
}
