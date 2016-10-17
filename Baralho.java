package tp2.cartas;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import tp2.ambiente.Define;

public class Baralho 
{

	//CONSTRUTOR DO BARALHO
    public static void inicializarBaralho(List<Carta> cartas)
    {
        //VARIAVEIS PARA CRIAR INSTANCIAS DE CADA CLASSE DE CARTA
        Espadas espada;
        Copas copa;
        Ouros ouro;
        Paus pau;

        //ADICIONAR CARTAS
        for(int i = 0; i < Define.CARTAS_NAIPE; i++)
        {
            //ESPADAS
            espada = new Espadas(i+1);
            cartas.add(espada);
            //COPAS
            copa = new Copas(i+1);
            cartas.add(copa);
            //OUROS
            ouro = new Ouros(i+1);
            cartas.add(ouro);
            //PAUS
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
    
    //CASO O MONTE ESTEJA VAZIO, O LIXO E EMBARALHADO PARA SE TORNAR MONTE
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
