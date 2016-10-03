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
public class Paus extends Carta
{
    //CONSTRUTOR
    public Paus(int ID, int numero)
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
            return Integer.toString(this.numero()) + " de Paus";
        //CASOS ESPECIAIS
        switch (this.numero()) 
        {
            case 1:
                return "As de Paus";
            case 11:
                return "Valete de Paus";
            case 12:
                return "Rainha de Paus";
            case 13:
                return "Rei de Paus";
            default:
                break;
        }
        
        //CASO O NUMERO NAO ESTEJA NO INTERVALO
        return "Erro: Numero de Carta Invalido! ID da Carta:" + Integer.toString(this.ID);
        
    }
}
