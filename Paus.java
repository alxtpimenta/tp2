package tp2.cartas;

/**
*
* @author alexandre
*/
public class Paus extends Carta
{
   //CONSTRUTOR
   public Paus(int numeroCarta)
   {
       this.numeroCarta = numeroCarta;
       this.naipeCarta = 4;
   }
   
   //RETORNA A CARTA EM FORMA DE STRING
   @Override
   public String toString()
   {
       //CASOS NORMAIS
       if((this.getNumeroCarta() >= 2) && (this.getNumeroCarta() <= 10))
           return Integer.toString(this.getNumeroCarta()) + " de Paus";
       //CASOS ESPECIAIS
       switch (this.getNumeroCarta()) 
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
       return "Erro: Numero de Carta Invalido!";
       
   }
}
