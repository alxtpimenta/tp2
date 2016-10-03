/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.pratico.pkg2;

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
    //REI (J) = 12
    protected int numero;
    
    //RETORNA O ID DA CARTA
    public int id()
    {
        return this.ID;
    }
    
    //RETORNA O NUMERO DA CARTA
    public int numero()
    {
        return this.numero;
    }
    
}
