/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.jogo;

import java.util.ArrayList;
import java.util.List;

import tp2.ambiente.*;
import tp2.cartas.Carta;
import tp2.cartas.AutenticidadeMao;

/**
 *
 * @author alexandre
 */
public class CondicoesVitoria
{    
    //CASO O JOGADOR QUEIRA BATER UMA COMBINACAO
    public static void pife(Sessao jogo)
    {
        //PERGUNTA AO USUARIO QUAL TIPO DE COMBINACAO ELE DESEJA BATER
        UserInterface.selecaoPife();
        jogo.entrada = jogo.scanner.next();
        
        //QUADRA
        if("Q".equals(jogo.entrada) | "q".equals(jogo.entrada))
        {
            CondicoesVitoria.quadra(jogo);
        }
        //TRINCAS
        else if("T".equals(jogo.entrada) | "t".equals(jogo.entrada))
        {
            CondicoesVitoria.trincas(jogo);
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
        InteracaoJogoJogador.selecionarCartas(trinca1, jogo, Define.TRINCA);
        
        //SELECIONAR SEGUNDA TRINCA
        UserInterface.segundaTrinca();
        InteracaoJogoJogador.selecionarCartas(trinca2, jogo, Define.TRINCA);
        
        //VERIFICAR VITORIA
        verificarVitoria(trinca1,trinca2,jogo);
    }
    
    //SELECIONA AS CARTAS DA QUADRA E VERIFICA A VITORIA
    public static void quadra(Sessao jogo)
    {
        List<Carta> quadra = new ArrayList<>();
        
        //SELECIONAR QUADRA
        UserInterface.selecionarQuadra();
        InteracaoJogoJogador.selecionarCartas(quadra, jogo, Define.QUADRA);
        
        //VERIFICAR VITORIA
        verificarVitoria(quadra, jogo);
    }
    
    //VERIFICA SE A COMBINACAO DA QUADRA E VITORIOSA
    public static void verificarVitoria(List<Carta> quadra, Sessao jogo)
    {
        if(AutenticidadeMao.verificarCombinacaoValida(quadra))
        {
            //IMPRIME VITORIA
            UserInterface.imprimirVitoria(jogo.jogadorAtual.getNomeJogador(), quadra);
            //ANULA JOGO
            FluxoJogo.anularJogo(jogo);
        }
        else
        {
            //COMBINACAO INVALIDA
            UserInterface.combinacaoInvalida();
            FluxoJogo.anularTurno(jogo);
        }
    }
    
    //OVERLOAD
    //VERIFICA SE A COMBINACAO DAS TRINCAS E VITORIOSA
    public static void verificarVitoria(List<Carta> trinca1,List<Carta> trinca2, Sessao jogo)
    {
        if(AutenticidadeMao.verificarCombinacaoValida(trinca1) && AutenticidadeMao.verificarCombinacaoValida(trinca2))
        {
            //IMPRIME VITORIA
            UserInterface.imprimirVitoria(jogo.jogadorAtual.getNomeJogador(), trinca1, trinca2);
            //ANULA JOGO
            FluxoJogo.anularJogo(jogo);
        }
        else
        {
            //COMBINACAO INVALIDA
            UserInterface.combinacaoInvalida();
            FluxoJogo.anularTurno(jogo);
        }
        
    }
}
