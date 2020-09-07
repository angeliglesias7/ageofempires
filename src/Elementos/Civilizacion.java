/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;
import java.util.HashMap;
import java.util.ArrayList;
import Estructuras.Posicion;
import Estructuras.Celda;
import Estructuras.Mapa;


public class Civilizacion {
    private String nombre;
    private HashMap<String,Personaje> Personajes;
    private HashMap<Posicion,Edificio> Edificios;
    private HashMap<String,Grupo> Grupos;
    private ArrayList<Celda> celdasvisibles;
    private  int madera;
    private int piedra;
    private int alimento;
    
    public Civilizacion(String nombre){
        this.nombre = nombre;
        Personajes=new HashMap<>();
        Edificios=new HashMap<>();
        Grupos =new HashMap<>();
        celdasvisibles=new ArrayList<>();
        piedra=0;
        alimento=0;
        madera=0;
    }
    public Civilizacion(String nombre,Personaje person, Edificio edif,ArrayList<ArrayList<Celda>> mapa){
        this.nombre=nombre;
        this.Personajes=new HashMap<>();
        Personajes.put(person.getCodigo(),person);
        this.Edificios=new HashMap<>();
        Edificios.put(edif.getPosicion(),edif);
        Grupos =new HashMap<>();
        celdasvisibles=new ArrayList<>();
        for(int i=0;i<mapa.size();i++){
            for(int j=0;j<mapa.get(0).size();j++){
                if(mapa.get(i).get(j).getPosicion().equals(person.getPosicion())||mapa.get(i).get(j).getPosicion().equals(edif.getPosicion())){
                celdasvisibles.add(mapa.get(i).get(j));
                if(i+1>=0 && i+1<mapa.size()){
                    //mapa.get(x+1).get(y).setVisible(true);
                    celdasvisibles.add(mapa.get(i+1).get(j));
                }
                if(i-1>=0 && i-1<mapa.size()){
                    //mapa.get(x-1).get(y).setVisible(true);
                    celdasvisibles.add(mapa.get(i-1).get(j));
                }
                if(j+1>=0 && j+1< mapa.get(0).size()){
                    //mapa.get(x).get(y+1).setVisible(true);
                    celdasvisibles.add(mapa.get(i).get(j+1));
                }
                if(j-1>=0 && j-1< mapa.get(0).size()){
                    //mapa.get(x).get(y-1).setVisible(true);
                    celdasvisibles.add(mapa.get(i).get(j-1));
                }
                }
            }
        }
        alimento=0;
        piedra=0;
        madera=0;
    }
    public String getNombre() {
        return nombre;
    }

    public int getMadera() {
        return madera;
    }

    public void setMadera(int madera) {
        this.madera = madera;
    }

    public int getPiedra() {
        return piedra;
    }

    public void setPiedra(int piedra) {
        this.piedra = piedra;
    }

    public int getAlimento() {
        return alimento;
    }

