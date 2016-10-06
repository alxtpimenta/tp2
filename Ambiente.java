/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.pratico.pkg2.core;

import java.util.ArrayList;
import java.util.List;
import trabalho.pratico.pkg2.modelagem.Carta;
import trabalho.pratico.pkg2.modelagem.Jogador;

/**
 *
 * @author alexandre
 */
public class Ambiente 
{
    private static Ambiente instancia = null;
    
    protected Ambiente(){} //PARA DESATIVAR INSTANCIACAO (SINGLETON)
    
    //CONSTRUTOR
    public static Ambiente retornarInstancia()
    {
        if(instancia == null)
            instancia = new Ambiente();
        
        return instancia;
    }
    
    //CARTAS DO AMBIENTE DE JOGO
    public List<Carta> Baralho;
    public List<Carta> Monte;
    public List<Carta> Lixo;
    
    //JOGADORES
    public List<Jogador> Jogadores;
    //JOGADOR ATIVO NO TURNO
    public Jogador jogadorAtual;
    
    //CONTROLES DO LOOP DE JOGO
    public boolean turno;
    public boolean jogo;
    
    //VARIAVEIS DE ENTRADA
    public String entrada;
    
    //VARIAVEIS DO JOGO
    public int operador;
    public int idJogadorAtual;
    public int qtdJogadores;
    
    public void inicializar()
    {
        //ALOCA A LISTA DE CARTAS
        this.Baralho = new ArrayList<>();
        this.Monte = new ArrayList<>();
        this.Lixo = new ArrayList<>();
        //ALOCA A LISTA DE JOGADORES
        this.Jogadores = new ArrayList<>();
        //INICIALIZA AS VARIAVEIS
        jogadorAtual = null;
        turno = true;
        jogo = true;
        entrada = null;
        operador = 0;
        idJogadorAtual = 0;
        qtdJogadores = 0;
    
    }
}
