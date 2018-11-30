package pasto.entidade;

import java.awt.Point;

import pasto.Pasto;

public abstract class Animal extends SerVivo {
	protected int tempoParaMover;
	protected int tempoSemComida;
	protected int idadeParaReproducao;
	
	public Animal(Pasto pasto) {
		super(pasto);
		reiniciaTempoParaMover();
		reiniciaTempoSemComida();
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
	
	@Override
	public void tick() {
		super.tick();
		tempoParaMover--;
		
		if (tempoParaMover == 0) {
			mover();
			reiniciaTempoParaMover();
			
			for (Entidade e : pasto.getEntidadesEm(pasto.getPosicaoEntidade(this))) {
				comer(e);
			}
		}
		
		tempoSemComida--;
		if (tempoSemComida == 0) {
			morrer();
		}
	}
	
	abstract protected void reiniciaTempoParaMover();
	abstract protected void reiniciaTempoSemComida();
	abstract protected void comer(Entidade entidadeASerComida);
	
	protected void mover() {
		Point neighbour = 
                (Point)getMembroAleatorio
                (pasto.getVizinhosLivres(this));
            
            if(neighbour != null) 
                pasto.moveEntidade(this, neighbour);
	}

	
	protected void morrer() {
		pasto.removeEntidade(this);
	}
}
