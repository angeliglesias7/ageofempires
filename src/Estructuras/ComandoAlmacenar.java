/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Elementos.Ciudadela;
import Elementos.Paisano;

/**
 *
 * @author raul
 */
public class ComandoAlmacenar implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoAlmacenar(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion,AlmacenarExtepcion,PGExtepcion,PuntoCardinalExtepcion,LimiteExtepcion{
            if (comando.length != 3) {
                System.out.println("Error de sintaxis: almacenar <personaje> <pto cardinal>\n");
                throw (new ComandoExtepcion());
            } else {
                Posicion pos1 = new Posicion();
                Posicion pos2 = new Posicion();

                if (mapa1.getPersonajes().containsKey(comando[1])) {
                    if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().containsKey(comando[1])) {
                        pos1 = mapa1.getPersonajes().get(comando[1]).getPosicion();
                        if (mapa1.getPersonajes().get(comando[1]) instanceof Paisano) {
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

                            if (pos2.getX() >= 0 && pos2.getY() >= 0) {
                                if (mapa1.getEdificios().containsKey(pos2)) {
                                    if (mapa1.getEdificios().get(pos2) instanceof Ciudadela) {
                                        mapa1.getPersonajes().get(comando[1]).almacenar((Ciudadela) mapa1.getEdificios().get(pos2));
                                    } else {
                                        throw (new AlmacenarExtepcion());
                                    }
                                } else {
                                    throw (new ComandoExtepcion());
                                }
                                if (mapa1.TorreAdyacente(mapa1.getPersonajes().get(comando[1]))) {
                                    mapa1.AtacarTorre(mapa1.getPersonajes().get(comando[1]));
                                }
                            } else {
                                throw (new LimiteExtepcion());
                            }
                        }
                    }else{
                        throw(new PGExtepcion());
                    }
                }else{
                    throw(new PGExtepcion());
                }
            }
        
    }
}
