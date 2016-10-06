/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.pratico.pkg2.core;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import trabalho.pratico.pkg2.modelagem.Carta;
import trabalho.pratico.pkg2.modelagem.Copas;
import trabalho.pratico.pkg2.modelagem.Espadas;
import trabalho.pratico.pkg2.modelagem.Ouros;
import trabalho.pratico.pkg2.modelagem.Paus;
import trabalho.pratico.pkg2.ui.Interface;

/**
 *
 * @author alexandre
 */
public class Procedimentos 
{
    //BUILDER PARA CONSTRUIR O BARALHO
    public static void inicializarBaralho(Ambiente jogo)
    {
        //VARIAVEIS PARA CRIAR INSTANCIAS DE CADA CLASSE DE CARTA
        Espadas espada;
        Copas copa;
        Ouros ouro;
        Paus pau;
        //INTEIRO PARA ITERACAO (DECLARAR ANTES EVITA VARIAS ALOCACOES/DESALOCACOES)
        int i;
        //ADICIONAR ESPADAS
        for(i = 0; i < Define.CARTAS_NAIPE; i++)
        {
            espada = new Espadas(i+1);
            jogo.Baralho.add(espada);
        }
        //ADICIONAR COPAS
        for(i = 0; i < Define.CARTAS_NAIPE; i++)
        {
            copa = new Copas(i+1);
            jogo.Baralho.add(copa);
        }
        //ADICIONAR OUROS
        for(i = 0; i < Define.CARTAS_NAIPE; i++)
        {
            ouro = new Ouros(i+1);
            jogo.Baralho.add(ouro);
        }
        //ADICIONAR PAUS
        for(i = 0; i < Define.CARTAS_NAIPE; i++)
        {
            pau = new Paus(i+1);
            jogo.Baralho.add(pau);
        }
        //EMBARALHAR O BARALHO
        embaralhar(jogo.Baralho);
    }
    
    //RECEBE O AMBIENDE DE JOGO E DISTRIBUI O BARALHO PARA CADA JOGADOR
    public static void distribuirCartas(Ambiente jogo)
    {
        //DAR 9 CARTAS A CADA JOGADOR E INSERIR O RESTO NO MONTE
        //AS CARTAS JA FORAM EMBARALHADAS NA INICIALIZACAO
        //VARIAVEIS PARA INDICAR OS INDICES PARA A INSERCAO
        int indice = 0;
        int fim = Define.MAX_MAO;
        //INSERIR CARTAS
        for(int i = 0; i < jogo.qtdJogadores; i++)
        {
            for(; indice < fim; indice ++)
            {
                //ADICIONA A CARTA DO BARALHO A MAO DO JOGADOR
                jogo.Jogadores.get(i).adicionarCarta(jogo.Baralho.get(indice));
            }
            //INCREMENTAR O FIM (CADA JOGADOR RECEBE A QUANTIDADE DEFINIDA DE CARTAS)
            fim = fim + Define.MAX_MAO;
        }
        //O RESTANTE DAS CARTAS SERAO INSERIDAS NO MONTE
        for(; indice < jogo.Baralho.size(); indice ++)
        {
            //INSERE CARTAS DO BARALHO NO MONTE
            jogo.Monte.add(jogo.Baralho.get(indice));
        }
    }
    
    public static void embaralhar(List<Carta> cartas)
    {
        //USA COMO SEMENTE O HORARIO DO SISTEMA
        long semente = System.nanoTime();
        //ALEATORIZA OS ITENS NA LISTA DE CARTAS, USANDO A SEMENTE
        Collections.shuffle(cartas, new Random(semente));
    }
    
    public static boolean verificarVitoria(List<Carta> mao)
    {
        if(mesmoNaipe(mao)) //SE AS CARTAS FOREM *TODAS* DO MESMO NAIPE
        {
            //VERIFICAR SEQUENCIAS
            if(mesmaSequencia(mao)) //CASO SEJA A SEQUENCIA SEJA IGUAL
                return false;
            else if(sequenciaCrescente(mao)) //CASO A SEQUENCIA SEJA CRESCENTE
                return true; //A VITORIA E CONFIRMADA
            else
                return false;
        }
        else if(diferenteNaipe(mao)) //SE AS CARTAS FOREM *TODAS* COM NAIPES DIFERENTES
        {
            //VERIFICAR SE A SEQUENCIA E A MESMA
            if(mesmaSequencia(mao))
                //CASO SEJA TRUE, A VITORIA E CONFIRMADA
                return true;
            else
                return false;
        }
        else
        {
            //CASO NAO CAIA EM NENHUM DOS CASOS A VITORIA E FALSA
            return false;
        }
    }
    
