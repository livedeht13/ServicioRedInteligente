/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package red_inteligente;

/**
 *
 * @author Alfonso
 */
public class senalizacion {
    String direccion = "1011010";
    String relleno = "101101";
    String tipo = "";
    String MSU = null;
    String msu;
    
    public void recive(){
         msu = "101010101010101010";
    }
    
    public String process(String rmsu){
         String tipo = rmsu.substring(1,4);
         String dir = rmsu.substring(5);
         String dir2 =null;
         if("1".equals(dir))
             dir2="0";
         else
             dir2="1";
         if("0000".equals(tipo))//FISSU;
            MSU="0000"+dir; //FISSU;
         if("1111".equals(tipo))//"Descuelga");
            MSU="1101"+dir; // Invitacion a marcar;
         
         
         if("0111".equals(tipo))//"Señal de dirección completa, con cargo");
            MSU="0100"+dir2; //Solicita info // tono de espera
         if("1001".equals(tipo))//"Abonado ocupado");
            MSU="1001"+dir; //Tono ocupado
         if("0110".equals(tipo))//"Abonado Libre");
            MSU="0110"+dir; //LISTO
         if("0001".equals(tipo))//"Colgar_ No incluye medio supresor de eco de entrada");
            MSU="1111"+dir2; // Ocupado
         if("0010".equals(tipo))//"Se include medio supresor de eco de entrada");
            MSU="1010";
         if("0011".equals(tipo))//"Llamada no hacia adelante");
            MSU="0101";
         if("1100".equals(tipo))//"Llamada hacia adelante");
            MSU="0001";
         if("0101".equals(tipo))//"Cualquier tipo de trayectoria");
            MSU="1000";
         return MSU;
    }
    
           
}
