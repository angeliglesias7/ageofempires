/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Estructuras.Posicion;

public class Ciudadela extends Edificio {

    

    public Ciudadela() {
        super();
        puntoSalud = 200;
        costeReparacionMadera = 50;
        costeReparacionPiedra = 50;
        costeCreacionPersonaje = 50;
        ataqueEdificio = 30;
        armaduraEdificio = 30;
        capacidad = 6;
        alimento = 100;
        madera = 100;
        piedra = 100;
    }

    public Ciudadela(Posicion posicion, String nombre) {
        super(posicion,nombre);
        puntoSalud = 200;
        costeReparacionMadera = 50;
        costeReparacionPiedra = 50;
        costeCreacionPersonaje = 50;
        ataqueEdificio = 30;
        armaduraEdificio = 30;
        capacidad = 6;
        alimento = 100;
        madera = 100;
        piedra = 100;
    }

    

}
