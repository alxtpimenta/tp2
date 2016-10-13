package tp2.cartas;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Baralho {

	//CONSTRUTOR DO BARALHO
    public static void inicializarBaralho(List<Carta> cartas)
    {
        //VARIAVEIS PARA CRIAR INSTANCIAS DE CADA CLASSE DE CARTA
        Espadas espada;
        Copas copa;
        Ouros ouro;
        Paus pau;
        //INTEIRO PARA ITERACAO (DECLARAR ANTES EVITA VARIAS ALOCACOES/DESALOCACOES)
        int i;
        //ADICIONAR ESPADAS
        for(i = 0; i < 12; i++)
        {
            espada = new Espadas(i+1);
            cartas.add(espada);
        }
        //ADICIONAR COPAS
        for(i = 0; i < 12; i++)
        {
            copa = new Copas(i+1);
            cartas.add(copa);
        }
        //ADICIONAR OUROS
        for(i = 0; i < 12; i++)
        {
            ouro = new Ouros(i+1);
            cartas.add(ouro);
        }
        //ADICIONAR PAUS
        for(i = 0; i < 12; i++)
        {
            pau = new Paus(i+1);
            cartas.add(pau);
        }
        //EMBARALHAR O BARALHO
        embaralharBaralho(cartas);
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
    public static void transformaLixoMonte(List<Carta> lixo, List<Carta> monte)
    {
        	//EMBARALHAR O LIXO
        	embaralharBaralho(lixo);
        	
        	//TRANSFORMA O LIXO NO MONTE
        	monte = lixo;
        	
        	//LIMPA O LIXO
        	lixo.clear();
    }
    
    
    
}
