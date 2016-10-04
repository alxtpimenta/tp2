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
        System.out.println("-> Insira a quantidade de jogadores");
        System.out.println("(Minimo de 2 e maximo de 4 jogadores por partida. Somente numeros.)");
        //VARIAVEL PARA ARMAZENAR A QUANTIDADE DE JOGADORES
        int qtdJogadores = 0;
        //LOOP PARA GARANTIR QUE A ENTRADA SERA MAIR QUE DOIS E MENOR QUE A 4
        while(true)
        {
            if(scanner.hasNextInt())
            {
                qtdJogadores = scanner.nextInt();
                if(qtdJogadores < 2 | qtdJogadores > 4)
                    //CASO A ENTRADA SEJA MENOR QUE DOIS OU MAIOR QUE QUATRO, IMPRIMIR MENSAGEM DE ERRO
                    System.out.println("!!! Erro: Quantidade de jogadores invalida! Tente novamente. !!!");
                else
                    //CASO CONTRARIO, FINALIZE O LOOP
                    break;
            }
            else
            {
                //CASO A ENTRADA NAO CONTENHA INTEIROS
                System.out.println("!!! Erro: Entrada Invalida! Somente numeros sao aceitos! !!!");
                scanner.close();
            }
                
        }
        //CRIAR JOGADORES (E PERGUNTAR O NOME AO USUARIO)
        for(int i = 0; i < qtdJogadores; i++)
        {
            //PEDE O NOME DO JOGADOR AO USUARIO
            System.out.println("-> Digite o nome do jogador " + Integer.toString(i +1));
            //LER O NOME
            String nome = scanner.next();
            //INSTANCIAR JOGADOR
            jogador = new Jogador(nome);
            //INSTANCIAR LISTA DE CARTAS DO JOGADOR
            jogador.inicializar();
            //INSERIR JOGADOR NA LISTA
            Jogadores.add(jogador);
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
                //ADICIONA A CARTA DO BARALHO A MAO DO JOGADOR
                Jogadores.get(i).adicionarCarta(Baralho.get(indice));
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
        
        //IDENTIFICADOR DO JOGADOR ATUAL
        int idJogadorAtual = 0;
        //INSTANCIA DO JOGADOR ATUAL
        Jogador jogadorAtual;
        //BOOLEANOS PARA OS LOOPS DE CONTROLE
        boolean jogo = true;
        boolean turno = true;
        //ARMAZENA A ENTRADA DO USUARIO
        String entrada;
        //INDICE PARA AS OPERACOES NAS CARTAS
        int operando;
        
        //"LIMPA" O CONSOLE
        Interface.skip();
        //LOOP DE CONTROLE DO JOGO
        while(jogo)
        {
            //LOOP DE CONTROLE DO TURNO
            while(turno)
            {
                //ATUALIZA O JOGADOR ATUAL
                jogadorAtual = Jogadores.get(idJogadorAtual);
                
                //IMPRIME A INTERFACE DO JOGO
                //A IMPRESSAO VARIA DE ACORDO COM A QUANTIDADE DE CARTAS NA MAO DO JOGADOR
                if(jogadorAtual.tamanho() == 9)
                    Interface.imprimirSessao9(jogadorAtual);
                else if(jogadorAtual.tamanho() == 8)
                    Interface.imprimirSessao8(jogadorAtual);
                
                //LE A ENTRADA DO USUARIO
                entrada = scanner.next();
                
                //BATER
                if("B".equals(entrada) | "b".equals(entrada))
                {
                    //NAO IMPLEMENTADO
                    List<Carta> pife = new ArrayList<>();
                    Interface.quantidadePife();
                    int quantidade = 0;
                    if(scanner.hasNextInt())
                    {
                        quantidade = scanner.nextInt();
                        if(qtdJogadores < 3 | qtdJogadores > 4)
                        //CASO A ENTRADA SEJA MENOR QUE DOIS OU MAIOR QUE QUATRO, IMPRIMIR MENSAGEM DE ERRO
                        System.out.println("!!! Erro: Quantidade de jogadores invalida! Tente novamente. !!!");
                        else
                        //CASO CONTRARIO, FINALIZE O LOOP
                        break;
                    }
                    else
                    {
                        //CASO A ENTRADA NAO CONTENHA INTEIROS
                        System.out.println("!!! Erro: Entrada Invalida! Somente numeros sao aceitos! !!!");
                        scanner.close();
                    }
                    //
                    Interface.selecaoPife();
                    //
                    for(int i = 0; i < quantidade; i++)
                    {
                        Interface.selecaoCarta();
                    }
                    System.out.println("-------**NOT YET IMPLEMENTED**-------");
                    turno = false;
                }
                //DESCARTAR
                else if("D".equals(entrada) | "d".equals(entrada))
                {
                    //PERGUNTA O USUARIO QUAL A CARTA A SER DESCARTADA
                    Interface.selecaoDescarte();
                    entrada = scanner.next();
                    //DETERMINA O OPERANDO
                    operando = Integer.parseInt(entrada) -1;
                    //DETERMINA A INTEGRIDADE DO OPERANDO
                    //CASO SEJA VALIDO REMOVER A CARTA
                    if(operando >= 0 && operando <= 8)
                    {
                        //ADICIONAR CARTA AO LIXO
                        Lixo.add(jogadorAtual.retornarCarta(operando));
                        //REMOVER DA MAO DO JOGADOR
                        jogadorAtual.removerCarta(operando);
                        Interface.skip();
                        Interface.descarte();
                    }
                    else
                    {
                        //MENSAGEM DE ERRO
                        System.out.println("!!! Entrada invalida! !!!");
                    }

                }
                //COMPRAR DO LIXO
                else if("L".equals(entrada) | "l".equals(entrada))
                {
                    //VERIFICA SE O LIXO ESTA VAZIO
                    if(!Lixo.isEmpty())
                    {
                        //ADICIONA A PRIMEIRA CARTA DA "PILHA" A MAO DO JOGADOR
                        jogadorAtual.adicionarCarta(Lixo.get(Lixo.size()-1));
                        //REMOVE A CARTA DO LIXO
                        Lixo.remove(Lixo.size()-1);
                        //TERMINA O TURNO
                        turno = false;
                        Interface.skip();
                        Interface.compra();;
                        Interface.fimTurno();
                    }
                    else
                    {
                        //MENSAGEM DE ERRO
                        System.out.println("!!! O lixo esta vazio! !!!");
                    }
                }
                //COMPRAR DO MONTE
                else if("M".equals(entrada) | "m".equals(entrada))
                {
                    //VERIFICA SE O MONTE ESTA VAZIO
                    if(!Monte.isEmpty())
                    {
                        //ADICIONA A PRIMEIRA CARTA DA "PILHA" DO MONTE A MAO DO JOGADOR
                        jogadorAtual.adicionarCarta(Monte.get(Monte.size()-1));
                        //REMOVE A CARTA DO MONTE
                        Monte.remove(Monte.size()-1);
                        //TERMINA O TURNO
                        turno = false;
                        Interface.skip();
                        Interface.compra();
                        Interface.fimTurno();
                    }
                    else
                    {
                        //MENSAGEM DE ERRO
                        System.out.println("!!! O monte esta vazio! !!!");
                    }
                }
                //PULAR O TURNO
                else if("P".equals(entrada) | "p".equals(entrada))
                {
                    //ANULA O LOOP DE CONTROLE DO TURNO
                    turno = false;
                    //
                    Interface.skip();
                    Interface.fimTurno();
                }
            }
            //INCREMENTA O IDENTIFICADOR DO JOGADOR ATUAL
            idJogadorAtual++;
            //CASO O IDENTIFICADOR TENHA EXCEDIDO A QUANTIDADE DE JOGADORES
            //RESETAR O IDENTIFICADOR
            if(idJogadorAtual == qtdJogadores)
                idJogadorAtual = 0;
            //VALIDA O LOOP DE CONTROLE DO TURNO
            turno = true;
        }
        //IMPORTANTE NOTAR QUE CADA CARTA DO BARALHO SOMENTE TERA UMA INSTANCIA
        //AS CARTAS SERAO INCLUIDAS E REMOVIDAS POR REFERENCIA NA EXECUCAO
        
        
    }
    
}
