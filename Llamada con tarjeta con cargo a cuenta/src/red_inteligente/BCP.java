/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package red_inteligente;

import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author Alfonso
 */
public class BCP {
    String tarjeta = "0";
    String pin = "0";
    String numero = "0";
    SSF_CCF ssf_ccf = new SSF_CCF();
    SCF scf = new SCF();
    SDF sdf = new SDF();
    SRF srf = new SRF();
    boolean ver = true;
    // Sería un HILO
    
    public void bcp() {
        
    ssf_ccf.iniciar();
    tarjeta = ssf_ccf.leer();
    
    ver = scf.verificar(tarjeta,10);
    if(ver == false)
        scf.POR(1);
    
    ver = sdf.cribado(tarjeta, "usuarios","tarjeta");
    if(ver == false){
        srf.inter_user_sol(1);
        srf.POR(1);
    }
    
    pin = srf.inter_user_ing(1);
    
    ver = scf.verificar(pin,5);
    if(ver == false)
        scf.POR(1);
    
    ver = sdf.cribado(tarjeta, pin, "usuarios","tarjeta","pin");
    if(ver == false)
    {
        srf.inter_user_sol(2);
        srf.POR(1);
    }
    
    numero = srf.inter_user_ing(2);
    
    ver = scf.verificar(numero,12);
    if(ver == false)
        scf.POR(1);
    
    ver = sdf.cribado(numero, "telefonos","numero");
    if(ver == false){
        srf.inter_user_sol(3);
        scf.POR(1);
    }
    scf.POR(2);
    // ESTABLECER LLAMADA*/
    }
}
