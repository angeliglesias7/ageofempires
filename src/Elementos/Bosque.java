/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Estructuras.Posicion;



public final class Bosque extends ContenedorDeRecurso{
    
    
    public Bosque(){
        super();
        Recurso madera = new Madera(200);
        materia = madera;
        cantidad = 200;
        
    }
    
    public Bosque(Posicion posicion, String nombre, String codigo){
        super(posicion,nombre,codigo);
        Recurso madera = new Madera(200);
        materia = madera;
        cantidad = 200;
        
    }
    
    @Override
    public boolean esTransitable(){
        boolean transitable;
        
        if(cantidad > 0){
            transitable = true;
        }
        else{
            transitable = false;
        }
        
        return transitable;
    }
    
}
