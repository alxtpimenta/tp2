/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.pratico.pkg2.modelagem;

/**
 *
 * @author alexandre
 */
public abstract class Carta 
{
    //INDICA O NUMERO DA CARTA
    //AS = 1
    //CARTAS NORMAIS = 2 A 10
    //RAINHA (Q) = 11
    //REI (K) = 12
    protected int numero;
    
    //1 COPAS
    //2 ESPADAS
    //3 OUROS
    //4 PAUS
    protected int tipo;
    
    //RETORNA O NUMERO DA CARTA
    public int numero()
    {
        return this.numero;
    }
    
    public int naipe()
    {
        return this.tipo;
    }
    
}

