package tp2.cartas;

/**
*
* @author alexandre
*/
public class Espadas extends Carta 
{
   //CONSTRUTOR
   public Espadas(int numeroCarta)
   {
       this.numeroCarta = numeroCarta;
       this.naipeCarta = 2;
   }
   //RETORNA A CARTA EM FORMA DE STRING
   @Override
   public String toString()
   {
       //CASOS NORMAIS
       if((this.getNumeroCarta() >= 2) && (this.getNumeroCarta() <= 10))
           return Integer.toString(this.getNumeroCarta()) + " de Espadas";
       //CASOS ESPECIAIS
       switch (this.getNumeroCarta()) 
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
