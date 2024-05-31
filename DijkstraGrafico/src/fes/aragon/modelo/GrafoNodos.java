package fes.aragon.modelo;

import fes.aragon.utilerias.dinamicas.listadoble.ListaDoble;

public class GrafoNodos {
	
	private ListaDoble<Nodos> grafo = new ListaDoble<>();

	public ListaDoble<Nodos> getGrafo() {
		return grafo;
	}

	public void setGrafo(ListaDoble<Nodos> grafo) {
		this.grafo = grafo;
	}

	public GrafoNodos(ListaDoble<Nodos> grafo) {
		super();
		this.grafo = grafo;
	}

	public GrafoNodos() {
		super();
	}
	
	
}
