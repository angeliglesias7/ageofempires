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
public class ComandoAgrupar implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoAgrupar(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion{
            String grupo = new String();
            //grupo = String.join(" ", comando);
            grupo = grupo.replaceAll("\\s", "");
            String parte1 = grupo.substring(8, 9);
            String parte2 = grupo.substring(10, 11);
            try {
                mapa1.agrupar(parte1, parte2);
            } catch (AgruparExtepcion ae) {
                ae.ErrorAgrupar();
            }
    }
    
}
