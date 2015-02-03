package red_inteligente; 
 
import java.sql.*; 

public class ConexionBD {
    
    java.sql.Connection conn = null;   
     public java.sql.Connection getPostgresConnection() 
    {
         try
         {
           String servidor = "localhost";
           Class.forName("org.postgresql.Driver"); 
           /**String url = "jdbc:postgresql://host:puerto/nombre_de_la_base_de_datos" ; **/
		   String url = "jdbc:postgresql://localhost:5432/llamada_con_tarjeta" ;
           /**conn = DriverManager.getConnection(url, "usrename", "password"); **/
		   conn = DriverManager.getConnection(url, "postgres", "livedeht13"); 
           if(conn != null)
  		   System.out.println("CONECTADO A EL SERVIDOR:" + servidor);
           else
              System.out.println("NO ES POSIBLE CONECTAR A EL SERVIDOR: "+ servidor);
           return conn;
         }
         catch(Exception e)
         {  
            e.printStackTrace();  
            System.out.println("ERROR = "+e);
            return null;
         }
      }
    
	public ResultSet ObtieneRS(String queryString, java.sql.Connection conn) throws SQLException,Exception{
         ResultSet rs=null;
         String error;
         
             Statement stmt = conn.createStatement();
             rs = stmt.executeQuery(queryString);
          return rs;
     
     }
     
     public void EjecutaUpdate(String queryString, java.sql.Connection conn) throws SQLException,Exception{
             Statement stmt = conn.createStatement();
             stmt.execute(queryString);
     }
      
     public void close()
     {
         try
         {
           conn.close();
         }
         catch (Exception e)
         {
           e.printStackTrace();
         }
     }
	         
}
