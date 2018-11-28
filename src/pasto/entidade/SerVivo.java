package pasto.entidade;

import pasto.Pasto;

public abstract class SerVivo {
	private int tempoParaNovaReproducao;
	private int idade;
	@SuppressWarnings("unused")
	private Pasto pasto;
	
	
	public SerVivo(Pasto pasto) {
		this.pasto = pasto;
		this.idade = 0;
	}

	public int getTempoParaNovaReproducao() {
		return tempoParaNovaReproducao;
	}

	public int getIdade() {
		return idade;
	}
	
	public void envelhece() {
		idade++;
	}
	
	abstract public void reproduzir();
}
