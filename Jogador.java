/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.pratico.pkg2;

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
    //NOME
    private String nome;
    //MAO DE CARTAS DO JOGADOR
    public List<Carta> mao;
    
    //RETORNA O NOME
    public String nome()
    {
        return this.nome;
    }
    
    //RETORNA O ID
    public int id()
    {
        return this.id;
    }
    
}
