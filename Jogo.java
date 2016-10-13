package tp2.jogo;

import tp2.ambiente.*;
import tp2.cartas.Baralho;

public class Jogo 
{
	public static void main(String[] args) 
    {
        //INICIALIZAR O SINGLETON DE SESSAO DO JOGO
        Sessao jogo;
        jogo = Sessao.retornarInstancia();
        jogo.inicializarSessao();

        //INICIALIZA O BARALHO
        Baralho.inicializarBaralho(jogo.baralho);
        
        //IMPRIMIR TELA DE BOAS VINDAS, E PEDIR QUANTIDADE DE JOGADORES
        UserInterface.boasVindas();
        
        //RECEBE QUANTIDADE DE JOGADORES
        InteracaoJogoJogador.iniciarQtdJogadores(jogo);
        
        //PERGUNTA AO USUARIO OS NOMES
        UserInterface.digiteJogadores();
        //CRIA OS JOGADORES
        Jogador.criarJogadores(jogo.jogadores, jogo.qtdJogadores, jogo.scanner);
        
        //DISTRIBUI AS CARTAS AOS JOGADORES
        InteracaoJogoJogador.distribuirCartas(jogo.jogadores, jogo.qtdJogadores, jogo.monte, jogo.baralho);
        
        //"LIMPA" O CONSOLE
        UserInterface.skip();
        
        //LOOP DE CONTROLE DO JOGO
        while(jogo.controlaJogo)
        {
            //VERIFICACOES PARA O NOVO TURNO
            //CASO O MONTE ESTEJA VAZIO, EMBARALHAR O MONTE E PRINTA NA TELA
            if(jogo.monte.isEmpty())
            {
            	Baralho.transformaLixoMonte(jogo.lixo,jogo.monte);
            	UserInterface.lixoVazioEmbaralhar();
            }            
            
            //LOOP DE CONTROLE DO TURNO
            while(jogo.controlaTurno)
            {
                //ATUALIZA O JOGADOR ATUAL
                jogo.jogadorAtual = jogo.jogadores.get(jogo.idJogadorAtual);
                
                //IMPRIMIR INTERFACE
                if(jogo.jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
                    if(jogo.lixo.size() > 1)
                        UserInterface.imprimirSessaoCompra(jogo.jogadorAtual, jogo.lixo.get(jogo.lixo.size()-1));
                    else
                        UserInterface.imprimirSessaoCompra(jogo.jogadorAtual, null);
                //CASO O JOGADOR NAO PRECISE COMPRAR
                else if(jogo.jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
                    UserInterface.imprimirSessao(jogo.jogadorAtual);
                
                //LE A ENTRADA DO USUARIO
                jogo.entrada = jogo.scanner.next();
                
                //BATER
                if(("B".equals(jogo.entrada) | "b".equals(jogo.entrada)) && jogo.jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
                {
                    CondicoesVitoria.pife(jogo);
                }
                //DESCARTAR
                else if(("D".equals(jogo.entrada) | "d".equals(jogo.entrada)) && jogo.jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
                {
                    //DESCARTA A CARTA
                	InteracaoJogoJogador.descartarCarta(jogo);
                }
                //COMPRAR DO LIXO
                else if(("L".equals(jogo.entrada) | "l".equals(jogo.entrada)) && jogo.jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
                {
                	InteracaoJogoJogador.comprarCarta(jogo.jogadorAtual, jogo.lixo);
                }
                //COMPRAR DO MONTE
                else if(("M".equals(jogo.entrada) | "m".equals(jogo.entrada)) && jogo.jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
                {
                	InteracaoJogoJogador.comprarCarta(jogo.jogadorAtual, jogo.monte);
                }
                //PULAR O TURNO
                else if("P".equals(jogo.entrada) | "p".equals(jogo.entrada))
                {
                	FluxoJogo.anularTurno(jogo);
                }
                else
                {
                    //ENTRADA INVALIDA
                    UserInterface.erroEntrada();
                }
            }
            //VERIFICACOES POS-TURNO
            FluxoJogo.posTurno(jogo);
        }
        //IMPORTANTE NOTAR QUE CADA CARTA DO BARALHO SOMENTE TERA UMA INSTANCIA
        //AS CARTAS SERAO INCLUIDAS E REMOVIDAS POR REFERENCIA NA EXECUCAO
    }
        
}
