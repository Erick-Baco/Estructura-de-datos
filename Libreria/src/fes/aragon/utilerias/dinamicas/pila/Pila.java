package fes.aragon.utilerias.dinamicas.pila;

import fes.aragon.utilerias.dinamicas.listadoble.*;

public class Pila<E> implements PilaInterface<E> {

	private ListaDoble<E> pila = new ListaDoble<>();
	
	/**
	 * Elimina todo el contenido de la pila
	 */
	@Override
	public void borrar() {
		// TODO Auto-generated method stub
		pila = new ListaDoble<>();
	}
	/**
	 * Devuelve un valor true si la cola esta vacia y false si tiene contenido.
	 */
	@Override
	public boolean esVacia() {
		// TODO Auto-generated method stub
		return pila.esVacia();
	}
	/**
	 * Inserta el elemento proporcionado en la pila.
	 */
	@Override
	public void insertar(E dato) {
		// TODO Auto-generated method stub
		pila.agregarEnCola(dato);
	}
	/**
	 * Elimina de la pila y devuelve el elemento recien quitado.
	 */
	@Override
	public E extraer() throws Exception {
		E tmp = null;
		if(!esVacia()) {
			tmp = pila.obtenerCola();
			pila.eliminarEnCola();
		}else {
			throw new Exception("Pila vacia");
		}
		return tmp;
	}
	/**
	 * Devuelve el primer elemento de la pila.
	 */
	@Override
	public E elementoSuperior() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
