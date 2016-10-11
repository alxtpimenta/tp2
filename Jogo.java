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
        
        //CRIA OS JOGADORES
        Jogador.criarJogadores(jogadores, qtdJogadores, scanner);
        
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
                
                //IMPRIMIR INTERFACE
                if(jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
                    if(lixo.size() > 1)
                        UserInterface.imprimirSessaoCompra(jogadorAtual, lixo.get(lixo.size()-1));
                    else
                        UserInterface.imprimirSessaoCompra(jogadorAtual, null);
                //CASO O JOGADOR NAO PRECISE COMPRAR
                else if(jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
                    UserInterface.imprimirSessao(jogadorAtual);
                
                //LE A ENTRADA DO USUARIO
                entrada = scanner.next();
                
                //BATER
                if(("B".equals(entrada) | "b".equals(entrada)) && jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
                {
                    //
                    UserInterface.selecaoPife();
                    entrada = scanner.next();
                    //QUADRA
                    if("Q".equals(entrada) | "q".equals(entrada))
                    {
                        //ARMAZENA A QUADRA A SER SELECIONADA
                        List<Carta> quadra = new ArrayList<>();
                        operando = 4;
                        //
                        Procedimentos.selecionarCartas(quadra, jogadorAtual, operando, scanner);
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
                        operando = 3;
                        
                        //PRIMEIRA TRINCA
                        UserInterface.primeiraTrinca();
                        Procedimentos.selecionarCartas(trinca1, jogadorAtual, operando, scanner);
                        
                        //SEGUNDA TRINCA
                        UserInterface.segundaTrinca();
                        Procedimentos.selecionarCartas(trinca2, jogadorAtual, operando, scanner);
                        
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
                else if(("D".equals(entrada) | "d".equals(entrada)) && jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
                {
                    //DESCARTA A CARTA
                    Procedimentos.descartarCarta(jogadorAtual, lixo, scanner);
                    //ENCERRA O TURNO
                    Procedimentos.anularTurno(turno);
                }
                //COMPRAR DO LIXO
                else if(("L".equals(entrada) | "l".equals(entrada)) && jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
                {
                    Procedimentos.comprarCarta(jogadorAtual, lixo);
                }
                //COMPRAR DO monte
                else if(("M".equals(entrada) | "m".equals(entrada)) && jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
                {
                    Procedimentos.comprarCarta(jogadorAtual, monte);
                }
                //PULAR O TURNO
                else if("P".equals(entrada) | "p".equals(entrada))
                {
                    Procedimentos.anularTurno(turno);
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
