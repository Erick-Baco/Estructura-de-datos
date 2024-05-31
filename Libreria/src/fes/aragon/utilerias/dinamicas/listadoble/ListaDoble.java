package fes.aragon.utilerias.dinamicas.listadoble;

public class ListaDoble<E> {
	
	protected NodoDoble<E> cabeza, cola; //punteros
	protected int longitud = 0;
	
	
	/**
	 * Constructor por defecto.
	 */
	public ListaDoble() {
		cabeza = cola = null;
	}
	
	/**
	 * Devuelve la longitud de la lista.
	 * 
	 * @return longitud
	 */
	public int getLongitud() {
		return longitud;
	}
	
	/**
	 * Agrega el elemento dado en la cabeza de la lista.
	 * @param dato elemento a agregar
	 */
	public void agregarEnCabeza (E dato) {
		if(cabeza == null) {
			cabeza = cola = new NodoDoble<E>(dato);
			longitud++;
		}else {
			cabeza.setAnterior(new NodoDoble<E>(dato,cabeza,null));
			cabeza = cabeza.getAnterior();
			
			if(cola == null) {
				cola = cabeza;
			}
			longitud++;
		}
		
	}
	/**
	 * Agrega el elemento dado en la cola de la lista.
	 * @param dato elemento a agregar
	 */
	public void agregarEnCola (E dato) {
		if(cabeza == null) {
			cabeza = cola = new NodoDoble<E>(dato);
		}else {
			cola.setSiguiente(new NodoDoble<E>(dato,null,cola));
			cola = cola. getSiguiente();
		}
		
		longitud++;
	}
	/**
	 * Verifica si la lista tiene contenido.
	 *  
	 * @return boolean si está vacía true y false si no
	 */
	public boolean esVacia() {
		if (cabeza == null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Elimina el elemento que se encuentra en cabeza.
	 */
	public void eliminarEnCabeza() {
		if(cabeza != null) {
			if(cola.equals(cabeza)) {
				cabeza = cola = null;
				longitud--;
			}else {
				cabeza = cabeza.getSiguiente();
				longitud--;
			}
		}
	}
	
	/**
	 * Elimina el elemento que se encuentra en cola.
	 */
	public void eliminarEnCola() {
		if(cabeza != null) {
			if(cabeza.equals(cola)) {
				cabeza = cola = null;
				longitud--;
			}else {
				cola = cola.getAnterior();
				cola.setSiguiente(null);
				longitud--;
			}
		}
	}
	
	/**
	 * Devuelve el nodo de una posición dada.
	 * 
	 * @param i Posición.
	 * @return nodo Devuelve el nodo de la posición i, si no se encuentra devuelve la cabeza
	 */
	public E obtenerNodo(int i) {
		NodoDoble<E> tmp1 = null;
		int indice = 0;
		if(cabeza != null){
			for(indice = 0, tmp1 = cabeza; tmp1 != null && indice < i; tmp1 = tmp1.getSiguiente(), indice++ );
			if(tmp1 == null) {
				System.out.println("indice fuera de rango");
				tmp1 = cabeza;
			}
		}
		return tmp1.getDato();
	}
	
	/**
	 * Verifica que un elemento dado este en la lista
	 * @param dato elemento a buscar
	 * @return devuelve la posición del elemento
	 */
	public int estaEnLista(E dato) {
		int indice = 0;
		NodoDoble<E> tmp;
		for(indice = 0, tmp = cabeza; tmp != null && !tmp.getDato().equals(dato); tmp = tmp.getSiguiente(), indice++ );
		if(tmp == null) {
			indice = -1;
		}
		return indice;
	}
	
	/**
	 * Elimina el elemento que esté en el índice dado.
	 * 
	 * @param indice ubicación donde se desea eliminar.
	 * @return boolean si se logra eliminar es true y false si no.
	 */
	public boolean eliminarEnIndice(int indice) {
		if(cabeza != null) {
			if(indice == 0) {
				cabeza = cabeza.getSiguiente();
				longitud--;
				return true;
			}else {
				int i;
				NodoDoble<E> prd, tmp;
				for( i = 0, prd = cabeza, tmp = cabeza.getSiguiente(); 
						tmp != null && i < indice - 1; 
						prd = prd.getSiguiente(), tmp = tmp.getSiguiente(), i++);
				if(tmp == null) {
					System.out.println("indice fuera de rango");
					return false;
				}else {
					longitud--;
					prd.setSiguiente(tmp.getSiguiente());
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Inserta el elemento dado en la posición deseada.
	 * @param i posición
	 * @param dato elemento
	 */
	public void insertarEnIndice(int i, E dato) {
		if(cabeza != null) {
			NodoDoble<E> tmp;
			int indice;
			for(indice = 0, tmp = cabeza; tmp != null && indice < i; tmp = tmp.getSiguiente(), indice++ );
			if(tmp == null) {
				System.out.println("indice fuera de rango");
			}else {
				tmp.setDato(dato);
			}
			
		}
	}
	
	/**
	 * Agrega el elemento dado en la posición deseada.
	 * @param i posición
	 * @param dato elemento
	 */
	public void asignar(int i, E dato) {
		if(cabeza != null) {
			NodoDoble<E> tmp;
			int indice;
			for(indice = 0, tmp = cabeza; tmp != null && indice < i; tmp = tmp.getSiguiente(), indice++ );
			if(tmp == null) {
				System.out.println("indice fuera de rango");
			}else {
				tmp.setDato(dato);
			}
			
		}
	}
	
	/**
	 * Sustituye el dato asignado con el nievo dato, true para cambiar todos y false para cambiar solo el primero que se encuentre en la lista
	 * de cabeza a cola.
	 * 
	 * @param dato elemento/s que se quiere/n reemplazar.
	 * @param nuevoDato elemento con el que se va/n a reemplazar.
	 * @param todos determina si se van a cambiar todos o solo el primero que se encuentre en la lista.
	 */
	public void asignarReemplazar (E dato, E nuevoDato, boolean todos) {
		NodoDoble<E> tmp;
		int contador = 0;
		
		for(tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
			if(todos) {
				contador = 0;
			}
			if (dato.equals(tmp.getDato()) && contador < 1) {
				tmp.setDato(nuevoDato);
				contador++;
			}
		}
		
	}
	
	/**
	 * Imprime todos los elementos de la lista de cabeza a cola.
	 * 
	 */
	public void imprimirElementos() {
		
		System.out.print("{");
		for(NodoDoble<E> tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
			System.out.print(tmp.getDato() + " " );
		}
		System.out.print("}");
		System.out.println();
	}
	
	public E obtenerCola() {
		return cola.getDato();
	}
}
