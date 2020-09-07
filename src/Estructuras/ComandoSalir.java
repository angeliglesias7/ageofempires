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
public class ComandoSalir implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoSalir(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() {
    }
}