    public static boolean sequenciaCrescente(List<Carta> mao)
    {
        //USANDO A CLASSE COLECTIONS E FUNCAO LAMBDA
        //ORDENA AS CARTAS PELA NUMERACAO
        Collections.sort(mao, (Carta o1, Carta o2) -> {
            if(o1.numero() == o2.numero())
                return 0;
            return o1.numero() < o2.numero() ? -1 : 1;
        });
        
        //VERIFICAR SE A NUMERACAO E CRESCENTE
        if(mao.size() == 3)
        {
            //VERIFICA DE FORMA DECRESCENTE SE O NUMERO DA CARTA E IGUAL AO NUMERO DA CARTA ANTERIOR +1 (SE ESTA ORDENADA)
            if( (mao.get(2).numero() == (mao.get(1).numero() +1) ) && (mao.get(1).numero() == (mao.get(0).numero() +1) ) )
                return true;
            else
                return false;
        }
        else if(mao.size() == 4)
        {
            if( (mao.get(3).numero() == (mao.get(2).numero() +1) ) && (mao.get(2).numero() == (mao.get(1).numero() +1) )&& (mao.get(1).numero() == (mao.get(0).numero() +1) ))
                return true;
            else return false;
        }
        else
        {
            //ERRO DESCONHECIDO
            Interface.erroDesconhecido();
            return false;
        }
    }
    
    
    public static boolean mesmoNaipe(List<Carta> mao)
    {
        //PREDETERMINAR A SAIDA COMO TRUE
        boolean output = true;
        //VERIFICA CADA CARTA COM TODAS AS OUTRAS PARA GARANTIR QUE TENHAM O MESMO NAIPE
        for(int i = 0; i < mao.size(); i++)
            for(int j = 0; j < mao.size(); j++)
            {
                //QUANDO AS CARTAS NAO FOREM IGUAIS
                if( i != j)
                {
                    //SE FOR ENCONTRADO UM NAIPE DIFERENTE, A SAIDA E FALSE
                    if(mao.get(i).naipe() != mao.get(j).naipe())
                        output = false;
                }
            }
        //RETORNA O RESULTADO
        return output;
    }
    
    public static boolean diferenteNaipe(List<Carta> mao)
    {
        //PREDETERMINAR A SAIDA COMO TRUE
        boolean output = true;
        //VERIFICA CADA CARTA COM TODAS AS OUTRAS PARA GARANTIR QUE NAO TENHAM O MESMO NAIPE
        for(int i = 0; i < mao.size(); i++)
            for(int j = 0; j < mao.size(); j++)
            {
                //QUANDO AS CARTAS NAO FOREM IGUAIS
                if( i != j)
                {
                    //SE FOR ENCONTRADO UM NAIPE IGUAL, A SAIDA E FALSE
                    if(mao.get(i).naipe() == mao.get(j).naipe())
                        output = false;
                }
            }
        //RETORNA O RESULTADO
        return output;
    }
    
    public static boolean mesmaSequencia(List<Carta> mao)
    {
        //COMPARA TODAS AS CARTAS COM O NUMERO DA PRIMEIRA CARTA
        for(int i = 0; i < mao.size(); i++)
            if(mao.get(i).numero() != mao.get(0).numero())
                //CASO ACHE ALGUMA DESIGUALDADE, A SAIDA E FALSE
                return false;
        //CASO CONTRARIO A SAIDA E TRUE
        return true;
    }
    
    
    public static void verificarTurno(List<Carta> lixo, List<Carta> monte)
    {
        //SE O MONTE ESTIVER VAZIO, EMBARALHAR O LIXO
        if(monte.isEmpty())
            embaralhar(lixo);
    }
    
    public static boolean verificarEntrada(int entrada)
    {
        return entrada >= 1 && entrada <= 9;
    }
}
