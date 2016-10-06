/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.pratico.pkg2.modelagem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexandre
 */
public class Jogador 
{
    //CONSTRUTOR
    public Jogador(String nome)
    {
        this.nome = nome;
    }
    //NOME DO JOGADOR
    private final String nome;
    //MAO DE CARTAS DO JOGADOR
    private List<Carta> mao;
    
    //RETORNA O NOME
    public String nome()
    {
        return this.nome;
    }
    
    //INICIALIZAR VETOR DE BARALHO
    public void inicializar()
    {
        this.mao = new ArrayList<>();
    }
    
    
    //ADICIONAR CARTA
    public void adicionarCarta(Carta c)
    {
        this.mao.add(c);
    }
    
    //REMOVER CARTA
    public void removerCarta(int index)
    {
        this.mao.remove(index);
    }
    
    //RETORNAR CARTA
    public Carta retornarCarta(int index)
    {
        return this.mao.get(index);
    }
    
    //RETORNA QUANTIDADE DE CARTAS NA MAO
    public int tamanho()
    {
        return this.mao.size();
    }
}
