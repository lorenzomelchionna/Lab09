package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private SimpleGraph<Country,DefaultEdge> Grafo;
	
	private ConnectivityInspector<Country,DefaultEdge> CE;
	
	private BordersDAO dao;
	
	private Map<Integer,Country> idMap;
	
	public Model() {
		
		dao = new BordersDAO();
		
		idMap = new HashMap<>();
		dao.loadAllCountries(idMap);
		
	}
	
	public void creaGrafo(int annoMax) {
		
		Grafo = new SimpleGraph<>(DefaultEdge.class);
		
		CE = new ConnectivityInspector<>(Grafo);
		
		//Graphs.addAllVertices(Grafo, idMap.values());
		
		for(Border b : dao.getCountryPairs(annoMax, idMap)) {
			
			if(!Grafo.containsVertex(b.getC1())) 
				Grafo.addVertex(b.getC1());
			
			if(!Grafo.containsVertex(b.getC2()))
				Grafo.addVertex(b.getC2());
					
			DefaultEdge e = Grafo.getEdge(b.getC1(), b.getC2());
				
			if(e == null)
				Grafo.addEdge(b.getC1(), b.getC2());
				
		}
		
		//for(Country c : Grafo.vertexSet())
			//if(Grafo.degreeOf(c) == 0)
				
		
		System.out.println("GRAFO CREATO!");
		System.out.println("# Vertici: " + Grafo.vertexSet().size());
		System.out.println("# Archi: "+Grafo.edgeSet().size());
		
	}
	
	public String getStati() {
		
		String result = "";
		
		result += "Numero componenti connesse: "+CE.connectedSets().size()+"\n";
		
		for(Country c : Grafo.vertexSet()) {
			result += c.getNomeStato()+" "+Grafo.degreeOf(c)+"\n";
		}
		
		return result;
		
	}
	
	public Set<Country> getNodi() {
		return Grafo.vertexSet();
	}

}
