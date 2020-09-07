/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Estructuras.Posicion;



public final class Arbusto extends ContenedorDeRecurso{
    
    
    public Arbusto(){
        super();
        Recurso alimento = new Alimento(200);
        materia = alimento;
        cantidad = 200;
        
    }
    
    public Arbusto(Posicion posicion, String nombre, String codigo){
        super(posicion,nombre,codigo);
        Recurso alimento = new Alimento(200);
        materia = alimento;
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
