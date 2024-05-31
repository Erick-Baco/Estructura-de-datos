package fes.aragon.utilerias.dinamicas.cola;

public interface ColaInterfaz<E> {
	public void borrar();
	public boolean esVacia();
	public void insertar(E dato);
	public E extraer() throws Exception;
	public E primerElemento() throws Exception;
	
}
