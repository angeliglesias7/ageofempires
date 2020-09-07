/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Elementos.Paisano;
import Elementos.Pradera;

/**
 *
 * @author raul
 */
public class ComandoRecolectar implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoRecolectar(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion,PGExtepcion,PuntoCardinalExtepcion{
            ConsolaNormal consola = new ConsolaNormal();
            if (comando.length != 3) {
                consola.imprimir("Error de sintaxis: recolectar <personaje> <pto cardinal>\n");
                throw (new ComandoExtepcion());
            } else {
                Posicion pos1 = new Posicion();
                Posicion pos2 = new Posicion();

                if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().containsKey(comando[1])) {
                    pos1 = mapa1.getPersonajes().get(comando[1]).getPosicion();
                } else if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().containsKey(comando[1])) {
                    pos1 = mapa1.getGrupos().get(comando[1]).getPosicion();
                } else {
                    consola.imprimir("No existe el personaje o el grupo en esta civilizacion");
                    //return;
                    throw (new PGExtepcion());
                }

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

                    if (pos2.getX() >= 0 && pos2.getY() >= 0 && pos2.getX() < mapa1.getFilas() && pos2.getY() < mapa1.getColumnas()) {
                        Celda celda = mapa1.getMapa().get(pos2.getX()).get(pos2.getY());

                        if (!celda.isContenedorDeRecursos()) {
                            //No podemos recolectar
                            throw (new ComandoExtepcion());
                        } else {
                            try{
                            mapa1.getPersonajes().get(comando[1]).recolectar(mapa1.getContenedorDeRecursos().get(pos2));
                            if (mapa1.getContenedorDeRecursos().get(pos2).getCantidad() == 0) {
                                Pradera pradera = new Pradera();
                                Celda celda1 = new Celda(pos2, pradera);
                                mapa1.getMapa().get(pos2.getX()).set(pos2.getY(), celda1);
                                mapa1.getMapa().get(pos2.getX()).get(pos2.getY()).setVisible(true);
                            }
                        }catch(RecolectarExtepcion re){
                                re.ErrorRecolectar();
                                }
                        }
                    } else {
                        throw (new ComandoExtepcion());
                    }
                } else {
                    throw(new PGExtepcion());
                }

                mapa1.vermapacivilizacion();
            }
    }
}
