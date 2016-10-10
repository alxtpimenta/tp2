package tp2.cartas;

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
   protected int numeroCarta;
   
   //1 COPAS
   //2 ESPADAS
   //3 OUROS
   //4 PAUS
   protected int naipeCarta;
   
   //RETORNA O NUMERO DA CARTA
   public int getNumeroCarta()
   {
       return this.numeroCarta;
   }
   
   //RETORNA O NAIPE DA CARTA
   public int getNaipeCarta()
   {
       return this.naipeCarta;
   }
   
}

