package tp2.jogo;

import tp2.ambiente.*;

public class Jogo 
{
	public static void main(String[] args) 
    {
        //INICIALIZAR SESSAO DO JOGO
        Sessao jogo;
        jogo = Sessao.retornarInstancia();
        jogo.inicializar();

        //INICIALIZA O BARALHO
        Baralho.inicializarBaralho(jogo.baralho);
        //IMPRIMIR TELA DE BOAS VINDAS, E PEDIR DADOS DOS JOGADORES
        UserInterface.boasVindas();
        
        //GARANTE QUE A ENTRADA ESTEJA DENTRO DOS LIMITES ESPECIFICADOS
        while(true)
        {
            if(jogo.scanner.hasNextInt())
            {
                jogo.qtdJogadores = jogo.scanner.nextInt();
                if(jogo.qtdJogadores < Define.MIN_JOGADORES | jogo.qtdJogadores > Define.MAX_JOGADORES)
                    //CASO A ENTRADA ESTEJA FORA DOS LIMITES ESPECIFICADOS, IMPRIMIR MENSAGEM DE ERRO
                    UserInterface.erroEntrada();
                else
                    //CASO CONTRARIO, FINALIZE O LOOP
                    break;
            }
            else
            {
                //CASO A ENTRADA NAO CONTENHA INTEIROS
                jogo.entrada = jogo.scanner.nextLine();
                jogo.entrada = null;
                UserInterface.skip();
            	UserInterface.erroEntrada();
            }
                
        }
        
        //CRIA OS JOGADORES
        Jogador.criarJogadores(jogo.jogadores, jogo.qtdJogadores, jogo.scanner);
        
        //DISTRIBUI AS CARTAS
        Jogador.distribuirCartas(jogo.jogadores, jogo.qtdJogadores, jogo.monte, jogo.baralho);
        
        //"LIMPA" O CONSOLE
        UserInterface.skip();
        //LOOP DE CONTROLE DO JOGO
        while(jogo.jogo)
        {
            //VERIFICACOES PARA O NOVO TURNO
            Baralho.verificaMonteVazio(jogo.lixo,jogo.monte);
            //LOOP DE CONTROLE DO TURNO
            while(jogo.turno)
            {
                //ATUALIZA O JOGADOR ATUAL
                jogo.jogadorAtual = jogo.jogadores.get(jogo.idJogadorAtual);
                
                //IMPRIMIR INTERFACE
                if(jogo.jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
                    if(jogo.lixo.size() > 1)
                        UserInterface.imprimirSessaoCompra(jogo.jogadorAtual, jogo.lixo.get(jogo.lixo.size()-1));
                    else
                        UserInterface.imprimirSessaoCompra(jogo.jogadorAtual, null);
                //CASO O JOGADOR NAO PRECISE COMPRAR
                else if(jogo.jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
                    UserInterface.imprimirSessao(jogo.jogadorAtual);
                
                //LE A ENTRADA DO USUARIO
                jogo.entrada = jogo.scanner.next();
                
                //BATER
                if(("B".equals(jogo.entrada) | "b".equals(jogo.entrada)) && jogo.jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
                {
                    Procedimentos.pife(jogo);
                }
                //DESCARTAR
                else if(("D".equals(jogo.entrada) | "d".equals(jogo.entrada)) && jogo.jogadorAtual.tamanhoMaoJogador() == Define.MAX_MAO)
                {
                    //DESCARTA A CARTA
                    Procedimentos.descartarCarta(jogo);
                }
                //COMPRAR DO LIXO
                else if(("L".equals(jogo.entrada) | "l".equals(jogo.entrada)) && jogo.jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
                {
                    Procedimentos.comprarCarta(jogo.jogadorAtual, jogo.lixo);
                }
                //COMPRAR DO monte
                else if(("M".equals(jogo.entrada) | "m".equals(jogo.entrada)) && jogo.jogadorAtual.tamanhoMaoJogador() == Define.MIN_MAO)
                {
                    Procedimentos.comprarCarta(jogo.jogadorAtual, jogo.monte);
                }
                //PULAR O TURNO
                else if("P".equals(jogo.entrada) | "p".equals(jogo.entrada))
                {
                    Procedimentos.anularTurno(jogo);
                }
                else
                {
                    //ENTRADA INVALIDA
                    UserInterface.erroEntrada();
                }
            }
            //INCREMENTA O IDENTIFICADOR DO JOGADOR ATUAL
            jogo.idJogadorAtual++;
            //CASO O IDENTIFICADOR TENHA EXCEDIDO A QUANTIDADE DE JOGADORES
            //RESETAR O IDENTIFICADOR
            if(jogo.idJogadorAtual == jogo.qtdJogadores)
                jogo.idJogadorAtual = 0;
            //VALIDA O LOOP DE CONTROLE DO TURNO
            jogo.turno = true;
        }
        //IMPORTANTE NOTAR QUE CADA CARTA DO BARALHO SOMENTE TERA UMA INSTANCIA
        //AS CARTAS SERAO INCLUIDAS E REMOVIDAS POR REFERENCIA NA EXECUCAO
    }
        
}
