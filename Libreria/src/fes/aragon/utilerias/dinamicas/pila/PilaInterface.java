package fes.aragon.utilerias.dinamicas.pila;

public interface PilaInterface<E> {
	/**
	 * Vacia el contenido de la lista
	 */
	public void borrar();
	/**
	 * Devuelve true si la pila esta vacia y false en el caso contrario.
	 * @return boolean
	 */
	public boolean esVacia();
	/**
	 * Inserta el dato proporcionado en la pila
	 * @param dato
	 */
	public void insertar (E dato);
	/**
	 * Elimina de la pila y devuelve el elemento recien quitado.
	 */
	public E extraer() throws Exception;
	/**
	 * Devuelve el primer elemento de la pila.
	 */
	public E elementoSuperior() throws Exception;
}
