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
    //IDENTIFICADOR
    protected int ID;
    
    //INDICA O NUMERO DA CARTA
    //AS = 1
    //CARTAS NORMAIS = 2 A 10
    //VALETE (J) = 11
    //RAINHA (Q) = 12
    //REI(K) = 13
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
