/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.jogo;

import java.util.ArrayList;
import java.util.List;
import tp2.ambiente.Jogador;
import tp2.ambiente.Verificadores;
import tp2.cartas.Carta;

/**
 *
 * @author alexandre
 */
public class Procedimentos 
{
    //ADICIONA AS CARTAS SELECIONADAS NA LISTA REFERENCIADA
    public static void selecionarCartas(List<Carta> selecao,Sessao jogo, int quantidade)
    {
        UserInterface.digiteCarta();
        //
        String entrada;
        int operando;
        for(int i = 0; i < quantidade; i++)
        {
            //CERTIFICA QUE A ENTRADA E UM INTEIRO
            if(jogo.scanner.hasNextInt())
            {
                entrada = jogo.scanner.next();
                operando = Integer.parseInt(entrada);
                if(Verificadores.verificarEntrada(operando))
                {
                    selecao.add(jogo.jogadorAtual.retornarCartaJogador(operando -1));
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
    }
    
    //COMPRA UMA CARTA DA FONTE
    //A FONTE PODE SER LIXO OU MONTE
    public static void comprarCarta(Jogador jogadorAtual, List<Carta> fonte)
    {
        //VERIFICA SE A FONTE ESTA VAZIA
        if(!fonte.isEmpty())
        {
            //ADICIONA A PRIMEIRA CARTA DA "PILHA" A MAO DO JOGADOR
            jogadorAtual.adicionarCartaJogador(fonte.get(fonte.size()-1));
            //REMOVE A CARTA DA FONTE
            fonte.remove(fonte.size()-1);
        }
        else
        {
            //MENSAGEM DE ERRO
            UserInterface.erroLixo();
        }
    }
    
    //PASSA O TURNO
    public static void anularTurno(Sessao jogo)
    {
        //
        jogo.turno = false;
        //
        UserInterface.skip();
        UserInterface.fimTurno();
    }
    
    //ENCERRA O JOGO
    public static void anularJogo(Sessao jogo)
    {
        //
        jogo.turno = false;
        jogo.jogo = false;
    }
    
    //DESCARTA A CARTA SELECIONADA
    public static void descartarCarta(Sessao jogo)
    {
        //PERGUNTA O USUARIO QUAL A CARTA A SER DESCARTADA
        UserInterface.selecaoDescarte();
        jogo.entrada = jogo.scanner.next();
        //DETERMINA O OPERANDO
        jogo.operando = Integer.parseInt(jogo.entrada);
        //DETERMINA A INTEGRIDADE DO OPERANDO
        //CASO SEJA VALIDO REMOVER A CARTA
        if(Verificadores.verificarEntrada(jogo.operando))
        {
            //ADICIONAR CARTA AO LIXO
            jogo.lixo.add(jogo.jogadorAtual.retornarCartaJogador(jogo.operando-1));
            //REMOVER DA MAO DO JOGADOR
            jogo.jogadorAtual.removerCartaJogador(jogo.operando-1);
            //GERA SAIDA PARA A INTERFACE
            UserInterface.descarte();
            //ENCERRA O TURNO
            Procedimentos.anularTurno(jogo);
        }
        else
        {
            //ENTRADA INVALIDA
            UserInterface.erroEntrada();
        }
    }
    
    //CASO O JOGADOR QUEIRA BATER UMA COMBINACAO
    public static void pife(Sessao jogo)
    {
        //PERGUNTA AO USUARIO QUAL TIPO DE COMBINACAO ELE DESEJA BATER
        UserInterface.selecaoPife();
        jogo.entrada = jogo.scanner.next();
        
        //QUADRA
        if("Q".equals(jogo.entrada) | "q".equals(jogo.entrada))
        {
            Procedimentos.quadra(jogo);
        }
        //TRINCAS
        else if("T".equals(jogo.entrada) | "t".equals(jogo.entrada))
        {
            Procedimentos.trincas(jogo);
        }
        else
        {
            //Entrada invalida
            UserInterface.erroEntrada();
        }
    }
    
    //SELECIONA AS CARTAS DA TRINCA E VERIFICA A VITORIA
    public static void trincas(Sessao jogo)
    {
        List<Carta> trinca1 = new ArrayList<>();
        List<Carta> trinca2 = new ArrayList<>();
        
        //SELECIONAR PRIMEIRA TRINCA
        UserInterface.primeiraTrinca();
        Procedimentos.selecionarCartas(trinca1, jogo, 3);
        
        //SELECIONAR SEGUNDA TRINCA
        UserInterface.segundaTrinca();
        Procedimentos.selecionarCartas(trinca2, jogo, 3);
        
        //VERIFICAR VITORIA
        verificarVitoria(trinca1,trinca2,jogo);
    }
    
    //SELECIONA AS CARTAS DA QUADRA E VERIFICA A VITORIA
    public static void quadra(Sessao jogo)
    {
        List<Carta> quadra = new ArrayList<>();
        
        //SELECIONAR QUADRA
        UserInterface.selecionarQuadra();
        Procedimentos.selecionarCartas(quadra, jogo, 4);
        
        //VERIFICAR VITORIA
        verificarVitoria(quadra, jogo);
    }
    
    //VERIFICA SE A COMBINACAO DA QUADRA E VITORIOSA
    public static void verificarVitoria(List<Carta> quadra, Sessao jogo)
    {
        if(Verificadores.verificarCombinacao(quadra))
        {
            //IMPRIME VITORIA
            UserInterface.imprimirVitoria(jogo.jogadorAtual.getNomeJogador(), quadra);
            //ANULA JOGO
            Procedimentos.anularJogo(jogo);
        }
        else
        {
            //COMBINACAO INVALIDA
            UserInterface.combinacaoInvalida();
            Procedimentos.anularTurno(jogo);
        }
    }
    
    //OVERLOAD
    //VERIFICA SE A COMBINACAO DAS TRINCAS E VITORIOSA
    public static void verificarVitoria(List<Carta> trinca1,List<Carta> trinca2, Sessao jogo)
    {
        if(Verificadores.verificarCombinacao(trinca1) && Verificadores.verificarCombinacao(trinca2))
        {
            //IMPRIME VITORIA
            UserInterface.imprimirVitoria(jogo.jogadorAtual.getNomeJogador(), trinca1, trinca2);
            //ANULA JOGO
            Procedimentos.anularJogo(jogo);
        }
        else
        {
            //COMBINACAO INVALIDA
            UserInterface.combinacaoInvalida();
            Procedimentos.anularTurno(jogo);
        }
        
    }
    
}
