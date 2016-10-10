package tp2.ambiente;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import tp2.cartas.*;
import tp2.jogo.Define;

public class Baralho {

	//CONSTRUTOR DO BARALHO
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
            jogo.baralho.add(espada);
        }
        //ADICIONAR COPAS
        for(i = 0; i < Define.CARTAS_NAIPE; i++)
        {
            copa = new Copas(i+1);
            jogo.baralho.add(copa);
        }
        //ADICIONAR OUROS
        for(i = 0; i < Define.CARTAS_NAIPE; i++)
        {
            ouro = new Ouros(i+1);
            jogo.baralho.add(ouro);
        }
        //ADICIONAR PAUS
        for(i = 0; i < Define.CARTAS_NAIPE; i++)
        {
            pau = new Paus(i+1);
            jogo.baralho.add(pau);
        }
        //EMBARALHAR O BARALHO
        embaralharBaralho(jogo.baralho);
    }

    //PROCEDIMENTO QUE RANDOMIZA AS CARTAS DO BARALHO
    public static void embaralharBaralho(List<Carta> cartas)
    {
        //USA COMO SEMENTE O HORARIO DO SISTEMA
        long semente = System.nanoTime();
        //ALEATORIZA OS ITENS NA LISTA DE CARTAS, USANDO A SEMENTE
        Collections.shuffle(cartas, new Random(semente));
    }
    
    //CASO O MONTE ESTEJA VAZIO, O LIXO É EMBARALHADO PARA SE TORNAR MONTE
    public static void verificaMonteVazio(List<Carta> lixo, List<Carta> monte)
    {
        //SE O MONTE ESTIVER VAZIO, EMBARALHAR O LIXO
        if(monte.isEmpty())
        	embaralharBaralho(lixo);
    }
    
    
    
}
