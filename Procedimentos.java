/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.jogo;

import java.util.List;
import java.util.Scanner;
import tp2.ambiente.Jogador;
import tp2.ambiente.Verificadores;
import tp2.cartas.Carta;

/**
 *
 * @author alexandre
 */
public class Procedimentos 
{
    public static void selecionarCartas(List<Carta> selecao,Jogador jogadorAtual,int quantidade,Scanner scanner)
    {
        UserInterface.digiteCarta();
        //
        String entrada;
        int operando;
        for(int i = 0; i < quantidade; i++)
        {
            //CERTIFICA QUE A ENTRADA E UM INTEIRO
            if(scanner.hasNextInt())
            {
                entrada = scanner.next();
                operando = Integer.parseInt(entrada);
                if(Verificadores.verificarEntrada(operando))
                {
                    selecao.add(jogadorAtual.retornarCartaJogador(operando -1));
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
    
    public static void anularTurno(boolean turno)
    {
        //
        turno = false;
        //
        UserInterface.skip();
        UserInterface.fimTurno();
    }
    
    public static void descartarCarta(Jogador jogadorAtual,List<Carta> lixo,Scanner scanner)
    {
        //ARMAZENA A ENTRADA DO USUARIO
        String entrada;
        int operando;
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
            //GERA SAIDA PARA A INTERFACE
            UserInterface.descarte();
        }
        else
        {
            //ENTRADA INVALIDA
            UserInterface.erroEntrada();
        }
    }
    
}
