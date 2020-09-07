/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Elementos.Edificio;
import Elementos.Personaje;
import java.util.Collection;

/**
 *
 * @author raul
 */
public class ComandoDefender implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoDefender(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion,DefenderExtepcion,PGExtepcion,PuntoCardinalExtepcion,LimiteExtepcion{
            if (comando.length != 3) {
                System.out.println("Error de sintaxis: defender <personaje> <pto cardinal>\n");
                throw (new ComandoExtepcion());
            } else {
                Posicion pos1 = new Posicion();
                Posicion pos2 = new Posicion();
                if (mapa1.getPersonajes().containsKey(comando[1])) {
                    if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().containsKey(comando[1])) {
                        pos1 = mapa1.getPersonajes().get(comando[1]).getPosicion();

                        if (comando[2].equals("SUR") || comando[2].equals("sur")) {
                            pos2.setX(pos1.getX() + 1);
                            pos2.setY(pos1.getY());
                        } else if (comando[2].equals("NORTE") || comando[2].equals("norte")) {
                            pos2.setX(pos1.getX() - 1);
                            pos2.setY(pos1.getY());
                        } else if (comando[2].equals("ESTE") || comando[2].equals("este")) {
                            pos2.setX(pos1.getX());
                            pos2.setY(pos1.getY() + 1);
                        } else if (comando[2].equals("OESTE") || comando[2].equals("oeste")) {
                            pos2.setX(pos1.getX());
                            pos2.setY(pos1.getY() - 1);
                        }else{
                            throw(new PuntoCardinalExtepcion());
                        }

                        if (pos2.getX() >= 0 && pos2.getX() <= mapa1.getColumnas() && pos2.getY() <= mapa1.getFilas() && pos2.getY() >= 0) {
                            Celda celda = mapa1.getMapa().get(pos2.getX()).get(pos2.getY());
                            if (celda.isContenedorDeRecursos()) {
                                System.out.println("No podemos defender un contenedor de recursos");
                                throw (new DefenderExtepcion());
                            } else if (celda.isGrupo()) {
                                System.out.println("No podemos defender un grupo");
                                throw (new DefenderExtepcion());
                            } else if (!celda.getPersonajes().isEmpty()) {
                                System.out.println("No podemos defender un personaje");
                                throw (new DefenderExtepcion());
                            } else if (celda.isEdificios()) {
                                Collection<Edificio> coledifd = mapa1.getEdificios().values();
                                for (Edificio edifd : coledifd) {
                                    if (edifd.getPosicion().equals(pos2)) {
                                        if (edifd.getCapacidad() <= 1) {
                                            System.out.println("No tenemos capacidad en el edificio");
                                            throw (new DefenderExtepcion());
                                        } else {
                                            System.out.println("El personaje defiende el edificio");
                                            edifd.getPersonajes().add(mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]));
                                            mapa1.getPersonajes().get(comando[1]).setPosicion(pos2);
                                            mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]).defender(edifd);
                                            mapa1.vermapacivilizacion();
                                        }
                                    }
                                }

                            } else {
                                System.out.println("En esta celda no hai un edificio para defender, esta vacia");
                                throw (new DefenderExtepcion());
                            }
                        }else{
                            throw(new LimiteExtepcion());
                        }

                    } else if (mapa1.getGrupos().containsKey(comando[1])) {
                        if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().containsKey(comando[1])) {
                            pos1 = mapa1.getGrupos().get(comando[1]).getPosicion();
                            if (comando[2].equals("SUR") || comando[2].equals("sur")) {
                                pos2.setX(pos1.getX() + 1);
                                pos2.setY(pos1.getY());
                            } else if (comando[2].equals("NORTE") || comando[2].equals("norte")) {
                                pos2.setX(pos1.getX() - 1);
                                pos2.setY(pos1.getY());
                            } else if (comando[2].equals("ESTE") || comando[2].equals("este")) {
                                pos2.setX(pos1.getX());
                                pos2.setY(pos1.getY() + 1);
                            } else if (comando[2].equals("OESTE") || comando[2].equals("oeste")) {
                                pos2.setX(pos1.getX());
                                pos2.setY(pos1.getY() - 1);
                            }else{
                                throw(new PuntoCardinalExtepcion());
                            }
                            if (pos2.getX() >= 0 && pos2.getX() < mapa1.getFilas() && pos2.getY() < mapa1.getColumnas() && pos2.getY() >= 0) {
                                Celda celda = mapa1.getMapa().get(pos2.getX()).get(pos2.getY());
                                if (celda.isContenedorDeRecursos()) {
                                    System.out.println("No podemos defender un contenedor de recursos");
                                    throw (new DefenderExtepcion());
                                } else if (celda.isGrupo()) {
                                    System.out.println("No podemos defender un grupo");
                                    throw (new DefenderExtepcion());
                                } else if (!celda.getPersonajes().isEmpty()) {
                                    System.out.println("No podemos defender un personaje");
                                    throw (new DefenderExtepcion());
                                } else if (celda.isEdificios()) {
                                    Collection<Edificio> coledifd = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getEdificios().values();
                                    for (Edificio edifd : coledifd) {
                                        if (edifd.getPosicion().equals(pos2)) {
                                            if (edifd.getCapacidad() <= mapa1.getGrupos().get(comando[1]).getPersonajes().size()) {
                                                System.out.println("No tenemos capacidad en el edificio");
                                                throw (new DefenderExtepcion());
                                            } else {
                                                System.out.println("El grupo defiende el edificio");
                                                edifd.getGrupos().add(mapa1.getGrupos().get(comando[1]));
                                                edifd.setCapacidad(edifd.getCapacidad() - mapa1.getGrupos().get(comando[1]).getPersonajes().size());
                                                mapa1.getMapa().get(mapa1.getGrupos().get(comando[1]).getPosicion().getX()).get(mapa1.getGrupos().get(comando[1]).getPosicion().getY()).setGrupo(false);
                                                mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().get(comando[1]).setPosicion(pos2);
                                                Collection<Personaje> colperg = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().get(comando[1]).getPersonajes().values();
                                                for (Personaje perg : colperg) {
                                                    try{
                                                    perg.setSalud(perg.vidamax);
                                                    }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                                    edifd.setArmaduraEdificio(edifd.getArmaduraEdificio() + perg.getArmadura());
                                                    edifd.setAtaqueEdificio(edifd.getAtaqueEdificio() + perg.getAtaque());
                                                }
                                                mapa1.vermapacivilizacion();
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("En esta celda no hai un edificio para defender, esta vacia");
                                    throw (new DefenderExtepcion());
                                }
                            } else {
                                throw (new LimiteExtepcion());
                            }
                        }
                    } else {
                        System.out.println("Esta civilización no tiene este grupo");
                        throw(new PGExtepcion());
                    }
                } else {
                    throw(new PGExtepcion());
                }

            }
    }
}
