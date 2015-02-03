/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package red_inteligente;

import static java.lang.System.exit;

/**
 *
 * @author Alfonso
 */
public class SCF {
    
    public boolean verificar(String numero, int longitud){
        if( numero.length() == longitud )
            return true;
        else
            return false;
    }
    
    public int tarificar(){
        int costo = 0;
        int tiempo = 0;
        int precio_serv = 0;
            //Hilo 
        costo = tiempo * precio_serv;
        return costo;
    }
    
    public void POR(int op){
        if(op == 1)
        {
            System.out.println("ERROR");
            System.exit(0);//terminar proceso
        }
        if(op == 2)
        {
            //Iniciar llamada
            System.out.println("Iniciar llamada");
        }
    }
    
    
    
}
