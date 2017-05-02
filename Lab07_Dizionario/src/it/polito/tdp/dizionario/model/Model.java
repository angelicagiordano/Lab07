package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;


import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class); //tra parentesi specifico la classe del tipo di arco

	List<String> tuttiVicini= new ArrayList<String>();
	
	

	public List<String> createGraph(int numeroLettere) {

		WordDAO wdao= new WordDAO();
		
		
		for(String s: wdao.getAllWordsFixedLength(numeroLettere)){
			grafo.addVertex(s);
			
			
		}
		
//		for(String s: grafo.vertexSet()){
//		for(String s1: wdao.getAllSimilarWords(s,numeroLettere)){
//			if(s.compareTo(s1)!=0){
//		    grafo.addEdge(s, s1);
//		    }
//	}

		
		
		   
		for(String s: grafo.vertexSet()){
			for(String s1: grafo.vertexSet()){
				if(isNeighbours(s, s1)==true){
					grafo.addEdge(s,s1);
				}
			}
		}
		
		
		//System.out.println(grafo);
		return wdao.getAllWordsFixedLength(numeroLettere);
	}
	
	

	private boolean isNeighbours(String s, String s1) {
	
		int conta=0;
		for(int i=0; i<s.length(); i++){
			if(s.charAt(i)!=s1.charAt(i)){
				conta++;
			}
		}
		
		if(conta==1){
			return true;
		}else
		return false;
	}



	public List<String> displayNeighbours(String parolaInserita) {

		
		
		//System.out.println("Model -- TODO" );
		return Graphs.neighborListOf(grafo, parolaInserita);
	}

	public String findMaxDegree() {
		//System.out.println("Model -- TODO");
		int gradoMax=0;
		for(String s: grafo.vertexSet()){
			if(grafo.degreeOf(s)>gradoMax){
				gradoMax= grafo.degreeOf(s);
			}
		}
		return ""+gradoMax;
	}
	public List <String> displayAllNeighbours(String parolaInserita){
		//METODO 1
//		List<String> daVisitare = new LinkedList<String>();
//		List<String> visitati = new ArrayList<String>();
//		
//		daVisitare.add(parolaInserita);
//		
//		while(daVisitare.size()>0){
//			String s1= daVisitare.get(0);
//			for(String s: this.displayNeighbours(s1)){
//				if(!visitati.contains(s)){
//					visitati.add(s);
//					
//					daVisitare.addAll(this.displayNeighbours(s));
//				}
//			}
//			daVisitare.remove(s1);
//			
//			}
//		
//		
//		return visitati;
		
		
		
		//METODO 2 CON RICORSIONE
		
		recursive( parolaInserita);
		tuttiVicini.remove(parolaInserita);
		return tuttiVicini;
		
		
		
	} 
	
	private void recursive(String parola){
		
		for(String s: this.displayNeighbours(parola)){
			if(!tuttiVicini.contains(s)){
				tuttiVicini.add(s);
				
				recursive( s);
			}
		}
		
	}
}
