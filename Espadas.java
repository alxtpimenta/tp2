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
public class Espadas extends Carta 
{
    //CONSTRUTOR
    public Espadas(int numero)
    {
        this.numero = numero;
        this.tipo = 2;
    }
    //RETORNA A CARTA EM FORMA DE STRING
    @Override
    public String toString()
    {
        //CASOS NORMAIS
        if((this.numero() >= 2) && (this.numero() <= 10))
            return Integer.toString(this.numero()) + " de Espadas";
        //CASOS ESPECIAIS
        switch (this.numero()) 
        {
            case 1:
                return "As de Espadas";
            case 11:
                return "Valete de Espadas";
            case 12:
                return "Rainha de Espadas";
            case 13:
                return "Rei de Espadas";
            default:
                break;
        }
        
        //CASO O NUMERO NAO ESTEJA NO INTERVALO
        return "Erro: Numero de Carta Invalido!";
    }
}
