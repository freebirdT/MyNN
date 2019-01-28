package it.uniroma1.lcl.mynn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Rappresenta uno strato qualunque delle rete neurale
 * @author Timea
 *
 */
public class Strato {
	
	/**
	 * Tipo di uno strato (di input o output)
	 */
	private String tipoDiStrato; 

	/**
	 * Funzione di attivazione dello strato
	 */
	private String funzioneDiAttivazione;
	
	/**
	 * Numero di unità di input dello strato
	 */
	private int unitaDiInput;
	
	/**
	 * Numero di unità di output dello strato
	 */
	private int unitaDiOutput;
	
	/**
	 * Lista di liste dei pesi dei neuroni 
	 * (ad ogni neurone viene associato una lista di pesi)
	 */
	private List<List<Double>> pesi;
	
	/**
	 * Lista dei neuroni presenti nello strato
	 */
	private List<Neurone> listaDiNeuroni;
	
	/**
	 * Costruisce uno strato 
	 * @param tipoDiStrato Tipo di strato (input o output)
	 * @param funzioneDiAttivazione Funzione di attivazione 
	 * @param unitaDiInput Numero di unità di input
	 * @param unitaDiOutput Numero di unità di output
	 * @param pesi Lista di pesi dei neuroni (è una lista di liste di valori double)
	 * @param listaDiNeuroni Lista dei neuroni
	 */
	public Strato(String tipoDiStrato, String funzioneDiAttivazione, int unitaDiInput, 
			int unitaDiOutput, List<List<Double>> pesi, List<Neurone> listaDiNeuroni) {
		this.tipoDiStrato = tipoDiStrato;
		this.funzioneDiAttivazione = funzioneDiAttivazione;
		this.unitaDiInput = unitaDiInput;
		this.unitaDiOutput = unitaDiOutput;
		this.pesi = pesi;
		this.listaDiNeuroni = listaDiNeuroni;
	}
	
	/**
	 * Restituisce la lista dei neuroni di uno strato
	 * @return Lista di neuroni dello strato
	 */
	public List<Neurone> getListaDiNeuroni() {return listaDiNeuroni;}
	
	/**
	 * Restituisce il numero di unità di input dello strato
	 * @return Numero di unità di input dello strato
	 */
	public int getUnitaDiInput() {return unitaDiInput;}
	
	/**
	 * Restituisce il numero di unità di output dello strato
	 * @return Numero di unità di output dello strato
	 */
	public int getUnitaDiOutput() {return unitaDiOutput;}
	
	/**
	 * Restituisce la funzione di attivazione dello strato 
	 * @return La funzione di attivazione dello strato
	 */
	public String getFunzioneDiAttivazione() {return funzioneDiAttivazione;}
	
	/**
	 * METODO AUSILIARIO/ NON RICHIESTO
	 * Restituisce una stringa che codifica lo strato
	 */
	@Override
	public String toString() {
		return funzioneDiAttivazione + " - " + unitaDiInput + " - " + unitaDiOutput + " - " + pesi;
	}
	
	/**
	 * Aggiorna un certo peso di un neurone 
	 * @param peso Peso aggiornato da assegnare al peso
	 * @param indiceNeurone Indice del neurone all'interno dello strato
	 * @param indicePeso Indice del peso all'interno della lista pesi di un neurone
	 */
	public void aggiornaPesoSingolo(double peso, int indiceNeurone, int indicePeso) {
		pesi.get(indiceNeurone).set(indicePeso, peso);
	}

