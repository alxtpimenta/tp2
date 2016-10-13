
package tp2.ambiente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tp2.cartas.*;

/**
 *
 * @author alexandre
 */
public class Sessao 
{
    private static Sessao  instancia = null;
    
    protected Sessao (){} //PARA DESATIVAR INSTANCIACAO (SINGLETON)
    
    //CONSTRUTOR
    public static Sessao  retornarInstancia()
    {
        if(instancia == null)
            instancia = new Sessao ();
        
        return instancia;
    }
    
    //CARTAS DO AMBIENTE DE JOGO
    public List<Carta> baralho;
    public List<Carta> monte;
    public List<Carta> lixo;
    
    //JOGADORES
    public List<Jogador> jogadores;
    //JOGADOR ATIVO NO TURNO
    public Jogador jogadorAtual;
    
    //CONTROLES DO LOOP DE JOGO
    public boolean controlaTurno;
    public boolean controlaJogo;
    
    //VARIAVEIS DE ENTRADA
    public Scanner scanner;
    public String entrada;
    
    //VARIAVEIS DO JOGO
	public boolean compraEfetuada;
    public int entradaJogador;
    public int idJogadorAtual;
    public int qtdJogadores;
    
    //INICIALIZA O SINGLETON
    public void inicializarSessao()
    {
        //ALOCA A LISTA DE CARTAS
        this.baralho = new ArrayList<>();
        this.monte = new ArrayList<>();
        this.lixo = new ArrayList<>();
        //ALOCA A LISTA DE JOGADORES
        this.jogadores = new ArrayList<>();
        //INICIALIZA AS VARIAVEIS
        this.scanner = new Scanner(System.in);
        this.jogadorAtual = null;
        this.controlaTurno = true;
        this.controlaJogo = true;
        this.entrada = null;
        this.entradaJogador = 0;
        this.idJogadorAtual = 0;
        this.qtdJogadores = 0;
		this.compraEfetuada = false;
    
    }
}