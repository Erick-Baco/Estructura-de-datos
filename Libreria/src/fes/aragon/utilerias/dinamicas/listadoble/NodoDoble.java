package fes.aragon.utilerias.dinamicas.listadoble;

public class NodoDoble<E> {
	private E dato;
	private NodoDoble<E> siguiente, anterior;
	
	/**
	 * Contructor de la clase nodo.
	 * 
	 * @param dato.
	 */
	public NodoDoble (E dato) {
		this(dato,null,null);
	}
	
	/**
	 * Constructor de la clase nodo.
	 * 
	 * @param dato.
	 * @param siguiente.
	 */
	public NodoDoble (E dato, NodoDoble<E> siguiente, NodoDoble<E> anteriorDoble) {
		this.dato = dato;
		this.siguiente = siguiente;
		this.anterior = anteriorDoble;
	}
	
	/**
	 * Devuelve el dato del nodo.
	 * @return dato contenido del nodo.
	 */
	public E getDato() {
		return dato;
	}
	
	/**
	 * Permite asignar un dato al nodo.
	 * @param dato elemento a asignar dentro del nodo.
	 */
	public void setDato(E dato) {
		this.dato = dato;
	}
	
	/**
	 * Permite conocer el nodo siguiente con respecto al nodo en el que se llama.
	 * @return siguiente.
	 */
	public NodoDoble<E> getSiguiente (){
		return siguiente;
	}
	
	/**
	 * Direcciona el nodo actual hacia otro. 
	 * @param siguiente nuevo nodo.
	 */
	public void setSiguiente(NodoDoble<E> siguiente){
		this.siguiente = siguiente;
	}

	/**
	 * Devuelve el nodo anterior
	 * @return Nodo doble 
	 */
	public NodoDoble<E> getAnterior() {
		return anterior;
	}

	/**
	 * Asignar un Nodo en la posicion anterior.
	 * @param anterior Nodo doble nuevo
	 */
	public void setAnterior(NodoDoble<E> anterior) {
		this.anterior = anterior;
	}
}
