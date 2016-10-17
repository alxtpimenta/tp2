package tp2.jogo;


import tp2.ambiente.Define;
import tp2.procedimentoseinteracoes.InteracaoJogoJogador;


public class AcaoCompraMonte implements CadeiaDeAcao
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
		//CASO A ACAO SELECIONADA SEJA DE COMPRAR DO MONTE E SEJA POSSIVEL FAZER ISSO INVOCA O METODO PARA A MESMA
        	if(("M".equals(requisicao.sessaoJogo.entrada) | "m".equals(requisicao.sessaoJogo.entrada)) && requisicao.sessaoJogo.jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
        	{
       	 		InteracaoJogoJogador.comprarCarta(requisicao.sessaoJogo, requisicao.sessaoJogo.monte);
        	}
		//CASO ISSO NAO SEJA POSSIVEL APONTA PARA O PROXIMO METODO DA CADEIA
        	else
        	{
        		proxNaCadeia.acaoJogador(requisicao);
        	}		
	}
}
