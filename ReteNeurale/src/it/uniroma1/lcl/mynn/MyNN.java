package it.uniroma1.lcl.mynn; 

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Rapresenta una rete neurale artificiale
 * @author Timea
 *
 */
public class MyNN implements IReteNeurale {
	
	/**
	 * Nome della rete neurale
	 */
	private String nomeDellaRete;
	
	/**
	 * Tipo della rete neurale (percettrone o singolo strato)
	 */
	private String tipoDellaRete;
	
	/**
	 * Numero di strati della rete neurale
	 */
	private int numeroDiStrati;
	
	/**
	 * Rete neurale codificata come stringa
	 */
	private String reteCodificata;
	
	/**
	 * Rete neurale aggiornata e codificata come stringa 
	 */
	private StringBuffer reteAggiornata = new StringBuffer();
	
	/**
	 * Insieme degli strati della rete neurale
	 */
	private List<Strato> listaDiStratiTotali;
	
	/**
	 * Costante di apprendimento usata nella formula di aggiornamento dei pesi
	 * (assume valori tra 0.01 e 0.001)
	 */
	private final double COSTANTE_DI_APPRENDIMENTO = 0.001 + (0.01 - 0.001) * new Random().nextDouble(); 
	
	/**
	 * Costruisce una rete neurale
	 * @param nomeDellaRete Nome della rete neurale
	 * @param reteCodificata Rete neurale codificata come stringa
	 * @param listaDiStratiTotali Lista degli strati della rete neurale
	 * @param tipoDelleRete Tipo della rete neurale (percettrone o a singolo strato)
	 */
	public MyNN(String nomeDellaRete, String reteCodificata, List<Strato> listaDiStratiTotali, String tipoDelleRete) {
		this.reteCodificata = reteCodificata;
		this.listaDiStratiTotali = listaDiStratiTotali;
		this.tipoDellaRete = tipoDelleRete;
		this.nomeDellaRete = nomeDellaRete;
	}

	/**
	 * Riceve una lista di valori in input e restituisce l’output calcolato su di essi  
	 */
	@Override
	public double[] process(double[] values) {
		
		// Controlla se l'input del metodo sia null
		try {
			if (values == null) throw new ProcessInputNullException();
		}
		catch (ProcessInputNullException e) {
			e.printStackTrace();
			return null;
		}
		
		// Converte double[] values in una lista di Double 
		List<Double> valuesAux = Arrays.stream(values).boxed().collect(Collectors.toList());
		 
		// Per ogni strato della rete
		for (Strato strato : listaDiStratiTotali) {
			
			// Funzione di attivazione di uno strato salvata come stringa
		    String funzioneDiAttivazione = strato.getFunzioneDiAttivazione();
		    
		    // Lista di output generati dai neuroni di uno strato
		    List<Double> listaDiOutputDiNeuroni = new ArrayList<>();
			
		    // Funzione di attivazione di uno strato
		    FunzioneDiAttivazione funDiAtt = generaFuncDiAttivazione(funzioneDiAttivazione);
		    	
		    // Per ogni neurone di uno strato
		    for (int j = 0; j < strato.getListaDiNeuroni().size(); j++) {
		    	
		    	// Un neurone di uno strato
		    	Neurone neurone = strato.getListaDiNeuroni().get(j);
		    	
		    	try {
		    		
		    		// Vengono individuati due possibili controlli
		    		
		    		// Controlla se il numero di unità di input di uno strato è diverso dal numero
		    		// di pesi di un neurone dello stesso strato
		    		if (strato.getUnitaDiInput() != neurone.getPesi().size()-1)
		    			throw new InputUnitsDifferWeightsException();
		    		
					// Controlla se il numero di pesi di un neurone sia diverso 
		    		// dal numero di valori presi in input
		    		if (valuesAux.size() != neurone.getPesi().size()-1) 
						throw new InputErratoException();
				}
		    	catch (InputUnitsDifferWeightsException e) {
		    		e.printStackTrace();
		    		return null;
		    	}
				catch (InputErratoException e) {
					e.printStackTrace();
					return null;
				}
		    		
		    // Se si superano i controlli si procede con il calcolo della somma pesata altrimenti il programma termina
		    	
		    // Calcolo parziale della somma pesata (non contiene il valore del thresold) 
		    	
		    	// Converte la lista di Double in un array di double
		    	double[] inputDelloStrato = valuesAux.stream().mapToDouble(i -> i).toArray();
		    	// Somma pesata parziale di un neurone
		    	double sommaPesata = calcolaSommaPesata(j, inputDelloStrato, strato); 
		    
		    	// Passa alla funzione di attivazione la somma pesata di un neurone
		    	listaDiOutputDiNeuroni.add(funDiAtt.formula(sommaPesata));
		    }
		    
		    // Setta la nuova lista di valori calcolati sui neuroni di uno strato
		    // Questa lista di valori double sarà il prossimo input per lo strato successivo
	    	valuesAux = listaDiOutputDiNeuroni;
		}
		
		// Ritorna l'output (array di double)
		return valuesAux.stream().mapToDouble(i->i).toArray();
    }			

