package fes.aragon.excep;

/**
 * Clase para el manejo de errores en arreglos
 * @author equipo 1
 *
 */

public class IndiceFueraDeRango extends Exception{

	  private static final long serialVersionUID = 1L;

	  public IndiceFueraDeRango(String msg){
	    super(msg);
	  }
	}