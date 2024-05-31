package fes.aragon.utilerias.dinamicas.cola;

import fes.aragon.utilerias.dinamicas.listadoble.*;

public class Cola<E> implements ColaInterfaz<E> {
	ListaDoble<E> cola = new ListaDoble<>();
	
	/**
	 * Limpia la cola.
	 */
	@Override
	public void borrar() {
		cola = new ListaDoble<>();
		
	}

	/**
	 * Devuelve un valor true si la cola esta vacia y false si tiene contenido.
	 */
	@Override
	public boolean esVacia() {
		return cola.esVacia();
	}

	/**
	 * Inserta el elemento proporcionado en la cola.
	 */
	@Override
	public void insertar(E dato) {
		cola.agregarEnCabeza(dato);
	}

	/**
	 * Elimina de la cola y devuelve el elemento recien quitado.
	 */
	@Override
	public E extraer() throws Exception {
		E tmp = null;
		if(!esVacia()) {
			tmp = cola.obtenerCola();
			cola.eliminarEnCola();
		}else {
			throw new Exception("Cola vacia");
		}
		return tmp;
	}

	/**
	 * Devuelve el primer elemento de la cola.
	 */
	@Override
	public E primerElemento() throws Exception {
		E tmp = null;
		if(!esVacia()) {
			tmp = cola.obtenerCola();
		}else {
			throw new Exception("Cola vacia");
		}
		return tmp;
	}

}
