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
public final class Paisano extends Personaje {

    public Paisano() {
        super();
        salud = 100;
        ataque = 10;
        defensa = 10;
        capacidad = 1;
        topeRecoleccion = 20;
        recoleccion = new Recurso(0);
        armadura = 0;
    }

    public Paisano(Posicion pos, String codigo, String descripcion ) {
        super(pos, codigo, descripcion);
        salud = 100;
        ataque = 10;
        defensa = 10;
        capacidad = 1;
        topeRecoleccion = 20;
        recoleccion = new Recurso(0);
        armadura = 0;
    }
    @Override
    public int capacidadMovimiento(){
     return 1;   
    }
}
