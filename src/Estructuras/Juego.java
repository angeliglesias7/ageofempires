/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Estructuras;

import java.util.Scanner;



public class Juego {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ConsolaNormal consola1=new ConsolaNormal();
       consola1.imprimir("Mapa preestablecido pulse (1)");
       consola1.imprimir("Mapa leido pulse (2)");
       String tipo=consola1.leer("Introduce opción 1 o 2");
       Mapa mapa1=null;
       if(tipo.equals("1")){
         mapa1 = new Mapa(10,10);
       }else if(tipo.equals("2")){
            mapa1=new Mapa();
       }
        boolean seguir = true;
        //Scanner scanner = new Scanner(System.in);
        
        while(seguir){
            /*System.out.print("$ ");
            String linea = scanner.nextLine();*/
            ConsolaNormal consola=new ConsolaNormal();
            String linea=consola.leer("$");
            String[] comando = linea.split(" ");
            switch (comando[0]){
                case "salir":
                    seguir = false;
                    break;
                case "listar":
                    try{
                    ComandoListar comlis= new ComandoListar(comando,mapa1);
                    comlis.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }
                    break;
                case "describir":
                    try{
                    ComandoDescribir comdes= new ComandoDescribir(comando,mapa1);
                    comdes.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }
                    break;
                case "mover":
                    try{
                    ComandoMover commov= new ComandoMover(comando,mapa1);
                    commov.ejecutar();
                    }catch(MoverExtepcion me){
                        me.MoverExtepcion();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }catch(LimiteExtepcion le){
                        le.ErrorLimite();
                    }
                    break;
                case "mirar":
                    ComandoMirar commir= new ComandoMirar(comando,mapa1);
                    commir.ejecutar();
                    break;
                case "construir":
                    try{
                    ComandoConstruir comcon= new ComandoConstruir(comando,mapa1);
                    comcon.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }catch(ConstruirExtepcion ce){
                        ce.ErrorConstruir();
                    }catch(LimiteExtepcion le){
                        le.ErrorLimite();
                    }
                    break;
                case "reparar":
                    try{
                    ComandoReparar comrep= new ComandoReparar(comando,mapa1);
                    comrep.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }catch(LimiteExtepcion le){
                        le.ErrorLimite();
                    }
                    break;
                case "crear":
                    try{
                    ComandoCrear comcre= new ComandoCrear(comando,mapa1);
                    comcre.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }catch(CrearExtepcion ce){
                        ce.ErrorCrear();
                    }catch(LimiteExtepcion le){
                        le.ErrorLimite();
                    }
                    break;
                case "recolectar":
                    try{
                    ComandoRecolectar comrec= new ComandoRecolectar(comando,mapa1);
                    comrec.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }
                    break;
                case "almacenar":
                    try{
                    ComandoAlmacenar comalm= new ComandoAlmacenar(comando,mapa1);
                    comalm.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }catch(AlmacenarExtepcion ae){
                        ae.ErrorAlmacenar();
                    }catch(LimiteExtepcion le){
                        le.ErrorLimite();
                    }
                    break;
                case "agrupar":
                    try{
                    ComandoAgrupar comagr= new ComandoAgrupar(comando,mapa1);
                    comagr.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }
                    break;
                    
                case "desligar":
                    try{
                    ComandoDesligar comdesl= new ComandoDesligar(comando,mapa1);
                    comdesl.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }
                    break;
                case "desagrupar":
                    try{
                    ComandoDesagrupar comdesg= new ComandoDesagrupar(comando,mapa1);
                    comdesg.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }
                    break;
                case "atacar":
                    try{
                    ComandoAtacar comata= new ComandoAtacar(comando,mapa1);
                    comata.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }catch(AtacarException ae){
                        ae.ErrorAtacar();
                    }catch(LimiteExtepcion le){
                        le.ErrorLimite();
                    }
                    break;
                case "defender":
                    try{
                    ComandoDefender comdef= new ComandoDefender(comando,mapa1);
                    comdef.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }catch(DefenderExtepcion de){
                        de.ErrorDefender();
                    }catch(LimiteExtepcion le){
                        le.ErrorLimite();
                    }
                    break;
                case "cambiar":
                    try{
                    ComandoCambiar comcam= new ComandoCambiar(comando,mapa1);
                    comcam.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }
                    break;
                case "civilizacion":
                    try{
                    ComandoCivilización comciv= new ComandoCivilización(comando,mapa1);
                    comciv.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }
                    break;
                case "imprimir":
                    try{
                    ComandoImprimir comimp= new ComandoImprimir(comando,mapa1);
                    comimp.ejecutar();
                    }catch(ComandoExtepcion ce){
                        ce.ErrorComando();
                    }
                    break;
                default:
                    consola.imprimir("Comando no existe");
                    break;
            }
        }
    }
    
}
