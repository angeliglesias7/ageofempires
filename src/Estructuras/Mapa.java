/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Elementos.Personaje;
import Elementos.Soldado;
import Elementos.Caballero;
import Elementos.Legionario;
import Elementos.Arquero;
import Elementos.Paisano;
import Elementos.Edificio;
import Elementos.Ciudadela;
import Elementos.Casa;
import Elementos.Cuartel;
import Elementos.Torre;
import Elementos.ContenedorDeRecurso;
import Elementos.Bosque;
import Elementos.Cantera;
import Elementos.Arbusto;
import Elementos.Recurso;
import Elementos.Madera;
import Elementos.Piedra;
import Elementos.Alimento;
import Elementos.Civilizacion;
import Elementos.Grupo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Mapa {

    private int filas;
    private int columnas;
    String civilizacion;
    private HashMap<String, Civilizacion> civilizaciones;
    private ArrayList<ArrayList<Celda>> mapa;
    private HashMap<String, Personaje> Personajes;
    private HashMap<Posicion, Edificio> Edificios;
    private ArrayList<Recurso> Recursos;
    private HashMap<Posicion, ContenedorDeRecurso> ContenedorDeRecursos;
    private HashMap<String, Grupo> Grupos;

    public Mapa(){
        mapa = new ArrayList();
        Personajes = new HashMap();
        Edificios = new HashMap();
        Recursos = new ArrayList();
        filas=7;
        columnas=7;
        ContenedorDeRecursos = new HashMap();
        Grupos = new HashMap();
        civilizaciones = new HashMap<>();
        for (int i = 0; i < filas; i++) {
            ArrayList fila = new ArrayList();
            mapa.add(fila);
            for (int j = 0; j <columnas; j++) {
                Posicion pos = new Posicion(i, j);
                Celda celda = new Celda(pos);
                fila.add(celda);
            }
        }
        try{
        leerarchivomapa();
        leerarchivopersonajes();
        leerarchivoedificios();
        }catch(IOException ioe){
                Errorlectura();
                }
        vermapacivilizacion();
    }
    public Mapa(int filas, int columnas) {
        mapa = new ArrayList();
        Personajes = new HashMap();
        Edificios = new HashMap();
        Recursos = new ArrayList();
        ContenedorDeRecursos = new HashMap();
        Grupos = new HashMap();
        civilizaciones = new HashMap<>();
        this.filas = filas;
        this.columnas = columnas;
        for (int i = 0; i < filas; i++) {
            ArrayList fila = new ArrayList();
            mapa.add(fila);
            for (int j = 0; j < columnas; j++) {
                Posicion pos = new Posicion(i, j);
                Celda celda = new Celda(pos);
                fila.add(celda);
            }
        }

        Posicion posPer = new Posicion(0, 0);
        Posicion possur = new Posicion(posPer.getX() + 1, posPer.getY());
        Posicion poseste = new Posicion(posPer.getX(), posPer.getY() + 1);
        Caballero Caballero1 = new Caballero(posPer, "Caballero1", "caba1");
        Personajes.put(Caballero1.getCodigo(), Caballero1);
        Celda c1 = new Celda(posPer, Caballero1);
        Celda csur = new Celda(possur);
        Celda ceste = new Celda(poseste);
        mapa.get(Caballero1.getPosicion().getX()).set(Caballero1.getPosicion().getY(), c1);
        mapa.get(csur.getPosicion().getX()).set(csur.getPosicion().getY(), csur);
        mapa.get(ceste.getPosicion().getX()).set(ceste.getPosicion().getY(), ceste);

        Posicion posPer1 = new Posicion(2, 0);
        Paisano Paisano1 = new Paisano(posPer1, "Paisano1", "pais1");
        Personajes.put(Paisano1.getCodigo(), Paisano1);
        Celda celdaper1 = new Celda(posPer1, Paisano1);
        mapa.get(Paisano1.getPosicion().getX()).set(Paisano1.getPosicion().getY(), celdaper1);

        Posicion posEd = new Posicion(0, 1);
        Posicion possure = new Posicion(posEd.getX() + 1, posEd.getY());
        Posicion posestee = new Posicion(posEd.getX(), posEd.getY() + 1);
        Ciudadela Ciudadela1 = new Ciudadela(posEd, "Ciudadela1");
        Edificios.put(posEd, Ciudadela1);
        try{
        Ciudadela1.setMadera(200);
        Ciudadela1.setPiedra(200);
        Ciudadela1.setAlimento(200);
        Celda c2 = new Celda(Ciudadela1.getPosicion(), Ciudadela1);
        Celda csure = new Celda(possure);
        Celda cestee = new Celda(posestee);
        mapa.get(Ciudadela1.getPosicion().getX()).set(Ciudadela1.getPosicion().getY(), c2);
        mapa.get(csure.getPosicion().getX()).set(csure.getPosicion().getY(), csure);
        mapa.get(cestee.getPosicion().getX()).set(cestee.getPosicion().getY(), cestee);
        }catch(DatosExtepcion de){
            de.ErrorDatos();
        }
        Posicion posSold2 = new Posicion(9, 5);
        Legionario Legionario1 = new Legionario(posSold2, "Legionario1", "legio1");
        Personajes.put(Legionario1.getCodigo(), Legionario1);
        Celda celda3 = new Celda(posSold2, Legionario1);
        mapa.get(Legionario1.getPosicion().getX()).set(Legionario1.getPosicion().getY(), celda3);

        Posicion posPer2 = new Posicion(8, 7);
        Paisano Paisano2 = new Paisano(posPer2, "Paisano2", "pais2");
        Personajes.put(Paisano2.getCodigo(), Paisano2);
        Celda celda1 = new Celda(posPer2, Paisano2);
        mapa.get(Paisano2.getPosicion().getX()).set(Paisano2.getPosicion().getY(), celda1);
        try{
        Posicion ciud3 = new Posicion(7, 9);
        Ciudadela Ciudadela2 = new Ciudadela(ciud3, "Ciudadela2");
        Ciudadela2.setMadera(200);
        Ciudadela2.setPiedra(200);
        Ciudadela2.setAlimento(200);
        Edificios.put(ciud3, Ciudadela2);
        Celda celdaciud = new Celda(Ciudadela2.getPosicion(), Ciudadela2);
        mapa.get(Ciudadela2.getPosicion().getX()).set(Ciudadela2.getPosicion().getY(), celdaciud);
        
        Posicion posB1 = new Posicion(2, 2);
        Bosque Bosque1 = new Bosque(posB1, "Bosque1", "bosq1");
        ContenedorDeRecursos.put(posB1, Bosque1);
        Celda c3 = new Celda(Bosque1.getPosicion(), Bosque1);
        mapa.get(Bosque1.getPosicion().getX()).set(Bosque1.getPosicion().getY(), c3);

        Posicion posB2 = new Posicion(2, 8);
        Bosque Bosque2 = new Bosque(posB2, "Bosque2", "bosq2");
        ContenedorDeRecursos.put(posB2, Bosque2);
        Celda c4 = new Celda(Bosque2.getPosicion(), Bosque2);
        mapa.get(Bosque2.getPosicion().getX()).set(Bosque2.getPosicion().getY(), c4);

        Posicion posB3 = new Posicion(7, 4);
        Bosque Bosque3 = new Bosque(posB3, "Bosque3", "bosq3");
        ContenedorDeRecursos.put(posB3, Bosque3);
        Celda c5 = new Celda(Bosque3.getPosicion(), Bosque3);
        mapa.get(Bosque3.getPosicion().getX()).set(Bosque3.getPosicion().getY(), c5);

        Posicion posC1 = new Posicion(2, 1);
        Cantera Cantera1 = new Cantera(posC1, "Cantera1", "cant1");
        ContenedorDeRecursos.put(posC1, Cantera1);
        Celda c6 = new Celda(Cantera1.getPosicion(), Cantera1);
        mapa.get(Cantera1.getPosicion().getX()).set(Cantera1.getPosicion().getY(), c6);

        Posicion posT1 = new Posicion(9, 8);
        Torre Torre1 = new Torre(posT1, "Torre1");
        Edificios.put(posT1, Torre1);
        Celda c20 = new Celda(Torre1.getPosicion(), Torre1);
        mapa.get(Torre1.getPosicion().getX()).set(Torre1.getPosicion().getY(), c20);

        Posicion posC2 = new Posicion(2, 6);
        Cantera Cantera2 = new Cantera(posC2, "Cantera2", "cant2");
        ContenedorDeRecursos.put(posC2, Cantera2);
        Celda c7 = new Celda(Cantera2.getPosicion(), Cantera2);
        mapa.get(Cantera2.getPosicion().getX()).set(Cantera2.getPosicion().getY(), c7);

        Posicion posC3 = new Posicion(7, 8);
        Cantera Cantera3 = new Cantera(posC3, "Cantera3", "cant3");
        ContenedorDeRecursos.put(posC3, Cantera3);
        Celda c8 = new Celda(Cantera3.getPosicion(), Cantera3);
        mapa.get(Cantera3.getPosicion().getX()).set(Cantera3.getPosicion().getY(), c8);

        Posicion posA1 = new Posicion(5, 1);
        Arbusto Arbusto1 = new Arbusto(posA1, "Arbusto1", "arb1");
        ContenedorDeRecursos.put(posA1, Arbusto1);
        Celda c9 = new Celda(Arbusto1.getPosicion(), Arbusto1);
        mapa.get(Arbusto1.getPosicion().getX()).set(Arbusto1.getPosicion().getY(), c9);

        Posicion posA2 = new Posicion(4, 4);
        Arbusto Arbusto2 = new Arbusto(posA2, "Arbusto2", "arb2");
        ContenedorDeRecursos.put(posA2, Arbusto2);
        Celda c10 = new Celda(Arbusto2.getPosicion(), Arbusto2);
        mapa.get(Arbusto2.getPosicion().getX()).set(Arbusto2.getPosicion().getY(), c10);

        Posicion posA3 = new Posicion(5, 8);
        Arbusto Arbusto3 = new Arbusto(posA3, "Arbusto3", "arb3");
        ContenedorDeRecursos.put(posA3, Arbusto3);
        Celda c11 = new Celda(Arbusto3.getPosicion(), Arbusto3);
        mapa.get(Arbusto3.getPosicion().getX()).set(Arbusto3.getPosicion().getY(), c11);

        Civilizacion Romana = new Civilizacion("Romana", Caballero1, Ciudadela1, mapa);
        civilizaciones.put(Romana.getNombre(), Romana);
        Romana.anadirPersonaje(Paisano1, mapa);
        Civilizacion Grecia = new Civilizacion("Griega", Paisano2, Torre1, mapa);
        civilizaciones.put(Grecia.getNombre(), Grecia);
        Grecia.anadirEdificio(Ciudadela2, mapa);
        Grecia.anadirPersonaje(Legionario1, mapa);
        civilizacion = Romana.getNombre();
        }catch(DatosExtepcion de){
            de.ErrorDatos();
        }
        vermapacivilizacion();
    }

    public void setFilas(int filas) throws DatosExtepcion {
        if (filas < 0) {
            System.out.println("El entero filas no puede ser negativo");
            throw(new DatosExtepcion());
        } else {
            this.filas = filas;
        }
    }

    public void setColumnas(int columnas) throws DatosExtepcion {
        if (columnas < 0) {
            System.out.println("El entero columnas no puede ser negativo");
            throw(new DatosExtepcion());
        } else {
            this.columnas = columnas;
        }
    }

    public void setMapa(ArrayList<ArrayList<Celda>> mapa) {
        this.mapa = mapa;
    }

    public void setPersonajes(HashMap<String, Personaje> Personajes) {
        this.Personajes = Personajes;
    }

    public void setEdificios(HashMap<Posicion, Edificio> Edificios) {
        this.Edificios = Edificios;
    }

    public void setRecursos(ArrayList<Recurso> Recursos) {
        this.Recursos = Recursos;
    }

    public void setContenedorDeRecursos(HashMap<Posicion, ContenedorDeRecurso> ContenedorDeRecursos) {
        this.ContenedorDeRecursos = ContenedorDeRecursos;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public ArrayList<ArrayList<Celda>> getMapa() {
        return mapa;
    }

    public HashMap<String, Personaje> getPersonajes() {
        return Personajes;
    }

    public HashMap<Posicion, Edificio> getEdificios() {
        return Edificios;
    }

    public ArrayList<Recurso> getRecursos() {
        return Recursos;
    }

    public HashMap<Posicion, ContenedorDeRecurso> getContenedorDeRecursos() {
        return ContenedorDeRecursos;
    }

    public void setGrupos(HashMap<String, Grupo> Grupos) {
        this.Grupos = Grupos;
    }

    public HashMap<String, Grupo> getGrupos() {
        return Grupos;
    }

    public String getCivilizacion() {
        return civilizacion;
    }

    public void setCivilizacion(String civilizacion) {
        this.civilizacion = civilizacion;
    }

    public HashMap<String, Civilizacion> getCivilizaciones() {
        return civilizaciones;
    }

    public void setCivilizaciones(HashMap<String, Civilizacion> civilizaciones) {
        this.civilizaciones = civilizaciones;
    }

    //METODOS
    public void activa() {
        System.out.println("La civilización activa actualmente es la: " + civilizacion);
    }

    public void cambiar(String a) throws PGExtepcion {
        for (int i = 0; i < civilizaciones.get(civilizacion).getCeldasvisibles().size(); i++) {
            civilizaciones.get(civilizacion).getCeldasvisibles().get(i).setVisible(false);
        }
        if (civilizaciones.containsKey(a)) {
            civilizacion = civilizaciones.get(a).getNombre();
        } else {
            System.out.println("Non existe esta civilización");
            throw(new PGExtepcion());
        }
        vermapacivilizacion();
    }

    public void listar(String a) throws ComandoExtepcion {
        

        if (a.equals("personajes")) {
            Collection<Personaje> col = civilizaciones.get(civilizacion).getPersonajes().values();

            for (Personaje x : col) {
                System.out.println(x.getCodigo() + x.getPosicion());
            }
        } else if (a.equals("edificios")) {
            Collection<Edificio> col = civilizaciones.get(civilizacion).getEdificios().values();

            for (Edificio x : col) {
                System.out.println(x.getNombre() + x.getPosicion());
            }
        } else if (a.equals("civilizaciones")) {
            Collection<Civilizacion> colci = civilizaciones.values();
            for (Civilizacion civ : colci) {
                System.out.println("Civilizacion: " + civ.getNombre());
            }
        }else if(a.equals("grupos")){
            Collection <Grupo> colgrup=civilizaciones.get(civilizacion).getGrupos().values();
            for (Grupo gru : colgrup) {
                System.out.println("Grupos: " + gru.getCodigo());
            }
    }else {
            System.out.println("No podemos listar");
            throw(new ComandoExtepcion());
        }

    }

    public void descripcion(String a) throws PGExtepcion {

        int x = 0;

        if (Personajes.containsKey(a)) {
            System.out.println("Salud: " + (Personajes.get(a).getSalud()));
            if (Personajes.get(a) instanceof Paisano) {

            }
            if (Personajes.get(a) instanceof Caballero) {

                System.out.println("Armadura: " + (Personajes.get(a).getArmadura()));
                System.out.println("Ataque: " + (Personajes.get(a).getAtaque()));
            }
            x++;
        }

        Collection<Edificio> coledif = Edificios.values();

        for (Edificio edif : coledif) {
            if (edif.getNombre().equals(a)) {
                System.out.println("Salud: " + edif.getPuntoSalud());
                System.out.println("Coste de reparación de madera: " + (edif.getCosteReparacionMadera()));
                System.out.println("Coste de reparación de piedra: " + (edif.getCosteReparacionPiedra()));
                x++;
            }

        }

        Collection<ContenedorDeRecurso> colrec = ContenedorDeRecursos.values();

        for (ContenedorDeRecurso cont : colrec) {
            if (cont.getCodigo().equals(a)) {
                if (cont instanceof Bosque) {
                    System.out.println("Tipo: " + ((Bosque) cont).getMateria());
                    System.out.println("Cantidad de recurso: " + ((Bosque) cont).getCantidad());
                }
                if (cont instanceof Cantera) {
                    System.out.println("Tipo: " + ((Cantera) cont).getMateria());
                    System.out.println("Cantidad de recurso: " + ((Cantera) cont).getCantidad());
                }
                if (cont instanceof Arbusto) {
                    System.out.println("Tipo: " + ((Arbusto) cont).getMateria());
                    System.out.println("Cantidad de recurso: " + ((Arbusto) cont).getCantidad());
                }
                x++;
            }
        }

        if (x != 1) {
            System.out.println("El nombre introducido no existe");
            throw(new PGExtepcion());
        }

    }
    public void mirarCelda(String a, String b) throws LimiteExtepcion {

        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);

        Posicion posicion = new Posicion();
        posicion.setX(x);
        posicion.setY(y);

        if ((mapa.get(x).get(y).getPosicion()).equals(posicion)) {
            if (mapa.get(x).get(y).isVisible()) {
                if (!mapa.get(x).get(y).getPersonajes().isEmpty()) {

                    for (Personaje p : mapa.get(x).get(y).getPersonajes()) {
                        System.out.println("Personaje: " + p.getCodigo());
                        System.out.println("Posicion: " + p.getPosicion());
                        System.out.println("Salud: " + p.getSalud());
                        System.out.println("Armadura: " + p.getArmadura());
                        System.out.println("Ataque: " + p.getAtaque());
                    }
                } else if (mapa.get(x).get(y).isEdificios()) {
                    Collection<Edificio> coledif = civilizaciones.get(civilizacion).getEdificios().values();
                    for (Edificio edif : coledif) {
                        if (edif.getPosicion().getX() == (posicion.getX()) && edif.getPosicion().getY() == (posicion.getY())) {
                            System.out.println("Edificio: " + edif.getNombre());
                            System.out.println("Posicion: " + edif.getPosicion());
                            System.out.println("Salud: " + edif.getPuntoSalud());
                            System.out.println("Coste de reparación de madera: " + (edif.getCosteReparacionMadera()));
                            System.out.println("Coste de reparación de piedra: " + (edif.getCosteReparacionPiedra()));
                        }
                    }
                } else if (mapa.get(x).get(y).isContenedorDeRecursos()) {
                    Collection<ContenedorDeRecurso> colrec = ContenedorDeRecursos.values();
                    for (ContenedorDeRecurso cont : colrec) {
                        if (cont.getPosicion().getX() == (posicion.getX()) && cont.getPosicion().getY() == (posicion.getY())) {
                            System.out.println("Posicion: " + cont.getPosicion());
                            if (cont instanceof Bosque) {
                                System.out.println("Celda de tipo Bosque");
                                System.out.println(((Bosque) cont).getMateria() + " disponible: " + ((Bosque) cont).getCantidad() + " unidades");
                            }
                            if (cont instanceof Cantera) {
                                System.out.println("Celda de tipo Cantera");
                                System.out.println(((Cantera) cont).getMateria() + " disponible: " + ((Cantera) cont).getCantidad() + " unidades");
                            }
                            if (cont instanceof Arbusto) {
                                System.out.println("Celda de tipo Arbusto");
                                System.out.println(((Arbusto) cont).getMateria() + " disponible: " + ((Arbusto) cont).getCantidad() + " unidades");
                            }

                        }
                    }
                } else if (mapa.get(x).get(y).isGrupo()) {
                    Collection<Grupo> colgrup = civilizaciones.get(civilizacion).getGrupos().values();
                    for (Grupo grupo : colgrup) {
                        if (grupo.getPosicion().getX() == (posicion.getX()) && grupo.getPosicion().getY() == (posicion.getY())) {
                            System.out.println("Posicion: " + grupo.getPosicion());
                            System.out.println("Nombre: " + grupo.getCodigo());
                            System.out.println("Capacidad Ataque/Defensa: " + grupo.getCapacidadAtaqueDefensa());
                            for (Personaje p : grupo.getPersonajes().values()) {
                                System.out.println(p.getCodigo());
                            }
                        }
                    }
                } else {
                    System.out.println("La celda es una pradera");
                }
            } else {
                System.out.println("No se puede mirar el contenido de celdas no visibles");
            }

        } else {
            System.out.println("La posicion no esta en el mapa");
            throw(new LimiteExtepcion());
        }

    }
    public void agrupar(String a, String b) throws AgruparExtepcion{
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);

        Posicion posicion = new Posicion();
        posicion.setX(x);
        posicion.setY(y);

        if ((mapa.get(x).get(y).getPosicion()).equals(posicion)) {
            if (mapa.get(x).get(y).isVisible()) {
                if (mapa.get(x).get(y).isEdificios()) {
                    System.out.println("En la celda que se quiere agrupar hay un edificio");
                    throw(new AgruparExtepcion());
                } else if (mapa.get(x).get(y).isContenedorDeRecursos()) {
                    System.out.println("En la celda que se quiere agrupar hay un contenedor de recursos");
                    throw(new AgruparExtepcion());
                } else if (mapa.get(x).get(y).getPersonajes().size() == 1) {
                    System.out.println("No se puede agrupar en la celda porque solo hay un personaje");
                    throw(new AgruparExtepcion());
                } else if (mapa.get(x).get(y).getPersonajes().size() > 1) {
                    if (mapa.get(x).get(y).isGrupo()) {
                        System.out.println("En la celda ya hay un grupo creado");
                        throw(new AgruparExtepcion());
                    } else {
                        int num = Grupos.size() + 1;
                        String cadena = String.valueOf(num);
                        String cadena1 = new String("grupo");
                        String cadena2 = cadena1.concat(cadena);
                        HashMap<String, Personaje> listap = new HashMap();
                        for (Personaje p : mapa.get(x).get(y).getPersonajes()) {
                            listap.put(p.getCodigo(), p);
                            Personajes.remove(p.getCodigo());
                        }
                        Grupo g = new Grupo(posicion, cadena2, cadena2, listap);
                        Grupos.put(g.getCodigo(), g);
                        civilizaciones.get(civilizacion).getGrupos().put(g.getCodigo(), g);
                        int ataque = calcularAtaque(cadena2, listap);
                        g.setCapacidadAtaqueDefensa(ataque);
                        Celda celda = new Celda(posicion);
                        celda.setGrupo(true);
                        mapa.get(posicion.getX()).set(posicion.getY(), celda);
                        System.out.println("Se ha creado el " + cadena2 + ". Sus miembros son: ");
                        Collection<Personaje> colpers = g.getPersonajes().values();
                        for (Personaje p : colpers) {
                            System.out.println(p.getCodigo());
                        }
                    }

                } else {
                    System.out.println("No se puede agrupar en celdas vacias (praderas)");
                    throw(new AgruparExtepcion());
                }

            } else {
                System.out.println("No se puede agrupar en celdas no visibles");
                throw(new AgruparExtepcion());
            }
        }
        vermapacivilizacion();
    }

    public String darNombre(String a) {
        int contador = 1;
        String nombre = "";
        if (a.equals("Paisano")) {
            nombre = "Paisano";
            Collection<Personaje> colpais = civilizaciones.get(civilizacion).getPersonajes().values();
            for (Personaje p : colpais) {
                if (p instanceof Paisano) {
                    contador++;
                }
            }
            return (nombre + contador);
        }
        if (a.equals("Arquero")) {
            nombre = "Arquero";
            Collection<Personaje> colarq = civilizaciones.get(civilizacion).getPersonajes().values();
            for (Personaje p : colarq) {
                if (p instanceof Arquero) {
                    contador++;
                }
            }
            return (nombre + contador);
        }
        if (a.equals("Caballero")) {
            nombre = "Caballero";
            Collection<Personaje> colcab = civilizaciones.get(civilizacion).getPersonajes().values();
            for (Personaje p : colcab) {
                if (p instanceof Caballero) {
                    contador++;
                }
            }
            return (nombre + contador);
        }
        if (a.equals("Legionario")) {
            nombre = "Legionario";
            Collection<Personaje> coleg = civilizaciones.get(civilizacion).getPersonajes().values();
            for (Personaje p : coleg) {
                if (p instanceof Legionario) {
                    contador++;
                }
            }
            return (nombre + contador);
        }
        if (a.equals("Ciudadela")) {
            nombre = "Ciudadela";
            Collection<Edificio> colciud = civilizaciones.get(civilizacion).getEdificios().values();
            for (Edificio e : colciud) {
                if (e instanceof Ciudadela) {
                    contador++;
                }
            }
            return (nombre + contador);
        }
        if (a.equals("Casa")) {
            nombre = "Casa";
            Collection<Edificio> colcas = civilizaciones.get(civilizacion).getEdificios().values();
            for (Edificio e : colcas) {
                if (e instanceof Casa) {
                    contador++;
                }
            }
            return (nombre + contador);
        }
        if (a.equals("Cuartel")) {
            nombre = "Cuartel";
            Collection<Edificio> colcua = civilizaciones.get(civilizacion).getEdificios().values();
            for (Edificio e : colcua) {
                if (e instanceof Cuartel) {
                    contador++;
                }
            }
            return (nombre + contador);
        }
        if (a.equals("Torre")) {
            nombre = "Torre";
            Collection<Edificio> coltor = civilizaciones.get(civilizacion).getEdificios().values();
            for (Edificio e : coltor) {
                if (e instanceof Torre) {
                    contador++;
                }
            }
            return (nombre + contador);
        }

        return null;
    }

    public int calcularAtaque(String a, HashMap hash) {
        int ataque = 0;
        Posicion pos1 = new Posicion();
        if (Grupos.containsKey(a)) {
            pos1 = Grupos.get(a).getPosicion();
        } else {
            System.out.println("El grupo no existe");
        }

        for (Personaje pers : mapa.get(pos1.getX()).get(pos1.getY()).getPersonajes()) {
            ataque += pers.getAtaque();
        }

        return ataque;
    }

    public void desagrupar(String a) throws PGExtepcion {

        if (Grupos.containsKey(a)) {
            if (civilizaciones.get(civilizacion).getGrupos().containsKey(a)) {
                ArrayList<Personaje> personajes = new ArrayList();
                for (Personaje p : Grupos.get(a).getPersonajes().values()) {
                    p.getPosicion().setX(Grupos.get(a).getPosicion().getX());
                    p.getPosicion().setY(Grupos.get(a).getPosicion().getY());
                    Personajes.put(p.getCodigo(), p);
                    personajes.add(p);
                }
                mapa.get(Grupos.get(a).getPosicion().getX()).get(Grupos.get(a).getPosicion().getY()).setGrupo(false);
                mapa.get(Grupos.get(a).getPosicion().getX()).get(Grupos.get(a).getPosicion().getY()).setPersonajes(personajes);
                System.out.println("El " + Grupos.get(a).getCodigo() + " ha sido eliminado");
                Grupos.clear();
                Grupos.remove(a);
                civilizaciones.get(civilizacion).getGrupos().remove(a);
                vermapacivilizacion();
            } else {
                System.out.println("El grupo no es de esta civilizacion");
                throw(new PGExtepcion());
            }
        } else {
            System.out.println("El grupo no existe");
            throw(new PGExtepcion());
        }

    }

    public void desligar(String a, String b) throws DesligarExtepcion{
        if (Grupos.containsKey(b)) {
            if (civilizaciones.get(civilizacion).getGrupos().containsKey(b)) {
                ArrayList<Personaje> lista = new ArrayList<Personaje>(Grupos.get(b).getPersonajes().values());
                for (int i = lista.size() - 1; i >= 0; i--) {
                    Personaje p = lista.get(i);
                    if (p.getCodigo().equals(a)) {
                        Personajes.put(p.getCodigo(), p);
                        p.setPosicion(Grupos.get(b).getPosicion());
                        System.out.println(p.getCodigo() + " ya no forma parte del grupo");
                        mapa.get(p.getPosicion().getX()).get(p.getPosicion().getY()).getPersonajes().add(p);
                        Grupos.get(b).setCapacidadAtaqueDefensa(Grupos.get(b).getCapacidadAtaqueDefensa() - p.getAtaque());
                        Grupos.get(b).getPersonajes().remove(p.getCodigo());
                        if (Grupos.get(b).getPersonajes().isEmpty()) {
                            mapa.get(Grupos.get(b).getPosicion().getX()).get(Grupos.get(b).getPosicion().getY()).setGrupo(false);
                            Grupos.remove(b);
                            civilizaciones.get(civilizacion).getGrupos().remove(b);
                        }
                        vermapacivilizacion();
                    }
                }
            } else {
                System.out.println("No tenemos este grupo en nuestra civilizacion");
                throw(new DesligarExtepcion());
            }
        } else {
            System.out.println("El grupo no existe");
            throw(new DesligarExtepcion());
        }
    }

    public void vermapa() {
        String dibujomapa = new String();
        for (int i = 0; i < mapa.size(); i++) {
            dibujomapa = dibujomapa + "\n";
            for (int j = 0; j < mapa.get(0).size(); j++) {
                Celda celda = mapa.get(i).get(j);
                if (!celda.isVisible()) {
                    dibujomapa = dibujomapa + " " + "\t";
                } else if (celda.isContenedorDeRecursos()) {
                    Collection<ContenedorDeRecurso> colrec = ContenedorDeRecursos.values();
                    for (ContenedorDeRecurso cont : colrec) {
                        if (cont.getPosicion().getX() == i && cont.getPosicion().getY() == j) {
                            if (cont instanceof Bosque) {
                                dibujomapa = dibujomapa + "\033[32mB" + "\t";
                            } else if (cont instanceof Cantera) {
                                dibujomapa = dibujomapa + "\033[32mC" + "\t";
                            } else if (cont instanceof Arbusto) {
                                dibujomapa = dibujomapa + "\033[32mA" + "\t";
                            }
                        }
                    }
                } else if (celda.isEdificios()) {
                    Collection<Edificio> coledif = Edificios.values();
                    for (Edificio edif : coledif) {
                        if (edif.getPosicion().equals(celda.getPosicion())) {
                            if (edif instanceof Ciudadela) {
                                dibujomapa = dibujomapa + "\033[31mCi" + "\t";
                            } else if (edif instanceof Casa) {
                                dibujomapa = dibujomapa + "\033[31mCa" + "\t";
                            } else if (edif instanceof Cuartel) {
                                dibujomapa = dibujomapa + "\033[31mCu" + "\t";
                            } else if (edif instanceof Torre) {
                                dibujomapa = dibujomapa + "\033[31mT" + "\t";
                            }
                        }
                    }
                } else if (!celda.getPersonajes().isEmpty()) {
                    if (celda.getPersonajes().size() == 1) {
                        for (int k = 0; k < celda.getPersonajes().size(); k++) {
                            if (celda.getPersonajes().get(k) instanceof Soldado) {
                                dibujomapa = dibujomapa + "\033[33mS" + "\t";
                            }
                            if (celda.getPersonajes().get(k) instanceof Paisano) {
                                dibujomapa = dibujomapa + "\033[33mP" + "\t";
                            }
                        }
                    } else {
                        for (int k = 0; k < celda.getPersonajes().size(); k++) {
                            if (celda.getPersonajes().get(k) instanceof Soldado) {
                                dibujomapa = dibujomapa + "\033[33mS";
                            }
                            if (celda.getPersonajes().get(k) instanceof Paisano) {
                                dibujomapa = dibujomapa + "\033[33mP";
                            }
                        }
                        dibujomapa = dibujomapa + "\t";
                    }

                } else if (celda.isGrupo()) {
                    dibujomapa = dibujomapa + "\033[32mG" + "\t";
                } else {
                    dibujomapa = dibujomapa + "\033[34m0" + "\t";
                }
            }
        }
        System.out.println(dibujomapa);
    }

    public void vermapacivilizacion() {
        System.out.println("\033[31mCi" + "--->" + "Ciudadela" + "\t" + "\033[31mCa" + "--->" + "Casa" + "\t" + "\033[31mCu" + "--->" + "Cuartel" + "\t" + "\033[31mT" + "--->" + "Torre");
        System.out.println("\033[33mCa" + "--->" + "Caballero" + "\t" + "\033[33mLe" + "--->" + "Legionario" + "\t" + "\033[33mAr" + "--->" + "Arquero" + "\t" + "\033[33mP" + "--->" + "Paisano");
        
        if (civilizaciones.containsKey(civilizacion)) {
            civilizaciones.get(civilizacion);
            for (int i = 0; i < civilizaciones.get(civilizacion).getCeldasvisibles().size(); i++) {
                mapa.get(civilizaciones.get(civilizacion).getCeldasvisibles().get(i).getPosicion().getX()).get(civilizaciones.get(civilizacion).getCeldasvisibles().get(i).getPosicion().getY()).setVisible(true);
            }
            String dibujomapa = new String();
            for (int i = 0; i < mapa.size(); i++) {
                dibujomapa = dibujomapa + "\n";
                for (int j = 0; j < mapa.get(0).size(); j++) {
                    Celda celda = mapa.get(i).get(j);
                    if (!celda.isVisible()) {
                        dibujomapa = dibujomapa + " " + "\t";
                    } else if (celda.isContenedorDeRecursos()) {
                        Collection<ContenedorDeRecurso> colrec = ContenedorDeRecursos.values();
                        for (ContenedorDeRecurso cont : colrec) {
                            if (cont.getPosicion().getX() == i && cont.getPosicion().getY() == j) {
                                if (cont instanceof Bosque) {
                                    dibujomapa = dibujomapa + "\033[32mB" + "\t";
                                } else if (cont instanceof Cantera) {
                                    dibujomapa = dibujomapa + "\033[32mC" + "\t";
                                } else if (cont instanceof Arbusto) {
                                    dibujomapa = dibujomapa + "\033[32mA" + "\t";
                                }
                            }
                        }
                    } else if (celda.isEdificios()) {
                        Collection<Edificio> coledif = civilizaciones.get(civilizacion).getEdificios().values();
                        for (Edificio edif : coledif) {
                            if (edif.getPosicion().equals(celda.getPosicion())) {
                                if (edif instanceof Ciudadela) {
                                    dibujomapa = dibujomapa + "\033[31mCi" + "\t";
                                } else if (edif instanceof Casa) {
                                    dibujomapa = dibujomapa + "\033[31mCa" + "\t";
                                } else if (edif instanceof Cuartel) {
                                    dibujomapa = dibujomapa + "\033[31mCu" + "\t";
                                } else if (edif instanceof Torre) {
                                    dibujomapa = dibujomapa + "\033[31mT" + "\t";
                                }
                            }
                        }
                    } else if (!celda.getPersonajes().isEmpty()) {
                        if (celda.getPersonajes().size() == 1) {
                            for (int k = 0; k < celda.getPersonajes().size(); k++) {
                                if (celda.getPersonajes().get(k) instanceof Caballero) {
                                    dibujomapa = dibujomapa + "\033[33mCa" + "\t";
                                }
                                if (celda.getPersonajes().get(k) instanceof Legionario) {
                                    dibujomapa = dibujomapa + "\033[33mLe" + "\t";
                                }
                                if (celda.getPersonajes().get(k) instanceof Arquero) {
                                    dibujomapa = dibujomapa + "\033[33mAr" + "\t";
                                }
                                if (celda.getPersonajes().get(k) instanceof Paisano) {
                                    dibujomapa = dibujomapa + "\033[33mP" + "\t";
                                }
                            }
                        } else {
                            for (int k = 0; k < celda.getPersonajes().size(); k++) {
                                if (celda.getPersonajes().get(k) instanceof Caballero) {
                                    dibujomapa = dibujomapa + "\033[33mCa";
                                }
                                if (celda.getPersonajes().get(k) instanceof Legionario) {
                                    dibujomapa = dibujomapa + "\033[33mLe";
                                }
                                if (celda.getPersonajes().get(k) instanceof Arquero) {
                                    dibujomapa = dibujomapa + "\033[33mAr";
                                }
                                if (celda.getPersonajes().get(k) instanceof Paisano) {
                                    dibujomapa = dibujomapa + "\033[33mP";
                                }
                            }
                            dibujomapa = dibujomapa + "\t";
                        }

                    } else if (celda.isGrupo()) {
                        dibujomapa = dibujomapa + "\033[32mG" + "\t";
                    } else {
                        dibujomapa = dibujomapa + "\033[34m0" + "\t";
                    }
                }
            }
            System.out.println(dibujomapa);

        }
    }

    public void imprimir(String a) {
        vermapacivilizacion();
    }

    public boolean TorreAdyacente(Grupo g1) {
        Posicion posad1 = new Posicion();
        posad1.setX(g1.getPosicion().getX() + 1);
        posad1.setY(g1.getPosicion().getY());
        Posicion posad2 = new Posicion();
        posad2.setX(g1.getPosicion().getX() - 1);
        posad2.setY(g1.getPosicion().getY());
        Posicion posad3 = new Posicion();
        posad3.setX(g1.getPosicion().getX());
        posad3.setY(g1.getPosicion().getY() + 1);
        Posicion posad4 = new Posicion();
        posad4.setX(g1.getPosicion().getX());
        posad4.setY(g1.getPosicion().getY() - 1);
        if (Edificios.containsKey(posad1)) {
            if (Edificios.get(posad1) instanceof Torre && !civilizaciones.get(civilizacion).getEdificios().containsKey(posad1)) {
                return true;
            }
        } else if (Edificios.containsKey(posad2)) {
            if (Edificios.get(posad2) instanceof Torre && !civilizaciones.get(civilizacion).getEdificios().containsKey(posad2)) {
                return true;
            }
        } else if (Edificios.containsKey(posad3)) {
            if (Edificios.get(posad3) instanceof Torre && !civilizaciones.get(civilizacion).getEdificios().containsKey(posad3)) {
                return true;
            }
        } else if (Edificios.containsKey(posad4)) {
            if (Edificios.get(posad4) instanceof Torre && !civilizaciones.get(civilizacion).getEdificios().containsKey(posad4)) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    public boolean TorreAdyacente(Personaje p1) {
        Posicion posad1 = new Posicion();
        posad1.setX(p1.getPosicion().getX() + 1);
        posad1.setY(p1.getPosicion().getY());
        Posicion posad2 = new Posicion();
        posad2.setX(p1.getPosicion().getX() - 1);
        posad2.setY(p1.getPosicion().getY());
        Posicion posad3 = new Posicion();
        posad3.setX(p1.getPosicion().getX());
        posad3.setY(p1.getPosicion().getY() + 1);
        Posicion posad4 = new Posicion();
        posad4.setX(p1.getPosicion().getX());
        posad4.setY(p1.getPosicion().getY() - 1);
        if (Edificios.containsKey(posad1)) {
            if (Edificios.get(posad1) instanceof Torre && !civilizaciones.get(civilizacion).getEdificios().containsKey(posad1)) {
                return true;
            }
        } else if (Edificios.containsKey(posad2)) {
            if (Edificios.get(posad2) instanceof Torre && !civilizaciones.get(civilizacion).getEdificios().containsKey(posad2)) {
                return true;
            }
        } else if (Edificios.containsKey(posad3)) {
            if (Edificios.get(posad3) instanceof Torre && !civilizaciones.get(civilizacion).getEdificios().containsKey(posad3)) {
                return true;
            }
        } else if (Edificios.containsKey(posad4)) {
            if (Edificios.get(posad4) instanceof Torre && !civilizaciones.get(civilizacion).getEdificios().containsKey(posad4)) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    public void AtacarTorre(Personaje p1) {
        if (0 >= p1.getSalud() - (50 - p1.getArmadura())) {
            System.out.println("El personaje " + p1.getCodigo() + " ha muerto");
            Personajes.remove(p1.getCodigo());
            civilizaciones.get(civilizacion).getPersonajes().remove(p1.getCodigo());
            mapa.get(p1.getPosicion().getX()).get(p1.getPosicion().getY()).getPersonajes().remove(p1);
            return;
        }
        try{
        System.out.println("Ha recibido un daño de " + (50 - p1.getArmadura()));
        p1.setSalud(p1.getSalud() - (50 - p1.getArmadura()));
        System.out.println("La salud de " + p1.getCodigo() + " es de " + p1.getSalud());
        }catch(DatosExtepcion de){
            de.ErrorDatos();
        }
    }

    public void AtacarTorre(Grupo g) {
        double ataque = 100 - g.getCapacidadAtaqueDefensa();
        ataque = ataque / g.getPersonajes().size();
        System.out.println(g.getCodigo() + " ha sido atacado por la torre");
        List<Personaje> lista = new ArrayList<>(g.getPersonajes().values());
        for (int i = lista.size() - 1; i >= 0; i--) {
            Personaje p = lista.get(i);
            if (0 >= p.getSalud() - ataque) {
                System.out.println("El personaje " + p.getCodigo() + " ha muerto");
                g.getPersonajes().remove(p.getCodigo());
                Personajes.remove(p.getCodigo());
                civilizaciones.get(civilizacion).getPersonajes().remove(p.getCodigo());
                if (g.getPersonajes().isEmpty()) {
                    System.out.println("Has matado al grupo " + g.getCodigo());
                    mapa.get(g.getPosicion().getX()).get(g.getPosicion().getY()).setGrupo(false);
                    Grupos.remove(g.getCodigo());
                    civilizaciones.get(civilizacion).getGrupos().remove(g.getCodigo());
                    vermapa();
                    return;
                }
            } else {
                try{
                p.setSalud(p.getSalud() - ataque);
                System.out.println("A " + p.getCodigo() + " le queda " + p.getSalud() + " salud");
            }catch(DatosExtepcion de){
            de.ErrorDatos();
            }
          }
        }
    }
    public void Desalojar(String a, String b, String c) {
        Posicion pos1 = new Posicion();
        Posicion pos2 = new Posicion();

        if (Personajes.containsKey(b)) {
            pos1 = Personajes.get(b).getPosicion();
        } else if (Grupos.containsKey(b)) {
            pos1 = Grupos.get(b).getPosicion();
        } else {
            System.out.println("No existe el personaje");
            return;
        }

        if (c.equals("SUR") || c.equals("sur")) {
            pos2.setX(pos1.getX() + 1);
            pos2.setY(pos1.getY());
        } else if (c.equals("NORTE") || c.equals("norte")) {
            pos2.setX(pos1.getX() - 1);
            pos2.setY(pos1.getY());
        } else if (c.equals("ESTE") || c.equals("este")) {
            pos2.setX(pos1.getX());
            pos2.setY(pos1.getY() + 1);
        } else if (c.equals("OESTE") || c.equals("oeste")) {
            pos2.setX(pos1.getX());
            pos2.setY(pos1.getY() - 1);
        }

        Collection<Edificio> coledifdes = Edificios.values();
        for (Edificio edifdes : coledifdes) {
            if (edifdes.getPosicion().equals(pos1)) {
                if (Personajes.containsKey(b)) {
                    for (int i = 0; i < edifdes.getPersonajes().size(); i++) {
                        if (edifdes.getPersonajes().get(i).getCodigo().equals(b)) {
                            edifdes.getPersonajes().remove(edifdes.getPersonajes().get(i));
                        }
                    }
                    Personajes.get(b).setPosicion(pos2);
                    mapa.get(pos2.getX()).get(pos2.getY()).getPersonajes().add(Personajes.get(b));
                    if (TorreAdyacente(Personajes.get(b))) {
                        AtacarTorre(Personajes.get(b));
                    }
                } else if (Grupos.containsKey(b)) {
                    for (int i = 0; i < edifdes.getGrupos().size(); i++) {
                        if (edifdes.getGrupos().get(i).getCodigo().equals(b)) {
                            edifdes.getGrupos().remove(edifdes.getGrupos().get(i));
                        }
                    }
                    Grupos.get(b).setPosicion(pos2);
                    mapa.get(pos2.getX()).get(pos2.getY()).setGrupo(true);
                    if (TorreAdyacente(Grupos.get(b))) {
                        AtacarTorre(Grupos.get(b));
                    }
                }
                vermapa();
            }
        }
    }
    public void leerarchivomapa() throws IOException{
    try{
            FileReader r=new FileReader("/home/raul/Escritorio/mapa.csv");
            BufferedReader buffer= new BufferedReader(r);
            System.out.println(buffer.readLine());
            String linea="";
            while(linea!=null){
                linea=buffer.readLine();
                if(linea==null){
                    break;
                }
                String [] lineas =linea.split(";");
                String [] caracter=lineas[0].split(",");
                int x = Integer.parseInt(caracter[0]);
                int y = Integer.parseInt(caracter[1]);
                   if(lineas[1].equals("Pradera")){
                    mapa.get(x).get(y).setContenedorDeRecursos(false);
                    mapa.get(x).get(y).setEdificios(false);
                    mapa.get(x).get(y).setVisible(false);
                    System.out.println(caracter[0]+caracter[1]+"Pradera");
                    }
                   else if(lineas[1].equals("Bosque")){
                       Posicion pos1=new Posicion(x,y);
                       int cantidad=Integer.parseInt(lineas[4]);
                       Bosque bosque= new Bosque(pos1,lineas[2],lineas[3]);
                        ContenedorDeRecursos.put(pos1,bosque);
                        mapa.get(x).get(y).setContenedorDeRecursos(true);
                        mapa.get(x).get(y).setEdificios(false);
                        mapa.get(x).get(y).setVisible(false);
                       System.out.println(caracter[0]+caracter[1]+"Bosque");
                   }
                   else if(lineas[1].equals("Cantera")){
                       Posicion pos2=new Posicion(x,y);
                       int cantidad=Integer.parseInt(lineas[4]);
                       Cantera cantera= new Cantera(pos2,lineas[2],lineas[3]);
                        ContenedorDeRecursos.put(pos2,cantera);
                        mapa.get(x).get(y).setContenedorDeRecursos(true);
                        mapa.get(x).get(y).setEdificios(false);
                        mapa.get(x).get(y).setVisible(false);
                       System.out.println(caracter[0]+caracter[1]+"Cantera");
                   }
                   else if(lineas[1].equals("Arbusto")){
                       Posicion pos3=new Posicion(x,y);
                       int cantidad=Integer.parseInt(lineas[3]);
                       Arbusto arbusto= new Arbusto(pos3,lineas[2],lineas[2]);
                        ContenedorDeRecursos.put(pos3,arbusto);
                        mapa.get(x).get(y).setContenedorDeRecursos(true);
                        mapa.get(x).get(y).setEdificios(false);
                        mapa.get(x).get(y).setVisible(false);
                       System.out.println(caracter[0]+caracter[1]+"Arbusto");
                   }
            }
        
        
        }catch(IOException e){
            Errorlectura();
        }
    }
    public void leerarchivopersonajes() throws IOException{
        try{
            FileReader r= new FileReader("/home/raul/Escritorio/personajes.csv");
            BufferedReader buffer= new BufferedReader(r);
            System.out.println(buffer.readLine());
            String linea="";
            while(linea!=null){
                linea=buffer.readLine();
                if(linea==null){
                    break;
                }
                String [] lineas =linea.split(";");
                String [] caracter=lineas[0].split(",");
                int x = Integer.parseInt(caracter[0]);
                int y = Integer.parseInt(caracter[1]);
                int salud=Integer.parseInt(lineas[4]);
                int ataque=Integer.parseInt(lineas[5]);
                int defensa=Integer.parseInt(lineas[6]);
                civilizacion=lineas[8];
                    if(lineas[1].equals("Paisano")){
                       Posicion pos0= new Posicion(x,y);
                       Paisano paisano=new Paisano(pos0,lineas[2],"Paisano");
                        mapa.get(x).get(y).setContenedorDeRecursos(false);
                        mapa.get(x).get(y).setEdificios(false);
                        mapa.get(x).get(y).setVisible(false);
                        mapa.get(x).get(y).getPersonajes().add((Personaje)paisano);
                        Personajes.put(lineas[2], (Personaje)paisano);
                        if(civilizaciones.containsKey(lineas[8])){
                            civilizaciones.get(lineas[8]).anadirPersonaje(paisano,mapa);
                        }else{
                            Civilizacion civili=new Civilizacion(lineas[8]);
                            civilizaciones.put(lineas[8],civili);
                            civilizaciones.get(lineas[8]).anadirPersonaje((Personaje)paisano,mapa);
                            
                        }
                        if(lineas[7].equals("")){
                            
                        }else{
                            if(Grupos.containsKey(lineas[7])){
                                Grupos.get(lineas[7]).getPersonajes().put(lineas[2],(Personaje)paisano);
                                Personajes.remove(lineas[3]);
                                for(int i=0;i<mapa.get(x).get(y).getPersonajes().size();i++){
                                    mapa.get(x).get(y).getPersonajes().remove(i);
                                }
                                mapa.get(x).get(y).setGrupo(true);
                            }else{
                                HashMap<String,Personaje> personajes=new HashMap<>();
                                Grupo grupo=new Grupo(pos0,lineas[7],lineas[7],personajes);
                                grupo.getPersonajes().put(lineas[2], (Personaje)paisano);
                                Personajes.remove(lineas[3]);
                                Grupos.put(lineas[7],grupo);
                                for(int i=0;i<mapa.get(x).get(y).getPersonajes().size();i++){
                                    mapa.get(x).get(y).getPersonajes().remove(i);
                                }
                                civilizaciones.get(lineas[8]).getGrupos().put(lineas[7], grupo);
                            }
                        }
                        System.out.println(caracter[0]+caracter[1]+"Paisano");
                    }
                   else if(lineas[1].equals("Caballero")){
                       Posicion pos2= new Posicion(x,y);
                       Caballero caballero=new Caballero(pos2,lineas[2],lineas[3]);
                        mapa.get(x).get(y).setContenedorDeRecursos(false);
                        mapa.get(x).get(y).setEdificios(false);
                        mapa.get(x).get(y).setVisible(false);
                        mapa.get(x).get(y).getPersonajes().add(caballero);
                        Personajes.put(lineas[2],(Personaje)caballero);
                       System.out.println(caracter[0]+caracter[1]+"Caballero");
                        if(civilizaciones.containsKey(lineas[8])){
                             civilizaciones.get(lineas[8]).anadirPersonaje((Personaje)caballero,mapa);
                         }else{
                             Civilizacion civili=new Civilizacion(lineas[8]);
                             civilizaciones.put(lineas[8],civili);
                             civilizaciones.get(lineas[8]).anadirPersonaje((Personaje)caballero,mapa);
                             
                         }
                        if(lineas[7].equals("")){
                            
                        }else{
                            if(Grupos.containsKey(lineas[7])){
                                Grupos.get(lineas[7]).getPersonajes().put(lineas[2],(Personaje)caballero);
                                Personajes.remove(lineas[3]);
                                for(int i=0;i<mapa.get(x).get(y).getPersonajes().size();i++){
                                        mapa.get(x).get(y).getPersonajes().remove(i);
                                    
                                }
                                mapa.get(x).get(y).setGrupo(true);
                            }else{
                                HashMap<String,Personaje> personajes=new HashMap<>();
                                Grupo grupo=new Grupo(pos2,lineas[7],lineas[7],personajes);
                                grupo.getPersonajes().put(lineas[2], (Personaje)caballero);
                                Personajes.remove(lineas[3]);
                                Grupos.put(lineas[7],grupo);
                                civilizaciones.get(lineas[8]).getGrupos().put(lineas[7], grupo);
                                for(int i=0;i<mapa.get(x).get(y).getPersonajes().size();i++){
                                    mapa.get(x).get(y).getPersonajes().remove(i);
                                }
                                mapa.get(x).get(y).setGrupo(true);
                            }
                        }
                   }else if(lineas[1].equals("Arquero")){
                       Posicion pos0= new Posicion(x,y);
                       Arquero arquero=new Arquero(pos0,lineas[2],"Arquero");
                        mapa.get(x).get(y).setContenedorDeRecursos(false);
                        mapa.get(x).get(y).setEdificios(false);
                        mapa.get(x).get(y).setVisible(false);
                        mapa.get(x).get(y).getPersonajes().add((Personaje)arquero);
                        Personajes.put(lineas[2], (Personaje)arquero);
                        if(civilizaciones.containsKey(lineas[8])){
                            civilizaciones.get(lineas[8]).anadirPersonaje(arquero,mapa);
                        }else{
                            Civilizacion civili=new Civilizacion(lineas[8]);
                            civilizaciones.put(lineas[8],civili);
                            civilizaciones.get(lineas[8]).anadirPersonaje((Personaje)arquero,mapa);
                            
                        }
                        if(lineas[7].equals("")){
                            
                        }else{
                            if(Grupos.containsKey(lineas[7])){
                                Grupos.get(lineas[7]).getPersonajes().put(lineas[2],(Personaje)arquero);
                                Personajes.remove(lineas[3]);
                                for(int i=0;i<mapa.get(x).get(y).getPersonajes().size();i++){
                                    mapa.get(x).get(y).getPersonajes().remove(i);
                                }
                                mapa.get(x).get(y).setGrupo(true);
                            }else{
                                HashMap<String,Personaje> personajes=new HashMap<>();
                                Grupo grupo=new Grupo(pos0,lineas[7],lineas[7],personajes);
                                grupo.getPersonajes().put(lineas[2], (Personaje)arquero);
                                Personajes.remove(lineas[3]);
                                Grupos.put(lineas[7],grupo);
                                for(int i=0;i<mapa.get(x).get(y).getPersonajes().size();i++){
                                    mapa.get(x).get(y).getPersonajes().remove(i);
                                }
                                civilizaciones.get(lineas[8]).getGrupos().put(lineas[7], grupo);
                            }
                        }
                        System.out.println(caracter[0]+caracter[1]+"Arquero");
                    }else if(lineas[1].equals("Legionario")){
                       Posicion pos0= new Posicion(x,y);
                       Legionario legionario=new Legionario(pos0,lineas[2],"Legionario");
                        mapa.get(x).get(y).setContenedorDeRecursos(false);
                        mapa.get(x).get(y).setEdificios(false);
                        mapa.get(x).get(y).setVisible(false);
                        mapa.get(x).get(y).getPersonajes().add((Personaje)legionario);
                        Personajes.put(lineas[2], (Personaje)legionario);
                        if(civilizaciones.containsKey(lineas[8])){
                            civilizaciones.get(lineas[8]).anadirPersonaje(legionario,mapa);
                        }else{
                            Civilizacion civili=new Civilizacion(lineas[8]);
                            civilizaciones.put(lineas[8],civili);
                            civilizaciones.get(lineas[8]).anadirPersonaje((Personaje)legionario,mapa);
                            
                        }
                        if(lineas[7].equals("")){
                            
                        }else{
                            if(Grupos.containsKey(lineas[7])){
                                Grupos.get(lineas[7]).getPersonajes().put(lineas[2],(Personaje)legionario);
                                Personajes.remove(lineas[3]);
                                for(int i=0;i<mapa.get(x).get(y).getPersonajes().size();i++){
                                    mapa.get(x).get(y).getPersonajes().remove(i);
                                }
                                mapa.get(x).get(y).setGrupo(true);
                            }else{
                                HashMap<String,Personaje> personajes=new HashMap<>();
                                Grupo grupo=new Grupo(pos0,lineas[7],lineas[7],personajes);
                                grupo.getPersonajes().put(lineas[2], (Personaje)legionario);
                                Personajes.remove(lineas[3]);
                                Grupos.put(lineas[7],grupo);
                                for(int i=0;i<mapa.get(x).get(y).getPersonajes().size();i++){
                                    mapa.get(x).get(y).getPersonajes().remove(i);
                                }
                                civilizaciones.get(lineas[8]).getGrupos().put(lineas[7], grupo);
                            }
                        }
                        System.out.println(caracter[0]+caracter[1]+"Legionario");
                    }
            }
        
        
        }catch(IOException e){
           // System.out.println("Error");
            Errorlectura();
        }
    }
    public void leerarchivoedificios() throws IOException{
                try{
            FileReader r=new FileReader("/home/raul/Escritorio/edificios.csv");
            BufferedReader buffer= new BufferedReader(r);
            System.out.println(buffer.readLine());
            String linea="";
            while(linea!=null){
                linea=buffer.readLine();
                if(linea==null){
                    break;
                }
                String [] lineas =linea.split(";");
                String [] caracter=lineas[0].split(",");
                int x = Integer.parseInt(caracter[0]);
                int y = Integer.parseInt(caracter[1]);
                   if(lineas[1].equals("Torre")){
                       Posicion pos0= new Posicion(x,y);
                       Torre torre=new Torre(pos0,lineas[2]);
                       Edificios.put(pos0,torre);
                        mapa.get(x).get(y).setContenedorDeRecursos(false);
                        mapa.get(x).get(y).setEdificios(true);
                        mapa.get(x).get(y).setVisible(true);
                    System.out.println(caracter[0]+caracter[1]+"Torre");
                    if(civilizaciones.containsKey(lineas[4])){
                            civilizaciones.get(lineas[4]).anadirEdificio(torre,mapa);
                        }else{
                            Civilizacion civili=new Civilizacion(lineas[4]);
                            civilizaciones.put(lineas[4],civili);
                            civilizaciones.get(lineas[4]).anadirEdificio(torre,mapa);
                            
                        }
                    }
                   else if(lineas[1].equals("Ciudadela")){
                       Posicion pos1= new Posicion(x,y);
                       Ciudadela ciudadela=new Ciudadela(pos1,lineas[2]);
                       Edificios.put(pos1,ciudadela);
                        mapa.get(x).get(y).setContenedorDeRecursos(false);
                        mapa.get(x).get(y).setEdificios(true);
                        mapa.get(x).get(y).setVisible(false);   
                        System.out.println(caracter[0]+caracter[1]+"Ciudadela");
                        if(civilizaciones.containsKey(lineas[4])){
                            civilizaciones.get(lineas[4]).anadirEdificio(ciudadela,mapa);
                        }else{
                            Civilizacion civili=new Civilizacion(lineas[4]);
                            civilizaciones.put(lineas[4],civili);
                            civilizaciones.get(lineas[4]).anadirEdificio(ciudadela,mapa);
                            
                        }
                   }
                   else if(lineas[1].equals("Cuartel")){
                       Posicion pos2= new Posicion(x,y);
                       Cuartel cuartel=new Cuartel(pos2,lineas[2]);
                       Edificios.put(pos2,cuartel);
                        mapa.get(x).get(y).setContenedorDeRecursos(false);
                        mapa.get(x).get(y).setEdificios(true);
                        mapa.get(x).get(y).setVisible(false);
                       System.out.println(caracter[0]+caracter[1]+"Cuartel");
                       if(civilizaciones.containsKey(lineas[4])){
                            civilizaciones.get(lineas[4]).anadirEdificio(cuartel,mapa);
                        }else{
                            Civilizacion civili=new Civilizacion(lineas[4]);
                            civilizaciones.put(lineas[4],civili);
                            civilizaciones.get(lineas[4]).anadirEdificio(cuartel,mapa);
                            
                        }
                   }
                   else if(lineas[1].equals("Casa")){
                       Posicion pos3= new Posicion(x,y);
                       Casa casa=new Casa(pos3,lineas[2]);
                       Edificios.put(pos3,casa);
                        mapa.get(x).get(y).setContenedorDeRecursos(false);
                        mapa.get(x).get(y).setEdificios(true);
                        mapa.get(x).get(y).setVisible(false);
                       System.out.println(caracter[0]+caracter[1]+"Casa");
                       if(civilizaciones.containsKey(lineas[8])){
                            civilizaciones.get(lineas[4]).anadirEdificio(casa,mapa);
                        }else{
                            Civilizacion civili=new Civilizacion(lineas[4]);
                            civilizaciones.put(lineas[4],civili);
                            civilizaciones.get(lineas[4]).anadirEdificio(casa,mapa);
                            
                        }
                   }
            }
        
        }catch(IOException e){
            //System.out.println("ERROR " + e.getMessage());
            Errorlectura();
        }
    }
    public void Errorlectura(){
        System.out.println("No podemos leer el archivo");
    }
}
