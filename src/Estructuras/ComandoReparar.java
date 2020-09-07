/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Estructuras;

/**
 *
 * @author raul
 */
public class ComandoReparar implements Comando {

    Mapa mapa1;
    String[] comando;

    public ComandoReparar(String[] comando, Mapa mapa) {
        this.comando = comando;
        this.mapa1 = mapa;
    }

    @Override
    public void ejecutar() throws ComandoExtepcion,PGExtepcion,LimiteExtepcion{
        ConsolaNormal consola = new ConsolaNormal();
        if (comando.length != 3) {
            consola.imprimir("Error de sintaxis: reparar <personaje> <pto cardinal>\n");
        } else {
            Posicion pos1 = new Posicion();
        Posicion pos2 = new Posicion();
        if(mapa1.getPersonajes().containsKey(comando[1])){
            pos1=mapa1.getPersonajes().get(comando[1]).getPosicion();


            if(comando[2].equals("SUR") || comando[2].equals("sur")){
                pos2.setX(pos1.getX()+1);
                pos2.setY(pos1.getY());
            }

            else if(comando[2].equals("NORTE") || comando[2].equals("norte")){
                pos2.setX(pos1.getX()-1);
                pos2.setY(pos1.getY());
            }

            else if(comando[2].equals("ESTE") || comando[2].equals("este")){
                pos2.setX(pos1.getX());
                pos2.setY(pos1.getY()+1);
            }

            else if(comando[2].equals("OESTE") || comando[2].equals("oeste")){
                pos2.setX(pos1.getX());
                pos2.setY(pos1.getY()-1);
            }else{
                throw(new PuntoCardinalExtepcion());
            }
            
            if(pos2.getX()>=0 && pos2.getY()>=0){
                
                Celda celda=mapa1.getMapa().get(pos2.getX()).get(pos2.getY());
                
                if(celda.isContenedorDeRecursos()){
                    System.out.println("La celda destino es un contenedor de recurso");
                }
                else if(!celda.getPersonajes().isEmpty()){
                    System.out.println("En la celda hay personajes");
                }
                else if(celda.isEdificios()){
                    /*if(mapa1.getPersonajes().get(comando[1]).getRecoleccion() >= mapa1.getEdificios().get(pos2).getCosteReparacionMadera() && mapa1.getPersonajes().get(comando[1]).getRecursos().get(1) >= mapa1.getEdificios().get(pos2).getCosteReparacionPiedra()){
                        mapa1.getPersonajes().get(comando[1]).getRecursos().set(0, mapa1.getPersonajes().get(comando[1]).getRecursos().get(0)-mapa1.getEdificios().get(pos2).getCosteReparacionMadera());
                        mapa1.getPersonajes().get(comando[1]).getRecursos().set(1, mapa1.getPersonajes().get(comando[1]).getRecursos().get(1)-mapa1.getEdificios().get(pos2).getCosteReparacionPiedra());
                        mapa1.getEdificios().get(pos2).setPuntoSalud(2 * mapa1.getEdificios().get(pos2).getPuntoSalud());
                        System.out.println("Coste de la reparación: " + mapa1.getEdificios().get(pos2).getCosteReparacionMadera() + " unidades de madera y " + mapa1.getEdificios().get(pos2).getCosteReparacionPiedra() + " unidades de piedra");
                    }
                    else{
                        System.out.println("No se pudo reparar. Coste de la reparación: " + mapa1.getEdificios().get(pos2).getCosteReparacionMadera() + " unidades de madera y " + mapa1.getEdificios().get(pos2).getCosteReparacionPiedra() + " unidades de piedra");
                    }*/
                    mapa1.getPersonajes().get(comando[1]).reparar(mapa1.getEdificios().get(pos2));
                }
                else{
                    System.out.println("Posicion pasada incorrectamente");
                }
                
                
            }
            else{
                System.out.println("Posicion fuera del mapa");
                throw (new LimiteExtepcion());
            }
            
            
            
        }
        else{
            System.out.println("El personaje no existe");
        }        
        
        }
    }
}
