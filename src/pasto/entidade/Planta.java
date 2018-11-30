package pasto.entidade;

import java.awt.Point;

import javax.swing.ImageIcon;

import pasto.Pasto;

public class Planta extends SerVivo implements Entidade {

    private static final int REPRODUCAO = 20;
	
	public Planta(Pasto pasto) {
		super(pasto);
		imagem = new ImageIcon("imagens/plant.gif"); 
	}

	@Override
	public void reproduzir() {
		Point vizinho = 
                (Point)getMembroAleatorio
                (pasto.getVizinhosLivres(this));
		
		pasto.adicionaEntidade(new Planta(pasto), vizinho);
	}

	@Override
	public void tick() {
		tempoParaNovaReproducao--;
        
        if(tempoParaNovaReproducao == 0) {      
            reproduzir();

            tempoParaNovaReproducao = REPRODUCAO;
        }
		
	}

	@Override
	public boolean eCompativel(Entidade outraEntidade) {
		// plantas sempre são compatíveis com outras entidades
		return !(outraEntidade instanceof Cerca || outraEntidade instanceof Planta);
	}

	@Override
	protected void reiniciaTempoParaNovaReproducao() {
		tempoParaNovaReproducao = REPRODUCAO;
	}

}
