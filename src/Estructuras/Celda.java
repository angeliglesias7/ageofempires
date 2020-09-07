/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Estructuras;
import Elementos.Personaje;
import Elementos.Edificio;
import Elementos.ContenedorDeRecurso;
import Elementos.Grupo;

import java.util.ArrayList;

/**
 *
 * @author angel
 */

public class Celda {
    private Posicion posicion;   
    private ArrayList <Personaje> Personajes = new ArrayList();
    private boolean Edificios;
    private boolean ContenedorDeRecursos;
    private boolean visible;
    private boolean grupo;
    
    public Celda(Posicion pos){
        posicion = pos;
        Edificios = false;
        ContenedorDeRecursos = false;
        visible = false;
        grupo = false;
    }
    
    public Celda(Posicion pos, Personaje personaje){
        posicion = pos;
        Personajes.add(personaje);
        Edificios = false;
        ContenedorDeRecursos = false;
        visible = false;
        grupo = false;
    }
    
    public Celda(Posicion pos, Edificio edificio){
        posicion = pos;
        Edificios = true;
        ContenedorDeRecursos = false; 
        visible = false;
        grupo = false;
    }
    
    
    public Celda(Posicion pos, ContenedorDeRecurso contenedor){
        posicion = pos;
        Edificios = false;
        ContenedorDeRecursos = true;
        visible = false;
        grupo = false;
    }
    
    

    public void setPosicion(Posicion posicion) throws DatosExtepcion {
        if(posicion.getX() < 0 || posicion.getY() < 0){
            System.out.println("La posicion no puede ser negativa");
            throw(new DatosExtepcion());
        }
        else{
            this.posicion = posicion;
        }
    }

    public void setPersonajes(ArrayList<Personaje> Personajes) {
        this.Personajes = Personajes;
    }

    public void setEdificios(boolean Edificios) {
        this.Edificios = Edificios;
    }

    public void setContenedorDeRecursos(boolean ContenedorDeRecursos) {
        this.ContenedorDeRecursos = ContenedorDeRecursos;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public ArrayList<Personaje> getPersonajes() {
        return Personajes;
    }

    public boolean isEdificios() {
        return Edificios;
    }

    public boolean isContenedorDeRecursos() {
        return ContenedorDeRecursos;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isGrupo() {
        return grupo;
    }

    public void setGrupo(boolean grupo) {
        this.grupo = grupo;
    }
    
    
    
    
}
