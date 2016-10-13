package tp2.chainofresponsability;

public interface Cadeia 
{
	//APONTA PARA O PROXIMO MÉTODO A TRATAR A REQUISICAO
	public void setProxCadeia(Cadeia proxCadeia);

	//EXECUTAR A REQUISICAO DO JOGADOR DE ACORDO COM AS DEFINICOES	
	public void acaoJogador(Acao requisicao);
}
