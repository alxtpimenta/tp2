package tp2.ambiente;

import java.util.ArrayList;
import java.util.List;

import tp2.cartas.*;

public class UserInterface 
{
    //IMPRIME A SESSAO DE JOGO CASO O JOGADOR TENHA O NUMERO MINIMO CARTAS
    //(SE O JOGADOR TEM O NUMERO MINIMO CARTAS, ELE OBRIGATORIAMENTE PRECISA COMPRAR UMA CARTA)
    public static void imprimirInterfaceMaoJogador(Jogador jogador, Carta lixo)
    {
        //LISTAS DE NUMEROS E NAIPES PARA A REPRESENTACAO GRAFICA
        List<String> naipes = new ArrayList<>();
        List<String> numeros = new ArrayList<>();
        
        //PARA CADA CARTA, ADICIONE SUA REPRESENTACAO GRAFICA DE NAIPE E NUMERO NA LISTA
        for(int i = 0; i< jogador.tamanhoMaoJogador(); i++)
        {
            //NUMEROS
            if(jogador.retornarCartaJogador(i).getNumeroCarta() >= 2 && jogador.retornarCartaJogador(i).getNumeroCarta() <= 9)
                numeros.add(Integer.toString(jogador.retornarCartaJogador(i).getNumeroCarta()));
			else if(jogador.retornarCartaJogador(i).getNumeroCarta() == 10)
				numeros.add("X");
            else if(jogador.retornarCartaJogador(i).getNumeroCarta() == 1)
                numeros.add("A");
            else if(jogador.retornarCartaJogador(i).getNumeroCarta() == 11)
                numeros.add("Q");
            else if(jogador.retornarCartaJogador(i).getNumeroCarta() == 12)
                numeros.add("K");
            else
                UserInterface.erroDesconhecido();
            
            //NAIPES
            switch (jogador.retornarCartaJogador(i).getNaipeCarta()) 
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
		//SE O JOGADOR TIVER O NUMERO MINIMO DE CARTAS, IMPRIMIR INTERFACE DE COMPRA
		if(jogador.tamanhoMaoJogador() == Define.MIN_MAO)
		{
			//INICIAR A REPRESENTACAO GRAFICA
			System.out.println("******** TURNO: "+ jogador.getNomeJogador()+" ********");
			System.out.println("******** VOCE DEVE COMPRAR UMA CARTA ********");
			System.out.println("--------------SUAS CARTAS------------------");
			System.out.println(" ___ ___ ___ ___ ___ ___ ___ ___ __________");
			System.out.println("|"+numeros.get(0)+"  |"+numeros.get(1)+"  |"+numeros.get(2)+"  |"+numeros.get(3)+"  |"+numeros.get(4)+"  |"+numeros.get(5)+"  |"+numeros.get(6)+"  |"+numeros.get(7)+"  |"+numeros.get(8)+"        |");
			System.out.println("|"+naipes.get(0)+"  |"+naipes.get(1)+"  |"+naipes.get(2)+"  |"+naipes.get(3)+"  |"+naipes.get(4)+"  |"+naipes.get(5)+"  |"+naipes.get(6)+"  |"+naipes.get(7)+"  |"+naipes.get(8)+"        |");
			System.out.println("|   |   |   |   |   |   |   |   |         |");
			System.out.println("|   |   |   |   |   |   |   |   |         |");
			System.out.println("|   |   |   |   |   |   |   |   |         |");
			System.out.println("|   |   |   |   |   |   |   |   |         |");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("  1   2   3   4   5   6   7   8    9");
			System.out.println("Copas: # | Espadas: @ | Ouros: O | Paus: +");
			System.out.println("");
			//
			if(lixo != null) //VERIFICA SE O LIXO ESTA VAZIO
				System.out.println("PRIMEIRA CARTA DO LIXO: " + lixo.toString());
			else
				System.out.println("PRIMEIRA CARTA DO LIXO: LIXO VAZIO");
			//
			System.out.println("-> PRESSIONE L PARA COMPRAR DO LIXO");
			System.out.println("-> PRESSIONE M PARA COMPRAR DO MONTE");
		}
		//CASO O JOGADOR TENHA O NUMERO MAXIMO, IMPRIMIR INTERFACE DE DESCARTE/BATER
		else if(jogador.tamanhoMaoJogador() == Define.MAX_MAO)
		{
			System.out.println("******** TURNO: "+ jogador.getNomeJogador()+" ********");
			System.out.println("--------------SUAS CARTAS------------------");
			System.out.println(" ___ ___ ___ ___ ___ ___ ___ ___ ___ __________");
			System.out.println("|"+numeros.get(0)+"  |"+numeros.get(1)+"  |"+numeros.get(2)+"  |"+numeros.get(3)+"  |"+numeros.get(4)+"  |"+numeros.get(5)+"  |"+numeros.get(6)+"  |"+numeros.get(7)+"  |"+numeros.get(8)+"  |"+numeros.get(9)+"        |");
			System.out.println("|"+naipes.get(0)+"  |"+naipes.get(1)+"  |"+naipes.get(2)+"  |"+naipes.get(3)+"  |"+naipes.get(4)+"  |"+naipes.get(5)+"  |"+naipes.get(6)+"  |"+naipes.get(7)+"  |"+naipes.get(8)+"  |"+naipes.get(9)+"        |");
			System.out.println("|   |   |   |   |   |   |   |   |   |         |");
			System.out.println("|   |   |   |   |   |   |   |   |   |         |");
			System.out.println("|   |   |   |   |   |   |   |   |   |         |");
			System.out.println("|   |   |   |   |   |   |   |   |   |         |");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("  1   2   3   4   5   6   7   8    9    10");
			System.out.println("Copas: # | Espadas: @ | Ouros: O | Paus: +");
			System.out.println("");
			System.out.println("-> PRESSIONE D PARA DESCARTAR UMA CARTA");
			System.out.println("-> PRESSIONE B PARA BATER");
                        System.out.println("-> PRESSIONE P PARA PASSAR O TURNO");
		}
		//CASO NAO TENHA O NUMERO MAXIMO NEM MINIMO, IMPRIMIR ERRO
		else
		{
			UserInterface.erroDesconhecido();
		}
    }
    
    //"LIMPA" O CONSOLE
    public static void skip()
    {
        System.out.println("\n\n\n\n\n");
    }
    
    //IMPRIME MENSAGEM DE COMPRA
    public static void adicionadaCarta()
    {
        System.out.println("--Carta adicionada!--");
    }
    
    //IMPRIME MENSAGEM DE DESCARTE
    public static void descarteCarta()
    {
        System.out.println("--Carta descartada!--");
    }

    //MENSAGEM DE ERRO: COMPRA JA REALIZADA NO TURNO
    public static void erroDescarte()
    {
        System.out.println("--- O JOGADOR JA COMPROU UMA CARTA NO TURNO! ---");
    }
    
    //CASO O MONTE SEJA EMBARALHADO POR CAUSA DE LIXO VAZIO
    public static void lixoVazioEmbaralhar()
    {
        System.out.println("--- LIXO VAZIO! EMBARALHANDO MONTE! ---");
    }
    
    //IMPRIME MENSAGEM DE SELECAO DE DESCARTE
    public static void selecaoDescarte()
    {
        System.out.println("-> Digite o numero da carta que deseja descartar (apenas numeros)");
    }
    
    //REQUISITA O USUARIO A DIGITAR O IDENTIFICADOR DA CARTA
    public static void digiteCarta()
    {
        //
        System.out.println("-> Digite o numero da carta (apenas numeros)");
    }
    
    //MENSAGEM DE ERRO CASO A ENTRADA SEJA INVALIDA
    public static void erroEntrada()
    {
        //
        System.out.println("!!! Entrada invalida! !!!");
    }
    
    //IMPRIME QUE A BATIDA FOI INEFETIVA E DEVE SER EFETUADO UM DESCARTE
    public static void batidaInefetivaDescarte()
    {
    	System.out.println("SUA BATIDA NAO FOI VALIDA! EFETUE UM DESCARTE");
    }
    
    //IMPRIME MENSAGEM DE FIM DE TURNO
    public static void fimTurno()
    {
        System.out.println("--Turno finalizado!--");
    }
    
    //MENSAGEM DE NUMERO DE CARTAS PARA BATER
    public static void numeroCartasBatidaPife()
    {
        System.out.println("-> Digite o numero das cartas que deseja bater separados por espaco (Apenas numeros!)");
    }
    
    //IMPRIME MENSAGEM DE SELECAO DE BATIDA
    public static void selecaoBatidaPife()
    {
        System.out.println("Selecione a opcao que deseja bater:");
        System.out.println("-> PRESSIONE Q PARA BATER UMA QUADRA");
        System.out.println("-> PRESSIONE T PARA BATER DUAS TRINCAS");
    }
    
    //MENSAGEM DE ERRO DESCONHECIDO
    public static void erroDesconhecido()
    {
        System.out.println("!!! ERRO DESCONHECIDO !!!");
    }
    
    //MENSAGEM DE ERRO: COMBINACAO INVALIDA
    public static void combinacaoInvalida()
    {
        System.out.println("-- A combinacao nao e valida! --");
    }
    
    //REQUISITA AO USUARIO A SEQUENCIA DE CARTAS DA PRIMEIRA TRINCA
    public static void requisitaPrimeiraTrinca()
    {
        System.out.println("->Primeira trinca:");
    }
    
    //REQUISITA AO USUARIO A SEQUENCIA DE CARTAS DA QUASRA
    public static void requisitaSelecaoQuadra()
    {
        System.out.println("->Quadra:");
    }
    
    //REQUISITA AO USUARIO A SEQUENCIA DE CARTAS DA SEGUNDA TRINCA
    public static void requisitaSegundaTrinca()
    {
        System.out.println("->Segunda trinca:");
    }
    
    //MENSAGEM DE ERRO: FONTE VAZIA
    public static void erroNaoHaCartas()
    {
        System.out.println("!!! ERRO: NAO HA CARTAS NESSE LUGAR! !!!");
    }
    
    //TELA DE BOAS VINDAS
    public static void boasVindas()
    {
        System.out.println("Bem vindo(a) a partida de Pife!");
        System.out.println("-> Insira a quantidade de jogadores");
        System.out.println("(Minimo de "+Integer.toString(Define.MIN_JOGADORES)+" e maximo de "+Integer.toString(Define.MAX_JOGADORES)+" jogadores por partida. Somente numeros.)");
    }
    
    //REQUISITA O NUMERO DE JOGADORES AO USUARIO
    public static void digiteJogadores()
    {
        System.out.println("-> Digite os nomes dos jogadores separados por espaco ");
    }
    
    //IMPRIME A TELA DE VITORIA (CASO A COMBINACAO VITORIOSA SEJA UMA QUADRA)
    public static void imprimirVitoria(String jogador, List<Carta> mao)
    {
    	UserInterface.skip();
        System.out.println("####### FIM DE JOGO #######");
        System.out.println("GANHADOR: " + jogador);
        System.out.println("Mao da jogada:");
        for(int i = 0; i< mao.size(); i++)
        {
            mao.get(i).toString();
        }
    }
    
    //OVERLOAD
    //IMPRIME A TELA DE VITORIA CASO A COMBINACAO VITORIOSA SEJA DUAS TRINCAS
    public static void imprimirVitoria(String jogador, List<Carta> mao, List<Carta> mao2)
    {
    	UserInterface.skip();
        System.out.println("####### FIM DE JOGO #######");
        System.out.println("GANHADOR: " + jogador);
        System.out.println("Mao da jogada:");
        for(int i = 0; i< mao.size(); i++)
        {
            mao.get(i).toString();
        }
        System.out.println("");
        for(int i = 0; i< mao2.size(); i++)
        {
            mao2.get(i).toString();
        }
    }
}