	/**
	 * Estrae dagli strati della rete le seguenti informazioni:
	 * funzione di attivazione, numero di unità di input, numero di unità di output 
	 * e pesi (se ci sono)
	 * @param reteCodificata Rete neurale codificata come stringa
	 * @return Una rete neurale 
	 */
	public static MyNN extractLayerInfo(String reteCodificata) {
		
		// Spezza la rete codificata (come stringa) in un array di stringhe
		String[] rete = reteCodificata.split("[\\r\\n]+");
		
		// Nome della rete neurale presa in input
		String nomeDellaRete = rete[0].substring(5);
		
		// Lista degli strati totali della rete neurale
		List<Strato> listaDiStratiTotali = new ArrayList<>();
		String[] stratiDellaRete = Arrays.copyOfRange(rete, 1, rete.length);
		Arrays.stream(stratiDellaRete).forEach
		(stratoCodificato -> listaDiStratiTotali.add(extractSingleLayerInfo(stratoCodificato)));
		
		// Contiene il tipo della rete presa in input (Percettrone, Singolo strato o Multistrato)
		String tipoDellaRete = "";
		
		// Se si tratta di una rete che ha un solo strato
		if (listaDiStratiTotali.size() == 1) {
			
			// Se la sua funzione di attivazione è Step allora si tratta di una rete percettrone
			if (listaDiStratiTotali.get(0).getFunzioneDiAttivazione().equals("Step")) 
				tipoDellaRete = "Percettrone";
			
			// Altrimenti si tratta di una rete a singolo strato
			else tipoDellaRete = "Singolo strato";
		}
		
		// Se la rete ha più di uno strato allora si tratta di una rete multistrato
		else tipoDellaRete = "Multistrato";
		
		// Controllo del numero di output-input tra gli strati
		for (int i = 1; i < listaDiStratiTotali.size(); i++) {
			
			try {
				if (listaDiStratiTotali.get(i-1).getUnitaDiOutput() != 
						listaDiStratiTotali.get(i).getUnitaDiInput()) 
					throw new OutputInputDiversiException();
			}
			catch (OutputInputDiversiException e) {
				e.printStackTrace();
				return null;
			}
		}	
		
		// Ritorna una rete neurale
		return new MyNN(nomeDellaRete, reteCodificata, listaDiStratiTotali, tipoDellaRete);	
	} 
	
	/**
	 * Ritorna il tipo di uno strato (input o output)
	 * @return Tipo di uno strato
	 */
	public String getTipoDiStrato() {return tipoDiStrato;}
	
	/**
	 * Estrae da uno strato della rete le seguenti informazioni:
	 * funzione di attivazione, numero di input, numero di output e pesi (se ci sono)
	 * @param singoloLayer Uno strato della rete
	 */
	private static Strato extractSingleLayerInfo(String singoloLayer) { 
		
		// Spezza la stringa che codifica uno strato 
		//usando come delimitatore uno o più spazi liberi
		String[] arr = singoloLayer.split("\\s+");
		
		// Strato di input/output
		String tipoDiStrato = arr[1].substring(5);
		
		// Funzione di attivazione
		String funzioneDiAttivazione = arr[2].substring(19);
		
		// Numero di unità di input
		int unitaDiInput = Integer.parseInt(arr[3].substring(11));
		
		// Numero di unità di output
		int unitaDiOutput = Integer.parseInt(arr[4].substring(12));
		
		// Contiene i pesi dei neuroni di uno strato
		List<List<Double>> pesiDiNeuroni = new ArrayList<>();
		
		// Se ci sono i pesi
		if (arr.length == 7) {
			String[] pesi = arr[5].substring(10, arr[5].length()-2).split("\\],\\[");
			for (String sss : pesi) pesiDiNeuroni.add(Arrays.stream
					(sss.split(",")).map(Double::valueOf).collect(Collectors.toList()));
		}
		
		// Se i pesi non sono stati definiti vengono generati a caso tra 0 e 1
		else {
			for (int i = 0; i < unitaDiOutput; i++) 
				pesiDiNeuroni.add(new Random().doubles().boxed().limit(unitaDiInput+1).
						collect(Collectors.toList()));
		}
		
		// Istanzia i neuroni dello strato
		List<Neurone> listaDiNeuroni = new ArrayList<>();
		pesiDiNeuroni.stream().forEach(s -> listaDiNeuroni.add(new Neurone(s)));
		
		// Ritorna uno strato
		return new Strato(tipoDiStrato, funzioneDiAttivazione, unitaDiInput, unitaDiOutput, pesiDiNeuroni, listaDiNeuroni);
	}
}

