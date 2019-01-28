package it.uniroma1.lcl.mynn;

/**
 * Rappresenta la funzione di attivazione Logistic
 * @author Timea
 *
 */
public class Logistic extends FunzioneDiAttivazione {

	/**
	 * Applica la funzione di attivazione Logistic alla somma pesata di un neurone
	 */
	@Override
	public double formula(double sommaPesata) {return 1/(1+Math.exp(-sommaPesata));}

	/**
	 * Calcola la derivata
	 */
	@Override
	public double deriva(double funzione) {return formula(funzione) * (1 - formula(funzione));}

}
