/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Estructuras;


import java.util.Scanner;

/**
 *
 * @author angel
 */
public class ConsolaNormal implements Consola{
    @Override
    public void imprimir(String mensaje){
        System.out.println(mensaje);
    }
    @Override
    public String leer(String descripcion){
       ConsolaNormal consola=new ConsolaNormal();
       Scanner scanner = new Scanner(System.in);
       consola.imprimir(descripcion);
       String linea = scanner.nextLine();
       return linea;
    }
}
