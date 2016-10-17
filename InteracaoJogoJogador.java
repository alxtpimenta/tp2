package tp2.procedimentoseinteracoes;

import java.util.List;

import tp2.ambiente.Define;
import tp2.ambiente.Jogador;
import tp2.ambiente.Sessao;
import tp2.ambiente.UserInterface;
import tp2.cartas.Carta;

public class InteracaoJogoJogador
{
	//RECEBE QUANTIDADE DE JOGADORES
    public static void iniciarQtdJogadores(Sessao sessaoJogo)
    {
        //GARANTE QUE A ENTRADA ESTEJA DENTRO DOS LIMITES ESPECIFICADOS NA CLASSE DEFINE
        while(true)
        {
            if(sessaoJogo.scanner.hasNextInt())
            {
            	sessaoJogo.qtdJogadores = sessaoJogo.scanner.nextInt();
                if(sessaoJogo.qtdJogadores < Define.MIN_JOGADORES | sessaoJogo.qtdJogadores > Define.MAX_JOGADORES)
                    //CASO A ENTRADA ESTEJA FORA DOS LIMITES ESPECIFICADOS, IMPRIMIR MENSAGEM DE ERRO
                    UserInterface.erroEntrada();
                else
                    //CASO CONTRARIO A ENTRADA ESTA CORRETA E O LOOP SERA FINALIZADO
                    break;
            }
            else
            {
                //CASO A ENTRADA NAO CONTENHA INTEIROS
            	sessaoJogo.entrada = sessaoJogo.scanner.nextLine();
            	sessaoJogo.entrada = null;
                //SAIDA PARA UI
                UserInterface.skip();
                UserInterface.erroEntrada();
            } 
        }
    }
    
    //VERIFICA SE A ENTRADA DO USUARIO ESTA EM CONFORMES COM MAXIMO DE CARTAS PERMITIDO
    public static boolean verificarEntradaJogador(int entrada)
    {
        return entrada >= 1 && entrada <= Define.MAX_MAO;
    }
	
	//DISTRIBUI AS CARTAS DE CADA JOGADOR
    public static void distribuirCartas(List<Jogador> jogadores, int qtdJogadores, List<Carta> monte, List<Carta> baralho)
    {
        //DAR O NUMERO DEFINIDO DE CARTAS CARTAS A CADA JOGADOR E INSERIR O RESTO NO MONTE
        //AS CARTAS JA FORAM EMBARALHADAS NA INICIALIZACAO
        //VARIAVEIS PARA INDICAR OS INDICES PARA A INSERCAO
        int indice = 0;
        int fim = Define.MIN_MAO;
        //INSERIR CARTAS
        for(int i = 0; i < qtdJogadores; i++)
        {
            for(; indice < fim; indice ++)
            {
                //ADICIONA A CARTA DO BARALHO A MAO DO JOGADOR
            	jogadores.get(i).adicionarCartaJogador(baralho.get(indice));
            }
            //INCREMENTAR O FIM
            fim = fim + Define.MIN_MAO;
        }
        //O RESTANTE DAS CARTAS SERAO INSERIDAS NO MONTE
        for(; indice < baralho.size(); indice ++)
        {
            //INSERE CARTAS DO BARALHO NO MONTE
            monte.add(baralho.get(indice));
        }
    }
    
    //ADICIONA AS CARTAS SELECIONADAS NA LISTA REFERENCIADA
    public static void selecionarCartas(List<Carta> selecao,Sessao sessaoJogo, int quantidade)
    {
        //PEDE QUE O USUARIO DIGITE AS CARTAS
        UserInterface.digiteCarta();
        //INSERE O NUMERO REFERENCIADO DE CARTAS NA LISTA REFERENCIADA
        for(int i = 0; i < quantidade; i++)
        {
            //CERTIFICA QUE A ENTRADA E UM INTEIRO
            if(sessaoJogo.scanner.hasNextInt())
            {
            	sessaoJogo.entrada = sessaoJogo.scanner.next();
            	sessaoJogo.entradaJogador = Integer.parseInt(sessaoJogo.entrada);
                if(InteracaoJogoJogador.verificarEntradaJogador(sessaoJogo.entradaJogador))
                {
                    selecao.add(sessaoJogo.jogadorAtual.retornarCartaJogador(sessaoJogo.entradaJogador -1));
                    UserInterface.adicionadaCarta();
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
    
    
    
    //COMPRA UMA CARTA DE UM MONTANTE DE CARTAS
    //A FONTE PODE SER LIXO OU MONTE
    public static void comprarCarta(Sessao sessaoJogo, List<Carta> fonte)
    {
        //VERIFICA SE A FONTE ESTA VAZIA
        if(!fonte.isEmpty())
        {
            //ADICIONA A PRIMEIRA CARTA DA "PILHA" A MAO DO JOGADOR
            sessaoJogo.jogadorAtual.adicionarCartaJogador(fonte.get(fonte.size()-1));
            //REMOVE A CARTA DA FONTE
            fonte.remove(fonte.size()-1);
            //ALTERA O INDICADOR DE COMPRA
            sessaoJogo.compraEfetuada = true;
        }
        else
        {
            //MENSAGEM DE ERRO
            UserInterface.erroNaoHaCartas();
        }
    }
	
    //DESCARTA A CARTA SELECIONADA
    public static void descartarCarta(Sessao sessaoJogo)
    {
		//VERIFICA SE O JOGADOR JA REALIZOU UMA COMPRA NO TURNO
		if(sessaoJogo.compraEfetuada)
		{
			//CASO TENHA EFETUADO, ELE NAO PODERA DESCARTAR
			UserInterface.erroDescarte();
			return;
		}
        //PERGUNTA O USUARIO QUAL A CARTA A SER DESCARTADA
        UserInterface.selecaoDescarte();
        sessaoJogo.entrada = sessaoJogo.scanner.next();
        //DETERMINA O OPERANDO
        sessaoJogo.entradaJogador = Integer.parseInt(sessaoJogo.entrada);
        //DETERMINA A INTEGRIDADE DO OPERANDO
        //CASO SEJA VALIDO REMOVER A CARTA
        if(InteracaoJogoJogador.verificarEntradaJogador(sessaoJogo.entradaJogador))
        {
            //ADICIONAR CARTA AO LIXO
        	sessaoJogo.lixo.add(sessaoJogo.jogadorAtual.retornarCartaJogador(sessaoJogo.entradaJogador-1));
            //REMOVER DA MAO DO JOGADOR
        	sessaoJogo.jogadorAtual.removerCartaJogador(sessaoJogo.entradaJogador-1);
            //GERA SAIDA PARA A INTERFACE
            UserInterface.descarteCarta();
            //ENCERRA O TURNO
            FluxoJogo.anularTurno(sessaoJogo);
        }
        else
        {
            //ENTRADA INVALIDA
            UserInterface.erroEntrada();
        }
    }
}
