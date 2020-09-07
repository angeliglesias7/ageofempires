/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Elementos;
import Estructuras.CrearExtepcion;
import Estructuras.DatosExtepcion;
import Estructuras.Posicion;
import java.util.ArrayList;



public abstract class Edificio {
    private Posicion posicion;
    private String nombre;
    protected double puntoSalud;
    protected double costeReparacionMadera;
    protected double costeReparacionPiedra;
    protected double costeCreacionPersonaje;
    protected double ataqueEdificio;
    protected double armaduraEdificio;
    protected ArrayList<Personaje> Personajes;
    protected ArrayList<Grupo> Grupos;
    protected int capacidad;
    public final int vidamax=200;
    protected double alimento;
    protected double madera;
    protected double piedra;
    
    
    public Edificio(Posicion pos, String nombre){
        posicion = pos;
        this.nombre = nombre;
    }

    public Edificio(){
        posicion = null;
        nombre = null;        
    }

    public int getVidamax() {
        return vidamax;
    }
    

    public Posicion getPosicion() {
        return posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPuntoSalud() {
        return puntoSalud;
    }

    public double getCosteReparacionMadera() {
        return costeReparacionMadera;
    }

    public double getCosteReparacionPiedra() {
        return costeReparacionPiedra;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntoSalud(double puntoSalud) {
        if(puntoSalud < 0){
            System.out.println("El punto de salud no puede ser negativo");
        }
        else{
            this.puntoSalud = puntoSalud;
        }
    }

    public void setCosteReparacionMadera(double costeReparacionMadera) {
        if(costeReparacionMadera < 0){
            System.out.println("El coste de reparacion no puede ser negativo");
        }
        else{
            this.costeReparacionMadera = costeReparacionMadera;
        }
    }

    public void setCosteReparacionPiedra(double costeReparacionPiedra) {
        if(costeReparacionPiedra < 0){
            System.out.println("El coste de reparacion no puede ser negativo");
        }
        else{
            this.costeReparacionPiedra = costeReparacionPiedra;
        }
    }
    

    public double getCosteCreacionPersonaje() {
        return costeCreacionPersonaje;
    }

    public void setCosteCreacionPersonaje(double costeCreacionPersonaje)throws DatosExtepcion{
        if(costeCreacionPersonaje < 0){
            System.out.println("El coste de creacion no puede ser negativo");
            throw(new DatosExtepcion());
        }
        else{
            this.costeCreacionPersonaje = costeCreacionPersonaje;
        }
    }

    public ArrayList<Personaje> getPersonajes() {
        return Personajes;
    }

    public void setPersonajes(ArrayList<Personaje> Personajes) {
        this.Personajes = Personajes;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public ArrayList<Grupo> getGrupos() {
        return Grupos;
    }

    public void setGrupos(ArrayList<Grupo> Grupos) {
        this.Grupos = Grupos;
    }

    public double getAtaqueEdificio() {
        return ataqueEdificio;
    }

    public void setAtaqueEdificio(double ataqueEdificio) {
        this.ataqueEdificio = ataqueEdificio;
    }

    public double getArmaduraEdificio() {
        return armaduraEdificio;
    }

    public void setArmaduraEdificio(double armaduraEdificio) {
        this.armaduraEdificio = armaduraEdificio;
    }
    
    public double getAlimento() {
        return alimento;
    }

    public void setAlimento(double alimento)throws DatosExtepcion{
        if (alimento < 0) {
            System.out.println("El entero alimento no puede ser negativo");
            throw(new DatosExtepcion());
        } else {
            this.alimento = alimento;
        }
    }

    public double getMadera() {
        return madera;
    }

    public void setMadera(double madera) throws DatosExtepcion {
        if (madera < 0) {
            
            System.out.println("El entero madera no puede ser negativo");
            throw(new DatosExtepcion());
        } else {
            this.madera = madera;
        }
    }

    public double getPiedra() {
        return piedra;
    }

    public void setPiedra(double piedra) throws DatosExtepcion{
        if (piedra < 0) {
            System.out.println("El entero piedra no puede ser negativo");
            throw(new DatosExtepcion());
        } else {
            this.piedra = piedra;
        }
    }
    
    
    
    //MÉTODOS
    
    void almacenar(Recurso recurso){
        if(recurso.getNombre().equals("Madera")){
            madera += recurso.getCantidad();
        }
        if(recurso.getNombre().equals("Piedra")){
            piedra += recurso.getCantidad();
        }
        if(recurso.getNombre().equals("Alimento")){
            alimento += recurso.getCantidad();
        }
        
    }

    public Personaje crear(String tipo_personaje) throws CrearExtepcion {
            Personaje p = null;
            if (tipo_personaje.equals("Paisano")) {
                p = new Paisano();
            } else if (tipo_personaje.equals("Arquero")) {
                p = new Arquero();
            } else if (tipo_personaje.equals("Legionario")) {
                p = new Legionario();
            } else if (tipo_personaje.equals("Caballero")) {
                p = new Caballero();
            } else {
                throw (new CrearExtepcion());
            }

            return p;

    }

    void atacar(Personaje[] personajes){
        double ataqueparticular = ataqueEdificio / personajes.length;
        for (int i=0; i < personajes.length; i++){
            if ((personajes[i].getArmadura() - ataqueparticular) > 0) {
                personajes[i].setArmadura(personajes[i].getArmadura() - ataqueparticular);
            }
            else if ((personajes[i].getArmadura() - ataqueparticular) < 0){
                personajes[i].setArmadura(0);
                try{
                personajes[i].setSalud(personajes[i].getSalud() - (personajes[i].getArmadura() - ataqueparticular));
            
                }catch(DatosExtepcion de){
                    de.ErrorDatos();
                }
            }
        }        
    }
    
    
    
    
    
}
