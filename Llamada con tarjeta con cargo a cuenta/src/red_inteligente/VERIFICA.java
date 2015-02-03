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
public class VERIFICA {
    
    public boolean verificar(String numero, int longitud){
        if( numero.length() == longitud )
            return true;
        else
            return false;
    }    
    
}
