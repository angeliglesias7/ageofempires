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
public class ComandoMirar implements Comando{
    Mapa mapa1;
    String[] comando;
    public  ComandoMirar(String[] comando,Mapa mapa){
        this.comando=comando;
        this.mapa1=mapa;
    }

    @Override
    public void ejecutar() {
        try{
            String junto = new String();
        //junto = String.join(" ", comando);
        junto = junto.replaceAll("\\s", "");
        String subStr0 = junto.substring(6, 7);
        String subStr1 = junto.substring(8, 9);

        mapa1.mirarCelda(subStr0, subStr1);
        }catch(Exception e){
            e.getMessage();
        }
        
    }
}