	/**
	 * Riceve in input due array di double: il primo array è l'input che riceve la rete 
	 * e il secondo array rappresenta l'output atteso che la rete deve restituire.
	 * Il metodo modifica i pesi in accordo con la​ formula di aggiornamento ​e 
	 * restituisce la  somma degli errori ottenuti dal confronto tra l’output 
	 * effettivo della rete e quello ideale passato in input
	 */
	@Override
	public double trainIstanza(double[] values, double[] output) {
		
		// Controlla se il numero di valori dati in input sia diverso dal numero di
		// unità di input del primo strato
		try {
			if (values.length != listaDiStratiTotali.get(0).getUnitaDiInput()) 
			throw new InputErratoException();
		}
		catch (InputErratoException e) {
			e.printStackTrace();
			return -1.0;
		}
	
		// Somma degli errori
		double errore = 0.0;
		
		// Output del metodo process
		double[] outputDiProcess = process(values);
		
		// Controlla se il numero dei valori di output di process sia diverso dal numero
		// di valori di output ideali
		try {
			if (outputDiProcess.length != output.length) throw new CardinalitaOutputException();
		}
		catch(CardinalitaOutputException e) {
			e.printStackTrace();
			return -1.0;
		}
		
		// Calcolo dell'errore tra output atteso e output del metodo process
		for (int i = 0; i < output.length; i++) errore += output[i] - outputDiProcess[i];
		
		// Aggiornamento dei pesi di una rete
	    aggiornaPesi(values, output, outputDiProcess);
	    
		// Ritorna l'errore calcolato
		return Math.abs(errore);
	}
	
	/**
	 * Aggiorna i pesi di tutti i neuroni dello strato
	 * @param values Input iniziale della rete
	 * @param output Output ideale della rete
	 * @param outputDiProcess Output del metodo process della rete
	 */
	private void aggiornaPesi(double[] values, double[] output, double[] outputDiProcess) {
		
		// Ottiene la funzione di attivazione del primo strato
		Strato primoStrato = listaDiStratiTotali.get(0);
		FunzioneDiAttivazione funDiAtt = generaFuncDiAttivazione(primoStrato.getFunzioneDiAttivazione());
		
		// Itera sul numero di neuroni dello strato
		for (int j = 0; j < primoStrato.getUnitaDiOutput(); j++) {
			
			// Derivata della funzione di attivazione applicata alla somma pesata
			double derivata;
			 
			// Calcolo della derivata in funzione del tipo di rete
			if (tipoDellaRete.equals("Percettrone")) derivata = 1.0;
			else derivata = funDiAtt.deriva(calcolaSommaPesata(j, values, primoStrato));
			
			// Itera sul numero di pesi di un neurone di quello strato
			for (int i = 0; i < primoStrato.getUnitaDiInput(); i++) {
				
				// Peso non aggiornato di un neurone
				double peso = primoStrato.getListaDiNeuroni().get(j).getPesi().get(i);
				
				// Aggiorna un peso alla volta
				peso += COSTANTE_DI_APPRENDIMENTO * (output[j] - outputDiProcess[j]) * values[i] * derivata;
				
				// Salva l'i-esimo peso del j-esimo neurone di uno strato
				primoStrato.aggiornaPesoSingolo(peso, j, i);
			}
			
			// Ora aggiorno il threshold
			int thresholdIndex = primoStrato.getUnitaDiInput();
			double valoreThreshold = primoStrato.getListaDiNeuroni().get(j).getPesi().get(thresholdIndex);
			
			valoreThreshold += COSTANTE_DI_APPRENDIMENTO * (output[j] - outputDiProcess[j]) * derivata;
			primoStrato.aggiornaPesoSingolo(valoreThreshold, j, thresholdIndex);
		}
	}
	
	/**
	 * Genera la funzione di attivazione specifica di uno strato (TanH, Logistic ecc.)
	 * @param funzioneDiAttivazione La funzione di attivazione di uno strato codificata sotto stringa
	 * @return Una funzione di attivazione
	 */
	private FunzioneDiAttivazione generaFuncDiAttivazione(String funzioneDiAttivazione) {
		FunzioneDiAttivazione funDiAtt = null;
		
		try {
	 	    Class<?> c = Class.forName("it.uniroma1.lcl.mynn." + funzioneDiAttivazione); //per es. tanH
	 	    Class<? extends FunzioneDiAttivazione> fa = c.asSubclass(FunzioneDiAttivazione.class);
	 	    Constructor<? extends FunzioneDiAttivazione> constr = fa.getConstructor();
	 	    funDiAtt = constr.newInstance();
	 	}
	 	catch (ClassNotFoundException e) {
	 		e.printStackTrace();
	 		return null;
	 	}
	 	catch (NoSuchMethodException e) {
	 		e.printStackTrace();
	 		return null;
	 	}
	 	catch (Exception e) {
	 		e.printStackTrace();
	 		return null;
	 	} 
		
		return funDiAtt;
	}
	
