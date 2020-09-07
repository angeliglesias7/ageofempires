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
public class ComandoDesligar implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoDesligar(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion{
            ConsolaNormal consola = new ConsolaNormal();
            if (comando.length != 3) {
                consola.imprimir("Error de sintaxis: desligar <personaje> <grupo>");
                throw (new ComandoExtepcion());
            } else {
                try {
                    mapa1.desligar(comando[1], comando[2]);
                } catch (DesligarExtepcion de) {
                    de.ErrorDesligar();
                }
            }
    }
}
