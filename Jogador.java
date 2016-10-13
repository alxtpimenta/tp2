package tp2.ambiente;

import tp2.cartas.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    
    //CRIA OS JOGADORES
    //SEPARADO DO PROGRAMA PRINCIPAL
    //TALVEZ CRIAR OUTRA CLASSE PARA criarJogadores/distribuirCartas ?
    public static void criarJogadores(List<Jogador> jogadores, int qtdJogadores, Scanner entrada)
    {
        //ARMAZENA O NOME FORNECIDO PELO USUARIO
        String nome;
        //INSTANCIA DE CADA NOVO JOGADOR
        Jogador jogador;

        for(int i=0; i< qtdJogadores; i++)
        {
            //LER O NOME
            nome = entrada.next();
            //INSTANCIAR JOGADOR
            jogador = new Jogador(nome);
            //INSTANCIAR LISTA DE CARTAS DO JOGADOR
            jogador.inicializarMaoJogador();
            //INSERIR JOGADOR NA LISTA
            jogadores.add(jogador);
            
        }
    }
}
