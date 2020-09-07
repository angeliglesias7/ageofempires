/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author raul
 */
public class ComandoCambiar implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoCambiar(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion{
            if (comando.length != 2) {
                System.out.println("Error de sintaxis: cambiar <civilizacion>");
                throw (new ComandoExtepcion());
            } else {
                mapa1.cambiar(comando[1]);
            }
    }
}
