/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafico;

/**
 *
 * @author Lucas
 */
interface Sensor{
            public boolean ativar(int linha, int coluna, Sala sala);
        }
        class SensorSujeira implements Sensor{
            public boolean ativar(int linha, int coluna, Sala sala){
            	Adapter.obterInstancia().sensorSujeira();
                return sala.estaSujo(linha, coluna);
            }
        }
        class SensorParede implements Sensor{
            public boolean ativar(int linha, int coluna, Sala sala){
            	Adapter.obterInstancia().sensorParede();
                return sala.estaNoLimite(linha, coluna);
            }
}
