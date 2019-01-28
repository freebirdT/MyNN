package it.uniroma1.lcl.mynn;

/**
 * Rappresenta un'eccezione che viene lanciata quando l'input del metodo process punta a null
 * @author Timea
 *
 */
public class ProcessInputNullException extends Exception {

	@Override
	public String toString() {return "L'input del metodo process è null!";}
}
