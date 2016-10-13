package tp2.chainofresponsability;

import tp2.ambiente.Define;
import tp2.jogo.CondicoesVitoria;


public class AcaoBater implements Cadeia
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
		//CASO A ACAO SELECIONADA SEJA DE BATER E SEJA POSSIVEL FAZER ISSO INVOCA O METODO PARA A MESMA
        	if(("B".equals(requisicao.jogo.entrada) | "b".equals(requisicao.jogo.entrada)) && requisicao.jogo.jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
       		{
           		CondicoesVitoria.pife(requisicao.jogo);
        	}
		//CASO ISSO NAO SEJA POSSIVEL APONTA PARA O PROXIMO METODO DA CADEIA
        	else
        	{
        		proxNaCadeia.acaoJogador(requisicao);
        	}
	}
	

}
