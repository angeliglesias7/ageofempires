/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;
import Estructuras.Posicion;
import Estructuras.PuntoCardinalExtepcion;

/**
 *
 * @author angel
 */

public final class Caballero extends Soldado{
    
    
    public Caballero(){
        super();
        salud = 150;
        ataque = 100;
        defensa = 100;
        capacidad = 2;
        armadura = 50;
    }
    
    public Caballero(Posicion pos, String codigo, String descripcion){
        super(pos,codigo,descripcion);
        salud = 150;
        ataque = 100;
        defensa = 100;
        capacidad = 2;
        armadura = 50;
    }
    @Override
    public int capacidadMovimiento(){
     return 2;   
    }
    @Override
    public Posicion mover(String pto_cardinal) throws PuntoCardinalExtepcion {
    try {
            Posicion pos = new Posicion();
            pos.setX(posicion.getX());
            pos.setY(posicion.getY());

            if ((pto_cardinal).equals("NORTE") || (pto_cardinal).equals("norte")) {
                pos.setX(posicion.getX() - 2);
                pos.setY(posicion.getY());
            } else if ((pto_cardinal).equals("SUR") || (pto_cardinal).equals("sur")) {
                pos.setX(posicion.getX() + 2);
                pos.setY(posicion.getY());
            } else if ((pto_cardinal).equals("ESTE") || (pto_cardinal).equals("este")) {
                pos.setX(posicion.getX());
                pos.setY(posicion.getY() + 2);
            } else if ((pto_cardinal).equals("OESTE") || (pto_cardinal).equals("oeste")) {
                pos.setX(posicion.getX());
                pos.setY(posicion.getY() - 2);
            } else {
                throw (new PuntoCardinalExtepcion());
            }
            return pos;
        } catch (PuntoCardinalExtepcion me) {
            me.ErroPuntoCardinal();
            return null;
        }
    }
    
}