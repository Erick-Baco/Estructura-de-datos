package fes.aragon.utilerias.dinamicas.listasimple;

/**
 * Herramientas para manejar una lista simple.
 * 
 * @author equipo 1
 *
 * @param <E> 
 */
public class ListaSimple <E>{

	protected Nodo<E> cabeza, cola; //punteros
	protected int longitud = 0;
	/**
	 * Metodo constructor.
	 */
	public ListaSimple() {
		cabeza = cola = null;
	}
	
	/**
	 * Permite agregar un dato directamente en la cabeza de la lista.
	 * 
	 * @param dato elemento a agregar.
	 */
	public void agregarEnCabeza (E dato) {
		cabeza = new Nodo<E>(dato, cabeza);
		if(cola == null) {
			cola = cabeza;
		}
		longitud++;
	}
	
	/**
	 * Permite agregar un dato directamente en la cola de la lista.
	 * 
	 * @param dato elemento a agregar.
	 */
	public void agregarEnCola(E dato) {
		if(cabeza == null) {
			cabeza = cola = new Nodo<E>(dato);
		}else {
			cola.setSiguiente(new Nodo<E>(dato));
			cola = cola. getSiguiente();
		}
		
		longitud++;
	}
	
	/**
	 * Imprime todos los elementos de la lista de cabeza a cola.
	 * 
	 */
	public void imprimirElementos() {
		
		System.out.print("{");
		for(Nodo<E> tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
			System.out.print(tmp.getDato() + " " );
		}
		System.out.print("}");
		System.out.println();
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
	 * Elimina el elemento que se encuentra en cabeza y lo devuelve.
	 * @return cabeza
	 */
	public E eliminarEnCabezaDevuelve() {
		E borradoE = null;
		if(cabeza != null) {
			if(cola.equals(cabeza)) {
				borradoE = cabeza.getDato();
				cabeza = cola = null;
				longitud--;
			}else {
				borradoE = cabeza.getDato();
				cabeza = cabeza.getSiguiente();
				longitud--;
			}
		}
		return borradoE;
	}
	
	/**
	 * Elimina el elemento que se encuentra en cola.
	 */
	@SuppressWarnings("unused")
	public void eliminarEnCola() {
		if(cabeza != null) {
			if(cabeza.equals(cola)) {
				cabeza = cola = null;
				longitud--;
			}else {
				Nodo<E> tmp;
				for(tmp = cabeza; tmp.getSiguiente() != cola; tmp = tmp.getSiguiente());
				tmp.setSiguiente(null);
				cola = tmp;
				longitud--;
			}
		}
	}
	
	/**
	 * Elimina el elemento que se encuentra en cola y lo devuelve.
	 * @return cola
	 */
	@SuppressWarnings("unused")
	public E eliminarEnColaDevuelve() {
		E borraE = null;
		if(cabeza != null) {
			if(cabeza.equals(cola)) {
				borraE = cola.getDato();
				cabeza = cola = null;
				longitud--;
			}else {
				Nodo<E> tmp;
				for(tmp = cabeza; tmp.getSiguiente() != cola; tmp = tmp.getSiguiente());
				borraE = tmp.getSiguiente().getDato();
				tmp.setSiguiente(null);
				cola = tmp;
				longitud--;
			}
		}
		return borraE;
	}
	
	/**
	 * Elimina el dato de la lista.
	 * 
	 * @param dato elemento a eliminar
	 * @return true si lo borra devuelve true y false si no lo puede borra.
	 */
	public boolean eliminar(E dato) {
		boolean borrado = false;
		if(cabeza != null) {
			if(cabeza.equals(cola) && dato.equals(cabeza.getDato())) {
				cabeza = cola = null;
				borrado = true;
				longitud--;
			}else if (dato.equals(cabeza.getDato()) ) {
				cabeza = cabeza.getSiguiente();
				borrado = true;
				longitud--;
			}else {
				Nodo<E> prd, tmp;
				for(prd = cabeza, tmp = cabeza.getSiguiente(); 
						tmp != null && !tmp.getDato().equals(dato); 
						prd = prd.getSiguiente(), tmp = tmp.getSiguiente());
				if (tmp != null) {
					borrado = true;
					longitud--;
					prd.setSiguiente(tmp.getSiguiente());
					if(tmp == cola) {
						cola = prd;
					}
				}
			}
		}
		return borrado;
	}
	
	/**
	 * Elimina el elemento que este en el indice dado.
	 * 
	 * @param indice ubicacion donde se desea eliminar.
	 * @return boolean si se logra eliminar es true y false si no.
	 */
	public boolean eliminarEnIndice(int indice) {
		boolean borrado = false;
		if(cabeza != null) {
			if(indice == 0) {
				borrado = true;
				cabeza = cabeza.getSiguiente();
				longitud--;
			}else {
				int i;
				Nodo<E> prd, tmp;
				for( i = 0, prd = cabeza, tmp = cabeza.getSiguiente(); 
						tmp != null && i < indice - 1; 
						prd = prd.getSiguiente(), tmp = tmp.getSiguiente(), i++);
				if(tmp == null) {
					System.out.println("indice fuera de rango");
					borrado = false;
				}else {
					longitud--;
					prd.setSiguiente(tmp.getSiguiente());
					borrado = true;
				}
			}
		}
		return borrado;
	}
	
	/**
	 * Devuelve la longitud de la lista.
	 * 
	 * @return longitud
	 */
	public int getLongitud() {
		int indice = 0;
		Nodo<E> tmpNodo;
		for (indice = 0, tmpNodo = cabeza; tmpNodo != null; tmpNodo = tmpNodo.getSiguiente(), indice++);
		return indice;
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
	 * Devuelve el nodo de una posición dada.
	 * 
	 * @param i Posicion.
	 * @return nodo Devuelve el nodo de la posicion i, si no se encuentra devuelve la cabeza
	 */
	public E obtenerNodo(int i) {
		Nodo<E> tmp1 = null;
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
		Nodo<E> tmp;
		for(indice = 0, tmp = cabeza; tmp != null && !tmp.getDato().equals(dato); tmp = tmp.getSiguiente(), indice++ );
		if(tmp == null) {
			indice = -1;
		}
		return indice;
	}
	
	/**
	 * Agrega el elemento dado en la posición deseada.
	 * @param i posicion
	 * @param dato elemento
	 */
	public void agregarEnIndice(int i, E dato) {
		if(cabeza != null) {
			Nodo<E> tmp;
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
		Nodo<E> tmp;
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
	 * Elimina  el dato, true para eliminar todos y false para eliminar solo el primero.
	 * @param dato elemento/s que se quieren eliminar.
	 * @param todos determina si se van a eliminar todos o solo el primero.
	 */
	public void eliminarRepetido (E dato, boolean todos) {
		Nodo<E> tmp, prd;
		int contador = 0;
		System.out.println("metodo");
		
		if(cabeza.equals(cola) && dato.equals(cabeza.getDato())) {
			cabeza = cola = null;
			longitud--;
			System.out.println("cola = cabeza");
		}else {
			for(prd = cabeza, tmp = cabeza.getSiguiente(); 
				tmp != null;
				prd = prd.getSiguiente(), tmp = tmp.getSiguiente()) {
				System.out.println("for");
				if(todos) {
					contador = 0;
					System.out.println("todos");
				}
				if(contador < 1) {
					System.out.println("contador");
					if (dato.equals(cabeza.getDato()) ) {
						cabeza = cabeza.getSiguiente();
						longitud--;
						contador++;
						System.out.println("dato en cabeza");
					}else if (dato.equals(tmp.getDato())) {
						prd.setSiguiente(tmp.getSiguiente());
						contador++;
						System.out.println("eliminar");
					}
				}
			}
		}
	}
}
