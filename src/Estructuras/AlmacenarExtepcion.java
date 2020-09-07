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
public class AlmacenarExtepcion extends Exception{
    
    public void ErrorAlmacenar(){
        ConsolaNormal consola =new ConsolaNormal();
        consola.imprimir("Error al almacenar");
    }
}
