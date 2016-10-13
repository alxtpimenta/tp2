package tp2.chainofresponsability;

import tp2.ambiente.UserInterface;
import tp2.jogo.FluxoJogo;

public class AcaoPulaTurno implements Cadeia
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
		//SE O JOGADOR PUDER PULAR O TURNO INVOCA O METODO QUE PULA O TURNO
        	if("P".equals(requisicao.jogo.entrada) | "p".equals(requisicao.jogo.entrada))
        	{
        		FluxoJogo.anularTurno(requisicao.jogo);
        	}
		//CASO ISSO NAO SEJA POSSIVEL, HOUVE UMA REQUISICAO INVALIDA E IMPRIME ISSO NA TELA
        	else
        	{
            		UserInterface.erroEntrada();
        	}			
	}
}
