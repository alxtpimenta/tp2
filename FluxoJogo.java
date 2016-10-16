package tp2.procedimentoseinteracoes;

import tp2.ambiente.Sessao;
import tp2.ambiente.UserInterface;

public class FluxoJogo 
{
    //PROCEDIMENTOS POS TURNO
    public static void posTurno(Sessao sessaoJogo)
    {
        //INCREMENTA O IDENTIFICADOR DO JOGADOR ATUAL
    	sessaoJogo.idJogadorAtual++;
        //CASO O IDENTIFICADOR TENHA EXCEDIDO A QUANTIDADE DE JOGADORES, RESETAR
        if(sessaoJogo.idJogadorAtual == sessaoJogo.qtdJogadores)
        	sessaoJogo.idJogadorAtual = 0;
        //RESETA O INDICADOR DE COMPRA
        sessaoJogo.compraEfetuada = false;
        //VALIDA O CONTROLADOR DO LOOP DE TURNO
        sessaoJogo.controlaTurno = true;
    }

    //PASSA O TURNO
    public static void anularTurno(Sessao sessaoJogo)
    {
        //ANULA O CONTROLADOR DE LOOP
    	sessaoJogo.controlaTurno = false;
        //SAIDA PARA UI
        UserInterface.skip();
        UserInterface.fimTurno();
    }
    
    //ENCERRA O JOGO
    public static void anularJogo(Sessao sessaoJogo)
    {
        //ANULA OS CONTROLADORES DE LOOP
    	sessaoJogo.controlaTurno = false;
    	sessaoJogo.controlaJogo = false;
    }
}
