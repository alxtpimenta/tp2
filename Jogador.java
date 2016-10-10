package tp2.ambiente;

import tp2.cartas.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexandre
 */
public class Jogador 
{
    //CONSTRUTOR
    public Jogador(String nomeJogador)
    {
        this.nomeJogador = nomeJogador;
    }
    //NOME DO JOGADOR
    private final String nomeJogador;
    //MAO DE CARTAS DO JOGADOR
    private List<Carta> maoJogador;
    
    //RETORNA O NOME DO JOGADOR
    public String getNomeJogador()
    {
        return this.nomeJogador;
    }
    
    //INICIALIZAR VETOR DE BARALHO QUE REPRESENTA A MAO DO JGADOR
    public void inicializarMaoJogador()
    {
        this.maoJogador = new ArrayList<>();
    }
    
    
    //ADICIONAR CARTA A MAO DO JOGADOR
    public void adicionarCartaJogador(Carta c)
    {
        this.maoJogador.add(c);
    }
    
    //REMOVER CARTA DA MAO DO JOGADOR
    public void removerCartaJogador(int index)
    {
        this.maoJogador.remove(index);
    }
    
    //RETORNAR CARTA DA MAO DO JOGADOR SELECIONADA PELO INDICIE
    public Carta retornarCartaJogador(int index)
    {
        return this.maoJogador.get(index);
    }
    
    //RETORNA QUANTIDADE DE CARTAS NA MAO DO JOGADOR
    public int tamanhoMaoJogador()
    {
        return this.maoJogador.size();
    }
    
    //DISTRIBUI AS CARTAS DE CADA JOGADOR
    public static void distribuirCartas(List<Jogador> Jogadores, int qtdJogadores, List<Carta> Monte, List<Carta> Baralho)
    {
        //DAR 9 CARTAS A CADA JOGADOR E INSERIR O RESTO NO MONTE
        //AS CARTAS JA FORAM EMBARALHADAS NA INICIALIZACAO
        //VARIAVEIS PARA INDICAR OS INDICES PARA A INSERCAO
        int indice = 0;
        int fim = 9;
        //INSERIR CARTAS
        for(int i = 0; i < qtdJogadores; i++)
        {
            for(; indice < fim; indice ++)
            {
                //ADICIONA A CARTA DO BARALHO A MAO DO JOGADOR
                Jogadores.get(i).adicionarCartaJogador(Baralho.get(indice));
            }
            //INCREMENTAR O FIM (CADA JOGADOR RECEBE NOVE CARTAS)
            fim = fim + 9;
        }
        //O RESTANTE DAS CARTAS SERAO INSERIDAS NO MONTE
        for(; indice < Baralho.size(); indice ++)
        {
            //INSERE CARTAS DO BARALHO NO MONTE
            Monte.add(Baralho.get(indice));
        }
    }
    
}
