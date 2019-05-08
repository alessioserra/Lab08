package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	List<String> listaParole;
	WordDAO dao = new WordDAO();
	
	//Dichiaro grafo
	private Graph<String,DefaultEdge> grafo;
	
	public void createGraph(int numeroLettere) {

		//Ottengo la lista delle parole con la lunghezza indicata
		listaParole =dao.getAllWordsFixedLength(numeroLettere);
		
		//Creo grafo semplice
		grafo = new SimpleGraph<>(DefaultEdge.class);
		
		//Aggiungo i NODI al grafo, senza ARCHI
		Graphs.addAllVertices(grafo, listaParole);
		
		//Contatore
		int cont;
		
		for (String s : listaParole) {
		for (String r: listaParole) {
			
			//Confronto le parole solo se l'arco non esiste gia'
			if (!grafo.containsEdge(s, r)) {
			
			//Ogni volta inizializzo contatore
			cont=0;
			
			//Creo 2 array "splittati" carattere per carattere
			char[] array1=s.toCharArray();
			char[] array2=r.toCharArray();
			
			//Confronto carattere per carattere
			for(int i=0;i<numeroLettere;i++) {
				if ( array1[i]!=array2[i] ) cont++;
				
				//Se trovo già 2 lettere diverse, esco dal ciclo
				if (cont==2) break;
			}
			
			//Se differiscono solo per un carattere, creo ARCO che li collega
			if (cont == 1) grafo.addEdge(s, r);
			}
		}			
		}
		
		System.out.println("Grafo creato!");
	}

	public List<String> displayNeighbours(String parolaInserita) {

		System.err.println("displayNeighbours -- TODO");
		return new ArrayList<String>();
	}

	public int findMaxDegree() {
		System.err.println("findMaxDegree -- TODO");
		return -1;
	}
}
