package tp2.chainofresponsability;

import tp2.ambiente.Define;
import tp2.ambiente.InteracaoJogoJogador;


public class AcaoDescartar implements Cadeia
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
		//CASO A ACAO SELECIONADA SEJA DE DESCARTE E SEJA POSSIVEL FAZER ISSO INVOCA O METODO PARA A MESMA
     		if(("D".equals(requisicao.jogo.entrada) | "d".equals(requisicao.jogo.entrada)) && requisicao.jogo.jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
       		{
        		InteracaoJogoJogador.descartarCarta(requisicao.jogo);
        	}
		//CASO ISSO NAO SEJA POSSIVEL APONTA PARA O PROXIMO METODO DA CADEIA
        	else
       		{
        		proxNaCadeia.acaoJogador(requisicao);
        	}		
	}
	

}
