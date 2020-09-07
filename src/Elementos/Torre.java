/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Estructuras.Posicion;



public class Torre extends Edificio{
    
    
    public Torre() {
        super();
        puntoSalud = 200;
        costeReparacionMadera = 50;
        costeReparacionPiedra = 50;
        costeCreacionPersonaje = 50;
        ataqueEdificio = 100;
        armaduraEdificio = 50;
        capacidad = 6;
    }
    
    public Torre(Posicion posicion, String nombre){
        super(posicion,nombre);
        puntoSalud = 200;
        costeReparacionMadera = 50;
        costeReparacionPiedra = 50;
        costeCreacionPersonaje = 50;
        ataqueEdificio = 100;
        armaduraEdificio = 50;
        capacidad = 6;        
        
    }
    
    
    
    
}
