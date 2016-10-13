package tp2.cartas;

/**
*
* @author alexandre
*/
public class Ouros extends Carta
{
   //CONSTRUTOR
   public Ouros(int numeroCarta)
   {
       this.numeroCarta = numeroCarta;
       this.naipeCarta = 3;
   }
   
   //RETORNA A CARTA EM FORMA DE STRING
   @Override
   public String toString()
   {
       //CASOS NORMAIS
       if((this.getNumeroCarta() >= 2) && (this.getNumeroCarta() <= 10))
           return Integer.toString(this.getNumeroCarta()) + " de Ouros";
       //CASOS ESPECIAIS
       switch (this.getNumeroCarta()) 
       {
           case 1:
               return "As de Ouros";
           case 11:
               return "Valete de Ouros";
           case 12:
               return "Rainha de Ouros";
           case 13:
               return "Rei de Ouros";
           default:
               break;
       }
       
       //CASO O NUMERO NAO ESTEJA NO INTERVALO
       return "Erro: Numero de Carta Invalido!";
       
   }
}
