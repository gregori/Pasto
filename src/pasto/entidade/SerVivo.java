package pasto.entidade;

import java.util.Collection;
import java.util.Iterator;

import pasto.Pasto;

public abstract class SerVivo {
	protected int tempoParaNovaReproducao;
	private int idade;
	protected Pasto pasto;
	
	
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
	
	abstract public void reproduzir();
}
