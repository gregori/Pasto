package pasto.entidade;

import java.awt.Point;

import javax.swing.ImageIcon;

import pasto.Pasto;

public class Ovelha extends Animal implements Entidade {

	private static final int MOVER = 10;
	private static final int FOME = 30;
	private static final int REPRODUCAO = 15;
	
	private final ImageIcon imagem = new ImageIcon("imagens/sheep.gif"); 
	
	public Ovelha(Pasto pasto) {
		super(pasto);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public ImageIcon getImagem() {
		return imagem;
	}

	@Override
	public boolean eCompativel(Entidade outraEntidade) {
		return !(outraEntidade instanceof Cerca || outraEntidade instanceof Ovelha);
	}

	@Override
	public void reproduzir() {
		Point vizinho = 
                (Point)getMembroAleatorio
                (pasto.getVizinhosLivres(this));
		
		pasto.adicionaEntidade(new Ovelha(pasto), vizinho);
	}

	@Override
	protected void reiniciaTempoParaMover() {
		tempoParaMover = MOVER;
	}

	@Override
	protected void reiniciaTempoParaNovaReproducao() {
		tempoParaNovaReproducao = REPRODUCAO;
	}
	
	@Override
	protected void reiniciaTempoSemComida() {
		tempoSemComida = FOME;
	}

	@Override
	protected void comer(Entidade entidadeASerComida) {
		if (entidadeASerComida instanceof Planta) {
			pasto.removeEntidade(entidadeASerComida);
			
			reiniciaTempoSemComida();
		}		
	}
	
}
