/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Estructuras.DatosExtepcion;

/**
 *
 * @author angel
 */
public class Recurso {
    
    private String nombre;
    private int cantidad;
    public Recurso(){
        
    }
    public Recurso(String nombre, int cantidad){
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    
    public Recurso(int cantidad){
        this.cantidad = cantidad;
    }

    public void setCantidad(int cantidad) throws DatosExtepcion{
        if(cantidad < 0){
            
            System.out.println("El entero cantidad no puede ser negativo");
            throw(new DatosExtepcion());
        }
        else{
            this.cantidad = cantidad;
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
