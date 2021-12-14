/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafico;

/**
 *
 * @author Lucas
 */
public class Sala {
    protected boolean [][] sala = new boolean [5][5];
            int qde_linha;
            int qde_coluna;
            
            public Sala(){
                qde_linha = 5;
                qde_coluna = 5;
                
                for(int i = 0; i < qde_linha ; i++){
                    for(int j = 0; j < qde_coluna ; j++){
                        sala[i][j] = false;
                    }
                }
            }
            
            public void sujar(int linha, int coluna){
            	Adapter.obterInstancia().mostrarSujeira(linha, coluna);
                sala[linha][coluna] = true;
            }
            
            public boolean estaSujo(int linha, int coluna){
                return sala[linha][coluna];
            }
            
            public void limpar(int linha, int coluna){
            	Adapter.obterInstancia().apagarSujeira(linha, coluna);
                sala[linha][coluna] = false;
            }
            
            public boolean estaNoLimite(int linha, int coluna){
                return !((linha > -1) && (linha < qde_linha) && (coluna > -1) && (coluna < qde_coluna));
            }
            
            public int qdeLinhas(){
                return qde_linha;
            }
            public int qdeColunas(){
                return qde_coluna;
            }
            
            public void texto(){
                System.out.println("");
                System.out.println("");
                System.out.println("----- SALA -----");
                System.out.println("");
                
                
                for(int i = 0; i < qde_linha ; i++){
                    for(int j = 0; j < qde_coluna; j++){
                        if(sala[i][j]){
                            System.out.print("X");
                        }
                        else{
                            System.out.print("-");
                        }
                    }
                    System.out.println("");
                }
            }
}
