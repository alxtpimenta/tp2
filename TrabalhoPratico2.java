/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.pratico.pkg2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import trabalho.pratico.pkg2.core.Ambiente;
import trabalho.pratico.pkg2.core.Define;
import trabalho.pratico.pkg2.core.Procedimentos;
import trabalho.pratico.pkg2.modelagem.Carta;
import trabalho.pratico.pkg2.modelagem.Jogador;
import trabalho.pratico.pkg2.ui.Interface;

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
        //CRIAR INSTANCIA SINGLETON DO AMBIENTE DE JOGO
        Ambiente jogo = Ambiente.retornarInstancia();
        //INICIALIZA O AMBIENTE DE JOGO
        jogo.inicializar();
        //SCANNER PARA LER AS ENTRADAS DA LINHA DE COMANDO
        Scanner scanner = new Scanner(System.in);
        //BUILDER PARA INICIALIZAR O BARALHO
        Procedimentos.inicializarBaralho(jogo);
        //VARIAVEL PARA INSTANCIAR OS JOGADORES
        Jogador jogador;
        
        //IMPRIMIR TELA DE BOAS VINDAS, E PEDIR DADOS DOS JOGADORES
        Interface.boasVindas();

        //GARANTE QUE A ENTRADA SERA MAIR QUE DOIS E MENOR QUE A 4
        while(true)
        {
            if(scanner.hasNextInt())
            {
                jogo.qtdJogadores = scanner.nextInt();
                if(jogo.qtdJogadores < Define.MIN_JOGADORES | jogo.qtdJogadores > Define.MAX_JOGADORES)
                    //CASO A ENTRADA ESTEJA FORA DOS LIMITES ESPECIFICADOS, IMPRIMIR MENSAGEM DE ERRO
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
        for(int i = 0; i < jogo.qtdJogadores; i++)
        {
            //PEDE O NOME DO JOGADOR AO USUARIO NA PRIMEIRA ITERACAO
            if(i == 0)
                Interface.digiteJogadores();
            //LER O NOME
            jogo.entrada = scanner.next();
            //INSTANCIAR JOGADOR
            jogador = new Jogador(jogo.entrada);
            //INSTANCIAR LISTA DE CARTAS DO JOGADOR
            jogador.inicializar();
            //INSERIR JOGADOR NA LISTA
            jogo.Jogadores.add(jogador);
        }
        
        //DISTRIBUI AS CARTAS
        Procedimentos.distribuirCartas(jogo);
        
        //"LIMPA" O CONSOLE E O LEITOR DE ENTRADA
        Interface.skip();
        jogo.entrada = scanner.nextLine();
        jogo.entrada = null;
        
        //LOOP DE CONTROLE DO JOGO
        while(jogo.jogo)
        {
            //VERIFICACOES PARA O NOVO TURNO
            Procedimentos.verificarTurno(jogo.Lixo,jogo.Monte);
            //LOOP DE CONTROLE DO TURNO
            while(jogo.turno)
            {
                //ATUALIZA O JOGADOR ATUAL
                jogo.jogadorAtual = jogo.Jogadores.get(jogo.idJogadorAtual);
                
                //IMPRIME A INTERFACE DO JOGO
                if(jogo.jogadorAtual.tamanho() == Define.MAX_MAO)
                    Interface.imprimirSessao(jogo.jogadorAtual);
                //SE O JOGADOR PRECISAR COMPRAR UMA CARTA
                else if(jogo.jogadorAtual.tamanho() == Define.MIN_MAO)
                    Interface.imprimirSessaoCompra(jogo.jogadorAtual, jogo.Lixo.get(jogo.Lixo.size()-1));
                
                //LE A ENTRADA DO USUARIO
                jogo.entrada = scanner.next();
                
                //BATER
                if("B".equals(jogo.entrada) | "b".equals(jogo.entrada))
                {
                    //
                    Interface.selecaoPife();
                    jogo.entrada = scanner.next();
                    //QUADRA
                    if("Q".equals(jogo.entrada) | "q".equals(jogo.entrada))
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
                                jogo.entrada = scanner.next();
                                jogo.operador = Integer.parseInt(jogo.entrada);
                                if(Procedimentos.verificarEntrada(jogo.operador))
                                {
                                    quadra.add(jogo.jogadorAtual.retornarCarta(jogo.operador -1));
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
                            //VITORIA
                            Interface.imprimirVitoria(jogo.jogadorAtual.nome(), quadra);
                            //DESATIVAR LOOPS DE CONTROLE PARA ENCERRAR O JOGO
                            jogo.turno = false;
                            jogo.jogo = false;
                        }
                        else
                        {
                            //CASO A VERIFICACAO FALHE, A VITORIA E ANULADA E O TURNO PASSADO
                            Interface.skip();
                            Interface.erroVitoria();
                            Interface.fimTurno();
                            jogo.turno = false;
                        }
                    }
                    //DUAS TRINCAS
                    else if("T".equals(jogo.entrada) | "t".equals(jogo.entrada))
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
                                jogo.entrada = scanner.next();
                                jogo.operador = Integer.parseInt(jogo.entrada);
                                if(Procedimentos.verificarEntrada(jogo.operador))
                                {
                                    trinca1.add(jogo.jogadorAtual.retornarCarta(jogo.operador -1));
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
                                jogo.entrada = scanner.next();
                                jogo.operador = Integer.parseInt(jogo.entrada);
                                if(Procedimentos.verificarEntrada(jogo.operador))
                                {
                                    trinca2.add(jogo.jogadorAtual.retornarCarta(jogo.operador -1));
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
                            Interface.imprimirVitoria(jogo.jogadorAtual.nome(), trinca1, trinca2);
                            //DESATIVAR LOOPS DE CONTROLE PARA ENCERRAR O JOGO
                            jogo.turno = false;
                            jogo.jogo = false;
                        }
                        else
                        {
                            //VITORIA INVALIDA. PASSAR TURNO
                            Interface.skip();
                            Interface.erroVitoria();
                            Interface.fimTurno();
                            jogo.turno = false;
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
                else if("D".equals(jogo.entrada) | "d".equals(jogo.entrada))
                {
                    //PERGUNTA O USUARIO QUAL A CARTA A SER DESCARTADA
                    Interface.selecaoDescarte();
                    jogo.entrada = scanner.next();
                    //DETERMINA O OPERANDO
                    jogo.operador = Integer.parseInt(jogo.entrada);
                    //DETERMINA A INTEGRIDADE DO OPERANDO
                    //CASO SEJA VALIDO REMOVER A CARTA
                    if(Procedimentos.verificarEntrada(jogo.operador))
                    {
                        //ADICIONAR CARTA AO LIXO
                        jogo.Lixo.add(jogo.jogadorAtual.retornarCarta(jogo.operador-1));
                        //REMOVER DA MAO DO JOGADOR
                        jogo.jogadorAtual.removerCarta(jogo.operador-1);
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
                else if("L".equals(jogo.entrada) | "l".equals(jogo.entrada))
                {
                    //VERIFICA SE O LIXO ESTA VAZIO
                    if(!jogo.Lixo.isEmpty())
                    {
                        //ADICIONA A PRIMEIRA CARTA DA "PILHA" A MAO DO JOGADOR
                        jogo.jogadorAtual.adicionarCarta(jogo.Lixo.get(jogo.Lixo.size()-1));
                        //REMOVE A CARTA DO LIXO
                        jogo.Lixo.remove(jogo.Lixo.size()-1);
                        //TERMINA O TURNO
                        jogo.turno = false;
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
                else if("M".equals(jogo.entrada) | "m".equals(jogo.entrada))
                {
                    //VERIFICA SE O MONTE ESTA VAZIO
                    if(!jogo.Monte.isEmpty())
                    {
                        //ADICIONA A PRIMEIRA CARTA DA "PILHA" DO MONTE A MAO DO JOGADOR
                        jogo.jogadorAtual.adicionarCarta(jogo.Monte.get(jogo.Monte.size()-1));
                        //REMOVE A CARTA DO MONTE
                        jogo.Monte.remove(jogo.Monte.size()-1);
                        //TERMINA O TURNO
                        jogo.turno = false;
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
                else if("P".equals(jogo.entrada) | "p".equals(jogo.entrada))
                {
                    //ANULA O LOOP DE CONTROLE DO TURNO
                    jogo.turno = false;
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
