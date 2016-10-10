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
        //CRIAR INSTANCIA SINGLETON DO AMBIENTE DE JOGO
        Ambiente jogo = Ambiente.getInstancia();
        //INICIALIZA O AMBIENTE DE JOGO
        jogo.inicializarAmbiente();
        //SCANNER PARA LER AS ENTRADAS DA LINHA DE COMANDO
        Scanner scanner = new Scanner(System.in);
        //BUILDER PARA INICIALIZAR O BARALHO
        Baralho.inicializarBaralho(jogo);
        //VARIAVEL PARA INSTANCIAR OS JOGADORES
        Jogador jogador;
        
        //IMPRIMIR TELA DE BOAS VINDAS, E PEDIR DADOS DOS JOGADORES
        UserInterface.boasVindas();

        //GARANTE QUE A ENTRADA ESTEJA DENTRO DOS LIMITES ESPECIFICADOS
        while(true)
        {
            if(scanner.hasNextInt())
            {
                jogo.qtdJogadores = scanner.nextInt();
                if(jogo.qtdJogadores < Define.MIN_JOGADORES | jogo.qtdJogadores > Define.MAX_JOGADORES)
                    //CASO A ENTRADA ESTEJA FORA DOS LIMITES ESPECIFICADOS, IMPRIMIR MENSAGEM DE ERRO
                	UserInterface.erroEntrada();
                else
                    //CASO CONTRARIO, FINALIZE O LOOP
                    break;
            }
            else
            {
                //CASO A ENTRADA NAO CONTENHA INTEIROS
                jogo.entrada = scanner.nextLine();
                jogo.entrada = null;
                UserInterface.skip();
            	UserInterface.erroEntrada();
            }
                
        }
        //CRIAR JOGADORES (E PERGUNTAR O NOME AO USUARIO)
        for(int i = 0; i < jogo.qtdJogadores; i++)
        {
            //PEDE O NOME DO JOGADOR AO USUARIO NA PRIMEIRA ITERACAO
            if(i == 0)
            	UserInterface.digiteJogadores();
            //LER O NOME
            jogo.entrada = scanner.next();
            //INSTANCIAR JOGADOR
            jogador = new Jogador(jogo.entrada);
            //INSTANCIAR LISTA DE CARTAS DO JOGADOR
            jogador.inicializarMaoJogador();
            //INSERIR JOGADOR NA LISTA
            jogo.jogadores.add(jogador);
        }
        
        //DISTRIBUI AS CARTAS
        Ambiente.distribuirCartas(jogo);
        
        //"LIMPA" O CONSOLE E O LEITOR DE ENTRADA
        UserInterface.skip();
        jogo.entrada = scanner.nextLine();
        jogo.entrada = null;
        
        //LOOP DE CONTROLE DO JOGO
        while(jogo.jogo)
        {
            //VERIFICACOES PARA O NOVO TURNO
            Baralho.verificaMonteVazio(jogo.lixo,jogo.monte);
            //LOOP DE CONTROLE DO TURNO
            while(jogo.turno)
            {
                //ATUALIZA O JOGADOR ATUAL
                jogo.jogadorAtual = jogo.jogadores.get(jogo.idJogadorAtual);
                
                //IMPRIME A INTERFACE DO JOGO
                if(jogo.jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
                	UserInterface.imprimirSessao(jogo.jogadorAtual);
                //SE O JOGADOR PRECISAR COMPRAR UMA CARTA
                else if(jogo.jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
                	UserInterface.imprimirSessaoCompra(jogo.jogadorAtual, jogo.lixo.get(jogo.lixo.size()-1));
                
                //LE A ENTRADA DO USUARIO
                jogo.entrada = scanner.next();
                
                //BATER
                if("B".equals(jogo.entrada) | "b".equals(jogo.entrada))
                {
                    //
                	UserInterface.selecaoPife();
                    jogo.entrada = scanner.next();
                    //QUADRA
                    if("Q".equals(jogo.entrada) | "q".equals(jogo.entrada))
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
                                jogo.entrada = scanner.next();
                                jogo.operador = Integer.parseInt(jogo.entrada);
                                if(Verificadores.verificarEntrada(jogo.operador))
                                {
                                    quadra.add(jogo.jogadorAtual.retornarCartaJogador(jogo.operador -1));
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
                        	UserInterface.imprimirVitoria(jogo.jogadorAtual.getNomeJogador(), quadra);
                            //DESATIVAR LOOPS DE CONTROLE PARA ENCERRAR O JOGO
                            jogo.turno = false;
                            jogo.jogo = false;
                        }
                        else
                        {
                            //CASO A VERIFICACAO FALHE, A VITORIA E ANULADA E O TURNO PASSADO
                        	UserInterface.skip();
                        	UserInterface.erroVitoria();
                        	UserInterface.fimTurno();
                            jogo.turno = false;
                        }
                    }
                    //DUAS TRINCAS
                    else if("T".equals(jogo.entrada) | "t".equals(jogo.entrada))
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
                                jogo.entrada = scanner.next();
                                jogo.operador = Integer.parseInt(jogo.entrada);
                                if(Verificadores.verificarEntrada(jogo.operador))
                                {
                                    trinca1.add(jogo.jogadorAtual.retornarCartaJogador(jogo.operador -1));
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
                                jogo.entrada = scanner.next();
                                jogo.operador = Integer.parseInt(jogo.entrada);
                                if(Verificadores.verificarEntrada(jogo.operador))
                                {
                                    trinca2.add(jogo.jogadorAtual.retornarCartaJogador(jogo.operador -1));
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
                        	UserInterface.imprimirVitoria(jogo.jogadorAtual.getNomeJogador(), trinca1, trinca2);
                            //DESATIVAR LOOPS DE CONTROLE PARA ENCERRAR O JOGO
                            jogo.turno = false;
                            jogo.jogo = false;
                        }
                        else
                        {
                            //VITORIA INVALIDA. PASSAR TURNO
                        	UserInterface.skip();
                        	UserInterface.erroVitoria();
                        	UserInterface.fimTurno();
                            jogo.turno = false;
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
                else if("D".equals(jogo.entrada) | "d".equals(jogo.entrada))
                {
                    //PERGUNTA O USUARIO QUAL A CARTA A SER DESCARTADA
                	UserInterface.selecaoDescarte();
                    jogo.entrada = scanner.next();
                    //DETERMINA O OPERANDO
                    jogo.operador = Integer.parseInt(jogo.entrada);
                    //DETERMINA A INTEGRIDADE DO OPERANDO
                    //CASO SEJA VALIDO REMOVER A CARTA
                    if(Verificadores.verificarEntrada(jogo.operador))
                    {
                        //ADICIONAR CARTA AO LIXO
                        jogo.lixo.add(jogo.jogadorAtual.retornarCartaJogador(jogo.operador-1));
                        //REMOVER DA MAO DO JOGADOR
                        jogo.jogadorAtual.removerCartaJogador(jogo.operador-1);
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
                else if("L".equals(jogo.entrada) | "l".equals(jogo.entrada))
                {
                    //VERIFICA SE O LIXO ESTA VAZIO
                    if(!jogo.lixo.isEmpty())
                    {
                        //ADICIONA A PRIMEIRA CARTA DA "PILHA" A MAO DO JOGADOR
                        jogo.jogadorAtual.adicionarCartaJogador(jogo.lixo.get(jogo.lixo.size()-1));
                        //REMOVE A CARTA DO LIXO
                        jogo.lixo.remove(jogo.lixo.size()-1);
                        //TERMINA O TURNO
                        jogo.turno = false;
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
                //COMPRAR DO MONTE
                else if("M".equals(jogo.entrada) | "m".equals(jogo.entrada))
                {
                    //VERIFICA SE O MONTE ESTA VAZIO
                    if(!jogo.monte.isEmpty())
                    {
                        //ADICIONA A PRIMEIRA CARTA DA "PILHA" DO MONTE A MAO DO JOGADOR
                        jogo.jogadorAtual.adicionarCartaJogador(jogo.monte.get(jogo.monte.size()-1));
                        //REMOVE A CARTA DO MONTE
                        jogo.monte.remove(jogo.monte.size()-1);
                        //TERMINA O TURNO
                        jogo.turno = false;
                        UserInterface.skip();
                        UserInterface.adicionada();
                        UserInterface.fimTurno();
                    }
                    else
                    {
                        //MENSAGEM DE ERRO
                        //MONTE VAZIO
                    	UserInterface.erroMonte();
                    }
                }
                //PULAR O TURNO
                else if("P".equals(jogo.entrada) | "p".equals(jogo.entrada))
                {
                    //ANULA O LOOP DE CONTROLE DO TURNO
                    jogo.turno = false;
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
            jogo.idJogadorAtual++;
            //CASO O IDENTIFICADOR TENHA EXCEDIDO A QUANTIDADE DE JOGADORES
            //RESETAR O IDENTIFICADOR
            if(jogo.idJogadorAtual == jogo.qtdJogadores)
                jogo.idJogadorAtual = 0;
            //VALIDA O LOOP DE CONTROLE DO TURNO
            jogo.turno = true;
        }
        //IMPORTANTE NOTAR QUE CADA CARTA DO BARALHO SOMENTE TERA UMA INSTANCIA
        //AS CARTAS SERAO INCLUIDAS E REMOVIDAS POR REFERENCIA NA EXECUCAO
    }
    
}
