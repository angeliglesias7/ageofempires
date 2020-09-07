/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Elementos.Ciudadela;
import Elementos.Cuartel;
import Elementos.Edificio;
import Elementos.Legionario;
import Elementos.Arquero;
import Elementos.Caballero;
import Elementos.Personaje;
import Elementos.Paisano;
import java.util.Collection;

/**
 *
 * @author raul
 */
public class ComandoCrear implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoCrear(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion,CrearExtepcion,LimiteExtepcion,PGExtepcion{
            ConsolaNormal consola = new ConsolaNormal();
            if (comando.length != 3) {
                consola.imprimir("Error de sintaxis: crear <edificio> <personaje>\n");
                throw (new ComandoExtepcion());
            } else {
                int z = 0;
                int alimentos = 0;
                int ciudadela = 0;
                Posicion posicion = new Posicion();
                Collection<Edificio> coledif = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getEdificios().values();
                for (Edificio edif : coledif) {
                    if (edif.getNombre().equals(comando[1])) {
                        posicion.setX(edif.getPosicion().getX());
                        posicion.setY(edif.getPosicion().getY());
                    }
                }

                Collection<Personaje> col = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().values();
                for (Personaje pers : col) {
                    if (pers.getCodigo().equals(comando[2])) {
                        consola.imprimir("No se puede crear un personaje con este nombre");
                        z = 1;
                    }
                    break;
                }
                if (z != 1) {
                    if (posicion.getX() < mapa1.getFilas() && posicion.getY() < mapa1.getColumnas()) {
                        if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getEdificios().containsKey(posicion)) {
                            if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getEdificios().get(posicion) instanceof Ciudadela) {
                                Ciudadela ciud = (Ciudadela) mapa1.getEdificios().get(posicion);
                                if (ciud.getAlimento() >= ciud.getCosteCreacionPersonaje()) {
                                    Posicion posicion1 = new Posicion();
                                    if (posicion.getX() + 1 < mapa1.getFilas() && !mapa1.getMapa().get(posicion.getX() + 1).get(posicion.getY()).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX() + 1).get(posicion.getY()).isEdificios()) {
                                        posicion1.setX(posicion.getX() + 1);
                                        posicion1.setY(posicion.getY());
                                    } else if (posicion.getY() + 1 < mapa1.getColumnas() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() + 1).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() + 1).isEdificios()) {
                                        posicion1.setX(posicion.getX());
                                        posicion1.setY(posicion.getY() + 1);
                                    } else if (posicion.getX() - 1 < mapa1.getFilas() && !mapa1.getMapa().get(posicion.getX() - 1).get(posicion.getY()).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX() - 1).get(posicion.getY()).isEdificios()) {
                                        posicion1.setX(posicion.getX() - 1);
                                        posicion1.setY(posicion.getY());
                                    } else if (posicion.getY() - 1 < mapa1.getColumnas() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() - 1).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() - 1).isEdificios()) {
                                        posicion1.setX(posicion.getX());
                                        posicion1.setY(posicion.getY() - 1);
                                    } else {
                                        throw (new ComandoExtepcion());
                                    }
                                    String nombre = mapa1.darNombre(comando[2]);
                                    try{
                                    Personaje p = (Paisano) ciud.crear(comando[2]);
                                    
                                    p.setPosicion(posicion1);
                                    p.setCodigo(nombre);
                                    mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().put(p.getCodigo(), p);
                                    mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).anadirPersonaje(p, mapa1.getMapa());
                                       try{
                                    ciud.setAlimento(ciud.getAlimento() - (int) ciud.getCosteCreacionPersonaje());
                                    
                                    //System.out.println("Coste de creación: " + Edificios.get(posicion).getCosteCreacionPersonaje() + " unidades de comida");
                                    Celda celda = new Celda(posicion1, p);
                                    int x = celda.getPosicion().getX();
                                    int y = celda.getPosicion().getY();

                                    if (x + 1 >= 0 && x + 1 < mapa1.getFilas()) {
                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x + 1).get(y));
                                    }
                                    if (x - 1 >= 0 && x - 1 < mapa1.getFilas()) {
                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x - 1).get(y));
                                    }
                                    if (y + 1 >= 0 && y + 1 < mapa1.getColumnas()) {
                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x).get(y + 1));
                                    }
                                    if (y - 1 >= 0 && y - 1 < mapa1.getColumnas()) {
                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x).get(y - 1));
                                    }
                                    }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                    if (mapa1.TorreAdyacente(p)) {
                                        mapa1.AtacarTorre(p);
                                    }
                                    mapa1.vermapacivilizacion();
                                    }catch(CrearExtepcion ce){
                                            ce.ErrorCrear();
                                            }
                                    //System.out.println("Te quedan " + mapa1.getEdificios().get(posicion).getAlimento() + " unidades de capacidad de alojamiento");
                                } else if (ciud.getAlimento() <= mapa1.getEdificios().get(posicion).getCosteCreacionPersonaje()) {
                                    Collection<Edificio> colectciu = mapa1.getEdificios().values();
                                    for (Edificio ciu : colectciu) {
                                        if (ciu instanceof Ciudadela) {
                                            Ciudadela ciu2 = (Ciudadela) ciu;
                                            alimentos = alimentos + (int) ciu2.getAlimento();
                                            ciudadela++;
                                        }
                                    }
                                    if (alimentos < mapa1.getEdificios().get(posicion).getCosteCreacionPersonaje()) {
                                        consola.imprimir("No hay suficientes alimentos en las ciudadelas");
                                        throw (new CrearExtepcion());
                                    } else {
                                        alimentos = alimentos - (int) ciud.getCosteCreacionPersonaje();
                                        alimentos = alimentos / ciudadela;
                                        for (Edificio ciuda : colectciu) {
                                            if (ciuda instanceof Ciudadela) {
                                                Ciudadela ciudade = (Ciudadela) ciuda;
                                                try{
                                                ciudade.setAlimento(alimentos);
                                                }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                            }
                                        }
                                        Posicion posicion1 = new Posicion();
                                        if (posicion.getX() + 1 < mapa1.getFilas() && !mapa1.getMapa().get(posicion.getX() + 1).get(posicion.getY()).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX() + 1).get(posicion.getY()).isEdificios()) {
                                            posicion1.setX(posicion.getX() + 1);
                                            posicion1.setY(posicion.getY());
                                        } else if (posicion.getY() + 1 < mapa1.getColumnas() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() + 1).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() + 1).isEdificios()) {
                                            posicion1.setX(posicion.getX());
                                            posicion1.setY(posicion.getY() + 1);
                                        } else if (posicion.getX() - 1 < mapa1.getFilas() && !mapa1.getMapa().get(posicion.getX() - 1).get(posicion.getY()).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX() - 1).get(posicion.getY()).isEdificios()) {
                                            posicion1.setX(posicion.getX() - 1);
                                            posicion1.setY(posicion.getY());
                                        } else if (posicion.getY() - 1 < mapa1.getColumnas() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() - 1).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() - 1).isEdificios()) {
                                            posicion1.setX(posicion.getX());
                                            posicion1.setY(posicion.getY() - 1);
                                        }

                                        String nombre = mapa1.darNombre(comando[2]);
                                        Personaje p = (Paisano) ciud.crear(comando[2]);
                                        p.setPosicion(posicion1);
                                        p.setCodigo(nombre);
                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().put(p.getCodigo(), p);
                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).anadirPersonaje(p, mapa1.getMapa());
                                        try{
                                        ciud.setAlimento(ciud.getAlimento() - (int) ciud.getCosteCreacionPersonaje());
                                        
                                        System.out.println("Coste de creación: " + ciud.getCosteCreacionPersonaje() + " unidades de comida");
                                        Celda celda = new Celda(posicion1, p);
                                        int x = celda.getPosicion().getX();
                                        int y = celda.getPosicion().getY();

                                        if (x + 1 >= 0 && x + 1 < mapa1.getFilas()) {
                                            mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x + 1).get(y));
                                        }
                                        if (x - 1 >= 0 && x - 1 < mapa1.getFilas()) {
                                            mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x - 1).get(y));
                                        }
                                        if (y + 1 >= 0 && y + 1 < mapa1.getColumnas()) {
                                            mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x).get(y + 1));
                                        }
                                        if (y - 1 >= 0 && y - 1 < mapa1.getColumnas()) {
                                            mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x).get(y - 1));
                                        }

                                        //System.out.println("Te quedan " + ciud.getAlimento() + " unidades de capacidad de alojamiento");
                                        if (mapa1.TorreAdyacente(p)) {
                                            mapa1.AtacarTorre(p);
                                        }
                                        mapa1.vermapacivilizacion();
                                        }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                    }
                                } else {
                                    throw (new CrearExtepcion());
                                }
                            } else if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getEdificios().get(posicion) instanceof Cuartel) {
                                Collection<Edificio> colectciu = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getEdificios().values();
                                Cuartel cuar = (Cuartel) mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getEdificios().get(posicion);
                                for (Edificio ciu : colectciu) {
                                    if (ciu instanceof Ciudadela) {
                                        Ciudadela ciu2 = (Ciudadela) ciu;
                                        alimentos = alimentos + (int) ciu2.getAlimento();
                                        ciudadela++;
                                    }
                                }
                                if (alimentos < cuar.getCosteCreacionPersonaje()) {
                                    consola.imprimir("No hay suficientes alimentos en las ciudadelas");
                                    throw (new CrearExtepcion());
                                } else {
                                    alimentos = alimentos - (int) cuar.getCosteCreacionPersonaje();
                                    alimentos = alimentos / ciudadela;
                                    for (Edificio ciud : colectciu) {
                                        if (ciud instanceof Ciudadela) {
                                            Ciudadela ciud2 = (Ciudadela) ciud;
                                            try{
                                            ciud2.setAlimento(alimentos);
                                            }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                        }
                                    }
                                    Posicion posicion1 = new Posicion();
                                    if (posicion.getX() + 1 < mapa1.getFilas() && !mapa1.getMapa().get(posicion.getX() + 1).get(posicion.getY()).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX() + 1).get(posicion.getY()).isEdificios()) {
                                        posicion1.setX(posicion.getX() + 1);
                                        posicion1.setY(posicion.getY());
                                    } else if (posicion.getY() + 1 < mapa1.getColumnas() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() + 1).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() + 1).isEdificios()) {
                                        posicion1.setX(posicion.getX());
                                        posicion1.setY(posicion.getY() + 1);
                                    } else if (posicion.getX() - 1 < mapa1.getFilas() && !mapa1.getMapa().get(posicion.getX() - 1).get(posicion.getY()).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX() - 1).get(posicion.getY()).isEdificios()) {
                                        posicion1.setX(posicion.getX() - 1);
                                        posicion1.setY(posicion.getY());
                                    } else if (posicion.getY() - 1 < mapa1.getColumnas() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() - 1).isContenedorDeRecursos() && !mapa1.getMapa().get(posicion.getX()).get(posicion.getY() - 1).isEdificios()) {
                                        posicion1.setX(posicion.getX());
                                        posicion1.setY(posicion.getY() - 1);
                                    }
                                    
                                    String nombre = mapa1.darNombre(comando[2]);
                                    Personaje p = cuar.crear(comando[2]);
                                    p.setCodigo(nombre);
                                    if (comando[2].equals("Arquero")) {
                                        p = (Arquero) cuar.crear(comando[2]);
                                        p.setPosicion(posicion1);
                                        p.setCodigo(nombre);
                                    } else if (comando[2].equals("Caballero")) {
                                        p = (Caballero) cuar.crear(comando[2]);
                                        p.setPosicion(posicion1);
                                        p.setCodigo(nombre);
                                    } else if (comando[2].equals("Legionario")) {
                                        p = (Legionario) cuar.crear(comando[2]);
                                        p.setPosicion(posicion1);
                                        p.setCodigo(nombre);
                                    }

                                    mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().put(p.getCodigo(), p);
                                    mapa1.getMapa().get(posicion1.getX()).get(posicion1.getY()).getPersonajes().add(p);

                                    //mapa1.getEdificios().get(posicion).setAlimento(ciud2.getAlimento() - mapa1.getEdificios().get(posicion).getCosteCreacionPersonaje());
                                    System.out.println("Coste de creación: " + cuar.getCosteCreacionPersonaje() + " unidades de comida");
                                    Celda celda = new Celda(posicion1, p);
                                    int x = celda.getPosicion().getX();
                                    int y = celda.getPosicion().getY();

                                    if (x + 1 >= 0 && x + 1 < mapa1.getFilas()) {
                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x + 1).get(y));
                                    }
                                    if (x - 1 >= 0 && x - 1 < mapa1.getFilas()) {
                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x - 1).get(y));
                                    }
                                    if (y + 1 >= 0 && y + 1 < mapa1.getColumnas()) {
                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x).get(y + 1));
                                    }
                                    if (y - 1 >= 0 && y - 1 < mapa1.getColumnas()) {
                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x).get(y - 1));
                                    }

                                    //System.out.println("Te quedan " + Edificios.get(posicion).getAlimento() + " unidades de capacidad de alojamiento");
                                    if (mapa1.TorreAdyacente(p)) {
                                        mapa1.AtacarTorre(p);
                                    }
                                    mapa1.vermapacivilizacion();
                                }
                            } else {
                                //System.out.println("No se pudo crear. Coste de la creación: " + Edificios.get(posicion).getCosteCreacionPersonaje() + " unidades de comida");
                                throw (new CrearExtepcion());
                            }
                        } else {
                            consola.imprimir("Este edificio no es de esta civilización");
                            throw(new PGExtepcion());
                        }
                    } else {
                        consola.imprimir("Posicion incorrecta\n");
                        throw (new LimiteExtepcion());
                    }
                }

            }
    }
}
