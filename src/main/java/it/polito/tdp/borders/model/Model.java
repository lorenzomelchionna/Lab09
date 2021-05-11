package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private SimpleGraph<Country,DefaultEdge> Grafo;
	
	private BordersDAO dao;
	
	private Map<Integer,Country> idMap;
	
	public Model() {
		
		dao = new BordersDAO();
		
		idMap = new HashMap<>();
		dao.loadAllCountries(idMap);
		
	}
	
	public void creaGrafo(int annoMax) {
		
		Grafo = new SimpleGraph<>(DefaultEdge.class);
		
		Graphs.addAllVertices(Grafo, idMap.values());
		
		for(Border b : dao.getCountryPairs(annoMax, idMap)) {
			
			if(Grafo.containsVertex(b.getC1()) && Grafo.containsVertex(b.getC2())) {
				
				DefaultEdge e = Grafo.getEdge(b.getC1(), b.getC2());
				
				if(e == null)
					Graphs.addEdgeWithVertices(Grafo, b.getC1(), b.getC2());
				
			}
			
		}
		
		System.out.println("GRAFO CREATO!");
		System.out.println("# Vertici: " + Grafo.vertexSet().size()+"\n");
		System.out.println("# Archi: "+Grafo.edgeSet().size()+"\n");
		
	}

}
