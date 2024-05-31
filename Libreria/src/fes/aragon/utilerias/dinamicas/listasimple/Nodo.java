package fes.aragon.utilerias.dinamicas.listasimple;

/**
 * Clase nodo
 * 
 * @author equipo 1
 *
 * @param <E>
 */
public class Nodo<E> {
	
	private E dato;
	private Nodo<E> siguiente;
	
	/**
	 * Contructor de la clase nodo.
	 * 
	 * @param dato.
	 */
	public Nodo (E dato) {
		this(dato,null);
	}
	
	/**
	 * Constructor de la clase nodo.
	 * 
	 * @param dato.
	 * @param siguiente.
	 */
	public Nodo (E dato, Nodo<E> siguiente) {
		this.dato = dato;
		this.siguiente = siguiente;
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
	public Nodo<E> getSiguiente (){
		return siguiente;
	}
	
	/**
	 * Direcciona el nodo actual hacia otro. 
	 * @param siguiente nuevo nodo.
	 */
	public void setSiguiente(Nodo<E> siguiente){
		this.siguiente = siguiente;
	}
}
