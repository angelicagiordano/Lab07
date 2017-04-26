package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;


import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class); //tra parentesi specifico la classe del tipo di arco


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
//		}
		
		
		
		   
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
}
