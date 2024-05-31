package fes.aragon.controller;

public class CDatos {
	private Integer inf, sup;
	
	public CDatos(Integer inf, Integer sup) {
		this.inf = inf;
		this.sup = sup;
	}
	
	public Integer obtenerInf() {
		return inf;
	}
	
	public Integer obtenerSup() {
		return sup;
	}
}
