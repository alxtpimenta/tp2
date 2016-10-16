package tp2.procedimentoseinteracoes;

import java.util.ArrayList;
import java.util.List;

import tp2.ambiente.*;
import tp2.cartas.Carta;

/**
 *
 * @author alexandre
 */
public class CondicoesVitoria
{    
    //CASO O JOGADOR QUEIRA BATER UMA COMBINACAO
    public static void pifeBatida(Sessao sessaoJogo)
    {
        //PERGUNTA AO USUARIO QUAL TIPO DE COMBINACAO ELE DESEJA BATER
        UserInterface.selecaoBatidaPife();
        sessaoJogo.entrada = sessaoJogo.scanner.next();
        
        //QUADRA
        if("Q".equals(sessaoJogo.entrada) | "q".equals(sessaoJogo))
        {
            CondicoesVitoria.quadra(sessaoJogo);
        }
        //TRINCAS
        else if("T".equals(sessaoJogo.entrada) | "t".equals(sessaoJogo.entrada))
        {
            CondicoesVitoria.trincas(sessaoJogo);
        }
        else
        {
            //Entrada invalida
            UserInterface.erroEntrada();
            sessaoJogo.batidaInefetiva = true;
        }
    }
    
    //SELECIONA AS CARTAS DA TRINCA E VERIFICA A VITORIA
    public static void trincas(Sessao sessaoJogo)
    {
        List<Carta> trinca1 = new ArrayList<>();
        List<Carta> trinca2 = new ArrayList<>();
        
        //SELECIONAR PRIMEIRA TRINCA
        UserInterface.requisitaPrimeiraTrinca();
        InteracaoJogoJogador.selecionarCartas(trinca1, sessaoJogo, Define.TRINCA);
        
        //SELECIONAR SEGUNDA TRINCA
        UserInterface.requisitaSegundaTrinca();
        InteracaoJogoJogador.selecionarCartas(trinca2, sessaoJogo, Define.TRINCA);
        
        //VERIFICAR VITORIA
        verificarVitoria(trinca1,trinca2,sessaoJogo);
    }
    
    //SELECIONA AS CARTAS DA QUADRA E VERIFICA A VITORIA
    public static void quadra(Sessao sessaoJogo)
    {
        List<Carta> quadra = new ArrayList<>();
        
        //SELECIONAR QUADRA
        UserInterface.requisitaSelecaoQuadra();
        InteracaoJogoJogador.selecionarCartas(quadra, sessaoJogo, Define.QUADRA);
        
        //VERIFICAR VITORIA
        verificarVitoria(quadra, sessaoJogo);
    }
    
    //VERIFICA SE A COMBINACAO DA QUADRA E VITORIOSA
    public static void verificarVitoria(List<Carta> quadra, Sessao sessaoJogo)
    {
        if(AutenticidadeMao.verificarCombinacaoValida(quadra))
        {
            //IMPRIME VITORIA
            UserInterface.imprimirVitoria(sessaoJogo.jogadorAtual.getNomeJogador(), quadra);
            //ANULA JOGO
            FluxoJogo.anularJogo(sessaoJogo);
        }
        else
        {
            //COMBINACAO INVALIDA
            UserInterface.combinacaoInvalida();
            FluxoJogo.anularTurno(sessaoJogo);
        }
    }
    
    //OVERLOAD
    //VERIFICA SE A COMBINACAO DAS TRINCAS E VITORIOSA
    public static void verificarVitoria(List<Carta> trinca1,List<Carta> trinca2, Sessao sessaoJogo)
    {
        if(AutenticidadeMao.verificarCombinacaoValida(trinca1) && AutenticidadeMao.verificarCombinacaoValida(trinca2))
        {
            //IMPRIME VITORIA
            UserInterface.imprimirVitoria(sessaoJogo.jogadorAtual.getNomeJogador(), trinca1, trinca2);
            //ANULA JOGO
            FluxoJogo.anularJogo(sessaoJogo);
        }
        else
        {
            //COMBINACAO INVALIDA
            UserInterface.combinacaoInvalida();
            FluxoJogo.anularTurno(sessaoJogo);
        }
        
    }
}
