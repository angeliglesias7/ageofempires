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
public class CrearExtepcion extends Exception{
    public void ErrorCrear(){
        ConsolaNormal consola=new ConsolaNormal();
        consola.imprimir("Error Crear");
    }
    
}
