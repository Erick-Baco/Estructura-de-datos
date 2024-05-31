package fes.aragon.controller;

import fes.aragon.utilerias.dinamicas.listadoble.ListaDoble;

public class Ackermann {
	public static ListaDoble<Integer> lista = new ListaDoble<>();
    int profundidad;
    
    public static int ackermann(int m, int n, int profundidad) {
        lista.agregarEnCabeza(profundidad); 
        if (m == 0) {
            return n + 1;
        } else if (n == 0) {
            return ackermann(m - 1, 1, profundidad + 1);
        } else {
            int tempM = m;
            m = m - 1;
            int tempN = n;
            n = ackermann(tempM, n - 1, profundidad + 1);
            int resultado = ackermann(m, n, profundidad + 1);
            return resultado;
        }
    }
    public static int getProfundidad() {
        return lista.getLongitud();
    }
}