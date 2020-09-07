package Estructuras;

import Elementos.Edificio;
import Elementos.Personaje;
import Elementos.Soldado;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import Elementos.Grupo;

/**
 *
 * @author raul
 */
public class ComandoAtacar implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoAtacar(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion,AtacarException,PuntoCardinalExtepcion,PGExtepcion,LimiteExtepcion{
            if (comando.length != 3) {
                System.out.println("Error de sintaxis: atacar <personaje> <pto cardinal>\n");
                throw (new ComandoExtepcion());
            } else {
                Posicion pos1 = new Posicion();
                Posicion pos2 = new Posicion();
                if (mapa1.getPersonajes().containsKey(comando[1])) {
                    if (mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().containsKey(comando[1])) {
                        if (mapa1.getPersonajes().get(comando[1]) instanceof Soldado) {
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
                            } else {
                                throw (new PuntoCardinalExtepcion());
                            }

                            if (pos2.getX() >= 0 && pos2.getX() <= mapa1.getColumnas() && pos2.getY() <= mapa1.getFilas() && pos2.getY() >= 0) {
                                Celda celda = mapa1.getMapa().get(pos2.getX()).get(pos2.getY());
                                if (celda.isContenedorDeRecursos()) {
                                    System.out.println("No podemos atacar un colector de recursos");
                                    throw(new AtacarException());
                                } else if (celda.isEdificios()) {
                                    Collection<Edificio> coledif = mapa1.getEdificios().values();
                                    for (Edificio edif : coledif) {
                                        if (edif.getPosicion().equals(pos2)) {
                                            if (!edif.getPersonajes().isEmpty() || !edif.getGrupos().isEmpty()) {
                                                if (0 >= edif.getPuntoSalud() - (mapa1.getPersonajes().get(comando[1]).getAtaque() - edif.getArmaduraEdificio())) {
                                                    System.out.println("El edificio " + edif.getNombre() + " ha sido destruido");
                                                    for (int i = 0; i < edif.getPersonajes().size(); i++) {
                                                        mapa1.getPersonajes().remove(edif.getPersonajes().get(i).getCodigo());
                                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().remove(edif.getPersonajes().get(i).getCodigo());
                                                        mapa1.getMapa().get(edif.getPersonajes().get(i).getPosicion().getX()).get(edif.getPersonajes().get(i).getPosicion().getY()).getPersonajes().remove(edif.getPersonajes().get(i));
                                                    }
                                                    for (int i = 0; i < edif.getGrupos().size(); i++) {
                                                        mapa1.getGrupos().remove(edif.getGrupos().get(i).getCodigo());
                                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().remove(edif.getGrupos().get(i).getCodigo());
                                                        mapa1.getMapa().get(edif.getGrupos().get(i).getPosicion().getX()).get(edif.getGrupos().get(i).getPosicion().getY()).setGrupo(false);
                                                    }
                                                    mapa1.getEdificios().remove(edif.getPosicion());
                                                    celda.setEdificios(false);
                                                }
                                                if (0 < mapa1.getPersonajes().get(comando[1]).getAtaque() - edif.getArmaduraEdificio()) {
                                                    edif.setPuntoSalud(edif.getPuntoSalud() - (mapa1.getPersonajes().get(comando[1]).getAtaque() - edif.getArmaduraEdificio()));
                                                    try{
                                                    mapa1.getPersonajes().get(comando[1]).setSalud(mapa1.getPersonajes().get(comando[1]).getSalud() - (edif.getAtaqueEdificio() - mapa1.getPersonajes().get(comando[1]).getArmadura()));
                                                    }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                                } else {
                                                    edif.setPuntoSalud(edif.getPuntoSalud() - (1));
                                                    try{
                                                    mapa1.getPersonajes().get(comando[1]).setSalud(mapa1.getPersonajes().get(comando[1]).getSalud() - (edif.getAtaqueEdificio() - mapa1.getPersonajes().get(comando[1]).getArmadura()));
                                                
                                                    }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                            }

                                            } else if (0 >= edif.getPuntoSalud() - (mapa1.getPersonajes().get(comando[1]).getAtaque())) {
                                                System.out.println(edif.getNombre() + " ha sido destruido");
                                                celda.setEdificios(false);
                                                mapa1.getEdificios().remove(edif.getPosicion());
                                                mapa1.vermapacivilizacion();
                                                return;
                                            } else if (0 < edif.getArmaduraEdificio() - mapa1.getPersonajes().get(comando[1]).getAtaque()) {
                                                edif.setPuntoSalud(edif.getPuntoSalud() - (edif.getArmaduraEdificio() - mapa1.getPersonajes().get(comando[1]).getAtaque()));
                                                System.out.println("La salud del edifico es: " + edif.getPuntoSalud());
                                                mapa1.getPersonajes().get(comando[1]).atacar(edif);
                                            }
                                        }
                                    }
                                } else if (!celda.getPersonajes().isEmpty()) {
                                    System.out.println("Empieza la batalla");
                                    List<Personaje> lista = new ArrayList<>(mapa1.getPersonajes().values());
                                    for (int i = lista.size() - 1; i >= 0; i--) {
                                        Personaje p = lista.get(i);
                                        if (p.getPosicion().equals(pos2)) {
                                            if (0 >= p.getSalud() - (mapa1.getPersonajes().get(comando[1]).getAtaque() - p.getArmadura())) {
                                                System.out.println("El personaje " + p.getCodigo() + " ha muerto");
                                                celda.getPersonajes().remove(p);
                                                mapa1.getPersonajes().remove(p.getCodigo());
                                                mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().remove(p.getCodigo());
                                                mapa1.vermapacivilizacion();
                                            } else if (0 < mapa1.getPersonajes().get(comando[1]).getAtaque() - p.getArmadura()) {
                                                //System.out.println("Has inflingido un daño de " + (Personajes.get(a).getAtaque() - p.getArmadura()));
                                                try{
                                                p.setSalud(p.getSalud() - (mapa1.getPersonajes().get(comando[1]).getAtaque() - p.getArmadura()));
                                                }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                            } else {
                                                System.out.println("Has inflingido un daño de " + 1);
                                                try{
                                                p.setSalud(p.getSalud() - (1));
                                                }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                            }
                                        }
                                    }
                                } else if (celda.isGrupo()) {
                                    List<Grupo> colg = new ArrayList<>(mapa1.getGrupos().values());
                                    for (int j = colg.size() - 1; j >= 0; j--) {
                                        Grupo grup = colg.get(j);
                                        if (grup.getPosicion().equals(pos2)) {
                                            double ataque = mapa1.getPersonajes().get(comando[1]).getAtaque() - grup.getCapacidadAtaqueDefensa();
                                            ataque = ataque / grup.getPersonajes().size();
                                            List<Personaje> lista = new ArrayList<>(grup.getPersonajes().values());
                                            for (int i = lista.size() - 1; i >= 0; i--) {
                                                Personaje p = lista.get(i);
                                                if (0 >= p.getSalud() - ataque) {
                                                    System.out.println("El personaje " + p.getCodigo() + " ha muerto");
                                                    grup.setCapacidadAtaqueDefensa(grup.getCapacidadAtaqueDefensa() - p.getAtaque());
                                                    grup.getPersonajes().remove(p.getCodigo());
                                                    mapa1.getPersonajes().remove(p.getCodigo());
                                                    mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getPersonajes().remove(p.getCodigo());
                                                    if (grup.getPersonajes().isEmpty()) {
                                                        System.out.println("Has matado al grupo " + grup.getCodigo());
                                                        mapa1.getGrupos().remove(grup.getCodigo());
                                                        mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().remove(grup.getCodigo());
                                                        mapa1.getMapa().get(p.getPosicion().getX()).get(p.getPosicion().getY()).setGrupo(false);
                                                        mapa1.vermapacivilizacion();
                                                        return;
                                                    }
                                                } else if (0 < ataque) {
                                                    try{
                                                    p.setSalud(p.getSalud() - ataque);
                                                    System.out.println("La salud de " + p.getCodigo() + " es " + p.getSalud());
                                                    }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                                } else {
                                                    try{
                                                    p.setSalud(p.getSalud() - 1);
                                                    }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                                }
                                            }
                                        }
                                        Personaje[] personas = new Personaje[celda.getPersonajes().size()];
                                        for (int i = 0; i < celda.getPersonajes().size(); i++) {
                                            personas[i] = celda.getPersonajes().get(i);
                                        }
                                        mapa1.getPersonajes().get(comando[1]).atacar(personas);
                                    }
                                } else {
                                    System.out.println("Non podemos atacar a unha pradera");
                                    throw(new AtacarException());
                                }
                                if (mapa1.TorreAdyacente(mapa1.getPersonajes().get(comando[1]))) {
                                    mapa1.AtacarTorre(mapa1.getPersonajes().get(comando[1]));
                                }
                            } else {
                                System.out.println("Non podemos atacar fora do mapa");
                                throw (new LimiteExtepcion());
                            }
                        } else {
                            System.out.println("Un paisano non pode atacar");
                            throw(new PGExtepcion());
                        }
                    } else {
                        System.out.println("El personaje no esta en la civilizacion");
                        throw(new PGExtepcion());
                    }

                } else if (mapa1.getGrupos().containsKey(comando[1])) {
                    if (!mapa1.getCivilizaciones().get(mapa1.getCivilizacion()).getGrupos().containsKey(comando[1])) {
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
                        } else {
                            throw (new PuntoCardinalExtepcion());
                        }
                        if (pos2.getX() >= 0 && pos2.getX() <= mapa1.getColumnas() && pos2.getY() <= mapa1.getFilas() && pos2.getY() >= 0) {
                            Celda celda = mapa1.getMapa().get(pos2.getX()).get(pos2.getY());
                            if (celda.isContenedorDeRecursos()) {
                                System.out.println("No podemos atacar un colector de recursos");
                                throw(new AtacarException());
                            } else if (celda.isEdificios()) {
                                Collection<Edificio> coledif = mapa1.getEdificios().values();
                                for (Edificio edif : coledif) {
                                    if (edif.getPosicion().equals(pos2)) {
                                        if (!edif.getPersonajes().isEmpty() || !edif.getGrupos().isEmpty()) {
                                            if (0 >= mapa1.getGrupos().get(comando[1]).getCapacidadAtaqueDefensa() - edif.getArmaduraEdificio()) {
                                                System.out.println("El edificio" + edif.getNombre() + "ha sido derruido");
                                                for (int i = 0; i < edif.getPersonajes().size(); i++) {
                                                    mapa1.getPersonajes().remove(edif.getPersonajes().get(i).getCodigo());
                                                    mapa1.getMapa().get(edif.getPersonajes().get(i).getPosicion().getX()).get(edif.getPersonajes().get(i).getPosicion().getY()).getPersonajes().remove(edif.getPersonajes().get(i));
                                                }
                                                for (int i = 0; i < edif.getGrupos().size(); i++) {
                                                    mapa1.getGrupos().remove(edif.getGrupos().get(i).getCodigo());
                                                    mapa1.getMapa().get(edif.getGrupos().get(i).getPosicion().getX()).get(edif.getGrupos().get(i).getPosicion().getY()).setGrupo(false);
                                                }
                                                mapa1.getEdificios().remove(edif.getPosicion());
                                                celda.setEdificios(false);
                                            } else if (0 < mapa1.getGrupos().get(comando[1]).getCapacidadAtaqueDefensa() - edif.getArmaduraEdificio()) {
                                                edif.setPuntoSalud(edif.getPuntoSalud() - (mapa1.getGrupos().get(comando[1]).getCapacidadAtaqueDefensa() - edif.getArmaduraEdificio()));
                                                try{
                                                mapa1.getPersonajes().get(comando[1]).setSalud(mapa1.getPersonajes().get(comando[1]).getSalud() - (edif.getAtaqueEdificio() - mapa1.getPersonajes().get(comando[1]).getArmadura()));
                                            }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                            } else {
                                                edif.setPuntoSalud(edif.getPuntoSalud() - (1));
                                                try{
                                                mapa1.getPersonajes().get(comando[1]).setSalud(mapa1.getPersonajes().get(comando[1]).getSalud() - (edif.getAtaqueEdificio() - mapa1.getPersonajes().get(comando[1]).getArmadura()));
                                            }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                                }
                                        } else if (0 >= edif.getPuntoSalud() - (mapa1.getGrupos().get(comando[1]).getCapacidadAtaqueDefensa())) {
                                            System.out.println(edif.getNombre() + " ha sido destruido");
                                            celda.setEdificios(false);
                                            mapa1.getEdificios().remove(edif.getPosicion());
                                            mapa1.vermapacivilizacion();
                                            return;
                                        } else if (0 < edif.getArmaduraEdificio() - mapa1.getGrupos().get(comando[1]).getCapacidadAtaqueDefensa()) {
                                            edif.setPuntoSalud(edif.getPuntoSalud() - (edif.getArmaduraEdificio() - mapa1.getGrupos().get(comando[1]).getCapacidadAtaqueDefensa()));
                                            System.out.println("La salud del edifico es: " + edif.getPuntoSalud());
                                        }
                                    }
                                    mapa1.getPersonajes().get(comando[1]).atacar(edif);
                                }
                            } else if (!celda.getPersonajes().isEmpty()) {
                                System.out.println("Empieza la batalla");
                                Collection<Personaje> colp = mapa1.getPersonajes().values();
                                for (Personaje p : colp) {
                                    if (p.getPosicion().equals(pos2)) {
                                        if (0 >= p.getSalud() - (mapa1.getGrupos().get(comando[1]).getCapacidadAtaqueDefensa() - p.getArmadura())) {
                                            System.out.println("El personaje " + p.getCodigo() + " ha muerto");
                                            celda.getPersonajes().remove(p);
                                            mapa1.vermapacivilizacion();
                                            mapa1.getPersonajes().remove(p.getCodigo());
                                            return;
                                        } else if (0 < mapa1.getGrupos().get(comando[1]).getCapacidadAtaqueDefensa() - p.getArmadura()) {
                                            //System.out.println("Has inflingido un daño de " + (Grupos.get(a).getCapacidadAtaqueDefensa() - p.getArmadura()));
                                            try{
                                            p.setSalud(p.getSalud() - (mapa1.getGrupos().get(comando[1]).getCapacidadAtaqueDefensa() - p.getArmadura()));
                                        }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                            } else {
                                            System.out.println("Has inflingido un daño de " + 1);
                                            try{
                                            p.setSalud(p.getSalud() - (1));
                                            }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                        }
                                    }
                                }
                                mapa1.vermapacivilizacion();
                            } else if (celda.isGrupo()) {
                                List<Grupo> colg = new ArrayList<>(mapa1.getGrupos().values());
                                for (int j = colg.size() - 1; j >= 0; j--) {
                                    Grupo grup = colg.get(j);
                                    if (grup.getPosicion().equals(pos2)) {
                                        if (mapa1.getGrupos().get(comando[1]).getCapacidadAtaqueDefensa() > 0) {
                                            double ataque = mapa1.getGrupos().get(comando[1]).getCapacidadAtaqueDefensa() - grup.getCapacidadAtaqueDefensa();
                                            ataque = ataque / grup.getPersonajes().size();
                                            List<Personaje> colpg = new ArrayList<>(grup.getPersonajes().values());
                                            for (Personaje pg : colpg) {
                                                if (0 >= pg.getSalud() - ataque) {
                                                    System.out.println("El personaje " + pg.getCodigo() + " ha muerto");
                                                    grup.setCapacidadAtaqueDefensa(grup.getCapacidadAtaqueDefensa() - pg.getAtaque());
                                                    grup.getPersonajes().remove(pg.getCodigo());
                                                    if (grup.getPersonajes().isEmpty()) {
                                                        System.out.println("Has matado a " + grup.getCodigo());
                                                        mapa1.getMapa().get(grup.getPosicion().getX()).get(grup.getPosicion().getY()).setGrupo(false);
                                                        mapa1.getGrupos().remove(grup.getCodigo());
                                                        mapa1.vermapacivilizacion();
                                                        return;
                                                    }
                                                } else if (0 < ataque) {
                                                    try{
                                                    pg.setSalud(pg.getSalud() - ataque);
                                                    }catch(DatosExtepcion de){
                                                        de.ErrorDatos();
                                                    }
                                                    System.out.println("La salud de " + pg.getCodigo() + " es " + pg.getSalud());
                                                }
                                            }
                                        } else {
                                            System.out.println("El grupo no puede atacar porque esta compuesto unicamente por paisanos");
                                            throw(new AtacarException());
                                        }
                                    }
                                }
                                mapa1.vermapacivilizacion();
                                Personaje[] personas = new Personaje[celda.getPersonajes().size()];
                                for (int i = 0; i < celda.getPersonajes().size(); i++) {
                                    personas[i] = celda.getPersonajes().get(i);
                                }
                                mapa1.getPersonajes().get(comando[1]).atacar(personas);
                            } else {
                                System.out.println("Non podemos atacar a unha pradera");
                                throw (new AtacarException());
                            }
                            if (mapa1.TorreAdyacente(mapa1.getGrupos().get(comando[1]))) {
                                mapa1.AtacarTorre(mapa1.getGrupos().get(comando[1]));
                            }
                        } else {
                            System.out.println("Non podemos atacar fora do mapa");
                            throw (new LimiteExtepcion());
                        }
                    } else {
                        System.out.println("Este grupo no corresponde a nuestra civilizacion");
                        throw(new PGExtepcion());
                    }
                } else {
                    System.out.println("Este grupo no pertenece a esta civilización");
                    throw(new PGExtepcion());
                }
            }

    }
}
