/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.pratico.pkg2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alexandre
 */

public class TrabalhoPratico2 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //DECLARAR LISTAS DE CARTAS
        List<Carta> Baralho = new ArrayList<>();
        List<Carta> Monte = new ArrayList<>();
        List<Carta> Lixo = new ArrayList<>();
        //LISTA DE JOGADORES
        List<Jogador> Jogadores = new ArrayList<>();
        //SCANNER PARA LER AS ENTRADAS DA LINHA DE COMANDO
        Scanner scanner = new Scanner(System.in);
        //INICIALIZAR O BARALHO
        Procedimentos.inicializarBaralho(Baralho);
        //VARIAVEL PARA INSTANCIAR OS JOGADORES
        Jogador jogador;
        //IMPRIMIR TELA DE BOAS VINDAS, E PEDIR DADOS DOS JOGADORES
        System.out.println("Bem vindo(a) a partida de Pife!");
        System.out.println("Por favor, insira a quantidade de jogadores.");
        System.out.println("*Minimo de 2 e maximo de 4 jogadores por partida*");
        //VARIAVEL PARA ARMAZENAR A QUANTIDADE DE JOGADORES
        int qtdJogadores = 0;
        //LOOP PARA GARANTIR QUE A ENTRADA SERA MAIR QUE DOIS E MENOR QUE A 4
        //COMO A VARIAVEL FOI INICIALIZADA COM 0, O LOOP IRA EXECUTAR
        while(qtdJogadores <= 4 && qtdJogadores >=2)
        {
            qtdJogadores = scanner.nextInt();
            //CASO A ENTRADA SEJA MENOR QUE DOIS OU MAIOR QUE QUATRO, IMPRIMIR MENSAGEM DE ERRO
            if(qtdJogadores < 2 | qtdJogadores > 4)
                System.out.println("Erro: Quantidade de jogadores invalida! Tente novamente.");
        }
        //IDENTIFICADOR UNICO PARA CADA JOGADOR
        int ID = 0;
        //CRIAR JOGADORES (E PERGUNTAR O NOME AO USUARIO)
        for(int i = 0; i < qtdJogadores; i++)
        {
            //PEDE O NOME DO JOGADOR AO USUARIO
            System.out.println("Digite o nome do jogador " + Integer.toString(i +1));
            //LER O NOME
            String nome = scanner.next();
            //INSTANCIAR JOGADOR
            jogador = new Jogador(nome, ID);
            //INSERIR JOGADOR NA LISTA
            Jogadores.add(jogador);
            //INCREMENTA O IDENTIFICADOR
            ID++;
        }
        //DEPOIS DAR 9 CARTAS A CADA JOGADOR
        //AS CARTAS JA FORAM EMBARALHADAS NA INICIALIZACAO
        //VARIAVEIS PARA INDICAR OS INDICES PARA A INSERCAO
        int indice = 0;
        int fim = 9;
        //INSERIR CARTAS
        for(int i = 0; i < qtdJogadores; i++)
        {
            for(; indice < fim; indice ++)
            {
                Jogadores.get(i).mao.add(Baralho.get(indice));
            }
            //INCREMENTAR O FIM (CADA JOGADOR RECEBE NOVE CARTAS)
            fim = fim + 9;
        }
        //O RESTANTE DAS CARTAS SERAO INSERIDAS NO MONTE
        for(; indice < Baralho.size(); indice ++)
        {
            Monte.add(Baralho.get(indice));
        }
        //IMPORTANTE NOTAR QUE CADA CARTA DO BARALHO SOMENTE TERA UMA INSTANCIA
        //AS CARTAS SERAO INCLUIDAS E REMOVIDAS POR REFERENCIA NA EXECUCAO
        
        
    }
    
}
