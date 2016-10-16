package tp2.jogo;

import tp2.ambiente.*;
import tp2.cartas.Baralho;
import tp2.procedimentoseinteracoes.FluxoJogo;
import tp2.procedimentoseinteracoes.InteracaoJogoJogador;


public class Jogo 
{
	public static void main(String[] args) 
    {
		//INICIA PROCEDIMENTO PARA A CADEIA DE RESPONSABILIDADE QUE CUIDA DAS REQUISICOES DO JOGADOR
		CadeiaDeAcao cadeiaRequest = new AcaoBater();
		CadeiaDeAcao cadeiaRequest2 = new AcaoCompraLixo();
		CadeiaDeAcao cadeiaRequest3 = new AcaoCompraMonte();
		CadeiaDeAcao cadeiaRequest4 = new AcaoDescartar();
		CadeiaDeAcao cadeiaRequest5 = new AcaoPulaTurno();
		//
		cadeiaRequest.setProxCadeia(cadeiaRequest2);
		cadeiaRequest2.setProxCadeia(cadeiaRequest3);
		cadeiaRequest3.setProxCadeia(cadeiaRequest4);
		cadeiaRequest4.setProxCadeia(cadeiaRequest5);
		//
		
        //INICIALIZAR O SINGLETON DE SESSAO DO JOGO
        Sessao sessaoJogo;
        sessaoJogo = Sessao.retornarInstancia();
        sessaoJogo.inicializarSessao();

        //IMPRIMIR TELA DE BOAS VINDAS, E PEDIR QUANTIDADE DE JOGADORES
        UserInterface.boasVindas();
        
        //RECEBE QUANTIDADE DE JOGADORES
        InteracaoJogoJogador.iniciarQtdJogadores(sessaoJogo);
        
        //PERGUNTA AO USUARIO OS NOMES
        UserInterface.digiteJogadores();
        //CRIA OS JOGADORES
        Jogador.criarJogadores(sessaoJogo.jogadores, sessaoJogo.qtdJogadores, sessaoJogo.scanner);
        
        //DISTRIBUI AS CARTAS AOS JOGADORES
        InteracaoJogoJogador.distribuirCartas(sessaoJogo.jogadores, sessaoJogo.qtdJogadores, sessaoJogo.monte, sessaoJogo.baralho);
        
        //"LIMPA" O CONSOLE
        UserInterface.skip();
        
        //LOOP DE CONTROLE DO JOGO
        while(sessaoJogo.controlaJogo)
        {
            //VERIFICACOES PARA O NOVO TURNO
            //CASO O MONTE ESTEJA VAZIO, EMBARALHAR O MONTE E PRINTA NA TELA
            if(sessaoJogo.monte.isEmpty())
            {
            	Baralho.transformaLixoMonte(sessaoJogo.lixo,sessaoJogo.monte);
            	UserInterface.lixoVazioEmbaralhar();
            }            
            
            //LOOP DE CONTROLE DO TURNO
            while(sessaoJogo.controlaTurno)
            {
                //ATUALIZA O JOGADOR ATUAL
                sessaoJogo.jogadorAtual = sessaoJogo.jogadores.get(sessaoJogo.idJogadorAtual);
                
                //IMPRIMIR INTERFACE
                if(sessaoJogo.lixo.size() > 1)
                    UserInterface.imprimirInterfaceMaoJogador(sessaoJogo.jogadorAtual, sessaoJogo.lixo.get(sessaoJogo.lixo.size()-1));
                else
                    UserInterface.imprimirInterfaceMaoJogador(sessaoJogo.jogadorAtual, null);
                
                //LE A ENTRADA DO USUARIO
                sessaoJogo.entrada = sessaoJogo.scanner.next();
                
				//VARIAVEL QUE VAI CONTROLAR AS ACOES DO JOGADOR NO JOGO
				Acao acao = new Acao(sessaoJogo, sessaoJogo.entrada);     		
				//PRIMEIRO METODO DA CADEIA DE RESPONSABILIDADE QUE ATENDE A REQUISICAO DA ACAO DO JOGADOR
				cadeiaRequest.acaoJogador(acao);

            }
            //VERIFICACOES POS-TURNO
            FluxoJogo.posTurno(sessaoJogo);
        }
        //IMPORTANTE NOTAR QUE CADA CARTA DO BARALHO SOMENTE TERA UMA INSTANCIA
        //AS CARTAS SERAO INCLUIDAS E REMOVIDAS POR REFERENCIA NA EXECUCAO
    }
}
