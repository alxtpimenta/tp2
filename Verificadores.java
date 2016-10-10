package tp2.ambiente;

import java.util.Collections;
import java.util.List;
import tp2.cartas.Carta;
import tp2.jogo.UserInterface;

public class Verificadores
{
    //VERIFICA SE A MAO ENVIADA PARA A FUNCAO E VALIDA PARA DECLARAR VITORIA
    public static boolean verificarVitoria(List<Carta> mao)
    {
        if(mesmoNaipe(mao)) //SE AS CARTAS FOREM *TODAS* DO MESMO NAIPE
        {
            //VERIFICAR SEQUENCIAS
            if(mesmaSequencia(mao)) //CASO SEJA A SEQUENCIA SEJA IGUAL
                return false;
            else if(sequenciaCrescente(mao)) //CASO A SEQUENCIA SEJA CRESCENTE
                return true; //A VITORIA E CONFIRMADA
            else
                return false;
        }
        else if(diferenteNaipe(mao)) //SE AS CARTAS FOREM *TODAS* COM NAIPES DIFERENTES
        {
            //VERIFICAR SE A SEQUENCIA E A MESMA
            if(mesmaSequencia(mao))
                //CASO SEJA TRUE, A VITORIA E CONFIRMADA
                return true;
            else
                return false;
        }
        else
        {
            //CASO NAO CAIA EM NENHUM DOS CASOS A VITORIA E FALSA
            return false;
        }
    }
    
    //VERIFICA SE A ORDEM DAS CARTAS ENVIADAS PARA A FUNCAO E CRESCENTE
    public static boolean sequenciaCrescente(List<Carta> mao)
    {
        //USANDO A CLASSE COLECTIONS E FUNCAO LAMBDA
        //ORDENA AS CARTAS PELA NUMERACAO
        Collections.sort(mao, (Carta o1, Carta o2) -> {
            if(o1.getNumeroCarta() == o2.getNumeroCarta())
                return 0;
            return o1.getNumeroCarta() < o2.getNumeroCarta() ? -1 : 1;
        });
        
        //VERIFICAR SE A NUMERACAO E CRESCENTE
        if(mao.size() == 3)
        {
            //VERIFICA DE FORMA DECRESCENTE SE O NUMERO DA CARTA E IGUAL AO NUMERO DA CARTA ANTERIOR +1 (SE ESTA ORDENADA)
            if( (mao.get(2).getNumeroCarta() == (mao.get(1).getNumeroCarta() +1) ) && (mao.get(1).getNumeroCarta() == (mao.get(0).getNumeroCarta() +1) ) )
                return true;
            else
                return false;
        }
        else if(mao.size() == 4)
        {
            if( (mao.get(3).getNumeroCarta() == (mao.get(2).getNumeroCarta() +1) ) && (mao.get(2).getNumeroCarta() == (mao.get(1).getNumeroCarta() +1) )&& (mao.get(1).getNumeroCarta() == (mao.get(0).getNumeroCarta() +1) ))
                return true;
            else return false;
        }
        else
        {
            //ERRO DESCONHECIDO
        	UserInterface.erroDesconhecido();
            return false;
        }
    }
    
    //VERIFICA SE *TODAS* AS CARTAS ENVIADAS A FUNCAO SAO DO MESMO NAIPE
    public static boolean mesmoNaipe(List<Carta> mao)
    {
        //PREDETERMINAR A SAIDA COMO TRUE
        boolean output = true;
        //VERIFICA CADA CARTA COM TODAS AS OUTRAS PARA GARANTIR QUE TENHAM O MESMO NAIPE
        for(int i = 0; i < mao.size(); i++)
            for(int j = 0; j < mao.size(); j++)
            {
                //QUANDO AS CARTAS NAO FOREM IGUAIS
                if( i != j)
                {
                    //SE FOR ENCONTRADO UM NAIPE DIFERENTE, A SAIDA E FALSE
                    if(mao.get(i).getNaipeCarta() != mao.get(j).getNaipeCarta())
                        output = false;
                }
            }
        //RETORNA O RESULTADO
        return output;
    }
    
    //VERIFICA SE *TODAS* AS FUNCOES ENVIADAS A CARTAS SAO DE NAIPES DIFERENTES
    public static boolean diferenteNaipe(List<Carta> mao)
    {
        //PREDETERMINAR A SAIDA COMO TRUE
        boolean output = true;
        //VERIFICA CADA CARTA COM TODAS AS OUTRAS PARA GARANTIR QUE NAO TENHAM O MESMO NAIPE
        for(int i = 0; i < mao.size(); i++)
            for(int j = 0; j < mao.size(); j++)
            {
                //QUANDO AS CARTAS NAO FOREM IGUAIS
                if( i != j)
                {
                    //SE FOR ENCONTRADO UM NAIPE IGUAL, A SAIDA E FALSE
                    if(mao.get(i).getNaipeCarta() == mao.get(j).getNaipeCarta())
                        output = false;
                }
            }
        //RETORNA O RESULTADO
        return output;
    }
    
    //VERIFICA SE A SEQUENCIA DAS CARTAS ENVIADA A FUNCAO E A MESMA
    public static boolean mesmaSequencia(List<Carta> mao)
    {
        //COMPARA TODAS AS CARTAS COM O NUMERO DA PRIMEIRA CARTA
        for(int i = 0; i < mao.size(); i++)
            if(mao.get(i).getNumeroCarta() != mao.get(0).getNumeroCarta())
                //CASO ACHE ALGUMA DESIGUALDADE, A SAIDA E FALSE
                return false;
        //CASO CONTRARIO A SAIDA E TRUE
        return true;
    }
    
    //VERIFICA SE A ENTRADA DO USUARIO ESTA EM CONFORMES COM O DEFINIDO NA CLASSE DEFINE
    public static boolean verificarEntrada(int entrada)
    {
        return entrada >= 1 && entrada <= 10;
    }
}
