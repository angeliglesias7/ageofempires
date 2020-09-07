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
public class PGExtepcion extends ComandoExtepcion{
    public void ErrorPG(){
        ConsolaNormal consola=new ConsolaNormal();
        consola.imprimir("Persoanje,civilizacion o grupo no encontrado");
    }
}
