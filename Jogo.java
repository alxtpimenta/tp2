package tp2.jogo;

import tp2.ambiente.*;
import tp2.cartas.Baralho;
import tp2.chainofresponsability.*;

public class Jogo 
{
	public static void main(String[] args) 
    {
		//INICIA PROCEDIMENTO PARA A CADEIA DE RESPONSABILIDADE QUE CUIDA DAS REQUISICOES DO JOGADOR
		Cadeia cadeiaRequest1 = new AcaoBater();
		Cadeia cadeiaRequest2 = new AcaoCompraLixo();
		Cadeia cadeiaRequest3 = new AcaoCompraMonte();
		Cadeia cadeiaRequest4 = new AcaoDescartar();
		Cadeia cadeiaRequest5 = new AcaoPulaTurno();
		//
		cadeiaRequest1.setProxCadeia(cadeiaRequest2);
		cadeiaRequest2.setProxCadeia(cadeiaRequest3);
		cadeiaRequest3.setProxCadeia(cadeiaRequest4);
		cadeiaRequest4.setProxCadeia(cadeiaRequest5);
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
                if(jogo.lixo.size() > 1)
                    UserInterface.imprimirInterface(jogo.jogadorAtual, jogo.lixo.get(jogo.lixo.size()-1));
                else
                    UserInterface.imprimirInterface(jogo.jogadorAtual, null);
                
                //LE A ENTRADA DO USUARIO
                jogo.entrada = jogo.scanner.next();
                
				//VARIAVEL QUE VAI CONTROLAR AS ACOES DO JOGADOR NO JOGO
				Acao acao = new Acao(jogo, jogo.entrada);     		
				//PRIMEIRO METODO DA CADEIA DE RESPONSABILIDADE QUE ATENDE A REQUISICAO DA ACAO DO JOGADOR
				cadeiaRequest1.acaoJogador(acao);

            }
            //VERIFICACOES POS-TURNO
            FluxoJogo.posTurno(jogo);
        }
        //IMPORTANTE NOTAR QUE CADA CARTA DO BARALHO SOMENTE TERA UMA INSTANCIA
        //AS CARTAS SERAO INCLUIDAS E REMOVIDAS POR REFERENCIA NA EXECUCAO
    }
}
