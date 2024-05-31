package fes.aragon.utilerias.estaticas;

import fes.aragon.excep.*;

/**
 * Clase arreglos
 * 
 * @author equipo 1
 *
 * @param <E> indicar el tipo 
 */
public class Arreglos <E>{
	    private int indice = 0;
	    private Object[] l;
	    public Arreglos(int numeroElementos){
	      this.l = new Object [numeroElementos];
	    }
	  
/**
 * Inserta el elemento recibido en la última posición disponible del arreglo.
 * 
 * @param x Elemento a insertar en el arreglo.
 * @throws IndiceFueraDeRango
 */
	  public void insertar (E x) throws IndiceFueraDeRango {
	    if (indice < l.length){
	      l[indice] = x;
	      indice++;
	    }else{
	      throw new IndiceFueraDeRango ("Indice fuera de rango");
	    }
	  }

	  /**
	   * Busca un elemento determinado dentro del arreglo y devuelve la posición en la que se encuentra. Si
	   * no se encuentra el elemento devuelve un -1.
	   * 
	   * @param x Elemento que se quiere buscar en el arreglo.
	   * @return
	   */
	  public Integer localiza(E x){
	    for( int i = 0; i < l.length; i++){
	      if(l[i].equals(x)){
	        return i;
	      }
	    }
	    return -1;
	  }

	  /**
	   * Busca determinada posición dentro del arreglo y devuelve el elemento que se encuentra en la posición. 
	   * 
	   * @param p Posición del elemento que se quiere recuperar.
	   * @return Elemento encontrado.
	   * 
	   * @throws IndiceFueraDeRango
	   */
	  public E recupera (int p) throws IndiceFueraDeRango{
	    if (p > l.length || p < 0){
	      throw new IndiceFueraDeRango ("indice fuera de rango");
	    }else{
	      @SuppressWarnings("unchecked")
	      final E e=(E)l[p];
	      return e;
	    }
	  }

	  /**
	   * Elimina el elemento que se encuentra en la posición indicada.
	   * 
	   * @param p Posición del elemento a eliminar.
	   * @throws IndiceFueraDeRango
	   */
	  public void suprime (int p) throws IndiceFueraDeRango{
	    if (p > l.length || p < 0){
	      throw new IndiceFueraDeRango ("indice fuera de rango");
	    }else{
	      l[p] = null;
	    }
	  }

	  /**
	   * Devuelve el elemento que se encuentra el la posición siguiente a la que se proporciona.
	   * 
	   * @param p Posicion.
	   * @return Elemento de la posición siguiente a p.
	   * @throws IndiceFueraDeRango
	   */
	  public E siguiente(int p) throws IndiceFueraDeRango{
	    if (p == l.length || p < -1){
	      throw new IndiceFueraDeRango ("indice fuera de rango");
	    }else{
	      @SuppressWarnings("unchecked")
	      final E e=(E)l[p + 1];
	      return e;
	    }
	  }

	  /**
	   * Devuelve el elemento que se encuentra el la posición anterior a la que se proporciona.
	   * 
	   * @param p Posición.
	   * @return Elemento anterior a la posición p.
	   * @throws IndiceFueraDeRango
	   */
	  public E anterior(int p) throws IndiceFueraDeRango{
	    if (p == l.length - 1 || p < -1){
	      throw new IndiceFueraDeRango ("indice fuera de rango");
	    }else{
	      @SuppressWarnings("unchecked")
	      final E e=(E)l[p - 1];
	      return e;
	    }
	  }

	  /**
	   * Borra todos los elementos del arreglo.
	   */
	  public void limpiar(){
	    l = new Object[l.length];
	  }
 
	  /**
	   * Regresa el primer elemento dentro del arreglo. 
	   * 
	   * @return elemento.
	   */
	  public E primero(){
	    @SuppressWarnings("unchecked")
	      final E e=(E)l[0];
	      return e;
	  }

	  /**
	   * Devuelve la longitud del arreglo. 
	   * 
	   * @return longitud.
	   */
	  public Integer longitud(){
	    return l.length;
	  }

	  /**
	   * Imprime el arreglo desde la primera posición hasta la última.
	   * 
	   */
	  public void imprime(){
	    for(int i = 0; i < l.length; i++){
	      System.out.print(l[i] + " ");
	    }
	    System.out.println();
	  }

	  /**
	   * Asigna dentro del arreglo el elemento proporcionado en la posición determinada. 
	   * 
	   * @param p Posición.
	   * @param x Elemento.
	   * @throws IndiceFueraDeRango
	   */
	  public void asignar(int p, E x) throws IndiceFueraDeRango{
	    if (p > l.length || p < 0){
	      throw new IndiceFueraDeRango ("indice fuera de rango");
	    }else{
	      l[p] = x;
	    }
	  }
	  
	}