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
public class Copas extends Carta 
{
    //CONSTRUTOR
    public Copas(int ID, int numero)
    {
        this.ID = ID;
        this.numero = numero;
    }
    //RETORNA A CARTA EM FORMA DE STRING
    @Override
    public String toString()
    {
        //CASOS NORMAIS
        if((this.numero() >= 2) && (this.numero() <= 10))
            return Integer.toString(this.numero()) + " de Copas";
        //CASOS ESPECIAIS
        switch (this.numero()) 
        {
            case 1:
                return "As de Copas";
            case 11:
                return "Valete de Copas";
            case 12:
                return "Rainha de Copas";
            case 13:
                return "Rei de Copas";
            default:
                break;
        }
        
        //CASO O NUMERO NAO ESTEJA NO INTERVALO
        return "Erro: Numero de Carta Invalido! ID da Carta:" + Integer.toString(this.ID);
        
    }
    
}
