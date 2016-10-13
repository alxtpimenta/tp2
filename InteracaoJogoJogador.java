package tp2.ambiente;

import java.util.List;

import tp2.cartas.Carta;
import tp2.jogo.FluxoJogo;

public class InteracaoJogoJogador
{
	//RECEBE QUANTIDADE DE JOGADORES
    public static void iniciarQtdJogadores(Sessao jogo)
    {
        //GARANTE QUE A ENTRADA ESTEJA DENTRO DOS LIMITES ESPECIFICADOS NA CLASSE DEFINE
        while(true)
        {
            if(jogo.scanner.hasNextInt())
            {
                jogo.qtdJogadores = jogo.scanner.nextInt();
                if(jogo.qtdJogadores < Define.MIN_JOGADORES | jogo.qtdJogadores > Define.MAX_JOGADORES)
                    //CASO A ENTRADA ESTEJA FORA DOS LIMITES ESPECIFICADOS, IMPRIMIR MENSAGEM DE ERRO
                    UserInterface.erroEntrada();
                else
                    //CASO CONTRARIO A ENTRADA ESTA CORRETA E O LOOP SERA FINALIZADO
                    break;
            }
            else
            {
                //CASO A ENTRADA NAO CONTENHA INTEIROS
                jogo.entrada = jogo.scanner.nextLine();
                jogo.entrada = null;
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
    public static void selecionarCartas(List<Carta> selecao,Sessao jogo, int quantidade)
    {
        //PEDE QUE O USUARIO DIGITE AS CARTAS
        UserInterface.digiteCarta();
        //INSERE O NUMERO REFERENCIADO DE CARTAS NA LISTA REFERENCIADA
        for(int i = 0; i < quantidade; i++)
        {
            //CERTIFICA QUE A ENTRADA E UM INTEIRO
            if(jogo.scanner.hasNextInt())
            {
                jogo.entrada = jogo.scanner.next();
                jogo.entradaJogador = Integer.parseInt(jogo.entrada);
                if(InteracaoJogoJogador.verificarEntradaJogador(jogo.entradaJogador))
                {
                    selecao.add(jogo.jogadorAtual.retornarCartaJogador(jogo.entradaJogador -1));
                    UserInterface.adicionada();
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
    
    
    
    //COMPRA UMA CARTA DA FONTE
    //A FONTE PODE SER LIXO OU MONTE
    public static void comprarCarta(Jogador jogadorAtual, List<Carta> fonte)
    {
        //VERIFICA SE A FONTE ESTA VAZIA
        if(!fonte.isEmpty())
        {
            //ADICIONA A PRIMEIRA CARTA DA "PILHA" A MAO DO JOGADOR
            jogadorAtual.adicionarCartaJogador(fonte.get(fonte.size()-1));
            //REMOVE A CARTA DA FONTE
            fonte.remove(fonte.size()-1);
			//ALTERA O INDICADOR DE COMPRA
			jogo.compraEfetuada = true;
        }
        else
        {
            //MENSAGEM DE ERRO
            UserInterface.erroFonteVazia();
        }
    }
	
    //DESCARTA A CARTA SELECIONADA
    public static void descartarCarta(Sessao jogo)
    {
		//VERIFICA SE O JOGADOR JA REALIZOU UMA COMPRA NO TURNO
		if(jogo.compraEfetuada)
		{
			//CASO TENHA EFETUADO, ELE NAO PODERA DESCARTAR
			UserInterface.erroDescarte();
			return;
		}
        //PERGUNTA O USUARIO QUAL A CARTA A SER DESCARTADA
        UserInterface.selecaoDescarte();
        jogo.entrada = jogo.scanner.next();
        //DETERMINA O OPERANDO
        jogo.entradaJogador = Integer.parseInt(jogo.entrada);
        //DETERMINA A INTEGRIDADE DO OPERANDO
        //CASO SEJA VALIDO REMOVER A CARTA
        if(InteracaoJogoJogador.verificarEntradaJogador(jogo.entradaJogador))
        {
            //ADICIONAR CARTA AO LIXO
            jogo.lixo.add(jogo.jogadorAtual.retornarCartaJogador(jogo.entradaJogador-1));
            //REMOVER DA MAO DO JOGADOR
            jogo.jogadorAtual.removerCartaJogador(jogo.entradaJogador-1);
            //GERA SAIDA PARA A INTERFACE
            UserInterface.descarte();
            //ENCERRA O TURNO
            FluxoJogo.anularTurno(jogo);
        }
        else
        {
            //ENTRADA INVALIDA
            UserInterface.erroEntrada();
        }
    }
}
