package tp2.jogo;

public interface CadeiaDeAcao 
{
	//APONTA PARA O PROXIMO MÉTODO A TRATAR A REQUISICAO
	public void setProxCadeia(CadeiaDeAcao proxCadeia);

	//EXECUTAR A REQUISICAO DO JOGADOR DE ACORDO COM AS DEFINICOES	
	public void acaoJogador(Acao requisicao);
}
