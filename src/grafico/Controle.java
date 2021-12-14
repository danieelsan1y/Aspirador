/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafico;

/**
 *
 * @author Lucas
 */
public class Controle {
	public void simular() {
		Porco porco = new Porco();
		Sala sala = new Sala();
		Adapter.obterInstancia().setDisplay(new Interface("Aspirador de pó"));
		Aspirador aspirador = new Aspirador(0, 0);
		sala.texto();
		porco.sujar(sala);
		sala.texto();
		aspirador.ligar();
		aspirador.limpar(sala);
		sala.texto();
	}
}
