package it.uniroma1.lcl.mynn;

/**
 * Rapresenta la funzione di attivazione Identity
 * @author Timea
 */
public class Identity extends FunzioneDiAttivazione {
	
	/**
	 * Applica la funzione di attivazione Identity alla somma pesata di un neurone
	 */
	@Override
	public double formula(double sommaPesata) {return sommaPesata;}

	/**
	 * Calcola la derivata della funzione di attivazione Identity
	 */
	@Override
	public double deriva(double funzione) {return 1;}
}

