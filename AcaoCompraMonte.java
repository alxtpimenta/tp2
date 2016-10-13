package tp2.chainofresponsability;

import tp2.ambiente.Define;
import tp2.ambiente.InteracaoJogoJogador;


public class AcaoCompraMonte implements Cadeia
{
	//VARIAVEL QUE APONTA PAR AO PROXIMO METODO DA CADEIA
	private Cadeia proxNaCadeia;

	//METODO QUE SETA O "APONTADOR" PARA O PROXIMO DA CADEIA
	@Override
	public void setProxCadeia(Cadeia proxCadeia)
	{
		this.proxNaCadeia = proxCadeia;
	}

	@Override
	public void acaoJogador(Acao requisicao) 
	{
		//CASO A ACAO SELECIONADA SEJA DE COMPRAR DO MONTE E SEJA POSSIVEL FAZER ISSO INVOCA O METODO PARA A MESMA
        	if(("M".equals(requisicao.jogo.entrada) | "m".equals(requisicao.jogo.entrada)) && requisicao.jogo.jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
        	{
       	 		InteracaoJogoJogador.comprarCarta(requisicao.jogo.jogadorAtual, requisicao.jogo.monte);
        	}
		//CASO ISSO NAO SEJA POSSIVEL APONTA PARA O PROXIMO METODO DA CADEIA
        	else
        	{
        		proxNaCadeia.acaoJogador(requisicao);
        	}		
	}
}
