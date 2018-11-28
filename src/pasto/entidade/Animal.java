package pasto.entidade;

import pasto.Pasto;

public abstract class Animal extends SerVivo {
	private int tempoParaMover;
	private int tempoSemComida;
	private int idadeParaReproducao;
	
	public Animal(Pasto pasto) {
		super(pasto);
	}

	public int getTempoParaMover() {
		return tempoParaMover;
	}

	public int getTempoSemComida() {
		return tempoSemComida;
	}

	public int getIdadeParaReproducao() {
		return idadeParaReproducao;
	}
	
}
