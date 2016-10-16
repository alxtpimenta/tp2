package tp2.jogo;

import tp2.ambiente.Define;
import tp2.ambiente.UserInterface;
import tp2.procedimentoseinteracoes.CondicoesVitoria;
import tp2.procedimentoseinteracoes.InteracaoJogoJogador;


public class AcaoBater implements CadeiaDeAcao
{
	//VARIAVEL QUE APONTA PAR AO PROXIMO METODO DA CADEIA
	private CadeiaDeAcao proxNaCadeia;

	//METODO QUE SETA O "APONTADOR" PARA O PROXIMO DA CADEIA
	@Override
	public void setProxCadeia(CadeiaDeAcao proxCadeia)
	{
		this.proxNaCadeia = proxCadeia;
	}

	@Override
	public void acaoJogador(Acao requisicao) 
	{
		//CASO A ACAO SELECIONADA SEJA DE BATER E SEJA POSSIVEL FAZER ISSO INVOCA O METODO PARA A MESMA
        	if(("B".equals(requisicao.sessaoJogo.entrada) | "b".equals(requisicao.sessaoJogo.entrada)) && requisicao.sessaoJogo.jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
       		{
           		CondicoesVitoria.pifeBatida(requisicao.sessaoJogo);
           		//CASO A BATIDA SEJA INVALIDA UMD ESCARTE DEVE SER EFETUADO
           		if(requisicao.sessaoJogo.batidaInefetiva == true)
           		{
           			UserInterface.batidaInefetivaDescarte();
           			InteracaoJogoJogador.descartarCarta(requisicao.sessaoJogo);
           			requisicao.sessaoJogo.batidaInefetiva = false;
           		}
        	}
		//CASO ISSO NAO SEJA POSSIVEL APONTA PARA O PROXIMO METODO DA CADEIA
        	else
        	{
        		proxNaCadeia.acaoJogador(requisicao);
        	}
}

}
