package it.uniroma1.lcl.mynn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Contiene tutti i metodi che una rete neurale possiede
 * @author Timea
 *
 */
public interface IReteNeurale {

	/**
	 * Riceve una lista di valori in input e restituisce l’output calcolato su di essi
	 * @param values Lista di valori
	 * @return Output della rete neurale per quella lista di valori
	 */
	public double[] process(double[] values);
	
	/**
	 * Riceve in input due array di double: il primo array è l'input che riceve la rete 
	 * e il secondo array rappresenta l'output atteso che la rete deve restituire.
	 * Il metodo modifica i pesi in accordo con la​ formula di aggiornamento ​e 
	 * restituisce la  somma degli errori ottenuti dal confronto tra l’output 
	 * effettivo della rete e quello ideale passato in input
	 * @param values Lista di valori che la rete riceve in input
	 * @param output Output ideale che la rete neurale dovrebbe restituire
	 * @return La somma degli errori
	 */
	public double trainIstanza(double[] values, double output[]);
	
	/**
	 * Riceve in input un insieme di addestramento e fa addestramento in accordo 
	 * con l'algoritmo d’addestramento
	 * @param inputs Matrice di valori di input da dare alla rete neurale
	 * @param outputs Matrice di valori di output ideali che la rete dovrebbe restituire
	 */
	public void train(double[][] inputs, double[][] outputs);
	
	/**
	 * Ritorna il nome della rete neurale
	 * @return Nome della rete neurale
	 */
	public String getNome(); 
	
	/**
	 * Restituisce una rete neurale artificiale da una rete codificata sotto stringa
	 * @param filename Nome del file contenente la rete neurale codificata
	 * @return Una rete neurale
	 */
	public static IReteNeurale carica(String filename) {
		
		StringBuilder testo = new StringBuilder();
		String testoStringa = "";
		
		BufferedReader br = null;
		
		try {
			
			br = new BufferedReader(new FileReader(filename));
			
			br.lines().forEach(line -> testo.append(line + '\n'));
			
			testoStringa = testo.toString();
			testoStringa = testoStringa.substring(0, testoStringa.length()-1);
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			if (br != null) {
				try {br.close();} 
				catch (IOException e1) {
					System.out.print("Il file è null!");
					e1.printStackTrace();
					return null;
				}
			}
		}
			
		return Strato.extractLayerInfo(testoStringa);
	}	
}

