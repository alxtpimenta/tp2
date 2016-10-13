package tp2.jogo;

import tp2.ambiente.Sessao;
import tp2.ambiente.UserInterface;

public class FluxoJogo 
{
    //PROCEDIMENTOS POS TURNO
    public static void posTurno(Sessao jogo)
    {
        //INCREMENTA O IDENTIFICADOR DO JOGADOR ATUAL
        jogo.idJogadorAtual++;
        //CASO O IDENTIFICADOR TENHA EXCEDIDO A QUANTIDADE DE JOGADORES, RESETAR
        if(jogo.idJogadorAtual == jogo.qtdJogadores)
            jogo.idJogadorAtual = 0;
        
        //VALIDA O CONTROLADOR DO LOOP DE TURNO
        jogo.controlaTurno = true;
    }

    //PASSA O TURNO
    public static void anularTurno(Sessao jogo)
    {
        //ANULA O CONTROLADOR DE LOOP
        jogo.controlaTurno = false;
        //SAIDA PARA UI
        UserInterface.skip();
        UserInterface.fimTurno();
    }
    
    //ENCERRA O JOGO
    public static void anularJogo(Sessao jogo)
    {
        //ANULA OS CONTROLADORES DE LOOP
        jogo.controlaTurno = false;
        jogo.controlaJogo = false;
    }
    
}
