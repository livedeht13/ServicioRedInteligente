/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package red_inteligente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alfonso
 */
public class SDF {
     
    public boolean cribado(String numero, String base_datos, String col){
        System.out.println("Buscando "+numero+" en la base de datos");
        ConexionBD con= new ConexionBD();
        con.getPostgresConnection();
        String consulta="select * from "+base_datos+" where "+col+" = '"+numero+"'";
        ResultSet tabla=null;
        try {
            tabla=con.ObtieneRS(consulta,con.getPostgresConnection());
        } catch (Exception ex) {
            Logger.getLogger(SDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        String tipo = "0";
        try {
            while (tabla.next())
            {
                tipo=tabla.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
        if( !"0".equals(tipo) )
            return true;
        else
            return false;
    }
    
    public boolean cribado(String tarnum, String numero, String base_datos, String tar, String col){
        System.out.println("Buscando "+numero+" en la base de datos");
        ConexionBD con= new ConexionBD();
        con.getPostgresConnection();
        String consulta="select * from "+base_datos+" where "+tar+" = '"+tarnum+"' and "+col+"='"+numero+"'";
        ResultSet tabla=null;
        try {
            tabla=con.ObtieneRS(consulta,con.getPostgresConnection());
        } catch (Exception ex) {
            Logger.getLogger(SDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        String tipo = "0";
        try {
            while (tabla.next())
            {
                tipo=tabla.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
        if( !"0".equals(tipo) )
            return true;
        else
            return false;
    }
    
    
}
