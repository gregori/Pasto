package pasto.entidade;

public abstract class SerVivo {
	private int tempoParaNovaReproducao;
	private int idade;
	
	
	
	public SerVivo(int tempoParaNovaReproducao) {
		this.tempoParaNovaReproducao = tempoParaNovaReproducao;
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
