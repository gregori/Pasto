package pasto.engine;
import java.util.*;
import javax.swing.Timer;

import pasto.Pasto;
import pasto.entidade.Entidade;

import java.awt.event.*;

/**
 * A simulação roda sob um temporizador interno, que envia um 'tique'
 * dado um certo intervalo. Um tique do temporizador significa que cada
 * entidade no pasto deve obter im tique. Quando uma entidade obtém
 * um tique, esta entidade poderá executar suas tarefas de acordo com
 * seu tipo. Ou seja, ela pode se mover, morrer de fome ou reproduzir-se.
 */

public class Engine implements ActionListener {
    
    private final int  REFERENCIA_VELOCIDADE = 1000; /* 1000 */
    private static int velocidade   = 10;
    private Timer      timer   = new Timer(REFERENCIA_VELOCIDADE/velocidade,this);
    private int        time    = 0;

    private Pasto pasto;


    public Engine (Pasto pasto) {
        this.pasto = pasto;
        this.velocidade = velocidade;
    }

    public void actionPerformed(ActionEvent event) {
     
        List<Entidade> queue = pasto.getEntidades();
        for (Entidade e : queue) {
            e.tick();
        }
        pasto.recarregarTela();
        time++;
    }

    public void setSpeed(int speed) {
        timer.setDelay(REFERENCIA_VELOCIDADE/speed);
    }

    public void start() {
        setSpeed(velocidade);
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public int getTime () {
        return time;
    }

}
