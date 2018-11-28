package pasto.entidade;

public abstract class Animal extends SerVivo {
	private int tempoParaMover;
	private int tempoSemComida;
	private int idadeParaReproducao;
	
	public Animal(int tempoParaMover, int tempoSemComida, int idadeParaReproducao, int tempoParaNovaReproducao) {
		super(tempoParaNovaReproducao);
		this.tempoParaMover = tempoParaMover;
		this.tempoSemComida = tempoSemComida;
		this.idadeParaReproducao = idadeParaReproducao;
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
