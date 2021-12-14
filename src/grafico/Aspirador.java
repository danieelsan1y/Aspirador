/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafico;

import java.util.Random;

/**
 *
 * @author Lucas
 */
public class Aspirador {
    protected int linha;
    protected int coluna;
    protected int direcao;
    protected boolean interruptor;
    protected Esteira [] direcoes = new Esteira[4];
    protected Esteira esteira;
    protected Sensor sensorSujeira, sensorParede;
    protected Sugador sugador;
    protected boolean [][] caminho_percorrido;
    
    public Aspirador(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
        interruptor = false; //O Estado inicial do Aspirador sempre será Desligado.
        
        //Aqui é estabelecido um valor para cada direção que Aspirador vai andar na esteira.
        direcoes[0] = new Norte();
        direcoes[1] = new Leste();
        direcoes[2] = new Sul();
        direcoes[3] = new Oeste();
        
        //Aqui o Aspirador sempre começará direcionado ao sul.
        direcao = 2; 
        esteira = direcoes[direcao];
        
        sensorSujeira = new SensorSujeira();
        sensorParede = new SensorParede();
        sugador = new Sugador();
    }
        //Método que inicializa o CaminhoPercorrido pelo Aspirador dentro da sala.
        protected void iniciarCaminhoPercorrido(Sala sala){ 
            caminho_percorrido = new boolean[sala.qdeLinhas()][sala.qdeColunas()];
            for(int i = 0; i < sala.qdeLinhas() ; i++){
                for(int j = 0; j < sala.qdeColunas() ; j++){
                    caminho_percorrido[i][j] = false;
                }
            }
        }
        
        //Método que deixará marcado o caminho percorrido pelo aspirador dentro da sala.
        protected void marcarCaminhoPercorrido(){
            caminho_percorrido[linha][coluna] = true;
        }
        
        //Método booleano que fará verificações se o aspirador já percorreu toda a sala
        protected boolean jaPercorreuTodaSala(Sala sala){
            for(int i = 0; i < sala.qdeLinhas() ; i++){
                for(int j = 0; j < sala.qdeColunas() ; j++){
                    if(!caminho_percorrido[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }
        
        //Método para ligar o aspirador
        public void ligar(){
            interruptor = true;
        }
        
        //Método para desligar o aspirador
        protected void desligar(){
            interruptor = false;
        }
        
        //Método booleano para verificar se o aspirador esta ligado ou desligado
        protected boolean estaLigado(){
            return interruptor;
        }
        
        //Método utilizando a importação random para gerar um valor aleatório.
        protected int qualAcaoRealizar(){
            Random moeda = new Random();
            Adapter.obterInstancia().acaoRealizar();
            return (moeda.nextInt(100)%2);
        }
        
        //Método utilizando a importação random para gerar um valor aleatório para mudar a direção do aspirador.
        protected void mudarDirecao(){
            Random moeda = new Random();
            int direcao = (moeda.nextInt(100)%2);
            
            //Condicional que definirá: 0 para esquerdar/ 1 para direita
            if(direcao == 0){
                //Ação para Esqueda
                this.direcao--;
                if(this.direcao == -1){
                    this.direcao = 3;
                }
            }
            else{
                //Ação para Direta
                this.direcao++;
                if(this.direcao == 4){
                    this.direcao = 0;
                }
            }
            esteira = direcoes[this.direcao]; //Ação de virar na esteira.
            Adapter.obterInstancia().mudarDirecaoAspirador(esteira.direcao());
        }
        
        protected boolean mover(Sala sala){
            int linha = this.linha;
            int coluna = this.coluna;
            ParametroPorReferencia prlinha = new ParametroPorReferencia();
            ParametroPorReferencia prcoluna = new ParametroPorReferencia();
            prlinha.valor = linha;
            prcoluna.valor = coluna;
            
            //Gera a provável posição para onde o aspirador deseja mover. Norte, Leste, Sul e Oeste.
            esteira.ativar(prlinha, prcoluna);
            linha = prlinha.valor;
            coluna = prcoluna.valor;
            
            //Condicional se o aspirador conseguiu mover
            if(!sensorParede.ativar(linha, coluna, sala)){
                this.linha = linha;
                this.coluna = coluna;
                Adapter.obterInstancia().moverAspirador(linha, coluna, esteira.direcao());
                return true;
            }
            return false; //Não conseguiu mover
        }
        
        protected void limpar(Sala sala){
            boolean condicional;            
            iniciarCaminhoPercorrido(sala);
            while(estaLigado()){
            	sala.texto();
                if(sensorSujeira.ativar(linha, coluna, sala)){
                    sugador.ativar(linha, coluna, sala);
                  
                }
                condicional = false;
                while(!condicional){
                    switch(qualAcaoRealizar()){
                        case 0: mudarDirecao();
                                condicional = true;
                                break;
                        case 1: if(mover(sala)){
                                   marcarCaminhoPercorrido();
                                   condicional = true;
                                }
                    } //fimswitch
                } //fimwhile
                if(jaPercorreuTodaSala(sala)){
                    desligar();
                }
            }//fimwhile
        }
}
