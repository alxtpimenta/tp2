package tp2.jogo;

import tp2.ambiente.Define;
import tp2.ambiente.UserInterface;
import tp2.procedimentoseinteracoes.FluxoJogo;

public class AcaoPulaTurno implements CadeiaDeAcao
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
		//SE O JOGADOR PUDER PULAR O TURNO INVOCA O METODO QUE PULA O TURNO
        	if(("P".equals(requisicao.sessaoJogo.entrada) | "p".equals(requisicao.sessaoJogo.entrada)) && requisicao.sessaoJogo.jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
        	{
        		FluxoJogo.anularTurno(requisicao.sessaoJogo);
        	}
		//CASO ISSO NAO SEJA POSSIVEL, HOUVE UMA REQUISICAO INVALIDA E IMPRIME ISSO NA TELA
        	else
        	{
            		UserInterface.erroEntrada();
        	}			
	}
}
