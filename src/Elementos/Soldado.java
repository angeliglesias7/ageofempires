/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;
import Estructuras.Posicion;



public abstract class Soldado extends Personaje{
    
    
    public Soldado(){
        super();        
                
    }
    
    
    public Soldado(Posicion pos, String codigo, String descripcion ){
        super(pos,codigo,descripcion);
        
    }
    
    
}
