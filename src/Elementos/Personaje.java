/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Estructuras.AlmacenarExtepcion;
import Estructuras.ConstruirExtepcion;
import Estructuras.DatosExtepcion;
import Estructuras.PuntoCardinalExtepcion;
import Estructuras.Posicion;
import Estructuras.RecolectarExtepcion;

public abstract class Personaje {
    protected Posicion posicion;
    private String codigo;
    private String descripcion;
    protected double salud;
    protected double ataque;
    protected double defensa;
    protected int capacidad;
    protected int topeRecoleccion;
    protected Recurso recoleccion;
    protected double armadura;
    public final int vidamax = 100;

    public Personaje() {
        posicion = null;
        this.codigo = null;
        this.descripcion = null;

    }

    public Personaje(Posicion pos, String codigo, String descripcion) {
        posicion = pos;
        this.codigo = codigo;
        this.descripcion = descripcion;
        recoleccion = new Recurso();
    }

    public void setPosicion(Posicion posicion) {
        if (posicion.getX() < 0 || posicion.getY() < 0) {
            System.out.println("La posicion no puede ser negativa");
        } else {
            this.posicion = posicion;
        }
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setSalud(double salud) throws DatosExtepcion {
        if (salud < 0) {
            System.out.println("El entero salud no puede ser negativo");
            throw(new DatosExtepcion());
        } else {
            this.salud = salud;
        }
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getSalud() {
        return salud;
    }

    public double getAtaque() {
        return ataque;
    }

    
    public void setAtaque(double ataque) throws DatosExtepcion {
            if (ataque < 0.0) {
                System.out.println("El ataque no puede ser negativo");
                throw(new DatosExtepcion());
            }
        this.ataque = ataque;
    }

    public double getDefensa() {
        return defensa;
    }

    public void setDefensa(double defensa) {
        this.defensa = defensa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getArmadura() {
        return armadura;
    }

    public void setArmadura(double armadura) {
        this.armadura = armadura;
    }

    public Recurso getRecoleccion() {
        return recoleccion;
    }

    public void setRecoleccion(Recurso recoleccion) {
        this.recoleccion = recoleccion;
    }

    public int getTopeRecoleccion() {
        return topeRecoleccion;
    }

    public void setTopeRecoleccion(int topeRecoleccion) {
        this.topeRecoleccion = topeRecoleccion;
    }

    //Métodos
    public Posicion mover(String pto_cardinal) throws PuntoCardinalExtepcion {
            Posicion pos = new Posicion();
            pos.setX(posicion.getX());
            pos.setY(posicion.getY());

            if ((pto_cardinal).equals("NORTE") || (pto_cardinal).equals("norte")) {
                pos.setX(posicion.getX() - 1);
                pos.setY(posicion.getY());
            } else if ((pto_cardinal).equals("SUR") || (pto_cardinal).equals("sur")) {
                pos.setX(posicion.getX() + 1);
                pos.setY(posicion.getY());
            } else if ((pto_cardinal).equals("ESTE") || (pto_cardinal).equals("este")) {
                pos.setX(posicion.getX());
                pos.setY(posicion.getY() + 1);
            } else if ((pto_cardinal).equals("OESTE") || (pto_cardinal).equals("oeste")) {
                pos.setX(posicion.getX());
                pos.setY(posicion.getY() - 1);
            } else {
                throw (new PuntoCardinalExtepcion());
            }

            return pos;
    }

    abstract public int capacidadMovimiento() ;

    public void recolectar(ContenedorDeRecurso contenedor) throws RecolectarExtepcion {
            if (contenedor instanceof Bosque) {
                if (contenedor.getCantidad() >= topeRecoleccion) {
                    try{
                    recoleccion.setCantidad(topeRecoleccion);
                    }catch(DatosExtepcion de){
                        de.ErrorDatos();
                    }
                    recoleccion.setNombre("Madera");
                    contenedor.setCantidad(contenedor.getCantidad() - topeRecoleccion);
                    System.out.println("Has recolectado " + recoleccion.getCantidad());
                    System.out.println("El contenedor tiene " + contenedor.getCantidad());
                    //Has conseguido tanto...
                } else {
                    try{
                    recoleccion.setCantidad(contenedor.getCantidad());
                    recoleccion.setCantidad(contenedor.getCantidad());
                    }catch(DatosExtepcion de){
                        de.ErrorDatos();
                    }
                    System.out.println("Has recolectado " + recoleccion);
                    //Eliminamos el contenedor.
                    //Recolectamos ...
                }
            } else if (contenedor instanceof Cantera) {
                try{
                if (contenedor.getCantidad() >= topeRecoleccion) {
                    recoleccion.setCantidad(topeRecoleccion);
                    recoleccion.setNombre("Piedra");
                    contenedor.setCantidad(contenedor.getCantidad() - topeRecoleccion);
                    System.out.println("Has recolectado " + recoleccion.getCantidad());
                    System.out.println("El contenedor tiene " + contenedor.getCantidad());
                    
                } else {
                    recoleccion.setCantidad(contenedor.getCantidad());
                    //Eliminamos el contenedor
                    //Recolectamos ...
                }
                }catch(DatosExtepcion de){
                        de.ErrorDatos();
                    }
            } else if (contenedor instanceof Arbusto) {
                try{
                if (contenedor.getCantidad() >= topeRecoleccion) {
                    recoleccion.setCantidad(topeRecoleccion);
                    recoleccion.setNombre("Alimento");
                    contenedor.setCantidad(contenedor.getCantidad() - topeRecoleccion);
                    //Has conseguido tanto...
                } else {
                    recoleccion.setCantidad(contenedor.getCantidad());
                    //Eliminamos el contenedor
                    //Recolectamos ...
                }
                }catch(DatosExtepcion de){
                        de.ErrorDatos();
                    }
            } else if (contenedor instanceof Pradera) {
                //No se puede recolectar en una pradera
                throw (new RecolectarExtepcion());
            }

    }

    public void almacenar(Ciudadela ciudadela) throws AlmacenarExtepcion{
            if (recoleccion.getCantidad() > 0) {
                if (recoleccion.getNombre().equals("Madera")) {
                    try{
                    ciudadela.setMadera(ciudadela.getMadera() + recoleccion.getCantidad());
                    recoleccion.setCantidad(0);
                    }catch(DatosExtepcion de){
                        de.ErrorDatos();
                    }
                } else if (recoleccion.getNombre().equals("Piedra")) {
                    try{
                    ciudadela.setPiedra(ciudadela.getPiedra() + recoleccion.getCantidad());
                    recoleccion.setCantidad(0);
                    }catch(DatosExtepcion de){
                        de.ErrorDatos();
                    }
                } else if (recoleccion.getNombre().equals("Alimento")) {
                    try{
                    ciudadela.setAlimento(ciudadela.getAlimento() + recoleccion.getCantidad());
                    recoleccion.setCantidad(0);
                    }catch(DatosExtepcion de){
                            de.ErrorDatos();
                            }
                } else {
                    throw (new AlmacenarExtepcion());
                }
            }
    }

    public void reparar(Edificio edificio){
       if (edificio.getPuntoSalud() < edificio.vidamax) {
           // if (edificio.getCosteReparacionMadera() < civilizacion.getMadera() && edificio.getCosteReparacionPiedra() < civilizacion.getPiedra()) {
                //civilizacion.setMadera(civilizacion.getMadera() - (int) edificio.getCosteReparacionMadera());
                //civilizacion.setPiedra(civilizacion.getPiedra() - (int) edificio.getCosteReparacionPiedra());
           // } else {
                System.out.println("No tenemos recursos suficientes");
           // }
        }else{
            System.out.println("El edificio ya esta reparado");  
        }

    }
    
    public Edificio construir(String tipo_edificio) throws ConstruirExtepcion{
            Edificio edif = null;

            switch (tipo_edificio) {
                case "Ciudadela":
                    edif = new Ciudadela();
                    return edif;
                case "Casa":
                    edif = new Casa();
                    return edif;
                case "Cuartel":
                    edif = new Cuartel();
                    return edif;
                case "Torre":
                    edif = new Torre();
                    return edif;
                default:
                    throw (new ConstruirExtepcion());
            }
    }

    public void defender(Edificio edificio) {
        edificio.setCapacidad(edificio.getCapacidad() - 1);
        salud = vidamax;
        edificio.setArmaduraEdificio(edificio.getArmaduraEdificio() + armadura);
        edificio.setAtaqueEdificio(edificio.getAtaqueEdificio() + ataque);

    }

    public void atacar(Personaje[] personajes) {
        double ataqueparticular = ataque / personajes.length;

        for (int i = 0; i < personajes.length; i++) {
            if ((personajes[i].getArmadura() - ataqueparticular) > 0) {
                personajes[i].setArmadura(personajes[i].getArmadura() - ataqueparticular);
            } else if ((personajes[i].getArmadura() - ataqueparticular) < 0) {
                personajes[i].setArmadura(0);
                try{
                personajes[i].setSalud(personajes[i].getSalud() - (personajes[i].getArmadura() - ataqueparticular));
            
                }catch(DatosExtepcion de){
                    de.ErrorDatos();
                }
            }
        }
    }

    public void atacar(Edificio edificio) {

        if (0 >= edificio.getPuntoSalud() - (ataque - edificio.getArmaduraEdificio())) {
            System.out.println("El edificio ha sido destruido");
        } else if (0 < edificio.getArmaduraEdificio() - ataque) {
            edificio.setPuntoSalud(edificio.getPuntoSalud() - (edificio.getArmaduraEdificio() - ataque));
        }
    }

    public double danhoAtaque(Personaje personaje) {

        double danhoataque = 0.0;

        if (personaje instanceof Paisano) {
            danhoataque = ataque - personaje.getAtaque();
        }
        if (personaje instanceof Caballero) {
            danhoataque = ataque - personaje.getAtaque();
        }
        if (personaje instanceof Legionario) {
            danhoataque = ataque - personaje.getAtaque();
        }
        if (personaje instanceof Arquero) {
            danhoataque = ataque - personaje.getAtaque();
        }

        return danhoataque;
    }

    public double danhoAtaque(Edificio edificio) {

        double danhoataque = 0.0;

        if (edificio instanceof Ciudadela) {
            danhoataque = ataque - ((Ciudadela) edificio).getArmaduraEdificio();
            if (danhoataque < 0) {
                danhoataque = 0;
            }
        }

        if (edificio instanceof Cuartel) {
            danhoataque = ataque - ((Cuartel) edificio).getArmaduraEdificio();
            if (danhoataque < 0) {
                danhoataque = 0;
            }
        }

        if (edificio instanceof Casa) {
            danhoataque = ataque - ((Casa) edificio).getArmaduraEdificio();
            if (danhoataque < 0) {
                danhoataque = 0;
            }
        }

        if (edificio instanceof Torre) {
            danhoataque = ataque - ((Torre) edificio).getArmaduraEdificio();
            if (danhoataque < 0) {
                danhoataque = 0;
            }
        }

        return danhoataque;

    }

}
