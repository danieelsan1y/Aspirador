/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafico;

/**
 *
 * @author Lucas
 */
public class Sugador {
	public void ativar(int linha, int coluna, Sala sala) {
		//Adapter.obterInstancia().sugador();
		sala.limpar(linha, coluna);
	}
}
