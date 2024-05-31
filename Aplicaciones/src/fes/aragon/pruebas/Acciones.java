package fes.aragon.pruebas;

import fes.aragon.controller.CalcularAcciones;
import fes.aragon.utilerias.dinamicas.cola.Cola;

public class Acciones {

	public static void main(String[] args) {
		
		Cola<String> cola = new Cola<String>();
		CalcularAcciones a = new CalcularAcciones();
		a.calcularGanancia("/Users/erickbaco/eclipse-workspace/1.1/SegundoParcial/src/fes/aragon/recursos/acciones.txt", cola);
	
	
	}
}

