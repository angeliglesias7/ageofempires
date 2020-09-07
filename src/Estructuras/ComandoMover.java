/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import Elementos.Caballero;

/**
 *
 * @author raul
 */
public class ComandoMover implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoMover(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion,PGExtepcion,MoverExtepcion,LimiteExtepcion{
            ConsolaNormal consola=new ConsolaNormal();
        if (comando.length != 3) {
            consola.imprimir("Error de sintaxis: mover <personaje>/<grupo> <pto cardinal>\n");
            throw(new ComandoExtepcion());
        } else {
            Posicion pos1 = new Posicion();
            Posicion pos2 = new Posicion();
            
            if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().containsKey(comando[1])) {
                pos1 = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]).getPosicion();
                pos2 = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]).mover(comando[2]);
            } else if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().containsKey(comando[1])) {
                pos1 = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().get(comando[1]).getPosicion();
                pos2 = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().get(comando[1]).mover(comando[2]);
            } else {
                consola.imprimir("No existe el personaje o el grupo es esta civilizacion");
                throw(new PGExtepcion());
                //return;
                //Producirse una extepción.
            }
            if (pos2.getX() >= 0 && pos2.getX() < mapa1.getFilas() && pos2.getY() >= 0 && pos2.getY() < mapa1.getColumnas()) {
            if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().containsKey(comando[1])) {
                if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getEdificios().containsKey(pos2)) {
                    //No podemos mover a un edifcio
                    throw(new MoverExtepcion());
                } else if (mapa1.getContenedorDeRecursos().containsKey(pos2)) {
                    //No podemos mover a un contenedor de recursos
                    throw(new MoverExtepcion());
                } else if (mapa1.getMapa().get(pos2.getX()).get(pos2.getY()).isGrupo()) {
                    //Non podemos mover a onde temos un grupo
                    throw(new MoverExtepcion());
                } else {
                    Celda celda = mapa1.getMapa().get(pos1.getX()).get(pos1.getY());
                    celda.getPersonajes().remove(mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]));
                    mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]).setPosicion(pos2);
                    mapa1.getMapa().get(pos2.getX()).get(pos2.getY()).getPersonajes().add(mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]));
                    int x = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]).getPosicion().getX();
                    int y = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]).getPosicion().getY();
                    mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x).get(y));
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
                    consola.imprimir("Se ha movido a la posicion " + pos2);
                    //System.out.println("El " + Personajes.get(a).getNombre() + " se ha movido a la posicion " + Personajes.get(a).getPosicion());
                    if (mapa1.TorreAdyacente(mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]))) {
                        mapa1.AtacarTorre(mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().get(comando[1]));
                    }
                }
                
            } else if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().containsKey(comando[1])) {
                if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getEdificios().containsKey(pos2)) {
                    //No podemos mover a un edifcio
                    throw(new MoverExtepcion());
                } else if (mapa1.getContenedorDeRecursos().containsKey(pos2)) {
                    throw(new MoverExtepcion());
                    //No podemos mover a un contenedor de recursos
                } else if (mapa1.getMapa().get(pos2.getX()).get(pos2.getY()).isGrupo()) {
                    //Non podemos mover a onde temos un grupo
                    throw(new MoverExtepcion());
                } else {
                    mapa1.getMapa().get(pos1.getX()).get(pos1.getY()).setGrupo(false);
                    mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().get(comando[1]).setPosicion(pos2);
                    mapa1.getMapa().get(pos2.getX()).get(pos2.getY()).setGrupo(true);
                    int x = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().get(comando[1]).getPosicion().getX();
                    int y = mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().get(comando[1]).getPosicion().getY();
                    if (x + 1 >= 0 && x + 1 < mapa1.getFilas()) {
                        //mapa.get(x+1).get(y).setVisible(true);
                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x + 1).get(y));
                    }
                    if (x - 1 >= 0 && x - 1 < mapa1.getFilas()) {
                        //mapa.get(x-1).get(y).setVisible(true);
                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x - 1).get(y));
                    }
                    if (y + 1 >= 0 && y + 1 < mapa1.getColumnas()) {
                        //mapa.get(x).get(y+1).setVisible(true);
                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x).get(y + 1));
                    }
                    if (y - 1 >= 0 && y - 1 < mapa1.getColumnas()) {
                        //mapa.get(x).get(y-1).setVisible(true);
                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getCeldasvisibles().add(mapa1.getMapa().get(x).get(y - 1));
                    }
                    //System.out.println("El " + Personajes.get(a).getNombre() + " se ha movido a la posicion " + Personajes.get(a).getPosicion());
                    if (mapa1.TorreAdyacente(mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().get(comando[1]))) {
                        mapa1.AtacarTorre(mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().get(comando[1]));
                    }
                }
            }else{
                throw(new PGExtepcion());
            }
            mapa1.vermapacivilizacion();
        }else{
                throw(new LimiteExtepcion());
            }
        }
    }
}
