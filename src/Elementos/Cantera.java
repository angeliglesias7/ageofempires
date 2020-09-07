/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Estructuras.Posicion;



public final class Cantera extends ContenedorDeRecurso{
    
    
    public Cantera(){
        super();
        Recurso piedra = new Piedra(200);
        materia = piedra;
        cantidad = 200;
        
    }
    
    public Cantera(Posicion posicion, String nombre, String codigo){
        super(posicion,nombre,codigo);
        Recurso piedra = new Piedra(200);
        materia = piedra;
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
