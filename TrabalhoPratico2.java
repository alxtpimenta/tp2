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
        Interface.boasVindas();
        //ARMAZENA A QUANTIDADE DE JOGADORES
        int qtdJogadores = 0;
        //LOOP PARA GARANTIR QUE A ENTRADA SERA MAIR QUE DOIS E MENOR QUE A 4
        while(true)
        {
            if(scanner.hasNextInt())
            {
                qtdJogadores = scanner.nextInt();
                if(qtdJogadores < 2 | qtdJogadores > 4)
                    //CASO A ENTRADA SEJA MENOR QUE DOIS OU MAIOR QUE QUATRO, IMPRIMIR MENSAGEM DE ERRO
                    Interface.erroEntrada();
                else
                    //CASO CONTRARIO, FINALIZE O LOOP
                    break;
            }
            else
            {
                //CASO A ENTRADA NAO CONTENHA INTEIROS
                Interface.erroEntrada();
                scanner.close();
            }
                
        }
        //CRIAR JOGADORES (E PERGUNTAR O NOME AO USUARIO)
        for(int i = 0; i < qtdJogadores; i++)
        {
            //PEDE O NOME DO JOGADOR AO USUARIO
            Interface.digiteJogador(i +1);
            //LER O NOME
            String nome = scanner.next();
            //INSTANCIAR JOGADOR
            jogador = new Jogador(nome);
            //INSTANCIAR LISTA DE CARTAS DO JOGADOR
            jogador.inicializar();
            //INSERIR JOGADOR NA LISTA
            Jogadores.add(jogador);
        }
        
        //DISTRIBUI AS CARTAS
        Procedimentos.distribuirCartas(Jogadores, qtdJogadores, Monte, Baralho);
        
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
            //VERIFICACOES PARA O NOVO TURNO
            Procedimentos.verificarTurno(Lixo,Monte);
            //LOOP DE CONTROLE DO TURNO
            while(turno)
            {
                //ATUALIZA O JOGADOR ATUAL
                jogadorAtual = Jogadores.get(idJogadorAtual);
                
                //IMPRIME A INTERFACE DO JOGO
                if(jogadorAtual.tamanho() == 9)
                    Interface.imprimirSessao(jogadorAtual);
                //SE O JOGADOR PRECISAR COMPRAR UMA CARTA
                else if(jogadorAtual.tamanho() == 8)
                    Interface.imprimirSessaoCompra(jogadorAtual, Lixo.get(Lixo.size()-1));
                
                //LE A ENTRADA DO USUARIO
                entrada = scanner.next();
                
                //BATER
                if("B".equals(entrada) | "b".equals(entrada))
                {
                    //
                    Interface.selecaoPife();
                    entrada = scanner.next();
                    //QUADRA
                    if("Q".equals(entrada) | "q".equals(entrada))
                    {
                        //ARMAZENA A QUADRA A SER SELECIONADA
                        List<Carta> quadra = new ArrayList<>();
                        //
                        for(int i = 0; i< 4; i++)
                        {
                            Interface.digiteCarta();
                            //VERIFICAR SE A ENTRADA E UM INTEIRO
                            if(scanner.hasNextInt())
                            {
                                entrada = scanner.next();
                                operando = Integer.parseInt(entrada);
                                if(Procedimentos.verificarEntrada(operando))
                                {
                                    quadra.add(jogadorAtual.retornarCarta(operando -1));
                                    Interface.adicionada();
                                }
                                else
                                {
                                    //ENTRADA INVALIDA
                                    Interface.erroEntrada();
                                    i--;
                                }
                            }
                        }
                        //
                        if(Procedimentos.verificarVitoria(quadra))
                        {
                            //CASO A VERIFICACAO SEJA TRUE, A VITORIA E CONFIRMADA
                            //VITORIA
                        }
                        else
                        {
                            //CASO A VERIFICACAO FALHE, A VITORIA E ANULADA E O TURNO PASSADO
                            Interface.skip();
                            Interface.erroVitoria();
                            Interface.fimTurno();
                            turno = false;
                        }
                    }
                    //DUAS TRINCAS
                    else if("T".equals(entrada) | "t".equals(entrada))
                    {
                        List<Carta> trinca1 = new ArrayList<>();
                        List<Carta> trinca2 = new ArrayList<>();
                        //PRIMEIRA TRINCA
                        Interface.primeiraTrinca();
                        for(int i = 0; i< 3; i++)
                        {
                            Interface.digiteCarta();
                            //VERIFICAR SE A ENTRADA E UM INTEIRO
                            if(scanner.hasNextInt())
                            {
                                entrada = scanner.next();
                                operando = Integer.parseInt(entrada);
                                if(Procedimentos.verificarEntrada(operando))
                                {
                                    trinca1.add(jogadorAtual.retornarCarta(operando -1));
                                    Interface.adicionada();
                                }
                                else
                                {
                                    //ENTRADA INVALIDA
                                    Interface.erroEntrada();
                                    i--;
                                }
                            }
                        }
                        //SEGUNDA TRINCA
                        Interface.segundaTrinca();
                        for(int i = 0; i< 3; i++)
                        {
                            Interface.digiteCarta();
                            //VERIFICAR SE A ENTRADA E UM INTEIRO
                            if(scanner.hasNextInt())
                            {
                                entrada = scanner.next();
                                operando = Integer.parseInt(entrada);
                                if(Procedimentos.verificarEntrada(operando))
                                {
                                    trinca2.add(jogadorAtual.retornarCarta(operando -1));
                                    Interface.adicionada();
                                }
                                else
                                {
                                    //ENTRADA INVALIDA
                                    Interface.erroEntrada();
                                    i--;
                                }
                            }
                        }
                        //VERIFICAR VITORIA
                        if(Procedimentos.verificarVitoria(trinca1) && Procedimentos.verificarVitoria(trinca2))
                        {
                            //VITORIA
                        }
                        else
                        {
                            //VITORIA INVALIDA. PASSAR TURNO
                            Interface.skip();
                            Interface.erroVitoria();
                            Interface.fimTurno();
                            turno = false;
                        }
                    }
                    //SELECAO INVALIDA
                    else
                    {
                        //ENTRADA INVALIDA
                        Interface.erroEntrada();
                    }
                }
                //DESCARTAR
                else if("D".equals(entrada) | "d".equals(entrada))
                {
                    //PERGUNTA O USUARIO QUAL A CARTA A SER DESCARTADA
                    Interface.selecaoDescarte();
                    entrada = scanner.next();
                    //DETERMINA O OPERANDO
                    operando = Integer.parseInt(entrada);
                    //DETERMINA A INTEGRIDADE DO OPERANDO
                    //CASO SEJA VALIDO REMOVER A CARTA
                    if(Procedimentos.verificarEntrada(operando))
                    {
                        //ADICIONAR CARTA AO LIXO
                        Lixo.add(jogadorAtual.retornarCarta(operando-1));
                        //REMOVER DA MAO DO JOGADOR
                        jogadorAtual.removerCarta(operando-1);
                        Interface.skip();
                        Interface.descarte();
                    }
                    else
                    {
                        //ENTRADA INVALIDA
                        Interface.erroEntrada();
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
                        Interface.adicionada();
                        Interface.fimTurno();
                    }
                    else
                    {
                        //MENSAGEM DE ERRO
                        //LIXO VAZIO
                        Interface.erroLixo();
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
                        Interface.adicionada();
                        Interface.fimTurno();
                    }
                    else
                    {
                        //MENSAGEM DE ERRO
                        //MONTE VAZIO
                        Interface.erroMonte();
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
                else
                {
                    //ENTRADA INVALIDA
                    Interface.erroEntrada();
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
