package pasto.entidade;

import javax.swing.ImageIcon;

import pasto.Pasto;

public class Cerca implements Entidade {
	/** O ícone desta entidade. */
    private final ImageIcon imagem = new ImageIcon("imagens/fence.gif"); 
    
    @SuppressWarnings("unused")
	private Pasto pasto;
    
    public Cerca(Pasto pasto) {
    	this.pasto = pasto;
    }
	
	@Override
	public void tick() {
		// não faz nada, é uma cerca
	}

	@Override
	public ImageIcon getImagem() {
		return imagem;
	}

	@Override
	public boolean eCompativel(Entidade outraEntidade) {
		return false;
	}

}
