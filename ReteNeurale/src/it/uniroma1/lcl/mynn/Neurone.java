package it.uniroma1.lcl.mynn;

import java.util.List;

/**
 * Rappresenta un neurone qualunque della rete neurale
 * @author Timea
 *
 */
public class Neurone {
	
	/**
	 * Pesi di un neurone
	 */
	private List<Double> pesi;

	/**
	 * Costruisce un neurone a partire da una lista di pesi
	 * @param pesi Lista di pesi dei neuroni
	 */
	public Neurone(List<Double> pesi) {this.pesi = pesi;}
	
	/**
	 * Restituisce la lista dei pesi di un neurone (incluso il threshold)
	 * @return la lista dei pesi di un neurone  
	 */
	public List<Double> getPesi() {return pesi;}
	
	/**
	 * Restituisce il threshold del neurone
	 * @return Threshold associato al neurone
	 */
	public double getThreshold() {return pesi.get(pesi.size()-1);}
	
	/**
	 * Restituisce i pesi del neurone
	 */
	@Override
	public String toString() {return pesi.toString();}
	
}