	/**
	 * Calcola la somma pesata di un neurone 
	 * @param indiceNeurone Posizione del neurone nello strato
	 * @param values Input iniziale
	 * @param strato Strato di riferimento del neurone
	 * @return Somma pesata di un neurone
	 */
	private double calcolaSommaPesata(int indiceNeurone, double[] values, Strato strato) {
		
		// Somma pesata del neurone
		double sommaPesata = 0.0;
		
		// Lista dei neuroni di uno strato
		List<Neurone> neuroniDelloStrato = strato.getListaDiNeuroni(); 
		
		// Calcolo parziale della somma pesata di un neurone (senza threshold) 
		for (int i = 0; i < strato.getUnitaDiInput(); i++) 
			sommaPesata += values[i] * neuroniDelloStrato.get(indiceNeurone).getPesi().get(i);
		
		// Individua la posizione del threshold
		int indiceThreshold = strato.getUnitaDiInput();
		
		// Calcolo finale della somma pesata di un neurone (con threshold)
		sommaPesata += neuroniDelloStrato.get(indiceNeurone).getPesi().get(indiceThreshold);
		
		// Restituisce la somma pesata di un neurone
		return sommaPesata;
	}

	/**
	 * Riceve in input un insieme di addestramento e fa addestramento in accordo con l'algoritmo d’addestramento
	 */
	@Override
	public void train(double[][] inputs, double[][] outputs) {
		
		try {
			
			// Controlla che le matrici prese in input non siano null
			if (inputs == null || outputs == null) throw new InputOutputNullException();
			
			// Controlla se il numero degli elementi della matrice inputs sia diverso
			// dal numero degli elementi della matrice outputs
			if (inputs.length != outputs.length) throw new IODiTrainException();
			
			for (int i = 1; i < inputs.length; i++) {
				
				// Controlla la presenza di qualche array che punta a null all'interno delle matrici prese in input
				if (inputs[i-1] == null || inputs[i] == null || outputs[i-1] == null || outputs[i] == null) 
					throw new MatriciNullException();
				
				// Controlla se le lunghezze tra gli array contenuti nelle matrici siano diverse
				if (inputs[i].length != inputs[i-1].length) throw new LunghezzeInputTrainException();
				if (outputs[i].length != outputs[i-1].length) throw new LunghezzeOutputTrainException();
			}
		}
		catch (InputOutputNullException e) {
			e.printStackTrace();
			return;
		}
		catch (IODiTrainException e) {
			e.printStackTrace();
			return;
		}
		catch (MatriciNullException e) {
			e.printStackTrace();
			return;
		}
		catch (LunghezzeInputTrainException e) {
			e.printStackTrace();
			return;
		}
		catch (LunghezzeOutputTrainException e) {
			e.printStackTrace();
			return;
		}
		
		// Somma degli errori restituita da un'iterazione del metodo trainIstanza
		double sommaErrori;
		do {
			sommaErrori = 0.0;
			
			// Per ogni coppia (x,y) nell'insieme di addestramento
			for (int i = 0; i < inputs.length; i++) {
				double errori = trainIstanza(inputs[i], outputs[i]);
				sommaErrori += errori;
			}
		}
		while (sommaErrori > 0.01 );
	}

	/**
	 * Ritorna il nome della rete neurale
	 */
	@Override
	public String getNome() {return nomeDellaRete;}
	
	/**
	 * Ritorna la rete neurale codificata come stringa
	 */
	@Override
	public String toString() {
		reteAggiornata.append("nome=");
		reteAggiornata.append(nomeDellaRete);
		reteAggiornata.append('\n');
		
	    for (int m = 0; m < listaDiStratiTotali.size(); m++) {
	    	
	    	String pesiStringa = "";
	    	
	    	for (int n = 0; n < listaDiStratiTotali.get(m).getUnitaDiOutput(); n++) 
	    		pesiStringa += listaDiStratiTotali.get(m).getListaDiNeuroni().get(n).getPesi() + ",";
	    	
	    	Strato stratoAttuale = listaDiStratiTotali.get(m);
	    	
	    	reteAggiornata.append("layer={ nome=" + listaDiStratiTotali.get(m).getTipoDiStrato() 
	    			+ " activationFunction=" + stratoAttuale.getFunzioneDiAttivazione()
	    			+ " inputUnits=" + stratoAttuale.getUnitaDiInput() 
	    			+ " outputUnits=" + stratoAttuale.getUnitaDiOutput()
	    			+ " weights=["); 
	    	reteAggiornata.append(pesiStringa.substring(0, pesiStringa.length()-1).replace(" ", "").replace(".0", "") + "] }");
	    }
	    
	    String reteAggiornataSalvata = reteAggiornata.toString();
	    
	    reteAggiornata = new StringBuffer();
	    
	    return reteAggiornataSalvata;
	}
}

