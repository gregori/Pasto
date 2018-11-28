package pasto.entidade;
import javax.swing.*;

/**
 * Esta é a superclasse de todas as entidades na simulação do sistema
 * de pasto. Esta interface <b>deve</b> ser implementada por todas as entidades
 * que existem na simulação do pasto.
 */
public interface Entidade {

    public void tick();

    /** 
     * ImageIcon retorna o ícone desta entidade, a ser mostrada
     * pela gui do pasto.
     */
    public ImageIcon getImagem();
    
    public boolean eCompativel(Entidade outraEntidade);

}
