/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Elementos.Edificio;
import Elementos.Paisano;
import java.util.Collection;

/**
 *
 * @author raul
 */
public class ComandoConstruir implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoConstruir(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion,ConstruirExtepcion,LimiteExtepcion,PGExtepcion {
            ConsolaNormal consola = new ConsolaNormal();
            if (comando.length != 4) {
                consola.imprimir("Error de sintaxis: construir <personaje> <edificio> <pto cardinal>\n");
                throw (new ComandoExtepcion());
            } else {
                Posicion pos1 = new Posicion();
                Posicion pos2 = new Posicion();
                if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().containsKey(comando[1])) {
                    if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().containsKey(comando[1])) {
                        pos1 = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]).getPosicion();
                        if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]) instanceof Paisano) {

                            if (comando[3].equals("SUR") || comando[3].equals("sur")) {
                                pos2.setX(pos1.getX() + 1);
                                pos2.setY(pos1.getY());
                            } else if (comando[3].equals("NORTE") || comando[3].equals("norte")) {
                                pos2.setX(pos1.getX() - 1);
                                pos2.setY(pos1.getY());
                            } else if (comando[3].equals("ESTE") || comando[3].equals("este")) {
                                pos2.setX(pos1.getX());
                                pos2.setY(pos1.getY() + 1);
                            } else if (comando[3].equals("OESTE") || comando[3].equals("oeste")) {
                                pos2.setX(pos1.getX());
                                pos2.setY(pos1.getY() - 1);
                            } else {
                                throw (new PuntoCardinalExtepcion());
                            }

                            if (pos2.getX() >= 0 && pos2.getY() >= 0 && pos2.getX() < mapa1.getFilas() && pos2.getY() < mapa1.getColumnas()) {

                                Celda celda = mapa1.getMapa().get(pos2.getX()).get(pos2.getY());

                                if (celda.isEdificios()) {
                                    consola.imprimir("No podemos construir un edificio en esta posición porque esta ocupada por otro edificio");
                                    throw (new ConstruirExtepcion());
                                } else if (celda.isContenedorDeRecursos()) {
                                    consola.imprimir("No podemos construir un edificio en esta posición tenemos un contenedor de recursos");
                                    throw (new ConstruirExtepcion());
                                } else if (!celda.getPersonajes().isEmpty()) {
                                    consola.imprimir("No podemos construir un edificio en esta posición porque esta ocupada por un personaje");
                                    throw (new ConstruirExtepcion());
                                } else {
                                    Edificio edif;
                                    String nombre = mapa1.darNombre(comando[2]);
                                    edif = mapa1.getPersonajes().get(comando[1]).construir(comando[2]);
                                    edif.setPosicion(pos2);
                                    edif.setNombre(nombre);
                                    int madera = 0, piedra = 0;
                                    Collection<Edificio> colectciu = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getEdificios().values();
                                    for (Edificio ciu : colectciu) {
                                        madera += ciu.getMadera();
                                        piedra += ciu.getPiedra();
                                    }
                                    if (madera >= edif.getCosteReparacionMadera() && piedra >= edif.getCosteReparacionPiedra()) {
                                        
                                        int x = celda.getPosicion().getX();
                                        int y = celda.getPosicion().getY();
                                        if (x + 1 >= 0 && x + 1 <= mapa1.getFilas()) {
                                            mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x + 1).get(y));
                                        }
                                        if (x - 1 >= 0 && x - 1 <= mapa1.getFilas()) {
                                            mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x - 1).get(y));
                                        }
                                        if (y + 1 >= 0 && y + 1 <= mapa1.getColumnas()) {
                                            mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x).get(y + 1));
                                        }
                                        if (y - 1 >= 0 && y - 1 <= mapa1.getColumnas()) {
                                            mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x).get(y - 1));
                                        }

                                        celda.setEdificios(true);
                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getEdificios().put(edif.getPosicion(), edif);                                        
                                        mapa1.getMapa().get(pos2.getX()).set(pos2.getY(), celda);
                                        if (mapa1.TorreAdyacente(mapa1.getPersonajes().get(comando[1]))) {
                                            mapa1.AtacarTorre(mapa1.getPersonajes().get(comando[1]));
                                        }
                                    } else {
                                        consola.imprimir("No se pudo construir. Coste de la construcción: " + edif.getCosteReparacionMadera() + " unidades de madera y " + edif.getCosteReparacionPiedra() + " unidades de piedra");
                                        throw (new ConstruirExtepcion());
                                    }
                                }
                                mapa1.vermapacivilizacion();
                            } else {
                                consola.imprimir("La posicion no esta en el mapa");
                                throw (new LimiteExtepcion());
                            }
                        } else {
                            consola.imprimir("Solo un Paisano puede construir");
                            throw(new PGExtepcion());
                        }
                    } else {
                        consola.imprimir("Este personaje no es de esta civilacion");
                        throw(new PGExtepcion());
                    }
                } else {
                    consola.imprimir("Este personaje no existe");
                    throw(new PGExtepcion());
                }

            }
       
    }
}
