/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;
import Estructuras.Posicion;

/**
 *
 * @author angel
 */

public final class Arquero extends Soldado{
    
    public Arquero(){
        super();
        salud = 200;
        ataque = 50;
        defensa = 50;
        capacidad = 1;
        armadura = 20;
    }
    
    public Arquero(Posicion pos, String codigo, String descripcion){
        super(pos,codigo,descripcion);
        salud = 200;
        ataque = 50;
        defensa = 50;
        capacidad = 1;
        armadura = 20;
    }
    @Override
    public int capacidadMovimiento(){
     return 1;   
    }
}