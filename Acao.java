package tp2.chainofresponsability;

import tp2.ambiente.Sessao;

public class Acao 
{

	//VARIAVEIS NECESSARIAS PARA A EXECUCAO DA REQUISICAO
	Sessao jogo;
	private String requisicao;
	
	//CONSTRUTOR DA REQUISICAO
	public Acao(Sessao jogo, String requisicao)
	{
		this.jogo = jogo;
		this.requisicao = requisicao;
	}
	
	//GETTER DA SESSAO DO JOGO
	public Sessao getJogo()
	{
		return this.jogo;
	}
	

	//GETTER DA REQUISICAO DO JOGADOR
	public String getRequisicao()
	{
		return this.requisicao;
	}
}
