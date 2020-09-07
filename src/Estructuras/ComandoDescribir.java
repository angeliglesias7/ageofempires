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
public class ComandoDescribir implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoDescribir(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion{
            ConsolaNormal consola = new ConsolaNormal();
            if (comando.length != 2) {
                consola.imprimir("Error de sintaxis: describir <personaje>\n");
                throw (new ComandoExtepcion());
            } else {
                for (int i = 1; i < comando.length; i++) {
                    mapa1.descripcion(comando[1]);
                }
            }
    }
}