    public void setAlimento(int alimento) {
        this.alimento = alimento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<String, Personaje> getPersonajes() {
        return Personajes;
    }

    public void setPersonajes(HashMap<String,Personaje> Personajes) {
        this.Personajes = Personajes;
    }

    public HashMap<Posicion,Edificio> getEdificios() {
        return Edificios;
    }

    public void setEdificios(HashMap<Posicion,Edificio> Edificios) {
        this.Edificios = Edificios;
    }

    public ArrayList<Celda> getCeldasvisibles() {
        return celdasvisibles;
    }

    public void setCeldasvisibles(ArrayList<Celda> celdasvisibles) {
        this.celdasvisibles = celdasvisibles;
    }

    public HashMap<String, Grupo> getGrupos() {
        return Grupos;
    }

    public void setGrupos(HashMap<String, Grupo> Grupos) {
        this.Grupos = Grupos;
    }
    public void anadirPersonaje(Personaje p1,ArrayList<ArrayList<Celda>> mapa){
        Personajes.put(p1.getCodigo(),p1);
        for(int i=0;i<mapa.size();i++){
            for(int j=0;j<mapa.get(0).size();j++){
                if(mapa.get(i).get(j).getPosicion().equals(p1.getPosicion())){
                    celdasvisibles.add(mapa.get(i).get(j));
                if(i+1>=0 && i+1<mapa.size()){
                    //mapa.get(x+1).get(y).setVisible(true);
                    celdasvisibles.add(mapa.get(i+1).get(j));
                }
                if(i-1>=0 && i-1<mapa.size()){
                    //mapa.get(x-1).get(y).setVisible(true);
                    celdasvisibles.add(mapa.get(i-1).get(j));
                }
                if(j+1>=0 && j+1< mapa.get(0).size()){
                    //mapa.get(x).get(y+1).setVisible(true);
                    celdasvisibles.add(mapa.get(i).get(j+1));
                }
                if(j-1>=0 && j-1< mapa.get(0).size()){
                    //mapa.get(x).get(y-1).setVisible(true);
                    celdasvisibles.add(mapa.get(i).get(j-1));
                }
                }
            }
        }
    }
    public void anadirEdificio(Edificio e1,ArrayList<ArrayList<Celda>> mapa){
        Edificios.put(e1.getPosicion(),e1);
        celdasvisibles.add(mapa.get(e1.getPosicion().getX()).get(e1.getPosicion().getY()));
        for(int i=0;i<mapa.size();i++){
            for(int j=0;j<mapa.get(0).size();j++){
                if(mapa.get(i).get(j).getPosicion().equals(e1.getPosicion())){
                celdasvisibles.add(mapa.get(i).get(j));
                if(i+1>=0 && i+1<mapa.size()){
                    //mapa.get(x+1).get(y).setVisible(true);
                    celdasvisibles.add(mapa.get(i+1).get(j));
                }
                if(i-1>=0 && i-1<mapa.size()){
                    //mapa.get(x-1).get(y).setVisible(true);
                    celdasvisibles.add(mapa.get(i-1).get(j));
                }
                if(j+1>=0 && j+1< mapa.get(0).size()){
                    //mapa.get(x).get(y+1).setVisible(true);
                    celdasvisibles.add(mapa.get(i).get(j+1));
                }
                if(j-1>=0 && j-1< mapa.get(0).size()){
                    //mapa.get(x).get(y-1).setVisible(true);
                    celdasvisibles.add(mapa.get(i).get(j-1));
                }
                }
            }
        }
    }
    public void anadirGrupo(Grupo g1,ArrayList<ArrayList<Celda>> mapa){
        Grupos.put(g1.getCodigo(),g1);
        celdasvisibles.add(mapa.get(g1.getPosicion().getX()).get(g1.getPosicion().getY()));
        for(int i=0;i<mapa.size();i++){
            for(int j=0;j<mapa.get(0).size();j++){
                if(mapa.get(i).get(j).getPosicion().equals(g1.getPosicion())){
                celdasvisibles.add(mapa.get(i).get(j));
                if(i+1>=0 && i+1<mapa.size()){
                    //mapa.get(x+1).get(y).setVisible(true);
                    celdasvisibles.add(mapa.get(i+1).get(j));
                }
                if(i-1>=0 && i-1<mapa.size()){
                    //mapa.get(x-1).get(y).setVisible(true);
                    celdasvisibles.add(mapa.get(i-1).get(j));
                }
                if(j+1>=0 && j+1< mapa.get(0).size()){
                    //mapa.get(x).get(y+1).setVisible(true);
                    celdasvisibles.add(mapa.get(i).get(j+1));
                }
                if(j-1>=0 && j-1< mapa.get(0).size()){
                    //mapa.get(x).get(y-1).setVisible(true);
                    celdasvisibles.add(mapa.get(i).get(j-1));
                }
                }
            }
        }
    }
}





















