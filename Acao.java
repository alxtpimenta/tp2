package tp2.jogo;


import tp2.ambiente.Sessao;

public class Acao 
{
	//VARIAVEIS NECESSARIAS PARA A EXECUCAO DA REQUISICAO
	Sessao sessaoJogo;
	private String requisicao;
	
	//CONSTRUTOR DA REQUISICAO
	public Acao(Sessao sessaoJogo, String requisicao)
	{
		this.sessaoJogo = sessaoJogo;
		this.requisicao = requisicao;
	}
	
	//GETTER DA SESSAO DO JOGO
	public Sessao getSessaoJogo()
	{
		return this.sessaoJogo;
	}
	

	//GETTER DA REQUISICAO DO JOGADOR
	public String getRequisicao()
	{
		return this.requisicao;
	}
}
