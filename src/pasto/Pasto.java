package pasto;
import java.util.*;

import pasto.engine.Engine;
import pasto.entidade.Cerca;
import pasto.entidade.Dummy;
import pasto.entidade.Entidade;
import pasto.gui.PastoGUI;

import java.awt.Point;


/**
 * Um pasto contém ovelhas, lobos, cercas, plantas e possivelmente
 * outras entidades. Estas entidades movem-se pelo pasto e tentam 
 * achar comida, outras entidades do mesmo tipo e fogem de possíveis
 * inimigos.
 */
public class Pasto {

    private int         largura = 20;
    private int         altura = 20;

    private int         dummys = 20;
    private int         lobos;
    private int         ovelhas;
    private int         plantas;
    private int         cercas;

    private Set<Entidade> mundo = 
        new HashSet<Entidade>();
    private Map<Point, List<Entidade>> grid = 
        new HashMap<Point, List<Entidade>>();
    private Map<Entidade, Point> ponto 
        = new HashMap<Entidade, Point>();

    private PastoGUI gui;

    /** 
     * Cria uma nova instância desta classe e posiciona as entidades
     * nela, em posições aleatórias.
     */
    public Pasto() {

        Engine engine = new Engine(this);
        gui = new PastoGUI(largura, altura, engine);

        /* O pasto é rodeado por uma cerca. Substitua Dummy por
         * Cerca quando você criar essa classe */
        for (int i = 0; i < largura; i++) {
            adicionaEntidade(new Cerca(this), new Point(i,0));
            adicionaEntidade(new Cerca(this), new Point(i, altura - 1));
        }
        for (int i = 1; i < altura-1; i++) {
            adicionaEntidade(new Cerca(this), new Point(0,i));
            adicionaEntidade(new Cerca(this), new Point(largura - 1,i));
        }

        /* 
         * Agora insira o número correto de entidades no pasto.
         */
        for (int i = 0; i < dummys; i++) {
            Entidade dummy = new Dummy(this, true);
            adicionaEntidade(dummy, getPosicaoLivre(dummy));
        }

        gui.update();
    }

    public void recarregarTela() {
        gui.update();
    }

   
    /**
     * Retorna uma posição livre aleatória no pasto, se houver uma.
     * 
     * Se a primeira posição aleatória estiver ocupada, o resto
     * é feita uma busca no resto da tela para encontrar uma 
     * posição livre. 
     */
    private Point getPosicaoLivre(Entidade aPosicionar) 
        throws MissingResourceException {
        Point posicao = new Point((int)(Math.random() * largura),
                                   (int)(Math.random() * altura)); 
        int startX = (int)posicao.getX();
        int startY = (int)posicao.getY();

        int p = startX+startY*largura;
        int m = altura * largura;
        int q = 97; //qualquer número primo grande serve

        for (int i = 0; i<m; i++) {
            int j = (p+i*q) % m;
            int x = j % largura;
            int y = j / largura;

            posicao = new Point(x,y);
            boolean livre = true;

            Collection <Entidade> c = getEntidadesEm(posicao);
            if (c != null) {
                for (Entidade estaEntidade : c) {
                    if(!aPosicionar.eCompativel(estaEntidade)) { 
                        livre = false; break; 
                    }
                }
            }
            if (livre) return posicao;
        }
        throw new MissingResourceException(
                  "Não há espaço livre"+" no pasto",
                  "Pasto", "");
    }
    
            
    public Point getPosicao (Entidade e) {
        return ponto.get(e);
    }

    /**
     * Adiciona uma nova entidade no pasto.
     */
    public void adicionaEntidade(Entidade entidade, Point pos) {

        mundo.add(entidade);

        List<Entidade> l = grid.get(pos);
        if (l == null) {
            l = new  ArrayList<Entidade>();
            grid.put(pos, l);
        }
        l.add(entidade);

        ponto.put(entidade,pos);

        gui.adicionaEntidade(entidade, pos);
    }
    
    public void moveEntidade(Entidade e, Point posicaoNova) {
        
        Point posicaoAntiga = ponto.get(e);
        List<Entidade> l = grid.get(posicaoAntiga);
        if (!l.remove(e)) 
            throw new IllegalStateException("Estado inconsistente no Pasto");
        /* Esperamos que a entidade esteja na posição antiga, antes que nos movamos. */
                
        l = grid.get(posicaoNova);
        if (l == null) {
            l = new  ArrayList<Entidade>();
            grid.put(posicaoNova, l);
        }
        l.add(e);

        ponto.put(e, posicaoNova);

        gui.moveEntity(e, posicaoAntiga, posicaoNova);
    }

    /**
     * Remove a entidade especificada deste pasto.
     */
    public void removeEntidade(Entidade entidade) { 

        Point p = ponto.get(entidade);
        mundo.remove(entidade); 
        grid.get(p).remove(entidade);
        ponto.remove(entidade);
        gui.removeEntidade(entidade, p);

    }

    /**
     * Vários métodos para obter informações sobre o pasto
     */

    public List<Entidade> getEntidades() {
        return new ArrayList<Entidade>(mundo);
    }
        
    public Collection<Entidade> getEntidadesEm(Point lookAt) {

        Collection<Entidade> l = grid.get(lookAt);
        
        if (l==null) {
            return null;
        }
        else {
            return new ArrayList<Entidade>(l);
        }
    }


    public Collection<Point> getVizinhosLivres(Entidade entidade) {
        Set<Point> free = new HashSet<Point>();
        Point p;

        int entityX = (int)getPosicaoEntidade(entidade).getX();
        int entityY = (int)getPosicaoEntidade(entidade).getY();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
            p = new Point(entityX + x,
                          entityY + y);
            if (espacoLivre(p, entidade))
                free.add(p);
            }
        }
        return free;
    }

    private boolean espacoLivre(Point p, Entidade e) {
                              
        List <Entidade> l = grid.get(p);
        if ( l == null  ) return true;
        for (Entidade old : l ) 
            if (! old.eCompativel(e)) return false;
        return true;
    }

    public Point getPosicaoEntidade(Entidade entidade) {
        return ponto.get(entidade);
    }


    /** O método para executar o programa. */
    public static void main(String[] args) {

        new Pasto();
    }


}


