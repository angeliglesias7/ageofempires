/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Elementos;
import Estructuras.Posicion;



public abstract class ContenedorDeRecurso {
    
    private Posicion posicion;
    private String codigo;
    private String descripcion;
    protected Recurso materia;
    protected int cantidad;
    
    public ContenedorDeRecurso(){
        posicion = null;
        codigo = null;
        descripcion = null;        
    }
    
    public ContenedorDeRecurso(Posicion posicion, String codigo, String descripcion){
        this.posicion = posicion;
        this.codigo = codigo;
        this.descripcion = descripcion;        
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public String getCodigo() {
        return codigo;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Recurso getMateria() {
        return materia;
    }

    public void setMateria(Recurso materia) {
        this.materia = materia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    //MÉTODOS
    
    
//    Recurso procesar(){
//        
//    }
//    
    
    abstract boolean esTransitable();



    
    
}
