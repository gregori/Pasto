package pasto.gui;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import pasto.engine.Engine;
import pasto.entidade.Entidade;

public class PastoGUI extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private final ImageIcon II_EMPTY      = new ImageIcon("imagens/empty.gif");
    private final int       ESCALA         = 30;
    private Engine          engine;

    private JLabel[][]      grid;
    private Map<Point, java.util.List<ImageIcon>> icons = 
        new HashMap<Point, java.util.List<ImageIcon>>();

    private JLabel          clockLabel    = new JLabel("Tempo: 0");
    private JLabel          entitiesLabel = new JLabel("Entidades: 0");
    private JButton         startButton   = new JButton("Iniciar");
    private JButton         stopButton    = new JButton("Parar");
    private JButton         exitButton    = new JButton("Sair");

    @SuppressWarnings("unused")
    private int altura;
    @SuppressWarnings("unused")
	private int largura;
    int size = 0;

    /**
     * Cria uma nova instância desta classe com as configurações
     * específicas para o pasto a ser mostrado.  
     */

    public PastoGUI(int largura, int altura, Engine engine) {
        this.altura = altura;
        this.largura = largura;

        this.engine = engine;
        
        setSize(largura * ESCALA, altura * ESCALA);
       
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,5));
        buttons.add(clockLabel);
        buttons.add(entitiesLabel);
        buttons.add(startButton);
        buttons.add(stopButton);
        buttons.add(exitButton);

        JPanel field = new JPanel();
        field.setBackground(new Color(27,204,89));
        field.setLayout(new GridLayout(altura, largura));
        grid = new JLabel[largura][altura];

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                grid[x][y] = new JLabel(II_EMPTY);
                grid[x][y].setVisible(true);
                field.add(grid[x][y]);

            }
        }

        Container display = getContentPane();
        display.setBackground(new Color(27,204,89));
        display.setLayout(new BorderLayout());
        display.add(field,BorderLayout.CENTER);
        display.add(buttons,BorderLayout.SOUTH);

        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        exitButton.setEnabled(true);
        
        update();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    

    /**
     * Este método é chamado quando um evento de ação ocorreu e
     * faz as ações corretas dependendo do evento. Nesta classe,
     * significa que alguem pressionou algum dos botões: 
     * Iniciar, Parar ou sair
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            exitButton.setEnabled(true);
            engine.start();
        }
        else if (e.getSource() == stopButton) {
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            exitButton.setEnabled(true);
            engine.stop();
        }
        else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    /**
     * O métodoThe method adicionaEntidade é chamado para notificar a GUI
     * de que uma entidade foi adicionada em uma posição. O ícone da entidade
     * adicionada é mostrada na posição.
     */

    public void adicionaEntidade(Entidade e, Point p) {
        if (p != null) {
        	ImageIcon icon = e.getImagem();

            java.util.List<ImageIcon> l = icons.get(p);
            if (l==null) {
                l = new ArrayList<ImageIcon>();
                icons.put(p, l);
            }
            l.add(icon);

            int x = (int)p.getX();
            int y = (int)p.getY();
            grid[x][y].setIcon(icon);
            
            size++;
        }
    }

    public void moveEntity(Entidade e, Point old, Point ny) {
        removeEntidade(e, old);
        adicionaEntidade(e, ny);
    }

    /**
     * O método The method removeEntidade é chamado para notificar a GUI de que
     * uma entidade foi removida de uma posição. Um ícone entre os ícones das
     * entidades remanescentes é mostrado na posição.
     */


    public void removeEntidade(Entidade e, Point p) {
        if (p != null) {
	        ImageIcon icon0 = e.getImagem();
	
	        java.util.List<ImageIcon> l = icons.get(p);
	        l.remove(icon0);
	
	        ImageIcon icon;
	        if (l.isEmpty()) icon = II_EMPTY ;
	            else icon = l.get(0);
	        
	        int x = (int)p.getX();
	        int y = (int)p.getY();
	        grid[x][y].setIcon(icon);
	        
	        size--;
        }
    }

    public void update() {

        clockLabel.setText("Tempo: " + engine.getTime());
        entitiesLabel.setText("Entidades: " + size);
    }
}

