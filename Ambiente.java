package tp2.ambiente;

import tp2.cartas.*;
import tp2.jogo.Define;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author alexandre
 */
public class Ambiente 
{
    private static Ambiente instancia = null;
    
    protected Ambiente(){} //PARA DESATIVAR INSTANCIACAO (SINGLETON)
    
    //CONSTRUTOR
    public static Ambiente getInstancia()
    {
        if(instancia == null)
            instancia = new Ambiente();
        
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
    public boolean turno;
    public boolean jogo;
    
    //VARIAVEIS DE ENTRADA
    public String entrada;
    
    //VARIAVEIS DO JOGO
    public int operador;
    public int idJogadorAtual;
    public int qtdJogadores;
    
    public void inicializarAmbiente()
    {
        //ALOCA A LISTA DE CARTAS
        this.baralho = new ArrayList<>();
        this.monte = new ArrayList<>();
        this.lixo = new ArrayList<>();
        //ALOCA A LISTA DE JOGADORES
        this.jogadores = new ArrayList<>();
        //INICIALIZA AS VARIAVEIS
        jogadorAtual = null;
        turno = true;
        jogo = true;
        entrada = null;
        operador = 0;
        idJogadorAtual = 0;
        qtdJogadores = 0;
    
    }
    
    //RECEBE O AMBIENDE DE JOGO E DISTRIBUI O BARALHO PARA CADA JOGADOR
    public static void distribuirCartas(Ambiente jogo)
    {
        //DAR 9 CARTAS A CADA JOGADOR E INSERIR O RESTO NO MONTE
        //AS CARTAS JA FORAM EMBARALHADAS NA INICIALIZACAO
        //VARIAVEIS PARA INDICAR OS INDICES PARA A INSERCAO
        int indice = 0;
        int fim = Define.MAX_MAO;
        //INSERIR CARTAS
        for(int i = 0; i < jogo.qtdJogadores; i++)
        {
            for(; indice < fim; indice ++)
            {
                //ADICIONA A CARTA DO BARALHO A MAO DO JOGADOR
                jogo.jogadores.get(i).adicionarCartaJogador(jogo.baralho.get(indice));
            }
            //INCREMENTAR O FIM (CADA JOGADOR RECEBE A QUANTIDADE DEFINIDA DE CARTAS)
            fim = fim + Define.MAX_MAO;
        }
        //O RESTANTE DAS CARTAS SERAO INSERIDAS NO MONTE
        for(; indice < jogo.baralho.size(); indice ++)
        {
            //INSERE CARTAS DO BARALHO NO MONTE
            jogo.monte.add(jogo.baralho.get(indice));
        }
    }
}
