/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;
import Estructuras.Posicion;
import Estructuras.SoldadoExtepcion;

/**
 *
 * @author angel
 */

public final class Legionario extends Soldado{
    
    
    public Legionario(){
        super();
        salud = 200;
        ataque = 100;
        defensa = 100;
        capacidad = 1;
        armadura = 50;
    }
    
    public Legionario(Posicion pos, String codigo, String descripcion){
        super(pos,codigo,descripcion);
        salud = 200;
        ataque = 100;
        defensa = 100;
        capacidad = 1;
        armadura = 50;
    }
    @Override
    public int capacidadMovimiento(){
     return 1;   
    }
}


