package tp2.jogo;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tp2.ambiente.*;
import tp2.cartas.*;

public class Jogo 
{
	public static void main(String[] args) 
    {
        //DECLARAR LISTAS DE CARTAS
        List<Carta> baralho = new ArrayList<>();
        List<Carta> monte = new ArrayList<>();
        List<Carta> lixo = new ArrayList<>();
        //LISTA DE JOGADORES
        List<Jogador> jogadores = new ArrayList<>();
        //SCANNER PARA LER AS ENTRADAS DA LINHA DE COMANDO
        Scanner scanner = new Scanner(System.in);
        //ARMAZENA A ENTRADA DO USUARIO
        String entrada = null;
        //ARMAZENA A QUANTIDADE DE JOGADORES
        int qtdJogadores = 0;
        //INICIALIZAR O BARALHO
        Baralho.inicializarBaralho(baralho);
        //VARIAVEL PARA INSTANCIAR OS JOGADORES
        Jogador jogador;
        //IMPRIMIR TELA DE BOAS VINDAS, E PEDIR DADOS DOS JOGADORES
        UserInterface.boasVindas();
        //GARANTE QUE A ENTRADA ESTEJA DENTRO DOS LIMITES ESPECIFICADOS
        while(true)
        {
            if(scanner.hasNextInt())
            {
                qtdJogadores = scanner.nextInt();
                if(qtdJogadores < Define.MIN_JOGADORES | qtdJogadores > Define.MAX_JOGADORES)
                    //CASO A ENTRADA ESTEJA FORA DOS LIMITES ESPECIFICADOS, IMPRIMIR MENSAGEM DE ERRO
                    UserInterface.erroEntrada();
                else
                    //CASO CONTRARIO, FINALIZE O LOOP
                    break;
            }
            else
            {
                //CASO A ENTRADA NAO CONTENHA INTEIROS
                entrada = scanner.nextLine();
                entrada = null;
                UserInterface.skip();
            	UserInterface.erroEntrada();
            }
                
        }
        //CRIAR JOGADORES (E PERGUNTAR O NOME AO USUARIO)
        for(int i = 0; i < qtdJogadores; i++)
        {
            //PEDE O NOME DO JOGADOR AO USUARIO NA PRIMEIRA ITERACAO
            if(i == 0)
            	UserInterface.digiteJogadores();
            //LER O NOME
            String nome = scanner.next();
            //INSTANCIAR JOGADOR
            jogador = new Jogador(nome);
            //INSTANCIAR LISTA DE CARTAS DO JOGADOR
            jogador.inicializarMaoJogador();
            //INSERIR JOGADOR NA LISTA
            jogadores.add(jogador);
        }
        
        //DISTRIBUI AS CARTAS
        Jogador.distribuirCartas(jogadores, qtdJogadores, monte, baralho);
        
        //IDENTIFICADOR DO JOGADOR ATUAL
        int idJogadorAtual = 0;
        //INSTANCIA DO JOGADOR ATUAL
        Jogador jogadorAtual;
        //BOOLEANOS PARA OS LOOPS DE CONTROLE
        boolean jogo = true;
        boolean turno = true;
        //INDICE PARA AS OPERACOES NAS CARTAS
        int operando;
        
        //"LIMPA" O CONSOLE
        UserInterface.skip();
        //LOOP DE CONTROLE DO JOGO
        while(jogo)
        {
            //VERIFICACOES PARA O NOVO TURNO
            Baralho.verificaMonteVazio(lixo,monte);
            //LOOP DE CONTROLE DO TURNO
            while(turno)
            {
                //ATUALIZA O JOGADOR ATUAL
                jogadorAtual = jogadores.get(idJogadorAtual);
                
                //IMPRIME A UserInterface DO JOGO
                if(jogadorAtual.tamanhoMaoJogador() == 9)
                    UserInterface.imprimirSessao(jogadorAtual);
                //SE O JOGADOR PRECISAR COMPRAR UMA CARTA
                else if(jogadorAtual.tamanhoMaoJogador() == 8)
                    UserInterface.imprimirSessaoCompra(jogadorAtual, lixo.get(lixo.size()-1));
                
                //LE A ENTRADA DO USUARIO
                entrada = scanner.next();
                
                //BATER
                if("B".equals(entrada) | "b".equals(entrada))
                {
                    //
                    UserInterface.selecaoPife();
                    entrada = scanner.next();
                    //QUADRA
                    if("Q".equals(entrada) | "q".equals(entrada))
                    {
                        //ARMAZENA A QUADRA A SER SELECIONADA
                        List<Carta> quadra = new ArrayList<>();
                        //
                        for(int i = 0; i< 4; i++)
                        {
                            UserInterface.digiteCarta();
                            //VERIFICAR SE A ENTRADA E UM INTEIRO
                            if(scanner.hasNextInt())
                            {
                                entrada = scanner.next();
                                operando = Integer.parseInt(entrada);
                                if(Verificadores.verificarEntrada(operando))
                                {
                                    quadra.add(jogadorAtual.retornarCartaJogador(operando -1));
                                    UserInterface.adicionada();
                                }
                                else
                                {
                                    //ENTRADA INVALIDA
                                    UserInterface.erroEntrada();
                                    i--;
                                }
                            }
                        }
                        //
                        if(Verificadores.verificarVitoria(quadra))
                        {
                            //VITORIA
                            UserInterface.imprimirVitoria(jogadorAtual.getNomeJogador(), quadra);
                            //DESATIVAR LOOPS DE CONTROLE PARA ENCERRAR O JOGO
                            turno = false;
                            jogo = false;
                        }
                        else
                        {
                            //CASO A VERIFICACAO FALHE, A VITORIA E ANULADA E O TURNO PASSADO
                            UserInterface.skip();
                            UserInterface.erroVitoria();
                            UserInterface.fimTurno();
                            turno = false;
                        }
                    }
                    //DUAS TRINCAS
                    else if("T".equals(entrada) | "t".equals(entrada))
                    {
                        List<Carta> trinca1 = new ArrayList<>();
                        List<Carta> trinca2 = new ArrayList<>();
                        //PRIMEIRA TRINCA
                        UserInterface.primeiraTrinca();
                        for(int i = 0; i< 3; i++)
                        {
                            UserInterface.digiteCarta();
                            //VERIFICAR SE A ENTRADA E UM INTEIRO
                            if(scanner.hasNextInt())
                            {
                                entrada = scanner.next();
                                operando = Integer.parseInt(entrada);
                                if(Verificadores.verificarEntrada(operando))
                                {
                                    trinca1.add(jogadorAtual.retornarCartaJogador(operando -1));
                                    UserInterface.adicionada();
                                }
                                else
                                {
                                    //ENTRADA INVALIDA
                                    UserInterface.erroEntrada();
                                    i--;
                                }
                            }
                        }
                        //SEGUNDA TRINCA
                        UserInterface.segundaTrinca();
                        for(int i = 0; i< 3; i++)
                        {
                            UserInterface.digiteCarta();
                            //VERIFICAR SE A ENTRADA E UM INTEIRO
                            if(scanner.hasNextInt())
                            {
                                entrada = scanner.next();
                                operando = Integer.parseInt(entrada);
                                if(Verificadores.verificarEntrada(operando))
                                {
                                    trinca2.add(jogadorAtual.retornarCartaJogador(operando -1));
                                    UserInterface.adicionada();
                                }
                                else
                                {
                                    //ENTRADA INVALIDA
                                    UserInterface.erroEntrada();
                                    i--;
                                }
                            }
                        }
                        //VERIFICAR VITORIA
                        if(Verificadores.verificarVitoria(trinca1) && Verificadores.verificarVitoria(trinca2))
                        {
                            //VITORIA
                            UserInterface.imprimirVitoria(jogadorAtual.getNomeJogador(), trinca1, trinca2);
                            //DESATIVAR LOOPS DE CONTROLE PARA ENCERRAR O JOGO
                            turno = false;
                            jogo = false;
                        }
                        else
                        {
                            //VITORIA INVALIDA. PASSAR TURNO
                            UserInterface.skip();
                            UserInterface.erroVitoria();
                            UserInterface.fimTurno();
                            turno = false;
                        }
                    }
                    //SELECAO INVALIDA
                    else
                    {
                        //ENTRADA INVALIDA
                        UserInterface.erroEntrada();
                    }
                }
                //DESCARTAR
                else if("D".equals(entrada) | "d".equals(entrada))
                {
                    //PERGUNTA O USUARIO QUAL A CARTA A SER DESCARTADA
                    UserInterface.selecaoDescarte();
                    entrada = scanner.next();
                    //DETERMINA O OPERANDO
                    operando = Integer.parseInt(entrada);
                    //DETERMINA A INTEGRIDADE DO OPERANDO
                    //CASO SEJA VALIDO REMOVER A CARTA
                    if(Verificadores.verificarEntrada(operando))
                    {
                        //ADICIONAR CARTA AO LIXO
                        lixo.add(jogadorAtual.retornarCartaJogador(operando-1));
                        //REMOVER DA MAO DO JOGADOR
                        jogadorAtual.removerCartaJogador(operando-1);
                        UserInterface.skip();
                        UserInterface.descarte();
                    }
                    else
                    {
                        //ENTRADA INVALIDA
                        UserInterface.erroEntrada();
                    }

                }
                //COMPRAR DO LIXO
                else if("L".equals(entrada) | "l".equals(entrada))
                {
                    //VERIFICA SE O LIXO ESTA VAZIO
                    if(!lixo.isEmpty())
                    {
                        //ADICIONA A PRIMEIRA CARTA DA "PILHA" A MAO DO JOGADOR
                        jogadorAtual.adicionarCartaJogador(lixo.get(lixo.size()-1));
                        //REMOVE A CARTA DO LIXO
                        lixo.remove(lixo.size()-1);
                        //TERMINA O TURNO
                        turno = false;
                        UserInterface.skip();
                        UserInterface.adicionada();
                        UserInterface.fimTurno();
                    }
                    else
                    {
                        //MENSAGEM DE ERRO
                        //LIXO VAZIO
                        UserInterface.erroLixo();
                    }
                }
                //COMPRAR DO monte
                else if("M".equals(entrada) | "m".equals(entrada))
                {
                    //VERIFICA SE O monte ESTA VAZIO
                    if(!monte.isEmpty())
                    {
                        //ADICIONA A PRIMEIRA CARTA DA "PILHA" DO monte A MAO DO JOGADOR
                        jogadorAtual.adicionarCartaJogador(monte.get(monte.size()-1));
                        //REMOVE A CARTA DO monte
                        monte.remove(monte.size()-1);
                        //TERMINA O TURNO
                        turno = false;
                        UserInterface.skip();
                        UserInterface.adicionada();
                        UserInterface.fimTurno();
                    }
                    else
                    {
                        //MENSAGEM DE ERRO
                        //monte VAZIO
                        UserInterface.erroMonte();
                    }
                }
                //PULAR O TURNO
                else if("P".equals(entrada) | "p".equals(entrada))
                {
                    //ANULA O LOOP DE CONTROLE DO TURNO
                    turno = false;
                    //
                    UserInterface.skip();
                    UserInterface.fimTurno();
                }
                else
                {
                    //ENTRADA INVALIDA
                    UserInterface.erroEntrada();
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
