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
public class Porco {
    public Porco(){
                
            }
            public void sujar(Sala sala){
                Random valor_aleatorio = new Random();
                int qde_sujeiras;
                qde_sujeiras = valor_aleatorio.nextInt(sala.qdeLinhas()*sala.qdeColunas());
                
                for(int i = 1; i <= qde_sujeiras ; i++){
                    int linha = valor_aleatorio.nextInt(sala.qdeLinhas());
                    int coluna = valor_aleatorio.nextInt(sala.qdeColunas());
                    if(!sala.estaSujo(linha, coluna)){
                        sala.sujar(linha, coluna);
                    }
                }
            }    
}
