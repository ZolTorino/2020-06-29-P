package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	public PremierLeagueDAO dao;
	private SimpleWeightedGraph<Match, DefaultWeightedEdge> grafo;
	public Map<Integer, Match> idMap;
	public Model() {
		dao= new PremierLeagueDAO();
	}
	
	public void creaGrafo(int min, int month) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		idMap=new HashMap<>();
		dao.listAllMatches(idMap, month);
		Graphs.addAllVertices(grafo, idMap.values());
		LinkedList<Arco> archi= new LinkedList<>();
		dao.getArchi(idMap, min, archi, month);
		System.out.println(archi.size());
		for(Arco a: archi)
		{
			if(grafo.containsVertex(a.m1)&&grafo.containsVertex(a.m2)) {
				DefaultWeightedEdge e1 = this.grafo.getEdge(a.m1,a.m2);
				DefaultWeightedEdge e2 = this.grafo.getEdge(a.m2,a.m1);
				//if(e1==null&&e2==null)
					Graphs.addEdge(grafo, a.m1, a.m2, a.peso);
			}
			else
				System.out.println("problema");
			
		}
		System.out.println("vertici: "+grafo.vertexSet().size()+" archi: "+grafo.edgeSet().size());
	}
	public LinkedList<Match> connessioneMax() {
		LinkedList<Match> best= new LinkedList<>();
		int max=0;
		for(DefaultWeightedEdge e: grafo.edgeSet())
		{
			if(grafo.getEdgeWeight(e)==max)
			{
				best.add(grafo.getEdgeSource(e));
				best.add(grafo.getEdgeTarget(e));
			}
			if(grafo.getEdgeWeight(e)>max)
			{
				best.clear();
				best.add(grafo.getEdgeSource(e));
				best.add(grafo.getEdgeTarget(e));
			}
		}
		return best;
	}
	LinkedList<Arco>best= new LinkedList<>();
	public void cammino(Match m, Match m2) {
		LinkedList<Arco>parziale= new LinkedList<>();
		
		int pesomax=0;
		
	}
	/*public void calcola(LinkedList<M> parziale, Match m1, Match m2,int pesomax) {
		if(parziale.get(parziale.size()-1).m2.equals(m2))
		{
			if(peso(parziale)>pesomax)
			{
				best=parziale;
			}
		}
		else
		{
			for(Match m: parziale.get(parziale.size()-1).)
			{
				
			}
			parziale.add(null)
		}
	}
	public int peso(LinkedList<Arco> in)
	{
		int res=0;
		for(Arco a: in)
		{
			res+=a.peso;
		}
		return res;
	}*/
}
