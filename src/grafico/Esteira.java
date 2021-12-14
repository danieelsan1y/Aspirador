/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafico;

/**
 *
 * @author Lucas
 */
abstract class Esteira{
            abstract public void ativar(ParametroPorReferencia linha, ParametroPorReferencia coluna);            
            public String direcao(){
                return this.getClass().getName();
            }
        }
        class Norte extends Esteira{
            public void ativar(ParametroPorReferencia linha, ParametroPorReferencia coluna){
                linha.valor--;
                //super.ativar(linha, coluna);
            }
        }
        class Leste extends Esteira{
            public void ativar(ParametroPorReferencia linha, ParametroPorReferencia coluna){
                coluna.valor++;
                //super.ativar(linha, coluna);
            }
        }
        class Sul extends Esteira{
            public void ativar(ParametroPorReferencia linha, ParametroPorReferencia coluna){
                linha.valor++;
                //super.ativar(linha, coluna);
            }
        }
        class Oeste extends Esteira{
            public void ativar(ParametroPorReferencia linha, ParametroPorReferencia coluna){
                coluna.valor--;
                //super.ativar(linha, coluna);
            }
        }
