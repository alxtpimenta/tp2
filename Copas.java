package tp2.cartas;

/**
 *
 * @author alexandre
 */
public class Copas extends Carta 
{
    //CONSTRUTOR
    public Copas(int numeroCarta)
    {
        this.numeroCarta = numeroCarta;
        this.naipeCarta = 1;
    }
    //RETORNA A CARTA EM FORMA DE STRING
    @Override
    public String toString()
    {
        //CASOS NORMAIS
        if((this.getNumeroCarta() >= 2) && (this.getNumeroCarta() <= 10))
            return Integer.toString(this.getNumeroCarta()) + " de Copas";
        //CASOS ESPECIAIS
        switch (this.getNumeroCarta()) 
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
        return "Erro: Numero de Carta Invalido!";
        
    }
}

