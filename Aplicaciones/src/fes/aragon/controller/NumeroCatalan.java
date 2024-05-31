package fes.aragon.controller;

public class NumeroCatalan {

    public static Integer calcularCatalan(Integer n) {
        
    	if (n <= 1) {
            return 1;
        }
        Integer resultado = 0;

        for (int i = 0; i < n; i++) {
            resultado = resultado + calcularCatalan(i) * calcularCatalan(n-i-1);
        }
        return resultado;
    }

    public static void main(String[] args) {
        int n = 3;

        Integer catalanN = calcularCatalan(n);

        System.out.println("El " + n + "-ésimo número de Catalan es: " + catalanN);
    }
}

