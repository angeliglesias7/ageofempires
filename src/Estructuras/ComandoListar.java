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
public class ComandoListar implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoListar(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion{
            ConsolaNormal consola = new ConsolaNormal();
            if (comando.length != 2) {
                consola.imprimir("Error de sintaxis: listar <personaje>");
                throw (new ComandoExtepcion());
            } else {
                mapa1.listar(comando[1]);
            }
       
    }
}
