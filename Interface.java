/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.pratico.pkg2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexandre
 */
public class Interface 
{
    public static void imprimirSessao8(Jogador jogador)
    {
        //
        System.out.flush();
        //
        List<String> naipes = new ArrayList<>();
        List<String> numeros = new ArrayList<>();
        
        for(int i = 0; i< jogador.tamanho(); i++)
        {
            if(jogador.retornarCarta(i).numero() >= 2 && jogador.retornarCarta(i).numero() <= 10)
                numeros.add(Integer.toString(jogador.retornarCarta(i).numero()));
            else if(jogador.retornarCarta(i).numero() == 1)
                numeros.add("A");
            else if(jogador.retornarCarta(i).numero() == 11)
                numeros.add("Q");
            else if(jogador.retornarCarta(i).numero() == 12)
                numeros.add("K");
            else
                System.out.println("ERRO FATAL");
            
            switch (jogador.retornarCarta(i).naipe()) 
            {
                case 1:
                    naipes.add("#");
                    break;
                case 2:
                    naipes.add("@");
                    break;
                case 3:
                    naipes.add("O");
                    break;
                case 4:
                    naipes.add("+");
                    break;
                default:
                    break;
            }
            
        }
        System.out.println("***********VOCE DEVE COMPRAR UMA CARTA***********");
        System.out.println("--------------SUAS CARTAS------------------");
        System.out.println(" ___ ___ ___ ___ ___ ___ ___ ___ __________");
        System.out.println("|"+numeros.get(0)+"  |"+numeros.get(1)+"  |"+numeros.get(2)+"  |"+numeros.get(3)+"  |"+numeros.get(4)+"  |"+numeros.get(5)+"  |"+numeros.get(6)+"  |"+numeros.get(7)+"  |"+"?"+"        |");
        System.out.println("|"+naipes.get(0)+"  |"+naipes.get(1)+"  |"+naipes.get(2)+"  |"+naipes.get(3)+"  |"+naipes.get(4)+"  |"+naipes.get(5)+"  |"+naipes.get(6)+"  |"+naipes.get(7)+"  |"+"?"+"        |");
        System.out.println("|   |   |   |   |   |   |   |   |         |");
        System.out.println("|   |   |   |   |   |   |   |   |         |");
        System.out.println("|   |   |   |   |   |   |   |   |        ?|");
        System.out.println("|   |   |   |   |   |   |   |   |        ?|");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("  1   2   3   4   5   6   7   8    9");
        System.out.println("Copas: # | Espadas: @ | Ouros: O | Paus: +");
        System.out.println("");
        System.out.println("PRESSIONE L PARA COMPRAR DO LIXO");
        System.out.println("PRESSIONE M PARA COMPRAR DO MONTE");
    }
    
    public static void imprimirSessao9(Jogador jogador)
    {
        //
        System.out.flush();
        //
        List<String> naipes = new ArrayList<>();
        List<String> numeros = new ArrayList<>();
        
        for(int i = 0; i< jogador.tamanho(); i++)
        {
            if(jogador.retornarCarta(i).numero() >= 2 && jogador.retornarCarta(i).numero() <= 10)
                numeros.add(Integer.toString(jogador.retornarCarta(i).numero()));
            else if(jogador.retornarCarta(i).numero() == 1)
                numeros.add("A");
            else if(jogador.retornarCarta(i).numero() == 11)
                numeros.add("Q");
            else if(jogador.retornarCarta(i).numero() == 12)
                numeros.add("K");
            else
                System.out.println("ERRO FATAL");
            
            switch (jogador.retornarCarta(i).naipe()) 
            {
                case 1:
                    naipes.add("#");
                    break;
                case 2:
                    naipes.add("@");
                    break;
                case 3:
                    naipes.add("O");
                    break;
                case 4:
                    naipes.add("+");
                    break;
                default:
                    break;
            }
            
        }
        System.out.println("***********TURNO: "+ jogador.nome()+"***********");
        System.out.println("--------------SUAS CARTAS------------------");
        System.out.println(" ___ ___ ___ ___ ___ ___ ___ ___ __________");
        System.out.println("|"+numeros.get(0)+"  |"+numeros.get(1)+"  |"+numeros.get(2)+"  |"+numeros.get(3)+"  |"+numeros.get(4)+"  |"+numeros.get(5)+"  |"+numeros.get(6)+"  |"+numeros.get(7)+"  |"+numeros.get(8)+"        |");
        System.out.println("|"+naipes.get(0)+"  |"+naipes.get(1)+"  |"+naipes.get(2)+"  |"+naipes.get(3)+"  |"+naipes.get(4)+"  |"+naipes.get(5)+"  |"+naipes.get(6)+"  |"+naipes.get(7)+"  |"+naipes.get(8)+"        |");
        System.out.println("|   |   |   |   |   |   |   |   |         |");
        System.out.println("|   |   |   |   |   |   |   |   |         |");
        System.out.println("|   |   |   |   |   |   |   |   |        "+naipes.get(8)+"|");
        System.out.println("|   |   |   |   |   |   |   |   |        "+numeros.get(8)+"|");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("  1   2   3   4   5   6   7   8    9");
        System.out.println("Copas: # | Espadas: @ | Ouros: O | Paus: +");
        System.out.println("");
        System.out.println("PRESSIONE D PARA DESCARTAR UMA CARTA");
        System.out.println("PRESSIONE B PARA BATER");
        System.out.println("PRESSIONE P PARA PASSAR O TURNO");
    }
    
    public static void skip()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    
}
