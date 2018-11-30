package pasto.entidade;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.ImageIcon;

import pasto.Pasto;

public abstract class SerVivo implements Entidade {
	protected int tempoParaNovaReproducao;
	private int idade;
	protected Pasto pasto;
	protected ImageIcon imagem;
	
	public SerVivo(Pasto pasto) {
		this.pasto = pasto;
		this.idade = 0;
		reiniciaTempoParaNovaReproducao();
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
	
	protected static <X> X getMembroAleatorio(Collection<X> c) {
        if (c.size() == 0)
            return null;
        
        Iterator<X> it = c.iterator();
        int n = (int)(Math.random() * c.size());

        while (n-- > 0) {
            it.next();
        }

        return it.next();
    }
	
	@Override
	public ImageIcon getImagem() {
		return imagem;
	}
	
	@Override
	public void tick() {
		tempoParaNovaReproducao--;
	    
	    if(tempoParaNovaReproducao == 0) {      
	        reproduzir();

	        reiniciaTempoParaNovaReproducao();
	    }
	}
	
	abstract protected void reiniciaTempoParaNovaReproducao();
	abstract public void reproduzir();
}
