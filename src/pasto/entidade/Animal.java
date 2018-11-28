package pasto.entidade;

import pasto.Pasto;

public abstract class Animal extends SerVivo {
	protected int tempoParaMover;
	protected int tempoSemComida;
	protected int idadeParaReproducao;
	
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
