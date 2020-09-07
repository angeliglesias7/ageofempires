/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Elementos;
import Estructuras.Posicion;
import java.util.HashMap;


public class Grupo extends Personaje{
    private HashMap<String, Personaje> Personajes = new HashMap();
    private double capacidadAtaqueDefensa;
    
    
    public Grupo(Posicion posicion, String codigo, String descripcion, HashMap Personajes){
        super(posicion,codigo,descripcion);
        this.Personajes = Personajes;        
    }    

    
    public HashMap<String, Personaje> getPersonajes() {
        return Personajes;
    }

    public void setPersonajes(HashMap<String, Personaje> Personajes) {
        this.Personajes = Personajes;
    }

    public double getCapacidadAtaqueDefensa() {
        return capacidadAtaqueDefensa;
    }

    public void setCapacidadAtaqueDefensa(double capacidadAtaqueDefensa) {
        this.capacidadAtaqueDefensa = capacidadAtaqueDefensa;
    }
    @Override
    public int capacidadMovimiento(){
     return 1;   
    }
    
   
}