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
	private final ImageIcon II_UNKNOWN    = new ImageIcon("imagens/unknown.gif");
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

    private int altura;
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
     * This method is called when an action event has occured and
     * carries out the correct actions depending on the event. In this
     * class, this means that someone has pressed any of the buttons
     * start, stop, or exit.
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
     * The method addEntity is called to notify the GUI that an entity
     * has been added to a position. The icon of the added entity is
     * displayed at the position.
     */

    public void addEntity(Entidade e, Point p) {
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

    public void moveEntity(Entidade e, Point old, Point ny) {
        removeEntity(e, old);
        addEntity(e, ny);
    }

    /**
     * The method removeEntity is called to notify the GUI that an
     * entity has been removed from a position. One icon among the
     * icons of the remaining entities is displayed at the position.
     */


    public void removeEntity(Entidade e, Point p) {
        
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

    public void update() {

        clockLabel.setText("Tempo: " + engine.getTime());
        entitiesLabel.setText("Entidades: " + size);
    }
}

