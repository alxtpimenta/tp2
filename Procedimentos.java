/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.pratico.pkg2;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author alexandre
 */
public class Procedimentos 
{
    //TODOS OS PROCEDIMENTOS SAO STATIC, PARA NAO SER NECESSARIO INSTANCIACAO
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
        //EMBARALHAR AS CARTAS
        embaralhar(cartas);
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
        //NAO IMPLEMENTADO
        return true;
    }
}
